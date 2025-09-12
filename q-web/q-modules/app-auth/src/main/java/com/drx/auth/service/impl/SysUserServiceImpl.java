package com.drx.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.auth.pojo.dto.LoginDTO;
import com.drx.auth.pojo.dto.RegisterDTO;
import com.drx.auth.service.SysUserService;
import com.drx.base.entity.User;
import com.drx.base.enums.USER_ROLE;
import com.drx.base.enums.USER_STATE;
import com.drx.base.tools.context.UserContext;
import com.drx.starter.tools.JwtTool;
import com.drx.starter.tools.TokenGeneratorTool;
import com.drx.starter.entity.SysUser;
import com.drx.starter.mapper.SysUserMapper;
import com.drx.starter.repository.RedisService;
import com.drx.starter.tools.BcryptTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final RedisService redisService;
    @Value("${jwt.secret}")
    private String tokenSecret;

    public SysUserServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public String login(LoginDTO dto) {
        LambdaQueryWrapper<SysUser> userWra = new LambdaQueryWrapper<>();
        userWra.eq(SysUser::getUsername, dto.getUsername());
        SysUser sysUser = baseMapper.selectOne(userWra);
        Assert.notNull(sysUser, "用户名或密码错误");
        Assert.isTrue(sysUser.getState().equals(USER_STATE.NORMAL.getKey()) && BcryptTool.matches(dto.getPassword(), sysUser.getPassword()), "用户名或密码错误");
        HashMap<String, String> result = new HashMap<>();
        result.put("id", sysUser.getId().toString());
        String session = TokenGeneratorTool.generateSecureToken(32);
        result.put("session", session);
        redisService.setValue(String.format("session_%s", sysUser.getId()), session, (long) (3 * 24 * 60 * 60));
        return JwtTool.createToken(result, tokenSecret, (long) (3 * 24 * 60 * 60 * 1000));
    }

    @Override
    public void logout() {
        redisService.remove(String.format("session_%s", UserContext.get().getId()));
    }

    @Override
    public void register(RegisterDTO dto) {
        LambdaQueryWrapper<SysUser> nameWra = new LambdaQueryWrapper<>();
        nameWra.eq(SysUser::getUsername, dto.getUsername());
        SysUser nonUser = baseMapper.selectOne(nameWra);
        Assert.isNull(nonUser, "用户名已存在");
        dto.setPassword(BcryptTool.encrypt(dto.getPassword()));
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        Long count = baseMapper.selectCount(null);
        if (count.equals(0L)) {
            user.setRole(USER_ROLE.ADMINISTRATOR.getKey());
        }
        user.setCreatedBy("register");
        int insert = baseMapper.insert(user);
        Assert.isTrue(insert == 1, "注册失败");
    }

    @Override
    public void signOut() {
        User user = UserContext.get();
        SysUser sysUser = new SysUser();
        sysUser.setId(user.getId());
        sysUser.setState(USER_STATE.DISABLED.getKey());
        int i = baseMapper.updateById(sysUser);
        Assert.isTrue(i == 1, "账户注销失败");
    }
}

package com.drx.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.auth.dto.LoginDTO;
import com.drx.auth.service.SysUserService;
import com.drx.cache.repository.CacheClient;
import com.drx.core.tools.BcryptTool;
import com.drx.core.tools.JwtTool;
import com.drx.core.tools.TokenGeneratorTool;
import com.drx.db.entity.SysUser;
import com.drx.db.mapper.SysUserMapper;
import com.drx.security.context.UserContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final CacheClient cacheClient;
    @Value("${jwt.secret}")
    private String tokenSecret;

    public SysUserServiceImpl(CacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    @Override
    public String login(LoginDTO dto) {
        LambdaQueryWrapper<SysUser> userWra = new LambdaQueryWrapper<>();
        userWra.eq(SysUser::getUsername, dto.getUsername());
        SysUser sysUser = baseMapper.selectOne(userWra);
        Assert.notNull(sysUser, "用户名或密码错误");
        Assert.isTrue(BcryptTool.matches(dto.getPassword(), sysUser.getPassword()), "用户名或密码错误");
        Assert.isTrue(sysUser.getState().equals("1"), "账户已禁用");
        HashMap<String, String> result = new HashMap<>();
        result.put("id", sysUser.getId());
        String session = TokenGeneratorTool.generateSecureToken(32);
        result.put("session", session);
        cacheClient.setValue(String.format("session_%s", sysUser.getId()), session, (long) (3 * 24 * 60 * 60));
        return JwtTool.createToken(result, tokenSecret, (long) (3 * 24 * 60 * 60));
    }

    @Override
    public void logout() {
        cacheClient.remove(String.format("session_%s", UserContext.get().getId()));
    }

}

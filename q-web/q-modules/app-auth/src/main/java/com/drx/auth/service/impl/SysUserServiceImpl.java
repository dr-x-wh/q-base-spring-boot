package com.drx.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.auth.pojo.form.LoginForm;
import com.drx.auth.service.SysUserService;
import com.drx.cache.repository.CacheClient;
import com.drx.db.entity.SysUser;
import com.drx.db.mapper.SysUserMapper;
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
    public String login(LoginForm form) {
        LambdaQueryWrapper<SysUser> userWra = new LambdaQueryWrapper<>();
        userWra.eq(SysUser::getUsername, form.getUsername());
        SysUser sysUser = baseMapper.selectOne(userWra);
        Assert.notNull(sysUser, "用户名或密码错误");
        Assert.isTrue(BcryptTool.matches(form.getPassword(), sysUser.getPassword()), "用户名或密码错误");
        Assert.isTrue(sysUser.getState().equals("1"), "账户已禁用");
        HashMap<String, String> result = new HashMap<>();
        result.put("id", sysUser.getId().toString());
        String session = TokenGeneratorTool.generateSecureToken(32);
        result.put("session", session);
        redisService.setValue(String.format("session_%s", sysUser.getId()), session, (long) (3 * 24 * 60 * 60));
        return JwtTool.createToken(result, tokenSecret, (long) (3 * 24 * 60 * 60));
    }

    @Override
    public void logout() {
        redisService.remove(String.format("session_%s", UserContext.get().getId()));
    }

}

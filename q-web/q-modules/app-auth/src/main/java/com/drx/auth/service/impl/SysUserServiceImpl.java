package com.drx.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drx.auth.pojo.dto.LoginDTO;
import com.drx.auth.service.SysUserService;
import com.drx.base.tools.security.JwtTool;
import com.drx.base.tools.security.TokenGenerator;
import com.drx.starter.entity.SysUser;
import com.drx.starter.mapper.SysUserMapper;
import com.drx.starter.repository.RedisService;
import com.drx.starter.tools.BcryptTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Value("${jwt.secret}")
    private String tokenSecret;

    private final RedisService redisService;

    public SysUserServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public String login(LoginDTO dto) {
        LambdaQueryWrapper<SysUser> userWra = new LambdaQueryWrapper<>();
        userWra.eq(SysUser::getUsername, dto.getUsername());
        SysUser sysUser = baseMapper.selectOne(userWra);
        Assert.notNull(sysUser, "用户名或密码错误");
        Assert.isTrue(BcryptTool.matches(dto.getPassword(), sysUser.getPassword()), "用户名或密码错误");
        HashMap<String, String> result = new HashMap<>();
        result.put("id", sysUser.getId().toString());
        String session = TokenGenerator.generateSecureToken(32);
        result.put("session", session);
        redisService.setValue(String.format("session_%s", sysUser.getId()), session, (long) (3 * 24 * 60 * 60));
        return JwtTool.createToken(result, tokenSecret, (long) (3 * 24 * 60 * 60 * 1000));
    }
}

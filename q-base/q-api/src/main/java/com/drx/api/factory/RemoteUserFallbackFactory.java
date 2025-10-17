package com.drx.api.factory;

import com.drx.api.service.RemoteUserService;
import com.drx.api.domain.LoginUser;
import com.drx.core.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public Result<LoginUser> getUserInfo(String id, String password) {
                return Result.error("获取用户失败: " + throwable.getMessage());
            }
        };
    }
}

package com.drx.api.service;

import com.drx.api.domain.LoginUser;
import com.drx.core.constant.SecurityConstant;
import com.drx.core.constant.ServiceNameConstant;
import com.drx.core.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "remoteUserService", value = ServiceNameConstant.SYSTEM_SERVICE)
public interface RemoteUserService {

    @GetMapping("/user/inner/{id}")
    Result<LoginUser> getUserInfo(@PathVariable("id") String username, @RequestHeader(SecurityConstant.INNER_HEADER) String source);

}

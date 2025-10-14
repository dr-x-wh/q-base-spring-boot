package com.drx.api;

import com.drx.api.domain.LoginUser;
import com.drx.base.constant.SecurityConstant;
import com.drx.base.constant.ServiceNameConstant;
import com.drx.base.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "remoteUserService", value = ServiceNameConstant.SYSTEM_SERVICE)
public interface RemoteUserService {

    @GetMapping("/user/inner/{username}")
    Result<LoginUser> getUserInfoByInner(@PathVariable("username") String username, @RequestHeader(SecurityConstant.INNER_HEADER) String source);

}

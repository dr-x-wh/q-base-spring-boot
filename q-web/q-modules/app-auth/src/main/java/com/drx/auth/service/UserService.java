package com.drx.auth.service;

import com.drx.auth.dto.RegisterDTO;

public interface UserService {

    boolean save(RegisterDTO dto);

}

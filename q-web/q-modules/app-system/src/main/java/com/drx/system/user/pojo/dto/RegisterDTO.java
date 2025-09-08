package com.drx.system.user.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String nickname;
    @NotBlank
    private String gender;
    private String avatar_url;

}

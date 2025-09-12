package com.drx.system.user.pojo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String nickname;
    private String avatar_url;
    @NotBlank
    private String gender;

}

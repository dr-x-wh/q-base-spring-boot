package com.drx.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String avatarUrl;

    @NotBlank
    private String gender;

}

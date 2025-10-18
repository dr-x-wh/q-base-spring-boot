package com.drx.auth.result;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInfo {

    private String id;
    private String username;
    private String nickname;
    private String avatarUrl;
    private String gender;

}

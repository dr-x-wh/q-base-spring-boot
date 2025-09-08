package com.drx.starter.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptTool {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encrypt(String raw) {
        return encoder.encode(raw);
    }

    public static boolean matches(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }

}

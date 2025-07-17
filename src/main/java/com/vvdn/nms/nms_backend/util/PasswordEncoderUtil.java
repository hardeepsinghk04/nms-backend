package com.vvdn.nms.nms_backend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderUtil {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String hash(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public boolean matches(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}

package com.vvdn.nms.nms_backend.util;

public class EncryptPasswordTest {
    public static void main(String[] args) {
        String plain = "Test@1234";  // 👈 Your real password here
        String encrypted = AesEncryptionUtil.encrypt(plain);
        System.out.println("Encrypted password: " + encrypted);
    }
}

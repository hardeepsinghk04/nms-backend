package com.vvdn.nms.nms_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Must be a valid email address")
    private String email;

    @NotBlank(message = "Password must not be blank")
    private String userPassword;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
}

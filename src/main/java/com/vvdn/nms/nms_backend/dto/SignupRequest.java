package com.vvdn.nms.nms_backend.dto;

import jakarta.validation.constraints.*;

public class SignupRequest {

    @NotBlank(message = "Customer type is required")
    @Pattern(regexp = "^(customer|admin)$", message = "Invalid customerType")
    private String customerType;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$",
            message = "Password must contain uppercase, lowercase, number, and special character"
    )
    private String userPassword;

    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
    private String lastName;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must be 2 uppercase letters")
    private String isoCountry;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digit numeric")
    private String phone;

    // Getters and Setters
    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

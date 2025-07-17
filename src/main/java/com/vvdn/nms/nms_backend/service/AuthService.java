package com.vvdn.nms.nms_backend.service;

import com.vvdn.nms.nms_backend.dto.LoginRequest;
import com.vvdn.nms.nms_backend.dto.SignupRequest;
import com.vvdn.nms.nms_backend.dto.ApiResponse;
import com.vvdn.nms.nms_backend.model.User;
import com.vvdn.nms.nms_backend.repository.UserRepository;
import com.vvdn.nms.nms_backend.util.AesEncryptionUtil;
import com.vvdn.nms.nms_backend.util.PasswordEncoderUtil;
import com.vvdn.nms.nms_backend.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoderUtil pwdUtil;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepo, PasswordEncoderUtil pwdUtil, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.pwdUtil = pwdUtil;
        this.jwtUtil = jwtUtil;
    }
    private boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }


    /**
     * Handles user registration with AES-decrypted password + BCrypt hashing.
     */
    public ApiResponse<User> signup(SignupRequest req) {
        if (userRepo.existsByEmail(req.getEmail()))
            return new ApiResponse<>(false, "12506", "Email already registered", null);

        try {
            String decryptedPassword = req.getUserPassword();
            String decryptedConfirmPassword = req.getConfirmPassword();

            if (!decryptedPassword.equals(decryptedConfirmPassword))
                return new ApiResponse<>(false, "12507", "Passwords must match", null);

            // ✅ Validate password strength manually after decryption
            if (!isStrongPassword(decryptedPassword)) {
                return new ApiResponse<>(false, "VALIDATION_FAILED",
                        "Password must contain uppercase, lowercase, number, and special character", null);
            }

            String hashed = pwdUtil.hash(decryptedPassword);

            Set<String> roles = "admin".equalsIgnoreCase(req.getCustomerType()) ?
                    Set.of("ROLE_ADMIN") : Set.of("ROLE_USER");

            User u = new User(
                    null,
                    req.getCustomerType(),
                    req.getEmail(),
                    hashed,
                    req.getFirstName(),
                    req.getLastName(),
                    req.getIsoCountry(),
                    req.getPhone(),
                    roles
            );

            userRepo.save(u);
            return new ApiResponse<>(true, "12508", "Success user signup", u);

        } catch (Exception e) {
            return new ApiResponse<>(false, "12511", "Password decryption failed: " + e.getMessage(), null);
        }
    }


    /**
     * Handles login: decrypts AES password, compares hash, and generates JWT.
     */
    public ApiResponse<String> login(LoginRequest req) {
        var opt = userRepo.findByEmail(req.getEmail());

        if (opt.isEmpty()) {
            return new ApiResponse<>(false, "12509", "Invalid credentials", null);
        }

        try {

            String plainPassword = req.getUserPassword();

            if (!pwdUtil.matches(plainPassword, opt.get().getPassword())) {
                return new ApiResponse<>(false, "12509", "Invalid credentials", null);
            }

            String token = jwtUtil.generateToken(req.getEmail());
            return new ApiResponse<>(true, "12510", "Login successful", token);

        } catch (Exception e) {
            return new ApiResponse<>(false, "12512", "Login failed: " + e.getMessage(), null);
        }
    }

}

package com.vvdn.nms.nms_backend.controller;

import com.vvdn.nms.nms_backend.dto.LoginRequest;
import com.vvdn.nms.nms_backend.dto.SignupRequest;
import com.vvdn.nms.nms_backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nms/user/v1")
@RequiredArgsConstructor
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest req) {
        var resp = authService.signup(req);
        return ResponseEntity
                .status(resp.isStatus() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        var resp = authService.login(req);
        return ResponseEntity
                .status(resp.isStatus() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED)
                .body(resp);
    }
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userOnly() {
        return "Welcome USER!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String adminOnly() {
        return "Welcome ADMIN!";
    }


}

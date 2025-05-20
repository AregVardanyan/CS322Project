package org.example.cs322project.controller;

import lombok.RequiredArgsConstructor;
import org.example.cs322project.model.dto.AuthRequest;
import org.example.cs322project.model.dto.RegisterRequest;
import org.example.cs322project.security.JwtUtil;
import org.example.cs322project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Map<String,String> map = userService.loginJWT(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(map);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        if (!jwtUtil.isVerified(refreshToken)) {
            return ResponseEntity.status(401).body("Invalid refresh token");
        }
        return ResponseEntity.ok(Map.of(
                "accessToken", userService.refreshJWT(refreshToken)
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        userService.registerJWT(request);
        return ResponseEntity.ok("User registered successfully");
    }



}

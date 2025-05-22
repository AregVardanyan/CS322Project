package org.example.cs322project.service;

import lombok.RequiredArgsConstructor;

import org.example.cs322project.model.User;
import org.example.cs322project.model.dto.RegisterRequest;
import org.example.cs322project.repository.UserRepository;
import org.example.cs322project.security.JwtUtil;
import org.example.cs322project.security.model.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;


    public void registerJWT(RegisterRequest request) {
        if (userRepository.existsByUsername((request.getUsername()))) {
            throw new RuntimeException("Email already taken");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    public String refreshJWT(String refreshToken) {

        String username = jwtUtil.getUsername(refreshToken);
        UserPrincipal user = (UserPrincipal) userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateAccessToken(user);
    }

    public Map<String, String> loginJWT(String username, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserPrincipal user = (UserPrincipal) auth.getPrincipal();
        return new HashMap<>(Map.of(
                "accessToken", jwtUtil.generateAccessToken(user),
                "refreshToken", jwtUtil.generateRefreshToken(user)));
    }
}

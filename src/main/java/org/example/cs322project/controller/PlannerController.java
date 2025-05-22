package org.example.cs322project.controller;

import lombok.RequiredArgsConstructor;
import org.example.cs322project.model.dto.PromptDto;
import org.example.cs322project.security.JwtUtil;
import org.example.cs322project.security.model.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.example.cs322project.service.PlannerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlannerController {

    private final PlannerService plannerService;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping("/plan")
    public String generatePlan(@RequestBody PromptDto promptDto,
                               @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;

        String username = jwtUtil.getUsername(token);
        UserPrincipal user = (UserPrincipal) userDetailsService.loadUserByUsername(username);

        return plannerService.generatePlan(user.getUser().getId(), promptDto);
    }
}


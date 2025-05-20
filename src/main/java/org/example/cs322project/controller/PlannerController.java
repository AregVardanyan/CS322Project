package org.example.cs322project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.cs322project.service.PlannerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping("/plan")
    public String generatePlan(@RequestBody StudentPreferences preferences) {
        return plannerService.generatePlan(preferences);
    }
}


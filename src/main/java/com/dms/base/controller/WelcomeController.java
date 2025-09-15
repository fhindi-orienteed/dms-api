package com.dms.base.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Delivery management system");
        response.put("version", "v1.0");
        response.put("status", "running");
        response.put("Environment", "Production");
        response.put("docs", "/swagger-ui/index.html");
        return response;
    }
}

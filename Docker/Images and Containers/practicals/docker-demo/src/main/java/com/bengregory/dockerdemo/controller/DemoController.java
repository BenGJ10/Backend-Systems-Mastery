package com.bengregory.dockerdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of(
                "app", "Docker Demo",
                "backend", "Spring Boot",
                "javaVersion", System.getProperty("java.version"),
                "message", "This is running inside Docker"
        );
    }

}

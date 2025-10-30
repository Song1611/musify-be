package com.xuansong.musifyapi.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class RootController {
    @GetMapping
    public String healthyCheck() {
        return "OK";
    }
}

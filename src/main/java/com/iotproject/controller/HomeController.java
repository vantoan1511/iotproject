package com.iotproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHome() {
        return "index";
    }

    @GetMapping("templogs")
    public String getTempLogs() {
        return "templogs";
    }

    @GetMapping("moisturelogs")
    public String getMoistureLogs() {
        return "moisturelogs";
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }
}

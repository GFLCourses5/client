package com.geeksforless.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "page";
    }

    @PostMapping("/p")
    public String submitForm() {
        return "page";
    }
}

package com.geeksforless.client.controller;

import com.geeksforless.client.model.dto.UserDto;
import com.geeksforless.client.model.entity.User;
import com.geeksforless.client.model.request.SaveUserRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.extras.springsecurity5.auth.Authorization;

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

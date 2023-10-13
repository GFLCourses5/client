package com.geeksforless.client.controller;

import com.geeksforless.client.model.entity.User;
import com.geeksforless.client.model.request.SaveUserRequest;
import com.geeksforless.client.service.user.impl.RegistrationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final RegistrationServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") SaveUserRequest request, Model model) {
        if (userService.createUser(request) == null) {
            model.addAttribute("errorMessage", "Пользователь с email: " + request.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

}
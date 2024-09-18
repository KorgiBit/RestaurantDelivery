package org.example.controller;

// Этот код представляет собой контроллер HomeController, который используется для перенаправления запросов
// на главную страницу (/) на страницу входа (/login). Контроллер является частью MVC архитектуры и служит
// для управления потоком запросов и ответов в приложении.

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

}


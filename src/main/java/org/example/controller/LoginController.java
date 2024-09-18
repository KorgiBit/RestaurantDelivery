package org.example.controller;

// Этот код представляет собой простой контроллер, который используется в шаблоне MVC для обработки запросов
// от пользователя и определения, какую страницу или действие нужно выполнить. Контроллеры — это мост между
// пользователем и логикой приложения, принимающий запросы и возвращающий соответствующие ответы.

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}


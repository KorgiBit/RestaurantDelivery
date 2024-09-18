package org.example.controller;

// Этот код представляет собой контроллер, который обрабатывает запросы для отображения домашней страницы клиента.
// Он отвечает за получение данных о текущем пользователе и передачу их в представление (client-home.html).
// Контроллер написан с использованием Spring MVC и интегрирован с системой безопасности Spring Security.

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientHomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/client/home")
    public String getClientHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            User user = userRepository.findBySurname(username); // фамилия используется в качестве имени пользователя
            if (user != null) {
                model.addAttribute("surname", user.getSurname());
            }
        }
        return "client-home";
    }
}

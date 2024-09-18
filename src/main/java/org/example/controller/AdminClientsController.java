package org.example.controller;

// Код контроллера AdminClientsController отвечает за отображение списка всех пользователей,
// за исключением администраторов, на странице, доступной для администратора.

import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminClientsController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/clients")
    public String getAllClients(Model model) {
        // Исключаем из списка пользователей с ролью значение которой передаётся в качестве параметра: "ROLE_ADMIN"
        model.addAttribute("clients", userRepository.findAllByRoleNot("ROLE_ADMIN"));
        return "admin-clients";
    }

}


package org.example.controller;

// Этот код представляет собой контроллер RegistrationController, который отвечает за обработку регистрации новых
// пользователей в приложении. Контроллер обрабатывает запросы на страницу регистрации, проверяет введенные данные,
// сохраняет нового пользователя в базе данных и обеспечивает обратную связь с пользователем.

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Используем интерфейс PasswordEncoder

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("surname") String surname,
                               @RequestParam("password") String password,
                               Model model) {
        if (userRepository.existsBySurname(surname)) {
            model.addAttribute("error", "Surname already in use. Please choose another.");
            return "register";
        }

        User newUser = new User();
        newUser.setSurname(surname);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole("ROLE_CLIENT");

        userRepository.save(newUser);
        return "redirect:/login";
    }
}

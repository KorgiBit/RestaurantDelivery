package org.example.controller;

// Этот контроллер предоставляет основные функции для работы с сущностью "блюдо" в административной части приложения:
// просмотр списка блюд, добавление новых и удаление выбранных блюд. Он использует DishRepository для взаимодействия
// с базой данных, а Thymeleaf — для рендеринга соответствующих страниц.

import org.example.model.Dish;
import org.example.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminDishesController {

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/admin/dishes")
    public String getAllDishes(Model model) {
        model.addAttribute("dishes", dishRepository.findByIsDeletedFalse());
        return "admin-dishes";
    }

    @PostMapping("/admin/dishes")
    public String addDish(@RequestParam String name, @RequestParam double price, Model model) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setPrice(price);
        dishRepository.save(dish);
        return "redirect:/admin/dishes";
    }

    @PostMapping("/admin/dishes/delete")
    public String deleteDishes(@RequestParam(value = "ids", required = false) List<Long> ids, Model model) {
        if (ids != null && !ids.isEmpty()) {
            List<Dish> dishesToDelete = dishRepository.findAllById(ids);
            for (Dish dish : dishesToDelete) {
                dish.setDeleted(true);
                dishRepository.save(dish); // Сохраняем изменения в базе данных
            }
        }
        model.addAttribute("dishes", dishRepository.findAll().stream()
                .filter(dish -> !dish.isDeleted())
                .collect(Collectors.toList()));
        return "admin-dishes";
    }
}


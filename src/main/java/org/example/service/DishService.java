package org.example.service;

// Этот код представляет собой сервисный слой для работы с сущностью Dish в приложении на основе Java Spring.
// Сервисный слой (DishService) взаимодействует с репозиторием (DishRepository) и инкапсулирует бизнес-логику,
// обеспечивая связь между слоем доступа к данным и контроллерами.

import org.example.model.Dish;
import org.example.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public List<Dish> findByIsDeletedFalse() { return dishRepository.findByIsDeletedFalse(); }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }
}

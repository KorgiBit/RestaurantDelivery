package org.example.repository;

// Этот код представляет собой репозиторий для работы с сущностью Dish в приложении на основе Java Spring
// и Spring Data JPA. Репозиторий играет ключевую роль в слое данных MVC и является одним из основных компонентов,
// отвечающих за взаимодействие приложения с базой данных.
import org.example.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByIsDeletedFalse();
}
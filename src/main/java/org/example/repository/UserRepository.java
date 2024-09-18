package org.example.repository;

// Репозиторий отвечает за управление данными и реализацию доступа к ним,
// что позволяет изолировать бизнес-логику от работы с базой данных.

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findBySurname(String surname);  // Поиск пользователя по фамилии
    boolean existsBySurname(String surname); // Метод для проверки уникальности фамилии
}


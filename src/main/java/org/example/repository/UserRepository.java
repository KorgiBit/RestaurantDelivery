package org.example.repository;

// Репозиторий отвечает за управление данными и реализацию доступа к ним,
// что позволяет изолировать бизнес-логику от работы с базой данных.

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;                                                      // NEW
import org.springframework.data.repository.query.Param;                                                    // NEW
import java.util.List;                                                                                     // NEW

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findBySurname(String surname);  // Поиск пользователя по фамилии
    boolean existsBySurname(String surname); // Метод для проверки уникальности фамилии

    // Исключаем из списка пользователей с ролью, значение которой                                         // NEW
    // передаётся в качестве параметра из контроллера                                                      // NEW
    @Query("SELECT u FROM User u WHERE u.role <> :role")                                                   // NEW
    List<User> findAllByRoleNot(@Param("role") String role);                                               // NEW
}


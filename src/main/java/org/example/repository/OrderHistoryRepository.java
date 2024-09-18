package org.example.repository;

// Этот код представляет собой репозиторий, который используется для взаимодействия с данными о заказах (OrderHistory)
// в базе данных. Репозиторий является частью слоя доступа к данным в архитектуре MVC и управляет операциями
// с объектами OrderHistory

import org.example.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    // JpaRepository уже предоставляет метод findById(Long id)

    List<OrderHistory> findByUserSurname(String surname);

}




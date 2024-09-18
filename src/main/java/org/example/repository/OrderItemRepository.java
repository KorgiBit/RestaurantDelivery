package org.example.repository;

// Этот код представляет собой репозиторий, который используется для взаимодействия с данными о позициях заказа
// (OrderItem) в базе данных. Репозиторий является частью слоя доступа к данным в архитектуре MVC и управляет
// операциями с объектами OrderItem.

import org.example.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Этот метод возвращает список заказов, которые имеют указанный orderId.
    List<OrderItem> findByOrderId(Long orderId);
}

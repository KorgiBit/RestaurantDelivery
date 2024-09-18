package org.example.service;

// Этот код представляет собой сервисный слой, который используется для управления данными о позициях заказа
// (OrderItem). Сервисный слой инкапсулирует бизнес-логику и взаимодействует с репозиторием OrderItemRepository,
// обеспечивая связь между контроллерами и данными.

import org.example.model.OrderItem;
import org.example.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void saveOrder(OrderItem order) {
        orderItemRepository.save(order);
    }

    public List<OrderItem> getAllOrderItem() {
        return orderItemRepository.findAll();
    }

    // Получение списка позиций текущего заказа по идентификатору заказа
    // (Метод используется в CurrentOrderController при выводе содержимого текущего заказа)
    public List<OrderItem> findOrderItemByOrderId(Long orderId) {
        // метод findByOrderItemId ищет заказы по полю orderId в модели OrderItem
        return orderItemRepository.findByOrderId(orderId);
    }
}

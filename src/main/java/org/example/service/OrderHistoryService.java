package org.example.service;

// Этот код представляет собой сервисный слой, который используется для управления данными о заказах (OrderHistory).
// Сервисный слой инкапсулирует бизнес-логику и взаимодействует с репозиторием OrderHistoryRepository, обеспечивая
// связь между контроллерами и данными.

import org.example.model.OrderHistory;
import org.example.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    public void saveOrderHistory(OrderHistory orderHistory) {
        orderHistoryRepository.save(orderHistory);
    }

    public Optional<OrderHistory> findOrderHistoryById(Long id) {
        return orderHistoryRepository.findById(id);
    }

    public List<OrderHistory> getAllOrderHistory() {
        return orderHistoryRepository.findAll();
    }

    public List<OrderHistory> getOrderHistoryBySurname(String surname) {
        return orderHistoryRepository.findByUserSurname(surname);
    }
}





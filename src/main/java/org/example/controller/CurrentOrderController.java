package org.example.controller;

// Этот контроллер отвечает за отображение текущего заказа клиента. Он получает данные о заказе по его идентификатору
// (orderId), извлекает позиции заказа и отображает их вместе с общей информацией о заказе на странице.

import org.example.model.OrderItem;
import org.example.model.OrderHistory;
import org.example.service.OrderItemService;
import org.example.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")  // Все методы внутри этого контроллера будут обрабатываться по URL, начинающимся с "/client"
public class CurrentOrderController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping("/order")
    public String getCurrentOrder(@RequestParam Long orderId, Model model) {

        // Получение списка позиций текущего заказа по идентификатору заказа
        List<OrderItem> currOrderItemsList = orderItemService.findOrderItemByOrderId(orderId);

        // Получение информации об истории заказа по идентификатору
        Optional<OrderHistory> optionalOrderHistory = orderHistoryService.findOrderHistoryById(orderId);

        if (optionalOrderHistory.isPresent()) {// Если запись об истории заказа существует, извлекаем её
            OrderHistory orderHistory = optionalOrderHistory.get();
            // Добавляем данные в модель, чтобы передать их в представление
            model.addAttribute("currOrderItemsList", currOrderItemsList);      // Список позиций заказа
            model.addAttribute("orderHistory", orderHistory);                  // Информация об истории заказа
            // Возвращаем название шаблона Thymeleaf, который будет отображён в качестве представления
            return "current-order";
        } else {  // Если запись об истории заказа не найдена, возвращаем страницу с ошибкой
            model.addAttribute("error", "Order not found");
            return "error";
        }
    }
}

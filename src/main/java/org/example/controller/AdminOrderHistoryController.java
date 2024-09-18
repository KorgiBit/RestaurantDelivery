package org.example.controller;

// Этот контроллер отвечает за отображение истории заказов клиентов и управление статусом доставки заказов.

import org.example.model.OrderHistory;
import org.example.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminOrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    // Метод для вывода истории заказов клиента по его фамилии
    @GetMapping("/clientorderhistory")
    public String getOrderHistoryBySurname(@RequestParam("clientSurname") String clientSurname, Model model) {
        // Получаем записи истории заказов по фамилии клиента
        List<OrderHistory> orderHistoryList = orderHistoryService.getOrderHistoryBySurname(clientSurname);
        model.addAttribute("orderHistoryList", orderHistoryList);
        model.addAttribute("clientSurname", clientSurname); // Передаём фамилию для отображения в заголовке
        return "admin-clientorderhistory"; // Используем обновлённое имя шаблона
    }

    // Метод для вывода истории заказов всех клиентов
    @GetMapping("/orderhistory")
    public String getAllOrderHistory(Model model) {
        model.addAttribute("orderHistoryList", orderHistoryService.getAllOrderHistory());
        return "admin-orderhistory";
    }

    // Метод для обновления статуса доставки
    @PostMapping("/updateDeliveryStatus")
    public String updateDeliveryStatus(@RequestParam Long orderId,
                                       @RequestParam(name = "isDelivered", required = false, defaultValue = "false")
                                       boolean isDelivered) {
        // Используем метод сервиса для получения заказа по ID
        Optional<OrderHistory> optionalOrderHistory = orderHistoryService.findOrderHistoryById(orderId);
        // Проверяем, найден ли заказ
        if (optionalOrderHistory.isPresent()) {
            // Извлекаем объект OrderHistory из Optional
            OrderHistory orderHistory = optionalOrderHistory.get();
            // Устанавливаем статус доставки
            orderHistory.setDelivered(isDelivered);
            orderHistoryService.saveOrderHistory(orderHistory);
        } else {
            // Если заказ не найден, можно добавить обработку ошибки
            System.out.println("Order with ID " + orderId + " not found.");
            return "redirect:/admin/orderhistory?error=OrderNotFound";
        }
        // Перенаправляем обратно на страницу истории заказов
        return "redirect:/admin/orderhistory";
    }

}

package org.example.controller;

import org.example.model.Dish;
import org.example.model.OrderItem;
import org.example.model.OrderHistory;
import org.example.model.User;
import org.example.service.DishService;
import org.example.service.OrderItemService;
import org.example.service.OrderHistoryService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/client/makeorder")
public class ClientMakeOrderController {

    @Autowired
    private DishService dishService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getClientDishes(Model model, Principal principal, HttpSession session) {
        // Получаем список всех доступных блюд, которые не были удалены из меню
        List<Dish> dishes = dishService.findByIsDeletedFalse();
        // Находим пользователя по фамилии
        User user = userService.findBySurname(principal.getName());
        // Извлекаем список заказанных блюд из сессии
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        // Извлекаем текущую сумму заказа из сессии
        Double currentOrderAmount = (Double) session.getAttribute("currentOrderAmount");
        if (currentOrderAmount == null) {
            // Если сумма отсутствует, устанавливаем её в 0
            currentOrderAmount = 0.0;
            // Сохраняем значение в сессию, чтобы оно отображалось при первом заходе на страницу
            session.setAttribute("currentOrderAmount", currentOrderAmount);
        }
        model.addAttribute("dishes", dishes);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("currentOrderAmount", currentOrderAmount);
        return "client-makeorder";
    }

    @PostMapping("/add")
    public String addDishToOrder(@RequestParam Long dishId, @RequestParam int quantity,
                                           Principal principal, HttpSession session) {
        // Получаем блюдо по его ID
        Dish dish = dishService.getDishById(dishId);
        // Создаем новый элемент заказа и добавляем блюдо в заказ
        OrderItem order = new OrderItem();
        order.setDish(dish);
        order.setQuantity(quantity);
        // Извлекаем список заказанных блюд из сессии
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        // Добавляем заказанное блюдо в список
        orderItems.add(order);
        session.setAttribute("orderItems", orderItems);
        // Извлекаем текущую сумму заказа из сессии и увеличиваем её
        Double currentOrderAmount = (Double) session.getAttribute("currentOrderAmount");
        if (currentOrderAmount == null) {
            currentOrderAmount = 0.0;
        }
        currentOrderAmount += dish.getPrice() * quantity;
        // Сохраняем обновленную сумму заказа в сессии
        session.setAttribute("currentOrderAmount", currentOrderAmount);
        return "redirect:/client/makeorder";
    }

    @PostMapping("/clear")
    public String clearOrder(Principal principal, HttpSession session) {
        // Удаляем список заказанных блюд из сессии
        session.removeAttribute("orderItems");
        // Сбрасываем текущую сумму заказа в 0 и сохраняем в сессию
        session.setAttribute("currentOrderAmount", 0.0);  // Сброс суммы в сессии
        return "redirect:/client/makeorder";
    }

    @PostMapping("/submit")
    public String submitOrder(@RequestParam String deliveryAddress,
                                               Principal principal, HttpSession session) {
        // Получаем список заказанных блюд из сессии
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");

        // Проверяем, есть ли заказанные блюда
        if (orderItems != null && !orderItems.isEmpty()) {
            // Создаем новый объект OrderItem для хранения информации о заказе
            OrderHistory orderHistory = new OrderHistory();
            // Устанавливаем адрес доставки
            orderHistory.setDeliveryAddress(deliveryAddress);
            // Сохраняем дату заказа
            orderHistory.setOrderDate(new Date());
            // Получаем текущего пользователя и устанавливаем его для OrderHistory
            User user = userService.findBySurname(principal.getName());
            orderHistory.setUser(user); // Устанавливаем пользователя
            // Сохраняем связи между orderItems и orderHistory
            // Для заказа устанавливаем заказанные позиции
            orderHistory.setOrderItems(orderItems);
            //Для каждой заказанной позици устанавливаем заказ
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrderHistory(orderHistory);
            }
            // Для получения общей суммы заказа, используем значение currentOrderAmount
            Double currentOrderAmount = (Double) session.getAttribute("currentOrderAmount");
            if (currentOrderAmount == null) {
                currentOrderAmount = 0.0;
            }
            // Сохраняем в истории заказов общую сумму заказа
            orderHistory.setTotalAmount(currentOrderAmount);
            // Сохраняем OrderItem в базу данных
            orderHistoryService.saveOrderHistory(orderHistory);
            // Получаем ID сохраненного OrderItem
            Long orderId = orderHistory.getId();
            // Удаляем список заказов из сессии после успешного сохранения
            session.removeAttribute("orderItems");
            // Обнуляем текущую сумму заказа в сессии
            session.removeAttribute("currentOrderAmount");
            // Обнуляем переменную currentOrderAmount в контроллере
            currentOrderAmount = 0.0;
            // Перенаправляем клиента на страницу текущего заказа с передачей orderId
            return "redirect:/client/order?orderId=" + orderId;
        }
        // Если список заказов пуст, перенаправляем клиента обратно на страницу заказа блюд
        return "redirect:/client/makeorder";
    }
}

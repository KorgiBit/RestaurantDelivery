package org.example.model;

// Этот класс представляет сущность, которая будет связана с таблицей в базе данных.
// Эта сущность используется для хранения информации о элементе заказа (заказанном блюде, его количестве, цене).

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    private int quantity;

    @Column(name = "order_id", insertable = false, updatable = false)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderHistory orderHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public String toString() {
        return "Блюдо {" +
                (dish != null ? dish.getName() : "Нет блюда") +
                ", " + quantity +
                (dish != null ? dish.getPrice() : "Нет цены") +
                "} ";
    }
}

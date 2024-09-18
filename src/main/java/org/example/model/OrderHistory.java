package org.example.model;

// Этот класс представляет сущность, которая будет связана с таблицей в базе данных.
// Эта сущность используется для хранения информации о заказах в системе.

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_history")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "orderHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "delivery", columnDefinition = "TINYINT(1)")
    private boolean isDelivered;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() { return orderDate; }

    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalAmount() {return totalAmount;}

    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

}

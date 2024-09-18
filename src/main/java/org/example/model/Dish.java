package org.example.model;

// Этот класс представляет собой модель данных в Java Spring, которая используется для взаимодействия
// с базой данных через ORM (Object-Relational Mapping  Объектно-реляционное отображение) фреймворк,
// в данном случае, Hibernate.

import javax.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      // Поле id является уникальным автоинкрементируемым ID сущности
    private String name;  // Поле name предназначено для хранения названия блюда
    private double price; // Поле price хранит цену блюда

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    private boolean isDeleted; // 1 - удалённое из меню блюдо, по умолчанию 0

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDeleted() { return isDeleted; }

    public void setDeleted(boolean deleted) {  isDeleted = deleted; }

}

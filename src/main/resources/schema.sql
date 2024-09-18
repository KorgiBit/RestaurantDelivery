-- Создание базы данных, если она не существует
CREATE DATABASE IF NOT EXISTS restaurant;
USE restaurant;

-- Создание таблицы users, если она не существует
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Уникальный идентификатор пользователя
    surname VARCHAR(50) NOT NULL,  -- Фамилия пользователя
    password VARCHAR(100) NOT NULL,  -- Пароль пользователя
    role VARCHAR(50) NOT NULL,  -- Роль пользователя
    UNIQUE (surname)  -- Уникальный индекс на поле surname
);
-- Вставка тестовой записи, если запись с фамилией 'admin' не существует
INSERT INTO users (surname, password, role)
SELECT 'admin', '$2a$10$iB0X32l.xyqfyX8NUYBb5OFV2haf8uEzzMhSrqddYWTTr41QDLw5m', 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE surname = 'admin');
-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS taskboard_db;
USE taskboard_db;

-- Tabela de Boards
CREATE TABLE IF NOT EXISTS boards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Tabela de Colunas
CREATE TABLE IF NOT EXISTS columns (
    id INT AUTO_INCREMENT PRIMARY KEY,
    board_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    type ENUM('INICIAL','PENDENTE','FINAL','CANCELAMENTO') NOT NULL,
    `order` INT NOT NULL,
    FOREIGN KEY (board_id) REFERENCES boards(id)
);

-- Tabela de Cards
CREATE TABLE IF NOT EXISTS cards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    column_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_blocked BOOLEAN DEFAULT FALSE,
    block_reason TEXT,
    FOREIGN KEY (column_id) REFERENCES columns(id)
);

-- Índices para melhor performance
CREATE INDEX idx_column_type ON columns(type);
CREATE INDEX idx_card_blocked ON cards(is_blocked);

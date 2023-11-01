-- Tabela para armazenar informações dos usuários
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(15) NOT NULL UNIQUE,
    senha VARCHAR(10) NOT NULL
);

-- Tabela para armazenar informações dos produtos
CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(40) NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL
);

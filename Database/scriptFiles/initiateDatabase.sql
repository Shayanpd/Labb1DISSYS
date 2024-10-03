CREATE SCHEMA `webshop_db` ;

-- Skapa tabellen för användare
CREATE TABLE Users (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Skapa tabellen för produkter
CREATE TABLE Products (
    productID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Skapa tabellen för varukorg
CREATE TABLE Cart (
    cartID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE
);

-- Skapa tabellen för varukorgens innehåll (länkar produkter och varukorg)
CREATE TABLE CartItems (
    cartItemID INT AUTO_INCREMENT PRIMARY KEY,
    cartID INT NOT NULL,
    productID INT NOT NULL,
    quantity INT DEFAULT 1,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cartID) REFERENCES Cart(cartID) ON DELETE CASCADE,
    FOREIGN KEY (productID) REFERENCES Products(productID) ON DELETE CASCADE
);

-- Lägg till några exempelprodukter (valfritt)
INSERT INTO Products (name, description, price, stock) 
VALUES 
('Laptop', 'A high-end gaming laptop', 1500.00, 10),
('Smartphone', 'Latest model smartphone', 999.99, 25),
('Headphones', 'Noise-cancelling over-ear headphones', 199.99, 30);

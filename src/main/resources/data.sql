DROP DATABASE IF EXISTS spring2025productinventorydb;
CREATE DATABASE spring2025productinventorydb;
USE spring2025productinventorydb;

-- Tạo bảng systemaccounts
CREATE TABLE SystemAccounts (
    AccountID INT PRIMARY KEY,
    Username VARCHAR(100) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role INT,
    IsActive BOOLEAN DEFAULT TRUE
);

-- Tạo bảng category
CREATE TABLE Category (
    CategoryID INT PRIMARY KEY,
    CategoryName VARCHAR(255) NOT NULL,
    Description VARCHAR(500)
);

-- Tạo bảng product
CREATE TABLE Product (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    CategoryID INT,
    ProductName VARCHAR(255) NOT NULL,
    Material VARCHAR(100),
    Price DECIMAL(10, 2),
    Quantity INT,
    ReleaseDate DATE,
    CONSTRAINT fk_product_category FOREIGN KEY (CategoryID) 
        REFERENCES Category(CategoryID) ON DELETE CASCADE
);

-- Nhập dữ liệu mẫu: SystemAccounts
INSERT INTO SystemAccounts (AccountID, Username, Email, Password, Role, IsActive) VALUES
(1, 'adminpro', 'admin@system.com', 'admin123', 1, TRUE),
(2, 'manager1', 'manager@system.com', 'manager123', 2, TRUE),
(3, 'analyst1', 'analyst@system.com', 'analyst123', 3, TRUE),
(4, 'user1', 'user1@system.com', 'user123', 4, TRUE),
(5, 'suspended', 'blocked@system.com', 'nopass', 2, FALSE);

-- Nhập dữ liệu mẫu: Category
INSERT INTO Category (CategoryID, CategoryName, Description) VALUES
(1, 'Electronics', 'Electronic devices and accessories'),
(2, 'Wearables', 'Smartwatches, fitness bands'),
(3, 'Home Appliances', 'Appliances for home use'),
(4, 'Books', 'Printed and digital books'),
(5, 'Gaming', 'Consoles, accessories and titles');

-- Nhập dữ liệu mẫu: Product 
ALTER TABLE Product AUTO_INCREMENT = 1;
INSERT INTO Product (CategoryID, ProductName, Material, Price, Quantity, ReleaseDate) VALUES
(1, 'Wireless Earbuds Pro', 'Plastic', 199.99, 100, '2024-01-15'),
(1, 'Smartphone X10', 'Aluminum & Glass', 999.00, 50, '2024-02-10'),
(2, 'Smartwatch Z3', 'Metal', 149.99, 75, '2024-03-01'),
(3, 'Air Purifier Pro', 'Steel', 259.00, 40, '2024-01-05'),
(4, 'Artificial Intelligence 101', 'Paper', 29.99, 200, '2023-12-20'),
(5, 'NextGen Console V', 'Plastic', 499.00, 30, '2024-02-20'),
(5, 'Wireless Controller 2.0', 'Plastic', 69.99, 150, '2024-01-25'),
(2, 'Fitness Band Plus', 'Rubber', 89.99, 90, '2024-03-10'),
(3, 'Robot Vacuum Cleaner', 'Plastic', 299.00, 25, '2024-04-01'),
(4, 'Data Structures Guidebook', 'Paper', 45.00, 120, '2024-01-18');
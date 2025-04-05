CREATE TABLE product (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        brand VARCHAR(255) NOT NULL,
        description TEXT,
        price DECIMAL(10, 2) NOT NULL
);

INSERT INTO product (name, brand, description, price)
VALUES ('Smartphone', 'BrandX', 'Latest model with advanced features', 699.99);

INSERT INTO product (name, brand, description, price)
VALUES ('Laptop', 'BrandY', 'Powerful laptop with high performance', 1200.50);

INSERT INTO product (name, brand, description, price)
VALUES ('Headphones', 'BrandZ', 'Noise-canceling headphones', 199.99);

INSERT INTO product (name, brand, description, price)
VALUES ('Watch','BrandA','Smart Watch with Advanced features',599.90);

INSERT INTO product (name, brand, description, price)
VALUES ('Shoe','BrandB','Comfortable Shoes',899.50 );


DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            productid BIGINT NOT NULL,
            quantity BIGINT NOT NULL
);

INSERT INTO orders (productid, quantity)
VALUES
    (1, 1),  -- Order for product ID 1 with quantity 10
    (2, 2),   -- Order for product ID 2 with quantity 5
    (3, 3),  -- Order for product ID 3 with quantity 15
    (4, 4),   -- Order for product ID 4 with quantity 8
    (5, 5);  -- Order for product ID 5 with quantity 20
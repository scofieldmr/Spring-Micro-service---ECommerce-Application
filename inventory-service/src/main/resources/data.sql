DROP TABLE IF EXISTS Inventory;
CREATE TABLE inventory (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        productid BIGINT NOT NULL,
        productinvquantity BIGINT NOT NULL
);

INSERT INTO Inventory (productid, productinvquantity)
VALUES
    (1, 10),  -- Order for product ID 1 with quantity 10
    (2, 20),   -- Order for product ID 2 with quantity 5
    (3, 30),  -- Order for product ID 3 with quantity 15
    (4, 0),   -- Order for product ID 4 with quantity 8
    (5,50);
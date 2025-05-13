DROP DATABASE Bakery;
CREATE DATABASE Bakery;
USE Bakery;
CREATE TABLE Customer (
                          customer_id VARCHAR(100) PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          address VARCHAR(100),
                          contact VARCHAR(10) NOT NULL
);

CREATE TABLE Users (
                       user_id VARCHAR(100) PRIMARY KEY,
                       user_name VARCHAR(100) NOT NULL,
                       password VARCHAR(10) NOT NULL,
                       role VARCHAR(100) NOT NULL
);

CREATE TABLE Orders (
                        order_id VARCHAR(100) PRIMARY KEY,
                        total_amount DECIMAL(10,2),
                        order_date DATETIME,
                        payment_status VARCHAR(10) NOT NULL,
                        customer_id VARCHAR(100) REFERENCES Customer(customer_id),
                        deliver_id VARCHAR(100) REFERENCES Deliver(deliver_id)
);

CREATE TABLE Deliver (
                         deliver_id VARCHAR(100) PRIMARY KEY,
                         deliver_address VARCHAR(100),
                         deliver_charge DECIMAL(10,2),
                         deliver_date DATETIME,
                         order_id VARCHAR(100) REFERENCES Orders(order_id)
);

CREATE TABLE Employee (
                          employee_id VARCHAR(100) PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          contact_no CHAR(10) NOT NULL,
                          email VARCHAR(100),
                          salary DECIMAL(10,2),
                          hire_date DATETIME,
                          role VARCHAR(40)
);

CREATE TABLE Salary (
                        salary_id VARCHAR(100) PRIMARY KEY,
                        basic_salary DECIMAL(10,2),
                        bonus DECIMAL(10,2),
                        net_salary DECIMAL(10,2),
                        payment_date DATE,
                        employee_id VARCHAR(100) REFERENCES Employee(employee_id)
);

CREATE TABLE Expenses (
                          expenses_id VARCHAR(100) PRIMARY KEY,
                          category VARCHAR(100),
                          amount DECIMAL(10,2),
                          date DATE,
                          description VARCHAR(255)
);

CREATE TABLE Payment (
                         payment_id VARCHAR(100) PRIMARY KEY,
                         amount DECIMAL(10,2),
                         payment_method VARCHAR(50),
                         payment_date DATE,
                         attribute VARCHAR(100),
                         order_id VARCHAR(100) REFERENCES Orders(order_id)
);

CREATE TABLE Discount (
                          discount_id VARCHAR(100) PRIMARY KEY,
                          discount_code VARCHAR(50),
                          percentage DECIMAL(5,2),
                          minimum_order_value DECIMAL(10,2),
                          date_of_expire DATE
);

CREATE TABLE Supplier (
                          supplier_id VARCHAR(100) PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          supplied_ingredient VARCHAR(255),
                          address VARCHAR(100),
                          email VARCHAR(100)
);

CREATE TABLE Product (
                         product_id VARCHAR(100) PRIMARY KEY,
                         name VARCHAR(100),
                         ingredient_list VARCHAR(255),
                         price DECIMAL(10,2),
                         category VARCHAR(100),
                         stock_quantity INT,
                         supplier_id VARCHAR(100) REFERENCES Supplier(supplier_id)
);

CREATE TABLE Ingredient (
                            ingredient_id VARCHAR(100) PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            expire_date DATE,
                            quantity_available DECIMAL(10,2),
                            supplier_id VARCHAR(100) REFERENCES Supplier(supplier_id)
);

CREATE TABLE Inventory (
                           inventory_id VARCHAR(100) PRIMARY KEY,
                           stock_quantity INT,
                           last_update DATETIME,
                           product_id VARCHAR(100) REFERENCES Product(product_id),
                           ingredient_id VARCHAR(100) REFERENCES Ingredient(ingredient_id)
);

CREATE TABLE Order_Details (
                               id VARCHAR(100) PRIMARY KEY,
                               subtotal DECIMAL(10,2),
                               quantity INT,
                               order_id VARCHAR(100) REFERENCES Orders(order_id),
                               product_id VARCHAR(100) REFERENCES Product(product_id)
);

CREATE TABLE Employee_Details (
                                  id VARCHAR(100) PRIMARY KEY,
                                  address VARCHAR(255),
                                  work_shift VARCHAR(50),
                                  date_of_birth DATETIME,
                                  employee_id VARCHAR(100) REFERENCES Employee(employee_id)
);

CREATE TABLE Product_Details (
                                 id VARCHAR(100) PRIMARY KEY,
                                 quantity_used DECIMAL(10,2),
                                 product_id VARCHAR(100) REFERENCES Product(product_id),
                                 ingredient_id VARCHAR(100) REFERENCES Ingredient(ingredient_id)
);
SELECT * FROM Product_Details;

INSERT INTO Customer (customer_id, name, address, contact)
VALUES ('C001', 'Maneesha', '123 Main St', '1234567890');

INSERT INTO Orders (order_id, customer_id, deliver_id, total_amount, order_date, payment_status)
VALUES ('O001', 'C001', 'D001', 99.99, '2025-04-10 14:30:00', 'Paid');

INSERT INTO Deliver (deliver_id, order_id, deliver_address, deliver_charge, deliver_date)
VALUES ('D001', 'O001', '123 Main St', 5.00, '2025-04-11 12:00:00');

INSERT INTO Product_Details (id,product_id, ingredient_id, quantity_used)
VALUES ('P001', 'P001','I001', 2.5);

INSERT INTO Payment (payment_id, order_id, amount, payment_method, payment_date, attribute)
VALUES ('PM001', 'O001', 104.99, 'Credit Card', '2025-04-10', 'On Market');

INSERT INTO Supplier (supplier_id, name, supplied_ingredient, address, email)
VALUES ('S001', 'Fresh Farms', 'Tomato, Onion', '12 Garden St', 'fresh@farms.com');

INSERT INTO Product (product_id, supplier_id, name, ingredient_list, price, category, stock_quantity)
VALUES ('P001', 'S001', 'Veggie Pizza', 'Tomato, Onion, Cheese', 12.99, 'Food', 50);

INSERT INTO Ingredient (ingredient_id, supplier_id, name, expire_date, quantity_available)
VALUES ('I001', 'S001', 'Tomato', '2025-12-31', 100.00);

INSERT INTO Inventory (inventory_id, product_id, ingredient_id, stock_quantity, last_update)
VALUES ('INV001', 'P001', 'I001', 50, '2025-04-10 10:00:00');

INSERT INTO Users (user_id, user_name, password, role)
VALUES ('U001', 'admin', 'admin123', 'Admin');

INSERT INTO Employee (employee_id, name, contact_no, email, salary, hire_date, role)
VALUES ('E001', 'Kasun', '0987654321', 'bob@company.com', 3000.00, '2023-01-01 09:00:00', 'Chef');

INSERT INTO Salary (salary_id, employee_id, basic_salary, bonus, net_salary, payment_date)
VALUES ('SAL001', 'E001', 2500.00, 500.00, 3000.00, '2025-04-01');

INSERT INTO Expenses (expenses_id, category, amount, date, description)
VALUES ('EX001', 'Utilities', 150.00, '2025-04-01', 'Electricity and Water Bill');

INSERT INTO Discount (discount_id, discount_code, percentage, minimum_order_value, date_of_expire)
VALUES ('D001', 'SAVE10', 10.00, 20.00, '2025-12-31');

INSERT INTO Order_Details (id, order_id, product_id, subtotal, quantity)
VALUES ('OD001', 'O001', 'P001', 12.99, 1);

INSERT INTO Employee_Details (id, employee_id, address, work_shift, date_of_birth)
VALUES ('ED001', 'E001', '78 Office Lane', 'Morning', '1990-05-20');

SELECT *  FROM Customer;
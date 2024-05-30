CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    surname VARCHAR,
    tel_number VARCHAR,
    email VARCHAR,
    hash_password VARCHAR
);
INSERT INTO users(name, surname, tel_number, email, hash_password)
VALUES('', '','', '', ''),
      ('Иванов', 'Иван','+7(977)926-92-61', 'ivan@mail.ru', 'qwerty001');


CREATE TABLE orders
(
    id SERIAL PRIMARY KEY,
    customer_user_id INTEGER,
    FOREIGN KEY (customer_user_id) REFERENCES users(id),
    delieveryman_user_id INTEGER DEFAULT NULL,
    FOREIGN KEY (delieveryman_user_id) REFERENCES users(id),
    weight INTEGER DEFAULT 0,
    price INTEGER DEFAULT 400
);
INSERT INTO orders(customer_user_id, weight) VALUES (1, 1),
                                                    (1, 2);

CREATE TABLE order_points
(
    id SERIAL PRIMARY KEY,
    order_id INTEGER,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    number_of_point INTEGER,
    address VARCHAR,
    name VARCHAR,
    tel_number VARCHAR,
    description VARCHAR DEFAULT 'Позвонить клиенту, уточнить детали'
);
INSERT INTO order_points(order_id, number_of_point, address, name, tel_number, description)
VALUES (1, 1, 'Moscow', 'Максим', '+7(977)926-92-64', 'забрать документы у Максима на 5 этаже, заказть пропуск'),
       (1, 2, 'Piter', 'Макси', '+7(977)926-92-65', 'отдать доки Максу на 1 этаже, позвонить за 15 мин');
INSERT INTO order_points(order_id, number_of_point, address, name, tel_number)
VALUES (2, 1, 'Saratov', 'Макс', '+7(977)926-92-66'),
       (2, 2, 'Moscow', 'Мак', '+7(977)926-92-67');


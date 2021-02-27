DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (userId, dateTime, description, calories)
VALUES (100000, '2021-02-26 10:00:00', 'Завтрак', 500),
       (100000, '2021-02-26 13:00:00', 'Обед', 1000),
       (100000, '2021-02-26 20:00:00', 'Ужин', 500),
       (100000, '2021-02-27 09:00:00', 'Еда на граничное значение', 100),
       (100000, '2021-02-27 10:00:00', 'Завтрак', 500),
       (100000, '2021-02-27 13:00:00', 'Обед', 1000),
       (100000, '2021-02-27 20:00:00', 'Ужин', 410);

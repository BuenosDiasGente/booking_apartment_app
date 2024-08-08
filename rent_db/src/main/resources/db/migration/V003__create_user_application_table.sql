CREATE TABLE IF NOT EXISTS user_application(
    id INT8 PRIMARY KEY NOT NULL,
    nik_name VARCHAR(50),
    login VARCHAR(100),
    password VARCHAR(100),
    booking_count INT4,
    parent_city VARCHAR,
    token VARCHAR,
    date_registration TIMESTAMP
);

CREATE SEQUENCE user_application_sequence START 3 INCREMENT 1;

INSERT INTO user_application(id,nik_name,login,password,booking_count,parent_city,token,date_registration)
VALUES (1,'yanchic333','morkovka29@yandex.ru','qwerty1234',5,'Тверь','fc564406-747b-4296-af90-e538b770e429|2025-02-15T12:08:58.551321800',NULL),
       (2,'ivashka56','redis@yandex.ru','qwerty789',3,'Иванова',NULL,NULL);
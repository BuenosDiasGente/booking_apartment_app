CREATE TABLE IF NOT EXISTS apartment_info(
    id Int8 PRIMARY KEY NOT NULL,
    count_rooms Int4 ,
    price Int4,
    global_rating Varchar,
    date_registration Timestamp,
    availability bool

);

CREATE SEQUENCE apartment_info_sequence START 3 INCREMENT 1;

INSERT INTO apartment_info(id, count_rooms, price ,global_rating ,date_registration,availability)
VALUES (1,2,5000,NULL,NULL,true),
       (2,3,4000,NULL,NULL,true);



CREATE TABLE IF NOT EXISTS address_info
(
    id Int8 PRIMARY KEY NOT NULL,
    region Varchar,
    city Varchar,
    street Varchar,
    house_number Varchar,
    flat_number Varchar,
    apartment_id Int8 REFERENCES apartment_info(id)

);

CREATE SEQUENCE address_info_sequence START 1 INCREMENT 1;
INSERT INTO address_info(id,region,city,street,house_number,flat_number,apartment_id)
VALUES (1,'Krasnodarskiy kray','Krasnodar','Lenina','167/3',NULL,1),
       (2,'Moscow','Moscow','Lenina','167/3',NULL,2);
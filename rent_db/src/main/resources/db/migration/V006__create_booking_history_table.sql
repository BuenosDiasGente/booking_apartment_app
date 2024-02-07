CREATE TABLE IF NOT EXISTS booking_history
(
    id             Int8 PRIMARY KEY NOT NULL,
    check_in      TIMESTAMP,
    check_out      TIMESTAMP,
    price_day      int4,
    total_discount int4,
    user_id        int8 REFERENCES user_application (id),
    apartment_id   int8 REFERENCES apartment_info (id),
    product_id int8 REFERENCES product(id)

);
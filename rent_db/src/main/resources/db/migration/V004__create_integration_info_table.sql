CREATE TABLE IF NOT EXISTS integration_info(
   unique_id_service VARCHAR(250) PRIMARY KEY,
   path_description VARCHAR(250),
    api_key VARCHAR(100)
    );


INSERT INTO integration_info(unique_id_service,   path_description,    api_key)
VALUES ('geocode_service', 'https://api.opencagedata.com/geocode/v1/json?q=%s+%s&no_annotations=1&key=%s', 'OTYyZDgwNzhhYjUwNDA3ZThkMjcwMTA2YjlhMGNkZmI=');

INSERT INTO integration_info(unique_id_service,   path_description,    api_key)
VALUES ('product_service', 'http://localhost:9093/prepare-product-for-booking?id=%s&tokenValue=%s', 'cHJvZHVjdA==');
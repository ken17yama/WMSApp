CREATE TABLE IF NOT EXISTS stock(
	stock_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	stock_name VARCHAR(50),
	quantity DOUBLE,
	unit VARCHAR(25),
	place VARCHAR(50),
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);
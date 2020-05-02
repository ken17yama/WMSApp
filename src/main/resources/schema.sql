CREATE TABLE IF NOT EXISTS user(
	user_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	user_name VARCHAR(50),
	mail VARCHAR(50) UNIQUE,
	password VARCHAR(100),
	birthday DATE,
	role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS stock(
	stock_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	stock_name VARCHAR(50),
	quantity DOUBLE,
	unit VARCHAR(25),
	place VARCHAR(50),
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);
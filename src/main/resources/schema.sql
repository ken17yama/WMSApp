CREATE TABLE IF NOT EXISTS users(
	user_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	user_name VARCHAR(50),
	mail VARCHAR(50) UNIQUE,
	password VARCHAR(100),
	birthday DATE,
	role VARCHAR(50),
	my_place VARCHAR
);

CREATE TABLE IF NOT EXISTS groups(
	group_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	group_name VARCHAR(50),
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS stocks(
	stock_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	stock_name VARCHAR(50),
	quantity DOUBLE,
	unit VARCHAR(25),
	place BIGINT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);
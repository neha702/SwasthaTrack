CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    gender VARCHAR(50),
    age INT,
    weight DOUBLE PRECISION,
    height DOUBLE PRECISION,
    email_id VARCHAR(255),
    password VARCHAR(255)
);
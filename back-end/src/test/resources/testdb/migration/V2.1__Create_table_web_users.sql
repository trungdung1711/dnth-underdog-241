CREATE TABLE web_users
(
    id BIGINT NOT NULL,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    password varchar(1000) NOT NULL,
    sex TINYINT CHECK (sex BETWEEN 0 and 2),
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    birth_day DATE,
    PRIMARY KEY (id)
);
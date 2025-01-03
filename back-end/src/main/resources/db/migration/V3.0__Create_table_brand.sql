CREATE TABLE brand
(
    id BIGINT AUTO_INCREMENT,
    name TINYINT CHECK (name BETWEEN 0 and 5),
    url VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
)
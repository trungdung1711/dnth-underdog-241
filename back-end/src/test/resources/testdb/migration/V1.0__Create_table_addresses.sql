CREATE TABLE addresses
(
     id BIGINT NOT NULL AUTO_INCREMENT,
     province VARCHAR(255) NOT NULL,
     city VARCHAR(255) NOT NULL,
     ward VARCHAR(255) NOT NULL,
     street VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
);
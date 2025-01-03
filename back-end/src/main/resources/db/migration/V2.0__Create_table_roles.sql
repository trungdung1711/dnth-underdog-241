#Create table roles
CREATE TABLE roles
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    CHECK ( name IN ('ROLE_CUSTOMER', 'ROLE_EMPLOYEE', 'ROLE_ADMIN') )
) engine=InnoDB;

#Insert the default values
INSERT INTO roles (name) VALUES ('ROLE_CUSTOMER');
INSERT INTO roles (name) VALUES ('ROLE_EMPLOYEE');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
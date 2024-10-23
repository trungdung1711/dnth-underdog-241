CREATE TABLE web_users_roles
(
    web_user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (web_user_id, role_id),

    -- Foreign key to web_users table
    CONSTRAINT fk_web_users_roles_web_users
    FOREIGN KEY (web_user_id)
    REFERENCES web_users(id),

    -- Foreign key to roles table
    CONSTRAINT fk_web_users_roles_roles
    FOREIGN KEY (role_id)
    REFERENCES roles(id)
) engine=InnoDB;
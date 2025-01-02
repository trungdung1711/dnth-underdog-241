ALTER TABLE
web_users
ADD COLUMN
    address_id BIGINT,
ADD CONSTRAINT
    fk_web_users_addresses
    FOREIGN KEY (address_id)
    REFERENCES addresses(id);
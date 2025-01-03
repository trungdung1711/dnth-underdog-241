ALTER TABLE
    web_users
ADD COLUMN
    web_user_type
    VARCHAR(50)
    NOT NULL
    DEFAULT 'CUSTOMER'
    CHECK ( web_user_type IN ('EMPLOYEE', 'CUSTOMER', 'ADMIN') );
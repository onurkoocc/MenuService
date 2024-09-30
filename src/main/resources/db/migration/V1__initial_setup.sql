
-- V1__Create_initial_schema.sql

-- Create the 'menu' table
    CREATE TABLE menu (
                          id BIGSERIAL PRIMARY KEY,
                          restaurant_id BIGINT NOT NULL
    );

-- Create the 'category' table
CREATE TABLE category (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          menu_id BIGINT NOT NULL REFERENCES menu(id) ON DELETE CASCADE
);

-- Create the 'product' table
CREATE TABLE product (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         title VARCHAR(255),
                         description TEXT,
                         price NUMERIC(10, 2) NOT NULL,
                         photo_url VARCHAR(500),
                         category_id BIGINT NOT NULL REFERENCES category(id) ON DELETE CASCADE
);

-- Indexes for performance (optional but recommended)
CREATE INDEX idx_category_menu_id ON category(menu_id);
CREATE INDEX idx_product_category_id ON product(category_id);

-- V3__additional_constraints_and_indexes.sql

-- Ensure that price is non-negative
ALTER TABLE product
    ADD CONSTRAINT chk_product_price_non_negative
        CHECK (price >= 0);

-- Ensure that category names are unique within the same menu and parent category
ALTER TABLE category
    ADD CONSTRAINT uq_category_name_menu_parent
        UNIQUE (name, menu_id, parent_category_id);

-- Ensure that product names are unique within the same category
ALTER TABLE product
    ADD CONSTRAINT uq_product_name_category
        UNIQUE (name, category_id);

-- V2__Add_subcategory_support.sql

-- Add parent_category_id to the 'category' table
ALTER TABLE category
    ADD COLUMN parent_category_id BIGINT;

-- Add foreign key constraint for parent_category_id
ALTER TABLE category
    ADD CONSTRAINT fk_category_parent
        FOREIGN KEY (parent_category_id)
            REFERENCES category(id) ON DELETE CASCADE;

-- Create an index on parent_category_id for performance
CREATE INDEX idx_category_parent_category_id ON category(parent_category_id);

ALTER TABLE product
    ADD is_prime_specific BIT(1) NULL;

ALTER TABLE product
    ADD last_updated_at datetime NULL;

ALTER TABLE category
    ADD last_updated_at datetime NULL;

ALTER TABLE category
DROP
COLUMN `description`;

ALTER TABLE category
DROP
COLUMN updated_at;

ALTER TABLE product
DROP
COLUMN is_prime;

ALTER TABLE product
DROP
COLUMN updated_at;

ALTER TABLE product
    MODIFY price DOUBLE NULL;
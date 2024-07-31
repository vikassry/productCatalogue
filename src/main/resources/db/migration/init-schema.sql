CREATE TABLE category
(
    id              BIGINT NOT NULL,
    name            VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    state           SMALLINT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                BIGINT NOT NULL,
    name              VARCHAR(255) NULL,
    image_url         VARCHAR(255) NULL,
    `description`     VARCHAR(255) NULL,
    price DOUBLE NULL,
    category_id       BIGINT NULL,
    is_prime_specific BIT(1) NULL,
    state             SMALLINT NULL,
    created_at        datetime NULL,
    last_updated_at   datetime NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);
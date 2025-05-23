CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP,
    last_modified_at TIMESTAMP,
    title VARCHAR(255)
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP,
    last_modified_at TIMESTAMP,
    title VARCHAR(255),
    price DOUBLE,
    description TEXT,
    image_url VARCHAR(512),
    category_id BIGINT,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES categories(id)
);
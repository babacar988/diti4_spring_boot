
CREATE TABLE IF NOT EXISTS types (
    id      BIGSERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id      BIGSERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    prix    DOUBLE PRECISION NOT NULL,
    type_id BIGINT NOT NULL REFERENCES types(id)
);

CREATE INDEX IF NOT EXISTS idx_products_type_id ON products(type_id);

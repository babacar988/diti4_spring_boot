-- V1__init_schema.sql
-- Schéma initial déduit de Type.java et Produit.java.
-- ⚠️ Ajuste les colonnes de "types" si Type.java a d'autres champs que id/libelle.

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

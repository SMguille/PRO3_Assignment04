DROP SCHEMA IF EXISTS pro3_assignment CASCADE;
CREATE SCHEMA pro3_assignment;
SET SCHEMA 'pro3_assignment';

CREATE DOMAIN weight AS NUMERIC(10, 3);
-- should be enough (?)

-- multiplicity direction: animal<-part->tray<->product<-HalfAnimal|Package

CREATE TABLE animal
(
    id             SERIAL PRIMARY KEY,
    arrival_at     TIMESTAMP WITH TIME ZONE,
    live_weight_kg weight
);

CREATE TABLE part_type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE tray
(
    id                     SERIAL PRIMARY KEY,
    max_weight_capacity_kg weight NOT NULL,
    parts_type             INT    REFERENCES part_type (id) ON DELETE SET NULL
);

CREATE TABLE part
(
    id         SERIAL PRIMARY KEY,
    weight_kg  weight NOT NULL,
    parts_type INT    NOT NULL REFERENCES part_type (id) ON DELETE SET NULL,
    animal_id  INT    NOT NULL REFERENCES animal (id) ON DELETE CASCADE,
    tray_id    INT    NOT NULL REFERENCES tray (id) ON DELETE SET NULL
);

CREATE TABLE product
(
    id           SERIAL PRIMARY KEY,
    product_name TEXT NOT NULL
);

CREATE TABLE product_tray
(
    product_id INT NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    tray_id    INT NOT NULL REFERENCES tray (id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, tray_id)
);


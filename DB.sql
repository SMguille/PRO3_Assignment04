DROP SCHEMA IF EXISTS pro3_assignment CASCADE;
CREATE SCHEMA pro3_assignment;
SET SCHEMA 'pro3_assignment';

CREATE DOMAIN weight AS NUMERIC(10, 3) CHECK (VALUE > 0);
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
    parts_type INT    REFERENCES part_type (id) ON DELETE SET NULL,
    animal_id  INT    NOT NULL REFERENCES animal (id) ON DELETE CASCADE,
    tray_id    INT    NOT NULL REFERENCES tray (id) ON DELETE CASCADE
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

-- animal parts
INSERT INTO part_type(name)
VALUES ('leg');
INSERT INTO part_type(name)
VALUES ('arm');

-- animals
INSERT INTO animal(arrival_at, live_weight_kg)
VALUES ('2025-10-01 08:30:00+02', 450.750),
       ('2025-10-02 09:15:00+02', 520.320);

-- trays
INSERT INTO tray(max_weight_capacity_kg, parts_type)
VALUES (50.000, 1),
       (60.000, 2),
       (55.000, 1);

-- parts (linked to animals and trays)
INSERT INTO part(weight_kg, parts_type, animal_id, tray_id)
VALUES (12.500, 1, 1, 1), -- leg from animal 1 in tray 1
       (14.200, 1, 2, 3), -- leg from animal 2 in tray 3
       (10.000, 2, 1, 2), -- arm from animal 1 in tray 2
       (11.350, 2, 2, 2);
-- arm from animal 2 in tray 2

-- products
INSERT INTO product(product_name)
VALUES ('Beef #1'),
       ('Premium Meat');

-- link trays to products
INSERT INTO product_tray(product_id, tray_id)
VALUES (1, 1),
       (1, 2),
       (2, 3);


-- 0) prove tables exist in current DB
SELECT table_schema, table_name
FROM information_schema.tables
WHERE table_schema='pro3_assignment'
  AND table_name IN ('animal','part','tray','product','product_tray');

SET search_path TO pro3_assignment;

SET SCHEMA 'pro3_assignment';
SET search_path TO pro3_assignment, public;



-- 1) Animals by product (should be {1,2} for product_id=1; {2} for product_id=2)
SELECT DISTINCT p.animal_id
FROM pro3_assignment.part p
         JOIN pro3_assignment.tray t       ON p.tray_id = t.id
         JOIN pro3_assignment.product_tray pt ON pt.tray_id = t.id
WHERE pt.product_id = 1          -- change to 2 to test the other
ORDER BY 1;

-- 2) Products by animal (should be {1} for animal_id=1; {1,2} for animal_id=2)
SELECT DISTINCT pt.product_id
FROM pro3_assignment.product_tray pt
         JOIN pro3_assignment.tray t ON pt.tray_id = t.id
         JOIN pro3_assignment.part p ON p.tray_id = t.id
WHERE p.animal_id = 1            -- change to 2 to test the other
ORDER BY 1;


SELECT current_database() AS db,
       current_user      AS usr,
       current_schema    AS schema,
       current_schemas(true) AS search_path;

SELECT inet_server_addr() AS host, inet_server_port() AS port;

SELECT table_schema, table_name
FROM information_schema.tables
WHERE table_schema='pro3_assignment';

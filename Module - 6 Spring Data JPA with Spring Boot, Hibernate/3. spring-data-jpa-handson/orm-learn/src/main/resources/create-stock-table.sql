-- ============================================================
-- Stock table creation
-- Run this in MySQL Workbench or mysql CLI before running the app
-- ============================================================

CREATE TABLE IF NOT EXISTS `ormlearn`.`stock` (
  `st_id`     INT NOT NULL AUTO_INCREMENT,
  `st_code`   varchar(10),
  `st_date`   date,
  `st_open`   numeric(10,2),
  `st_close`  numeric(10,2),
  `st_volume` numeric,
  PRIMARY KEY (`st_id`)
);

-- NOTE: After creating this table, generate and run stock-data.sql
-- using the Excel formula provided in the hands-on instructions:
-- =CONCATENATE("insert into stock (st_code, st_date, st_open, st_close, st_volume) values (""",
--   B2, """, """, YEAR(A2), "-", MONTH(A2), "-", DAY(A2), """, ", C2, ", ", D2, ", ", E2, ");")

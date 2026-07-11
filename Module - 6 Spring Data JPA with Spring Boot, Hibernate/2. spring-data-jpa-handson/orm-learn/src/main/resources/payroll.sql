-- ============================================================
-- Hands-on 3: Payroll schema - department, employee, skill, employee_skill
-- Run this in MySQL Workbench or mysql CLI before running the app
-- ============================================================

USE ormlearn;

-- Department table
CREATE TABLE IF NOT EXISTS `department` (
  `dp_id`   INT NOT NULL AUTO_INCREMENT,
  `dp_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`dp_id`)
);

-- Employee table
CREATE TABLE IF NOT EXISTS `employee` (
  `em_id`            INT NOT NULL AUTO_INCREMENT,
  `em_name`          VARCHAR(100) NOT NULL,
  `em_salary`        DOUBLE NOT NULL,
  `em_permanent`     TINYINT(1) NOT NULL DEFAULT 0,
  `em_date_of_birth` DATE,
  `em_dp_id`         INT,
  PRIMARY KEY (`em_id`),
  CONSTRAINT `fk_em_dp` FOREIGN KEY (`em_dp_id`) REFERENCES `department` (`dp_id`)
);

-- Skill table
CREATE TABLE IF NOT EXISTS `skill` (
  `sk_id`   INT NOT NULL AUTO_INCREMENT,
  `sk_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`sk_id`)
);

-- Employee-Skill join table (ManyToMany)
CREATE TABLE IF NOT EXISTS `employee_skill` (
  `es_em_id` INT NOT NULL,
  `es_sk_id` INT NOT NULL,
  PRIMARY KEY (`es_em_id`, `es_sk_id`),
  CONSTRAINT `fk_es_em` FOREIGN KEY (`es_em_id`) REFERENCES `employee` (`em_id`),
  CONSTRAINT `fk_es_sk` FOREIGN KEY (`es_sk_id`) REFERENCES `skill` (`sk_id`)
);

-- ============================================================
-- Sample Data
-- ============================================================

INSERT INTO `department` (`dp_name`) VALUES
  ('Engineering'),
  ('Human Resources'),
  ('Finance');

INSERT INTO `skill` (`sk_name`) VALUES
  ('Java'),
  ('Spring Boot'),
  ('MySQL'),
  ('Python'),
  ('Angular');

INSERT INTO `employee` (`em_name`, `em_salary`, `em_permanent`, `em_date_of_birth`, `em_dp_id`) VALUES
  ('Alice Johnson',  75000.00, 1, '1990-03-15', 1),
  ('Bob Smith',      60000.00, 1, '1985-07-22', 1),
  ('Carol White',    55000.00, 0, '1995-11-10', 2),
  ('David Brown',    80000.00, 1, '1988-01-30', 3),
  ('Eva Green',      50000.00, 0, '1998-06-05', 2);

INSERT INTO `employee_skill` (`es_em_id`, `es_sk_id`) VALUES
  (1, 1),   -- Alice -> Java
  (1, 2),   -- Alice -> Spring Boot
  (2, 1),   -- Bob   -> Java
  (2, 3),   -- Bob   -> MySQL
  (3, 5),   -- Carol -> Angular
  (4, 3),   -- David -> MySQL
  (4, 4),   -- David -> Python
  (5, 5);   -- Eva   -> Angular

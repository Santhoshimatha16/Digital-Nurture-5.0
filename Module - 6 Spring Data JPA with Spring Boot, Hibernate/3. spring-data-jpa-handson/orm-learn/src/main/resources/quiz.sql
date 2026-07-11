-- ============================================================
-- Hands-on 3: Quiz Schema for ormlearn database
-- Tables: user, question, options, attempt, attempt_question, attempt_option
-- Run this in MySQL Workbench or mysql CLI in ormlearn schema
-- ============================================================

USE ormlearn;

-- -------------------------------------------------------
-- Master data tables
-- -------------------------------------------------------

CREATE TABLE IF NOT EXISTS `user` (
  `us_id`       INT NOT NULL AUTO_INCREMENT,
  `us_name`     VARCHAR(100) NOT NULL,
  `us_email`    VARCHAR(150) NOT NULL,
  PRIMARY KEY (`us_id`)
);

CREATE TABLE IF NOT EXISTS `question` (
  `qu_id`       INT NOT NULL AUTO_INCREMENT,
  `qu_text`     VARCHAR(500) NOT NULL,
  PRIMARY KEY (`qu_id`)
);

CREATE TABLE IF NOT EXISTS `options` (
  `op_id`       INT NOT NULL AUTO_INCREMENT,
  `op_text`     VARCHAR(300) NOT NULL,
  `op_score`    DOUBLE NOT NULL DEFAULT 0.0,
  `op_qu_id`    INT NOT NULL,
  PRIMARY KEY (`op_id`),
  CONSTRAINT `fk_op_qu` FOREIGN KEY (`op_qu_id`) REFERENCES `question` (`qu_id`)
);

-- -------------------------------------------------------
-- Attempt tables (transactional data)
-- -------------------------------------------------------

CREATE TABLE IF NOT EXISTS `attempt` (
  `at_id`       INT NOT NULL AUTO_INCREMENT,
  `at_us_id`    INT NOT NULL,
  `at_date`     DATE NOT NULL,
  PRIMARY KEY (`at_id`),
  CONSTRAINT `fk_at_us` FOREIGN KEY (`at_us_id`) REFERENCES `user` (`us_id`)
);

CREATE TABLE IF NOT EXISTS `attempt_question` (
  `aq_id`       INT NOT NULL AUTO_INCREMENT,
  `aq_at_id`    INT NOT NULL,
  `aq_qu_id`    INT NOT NULL,
  PRIMARY KEY (`aq_id`),
  CONSTRAINT `fk_aq_at` FOREIGN KEY (`aq_at_id`) REFERENCES `attempt` (`at_id`),
  CONSTRAINT `fk_aq_qu` FOREIGN KEY (`aq_qu_id`) REFERENCES `question` (`qu_id`)
);

CREATE TABLE IF NOT EXISTS `attempt_option` (
  `ao_id`       INT NOT NULL AUTO_INCREMENT,
  `ao_aq_id`    INT NOT NULL,
  `ao_op_id`    INT NOT NULL,
  `ao_is_answer` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ao_id`),
  CONSTRAINT `fk_ao_aq` FOREIGN KEY (`ao_aq_id`) REFERENCES `attempt_question` (`aq_id`),
  CONSTRAINT `fk_ao_op` FOREIGN KEY (`ao_op_id`) REFERENCES `options` (`op_id`)
);

-- -------------------------------------------------------
-- Sample master data
-- -------------------------------------------------------

INSERT INTO `user` (`us_name`, `us_email`) VALUES
  ('John Smith',  'john.smith@example.com'),
  ('Jane Doe',    'jane.doe@example.com');

INSERT INTO `question` (`qu_text`) VALUES
  ('What is the extension of the hyper text markup language file?'),
  ('What is the maximum level of heading tag can be used in a HTML page?'),
  ('The HTML document itself begins with <html> and ends </html>. State True or False'),
  ('Choose the right option to store text value value in a variable');

-- Options for Q1 (extension)
INSERT INTO `options` (`op_text`, `op_score`, `op_qu_id`) VALUES
  ('.xhtm', 0.0, 1),
  ('.ht',   0.0, 1),
  ('.html', 1.0, 1),
  ('.htmx', 0.0, 1);

-- Options for Q2 (heading level)
INSERT INTO `options` (`op_text`, `op_score`, `op_qu_id`) VALUES
  ('5', 0.0, 2),
  ('3', 0.0, 2),
  ('4', 0.0, 2),
  ('6', 1.0, 2);

-- Options for Q3 (true/false)
INSERT INTO `options` (`op_text`, `op_score`, `op_qu_id`) VALUES
  ('false', 0.0, 3),
  ('true',  1.0, 3);

-- Options for Q4 (variable)
INSERT INTO `options` (`op_text`, `op_score`, `op_qu_id`) VALUES
  ("'John'",  0.5, 4),
  ('John',    0.0, 4),
  ('"John"',  0.5, 4),
  ('/John/',  0.0, 4);

-- -------------------------------------------------------
-- Attempt data: user 1, attempt 1
-- -------------------------------------------------------

INSERT INTO `attempt` (`at_us_id`, `at_date`) VALUES (1, '2024-06-01');

-- Attempt questions (all 4 questions for attempt 1)
INSERT INTO `attempt_question` (`aq_at_id`, `aq_qu_id`) VALUES
  (1, 1),  -- aq_id=1: Q1
  (1, 2),  -- aq_id=2: Q2
  (1, 3),  -- aq_id=3: Q3
  (1, 4);  -- aq_id=4: Q4

-- Attempt options for Q1 (all options; user selected op_id=3 => .html)
INSERT INTO `attempt_option` (`ao_aq_id`, `ao_op_id`, `ao_is_answer`) VALUES
  (1, 1, 0),   -- .xhtm  not selected
  (1, 2, 0),   -- .ht    not selected
  (1, 3, 1),   -- .html  selected (correct)
  (1, 4, 0);   -- .htmx  not selected

-- Attempt options for Q2 (user selected op_id=6 => '3', correct is op_id=8 => '6')
INSERT INTO `attempt_option` (`ao_aq_id`, `ao_op_id`, `ao_is_answer`) VALUES
  (2, 5, 0),   -- 5  not selected
  (2, 6, 1),   -- 3  selected (wrong answer, but user chose it)
  (2, 7, 0),   -- 4  not selected
  (2, 8, 0);   -- 6  not selected

-- Attempt options for Q3 (user selected op_id=10 => 'true')
INSERT INTO `attempt_option` (`ao_aq_id`, `ao_op_id`, `ao_is_answer`) VALUES
  (3, 9,  0),   -- false not selected
  (3, 10, 1);   -- true  selected

-- Attempt options for Q4 (user selected op_id=11 => 'John')
INSERT INTO `attempt_option` (`ao_aq_id`, `ao_op_id`, `ao_is_answer`) VALUES
  (4, 11, 1),   -- 'John' selected
  (4, 12, 0),   -- John   not selected
  (4, 13, 0),   -- "John" not selected
  (4, 14, 0);   -- /John/ not selected

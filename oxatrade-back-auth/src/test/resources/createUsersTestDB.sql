-- users definition
CREATE TABLE IF NOT EXISTS users (
  id BINARY(16) NOT NULL,
  email varchar(64) NOT NULL,
  password varchar(2048) NOT NULL,
  phone varchar(25) DEFAULT NULL,
  provider varchar(25) DEFAULT NULL COMMENT 'google or facebook, etc.',
  user_role varchar(32) NOT NULL DEFAULT 'USER',
  first_name varchar(64) NOT NULL,
  last_name varchar(64) DEFAULT NULL,
  title varchar(32) DEFAULT NULL,
  enabled BOOLEAN NOT NULL DEFAULT '0',
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY email (email)
) ENGINE=InnoDB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Table of users';

-- Triggers to automatically generate the UUID by "users"
 CREATE TRIGGER IF NOT EXISTS before_insert_users
 BEFORE INSERT ON users
 FOR EACH ROW
 BEGIN
     IF NEW.id IS NULL THEN
         SET NEW.id = (UUID_TO_BIN(UUID()));
     END IF;
 END;

-- Create new admin with pass: "12345$aA"
INSERT INTO users(
	email,
	password,
	user_role,
	first_name,
	last_name,
	enabled)
VALUES ("admin@adminov.com", "Ojv3Ym0Rg2atgLuVEhe3Ek9xVWp+zYQSvj51M7L66LA=", "ADMIN", "admin", "adminov", true);

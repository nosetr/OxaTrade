CREATE SCHEMA IF NOT EXISTS oxatrade;

-- oxatrade.users definition
CREATE TABLE `users` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(64) NOT NULL,
  `password` varchar(2048) NOT NULL,
  `provider` varchar(25) DEFAULT NULL COMMENT 'google or facebook, etc.',
  `user_role` varchar(32) NOT NULL DEFAULT 'USER',
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `title` varchar(32) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Table of users';

-- Unlike MySql, Postgres doesn’t come with inbuilt functionality.
-- So to solve this problem we will be using Triggers and Procedures.
-- Next step is creating a Postgres function which PL/pgSQL programming supports.
-- CREATE FUNCTION update_updated_at_users()
-- RETURNS TRIGGER AS $$
-- BEGIN
--     NEW.updated_at = now();
--     RETURN NEW;
-- END;
-- $$ language 'plpgsql';

-- Create the Trigger.
-- It will execute the update_updated_at_users() function that we defined earlier.
-- It will do so whenever a row is updated in the users table.
-- CREATE TRIGGER update_users_updated_at
--     BEFORE UPDATE
--     ON  users
--     FOR EACH ROW
-- EXECUTE PROCEDURE update_updated_at_users();

/*
 * Create new admin with pass: "12345$aA"
 */
INSERT INTO oxatrade.users(
	email,
	password,
	user_role,
	first_name,
	last_name,
	enabled)
VALUES ("admin@adminov.com", "Ojv3Ym0Rg2atgLuVEhe3Ek9xVWp+zYQSvj51M7L66LA=", "ADMIN", "admin", "adminov", true);

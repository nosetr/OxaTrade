CREATE SCHEMA IF NOT EXISTS oxatrade;

-- Create the "users" Table
CREATE TABLE IF NOT EXISTS oxatrade.users (
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(64) NOT NULL UNIQUE,
    password   VARCHAR(2048) NOT NULL,
    user_role  VARCHAR(32) NOT NULL DEFAULT "USER",
    first_name VARCHAR(64) NOT NULL,
    last_name  VARCHAR(64) DEFAULT NULL,
    title 		 VARCHAR(32) DEFAULT NULL,
    enabled    BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Table of users';

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

CREATE SCHEMA IF NOT EXISTS oxatrade;

-- Create the "users" Table
CREATE TABLE IF NOT EXISTS oxatrade.users (
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(64)   NOT NULL UNIQUE,
    password   VARCHAR(2048) NOT NULL,
    user_role  VARCHAR(32)   NOT NULL,
    first_name VARCHAR(64)   NOT NULL,
    last_name  VARCHAR(64)   NOT NULL,
    enabled    BOOLEAN       NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

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

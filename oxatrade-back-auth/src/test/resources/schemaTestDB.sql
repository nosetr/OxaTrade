
CREATE SCHEMA IF NOT EXISTS oxatrade_test;

-- Falls Funktion "uuid_generate_v4()" in PostgreSQL-Datenbank nicht verfügbar ist:
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- users definition
CREATE TABLE IF NOT EXISTS users (
  id UUID NOT NULL DEFAULT uuid_generate_v4(),
  email varchar(64) NOT NULL,
  password varchar(2048) NOT NULL,
  phone varchar(25),
  provider varchar(25),
  user_role varchar(32) NOT NULL DEFAULT 'USER',
  first_name varchar(64) NOT NULL,
  last_name varchar(64),
  title varchar(32),
  enabled BOOLEAN NOT NULL DEFAULT false,
  created_at timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT email_unique UNIQUE (email)
);

-- DROP DATABASE IF EXISTS oxatrade_webflux;

-- NEWSLETTER-BLOCK:

-- oxatrade_webflux.newsletter definition
CREATE TABLE IF NOT EXISTS oxatrade_webflux.newsletter (
	id BINARY(16) NOT NULL,
  email varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT '0' COMMENT 'Email is verificated',
  last_update timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='List of emails for newsletter-mailing';

-- Triggers to automatically generate the UUID by "users"
 CREATE TRIGGER IF NOT EXISTS before_insert_newsletter
 BEFORE INSERT ON oxatrade_webflux.newsletter
 FOR EACH ROW
 BEGIN
     IF NEW.id IS NULL THEN
         SET NEW.id = (UUID_TO_BIN(UUID()));
     END IF;
 END;

-- oxatrade_webflux.newsthema definition
CREATE TABLE IF NOT EXISTS oxatrade_webflux.newsthema (
	id bigint unsigned NOT NULL AUTO_INCREMENT,
  thema_name varchar(25) NOT NULL COMMENT 'title',
  memo text COMMENT 'memo/information',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='List of themas spec. for newsletter-mailing';

-- Dumping data for table "oxatrade_webflux.countries"
INSERT INTO oxatrade_webflux.newsthema (thema_name, memo) VALUES
('DEFAULT', 'Auto created');

-- oxatrade_webflux.newsletter_newsthema definition
-- Create the Join Table to establish a many-to-many relationship
CREATE TABLE IF NOT EXISTS oxatrade_webflux.newsletter_newsthema (
	email_id BINARY(16) NOT NULL,
  thema_id bigint unsigned NOT NULL,
  PRIMARY KEY (email_id, thema_id),
  FOREIGN KEY (email_id) REFERENCES oxatrade_webflux.newsletter(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (thema_id) REFERENCES oxatrade_webflux.newsthema(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Many-to-Many relationship between newsletter and newsthema';

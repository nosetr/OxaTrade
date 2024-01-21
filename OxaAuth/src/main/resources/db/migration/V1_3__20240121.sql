-- NEWSLETTER-BLOCK:

-- oxatrade.newsletter definition
CREATE TABLE IF NOT EXISTS oxatrade.newsletter (
  email varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT '0' COMMENT 'Email is verificated',
  last_update timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='List of emails for newsletter-mailing';

-- Dumping data oxatrade.newsletter
INSERT INTO oxatrade.newsletter (email, enabled) VALUES
("admin@adminov.com", true);

-- oxatrade.newsthema definition
CREATE TABLE IF NOT EXISTS oxatrade.newsthema (
	id bigint unsigned NOT NULL AUTO_INCREMENT,
  thema_name varchar(64) NOT NULL COMMENT 'title',
  memo text COMMENT 'memo/information',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='List of themas spec. for newsletter-mailing';

-- Dumping data oxatrade.newsthema
INSERT INTO oxatrade.newsthema (thema_name, memo) VALUES
("Default", "Auto created");

-- oxatrade.newsletter_newsthema definition
-- Create the Join Table to establish a many-to-many relationship
CREATE TABLE IF NOT EXISTS oxatrade.newsletter_newsthema (
  email varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  thema_id bigint unsigned NOT NULL,
  PRIMARY KEY (email, thema_id),
  FOREIGN KEY (email) REFERENCES oxatrade.newsletter(email) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (thema_id) REFERENCES oxatrade.newsthema(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Many-to-Many relationship between newsletter and newsthema';

-- Dumping data oxatrade.newsletter_newsthema
INSERT INTO oxatrade.newsletter_newsthema (email, thema_id) VALUES
("admin@adminov.com", 1);



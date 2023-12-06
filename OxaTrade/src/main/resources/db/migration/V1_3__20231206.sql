-- Create the "organizations" Table
CREATE TABLE IF NOT EXISTS oxatrade.organizations (
    id					SERIAL PRIMARY KEY,
    org_name		VARCHAR(64)   NOT NULL,
    address			BIGINT UNSIGNED,
    email				VARCHAR(64),
    enabled			BOOLEAN       NOT NULL DEFAULT TRUE,
    creator			SERIAL NOT NULL,
    created_at	TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at	TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

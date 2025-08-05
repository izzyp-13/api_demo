CREATE SCHEMA texas;
CREATE TABLE IF NOT EXISTS school_district (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     superintendent VARCHAR(255),
     address VARCHAR(255)
);
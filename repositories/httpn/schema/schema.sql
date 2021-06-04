-- mysql5.7
CREATE DATABASE test
  CHARACTER SET utf8;

USE test;

-- user
CREATE TABLE IF NOT EXISTS user (
  id         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(32)            NOT NULL,
  password   VARCHAR(32)            NOT NULL,
  age        INT                    NOT NULL,
  created_at DATETIME DEFAULT NOW() NOT NULL,
  updated_at DATETIME DEFAULT NOW() NOT NULL
);

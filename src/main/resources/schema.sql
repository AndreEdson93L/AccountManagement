CREATE TABLE Account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255),
    consent BOOLEAN NOT NULL,
    role ENUM('ADMIN', 'USER') NOT NULL
);

CREATE TABLE history (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   action VARCHAR(255) NOT NULL,
   timestamp TIMESTAMP NOT NULL,
   account_id BIGINT,
   FOREIGN KEY (account_id) REFERENCES account(id)
);

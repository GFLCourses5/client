CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

INSERT INTO users(password, email) VALUES('test1', 'test1@gmail.com');
INSERT INTO users(password, email) VALUES('test2', 'test2@gmail.com');
INSERT INTO users(password, email) VALUES('test3', 'test3@gmail.com');
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

INSERT INTO users(password, email, name) VALUES('$2a$08$95ODixNWRwnYKHOfzB8VhOtIys4qw3.pKqd0tIYa2VMc2VNHiGQfC', 'test1@gmail.com', 'Foo');
INSERT INTO users(password, email, name) VALUES('$2a$08$46lK6mkg6lXTiNXUL2.KleA5cKSWOf57XtZ4gPu5ijSgFI36V6Zra
', 'test2@gmail.com', 'Bar');
INSERT INTO users(password, email, name) VALUES('$2a$08$/QNeWViyP9jlIt/ml3l7AOtuevjaoZO4/e9tOi3y9qIP3MUtnxEFm', 'test3@gmail.com', 'John');
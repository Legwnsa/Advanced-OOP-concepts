CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       type VARCHAR(20) NOT NULL,
                       price NUMERIC(10,2) NOT NULL,
                       author_id INT,
                       CONSTRAINT fk_author FOREIGN KEY(author_id) REFERENCES authors(id)
);



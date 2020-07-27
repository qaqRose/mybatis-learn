-- database schema

-- table
DROP TABLE book;
DROP TABLE book_type;


CREATE TABLE book (
id          INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
name        VARCHAR(20) NOT NULL,
type_id       INT NOT NULL,
PRIMARY KEY (id)
);


CREATE TABLE book_type (
id     INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
type   VARCHAR(20) NOT NULL,
PRIMARY KEY (id)
);




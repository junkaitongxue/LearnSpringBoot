-- CREATE DATABASE learn_db;

CREATE TABLE IF NOT EXISTS t_persons
(
    Id_P      varchar(255) NOT NULL,
    LastName  varchar(255) NOT NULL,
    FirstName varchar(255),
    Address   varchar(255),
    City      varchar(255),
    PRIMARY KEY (Id_P)
);
COMMENT on table t_persons is 'The persons table';

CREATE TABLE IF NOT EXISTS t_paper
(
    paper_id serial PRIMARY KEY,
    name varchar(255),
    number varchar(255),
    detail varchar(255)
);

COMMENT on table t_paper is 'The paper table';
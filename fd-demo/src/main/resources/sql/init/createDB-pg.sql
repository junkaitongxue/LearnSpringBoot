-- CREATE DATABASE learn_db;

CREATE TABLE sys_image
(
    id        varchar NOT NULL,
    "name"    varchar NOT NULL,
    "content" bytea NULL,
    CONSTRAINT sys_image_pk PRIMARY KEY (id)
);

COMMENT
on table t_persons is '图片数据表';

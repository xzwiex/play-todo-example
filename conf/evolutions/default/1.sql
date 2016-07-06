-- Users schema

# --- !Ups

CREATE TABLE profile (
    id SERIAL,
    email varchar(255),
    name varchar(255),
    primary key(id)
);

# --- !Downs

DROP TABLE profile;
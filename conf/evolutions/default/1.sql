-- Users schema

# --- !Ups

CREATE TABLE todo (
    id SERIAL,
    text VARCHAR(255),
    finished BOOLEAN,
    weight INT
);


# --- !Downs

DROP TABLE todo;
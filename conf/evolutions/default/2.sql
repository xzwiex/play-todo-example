-- Users schema

# --- !Ups

CREATE TABLE profile (
    id SERIAL,
    email varchar(255)
);


# --- !Downs

DROP TABLE profile;
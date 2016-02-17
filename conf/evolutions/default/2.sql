-- Users schema

# --- !Ups

CREATE TABLE profile (
    id IDENTITY,
    email varchar(255)
);


# --- !Downs

DROP TABLE profile;
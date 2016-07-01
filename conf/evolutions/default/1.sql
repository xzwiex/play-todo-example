-- Users schema

# --- !Ups

CREATE TABLE profile (
    id SERIAL,
    email varchar(255),
    name varchar(255),
    primary key(id)
);


CREATE TABLE todo (
    id SERIAL,
    text VARCHAR(255),
    finished BOOLEAN,
    profile_id int references profile("id"),
    weight INT,
    primary key(id)
);


# --- !Downs

DROP TABLE todo;
DROP TABLE profile;
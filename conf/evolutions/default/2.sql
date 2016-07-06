-- Users schema

# --- !Ups

CREATE TABLE todo (
    id SERIAL,
    text VARCHAR(255),
    finished BOOLEAN,
    profile_id int not null references profile("id"),
    weight INT,
    primary key(id)
);

# --- !Downs

DROP TABLE todo;
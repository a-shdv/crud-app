DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    username   VARCHAR(255)          NOT NULL UNIQUE,
    password   VARCHAR(255)          NOT NULL,
    age        INTEGER               NOT NULL,
    house_id   BIGINT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    FOREIGN KEY (house_id) REFERENCES houses (id)
);
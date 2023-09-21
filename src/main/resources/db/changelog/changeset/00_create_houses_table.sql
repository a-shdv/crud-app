DROP TABLE IF EXISTS houses CASCADE;

CREATE TABLE houses
(
    id      BIGSERIAL PRIMARY KEY  NOT NULL ,
    address VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6)
);

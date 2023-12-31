DROP TABLE IF EXISTS user_roles CASCADE;

CREATE TABLE users_roles
(
    user_id    BIGINT NOT NULL ,
    user_roles VARCHAR(255) CHECK (user_roles IN ('OWNER', 'TENANT')),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

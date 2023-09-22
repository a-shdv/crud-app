ALTER TABLE users
    ALTER COLUMN username
        DROP NOT NULL;

ALTER TABLE users
    ALTER COLUMN password
        DROP NOT NULL;

ALTER TABLE users
    ALTER COLUMN age
        DROP NOT NULL;
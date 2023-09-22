INSERT INTO users (age, password, username)
    VALUES (25, 'owner', 'owner'),
           (22, 'user', 'user');

INSERT INTO users_roles (user_id, user_roles)
    VALUES(1, 'OWNER'),
          (2, 'TENANT')
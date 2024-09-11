# insert some sample posts

-- !Ups

INSERT INTO Posts (title, content)
    VALUES
        ('test title', 'test content'),
        ('study', 'spring boot')
        ;

-- !Downs
DELETE FROM Posts WHERE title in ('test title', 'study');
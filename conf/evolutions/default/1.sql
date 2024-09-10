-- Posts Table

-- !Ups

CREATE TABLE Posts (
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
title varchar(100) NOT NULL,
content varchar(200) NOT NULL
);

-- !Downs

DROP TABLE Posts;
USE `news_db`;

CREATE TABLE `users` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL UNIQUE,
    `password`  CHAR(32) NOT NULL,
    `role` ENUM ('administrator', 'writer', 'visitor'),
    `name` VARCHAR (32) NOT NULL,
    `surname` VARCHAR (32) NOT NULL,
    `date_of_birth` DATE NOT NULL,
    `status` ENUM ('blocked', 'unblocked'),
    `registration_date` DATE NOT NULL,
    `icon_url` VARCHAR(255),
    CONSTRAINT `PK_users` PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `articles` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `title` VARCHAR (255) NOT NULL UNIQUE,
    `text` TEXT NOT NULL ,
    `status`  ENUM ('not_approved', 'approved'),
    `writer_id` INTEGER NOT NULL,
    `creation_date` DATETIME,
    `category` ENUM ('World', 'Science', 'Politics', 'Accidents', 'Technology', 'Cars'),
    `image_url` VARCHAR (255),
    CONSTRAINT `PK_articles` PRIMARY KEY (`id`),
    CONSTRAINT `FK_articles_users` FOREIGN KEY (`writer_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `comments` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `text` VARCHAR (3000),
    `creation_date` DATETIME,
    `commentator_id` INTEGER NOT NULL,
    `article_id` INTEGER NOT NULL,
    `quoted_comment_id` INTEGER,
    CONSTRAINT `PK_comments` PRIMARY KEY (`id`),
    CONSTRAINT `FK_comments_users` FOREIGN KEY (`commentator_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `FK_comments_articles` FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`)  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


CREATE TABLE `comments_rating`(
    `evaluator_id` INTEGER NOT NULL,
    `evaluated_comment_id` INTEGER NOT NULL,
    `plus` BOOLEAN,
    `minus` BOOLEAN,
    CONSTRAINT `PK_comments_rating` PRIMARY KEY (`evaluator_id`,`evaluated_comment_id`),
    CONSTRAINT `FK_comments_rating_evaluator_id` FOREIGN KEY (`evaluator_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `FK_comments_rating_evaluated_comment_id` FOREIGN KEY (`evaluated_comment_id`) REFERENCES `comments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `marks`(
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT `PK_marks` PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `m2m_articles_marks`(
    `article_id` INTEGER NOT NULL,
    `mark_id` INTEGER NOT NULL,
    CONSTRAINT `PK_m2m_articles_marks` PRIMARY KEY (`article_id`, `mark_id`),
    CONSTRAINT `FK_m2m_articles_marks_articles` FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`) ON DELETE Cascade ON UPDATE Cascade,
    CONSTRAINT `FK_m2m_articles_marks_marks` FOREIGN KEY (`mark_id`) REFERENCES `marks` (`id`) ON DELETE Cascade ON UPDATE Cascade
)ENGINE=INNODB DEFAULT CHARACTER SET utf8;


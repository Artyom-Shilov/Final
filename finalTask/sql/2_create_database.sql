CREATE DATABASE `news_db` DEFAULT CHARACTER SET utf8;



GRANT SELECT,INSERT,UPDATE,DELETE
ON `news_db`.*
TO news_user@localhost
IDENTIFIED BY '123';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `news_db`.*
TO news_user@'%'
IDENTIFIED BY '123';
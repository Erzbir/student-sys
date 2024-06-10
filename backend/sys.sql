CREATE DATABASE IF NOT EXISTS `stu_sys`;
USE `stu_sys`;
CREATE TABLE IF NOT EXISTS `stu`
(
    `id`     BIGINT PRIMARY KEY,
    `name`   VARCHAR(50),
    `gender` CHAR,
    `major`  VARCHAR(50),
    `grade`  INT(8)
);

CREATE TABLE IF NOT EXISTS `user`
(
    `username` VARCHAR(50) PRIMARY KEY,
    `password` VARCHAR(50) NOT NULL
);
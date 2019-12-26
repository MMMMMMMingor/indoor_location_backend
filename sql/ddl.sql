CREATE DATABASE location DEFAULT CHARACTER SET utf8mb4 ;

USE location;

CREATE TABLE location.user_basic (
    user_id VARCHAR(32) NOT NULL COMMENT '主键ID、用户ID',
    username VARCHAR(45) NOT NULL UNIQUE COMMENT '账号',
    password VARCHAR(45) NOT NULL COMMENT '密码',
    PRIMARY KEY (user_id),
    INDEX idx_username (username)
)ENGINE = InnoDB;

CREATE TABLE location.user_information(
    user_id VARCHAR(32) NOT NULL COMMENT '主键ID',
    nickname VARCHAR(12) DEFAULT '未命名' COMMENT '昵称',
    gender VARCHAR(1) DEFAULT '男' COMMENT '性别',
    age INT(3) DEFAULT 18 COMMENT '年龄',
    vocation VARCHAR(12) DEFAULT '' COMMENT '职业',
    person_label VARCHAR(20) DEFAULT '' COMMENT '个人标签',
    avatar_url VARCHAR(50) DEFAULT '' COMMENT '头像地址',
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user_basic (user_id) ON DELETE CASCADE
)ENGINE = InnoDB;


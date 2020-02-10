CREATE DATABASE location DEFAULT CHARACTER SET utf8mb4 ;

USE location;

# 用户基础信息
CREATE TABLE location.user_basic (
    user_id VARCHAR(32) NOT NULL COMMENT '主键ID、用户ID',
    username VARCHAR(45) NOT NULL UNIQUE COMMENT '账号',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    email VARCHAR(100) NOT NULL COMMENT '邮箱',
    PRIMARY KEY (user_id),
    INDEX idx_username (username)
)ENGINE = InnoDB;

# 用户信息
CREATE TABLE location.user_information(
    user_id VARCHAR(32) NOT NULL COMMENT '主键ID',
    nickname VARCHAR(12) DEFAULT '未命名' COMMENT '昵称',
    gender VARCHAR(1) DEFAULT '男' COMMENT '性别',
    age INT(3) DEFAULT 18 COMMENT '年龄',
    vocation VARCHAR(12) DEFAULT '' COMMENT '职业',
    person_label VARCHAR(20) DEFAULT '' COMMENT '个人标签',
    avatar_url VARCHAR(100) DEFAULT '' COMMENT '头像地址',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '最近一次更新时间',
    PRIMARY KEY (user_id),
    INDEX idx_nickname (nickname),
    FOREIGN KEY (user_id) REFERENCES user_basic (user_id) ON DELETE CASCADE
)ENGINE = InnoDB;

# 商铺信息
CREATE TABLE location.store(
    store_id VARCHAR(32) NOT NULL COMMENT '主键ID',
    owner_id VARCHAR(32) NOT NULL COMMENT '店铺拥有者ID',
    store_name VARCHAR(20) NOT NULL DEFAULT '未命名' COMMENT '店铺名',
    address VARCHAR(50) NOT NULL DEFAULT '店铺地址' COMMENT '地址',
    business_time VARCHAR(20) NOT NULL DEFAULT '周一-周日 11:00-21:00' COMMENT '营业时间',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    PRIMARY KEY (store_id),
    FOREIGN KEY (owner_id) REFERENCES user_basic (user_id) ON DELETE CASCADE
)ENGINE = InnoDB;

# 菜单信息
CREATE TABLE location.menu_item(
    menu_id VARCHAR(32) NOT NULL COMMENT '主键ID',
    store_id VARCHAR(32) NOT NULL COMMENT '店铺ID',
    item_name VARCHAR(15) NOT NULL DEFAULT '' COMMENT '菜单名称',
    introduction VARCHAR(50) NOT NULL DEFAULT '' COMMENT '简介',
    price INT(5) NOT NULL DEFAULT 1 COMMENT '价格',
    image_url VARCHAR(50) NOT NULL DEFAULT '' COMMENT '图片地址',
    PRIMARY KEY (menu_id),
    INDEX idx_store_id (store_id),
    FOREIGN KEY (store_id) REFERENCES store (store_id) ON DELETE CASCADE
)ENGINE = InnoDB;

# 评论信息
CREATE TABLE location.comment(
    comment_id VARCHAR(32) NOT NULL COMMENT '主键ID',
    store_id VARCHAR(32) NOT NULL COMMENT '店铺ID',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    score INT(2) NOT NULL COMMENT '评分',
    comment VARCHAR(100) NOT NULL COMMENT '评论内容',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    PRIMARY KEY (comment_id),
    INDEX idx_store_id (store_id),
    FOREIGN KEY (user_id) REFERENCES user_basic (user_id) ON DELETE CASCADE,
    FOREIGN KEY (store_id) REFERENCES store (store_id) ON DELETE CASCADE
)ENGINE = InnoDB;

# 收藏信息
CREATE TABLE location.collection(
    collection_id VARCHAR(32) NOT NULL COMMENT '主键ID',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    store_id VARCHAR(32) NOT NULL COMMENT '商铺ID',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    PRIMARY KEY (collection_id),
    INDEX idx_user_id (user_id),
    FOREIGN KEY (user_id) REFERENCES user_basic (user_id) ON DELETE CASCADE,
    FOREIGN KEY (store_id) REFERENCES store (store_id) ON DELETE CASCADE
)ENGINE = InnoDB;

# 交友信息
CREATE TABLE location.friends(
    id VARCHAR(32) NOT NULL COMMENT '主键ID',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    friend_id VARCHAR(32) NOT NULL COMMENT '朋友ID',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    PRIMARY KEY (id),
    INDEX idx_user_id (user_id),
    FOREIGN KEY (user_id) REFERENCES user_basic (user_id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES user_basic (user_id) ON DELETE CASCADE
)ENGINE = InnoDB;
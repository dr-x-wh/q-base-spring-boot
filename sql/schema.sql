CREATE DATABASE IF NOT EXISTS `q_base` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs;
USE `q_base`;

SET NAMES utf8mb4;

-- ------------------------------
-- 用户表（存储用户核心信息）
-- ------------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user`
(
    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    username   VARCHAR(50)     NOT NULL COMMENT '用户名',
    password   VARCHAR(255)    NOT NULL COMMENT '密码',
    nickname   VARCHAR(50) COMMENT '昵称',
    gender     VARCHAR(50)     NOT NULL COMMENT '性别',
    role       VARCHAR(50)     NOT NULL DEFAULT 'user' COMMENT '用户权限',
    state      VARCHAR(50)     NOT NULL DEFAULT '1' COMMENT '用户状态',
    avatar_url VARCHAR(255) COMMENT '头像URL',
    updated_at DATETIME COMMENT '更新时间',
    updated_by VARCHAR(50) COMMENT '更新人',
    created_at DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    created_by VARCHAR(50)     NOT NULL DEFAULT 'system' COMMENT '创建人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_username` (`username`),
    INDEX `idx_status` (`state`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_zh_0900_as_cs COMMENT ='用户表';

-- ------------------------------
-- 字典表（存储字典类型，如性别、状态）
-- ------------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE IF NOT EXISTS `sys_dict`
(
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    code        VARCHAR(50)     NOT NULL COMMENT '字典类型编码',
    name        VARCHAR(50)     NOT NULL COMMENT '字典类型名称',
    description VARCHAR(255) COMMENT '字典描述',
    updated_at  DATETIME COMMENT '更新时间',
    updated_by  VARCHAR(50) COMMENT '更新人',
    created_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    created_by  VARCHAR(50)     NOT NULL DEFAULT 'system' COMMENT '创建人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_code` (`code`),
    INDEX `inx_code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_zh_0900_as_cs COMMENT ='字典类型表';

-- ------------------------------
-- 字典项表（存储字典类型下的具体值，如"男"、"女"）
-- ------------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE IF NOT EXISTS `sys_dict_item`
(
    id          BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
    dict_id     BIGINT           NOT NULL COMMENT '所属字典类型编码',
    item_key    VARCHAR(50)      NOT NULL COMMENT '字典项编码',
    item_value  VARCHAR(50)      NOT NULL COMMENT '字典项名称',
    has_default TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认值（0-否，1-是）',
    sort        TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序（数值越小越靠前）',
    state       TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否启用（0-否，1-是）',
    description VARCHAR(255) COMMENT '字典项描述',
    updated_at  DATETIME COMMENT '更新时间',
    updated_by  VARCHAR(50) COMMENT '更新人',
    created_at  DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    created_by  VARCHAR(50)      NOT NULL DEFAULT 'system' COMMENT '创建人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_item_key` (`dict_id`, `item_key`),
    INDEX `idx_dict_id` (`dict_id`),
    INDEX `idx_item_key` (`item_key`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_zh_0900_as_cs COMMENT ='字典项表';

-- ------------------------------
-- 插入字典类型（sys_dict 表）
-- ------------------------------
INSERT INTO `sys_dict` (`id`, `code`, `name`, `description`)
VALUES (1, 'gender', '性别', '用户性别类型'),
       (2, 'sys_user_state', '用户状态', '用户账号状态类型'),
       (3, 'sys_user_role', '用户权限', '用户账号权限');

-- ------------------------------
-- 插入字典项（sys_dict_item 表）
-- ------------------------------
INSERT INTO `sys_dict_item` (`dict_id`, `item_key`, `item_value`, `has_default`, `sort`, `state`, `description`)
VALUES (1, '1', '男', 0, 1, 1, '男性'),
       (1, '0', '女', 0, 2, 1, '女性'),
       (2, '1', '正常', 1, 1, 1, '正常'),
       (2, '0', '禁用', 0, 2, 1, '禁用'),
       (2, '-1', '锁定', 0, 3, 1, '锁定'),
       (3, 'user', '用户', 1, 1, 1, '用户'),
       (3, 'administrator', '开发者', 0, 9, 1, '开发者');

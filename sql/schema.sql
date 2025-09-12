CREATE DATABASE IF NOT EXISTS `q_base` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs;
USE `q_base`;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS sys_dict_item;
DROP TABLE IF EXISTS sys_dict;
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_user;

-- ------------------------------
-- 用户表（存储用户核心信息）
-- ------------------------------
CREATE TABLE IF NOT EXISTS sys_user
(
    id         BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    username   VARCHAR(63)  NOT NULL COMMENT '用户名',
    password   VARCHAR(255) NOT NULL COMMENT '密码',
    nickname   VARCHAR(63) COMMENT '昵称',
    avatar_url VARCHAR(255) COMMENT '头像URL',
    gender     VARCHAR(15)  NOT NULL COMMENT '性别',
    state      VARCHAR(15)  NOT NULL DEFAULT '1' COMMENT '状态，1启用，0禁用',
    updated_at DATETIME COMMENT '更新时间',
    updated_by VARCHAR(63) COMMENT '更新人',
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    created_by VARCHAR(63)  NOT NULL DEFAULT 'system' COMMENT '创建人',
    UNIQUE KEY uniq_username (username),
    INDEX idx_state (state)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

-- ------------------------------
-- 权限表
-- ------------------------------
CREATE TABLE IF NOT EXISTS sys_role
(
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    code        VARCHAR(15) NOT NULL COMMENT '权限标识',
    name        VARCHAR(15) NOT NULL COMMENT '权限名称',
    description VARCHAR(63) COMMENT '权限名称',
    state       VARCHAR(15) NOT NULL DEFAULT '1' COMMENT '状态，1启用，0禁用',
    updated_at  DATETIME COMMENT '更新时间',
    updated_by  VARCHAR(63) COMMENT '更新人',
    created_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    created_by  VARCHAR(63) NOT NULL DEFAULT 'system' COMMENT '创建人',
    UNIQUE KEY uniq_code (code),
    UNIQUE KEY uniq_name (name),
    INDEX idx_state (state)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';

-- ------------------------------
-- 用户权限表
-- ------------------------------
CREATE TABLE IF NOT EXISTS sys_user_role
(
    user_id    BIGINT UNSIGNED NOT NULL COMMENT '用户',
    role_id    BIGINT UNSIGNED NOT NULL COMMENT '权限',
    created_at DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    created_by VARCHAR(63)     NOT NULL DEFAULT 'system' COMMENT '创建人',
    PRIMARY KEY (user_id, role_id),
    INDEX idx_user (user_id),
    INDEX idx_role (role_id),
    FOREIGN KEY (user_id) REFERENCES sys_user (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES sys_role (id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户权限表';

-- ------------------------------
-- 字典表
-- ------------------------------
CREATE TABLE IF NOT EXISTS sys_dict
(
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    code        VARCHAR(15) NOT NULL COMMENT '字典类型编码',
    name        VARCHAR(15) NOT NULL COMMENT '字典类型名称',
    description VARCHAR(255) COMMENT '字典描述',
    updated_at  DATETIME COMMENT '更新时间',
    updated_by  VARCHAR(63) COMMENT '更新人',
    created_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    created_by  VARCHAR(63) NOT NULL DEFAULT 'system' COMMENT '创建人',
    UNIQUE KEY uniq_code (code),
    UNIQUE KEY uniq_name (name),
    INDEX idx_code (code)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典表';

-- ------------------------------
-- 字典项表
-- ------------------------------
CREATE TABLE IF NOT EXISTS sys_dict_item
(
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    dict_id     BIGINT UNSIGNED  NOT NULL COMMENT '所属字典类型编码',
    code        VARCHAR(63)      NOT NULL COMMENT '字典项编码',
    name        VARCHAR(63)      NOT NULL COMMENT '字典项名称',
    description VARCHAR(255) COMMENT '字典项描述',
    is_default  BOOLEAN          NOT NULL DEFAULT FALSE COMMENT '是否默认值',
    sort        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序（数值越小越靠前）',
    state       VARCHAR(15)      NOT NULL DEFAULT '1' COMMENT '状态，1启用，0禁用',
    updated_at  DATETIME COMMENT '更新时间',
    updated_by  VARCHAR(63) COMMENT '更新人',
    created_at  DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    created_by  VARCHAR(63)      NOT NULL DEFAULT 'system' COMMENT '创建人',
    UNIQUE KEY uniq_dict_code (dict_id, code),
    INDEX idx_dict_id (dict_id),
    INDEX idx_code (code),
    INDEX idx_state (state),
    FOREIGN KEY (dict_id) REFERENCES sys_dict (id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典项表';

-- ------------------------------
-- 插入默认权限
-- ------------------------------
INSERT INTO sys_role (code, name, description)
VALUES ('user', '用户', '系统用户'),
       ('admin', '管理员', '系统管理员'),
       ('administrator', '超级管理员', '系统超级管理员');

-- ------------------------------
-- 插入字典类型
-- ------------------------------
INSERT INTO sys_dict (id, code, name, description)
VALUES (1, 'gender', '性别', '性别类型');

-- ------------------------------
-- 插入字典项
-- ------------------------------
INSERT INTO sys_dict_item (dict_id, code, name, description, is_default, sort)
VALUES (1, 'male', '男', '男性', FALSE, 1),
       (1, 'female', '女', '女性', FALSE, 2);

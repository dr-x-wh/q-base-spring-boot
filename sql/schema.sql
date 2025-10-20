SET TIMEZONE = 'Asia/Shanghai';
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- ------------------------------
-- 用户表（存储用户核心信息）
-- ------------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE IF NOT EXISTS sys_user
(
    id         UUID PRIMARY KEY      DEFAULT UUID_GENERATE_V4(),
    username   VARCHAR(63)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    nickname   VARCHAR(63),
    avatar_url VARCHAR(255),
    gender     VARCHAR(15)  NOT NULL,
    state      VARCHAR(15)  NOT NULL DEFAULT '1',
    updated_at TIMESTAMP(0),
    updated_by VARCHAR(63),
    created_at TIMESTAMP(0) NOT NULL DEFAULT NOW(),
    created_by VARCHAR(63)  NOT NULL DEFAULT 'system'
);

-- ------------------------------
-- 权限表
-- ------------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE IF NOT EXISTS sys_role
(
    id          UUID PRIMARY KEY      DEFAULT UUID_GENERATE_V4(),
    code        VARCHAR(15)  NOT NULL UNIQUE,
    name        VARCHAR(15)  NOT NULL UNIQUE,
    description VARCHAR(63),
    state       VARCHAR(15)  NOT NULL DEFAULT '1',
    updated_at  TIMESTAMP(0),
    updated_by  VARCHAR(63),
    created_at  TIMESTAMP(0) NOT NULL DEFAULT NOW(),
    created_by  VARCHAR(63)  NOT NULL DEFAULT 'system'
);

-- ------------------------------
-- 用户权限表
-- ------------------------------
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE IF NOT EXISTS sys_user_role
(
    user_id    UUID,
    role_id    UUID,
    created_at TIMESTAMP(0) NOT NULL DEFAULT NOW(),
    created_by VARCHAR(63)  NOT NULL DEFAULT 'system',
    CONSTRAINT pk_value PRIMARY KEY (user_id, role_id)
);

-- ------------------------------
-- 字典表
-- ------------------------------
DROP TABLE IF EXISTS sys_dict;
CREATE TABLE IF NOT EXISTS sys_dict
(
    id          UUID PRIMARY KEY      DEFAULT UUID_GENERATE_V4(),
    code        VARCHAR(15)  NOT NULL UNIQUE,
    name        VARCHAR(15)  NOT NULL UNIQUE,
    description VARCHAR(255),
    updated_at  TIMESTAMP(0),
    updated_by  VARCHAR(63),
    created_at  TIMESTAMP(0) NOT NULL DEFAULT NOW(),
    created_by  VARCHAR(63)  NOT NULL DEFAULT 'system'
);

-- ------------------------------
-- 字典项表
-- ------------------------------
DROP TABLE IF EXISTS sys_dict_item;
CREATE TABLE IF NOT EXISTS sys_dict_item
(
    id          UUID PRIMARY KEY      DEFAULT UUID_GENERATE_V4(),
    dict_id     UUID         NOT NULL,
    code        VARCHAR(63)  NOT NULL,
    name        VARCHAR(63)  NOT NULL,
    description VARCHAR(255),
    is_default  BOOLEAN      NOT NULL DEFAULT FALSE,
    sort        INT          NOT NULL DEFAULT 1,
    state       VARCHAR(15)  NOT NULL DEFAULT '1',
    updated_at  TIMESTAMP(0),
    updated_by  VARCHAR(63),
    created_at  TIMESTAMP(0) NOT NULL DEFAULT NOW(),
    created_by  VARCHAR(63)  NOT NULL DEFAULT 'system',
    CONSTRAINT uq_dict_code UNIQUE (dict_id, code)
);

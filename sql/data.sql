SET TIMEZONE = 'Asia/Shanghai';
-- ------------------------------
-- 插入默认权限
-- ------------------------------
INSERT INTO sys_role (id, code, name, description)
VALUES ('e7bc76f8-aa1d-42c3-82f9-724e382e0565', 'user', '用户', '系统用户'),
       ('b138e309-b004-49a5-9f8b-ee7886fce93b', 'admin', '管理员', '系统管理员'),
       ('13c2abbe-6726-4beb-a018-46c265898e29', 'administrator', '超级管理员', '系统超级管理员');

-- ------------------------------
-- 插入字典类型
-- ------------------------------
INSERT INTO sys_dict (id, code, name, description)
VALUES ('a6069ec2-7115-427f-9d8e-c0b73b896afe', 'gender', '性别', '性别类型');

-- ------------------------------
-- 插入字典项
-- ------------------------------
INSERT INTO sys_dict_item (id, dict_id, code, name, description, is_default, sort)
VALUES ('7cf01bd3-5286-4e06-914d-c8187f8825ca', 'a6069ec2-7115-427f-9d8e-c0b73b896afe', 'male', '男', '男性', FALSE, 1),
       ('0958f801-8016-4754-be4b-f349a306978a', 'a6069ec2-7115-427f-9d8e-c0b73b896afe', 'female', '女', '女性', FALSE,
        2);

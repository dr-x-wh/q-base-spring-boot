SET TIMEZONE = 'Asia/Shanghai';
-- ------------------------------
-- 插入默认权限
-- ------------------------------
INSERT INTO sys_role (id, code, name, description)
VALUES ('0199e090-82ef-7a7f-a82f-3623b13d38b7', 'user', '用户', '系统用户'),
       ('0199e094-c363-7fdc-a6fe-4e3d9cbbb628', 'admin', '管理员', '系统管理员'),
       ('0199e094-dbbd-7453-a133-cba33d0b9573', 'administrator', '超级管理员', '系统超级管理员');

-- ------------------------------
-- 插入字典类型
-- ------------------------------
INSERT INTO sys_dict (id, code, name, description)
VALUES ('0199e094-f750-73f8-ac71-dc3bc01811ba', 'gender', '性别', '性别类型');

-- ------------------------------
-- 插入字典项
-- ------------------------------
INSERT INTO sys_dict_item (id, dict_id, code, name, description, is_default, sort)
VALUES ('0199e095-2856-72b1-8be7-b5567aae7b8e', '0199e094-f750-73f8-ac71-dc3bc01811ba', 'male', '男', '男性', FALSE, 1),
       ('0199e095-4236-7bd7-88b1-7fb491dfbe30', '0199e094-f750-73f8-ac71-dc3bc01811ba', 'female', '女', '女性', FALSE,
        2);

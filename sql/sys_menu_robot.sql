-- 呼叫任务明细按钮：需要替换parent_id
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', 2139, '1',  '#',  'F', '0', 'collect:robot:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', 2139, '5',  '#',  'F', '0', 'collect:robot:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

-- 呼叫任务管理按钮：需要替换parent_id
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', 2140, '1',  '#',  'F', '0', 'robot:pandect:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', 2140, '5',  '#',  'F', '0', 'robot:pandect:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

-- 删除菜单：任务管理新增、修改、删除，需要替换menu_id
DELETE FROM sys_menu where menu_id in (2122,2123,2124);
-- 删除菜单：案件综合查询新增、修改、删除，需要替换menu_id
DELETE FROM sys_menu where menu_id in (2127,2128,2129);
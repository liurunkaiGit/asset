-- 锁定状态
alter table t_lc_task add `suoding` int(2) DEFAULT 0 COMMENT '锁定状态';
-- 历史催记报表
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('历史催记报表', (SELECT t.menu_id from sys_menu t where t.menu_name='报表管理'), '4', '/call/recordhis', 'menuItem', 'M', '0', NULL, '#', 'admin', now(), 'admin', now(), '');
-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();
-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', @parentId, '1',  '#',  'F', '0', 'call:recordhis:export',   '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
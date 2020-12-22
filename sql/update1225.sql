INSERT INTO `sys_config` (`config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('批量外呼数据归档天数', 'batchCallHisDataMigrateDays', '0', 'N', 'zhang', '2020-12-14 15:29:21', '', NULL, '批量外呼数据归档天数');

INSERT INTO asset_dev.sys_menu (menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('批量外呼历史查询', (select t.menu_id from sys_menu t where t.menu_name = '批量外呼'), '4', '/batchcall/his/view', 'menuItem', 'C', '0', 'batchcall:his:view', '#', 'zhang', '2020-12-14 16:09:22', '', NULL, '');
INSERT INTO asset_dev.sys_menu (menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('查询', (select t.menu_id from sys_menu t where t.menu_name = '批量外呼历史查询'), '1', '#', 'menuItem', 'F', '0', 'batchcall:his:list', '#', 'admin', '2020-12-14 17:11:28', '', NULL, '');


INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('案件查询评分', (select t.menu_id from sys_menu t where t.menu_name = '案件综合查询'), '1', '#', '', 'F', '0', 'collect:duncase:findScore', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('组内案件查询', (select t.menu_id from sys_menu t where t.menu_name = '催收管理'), '13', '/collect/duncase/group/view', 'menuItem', 'C', '0', 'collect:duncase:group:view', '#', 'zhang', '2020-12-17 11:10:50', '', NULL, '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('组内每日过程指标', (select t.menu_id from sys_menu t where t.menu_name = '报表管理'), '4', '/report/process/group/view', 'menuItem', 'C', '0', 'report:process:group:view', '#', 'admin', '2020-12-17 11:14:35', '', NULL, '');




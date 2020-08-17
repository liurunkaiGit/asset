INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '1', '#', 'menuItem', 'F', '0', 'call:config:list', '#', 'zhang', '2020-08-17 11:52:54', 'admin', '2020-08-17 11:54:28', '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('新增', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '2', '#', 'menuItem', 'F', '0', 'call:config:add', '#', 'admin', '2020-08-17 11:54:56', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('修改', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '3', '#', 'menuItem', 'F', '0', 'call:config:edit', '#', 'admin', '2020-08-17 11:55:21', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('删除', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '4', '#', 'menuItem', 'F', '0', 'call:config:remove', '#', 'admin', '2020-08-17 11:55:44', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('导出', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '5', '#', 'menuItem', 'F', '0', 'call:config:export', '#', 'admin', '2020-08-17 11:56:08', '', NULL, '');

CREATE INDEX create_time_index ON t_lc_call_record (create_time);
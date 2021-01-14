INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('月度过程指标', (select t.menu_id from sys_menu t where t.menu_name = '报表管理'), '5', '/report/process/month/view', 'menuItem', 'C', '0', 'report:process:month:view', '#', 'zhang', '2021-01-05 16:29:33', '', NULL, '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('查询', (select t.menu_id from sys_menu t where t.menu_name = '月度过程指标'), '1', '#', '', 'F', '0', 'report:process:month:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '查询');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('导出', (select t.menu_id from sys_menu t where t.menu_name = '月度过程指标'), '5', '#', '', 'F', '0', 'report:process:month:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '导出');

ALTER TABLE t_lc_station_letter MODIFY content varchar(500) DEFAULT NULL COMMENT '消息内容';
ALTER TABLE t_lc_station_letter_agent MODIFY content varchar(500) DEFAULT NULL COMMENT '消息内容';

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('中银回收率报表', (select t.menu_id from sys_menu t where t.menu_name = '报表管理'), '1', '/report/recovery/zy/view', 'menuItem', 'C', '0', 'report:recovery:zy:view', '#', 'admin', '2020-04-09 16:15:45', '', NULL, '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('查询', (select t.menu_id from sys_menu t where t.menu_name = '中银回收率报表'), '1', '#', '', 'F', '0', 'report:recovery:zy:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '查询');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('导出', (select t.menu_id from sys_menu t where t.menu_name = '中银回收率报表'), '5', '#', '', 'F', '0', 'report:recovery:zy:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '导出');

INSERT INTO `sys_config` (`config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('中银orgId', 'zyOrgId', '208', 'Y', 'zhang', '2021-01-11 15:10:10', '', NULL, '中银机构id');

ALTER TABLE t_lc_report_day_process MODIFY COLUMN colling_case_money  decimal(16,2) DEFAULT NULL COMMENT '在催案件金额';

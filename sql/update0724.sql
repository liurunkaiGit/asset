INSERT INTO `sys_job` (`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('每日过程指标', 'DEFAULT', 'reportSchedule.createDayProcessReport', '0 0 22 * * ?', '1', '1', '1', 'zhang', '2020-07-21 09:47:34', 'zhang', '2020-07-22 09:29:36', '每天晚上10点定时生成每日过程指标报表');
INSERT INTO `sys_job` (`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('回收率报表', 'DEFAULT', 'reportSchedule.createRecoveryReport', '0 0 22 * * ?', '1', '1', '1', 'zhang', '2020-07-21 20:34:12', '', NULL, '每天晚上10点生成回收率报表');
INSERT INTO `sys_job` (`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('案件可联率报表', 'DEFAULT', 'reportSchedule.createCaseContactReport', '0 0 22 * * ?', '1', '1', '1', 'zhang', '2020-07-21 20:35:18', '', NULL, '每天晚上10点生成案件可联率报表');

update sys_menu set menu_name = '呼叫明细管理' where menu_name = '呼叫明细查询';

ALTER TABLE t_lc_robot_task add call_sign varchar(100) DEFAULT NULL COMMENT '电话码键值';
ALTER TABLE t_lc_robot_task add call_sign_value varchar(100) DEFAULT NULL COMMENT '电话码中文';
ALTER TABLE t_lc_robot_task add modify_by int(20) DEFAULT NULL COMMENT '修改人';
ALTER TABLE t_lc_robot_task add modify_time datetime DEFAULT NULL COMMENT '修改时间';
ALTER TABLE t_lc_robot_task add call_back_time datetime DEFAULT NULL COMMENT '呼入回调时间';

ALTER TABLE t_lc_robot_task_his add call_sign varchar(100) DEFAULT NULL COMMENT '电话码键值';
ALTER TABLE t_lc_robot_task_his add call_sign_value varchar(100) DEFAULT NULL COMMENT '电话码中文';
ALTER TABLE t_lc_robot_task_his add modify_by int(20) DEFAULT NULL COMMENT '修改人';
ALTER TABLE t_lc_robot_task_his add modify_time datetime DEFAULT NULL COMMENT '修改时间';
ALTER TABLE t_lc_robot_task_his add call_back_time datetime DEFAULT NULL COMMENT '呼入回调时间';

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('呼叫明细查询', (SELECT t.menu_id from sys_menu t where t.menu_name = '机器人任务管理'), '5', '/robot/his/view', 'menuItem', 'C', '0', 'robot:his:view', '#', 'zhang', '2020-07-21 14:20:40', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('呼叫任务查询', (SELECT t.menu_id from sys_menu t where t.menu_name = '机器人任务管理'), '6', '/robot/pandect/his/view', 'menuItem', 'C', '0', 'robot:pandect:his:view', '#', 'zhang', '2020-07-21 14:21:45', '', NULL, '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name = '历史明细查询'), '1', '#', 'menuItem', 'F', '0', 'robot:his:list', '#', 'zhang', '2020-07-21 14:26:13', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name = '历史任务查询'), '1', '#', 'menuItem', 'F', '0', 'robot:pandect:his:list', '#', 'admin', '2020-07-21 14:30:51', '', NULL, '');


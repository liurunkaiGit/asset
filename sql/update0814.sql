DROP TABLE IF EXISTS `t_lc_report_platform`;
CREATE TABLE `t_lc_report_platform` (
  `report_data` date DEFAULT NULL COMMENT '报表日期',
  `time_period` varchar(10) DEFAULT NULL COMMENT '时间段',
  `pa_called_num` int(5) DEFAULT NULL COMMENT '平安通话次数',
  `pa_call_len` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '平安通话时长',
  `pa_call_num` int(5) DEFAULT NULL COMMENT '平安拨打次数',
  `zj_called_num` int(5) DEFAULT NULL COMMENT '自建通话次数',
  `zj_call_len` varchar(20) DEFAULT NULL COMMENT '自建通话时长',
  `zj_call_num` int(5) DEFAULT NULL COMMENT '自建拨打次数',
  `total_called_num` int(5) DEFAULT NULL COMMENT '全部通话次数',
  `total_call_len` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全部通话时长',
  `total_call_num` int(5) DEFAULT NULL COMMENT '全部拨打次数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通时通次平台汇总报表';

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('通时通次报表', (SELECT t.menu_id from sys_menu t where t.menu_name='报表管理'), '5', '#', 'menuItem', 'M', '0', '', '#', 'admin', '2020-08-04 14:47:00', 'admin', '2020-08-04 14:47:45', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('平台汇总表', (SELECT t.menu_id from sys_menu t where t.menu_name='通时通次报表'), '2', '/report/platform', 'menuItem', 'C', '0', 'report:platform:view', '#', 'admin', '2020-08-04 14:49:13', '', NULL, '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('平台汇总表查询', (SELECT t.menu_id from sys_menu t where t.menu_name='平台汇总表'), '1',  '#',  'F', '0', 'report:platform:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('平台汇总表导出', (SELECT t.menu_id from sys_menu t where t.menu_name='平台汇总表'), '5',  '#',  'F', '0', 'report:platform:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

-- INSERT INTO `sys_job` (`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
-- VALUES ('通时通次-平台汇总表', 'DEFAULT', 'reportSchedule.createPlatformReport('')', '0 0 1 * * ?', '1', '1', '0', 'admin', '2020-08-05 09:40:59', '', '2020-08-05 09:41:12', '每天凌晨1点生成前一天的报表数据');

DROP TABLE IF EXISTS `t_lc_report_personal`;
CREATE TABLE `t_lc_report_personal` (
  `report_data` date DEFAULT NULL COMMENT '报表日期',
  `time_period` varchar(10) DEFAULT NULL COMMENT '时间段',
  `pa_called_num` int(5) DEFAULT NULL COMMENT '平安通话次数',
  `pa_call_len` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '平安通话时长',
  `pa_call_num` int(5) DEFAULT NULL COMMENT '平安拨打次数',
  `zj_called_num` int(5) DEFAULT NULL COMMENT '自建通话次数',
  `zj_call_len` varchar(20) DEFAULT NULL COMMENT '自建通话时长',
  `zj_call_num` int(5) DEFAULT NULL COMMENT '自建拨打次数',
  `total_called_num` int(5) DEFAULT NULL COMMENT '全部通话次数',
  `total_call_len` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全部通话时长',
  `total_call_num` int(5) DEFAULT NULL COMMENT '全部拨打次数',
  `user_group` varchar(10) DEFAULT NULL COMMENT '组别',
  `login_name` varchar(50) DEFAULT NULL COMMENT '坐席编码',
  `user_name` varchar(50) DEFAULT NULL COMMENT '坐席姓名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通时通次个人明细汇总报表';

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('个人明细汇总表', (SELECT t.menu_id from sys_menu t where t.menu_name='通时通次报表'), '1', '/report/personal', 'menuItem', 'C', '0', 'report:personal:view', '#', 'admin', '2020-08-04 14:49:13', '', NULL, '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人明细汇总表查询', (SELECT t.menu_id from sys_menu t where t.menu_name='个人明细汇总表'), '1',  '#',  'F', '0', 'report:personal:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人明细汇总表导出', (SELECT t.menu_id from sys_menu t where t.menu_name='个人明细汇总表'), '5',  '#',  'F', '0', 'report:personal:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

-- INSERT INTO `sys_job` (`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
-- VALUES ('通时通次-个人明细汇总表', 'DEFAULT', 'reportSchedule.createPersonalReport('')', '0 0 1 * * ?', '1', '1', '0', 'admin', '2020-08-05 09:40:59', '', '2020-08-05 09:41:12', '每天凌晨1点生成前一天的报表数据');

INSERT INTO `sys_config` (`config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('jxph', 'jxphSendCallRecordUrl', 'https://jxph.itaiping.com/gateway/v1/credit/universal/hd/record', 'Y', 'admin', '2020-08-10 11:52:10', '', NULL, 'jxph推送电催记录');

INSERT INTO `sys_config` (`config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('jxph', 'jxphOrgId', '214', 'Y', 'admin', '2020-08-10 15:07:36', '', NULL, 'jxph机构id');

INSERT INTO `sys_job` (`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('自建录音下载', 'DEFAULT', 'callTask.downloadZJCallFile', '0 0 3 * * ?', '1', '1', '1', 'admin', '2020-08-11 09:54:18', '', NULL, '只执行一次，因为之前的自建录音没有下载');



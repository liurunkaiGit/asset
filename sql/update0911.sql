alter table t_lc_task add column notebook varchar(100) DEFAULT NULL COMMENT '记事本';
alter table t_lc_task_his add column notebook varchar(100) DEFAULT NULL COMMENT '记事本';

DROP TABLE IF EXISTS `t_lc_report_personal_new`;
CREATE TABLE `t_lc_report_personal_new` (
  `report_data` date DEFAULT NULL COMMENT '报表日期',
  `time_period` varchar(10) DEFAULT NULL COMMENT '时间段',
  `platform` varchar(10) DEFAULT NULL COMMENT '平安通话次数',
  `call_len` decimal(10,2) DEFAULT NULL COMMENT '平安通话时长',
  `call_num` int(5) DEFAULT NULL COMMENT '平安拨打次数',
  `called_num` int(5) DEFAULT NULL COMMENT '自建通话次数',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '自建通话时长'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通时通次个人明细汇总报表';

DROP TABLE IF EXISTS `t_lc_report_platform_new`;
CREATE TABLE `t_lc_report_platform_new` (
  `report_data` date DEFAULT NULL COMMENT '报表日期',
  `time_period` varchar(10) DEFAULT NULL COMMENT '时间段',
  `called_num` int(5) DEFAULT NULL COMMENT '通话次数',
  `call_len` decimal(10,2) DEFAULT NULL COMMENT '通话时长',
  `call_num` int(5) DEFAULT NULL COMMENT '拨打次数',
  `platform` varchar(10) DEFAULT NULL COMMENT '平台'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通时通次平台汇总报表';
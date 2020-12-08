ALTER TABLE t_lc_call_record add COLUMN `ucid` varchar(64) DEFAULT NULL COMMENT '话务平台返回唯一id';

alter TABLE t_lc_task ADD COLUMN `back_case_date` date DEFAULT NULL COMMENT '退案日';
alter TABLE t_lc_task_his ADD COLUMN `back_case_date` date DEFAULT NULL COMMENT '退案日';

-- INSERT INTO `sys_job` (`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
-- VALUES ('通时通次-生成个人明细汇总报表', 'DEFAULT', 'reportSchedule.createPersonalReportResult(\'2020-08-03\')', '0 0 3 * * ?', '1', '1', '1', 'zhang', '2020-12-07 15:08:27', 'zhang', '2020-12-07 15:37:45', '每天晚上凌晨3点自动生成通时通次-个人明细汇总表数据');

ALTER TABLE t_lc_report_personal add COLUMN dy_called_num int(5) DEFAULT NULL COMMENT '度言通话次数';

ALTER TABLE t_lc_report_personal add COLUMN dy_call_len varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '度言通话时长';

ALTER TABLE t_lc_report_personal add COLUMN dy_call_num int(5) DEFAULT NULL COMMENT '度言拨打次数';
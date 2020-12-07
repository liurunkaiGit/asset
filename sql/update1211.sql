ALTER TABLE t_lc_call_record add COLUMN `ucid` varchar(64) DEFAULT NULL COMMENT '话务平台返回唯一id';

alter TABLE t_lc_task ADD COLUMN `back_case_date` date DEFAULT NULL COMMENT '退案日';
alter TABLE t_lc_task_his ADD COLUMN `back_case_date` date DEFAULT NULL COMMENT '退案日';
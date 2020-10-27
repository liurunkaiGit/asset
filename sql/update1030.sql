alter table org_package add column is_expire_auto_back_case tinyint(1) default null comment '是否到期自动退案,1：是，2：否';

alter TABLE t_lp_result MODIFY COLUMN `total_recycle` decimal(10,2) DEFAULT NULL COMMENT '累计回收';
alter TABLE t_lp_result MODIFY COLUMN `predict_commission` decimal(10,2) DEFAULT NULL COMMENT '预计佣金';
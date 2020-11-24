alter TABLE t_lp_result add COLUMN `remark` varchar(500) DEFAULT NULL COMMENT '备注';

alter TABLE t_lp_monthly_target add COLUMN `transfer_type` varchar(255) DEFAULT NULL COMMENT '账龄';

alter TABLE t_lp_monthly_target MODIFY COLUMN `allocation_of_seats` decimal(15,2) DEFAULT NULL COMMENT '分配席位';

alter TABLE t_lp_monthly_target MODIFY COLUMN `number_of_commission` int(9) DEFAULT NULL COMMENT '月初委案件数';
alter TABLE t_lp_monthly_target MODIFY COLUMN `commission_amount` decimal(15,2) DEFAULT NULL COMMENT '月初委案金额';

CREATE UNIQUE INDEX ympt ON t_lp_monthly_target(particular_year, particular_month, pro_id, transfer_type);

DROP INDEX ymp ON t_lp_monthly_target;

alter table t_lp_project_information MODIFY COLUMN `cases_number` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '人均委案量';

alter table t_lp_attendance MODIFY COLUMN `attendance_date` date DEFAULT NULL COMMENT '出勤日期';

ALTER TABLE t_lc_call_strategy_config add COLUMN `phone_type` TINYINT(1) DEFAULT NULL COMMENT '主叫号码类型,0:手机号;1:固话;2:无主叫线路';
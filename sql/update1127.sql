alter TABLE t_lp_result add COLUMN `remark` varchar(500) DEFAULT NULL COMMENT '备注';

alter TABLE t_lp_monthly_target add COLUMN `transfer_type` varchar(255) DEFAULT NULL COMMENT '账龄';

alter TABLE t_lp_monthly_target MODIFY COLUMN `allocation_of_seats` decimal(15,2) DEFAULT NULL COMMENT '分配席位';

alter TABLE t_lp_monthly_target MODIFY COLUMN `number_of_commission` int(9) DEFAULT NULL COMMENT '月初委案件数';
alter TABLE t_lp_monthly_target MODIFY COLUMN `commission_amount` decimal(15,2) DEFAULT NULL COMMENT '月初委案金额';

CREATE UNIQUE INDEX ympt ON t_lp_monthly_target(particular_year, particular_month, pro_id, transfer_type);

DROP INDEX ymp ON t_lp_monthly_target;

alter table t_lp_project_information MODIFY COLUMN `cases_number` varchar(200) DEFAULT NULL COMMENT '人均委案量';
alter table t_lp_project_information MODIFY COLUMN `per_household` varchar(200) DEFAULT NULL COMMENT '户均金额';
ALTER TABLE t_lp_monthly_target MODIFY COLUMN `commission_batch` VARCHAR(200) NOT NULL COMMENT '委案批次';

alter table t_lp_attendance MODIFY COLUMN `attendance_date` date DEFAULT NULL COMMENT '出勤日期';

ALTER TABLE t_lc_call_strategy_config add COLUMN `phone_type` TINYINT(1) DEFAULT NULL COMMENT '主叫号码类型,0:手机号;1:固话;2:无主叫线路';

INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('4', '甲方话务', 'JFHW', 'call_platform', NULL, NULL, 'Y', '0', 'zhang', '2020-11-17 15:20:01', '', NULL, '甲方话务');

alter table cur_assets_package_temp add COLUMN customer_no varchar(64) DEFAULT NULL COMMENT '客户号';
alter table cur_assets_package_temp add COLUMN sjrq varchar(64) DEFAULT NULL COMMENT '数据日期';
alter table cur_assets_package add COLUMN customer_no varchar(64) DEFAULT NULL COMMENT '客户号';
alter table cur_assets_package add COLUMN sjrq date DEFAULT NULL COMMENT '数据日期';

alter table cur_assets_package_his add COLUMN customer_no varchar(64) DEFAULT NULL COMMENT '客户号';
alter table cur_assets_package_his add COLUMN sjrq date DEFAULT NULL COMMENT '数据日期';

alter table phone_status add COLUMN flow_no varchar(128) DEFAULT NULL COMMENT '流水号';

alter table t_lc_call_record add COLUMN action_code varchar(100) DEFAULT NULL COMMENT '行动码键值';
alter table t_lc_call_record add COLUMN action_code_value varchar(100) DEFAULT NULL COMMENT '行动码中文';

alter table t_lc_call_record_his add COLUMN action_code varchar(100) DEFAULT NULL COMMENT '行动码键值';
alter table t_lc_call_record_his add COLUMN action_code_value varchar(100) DEFAULT NULL COMMENT '行动码中文';

update t_lc_column_query set column_value='Fresh-新任务|Search1-联系方式有效|Search2-联系方式无效|Found1-找到本人|Found2-找到联系人|PTP-承诺还款|Check-检查付款|TS-投诉|QT-其它' where table_name = 't_lc_task' and column_name = 'action_code' and column_type = 'dict';
update t_lc_column_query set column_value='Fresh-新任务|Search1-联系方式有效|Search2-联系方式无效|Found1-找到本人|Found2-找到联系人|PTP-承诺还款|Check-检查付款|TS-投诉|QT-其它' where table_name = 't_lc_duncase' and column_name = 'action_code' and column_type = 'dict';

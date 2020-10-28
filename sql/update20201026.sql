insert into sys_config ( config_name, config_key, config_value, config_type, create_by, create_time )values( '东桥orgId', 'dqConfigOrgId', '217', 'N', 'admin', sysdate());

alter table t_lc_call_record add COLUMN cnje decimal(20,2) DEFAULT NULL COMMENT '承诺金额';
alter table t_lc_call_record_his add COLUMN cnje decimal(20,2) DEFAULT NULL COMMENT '承诺金额';
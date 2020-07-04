alter table cur_assets_package add column dqyhje decimal(20,2) DEFAULT NULL COMMENT '当前已还金额';
alter table t_lc_task add column dqyhje decimal(20,2) DEFAULT NULL COMMENT '当前已还金额';
alter table t_lc_duncase add column ljyhje decimal(20,2) DEFAULT NULL COMMENT '累计已还金额';
alter table sys_user add column job_no varchar(64) DEFAULT NULL COMMENT '员工工牌号';

insert into sys_dict_data( dict_sort, dict_label, dict_value, dict_type, is_default, status, remark, create_by, create_time )values( 20, '当前已还金额', 'dqyhje', 't_lc_task', 'Y', '0', '当前已还金额', 'admin', sysdate() );
insert into sys_dict_data( dict_sort, dict_label, dict_value, dict_type, is_default, status, remark, create_by, create_time )values( 21, '累计已还金额', 'ljyhje', 't_lc_task', 'Y', '0', '累计已还金额', 'admin', sysdate() );
insert into sys_dict_data( dict_sort, dict_label, dict_value, dict_type, is_default, status, remark, create_by, create_time )values( 22, '逾期天数', 'overdue_days', 't_lc_task', 'Y', '0', '逾期天数', 'admin', sysdate() );
insert into sys_dict_data( dict_sort, dict_label, dict_value, dict_type, is_default, status, remark, create_by, create_time )values( 20, '逾期天数', 'overdue_days', 't_lc_duncase', 'Y', '0', '逾期天数', 'admin', sysdate() );



insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, create_by, create_time, modify_by, modify_time )values ( 208, '中银消金', 't_lc_task', 'dqyhje', '当前已还金额', 'number_interval', 'startDqyhje|endDqyhje', '1', NOW(), 1, NOW() );

insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, create_by, create_time, modify_by, modify_time )values ( 211, '中投保（浦发银行）', 't_lc_task', 'product_name', '产品名称', 'string', 'productName', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, create_by, create_time, modify_by, modify_time )values ( 211, '中投保（浦发银行）', 't_lc_task', 'dqyhje', '当前已还金额', 'number_interval', 'startDqyhje|endDqyhje', '1', NOW(), 1, NOW() );

insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, create_by, create_time, modify_by, modify_time )values ( 212, '捷信消金', 't_lc_task', 'product_name', '产品名称', 'string', 'productName', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, create_by, create_time, modify_by, modify_time )values ( 212, '捷信消金', 't_lc_task', 'dqyhje', '当前已还金额', 'number_interval', 'startDqyhje|endDqyhje', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, create_by, create_time, modify_by, modify_time )values ( 212, '捷信消金', 't_lc_task', 'ljyhje', '累计已还金额', 'number_interval', 'startLjyhje|endLjyhje', '1', NOW(), 1, NOW() );


insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, create_by, create_time, modify_by, modify_time )values ( 212, '捷信消金', 't_lc_task', 'overdue_days', '逾期天数', 'number_interval', 'startOverdueDays|endOverdueDays', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, create_by, create_time, modify_by, modify_time )values ( 212, '捷信消金', 't_lc_duncase', 'overdue_days', '逾期天数', 'number_interval', 'startOverdueDays|endOverdueDays', '1', NOW(), 1, NOW() );


alter table t_lc_call_record add column make_call_time datetime DEFAULT NULL COMMENT '拨打时间';
alter table t_lc_collectionrecordimprot_temp add column make_call_time datetime DEFAULT NULL COMMENT '拨打时间';

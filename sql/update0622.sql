UPDATE t_lc_call_record t set t.agent_name= '机器人'
WHERE EXISTS(SELECT 1 from t_lc_duncase b where t.case_no = b.case_no and b.org_id = '212' and t.agent_name is null);

ALTER TABLE org_package add is_auto_score char(1) DEFAULT NULL COMMENT '是否度小满自动评分(1是,2否)';
drop table if exists t_lc_score;
create table t_lc_score
(
   id                   bigint not null AUTO_INCREMENT comment '主键',
   org_casno            varchar(64) comment '机构案件号',
   score                int comment '催收评分',
   org_id               varchar(64),
   org_name             varchar(64),
   import_batch_no      varchar(64) comment '导入批次号',
   is_auto_score        char(1) comment '是否自动评分(1是,2否)',
   create_by            varchar(64),
   create_time          datetime,
   update_by            varchar(64),
   update_time          datetime,
   primary key (id)
);
alter table t_lc_score comment '催收评分表';

insert into sys_dict_data( dict_sort, dict_label, dict_value, dict_type, is_default, status, remark, create_by, create_time )values( 21, '催收评分', 'score', 't_lc_duncase', 'Y', '0', '催收评分', 'admin', sysdate() );

insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, table_prefix, create_by, create_time, modify_by, modify_time ) values ( 208, '中银消金', 't_lc_duncase', 'score', '催收评分', 'number_interval', 'startScore|endScore', 'tt', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, table_prefix, create_by, create_time, modify_by, modify_time ) values ( 211, '中投保（浦发银行）', 't_lc_duncase', 'score', '催收评分', 'number_interval', 'startScore|endScore', 'tt', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, table_prefix, create_by, create_time, modify_by, modify_time ) values ( 212, '捷信消金', 't_lc_duncase', 'score', '催收评分', 'number_interval', 'startScore|endScore', 'tt', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, table_prefix, create_by, create_time, modify_by, modify_time ) values ( 213, '兴业消金', 't_lc_duncase', 'score', '催收评分', 'number_interval', 'startScore|endScore', 'tt', '1', NOW(), 1, NOW() );


insert into sys_dict_data( dict_sort, dict_label, dict_value, dict_type, is_default, status, remark, create_by, create_time )values( 21, '催收评分', 'score', 't_lc_task', 'Y', '0', '催收评分', 'admin', sysdate() );

insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, table_prefix, create_by, create_time, modify_by, modify_time ) values ( 208, '中银消金', 't_lc_task', 'score', '催收评分', 'number_interval', 'startScore|endScore', 'tt', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, table_prefix, create_by, create_time, modify_by, modify_time ) values ( 211, '中投保（浦发银行）', 't_lc_task', 'score', '催收评分', 'number_interval', 'startScore|endScore', 'tt', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, table_prefix, create_by, create_time, modify_by, modify_time ) values ( 212, '捷信消金', 't_lc_task', 'score', '催收评分', 'number_interval', 'startScore|endScore', 'tt', '1', NOW(), 1, NOW() );
insert into t_lc_column_query ( org_id, org_name, table_name, column_name, column_name_cn, column_type, bean_name, table_prefix, create_by, create_time, modify_by, modify_time ) values ( 213, '兴业消金', 't_lc_task', 'score', '催收评分', 'number_interval', 'startScore|endScore', 'tt', '1', NOW(), 1, NOW() );

insert into sys_job( job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time )values( '自动评分', 'SYSTEM', 'ScoreTimer.autoScoreTimer', '0 0/2 * * * ? ', '1', '0', '0', 'admin', sysdate() );

INSERT into t_lc_score(org_casno,org_id,org_name,import_batch_no,is_auto_score,create_by,create_time)
select t.case_no,t.org_id,t.org_name,t.import_batch_no,
(SELECT is_auto_score from org_package tt WHERE t.org_id = tt.dept_id ) as is_auto_score ,
(SELECT tt.login_name from sys_user tt WHERE t.create_by = tt.user_id )as create_by,
t.create_time
FROM t_lc_duncase t;

CREATE INDEX case_no_index ON t_lc_cust_contact (case_no);


-- 创建索引
CREATE INDEX case_no_index ON t_lc_duncase_assign (case_no);
CREATE INDEX case_no_index ON t_lc_call_record (case_no);
CREATE INDEX case_no_index ON t_lc_duncase_action_record (case_no);
CREATE INDEX case_no_index ON cur_assets_repayment_package (org_casNo);
CREATE INDEX case_no_index ON free_import (org_casno);
CREATE INDEX case_no_index ON t_lc_select_record (case_no);












alter TABLE t_lc_duncase add COLUMN score int(11) DEFAULT NULL COMMENT '催收评分';
alter TABLE t_lc_score add COLUMN sendflag char(1) DEFAULT NULL COMMENT '是否推送案件表(0未推送,1已推送)';
insert into sys_job( job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time )values( '更新评分', 'SYSTEM', 'ScoreTimer.updateDuncaseScoreTimer', '0 0/5 * * * ? ', '1', '0', '0', 'admin', sysdate() );

update t_lc_column_query SET table_prefix = 'd' where table_name = 't_lc_task' and table_prefix = 'tt' and column_name_cn = '催收评分';
update t_lc_column_query SET table_prefix = 't' where table_name = 't_lc_duncase' and table_prefix = 'tt' and column_name_cn = '催收评分';
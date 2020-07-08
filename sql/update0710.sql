alter table t_lc_cust_job MODIFY COLUMN create_time datetime DEFAULT NULL COMMENT '创建时间';
alter table t_lc_cust_job MODIFY COLUMN modify_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_custinfo MODIFY COLUMN create_time datetime DEFAULT NULL COMMENT '创建时间';
alter table t_lc_custinfo MODIFY COLUMN modify_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_duncase_assign MODIFY COLUMN create_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_task MODIFY COLUMN close_date datetime DEFAULT NULL COMMENT '结案时间';
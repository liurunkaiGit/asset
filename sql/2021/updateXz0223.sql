alter table t_lc_task add `assist_id` bigint(10) DEFAULT NULL COMMENT '协助人id';
alter table t_lc_task add `assist_id_bf` bigint(10) DEFAULT NULL COMMENT '准协助人id';
alter table t_lc_task add `assist_name` varchar(255)  DEFAULT NULL COMMENT '协助人名字';
alter table t_lc_task add `assist_name_bf` varchar(255)  DEFAULT NULL COMMENT '准协助人名字';
alter table t_lc_task add `assist_type` int(11) DEFAULT 0 COMMENT '协助状态';
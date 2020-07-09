alter table t_lc_cust_job MODIFY COLUMN create_time datetime DEFAULT NULL COMMENT '创建时间';
alter table t_lc_cust_job MODIFY COLUMN modify_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_custinfo MODIFY COLUMN create_time datetime DEFAULT NULL COMMENT '创建时间';
alter table t_lc_custinfo MODIFY COLUMN modify_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_duncase_assign MODIFY COLUMN create_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_task MODIFY COLUMN close_date datetime DEFAULT NULL COMMENT '结案时间';

INSERT INTO `sys_config` (`config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('数据归档天数', 'hisDataMigrateDays', '1', 'N', 'admin', '2020-07-09 16:04:41', 'admin', '2020-07-09 17:00:03', '数据归档天数');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('历史案件综合查询', '2001', '18', '/his/duncase/view', 'menuItem', 'C', '0', 'his:duncase:view', '#', 'admin', '2020-07-09 10:40:19', 'admin', '2020-07-09 10:45:37', '');

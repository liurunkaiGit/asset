ALTER TABLE ext_phone add password varchar(64) DEFAULT NULL COMMENT '自建坐席登录时密码';
ALTER TABLE ext_phone add domain varchar(64) DEFAULT NULL COMMENT '自建企业编号或者域名';

ALTER TABLE t_lc_robot_task_pandect add call_blank_count int(11) DEFAULT NULL COMMENT '空号总数';
ALTER TABLE t_lc_robot_task_pandect add call_closed_count int(11) DEFAULT NULL COMMENT '关机总数';
ALTER TABLE t_lc_robot_task_pandect add call_down_count int(11) DEFAULT NULL COMMENT '停机总数';
ALTER TABLE t_lc_robot_task_pandect add call_black_count int(11) DEFAULT NULL COMMENT '黑名单总数';
ALTER TABLE t_lc_robot_task_pandect add call_fail_count int(11) DEFAULT NULL COMMENT '外呼失败总数';
ALTER TABLE t_lc_robot_task_pandect add call_loss_count int(11) DEFAULT NULL COMMENT '呼损总数';
ALTER TABLE t_lc_robot_task_pandect add call_overdue_count int(11) DEFAULT NULL COMMENT '主叫欠费总数';

ALTER TABLE t_lc_task MODIFY case_no varchar(100) DEFAULT NULL COMMENT '案件编号';

ALTER TABLE t_lc_column_query add table_prefix varchar(50) DEFAULT NULL COMMENT '表简称';

update t_lc_column_query set table_prefix = 't' where table_name='t_lc_duncase';

update t_lc_column_query set table_prefix = 'ta' where table_name='t_lc_duncase' and column_name in('owner_name','call_sign','recently_allot_date','recently_follow_up_date','close_case_yhje','action_code','task_type');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('手动回调', '2135', '1', '#', '', 'F', '0', 'collect:robot:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');


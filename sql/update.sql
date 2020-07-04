-- 角色表role_key添加默认值
alter table sys_role alter column role_key set default 'admin';

-- 修改电催记录表remark改为text
alter table t_lc_call_record modify column remark text COMMENT '备注';

-- 修改表字段时间不勾选根据当前时间戳更新
alter table cur_assets_repayment_package change close_case_date close_case_date datetime DEFAULT NULL COMMENT '结案时间';

alter table t_lc_duncase change recently_allot_date recently_allot_date datetime DEFAULT NULL COMMENT '最近分配日期';

alter table t_lc_duncase change recently_follow_up_date recently_follow_up_date datetime DEFAULT NULL COMMENT '最近跟进时间';

alter table t_lc_import_flow change create_time create_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_robot_call_analyse_result change create_time create_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_robot_call_detail change start_time start_time datetime DEFAULT NULL COMMENT '说话的开始时间';

alter table t_lc_robot_call_detail change end_time end_time datetime DEFAULT NULL COMMENT '说话的结束时间';

alter table t_lc_robot_call_detail change create_time create_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_robot_call_record_mete_data change start_time start_time datetime DEFAULT NULL COMMENT '说话的开始时间';

alter table t_lc_robot_call_record_mete_data change end_time end_time datetime DEFAULT NULL COMMENT '说话的结束时间';

alter table t_lc_robot_call_record_mete_data change create_time create_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_robot_task change call_start_date call_start_date datetime DEFAULT NULL COMMENT '拨打开始时间';

alter table t_lc_robot_task change call_end_date call_end_date datetime DEFAULT NULL COMMENT '任务拨打结束时间';

alter table t_lc_robot_task change create_time create_time datetime DEFAULT NULL COMMENT '创建时间';

alter table t_lc_task change recently_allot_date recently_allot_date datetime DEFAULT NULL COMMENT '最近分配日期';

alter table t_lc_task change recently_follow_up_date recently_follow_up_date datetime DEFAULT NULL COMMENT '最近跟进时间';

alter table t_lc_task change modify_owner_time modify_owner_time datetime DEFAULT NULL COMMENT '修改任务归属人时间：用来记录在催收员名下天数使用';

-- 菜单表初始化数据
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2086', '报表管理', '0', '7', '#', 'menuItem', 'M', '0', '', 'fa fa-calendar', 'admin', '2020-04-07 17:40:25', 'admin', '2020-04-09 16:17:04', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2087', '回收率报表', '2086', '1', '/report/recovery', 'menuItem', 'C', '0', 'report:recovery:view', '#', 'admin', '2020-04-09 16:15:45', '', NULL, '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2088', '案件可联率', '2086', '2', '/report/contact', 'menuItem', 'C', '0', 'report:contact:view', '#', 'admin', '2020-04-10 15:56:36', '', NULL, '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2089', '每日过程指标', '2086', '3', '/report/process', 'menuItem', 'C', '0', 'report:process:view', '#', 'admin', '2020-04-10 15:59:36', '', NULL, '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2092', '查询', '2088', '1', '#', '', 'F', '0', 'report:contact:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '案件可联率报表');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2093', '导出', '2088', '5', '#', '', 'F', '0', 'report:contact:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '案件可联率报表');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2094', '查询', '2089', '1', '#', '', 'F', '0', 'report:process:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '每日过程指标报查询');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2095', '导出', '2089', '5', '#', '', 'F', '0', 'report:process:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '每日过程指标报导出');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2096', '查询', '2087', '1', '#', '', 'F', '0', 'report:recovery:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '回收率报查询');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2097', '导出', '2087', '5', '#', '', 'F', '0', 'report:recovery:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '回收率报导出');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2098', '查询', '2076', '1', '#', '', 'F', '0', 'collect:robot:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2100', '拉回', '2076', '4', '#', '', 'F', '0', 'collect:robot:pullback', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2101', '启动', '2076', '2', '#', '', 'F', '0', 'collect:robot:start', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2102', '暂停', '2076', '3', '#', '', 'F', '0', 'collect:robot:pause', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2103', '停止', '2076', '3', '#', '', 'F', '0', 'collect:robot:stop', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2104', '导出', '2076', '5', '#', '', 'F', '0', 'collect:robot:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
insert into sys_menu( parent_id, menu_name, order_num, url, target, menu_type, visible, perms, create_by, create_time )values( 3, '动态sql', '5', '/assetspackage/dynamicsql', 'menuItem', 'C', '0', 'assetspackage:dynamicsql:view', 'admin', sysdate() )

-- 字典表初始化数据
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('206', '24', '线路盲区', 'R08', 'call_record_code', NULL, NULL, 'Y', '0', 'admin', '2020-04-07 13:53:15', '', NULL, '线路盲区');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('205', '23', '黑名单', 'R07', 'call_record_code', NULL, NULL, 'Y', '0', 'admin', '2020-04-07 13:52:48', '', NULL, '黑名单');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('204', '22', '呼损', 'R06', 'call_record_code', NULL, NULL, 'Y', '0', 'admin', '2020-04-07 13:52:18', '', NULL, '呼损');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('203', '21', '主叫欠费', 'R05', 'call_record_code', NULL, NULL, 'Y', '0', 'admin', '2020-04-07 13:51:57', '', NULL, '主叫欠费');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('202', '20', '停机', 'R04', 'call_record_code', NULL, NULL, 'Y', '0', 'admin', '2020-04-07 13:51:20', '', NULL, '停机');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('201', '19', '主叫号码不可用', 'R03', 'call_record_code', NULL, NULL, 'Y', '0', 'admin', '2020-04-07 13:50:57', '', NULL, '主叫号码不可用');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('200', '18', '无法接通', 'R02', 'call_record_code', NULL, NULL, 'Y', '0', 'admin', '2020-04-07 13:50:32', '', NULL, '无法接通');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('199', '17', '已接听', 'R01', 'call_record_code', '', '', 'Y', '0', 'admin', '2020-04-07 13:49:55', 'admin', '2020-04-07 13:50:10', '已接听');

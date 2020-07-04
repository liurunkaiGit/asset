DROP TABLE IF EXISTS `t_lc_exon_num`;
CREATE TABLE `t_lc_exon_num` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exon_num_group` varchar(50) DEFAULT NULL COMMENT '外显号码组名称',
  `exon_num_count` int(5) DEFAULT NULL COMMENT '外显号码个数',
  `exon_num` varchar(100) DEFAULT NULL COMMENT '外显号码，多个号码；分隔',
  `org_id` int(5) DEFAULT NULL COMMENT '机构编号',
  `org_name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `create_by` bigint(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='外显号码管理表';

DROP TABLE IF EXISTS `t_lc_allocat_case_config`;
CREATE TABLE `t_lc_allocat_case_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `org_id` bigint(20) DEFAULT NULL COMMENT '委托方id',
  `org_name` varchar(50) DEFAULT NULL COMMENT '委托方名称',
  `rule_engine` varchar(50) DEFAULT NULL COMMENT '规则引擎',
  `robot` varchar(50) DEFAULT NULL COMMENT '机器人',
  `call_platform` varchar(50) DEFAULT NULL COMMENT '话务平台',
  `auto_allocat_case` int(1) DEFAULT NULL COMMENT '是否自动分案',
  `allocat_case_startegy` varchar(50) DEFAULT NULL COMMENT '自动分案策略',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `allocat_case_rule` varchar(50) DEFAULT NULL,
  `rule_engine_name` varchar(50) DEFAULT NULL COMMENT '规则引擎名称',
  `robot_name` varchar(50) DEFAULT NULL COMMENT '机器人名称',
  `call_platform_name` varchar(50) DEFAULT NULL COMMENT '话务平台名称',
  `allocat_case_startegy_name` varchar(50) DEFAULT NULL COMMENT '分配策略名称',
  `allocat_case_rule_name` varchar(50) DEFAULT NULL COMMENT '分配规则名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='智能分案配置';

ALTER TABLE ext_phone ADD exon_num_group varchar(25) COMMENT '外显号码组';
ALTER TABLE ext_phone ADD exon_num_group_id BIGINT(5) COMMENT '外显号码组id';
ALTER TABLE ext_phone ADD seat_name varchar(25) COMMENT '绑定坐席名称';
ALTER TABLE ext_phone ADD seat_id BIGINT(5) COMMENT '绑定坐席id';
ALTER TABLE ext_phone ADD org_name varchar(100) COMMENT '机构名称';
ALTER TABLE ext_phone ADD org_id int (5) COMMENT '机构编号';

alter table t_lc_cust_contact modify column create_time datetime;
alter table t_lc_cust_contact modify column modify_time datetime;

ALTER TABLE t_lc_robot_task ADD robot varchar(10) COMMENT '所属机器人：BR、DXM';

ALTER TABLE t_lc_call_record ADD platform varchar(10) COMMENT '所属话务平台：PA、ZJ';

ALTER TABLE t_lc_task ADD hit_rule varchar(10) COMMENT '命中规则';
ALTER TABLE t_lc_task ADD hit_rule_desc varchar(200) COMMENT '命中规则描述';
ALTER TABLE t_lc_task ADD distribution_strategy varchar(10) COMMENT '策略';
ALTER TABLE t_lc_task ADD import_batch_no varchar(20) COMMENT '导入批次号';

ALTER TABLE t_lc_duncase ADD hit_rule varchar(10) COMMENT '命中规则';
ALTER TABLE t_lc_duncase ADD hit_rule_desc varchar(200) COMMENT '命中规则描述';
ALTER TABLE t_lc_duncase ADD distribution_strategy varchar(10) COMMENT '策略';
ALTER TABLE t_lc_duncase ADD import_batch_no varchar(20) COMMENT '导入批次号';
ALTER TABLE t_lc_duncase ADD pack_no varchar(100) COMMENT '分发编号';

INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2106', '外显号码管理', '2001', '8', '/collect/exonNum', 'menuItem', 'C', '0', NULL, '#', 'admin', '2020-04-21 10:13:35', '', NULL, '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2108', '查询', '2106', '1', '#', '', 'F', '0', 'ruoyi:num:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2109', '新增', '2106', '2', '#', '', 'F', '0', 'ruoyi:num:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2110', '修改', '2106', '3', '#', '', 'F', '0', 'ruoyi:num:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2111', '删除', '2106', '4', '#', '', 'F', '0', 'ruoyi:num:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2112', '导出', '2106', '5', '#', '', 'F', '0', 'ruoyi:num:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2113', '智能分案配置', '2001', '20', '/case/config', 'menuItem', 'C', '0', 'ruoyi:config:view', '#', 'admin', '2020-04-23 13:57:46', '', NULL, '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2115', '智能分案配置查询', '2113', '1', '#', '', 'F', '0', 'ruoyi:config:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2116', '智能分案配置新增', '2113', '2', '#', '', 'F', '0', 'ruoyi:config:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2117', '智能分案配置修改', '2113', '3', '#', '', 'F', '0', 'ruoyi:config:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2118', '智能分案配置删除', '2113', '4', '#', '', 'F', '0', 'ruoyi:config:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2119', '智能分案配置导出', '2113', '5', '#', '', 'F', '0', 'ruoyi:config:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2121', '任务查询', '2003', '1', '#', '', 'F', '0', 'collect:task:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2122', '任务新增', '2003', '2', '#', '', 'F', '0', 'collect:task:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2123', '任务修改', '2003', '3', '#', '', 'F', '0', 'collect:task:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2124', '任务删除', '2003', '4', '#', '', 'F', '0', 'collect:task:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2125', '任务导出', '2003', '5', '#', '', 'F', '0', 'collect:task:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2126', '案件查询', '2006', '1', '#', '', 'F', '0', 'collect:duncase:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2127', '案件新增', '2006', '2', '#', '', 'F', '0', 'collect:duncase:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2128', '案件修改', '2006', '3', '#', '', 'F', '0', 'collect:duncase:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2129', '案件删除', '2006', '4', '#', '', 'F', '0', 'collect:duncase:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `asset`.`sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2130', '案件导出', '2006', '5', '#', '', 'F', '0', 'collect:duncase:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');



INSERT INTO `asset`.`sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('116', '规则引擎', 'rule_engine', '0', 'admin', '2020-04-23 11:59:02', '', NULL, '规则引擎');
INSERT INTO `asset`.`sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('117', '机器人', 'robot', '0', 'admin', '2020-04-23 12:00:57', '', NULL, '机器人');
INSERT INTO `asset`.`sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('118', '话务平台', 'call_platform', '0', 'admin', '2020-04-23 12:03:05', '', NULL, '话务平台');
INSERT INTO `asset`.`sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('119', '催收模式', 'collect_model', '0', 'admin', '2020-04-23 12:04:22', '', NULL, '催收模式');

INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('207', '1', '案件自动分配', 'caseAutoDistribution', 'rule_engine', NULL, NULL, 'Y', '0', 'admin', '2020-04-23 12:00:11', '', NULL, '案件自动分配');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('208', '2', '案件自动分配02', 'caseAutoDistribution', 'rule_engine', NULL, NULL, 'Y', '0', 'admin', '2020-04-23 12:00:30', '', NULL, '案件自动分配02');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('209', '1', '百融', 'BR', 'robot', NULL, NULL, 'Y', '0', 'admin', '2020-04-23 12:01:20', '', NULL, '百融');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('210', '2', '度小满', 'DXM', 'robot', NULL, NULL, 'Y', '0', 'admin', '2020-04-23 12:01:38', '', NULL, '度小满');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('211', '1', '平安', 'PA', 'call_platform', NULL, NULL, 'Y', '0', 'admin', '2020-04-23 12:03:22', '', NULL, '平安');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('212', '2', '自建', 'ZJ', 'call_platform', NULL, NULL, 'Y', '0', 'admin', '2020-04-23 12:03:42', '', NULL, '自建');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('213', '1', '全机器人', 'all_robot', 'collect_model', NULL, NULL, 'Y', '0', 'admin', '2020-04-23 12:04:53', '', NULL, '全机器人');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('214', '2', '全人工', 'all_person', 'collect_model', NULL, NULL, 'Y', '0', 'admin', '2020-04-23 12:05:21', '', NULL, '全人工');
INSERT INTO `asset`.`sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('215', '3', '人机结合', 'robot_person', 'collect_model', NULL, NULL, 'Y', '0', 'admin', '2020-04-23 12:05:49', '', NULL, '人机结合');


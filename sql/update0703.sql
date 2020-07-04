-- ----------------------------
-- Table structure for t_lc_robot_black
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_robot_black`;
CREATE TABLE `t_lc_robot_black` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `case_no` varchar(255) DEFAULT NULL COMMENT '案件编号',
  `org_id` int(11) DEFAULT NULL COMMENT '机构编号',
  `org_name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `import_batch_no` varchar(255) DEFAULT NULL COMMENT '导入批次号',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `reason` varchar(255) DEFAULT NULL COMMENT '触发原因',
  `create_by` int(5) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` int(5) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='机器人黑名单管理';

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('黑名单管理', (select t.menu_id from sys_menu t where t.menu_name = '机器人任务管理'), '3', '/robot/black/view', 'menuItem', 'C', '0', 'robot:black:view', '#', 'admin', '2020-06-24 10:10:50', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('查询', (select t.menu_id from sys_menu t where t.menu_name = '黑名单管理'), '1', '#', '', 'F', '0', 'robot:black:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('移除', (select t.menu_id from sys_menu t where t.menu_name = '黑名单管理'), '4', '#', '', 'F', '0', 'robot:black:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('导出', (select t.menu_id from sys_menu t where t.menu_name = '黑名单管理'), '5', '#', '', 'F', '0', 'robot:black:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');

UPDATE t_lc_column_query SET column_name = 'arrears_total', table_prefix = 'ta' WHERE column_name_cn = '委案金额' and table_name='t_lc_duncase';

ALTER TABLE t_lc_report_day_process add `colling_case_money` decimal(10,2) DEFAULT NULL COMMENT '在催案件金额';



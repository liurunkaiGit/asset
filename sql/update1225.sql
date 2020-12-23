INSERT INTO `sys_config` (`config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('批量外呼数据归档天数', 'batchCallHisDataMigrateDays', '0', 'N', 'zhang', '2020-12-14 15:29:21', '', NULL, '批量外呼数据归档天数');

CREATE TABLE `t_lc_batch_call_his` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `batch_no` int(8) DEFAULT NULL COMMENT '批次号',
  `case_no` varchar(255) DEFAULT NULL COMMENT '案件号',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `contact_name` varchar(255) DEFAULT NULL COMMENT '联系人姓名',
  `contact_relation` int(10) DEFAULT NULL COMMENT '与本人关系',
  `exon_num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外显号码',
  `org_id` varchar(30) DEFAULT NULL COMMENT '委托机构ID',
  `import_batch_no` varchar(50) DEFAULT NULL COMMENT '案件导入批次编号',
  `phone_type` varchar(2) DEFAULT NULL COMMENT '电话类型：1 手机；2 固话',
  `task_status` int(10) DEFAULT NULL COMMENT '任务状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `ind` (`create_by`,`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8;

INSERT INTO sys_menu (menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('批量外呼历史查询', (select t.menu_id from sys_menu t where t.menu_name = '批量外呼'), '4', '/batchcall/his/view', 'menuItem', 'C', '0', 'batchcall:his:view', '#', 'zhang', '2020-12-14 16:09:22', '', NULL, '');
INSERT INTO sys_menu (menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('查询', (select t.menu_id from sys_menu t where t.menu_name = '批量外呼历史查询'), '1', '#', 'menuItem', 'F', '0', 'batchcall:his:list', '#', 'admin', '2020-12-14 17:11:28', '', NULL, '');


INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('案件查询评分', (select t.menu_id from sys_menu t where t.menu_name = '案件综合查询'), '1', '#', '', 'F', '0', 'collect:duncase:findScore', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('组内案件查询', (select t.menu_id from sys_menu t where t.menu_name = '催收管理'), '13', '/collect/duncase/group/view', 'menuItem', 'C', '0', 'collect:duncase:group:view', '#', 'zhang', '2020-12-17 11:10:50', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('查询', (select t.menu_id from sys_menu t where t.menu_name = '组内案件查询'), '1', '#', '', 'F', '0', 'collect:duncase:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('组内每日过程指标', (select t.menu_id from sys_menu t where t.menu_name = '报表管理'), '4', '/report/process/group/view', 'menuItem', 'C', '0', 'report:process:group:view', '#', 'admin', '2020-12-17 11:14:35', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('查询', (select t.menu_id from sys_menu t where t.menu_name = '组内每日过程指标'), '1', '#', '', 'F', '0', 'report:process:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '每日过程指标报查询');





INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '1', '#', 'menuItem', 'F', '0', 'call:config:list', '#', 'zhang', '2020-08-17 11:52:54', 'admin', '2020-08-17 11:54:28', '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('新增', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '2', '#', 'menuItem', 'F', '0', 'call:config:add', '#', 'admin', '2020-08-17 11:54:56', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('修改', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '3', '#', 'menuItem', 'F', '0', 'call:config:edit', '#', 'admin', '2020-08-17 11:55:21', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('删除', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '4', '#', 'menuItem', 'F', '0', 'call:config:remove', '#', 'admin', '2020-08-17 11:55:44', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('导出', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫策略配置'), '5', '#', 'menuItem', 'F', '0', 'call:config:export', '#', 'admin', '2020-08-17 11:56:08', '', NULL, '');

CREATE INDEX create_time_index ON t_lc_call_record (create_time);

DROP TABLE IF EXISTS `t_lc_letter`;
CREATE TABLE `t_lc_letter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `case_no` varchar(100) DEFAULT NULL COMMENT '机构案件号',
  `import_batch_no` varchar(100) DEFAULT NULL COMMENT '导入批次号',
  `org_id` int(5) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `tast_id` bigint(20) DEFAULT NULL COMMENT '任务id',
  `custom_name` varchar(100) DEFAULT NULL COMMENT '客户姓名',
  `owner_id` int(5) DEFAULT NULL COMMENT '业务归属人id',
  `owner_name` varchar(255) DEFAULT NULL COMMENT '业务归属人姓名',
  `transfer_type` varchar(255) DEFAULT NULL COMMENT '手别',
  `arrears_total` decimal(10,2) DEFAULT NULL COMMENT '委案金额',
  `close_case_yhje` decimal(10,2) DEFAULT NULL COMMENT '结案应还金额',
  `loan_date` datetime DEFAULT NULL COMMENT '借款日期',
  `letter_type` tinyint(1) DEFAULT NULL COMMENT '信函类型，1：催缴函，2：律师函',
  `apply_status` tinyint(1) DEFAULT NULL COMMENT '审批状态，1：待审批，2：审批通过，3：审批拒绝',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int(5) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` int(5) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '信函';

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('信函管理', (SELECT t.menu_id from sys_menu t where t.menu_name='催收管理'), '1', '/coll/letter', 'C', '0', 'coll:letter:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '信函管理');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', (SELECT t.menu_id from sys_menu t where t.menu_name='信函管理'), '1',  '#',  'F', '0', 'coll:letter:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('审批通过', (SELECT t.menu_id from sys_menu t where t.menu_name='信函管理'), '2',  '#',  'F', '0', 'coll:letter:apply',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('审批拒绝', (SELECT t.menu_id from sys_menu t where t.menu_name='信函管理'), '3',  '#',  'F', '0', 'coll:letter:apply',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', (SELECT t.menu_id from sys_menu t where t.menu_name='信函管理'), '5',  '#',  'F', '0', 'coll:letter:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('手动回调', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫明细管理'), '4', '#', 'menuItem', 'F', '0', 'collect:hand:callback', '#', 'admin', '2020-08-19 14:33:50', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('拉回', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫任务管理'), '3', '#', 'menuItem', 'F', '0', 'robot:pandect:pullback', '#', 'admin', '2020-08-19 14:34:24', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('启动', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫任务管理'), '4', '#', 'menuItem', 'F', '0', 'robot:pandect:start', '#', 'admin', '2020-08-19 14:35:32', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('暂停', (SELECT t.menu_id from sys_menu t where t.menu_name='呼叫任务管理'), '5', '#', 'menuItem', 'F', '0', 'robot:pandect:pause', '#', 'admin', '2020-08-19 14:36:01', '', NULL, '');

ALTER table org_package add is_auto_send_sms tinyint(1) DEFAULT NULL COMMENT '是否自动发送短信，1：是吗，2：否';
ALTER table org_package add sms_template_id varchar(50) DEFAULT NULL COMMENT '短信模板id';
ALTER table org_package add sms_template_name varchar(100) DEFAULT NULL COMMENT '短信模板名称';

ALTER table org_package add is_same_case_deal  tinyint(1) DEFAULT NULL COMMENT '是否共案处理，1：是，2：否';
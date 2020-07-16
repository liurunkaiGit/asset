update t_lc_column_query t set t.column_value= 'PTP:承诺还款|CYH:称已还|TP:谈判沟通|WCY:无诚意|HKKN:还款困难|ZG:转告|WRJT:无人接听|JJ:拒接|ZX:占线忙音|GJ:关机|GD:挂断|KH:空号|BZ:不在|WCR:无此人|WHY:无回应|SL:失联|BPHZG:不配合转告|FBRJT:非本人接听|R01:已接听|R02:无法接通|R03:主叫号码不可用|R04:停机|R05:主叫欠费|R06:呼损|R07:黑名单|R08:线路盲区' where t.column_name = 'call_sign';

-- ----------------------------
-- Table structure for t_lc_send_robot_apply
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_send_robot_apply`;
CREATE TABLE `t_lc_send_robot_apply` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `send_robot_batch_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务名称',
  `org_id` int(5) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `task_num` int(5) DEFAULT NULL COMMENT '任务数量',
  `owner_id` int(5) DEFAULT NULL COMMENT '业务归属人id',
  `owner_name` varchar(255) DEFAULT NULL COMMENT '业务归属人姓名',
  `task_status` int(1) DEFAULT NULL COMMENT '任务状态，0：待审批，1：审批通过，2：审批拒绝',
  `create_by` int(5) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(5) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='推送机器人申请表';

ALTER TABLE t_lc_task ADD send_robot_batch_no varchar(255) DEFAULT NULL COMMENT '推送任务批次号';

ALTER TABLE t_lc_robot_task_pandect add `phone_num` int(11) DEFAULT NULL COMMENT '任务实际可拨打的号码总数';

update t_lc_robot_task_pandect set phone_num = call_total_count;

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('呼叫审核管理',(SELECT t.menu_id from sys_menu t where t.menu_name = '机器人任务管理'), '3', '/robot/approval/view', 'menuItem', 'C', '0', 'robot:approval:view', '#', 'zhang', '2020-07-13 09:53:01', '', NULL, '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', (SELECT t.menu_id from sys_menu t where t.menu_name = '呼叫审核管理'), '1',  '#',  'F', '0', 'robot:approval:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('审批通过', (SELECT t.menu_id from sys_menu t where t.menu_name = '呼叫审核管理'), '2',  '#',  'F', '0', 'robot:approval:apply',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('审批拒绝', (SELECT t.menu_id from sys_menu t where t.menu_name = '呼叫审核管理'), '3',  '#',  'F', '0', 'robot:approval:apply',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', (SELECT t.menu_id from sys_menu t where t.menu_name = '呼叫审核管理'), '5',  '#',  'F', '0', 'robot:approval:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

INSERT INTO `sys_job` (`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('更新机器人呼叫统计', 'DEFAULT', 'robotTaskSchedule.updateRobotPandect', '0 0/10 * * * ?', '1', '0', '1', 'admin', '2020-07-11 09:42:05', 'admin', '2020-07-11 11:03:12', '更新机器人呼叫统计，每隔10分钟统计呼叫中的各个状态条数');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name = '分机号码管理'), '18', '/agent/phone', 'menuItem', 'F', '0', 'agent:phone:list', '#', 'admin', '2020-03-03 09:16:55', 'admin', '2020-04-29 09:14:56', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('导出', (SELECT t.menu_id from sys_menu t where t.menu_name = '分机号码管理'), '18', '/agent/phone', 'menuItem', 'F', '0', 'agent:phone:export', '#', 'admin', '2020-03-03 09:16:55', 'admin', '2020-04-29 09:14:56', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('新增', (SELECT t.menu_id from sys_menu t where t.menu_name = '分机号码管理'), '18', '/agent/phone', 'menuItem', 'F', '0', 'agent:phone:add', '#', 'admin', '2020-03-03 09:16:55', 'admin', '2020-04-29 09:14:56', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('修改', (SELECT t.menu_id from sys_menu t where t.menu_name = '分机号码管理'), '18', '/agent/phone', 'menuItem', 'F', '0', 'agent:phone:edit', '#', 'admin', '2020-03-03 09:16:55', 'admin', '2020-04-29 09:14:56', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('删除', (SELECT t.menu_id from sys_menu t where t.menu_name = '分机号码管理'), '18', '/agent/phone', 'menuItem', 'F', '0', 'agent:phone:remove', '#', 'admin', '2020-03-03 09:16:55', 'admin', '2020-04-29 09:14:56', '');

CREATE TABLE `t_lc_batch_call` (
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8;

CREATE TABLE `t_lc_batch_call_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构ID',
  `batch_call_num` int(11) DEFAULT NULL COMMENT '批量外呼最大案件数',
  `start_time1` varchar(100) DEFAULT NULL COMMENT '可呼叫开始时间1',
  `end_time1` varchar(100) DEFAULT NULL COMMENT '可呼叫结束时间1',
  `start_time2` varchar(100) DEFAULT NULL COMMENT '可呼叫开始时间2',
  `end_time2` varchar(100) DEFAULT NULL COMMENT '可呼叫结束时间2',
  `start_time3` varchar(100) DEFAULT NULL COMMENT '可呼叫开始时间3',
  `end_time3` varchar(100) DEFAULT NULL COMMENT '可呼叫结束时间3',
  `is_call_other` varchar(2) DEFAULT NULL COMMENT '本人接通后是否继续拨打本案其他号码：0 不呼叫，1 呼叫',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varbinary(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

INSERT INTO `asset`.`sys_dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('电话类型', 'phone_type', '0', 'admin', '2020-07-13 10:06:08', 'admin', '2020-07-16 13:52:50', '电话类型');
INSERT INTO `asset`.`sys_dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('批量外呼任务状态', 'batch_call_status', '0', 'admin', '2020-07-13 10:07:14', 'admin', '2020-07-16 13:52:43', '批量外呼任务状态');




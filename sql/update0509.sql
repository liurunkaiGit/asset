-- ----------------------------
-- Table structure for t_lc_robot_task_pandect
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_robot_task_pandect`;
CREATE TABLE `t_lc_robot_task_pandect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `robot_task_id` int(11) DEFAULT NULL COMMENT '机器人任务id',
  `task_name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `speech_craft_name_id` int(11) DEFAULT NULL COMMENT '机器人话术id',
  `speech_craft_name` varchar(50) DEFAULT NULL COMMENT '机器人话术名称',
  `robot_task_status` varchar(50) DEFAULT NULL COMMENT '机器人任务状态',
  `org_id` int(11) DEFAULT NULL COMMENT '机构编号',
  `org_name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `robot` varchar(255) DEFAULT NULL COMMENT '所属机器人',
  `call_total_count` int(11) DEFAULT NULL COMMENT '任务拨打的号码总数',
  `call_done_count` int(11) DEFAULT NULL COMMENT '任务已完成拨打的号码总数',
  `call_called_count` int(11) DEFAULT NULL COMMENT '任务已完成呼通的号码总数',
  `call_rejected_count` int(11) DEFAULT NULL COMMENT '任务呼叫被拒接的号码总数',
  `call_unavailable_count` int(11) DEFAULT NULL COMMENT '任务呼叫无法接通的号码总数',
  `call_from_unavailable_count` int(11) DEFAULT NULL COMMENT '任务主叫号码不可用的号码总',
  `create_by` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `task_start_time` datetime DEFAULT NULL COMMENT '任务开始时间',
  `task_end_time` datetime DEFAULT NULL COMMENT '任务结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='机器人任务总览表';

alter table t_lc_robot_task_pandect add call_busy_count int(11) COMMENT '占线';
alter table t_lc_robot_task_pandect add call_miss_count int(11) COMMENT '失联';

alter table t_lc_robot_task add is_recall int(1) COMMENT '呼叫类型,1：重复呼叫，2：停止呼叫，3：第二天呼叫';
alter table t_lc_robot_task add continue_days int(5) COMMENT '连续呼叫天数';
alter table t_lc_robot_task add continue_frequency int(5) COMMENT '当天连续呼叫次数';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('呼叫任务管理', '3', '1', '/robot/pandect', 'C', '0', 'robot:pandect:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '呼叫任务管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', @parentId, '1',  '#',  'F', '0', 'robot:pandect:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', @parentId, '5',  '#',  'F', '0', 'robot:pandect:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('呼叫明细查询', '2076', '1', '/collect/robot/view', 'menuItem', 'C', '0', 'collect:robot:view', '#', 'admin', '2020-05-09 20:58:13', '', NULL, '');
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('呼叫任务管理', '2076', '2', '/robot/pandect/view', 'menuItem', 'C', '0', 'robot:pandect:view', '#', 'admin', '2020-05-09 20:59:59', '', NULL, '');

INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('机器人呼叫配置', 'robot_call_config', '0', 'admin', '2020-05-06 17:55:31', 'admin', '2020-05-06 17:59:05', '机器人呼叫配置');

INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('1', '08:00', 'robot_help_collect_call_start_time', 'robot_call_config', NULL, NULL, 'Y', '0', 'admin', '2020-05-06 17:58:00', '', NULL, '机器人协催呼叫开始时间');
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2', '3', 'task_call_num', 'robot_call_config', '', '', 'Y', '0', 'admin', '2020-05-06 18:00:58', 'admin', '2020-05-06 18:01:14', '任务中呼叫数量配置');
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('3', '1', 'robot_num', 'robot_call_config', NULL, NULL, 'Y', '0', 'admin', '2020-05-11 11:19:29', '', NULL, '机器人（ai）坐席数');


CREATE TABLE `t_lc_collectionrecordimprot_temp` (
  `id` varchar(64) NOT NULL,
  `org_caseNo` varchar(64) DEFAULT NULL COMMENT '机构案件号',
  `certificate_no` varchar(30) DEFAULT NULL COMMENT '身份证',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号',
  `relation` varchar(128) DEFAULT NULL COMMENT '关系',
  `phone_code` varchar(100) DEFAULT NULL COMMENT '电话码',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `seat` varchar(100) DEFAULT NULL COMMENT '坐席',
  `call_start_time` datetime DEFAULT NULL COMMENT '通话开始时间',
  `call_end_time` datetime DEFAULT NULL COMMENT '通话结束时间',
  `call_length` varchar(20) DEFAULT NULL COMMENT '通话时长',
  `call_record_id` varchar(128) DEFAULT NULL COMMENT '通话录音id',
  `grade` varchar(100) DEFAULT NULL COMMENT '客户意向等级',
  `name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `call_status` varchar(64) DEFAULT NULL COMMENT '通话状态',
  `org_id` varchar(64) DEFAULT NULL,
  `import_batch_no` varchar(20) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_exception` char(1) DEFAULT '0' COMMENT '异常标识（0新增，1异常，2案件不存在）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_lc_collectionrecordimprot_flow` (
  `id` varchar(64) NOT NULL,
  `import_batch_no` varchar(32) DEFAULT NULL,
  `org_id` varchar(64) DEFAULT NULL,
  `org_name` varchar(64) DEFAULT NULL,
  `total` varchar(32) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sys_menu( parent_id, menu_name, order_num, url, target, menu_type, visible, perms, create_by, create_time )
values( 2001, '催收记录导入', '20', '/assetspackage/CollectionRecordImprot', 'menuItem', 'C', '0', 'assetspackage:CollectionRecordImprot:view', 'admin', sysdate() );

CREATE TABLE `cur_assets_repayment_package_temp` (
  `id` varchar(64) NOT NULL,
  `org_casNo` varchar(64) NOT NULL COMMENT '机构案件号',
  `jyqtfy` varchar(20) DEFAULT NULL COMMENT '交易其他费用',
  `jylx` varchar(20) DEFAULT NULL COMMENT '交易利息',
  `jybj` varchar(20) DEFAULT NULL COMMENT '交易本金',
  `jyznf` varchar(20) DEFAULT NULL COMMENT '交易滞纳费',
  `jy_type` varchar(64) DEFAULT NULL COMMENT '交易类型',
  `jyje` varchar(20) DEFAULT NULL COMMENT '交易金额',
  `product_type` varchar(32) DEFAULT NULL COMMENT '产品类型',
  `jjh` varchar(32) DEFAULT NULL COMMENT '借据号',
  `csr` varchar(32) DEFAULT NULL COMMENT '催收人',
  `csjd` varchar(64) DEFAULT NULL COMMENT '催收节点',
  `fprq` varchar(64) DEFAULT NULL COMMENT '分配日期',
  `area_center` varchar(128) DEFAULT NULL COMMENT '区域中心',
  `accept_city` varchar(20) DEFAULT NULL COMMENT '受理城市',
  `hth` varchar(32) DEFAULT NULL COMMENT '合同号',
  `dqsyb_yj` varchar(64) DEFAULT NULL COMMENT '地区事业部(一级)',
  `dqsyb_ej` varchar(64) DEFAULT NULL COMMENT '地区事业部(二级)',
  `wbqs` varchar(10) DEFAULT NULL COMMENT '外包期数',
  `wbjb` varchar(64) DEFAULT NULL COMMENT '外包经办',
  `warq` varchar(64) DEFAULT NULL COMMENT '委案日期',
  `cur_name` varchar(64) DEFAULT NULL COMMENT '客户名称',
  `khjlxm` varchar(32) DEFAULT NULL COMMENT '客户经理姓名',
  `sjrq` varchar(64) DEFAULT NULL COMMENT '数据日期',
  `sfwbcs` varchar(32) DEFAULT NULL COMMENT '是否外包催收',
  `sfjq` varchar(32) DEFAULT NULL COMMENT '是否结清',
  `bywa` varchar(64) DEFAULT NULL COMMENT '本月委案',
  `ajhsrq` varchar(64) DEFAULT NULL COMMENT '案件回收日期',
  `xfjrzh` varchar(32) DEFAULT NULL COMMENT '消费金融账号',
  `tzsx` varchar(64) DEFAULT NULL COMMENT '调整事项',
  `tzje` varchar(20) DEFAULT NULL COMMENT '调整金额',
  `zhzt` varchar(32) DEFAULT NULL COMMENT '账户状态',
  `hkrq` varchar(64) DEFAULT NULL COMMENT '还款日期',
  `hksyqqs` varchar(64) DEFAULT NULL COMMENT '还款时逾期期数',
  `hkje` varchar(20) DEFAULT NULL COMMENT '还款金额',
  `yqcplx` varchar(64) DEFAULT NULL COMMENT '逾期产品类型',
  `yqjd` varchar(64) DEFAULT NULL COMMENT '逾期阶段',
  `quota_product` varchar(64) DEFAULT NULL COMMENT '额度产品',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `jazt` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '结案状态0：未结案 1：已结案',
  `create_by` varchar(32) DEFAULT NULL COMMENT '导入人',
  `create_time` datetime DEFAULT NULL COMMENT '导入时间',
  `is_exit_collect` int(1) DEFAULT NULL COMMENT '是否出催1：是 2：否',
  `import_batch_no` varchar(20) DEFAULT NULL COMMENT '导入批次号，年月日时分秒生成',
  `close_case_date` datetime DEFAULT NULL COMMENT '结案时间',
  `org_name` varchar(64) DEFAULT NULL,
  `is_exception` char(1) DEFAULT '0' COMMENT '是否异常（0修改，1异常，2不存在，3已结案）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table cur_assets_repayment_package add column org_name varchar(64) DEFAULT NULL COMMENT '机构名称';


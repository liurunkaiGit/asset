-- 字典
INSERT INTO `sys_dict_type` VALUES ('144', '产品性质', 'product_nature', '0', 'admin', '2020-10-14 09:59:29', '', null, null);
INSERT INTO `sys_dict_type` VALUES ('145', '甲方类型', 'type_of_party', '0', 'admin', '2020-10-14 10:09:02', '', null, null);
INSERT INTO `sys_dict_type` VALUES ('146', '结费方式', 'settlement_method', '0', 'admin', '2020-10-14 10:47:29', '', null, null);
INSERT INTO `sys_dict_type` VALUES ('147', '作业系统', 'operating_system', '0', 'admin', '2020-10-14 10:48:43', '', null, null);

INSERT INTO `sys_dict_data` VALUES ('341', '1', '信用卡', '1', 'product_nature', null, null, 'Y', '0', 'admin', '2020-10-14 10:00:36', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('342', '2', '消费贷', '2', 'product_nature', null, null, 'Y', '0', 'admin', '2020-10-14 10:01:06', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('343', '1', '银行', '1', 'type_of_party', null, null, 'Y', '0', 'admin', '2020-10-14 10:09:27', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('344', '2', '消金', '2', 'type_of_party', null, null, 'Y', '0', 'admin', '2020-10-14 10:29:53', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('345', '3', '小贷', '3', 'type_of_party', null, null, 'Y', '0', 'admin', '2020-10-14 10:46:18', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('346', '4', '担保', '4', 'type_of_party', null, null, 'Y', '0', 'admin', '2020-10-14 10:46:29', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('347', '5', '其它', '5', 'type_of_party', null, null, 'Y', '0', 'admin', '2020-10-14 10:46:41', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('348', '1', '包人头', '1', 'settlement_method', null, null, 'Y', '0', 'admin', '2020-10-14 10:47:50', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('349', '2', '佣金制', '2', 'settlement_method', null, null, 'Y', '0', 'admin', '2020-10-14 10:48:06', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('350', '1', '甲方系统', '1', 'operating_system', null, null, 'Y', '0', 'admin', '2020-10-14 10:49:00', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('351', '2', '掘金系统', '2', 'operating_system', null, null, 'Y', '0', 'admin', '2020-10-14 10:49:11', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('352', '3', '智催系统', '3', 'operating_system', null, null, 'Y', '0', 'admin', '2020-10-14 10:49:28', '', null, null);


CREATE TABLE `t_lp_attendance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pro_id` bigint(20) DEFAULT NULL COMMENT '项目表id',
  `pro_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目名称',
  `attendance_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出勤日期',
  `attendance_required` int(7) NOT NULL COMMENT '应出勤人数',
  `actual_attendance` int(7) NOT NULL COMMENT '实际出勤人数',
  `number_of_departures` int(7) NOT NULL COMMENT '申请离职人数',
  `number_of_recruiters` int(7) NOT NULL COMMENT '待招聘上线人数',
  `new_number_of_recruiters` int(7) NOT NULL COMMENT '新人上线人数',
  `number_types` int(7) NOT NULL COMMENT '休假旷工请假',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xmdd` (`pro_id`,`attendance_date`),
  KEY `ad` (`attendance_date`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `t_lp_information_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信息中心ID',
  `center_name` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '中心名称',
  `center_address` varchar(100) NOT NULL COMMENT '中心地址',
  `agent_num` int(11) NOT NULL COMMENT '坐席数量',
  `work_seat_num` int(11) NOT NULL COMMENT '工位数量',
  `trainning_room` varchar(100) DEFAULT NULL COMMENT '培训室',
  `rest_area` varchar(100) DEFAULT NULL COMMENT '休息区',
  `leader` varchar(50) NOT NULL COMMENT '负责人',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `zs` (`center_name`,`leader`,`update_time`) USING BTREE,
  KEY `ld` (`leader`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

CREATE TABLE `t_lp_monthly_target` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `particular_year` int(5) NOT NULL COMMENT '年份',
  `particular_month` int(3) NOT NULL COMMENT '月份',
  `pro_id` bigint(20) NOT NULL COMMENT '项目表id',
  `pro_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目名称',
  `allocation_of_seats` int(9) DEFAULT NULL COMMENT '分配席位',
  `commission_batch` int(9) NOT NULL COMMENT '委案批次',
  `number_of_commission` int(9) NOT NULL COMMENT '委案件数',
  `commission_amount` decimal(15,2) NOT NULL COMMENT '委案金额',
  `amount_received` decimal(15,2) DEFAULT NULL COMMENT '目标回款金额',
  `amount_created` decimal(15,2) NOT NULL COMMENT '创佣金额',
  `inflowrate_of_lastmonth` varchar(20) DEFAULT NULL COMMENT '上月流入率',
  `inflowrate_of_month` varchar(20) DEFAULT NULL COMMENT '本月目标流入率',
  `target_ranking` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '目标排名',
  `remark` varchar(255) DEFAULT NULL,
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ymp` (`particular_year`,`particular_month`,`pro_id`),
  KEY `xm` (`particular_year`,`pro_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

CREATE TABLE `t_lp_project_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `names` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `org_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构编码',
  `org_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL COMMENT '服务部门id',
  `dept_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '服务部门名称',
  `project_manager` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目经理',
  `project_director` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目主管',
  `seats_number` int(11) NOT NULL DEFAULT '0' COMMENT '坐席数量',
  `party_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '甲方名称',
  `party_type` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '甲方类型',
  `billing_information` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开票信息',
  `product_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `product_nature` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品性质',
  `settlement_method` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结费方式',
  `division_logic` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分案逻辑',
  `withdraw_case` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '撤案',
  `rates` varchar(20) NOT NULL COMMENT '费率',
  `billing_formula` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计费公式',
  `operating_system` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业系统',
  `traffic_platform` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '话务平台',
  `cases_number` int(11) NOT NULL DEFAULT '0' COMMENT '人均委案量',
  `per_household` decimal(13,2) DEFAULT '0.00' COMMENT '户均金额',
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '项目开始时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '项目结束时间',
  `remark` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT NULL,
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `belonging_center_id` int(11) NOT NULL COMMENT '归属中心id',
  `belonging_center_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '归属中心名字',
  `account_age` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账龄',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xmwt` (`names`,`org_code`) USING BTREE,
  KEY `jl` (`names`,`project_manager`,`belonging_center_id`) USING BTREE,
  KEY `mr` (`project_manager`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- 菜单 一级
INSERT INTO `sys_menu` VALUES ('2346', '共享管理', '0', '8', '#', 'menuItem', 'M', '0', '', 'fa fa-newspaper-o', 'admin', '2020-10-12 11:42:38', 'admin', '2020-10-20 10:46:33', '');

INSERT INTO `sys_menu` VALUES ('2350', '日报管理', '2346', '3', '#', 'menuItem', 'C', '0', '', '#', 'admin', '2020-10-13 17:20:20', 'admin', '2020-10-20 10:47:18', '');
INSERT INTO `sys_menu` VALUES ('2351', '出勤信息管理', '2350', '1', '/shareproject/attendance', 'menuItem', 'C', '0', 'shareproject:attendance:view', '#', 'admin', '2020-10-13 17:20:49', 'admin', '2020-10-15 17:47:08', '');
INSERT INTO `sys_menu` VALUES ('2352', '查询', '2351', '1', '#', 'menuItem', 'F', '0', 'shareproject:attendance:list', '#', 'admin', '2020-10-13 17:25:01', 'admin', '2020-10-15 17:47:38', '');
INSERT INTO `sys_menu` VALUES ('2353', '修改', '2351', '3', '#', 'menuItem', 'F', '0', 'shareproject:attendance:edit', '#', 'admin', '2020-10-13 17:25:37', 'admin', '2020-10-15 17:48:15', '');
INSERT INTO `sys_menu` VALUES ('2354', '新增', '2351', '2', '#', 'menuItem', 'F', '0', 'shareproject:attendance:add', '#', 'admin', '2020-10-13 17:32:18', 'admin', '2020-10-15 17:47:58', '');
INSERT INTO `sys_menu` VALUES ('2355', '导出', '2351', '4', '#', 'menuItem', 'F', '0', 'shareproject:attendance:export', '#', 'admin', '2020-10-13 17:32:59', 'admin', '2020-10-15 17:49:16', '');
INSERT INTO `sys_menu` VALUES ('2376', '删除', '2351', '5', '#', 'menuItem', 'F', '0', 'shareproject:attendance:remove', '#', 'admin', '2020-10-15 17:49:54', '', null, '');


INSERT INTO `sys_menu` VALUES ('2356', '指标信息管理', '2346', '2', '#', 'menuItem', 'M', '0', '', '#', 'admin', '2020-10-15 14:29:25', 'admin', '2020-10-20 10:47:04', '');
INSERT INTO `sys_menu` VALUES ('2357', '月度目标', '2356', '1', '/shareproject/monthlytarget', 'menuItem', 'C', '0', 'shareproject:monthlytarget:view', '#', 'admin', '2020-10-15 14:30:36', 'admin', '2020-10-20 11:48:31', '');
INSERT INTO `sys_menu` VALUES ('2358', '查询', '2357', '1', '#', 'menuItem', 'F', '0', 'shareproject:monthlytarget:list', '#', 'admin', '2020-10-15 14:34:23', '', null, '');
INSERT INTO `sys_menu` VALUES ('2360', '新增', '2357', '2', '#', 'menuItem', 'F', '0', 'shareproject:monthlytarget:add', '#', 'admin', '2020-10-15 18:23:28', '', null, '');
INSERT INTO `sys_menu` VALUES ('2361', '修改', '2357', '3', '#', 'menuItem', 'F', '0', 'shareproject:monthlytarget:edit', '#', 'admin', '2020-10-15 18:23:51', '', null, '');
INSERT INTO `sys_menu` VALUES ('2362', '删除', '2357', '4', '#', 'menuItem', 'F', '0', 'shareproject:monthlytarget:remove', '#', 'admin', '2020-10-15 18:24:20', '', null, '');


INSERT INTO `sys_menu` VALUES ('2363', '基础信息管理', '2346', '1', '#', 'menuItem', 'M', '0', null, '#', 'admin', '2020-10-12 11:43:08', '', null, '');

INSERT INTO `sys_menu` VALUES ('2364', '中心信息', '2363', '1', '/information/center', 'menuItem', 'C', '0', 'information:center:view', '#', 'admin', '2020-10-12 12:05:08', 'admin', '2020-10-20 10:48:56', '');
INSERT INTO `sys_menu` VALUES ('2365', '查询', '2364', '1', '#', 'menuItem', 'F', '0', 'information:center:list', '#', 'admin', '2020-10-12 12:07:38', 'admin', '2020-10-12 15:25:04', '');
INSERT INTO `sys_menu` VALUES ('2366', '新增', '2364', '2', '#', 'menuItem', 'F', '0', 'information:center:add', '#', 'admin', '2020-10-12 12:08:34', 'admin', '2020-10-12 15:25:22', '');
INSERT INTO `sys_menu` VALUES ('2367', '修改', '2364', '3', '#', 'menuItem', 'F', '0', 'information:center:edit', '#', 'admin', '2020-10-12 12:09:40', 'admin', '2020-10-12 15:25:33', '');
INSERT INTO `sys_menu` VALUES ('2368', '删除', '2364', '4', '#', 'menuItem', 'F', '0', 'information:remove', '#', 'admin', '2020-10-12 12:11:11', 'admin', '2020-10-12 15:25:46', '');
INSERT INTO `sys_menu` VALUES ('2369', '导出', '2364', '5', '#', 'menuItem', 'F', '0', 'information:center:export', '#', 'admin', '2020-10-12 12:12:33', 'admin', '2020-10-12 15:26:12', '');

INSERT INTO `sys_menu` VALUES ('2370', '项目信息', '2363', '2', 'shareproject/projectinformation', 'menuItem', 'C', '0', '', '#', 'admin', '2020-10-12 15:04:51', 'admin', '2020-10-20 10:49:08', '');
INSERT INTO `sys_menu` VALUES ('2371', '项目信息管理-查询', '2370', '1', '#', 'menuItem', 'F', '0', 'shareproject:projectinformation:list', '#', 'admin', '2020-10-12 15:07:54', 'admin', '2020-10-15 18:26:58', '');
INSERT INTO `sys_menu` VALUES ('2372', '项目信息管理-新增', '2370', '2', '#', 'menuItem', 'F', '0', 'shareproject:projectinformation:add', '#', 'admin', '2020-10-15 17:42:58', 'admin', '2020-10-20 10:58:07', '');
INSERT INTO `sys_menu` VALUES ('2373', '项目信息管理-修改', '2370', '3', '#', 'menuItem', 'F', '0', 'shareproject:projectinformation:edit', '#', 'admin', '2020-10-15 17:43:54', '', null, '');
INSERT INTO `sys_menu` VALUES ('2374', '项目信息管理-删除', '2370', '4', '#', 'menuItem', 'F', '0', 'shareproject:projectinformation:remove', '#', 'admin', '2020-10-15 17:44:20', '', null, '');
INSERT INTO `sys_menu` VALUES ('2375', '项目信息管理-导出', '2370', '5', '#', 'menuItem', 'F', '0', 'shareproject:projectinformation:export', '#', 'admin', '2020-10-15 17:44:54', '', null, '');


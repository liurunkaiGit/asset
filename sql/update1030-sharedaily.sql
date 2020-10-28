-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目日报', (select t.menu_id from sys_menu t where t.menu_name = '日报管理'), '2', '/shareproject/daily', 'C', '0', 'shareproject:daily:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '【项目日报】菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【项目日报】查询', @parentId, '1',  '#',  'F', '0', 'shareproject:daily:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【项目日报】新增', @parentId, '2',  '#',  'F', '0', 'shareproject:daily:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【项目日报】修改', @parentId, '3',  '#',  'F', '0', 'shareproject:daily:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【项目日报】删除', @parentId, '4',  '#',  'F', '0', 'shareproject:daily:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【项目日报】导出', @parentId, '5',  '#',  'F', '0', 'shareproject:daily:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

INSERT INTO sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('查询指标', @parentId, '6', '#', 'F', '0', 'shareproject:daily:zhibiao', '#', 'admin', '2020-10-26 16:21:07', 'ry', '2018-03-01', '');

INSERT INTO sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('查询出勤信息是否存在', @parentId, '7', '#',  'F', '0', 'shareproject:attendance:lookOne', '#', 'admin', '2020-10-26 16:21:57', 'ry', '2018-03-01', '');

CREATE TABLE `t_lp_attendance_daily` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '主键',
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
  `daily_id` bigint(20) NOT NULL COMMENT '日报id',
  `daily_name` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_lp_monthly_target_daily` (
  `id` bigint(20) NOT NULL DEFAULT '0',
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
  `daily_id` bigint(20) NOT NULL COMMENT '日报id',
  `daily_name` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_lp_process_daily` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '主键id',
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `org_id` int(11) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `total_call_num` int(11) DEFAULT NULL COMMENT '拨打量(次)',
  `ave_call_num` decimal(10,0) DEFAULT NULL COMMENT '人均拨打量',
  `total_called_num` int(11) DEFAULT NULL COMMENT '接通量(次)',
  `avg_called_num` decimal(10,0) DEFAULT NULL COMMENT '人均接通次数',
  `total_call_len` decimal(10,0) DEFAULT NULL COMMENT '通话时长',
  `avg_call_len` decimal(10,0) DEFAULT NULL COMMENT '人均通话时长',
  `total_called_rate` varchar(100) DEFAULT NULL COMMENT '接通率',
  `avg_called_rate` varchar(100) DEFAULT NULL COMMENT '人均接通率',
  `ext_phone_sign` varchar(255) DEFAULT NULL COMMENT '外显号码标记情况',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `daily_id` bigint(20) NOT NULL COMMENT '日报id',
  `daily_name` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_lp_project_daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attendance_today` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '今日出勤',
  `index_today` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '今日指标',
  `daily_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '日报时间',
  `customer_update` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户最新通知',
  `other_project` varchar(600) DEFAULT NULL COMMENT '项目其它事项',
  `remarks` varchar(600) DEFAULT NULL,
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  `names_attendance` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '日报名称',
  `url__attendance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详情连接',
  `pro_id` bigint(20) NOT NULL COMMENT '项目表id',
  `pro_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目名称',
  `daily_name` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idm` (`pro_id`,`daily_time`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

CREATE TABLE `t_lp_project_information_daily` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '主键',
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
  `per_household` decimal(15,2) DEFAULT '0.00' COMMENT '户均金额',
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
  `daily_id` bigint(20) NOT NULL COMMENT '日报id',
  `daily_name` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_lp_result_daily` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '主键id',
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `org_id` int(11) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `transfer_type` varchar(255) DEFAULT NULL COMMENT '账龄',
  `import_batch_no` varchar(255) DEFAULT NULL COMMENT '委案批次',
  `commission_proportion` varchar(255) DEFAULT NULL COMMENT '佣金比例',
  `total_recycle` decimal(10,0) DEFAULT NULL COMMENT '累计回收',
  `predict_commission` decimal(10,0) DEFAULT NULL COMMENT '预计佣金',
  `recycle_rate` varchar(255) DEFAULT NULL COMMENT '回收率',
  `target_rank` int(2) DEFAULT NULL COMMENT '目标排名',
  `create_by` int(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `daily_id` bigint(20) NOT NULL COMMENT '日报id',
  `daily_name` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

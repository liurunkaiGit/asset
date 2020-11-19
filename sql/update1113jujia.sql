update t_lc_inforeporting_set set from_column='jjh' where from_column='email';
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('预警规则', (select t.menu_id from sys_menu t where t.menu_name = '系统监控'), '1', '/shareproject/hmrule', 'C', '0', 'shareproject:rule:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '【预警规则】菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【预警规则】查询', @parentId, '1',  '#',  'F', '0', 'shareproject:rule:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【预警规则】新增', @parentId, '2',  '#',  'F', '0', 'shareproject:rule:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【预警规则】修改', @parentId, '3',  '#',  'F', '0', 'shareproject:rule:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【预警规则】删除', @parentId, '4',  '#',  'F', '0', 'shareproject:rule:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ( '【预警规则具体规则】查询', @parentId, '5', '#',  'F', '0', 'shareproject:details:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2020-11-03 09:20:17', '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ( '【预警规则具体规则】新增', @parentId, '6', '#',  'F', '0', 'shareproject:details:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2020-11-03 09:20:30', '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ( '【预警规则具体规则】修改', @parentId, '7', '#',  'F', '0', 'shareproject:details:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2020-11-03 09:20:43', '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ( '【预警规则具体规则】删除', @parentId, '8', '#',  'F', '0', 'shareproject:details:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2020-11-03 09:20:55', '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ( '预警规则列表', @parentId, '9', '#',  'F', '0', 'shareproject:details:view', '#', 'admin', '2020-11-03 09:21:34', '', null, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ( '选择用户', @parentId, '10', '#',  'F', '0', 'system:user:sel', '#', 'admin', '2020-11-04 09:17:48', '', null, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ( '选择用户-用户列表', @parentId, '11', '#',  'F', '0', 'system:user:listsel', '#', 'ry', '2020-11-04 09:24:38', '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工状态', (select t.menu_id from sys_menu t where t.menu_name = '系统监控'), '2', '/shareproject/userlogs', 'C', '0', 'shareproject:userlogs:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '【员工状态】菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【员工状态】查询', @parentId, '1',  '#',  'F', '0', 'shareproject:userlogs:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【员工状态】新增', @parentId, '2',  '#',  'F', '0', 'shareproject:userlogs:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【员工状态】修改', @parentId, '3',  '#',  'F', '0', 'shareproject:userlogs:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('【员工状态】删除', @parentId, '4',  '#',  'F', '0', 'shareproject:userlogs:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

INSERT INTO `sys_dict_type`  (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('规则状态', 'rule_status', '0', 'admin', '2020-11-02 09:32:44', 'zhang', '2020-11-02 10:30:02', '');
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('1', '草稿', '1', 'rule_status', null, null, 'Y', '0', 'admin', '2020-11-02 09:33:24', '', null, null);
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('2', '有效', '2', 'rule_status', null, null, 'Y', '0', 'admin', '2020-11-02 09:34:03', '', null, null);
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('3', '失效', '23', 'rule_status', null, null, 'Y', '0', 'admin', '2020-11-02 09:34:16', '', null, null);

INSERT INTO `sys_dict_type`  (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('在岗状态', 'onthejob_tatus', '0', 'admin', '2020-11-02 09:34:38', '', null, null);
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('1', '居家', '1', 'onthejob_tatus', null, null, 'Y', '0', 'admin', '2020-11-02 09:35:01', '', null, null);
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('2', '职场', '2', 'onthejob_tatus', null, null, 'Y', '0', 'admin', '2020-11-02 09:35:12', '', null, null);

INSERT INTO `sys_dict_type`  (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('规则种类', 'rule_type', '0', 'admin', '2020-11-02 09:36:09', 'zhang', '2020-11-02 10:29:54', '');
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('1', '部门', '1', 'rule_type', null, null, 'Y', '0', 'admin', '2020-11-02 09:36:22', '', null, null);
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('2', '人员', '2', 'rule_type', null, null, 'Y', '0', 'admin', '2020-11-02 09:36:31', '', null, null);

INSERT INTO sys_job( job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time )
VALUES ('居家运行-预警规则', 'DEFAULT', 'earlyWarningTask.findEwInfo', '0 */10 * * * ?', '1', '1', '1', 'admin', '2020-11-04 14:07:16');

DROP TABLE IF EXISTS `t_lj_rule`;
CREATE TABLE `t_lj_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_code` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规则编码',
  `rule_status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则状态',
  `onthejob_status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '在岗状态',
  `rule_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规则种类0=部门 1=人员',
  `rule_name` varchar(60) DEFAULT NULL COMMENT '规则名称',
  `start_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '规则有效开始时间',
  `end_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '规则有效结束时间',
  `remark` varchar(150) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_lj_rule_details`;
CREATE TABLE `t_lj_rule_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_id` bigint(20) NOT NULL COMMENT '规则主键',
  `start_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开始',
  `start_time_hm` bigint(20) DEFAULT NULL COMMENT '开始时间毫秒数',
  `end_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结束时间',
  `end_time_hm` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `online_time` varchar(10) DEFAULT NULL COMMENT '在线时长0=不选择1=选择',
  `online_condition` varchar(150) DEFAULT NULL,
  `online_one` int(9) DEFAULT NULL COMMENT '在线时长-条件1',
  `online_two` int(9) DEFAULT NULL COMMENT '在线时长-条件2',
  `out_time` varchar(10) DEFAULT NULL COMMENT '退出次数',
  `out_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '退出条件',
  `out_one` int(9) DEFAULT NULL COMMENT '退出条件1',
  `out_two` int(9) DEFAULT NULL COMMENT '退出条件2',
  `intervals` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '时间间隔',
  `intervals_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '间隔条件',
  `intervals_one` int(9) DEFAULT NULL COMMENT '间隔条件1',
  `intervals_two` int(9) DEFAULT NULL COMMENT '间隔条件2',
  `conversation_time` varchar(10) DEFAULT NULL COMMENT '通话时长',
  `conversation_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通话时长条件',
  `conversation_one` int(9) DEFAULT NULL COMMENT '通过条件1',
  `conversation_two` int(9) DEFAULT NULL COMMENT '通话条件2',
  `conversation_cishu` varchar(10) DEFAULT NULL COMMENT '通话次数',
  `conversation_cishu_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通话次数条件',
  `conversation_cishu_one` int(9) DEFAULT NULL COMMENT '通话次数1',
  `conversation_cishu_two` int(9) DEFAULT NULL COMMENT '通话次数2',
  `eng_rate` varchar(10) DEFAULT NULL COMMENT '接通率',
  `eng_rate_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接通条件',
  `eng_rate_one` int(9) DEFAULT NULL COMMENT '接通率条件1',
  `eng_rate_two` int(9) DEFAULT NULL COMMENT '接通率条件2',
  `case_numbers` varchar(10) DEFAULT NULL COMMENT '案件数量',
  `case_numbers_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '案件处理量条件',
  `case_numbers_one` int(9) DEFAULT NULL COMMENT '案件数量条件1',
  `case_numbers_two` int(9) DEFAULT NULL COMMENT '案件数量条件2',
  `case_rate` varchar(10) DEFAULT NULL COMMENT '案件处理率',
  `case_rate_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '案件处理率条件',
  `case_rate_one` decimal(13,2) DEFAULT NULL COMMENT '案件处理率条件1',
  `case_rate_two` decimal(13,2) DEFAULT NULL COMMENT '案件处理率条件2',
  `sj_repayment` varchar(10) DEFAULT NULL COMMENT '实际还款',
  `sj_repayment_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实际还款条件',
  `sj_repayment_one` decimal(15,2) DEFAULT NULL COMMENT '实际还款条件1',
  `sj_repayment_two` decimal(15,2) DEFAULT NULL COMMENT '实际还款条件2',
  `sj_repayment_rate` varchar(10) DEFAULT NULL COMMENT '实际还款率',
  `sj_repayment_rate_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实际还款率条件',
  `sj_repayment_rate_one` decimal(13,2) DEFAULT NULL COMMENT '实际还款率条件1',
  `sj_repayment_rate_two` decimal(13,2) DEFAULT NULL COMMENT '实际还款率条件2',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_lj_rule_range`;
CREATE TABLE `t_lj_rule_range` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_id` bigint(20) NOT NULL COMMENT '规则主键',
  `dporus_id` varchar(20) NOT NULL,
  `types` varchar(20) DEFAULT NULL COMMENT '0=部门 1=人员',
  `names` varchar(30) DEFAULT NULL,
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`),
  KEY `r` (`rule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_lj_rule_user_logs`;
CREATE TABLE `t_lj_rule_user_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录id',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `rule_id` bigint(20) DEFAULT NULL COMMENT '规则id',
  `rule_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主规则名称',
  `details_id` bigint(20) NOT NULL,
  `start_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开始时间段',
  `end_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '结束时间段',
  `errors` int(11) DEFAULT NULL COMMENT '异常数',
  `days` date NOT NULL COMMENT '时间-天',
  `online_error` int(9) DEFAULT NULL COMMENT '在线时长异常',
  `online_time` decimal(15,2) DEFAULT NULL COMMENT '在线时长',
  `out_error` int(9) DEFAULT NULL,
  `out_cishu` int(9) DEFAULT NULL COMMENT '退出次数',
  `jiange_error` int(9) DEFAULT NULL COMMENT '间隔时长',
  `jiange` decimal(13,2) DEFAULT NULL,
  `tonghua_error` int(9) DEFAULT NULL COMMENT '通话时长',
  `tonghua_duration` decimal(13,2) DEFAULT NULL,
  `tonghuacs_error` int(9) DEFAULT NULL,
  `tonghuacs` int(9) DEFAULT NULL COMMENT '通话次数',
  `jietonglv_error` int(9) DEFAULT NULL COMMENT '接通率',
  `jietongcs` int(9) DEFAULT NULL COMMENT '接通次数',
  `jietonglv` decimal(13,2) DEFAULT NULL,
  `anjian_error` int(9) DEFAULT NULL COMMENT '案件量',
  `anjian_duration` int(11) DEFAULT NULL COMMENT '案件处理量',
  `anjianlv_error` int(9) DEFAULT NULL COMMENT '案件处理率',
  `anjianlv` decimal(13,2) DEFAULT NULL,
  `anjianyichuli` int(11) DEFAULT NULL,
  `shiji_error` int(9) DEFAULT NULL COMMENT '实际',
  `yinghuan` decimal(15,2) DEFAULT NULL,
  `shijilv_error` int(9) DEFAULT NULL COMMENT '实际率',
  `shijilv` decimal(15,2) DEFAULT NULL COMMENT '实际率',
  `shiji` decimal(15,2) DEFAULT NULL,
  `login_zong` int(9) DEFAULT NULL COMMENT '登录异常总',
  `huankuan_zong` int(9) DEFAULT NULL COMMENT '还款异常总',
  `tonghua_zong` int(9) DEFAULT NULL COMMENT '通话异常总',
  `anjian_zong` int(9) DEFAULT NULL COMMENT '案件异常总',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  `online_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `online_one` int(9) DEFAULT NULL COMMENT '在线时长-条件1',
  `online_two` int(9) DEFAULT NULL COMMENT '在线时长-条件2',
  `out_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '退出条件',
  `out_one` int(9) DEFAULT NULL COMMENT '退出条件1',
  `out_two` int(9) DEFAULT NULL COMMENT '退出条件2',
  `intervals_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '间隔条件',
  `intervals_one` int(9) DEFAULT NULL COMMENT '间隔条件1',
  `intervals_two` int(9) DEFAULT NULL COMMENT '间隔条件2',
  `conversation_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通话时长条件',
  `conversation_one` int(9) DEFAULT NULL COMMENT '通过条件1',
  `conversation_two` int(9) DEFAULT NULL COMMENT '通话条件2',
  `conversation_cishu_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通话次数条件',
  `conversation_cishu_one` int(9) DEFAULT NULL COMMENT '通话次数1',
  `conversation_cishu_two` int(9) DEFAULT NULL COMMENT '通话次数2',
  `eng_rate_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接通条件',
  `eng_rate_one` int(9) DEFAULT NULL COMMENT '接通率条件1',
  `eng_rate_two` int(9) DEFAULT NULL COMMENT '接通率条件2',
  `case_numbers_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '案件处理量条件',
  `case_numbers_one` int(9) DEFAULT NULL COMMENT '案件数量条件1',
  `case_numbers_two` int(9) DEFAULT NULL COMMENT '案件数量条件2',
  `case_rate_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '案件处理率条件',
  `case_rate_one` int(9) DEFAULT NULL COMMENT '案件处理率条件1',
  `case_rate_two` int(9) DEFAULT NULL COMMENT '案件处理率条件2',
  `sj_repayment_condition` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实际还款条件',
  `sj_repayment_one` int(9) DEFAULT NULL COMMENT '实际还款条件1',
  `sj_repayment_two` int(9) DEFAULT NULL COMMENT '实际还款条件2',
  `sj_repayment_rate_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实际还款率条件',
  `sj_repayment_rate_one` int(9) DEFAULT NULL COMMENT '实际还款率条件1',
  `sj_repayment_rate_two` int(9) DEFAULT NULL COMMENT '实际还款率条件2',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ud` (`rule_id`,`details_id`,`days`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

alter table t_lc_inforeporting_company  modify column top_four_cards varchar(10) COMMENT '卡号前四位';
alter table t_lc_inforeporting_company  modify column last_four_cards varchar(10) COMMENT '卡号后四位';
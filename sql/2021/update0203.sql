alter table t_lc_task add `info_up` int(3) DEFAULT '0' COMMENT '信息更新申请状态';
DROP TABLE IF EXISTS `t_lc_task_uplog`;
CREATE TABLE `t_lc_task_uplog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `date_log` date DEFAULT NULL,
  `cishu` int(3) DEFAULT '0' COMMENT '次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udd` (`user_id`,`date_log`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `t_lc_task_infoup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主鍵ID',
  `task_id` bigint(20) DEFAULT NULL COMMENT '任务id',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案件编号',
  `certificate_no` varchar(100) DEFAULT NULL COMMENT '证件号',
  `certificate_type` int(11) DEFAULT NULL COMMENT '证件类型',
  `custom_code` varchar(100) DEFAULT NULL COMMENT '客户编号',
  `custom_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户姓名',
  `arrears_total` decimal(13,2) DEFAULT NULL COMMENT '委案金额',
  `overdue_days` bigint(11) DEFAULT NULL COMMENT '逾期天数',
  `owner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属人',
  `org_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属机构',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属机构',
  `create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` varchar(30) DEFAULT NULL COMMENT '信息审批人',
  `modify_time` datetime DEFAULT NULL COMMENT '信息审批时间',
  `infoup_apro_status` int(3) DEFAULT '0' COMMENT '信息审核状态',
  `infoup_by` varchar(30) DEFAULT NULL COMMENT '信息更新人',
  `infoup_time` datetime DEFAULT NULL COMMENT '信息更新时间',
  `infoup_status` int(3) DEFAULT '0' COMMENT '信息更新状态',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `certificate_address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `import_batch_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3939964482257 DEFAULT CHARSET=utf8 COMMENT='信息更新表';
CREATE TABLE `t_lc_infoup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_infoup_id` bigint(20) NOT NULL COMMENT '更新任务id',
  `task_id` bigint(20) NOT NULL COMMENT '关联任务id',
  `types` tinyint(3) DEFAULT '0' COMMENT '更新类型',
  `names` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `relations` tinyint(3) DEFAULT NULL COMMENT '与本人关系',
  `phone` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `contents` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `import_batch_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `org_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属机构',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属机构',
  `case_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '案件编号',
  `cunzai` tinyint(3) DEFAULT '0' COMMENT '存在联系表中',
  PRIMARY KEY (`id`),
  KEY `pri` (`task_id`),
  KEY `sc` (`org_id`,`case_no`,`import_batch_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
alter table t_lc_cust_contact add `infoup_id` bigint(20)  DEFAULT '0' COMMENT '信息更新id';
alter table t_lc_cust_contact add `infoup_status` int(3)  DEFAULT '0' COMMENT '信息更新状态';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('信息更新', 0, '6', '#', 'M', '0', '', 'fa fa-book', 'admin', '2018-03-01', 'ry', '2018-03-01', '【信息更新】');
-- 菜单 SQL 信息更新
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('信息更新任务', (select t.menu_id from sys_menu t where t.menu_name = '信息更新'), '1', '/task/infoup/infoupGx', 'C', '0', 'taskinfoup:infoup:listGx', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '【信息更新任务】菜单');
-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();
-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', @parentId, '1',  '#',  'F', '0', 'taskinfoup:infoup:listGx',   '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('状态更新', @parentId, '2',  '#',  'F', '0', 'taskinfoup:infoup:updateStatusGx',   '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('电话联系人列表', @parentId, '3',  '#',  'F', '0', 'infouplog:infoup:list',   '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('电话或联系人新增', @parentId, '4',  '#',  'F', '0', 'infouplog:infoup:add',   '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('电话或联系人修改', @parentId, '5',  '#',  'F', '0', 'infouplog:infoup:edit',   '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
-- 菜单 SQL 信息更新审批
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('信息更新审批', (select t.menu_id from sys_menu t where t.menu_name = '信息更新'), '2', '/task/infoup', 'C', '0', 'taskinfoup:infoup:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '【信息更新审批】菜单');
-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();
-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('信息更新查询-按钮', @parentId, '1',  '#',  'F', '0', 'taskinfoup:infoup:list',   '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('信息状态更新', @parentId, '1',  '#',  'F', '0', 'taskinfoup:infoup:updateStatus',   '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
-- 菜单 SQL 信息更新审批
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('信息更新查询', (select t.menu_id from sys_menu t where t.menu_name = '信息更新'), '3', '/task/infoup/infoupGxSer', 'C', '0', 'taskinfoup:infoup:listGxSer', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '【信息更新查询】菜单');
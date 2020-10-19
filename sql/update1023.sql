DROP TABLE IF EXISTS `t_lp_result`;
CREATE TABLE `t_lp_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `org_id` int(11) DEFAULT NULL COMMENT '项目id',
  `org_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目结果指标', (select t.menu_id from sys_menu t where t.menu_name = '通用项目指标'), '1', '/project/result', 'C', '0', 'project:result:view', '#', 'admin', NOW(), 'admin', NOW(), '项目结果指标');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', @parentId, '1',  '#',  'F', '0', 'project:result:list',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新增', @parentId, '2',  '#',  'F', '0', 'project:result:add',          '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('修改', @parentId, '3',  '#',  'F', '0', 'project:result:edit',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('删除', @parentId, '4',  '#',  'F', '0', 'project:result:remove',       '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', @parentId, '5',  '#',  'F', '0', 'project:result:export',       '#', 'admin', NOW(), 'admin', NOW(), '');


DROP TABLE IF EXISTS `t_lp_process`;
CREATE TABLE `t_lp_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `org_id` int(11) DEFAULT NULL COMMENT '项目id',
  `org_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `total_call_num` int(11) DEFAULT NULL COMMENT '拨打量(次)',
  `ave_call_num` decimal(10,0) DEFAULT NULL COMMENT '人均拨打量',
  `total_called_num` int(11) DEFAULT NULL COMMENT '接通量(次)',
  `avg_called_num` decimal(10,0) DEFAULT NULL COMMENT '人均接通次数',
  `total_call_len` decimal(10,0) DEFAULT NULL COMMENT '通话时长',
  `avg_call_len` decimal(10,0) DEFAULT NULL COMMENT '人均通话时长',
  `total_called_rate` decimal(10,0) DEFAULT NULL COMMENT '接通率',
  `avg_called_rate` decimal(10,0) DEFAULT NULL COMMENT '人均接通率',
  `ext_phone_sign` varchar(255) DEFAULT NULL COMMENT '外显号码标记情况',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目过程指标', (select t.menu_id from sys_menu t where t.menu_name = '通用项目指标'), '1', '/project/process', 'C', '0', 'project:process:view', '#', 'admin', NOW(), 'admin', NOW(), '项目过程指标');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', @parentId, '1',  '#',  'F', '0', 'project:process:list',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新增', @parentId, '2',  '#',  'F', '0', 'project:process:add',          '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('修改', @parentId, '3',  '#',  'F', '0', 'project:process:edit',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('删除', @parentId, '4',  '#',  'F', '0', 'project:process:remove',       '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', @parentId, '5',  '#',  'F', '0', 'project:process:export',       '#', 'admin', NOW(), 'admin', NOW(), '');

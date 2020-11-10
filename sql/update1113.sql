-- ----------------------------
-- Table structure for t_lc_station_letter
-- ----------------------------
CREATE TABLE `t_lc_station_letter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '消息标题',
  `content` varchar(100) DEFAULT NULL COMMENT '消息内容',
  `send_range` tinyint(1) DEFAULT NULL COMMENT '发送范围，是否全部用户，1：是，2：否(指定用户)',
  `user_ids` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '坐席id，多个之前用逗号分隔',
  `send_type` tinyint(1) DEFAULT NULL COMMENT '发送方式，是否立即发送，1：是，2：否(定时发送)',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间，如果立即发送则等于创建时间',
  `send_status` tinyint(1) DEFAULT NULL COMMENT '发送状态，1：已发送，2：待发送',
  `create_by` int(5) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(5) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `org_id` int(11) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='站内信';

-- ----------------------------
-- Table structure for t_lc_station_letter_agent
-- ----------------------------
CREATE TABLE `t_lc_station_letter_agent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `letter_id` int(11) DEFAULT NULL COMMENT '站内信主键',
  `title` varchar(50) DEFAULT NULL COMMENT '消息标题',
  `content` varchar(100) DEFAULT NULL COMMENT '消息内容',
  `agent_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '坐席id',
  `send_by` int(5) DEFAULT NULL COMMENT '发送人',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间，如果立即发送则等于创建时间',
  `read_status` tinyint(1) DEFAULT NULL COMMENT '状态，1：已读，2：未读',
  `create_by` int(5) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(5) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `org_id` int(11) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='站内信';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('站内信管理', (select t.menu_id from sys_menu t where t.menu_name = '催收管理'), '9', '/station/letter', 'C', '0', 'station:letter:view', '#', 'admin', NOW(), 'admin', NOW(), '站内信菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', @parentId, '1',  '#',  'F', '0', 'station:letter:list',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新增', @parentId, '2',  '#',  'F', '0', 'station:letter:add',          '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('修改', @parentId, '3',  '#',  'F', '0', 'station:letter:edit',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('删除', @parentId, '4',  '#',  'F', '0', 'station:letter:remove',       '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('详情', @parentId, '5',  '#',  'F', '0', 'station:letter:detail',       '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', @parentId, '6',  '#',  'F', '0', 'station:letter:export',       '#', 'admin', NOW(), 'admin', NOW(), '');
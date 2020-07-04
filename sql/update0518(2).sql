-- ----------------------------
-- Table structure for t_lc_org_speechcraft_conf
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_org_speechcraft_conf`;
CREATE TABLE `t_lc_org_speechcraft_conf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL COMMENT '委托方编码',
  `org_name` varchar(50) DEFAULT NULL COMMENT '委托方名称',
  `concurrent_value` int(11) DEFAULT NULL COMMENT '并发量：每个委托方可配的ai坐席数',
  `speechcraft_id` varchar(255) DEFAULT NULL COMMENT '机器人话术id，多个之间逗号分隔',
  `speechcraft_name` varchar(255) DEFAULT NULL COMMENT '话术名称，多个之间逗号分隔',
  `del_flag` int(1) DEFAULT NULL COMMENT '是否删除，1：是2：否',
  `scene_def_id` varchar(255) DEFAULT NULL COMMENT '场景id，多个之间逗号分隔',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='委托方话术配置';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('委托方话术配置', '2131', '1', '/orgspeech/conf', 'C', '0', 'orgspeech:conf:view', '#', 'admin',NOW(), 'admin', NOW(), '委托方话术配置');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', @parentId, '1',  '#',  'F', '0', 'orgspeech:conf:list',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新增', @parentId, '2',  '#',  'F', '0', 'orgspeech:conf:add',          '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('修改', @parentId, '3',  '#',  'F', '0', 'orgspeech:conf:edit',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('删除', @parentId, '4',  '#',  'F', '0', 'orgspeech:conf:remove',       '#', 'admin', NOW(), 'admin', NOW(), '');

INSERT INTO `asset`.`t_lc_call_code` (`call_code`, `is_valid`, `is_connected`) VALUES ('R01', '1', '1');

INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('4', '20:00', 'robot_help_collect_call_end_time', 'robot_call_config', NULL, NULL, 'Y', '0', 'admin', '2020-05-21 20:20:53', '', NULL, '机器人协催呼叫结束时间');

alter table t_lc_cust_contact add is_close char(1) DEFAULT '0' COMMENT '是否停拨(0,正常，1,停拨)';

alter table t_lc_call_record add find_date date  DEFAULT NULL COMMENT '查账日期';
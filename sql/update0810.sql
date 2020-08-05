drop table if exists sys_login_status;
CREATE TABLE `sys_login_status` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `org_id` varchar(10) DEFAULT NULL COMMENT '委托方id',
  `org_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '委托方名称',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名称',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `start_time` datetime DEFAULT NULL COMMENT '登录开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '登录结束时间',
  `online_len` varchar(64) DEFAULT NULL COMMENT '在线时长(分钟)',
  `status` char(1) DEFAULT NULL COMMENT '当前状态(0未登录,1已登录)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table sys_login_status comment '登录状态表';



INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('登录状态', (SELECT t.menu_id from sys_menu t where t.menu_name='系统监控'), '2', '/system/status', 'menuItem', 'C', '0', 'system:status:view', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name='登录状态'), '1', '#', 'menuItem', 'F', '0', 'system:status:list', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('导出', (SELECT t.menu_id from sys_menu t where t.menu_name='登录状态'), '2', '#', 'menuItem', 'F', '0', 'system:status:export', '#', 'admin', now(), 'admin', now(), '');



-- 历史数据处理
update sys_login_status set online_len = online_len * 60 where online_len is not null;
ALTER table sys_login_status modify COLUMN online_len varchar(64) DEFAULT NULL COMMENT '在线时长(秒)';


ALTER TABLE sys_login_status ADD COLUMN ip_addr varchar(20) DEFAULT NULL COMMENT 'ip地址';
ALTER TABLE sys_login_status ADD COLUMN logout_num int DEFAULT 0 COMMENT '退出次数';
ALTER TABLE sys_login_status ADD COLUMN session_id varchar(100) DEFAULT NULL COMMENT 'sessionId';
ALTER TABLE sys_login_status ADD COLUMN host_addr varchar(20) DEFAULT NULL COMMENT '服务器IP';
ALTER TABLE sys_login_status ADD COLUMN interval_time varchar(64) DEFAULT 0 COMMENT '间隔时间(秒)';


CREATE TABLE `sys_ip_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `start_ip` varchar(64) DEFAULT NULL COMMENT '开始ip',
  `end_ip` varchar(64) DEFAULT NULL COMMENT '结束ip',
	`part_ip` varchar(64) DEFAULT NULL COMMENT 'ip段',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ip段配置';

INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('ip段配置', (SELECT t.menu_id from sys_menu t where t.menu_name='系统配置'), '21', '/ip/config', 'menuItem', 'C', '0', 'ip:config:view', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name='ip段配置'), '1', '#', 'menuItem', 'F', '0', 'ip:config:list', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('新增', (SELECT t.menu_id from sys_menu t where t.menu_name='ip段配置'), '2', '#', 'menuItem', 'F', '0', 'ip:config:add', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('修改', (SELECT t.menu_id from sys_menu t where t.menu_name='ip段配置'), '3', '#', 'menuItem', 'F', '0', 'ip:config:edit', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('删除', (SELECT t.menu_id from sys_menu t where t.menu_name='ip段配置'), '4', '#', 'menuItem', 'F', '0', 'ip:config:remove', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('导出', (SELECT t.menu_id from sys_menu t where t.menu_name='ip段配置'), '5', '#', 'menuItem', 'F', '0', 'ip:config:export', '#', 'admin', now(), 'admin', now(), '');

-- 定时任务配置
insert into sys_job( job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time )values( '定时退出系统', 'DEFAULT', 'logoutTimer.autoLogout', '0 0/15 * * * ?', '1', '1', '0', 'admin', sysdate() );

--兴业orgId配置
insert into sys_config ( config_name, config_key, config_value, config_type, create_by, create_time )values( '兴业orgId', 'xyOrgId', '213', 'N', 'admin', sysdate() );

-- 把历史数据中没有退出的都给退出

-- 借据号
alter table cur_assets_package_temp add COLUMN jjh varchar(64) DEFAULT NULL COMMENT '借据号';
alter table cur_assets_package add COLUMN jjh varchar(64) DEFAULT NULL COMMENT '借据号';
alter table cur_assets_package_his add COLUMN jjh varchar(64) DEFAULT NULL COMMENT '借据号';
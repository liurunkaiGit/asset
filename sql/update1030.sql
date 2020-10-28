alter table org_package add column is_expire_auto_back_case tinyint(1) default null comment '是否到期自动退案,1：是，2：否';

alter TABLE t_lp_result MODIFY COLUMN `total_recycle` decimal(10,2) DEFAULT NULL COMMENT '累计回收';
alter TABLE t_lp_result MODIFY COLUMN `predict_commission` decimal(10,2) DEFAULT NULL COMMENT '预计佣金';

CREATE TABLE `t_lp_finance_commission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `month` varchar(10) DEFAULT NULL COMMENT '月份',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `total_num` int(11) DEFAULT NULL COMMENT '总笔数',
  `latest_predict_commission` decimal(10,0) DEFAULT NULL COMMENT '最新预计结佣',
  `actual_commission` decimal(10,0) DEFAULT NULL COMMENT '实际结佣',
  `commission_different` decimal(10,0) DEFAULT NULL COMMENT '结佣差异',
  `fee_status` tinyint(255) DEFAULT NULL COMMENT '结费状态-是否填写，1：是，2：否',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务结佣表';

-- 菜单 SQL
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('财务核算', (select t.menu_id from sys_menu t where t.menu_name = '共享信息管理'), '4', '#', 'menuItem', 'M', '0', NULL, '#', 'admin', NOW(), '', NULL, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('财务结佣', (select t.menu_id from sys_menu t where t.menu_name = '财务核算'), '1', '/finance/commission', 'C', '0', 'finance:commission:view', '#', 'admin', NOW(), 'admin', NOW(), '财务结佣');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', @parentId, '1',  '#',  'F', '0', 'finance:commission:list',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', @parentId, '2',  '#',  'F', '0', 'finance:commission:export',       '#', 'admin', 'admin', NOW(), 'admin', NOW(), '');
alter table org_package add column is_expire_auto_back_case tinyint(1) default null comment '是否到期自动退案,1：是，2：否';

-- alter TABLE t_lp_result MODIFY COLUMN `total_recycle` decimal(10,2) DEFAULT NULL COMMENT '累计回收';
-- alter TABLE t_lp_result MODIFY COLUMN `predict_commission` decimal(10,2) DEFAULT NULL COMMENT '预计佣金';

CREATE TABLE `t_lp_finance_commission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `month` varchar(10) DEFAULT NULL COMMENT '月份',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `total_num` int(11) DEFAULT NULL COMMENT '总笔数',
  `latest_predict_commission` decimal(10,2) DEFAULT NULL COMMENT '最新预计结佣',
  `actual_commission` decimal(10,2) DEFAULT NULL COMMENT '实际结佣',
  `commission_different` decimal(10,2) DEFAULT NULL COMMENT '结佣差异',
  `fee_status` tinyint(1) DEFAULT NULL COMMENT '结费状态-是否填写，1：是，2：否',
  `update_by` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务结佣表';

-- 菜单 SQL
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('财务核算', (select t.menu_id from sys_menu t where t.menu_name = '共享管理'), '4', '#', 'menuItem', 'M', '0', NULL, '#', 'admin', NOW(), '', NULL, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('财务结佣', (select t.menu_id from sys_menu t where t.menu_name = '财务核算'), '1', '/finance/commission', 'C', '0', 'finance:commission:view', '#', 'admin', NOW(), 'admin', NOW(), '财务结佣');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('查询', @parentId, '1',  '#',  'F', '0', 'finance:commission:list',         '#', 'admin', NOW(), 'admin', NOW(), '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('导出', @parentId, '2',  '#',  'F', '0', 'finance:commission:export',       '#', 'admin', NOW(), 'admin', NOW(), '');

INSERT INTO `sys_job` (`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('生成任务结佣数据', 'DEFAULT', 'financeSchedule.createCommission', '0 0 0 1 * ?', '1', '1', '1', 'admin', '2020-10-27 18:42:56', '', NULL, '每个月1号生成任务结佣数据');

ALTER table t_lc_call_record add column org_id varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属机构';
ALTER table t_lc_call_record_his add column org_id varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属机构';

-- SELECT COUNT(1) FROM t_lc_task where case_no like 'HT%';
--
-- SELECT COUNT(1) FROM t_lc_task where case_no like 'HT%' and org_id = 213;

-- update t_lc_call_record set org_id = 213 where case_no like 'HT%';

-- update t_lc_call_record set org_id = 213 where create_by in (164,
-- 165,
-- 168,
-- 169,
-- 170,
-- 171,
-- 173,
-- 174,
-- 175,
-- 176,
-- 178,
-- 179,
-- 181,
-- 185,
-- 186,
-- 187,
-- 188,
-- 189,
-- 190,
-- 192,
-- 194,
-- 195,
-- 196,
-- 197,
-- 200,
-- 209,
-- 214,
-- 215,
-- 216,
-- 243,
-- 244,
-- 245,
-- 247,
-- 248,
-- 249,
-- 250,
-- 251,
-- 252,
-- 253,
-- 254,
-- 255,
-- 256,
-- 257,
-- 258,
-- 259,
-- 260,
-- 261,
-- 262,
-- 263,
-- 264,
-- 265,
-- 266,
-- 268,
-- 269,
-- 283,
-- 291,
-- 292,
-- 293,
-- 294,
-- 295,
-- 296,
-- 297,
-- 298,
-- 299,
-- 300,
-- 301,
-- 302,
-- 304,
-- 305);
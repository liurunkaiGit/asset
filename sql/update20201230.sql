CREATE TABLE `t_lc_pre_call_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `case_no` varchar(255) DEFAULT NULL COMMENT '案件号',
  `import_batch_no` varchar(50) DEFAULT NULL COMMENT '案件导入批次编号',
  `org_id` varchar(30) DEFAULT NULL COMMENT '委托机构ID',
  `batch_no` int(20) DEFAULT NULL COMMENT '批次号',
  `plan_id` varchar(30) DEFAULT NULL COMMENT '预测式计划id',
  `caller` varchar(30) DEFAULT NULL COMMENT '主叫号码',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `contact_name` varchar(255) DEFAULT NULL COMMENT '联系人姓名',
  `contact_relation` int(10) DEFAULT NULL COMMENT '与本人关系',
  `call_result` varchar(30) DEFAULT NULL COMMENT '呼叫结果',
  `last_call_time` varchar(50) DEFAULT NULL COMMENT '最后呼叫时间',
  `exec_status` int(10) DEFAULT NULL COMMENT '执行状态，0：已暂停 1：已完成 2：未执行 3：取消',
  `is_distinct` char(1) DEFAULT NULL COMMENT '是否去重，0：不去重 1：去重',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预测式外呼任务';

INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('预测式外呼管理', (SELECT t.menu_id from sys_menu t where t.menu_name='催收管理'), '20', '#', 'menuItem', 'M', '0', NULL, '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('预测式外呼计划', (SELECT t.menu_id from sys_menu t where t.menu_name='预测式外呼管理'), '1', '/collect/pretest/workListForMenu', 'menuItem', 'C', '0', NULL, '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('预测式外呼查询', (SELECT t.menu_id from sys_menu t where t.menu_name='预测式外呼管理'), '2', '/collect/pretest/selectPreTestDetail', 'menuItem', 'C', '0', NULL, '#', 'admin', now(), 'admin', now(), '');


insert into sys_job( job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time )values( '预测式外呼状态更新', 'DEFAULT', 'PreTestTaskTimer.updateCallResultStatus', '0 0/10 * * * ?', '1', '1', '0', 'admin', sysdate() );
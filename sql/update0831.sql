alter table t_lc_cust_contact add phone_status varchar(16) DEFAULT NULL  COMMENT '手机号码状态';
alter table t_lc_cust_contact_his add phone_status varchar(16) DEFAULT NULL  COMMENT '手机号码状态';
alter table cur_assets_package_his add COLUMN dzhxrq date DEFAULT NULL COMMENT '呆账核销日期';
alter TABLE cur_assets_package_his ADD COLUMN curNo varchar(64) DEFAULT NULL COMMENT '用户编号';
alter TABLE cur_assets_package_his ADD COLUMN payStatus varchar(64) DEFAULT NULL COMMENT '还款状态';
alter TABLE cur_assets_package_his ADD COLUMN lastLoanDate date DEFAULT NULL COMMENT '贷款到期日';
alter TABLE cur_assets_package_his ADD COLUMN lastRepayAmount decimal(20,2) DEFAULT NULL COMMENT '最近还款金额';

CREATE TABLE `phone_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `case_no` varchar(64) DEFAULT NULL COMMENT '案件编号',
  `waje` decimal(13,2) DEFAULT NULL COMMENT '委案金额',
  `jayhje` decimal(13,2) DEFAULT NULL COMMENT '结案应还金额',
  `phone` varchar(64) DEFAULT NULL COMMENT '电话号码',
  `relation` int(100) DEFAULT NULL COMMENT '与本人关系',
  `phoneStatus` varchar(64) DEFAULT NULL COMMENT '号码状态(为百融号码状态，-1表示失败)',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构号',
  `org_name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` datetime DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='号码状态表';

INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('号码状态查询', (SELECT t.menu_id from sys_menu t where t.menu_name='催收管理'), '18', '/phone/status', 'menuItem', 'C', '0', 'phone:status:view', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name='号码状态查询'), '1', '#', 'menuItem', 'F', '0', 'phone:status:list', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('导出', (SELECT t.menu_id from sys_menu t where t.menu_name='号码状态查询'), '2', '#', 'menuItem', 'F', '0', 'phone:status:export', '#', 'admin', now(), 'admin', now(), '');

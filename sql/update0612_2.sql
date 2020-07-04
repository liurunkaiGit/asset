alter table t_lc_call_record modify column create_time datetime(0);

alter table t_lc_report_day_process modify column average_effective_call_code_num varchar(20);

ALTER TABLE t_lc_org_speechcraft_conf add start_call_time varchar(20) DEFAULT NULL COMMENT '呼叫开始时间';
ALTER TABLE t_lc_org_speechcraft_conf add end_call_time varchar(20) DEFAULT NULL COMMENT '呼叫结束时间';
ALTER TABLE t_lc_org_speechcraft_conf add company_id int(5) DEFAULT NULL COMMENT '公司id';
ALTER TABLE t_lc_org_speechcraft_conf add speechcraft_variable varchar(2000) DEFAULT NULL COMMENT '话术变量';

INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('19', '产品名称', 'product_name', 't_lc_task', NULL, NULL, 'Y', '0', 'admin', '2020-06-09 16:44:20', '', NULL, '产品名称');
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('19', '产品名称', 'product_name', 't_lc_duncase', NULL, NULL, 'Y', '0', 'admin', '2020-06-09 16:44:56', '', NULL, '产品名称');

INSERT INTO `t_lc_column_query` (`org_id`, `org_name`, `table_name`, `column_name`, `column_name_cn`, `column_type`, `bean_name`, `column_value`, `create_by`, `create_time`, `modify_by`, `modify_time`) VALUES ('208', '中银消金催收项目组', 't_lc_task', 'product_name', '产品名称', 'string', 'productName', NULL, '1', '2020-06-09 16:46:03', '1', '2020-06-09 16:46:03');
INSERT INTO `t_lc_column_query` (`org_id`, `org_name`, `table_name`, `column_name`, `column_name_cn`, `column_type`, `bean_name`, `column_value`, `create_by`, `create_time`, `modify_by`, `modify_time`) VALUES ('208', '中银消金催收项目组', 't_lc_duncase', 'product_name', '产品名称', 'string', 'productName', NULL, '1', '2020-06-09 16:46:29', '1', '2020-06-09 16:46:29');

CREATE TABLE `t_lc_select_record` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `case_no` varchar(255) DEFAULT NULL COMMENT '案件编号',
  `search_type` int(11) DEFAULT NULL COMMENT '查找方式 1：114、2：12580、3：天眼查、4：其他',
  `contact_relation` int(11) DEFAULT NULL COMMENT '和本人关系 -1=其它,1=本人,2=直系,3=亲戚,4=朋友,5=父母,6=配偶,7=兄弟,8=姐妹,9=哥哥,10=兄长,11=弟弟,12=姐姐,13=妹妹,14=家人,15=老公,16=老婆,17=同事,18=公司',
  `validate_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'Y' COMMENT '是否有效 Y：是，N：否',
  `other_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其他查找方式备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人名称',
  `content` varchar(255) DEFAULT NULL COMMENT '查找记录',
  `obj_name` varchar(64) DEFAULT NULL COMMENT '查找对象名称',
  `other_contact_relation` varchar(255) DEFAULT NULL COMMENT '其他关系备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000918213 DEFAULT CHARSET=utf8 COMMENT='查找记录表';

INSERT INTO `sys_dict_type`(`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('查找方式', 'search_type', '0', 'admin', '2020-06-10 14:46:59', '', NULL, '查找方式:1表示114、2表示12580、3表示天眼查、4表示其他');

INSERT INTO `sys_dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '114', '1', 'search_type', '', 'default', 'Y', '0', 'admin', '2020-06-10 14:48:06', 'admin', '2020-06-10 14:48:26', '114');
INSERT INTO `sys_dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '12580', '2', 'search_type', NULL, 'default', 'Y', '0', 'admin', '2020-06-10 14:48:49', '', NULL, '12580');
INSERT INTO `sys_dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '天眼查', '3', 'search_type', NULL, 'default', 'Y', '0', 'admin', '2020-06-10 14:49:13', '', NULL, '天眼查');
INSERT INTO `sys_dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, '其他', '4', 'search_type', NULL, 'default', 'Y', '0', 'admin', '2020-06-10 14:49:35', '', NULL, '其他');


INSERT INTO `sys_dict_data`(`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (100, '其他', '-1', 'sys_custom_contact_rela', '', '', 'Y', '0', 'admin', '2020-06-11 11:50:39', 'admin', '2020-06-11 12:01:41', '');


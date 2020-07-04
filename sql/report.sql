-- ----------------------------
-- Table structure for t_lc_report_case_contact
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_report_case_contact`;
CREATE TABLE `t_lc_report_case_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `appoint_case_money_section` varchar(10) DEFAULT NULL COMMENT '委案金额区间',
  `total_customer` int(11) DEFAULT NULL COMMENT '总户数',
  `can_contact_customer` int(11) DEFAULT NULL COMMENT '可联户数',
  `customer_contact_recovery` varchar(10) DEFAULT NULL COMMENT '账户联系率',
  `can_contact_count` int(11) DEFAULT NULL COMMENT '可联次数',
  `can_contact_case_permeta_recovery` varchar(10) DEFAULT NULL COMMENT '可联案件渗透率',
  `create_by` varchar(10) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案件可联率报表';

-- ----------------------------
-- Table structure for t_lc_report_day_process
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_report_day_process`;
CREATE TABLE `t_lc_report_day_process` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `seat_group` varchar(10) DEFAULT NULL COMMENT '用户组别',
  `seat_num` varchar(10) DEFAULT NULL COMMENT '坐席工号',
  `seat_name` varchar(10) DEFAULT NULL COMMENT '坐席名称',
  `deal_with_consumer_count` int(5) DEFAULT NULL COMMENT '处理客户数',
  `action_code_num` int(5) DEFAULT NULL COMMENT '行动码数量',
  `call_code_num` int(5) DEFAULT NULL COMMENT '电话码量',
  `call_action_code_recovery` varchar(10) DEFAULT NULL COMMENT '电话码于行动码比值',
  `average_call_code` varchar(10) DEFAULT NULL COMMENT '案件平均电话码量',
  `average_action_code` varchar(10) DEFAULT NULL COMMENT '案件平均行动码量',
  `call_len` varchar(255) DEFAULT NULL COMMENT '通话时长',
  `self_effective_call_code_num` int(5) DEFAULT NULL COMMENT '本人有效电话码量',
  `third_effective_call_code_num` int(5) DEFAULT NULL COMMENT '三方有效电话码量',
  `average_effective_call_code_num` varchar(10) DEFAULT NULL COMMENT '案均有效电话码量',
  `call_connected_recovery` varchar(10) DEFAULT NULL COMMENT '电话接通率',
  `complaint_num` int(5) DEFAULT NULL COMMENT '投诉代码量',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每日过程指标报表';

-- ----------------------------
-- Table structure for t_lc_report_recovery
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_report_recovery`;
CREATE TABLE `t_lc_report_recovery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `seat_group` varchar(10) DEFAULT NULL COMMENT '坐席所在组',
  `seat_num` varchar(255) DEFAULT NULL COMMENT '坐席编号',
  `seat_name` varchar(20) DEFAULT NULL COMMENT '坐席姓名',
  `colling_case_num` bigint(20) DEFAULT NULL COMMENT '在催案件数',
  `confirmed_recycle_case_num` bigint(20) DEFAULT NULL COMMENT '已确认回收案件数',
  `unconfirmed_recycle_case_num` bigint(20) DEFAULT NULL COMMENT '待确认回收案件数',
  `case_recovery` varchar(10) DEFAULT NULL COMMENT '案件回收率',
  `colling_case_money` decimal(10,0) DEFAULT NULL COMMENT '在催案件金额',
  `confirmed_recycle_case_money` decimal(10,0) DEFAULT NULL COMMENT '已确认回收案件金额',
  `unconfirmed_recycle_case_money` decimal(10,0) DEFAULT NULL COMMENT '待确认回收案件金额',
  `money_recovery` varchar(10) DEFAULT NULL COMMENT '金额回收率',
  `create_by` varchar(10) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回收率报表';

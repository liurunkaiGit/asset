/*
Navicat MySQL Data Transfer

Source Server         : 47.102.137.191
Source Server Version : 80018
Source Host           : 47.102.137.191:3306
Source Database       : asset_dev

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-07-21 14:00:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cur_assets_repayment_package_his
-- ----------------------------
DROP TABLE IF EXISTS `cur_assets_repayment_package_his`;
CREATE TABLE `cur_assets_repayment_package_his` (
  `id` varchar(64) NOT NULL,
  `org_casNo` varchar(64) NOT NULL COMMENT '机构案件号',
  `jyqtfy` decimal(20,2) DEFAULT NULL COMMENT '交易其他费用',
  `jylx` decimal(20,2) DEFAULT NULL COMMENT '交易利息',
  `jybj` decimal(20,2) DEFAULT NULL COMMENT '交易本金',
  `jyznf` decimal(20,2) DEFAULT NULL COMMENT '交易滞纳费',
  `jy_type` varchar(64) DEFAULT NULL COMMENT '交易类型',
  `jyje` decimal(20,2) DEFAULT NULL COMMENT '交易金额',
  `product_type` varchar(32) DEFAULT NULL COMMENT '产品类型',
  `jjh` varchar(32) DEFAULT NULL COMMENT '借据号',
  `csr` varchar(32) DEFAULT NULL COMMENT '催收人',
  `csjd` varchar(64) DEFAULT NULL COMMENT '催收节点',
  `fprq` date DEFAULT NULL COMMENT '分配日期',
  `area_center` varchar(128) DEFAULT NULL COMMENT '区域中心',
  `accept_city` varchar(20) DEFAULT NULL COMMENT '受理城市',
  `hth` varchar(32) DEFAULT NULL COMMENT '合同号',
  `dqsyb_yj` varchar(64) DEFAULT NULL COMMENT '地区事业部(一级)',
  `dqsyb_ej` varchar(64) DEFAULT NULL COMMENT '地区事业部(二级)',
  `wbqs` varchar(10) DEFAULT NULL COMMENT '外包期数',
  `wbjb` varchar(64) DEFAULT NULL COMMENT '外包经办',
  `warq` date DEFAULT NULL COMMENT '委案日期',
  `cur_name` varchar(64) DEFAULT NULL COMMENT '客户名称',
  `khjlxm` varchar(32) DEFAULT NULL COMMENT '客户经理姓名',
  `sjrq` date DEFAULT NULL COMMENT '数据日期',
  `sfwbcs` varchar(32) DEFAULT NULL COMMENT '是否外包催收',
  `sfjq` varchar(32) DEFAULT NULL COMMENT '是否结清',
  `bywa` varchar(64) DEFAULT NULL COMMENT '本月委案',
  `ajhsrq` date DEFAULT NULL COMMENT '案件回收日期',
  `xfjrzh` varchar(32) DEFAULT NULL COMMENT '消费金融账号',
  `tzsx` varchar(64) DEFAULT NULL COMMENT '调整事项',
  `tzje` decimal(20,2) DEFAULT NULL COMMENT '调整金额',
  `zhzt` varchar(32) DEFAULT NULL COMMENT '账户状态',
  `hkrq` datetime DEFAULT NULL COMMENT '还款日期',
  `hksyqqs` varchar(64) DEFAULT NULL COMMENT '还款时逾期期数',
  `hkje` decimal(20,2) DEFAULT NULL COMMENT '还款金额',
  `yqcplx` varchar(64) DEFAULT NULL COMMENT '逾期产品类型',
  `yqjd` varchar(64) DEFAULT NULL COMMENT '逾期阶段',
  `quota_product` varchar(64) DEFAULT NULL COMMENT '额度产品',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `jazt` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '结案状态0：未结案 1：已结案',
  `create_by` varchar(32) DEFAULT NULL COMMENT '导入人',
  `create_time` datetime DEFAULT NULL COMMENT '导入时间',
  `is_exit_collect` int(1) DEFAULT NULL COMMENT '是否出催1：是 2：否',
  `import_batch_no` varchar(20) DEFAULT NULL COMMENT '导入批次号，年月日时分秒生成',
  `close_case_date` datetime DEFAULT NULL COMMENT '结案时间',
  `org_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `case_no_index` (`org_casNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产还款';

-- ----------------------------
-- Table structure for free_import_his
-- ----------------------------
DROP TABLE IF EXISTS `free_import_his`;
CREATE TABLE `free_import_his` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_casno` varchar(64) DEFAULT NULL COMMENT '机构案件号',
  `org_id` varchar(64) DEFAULT NULL,
  `import_batch_no` varchar(64) DEFAULT NULL,
  `value` text COMMENT '自由导入数据',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `case_no_index` (`org_casno`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='自由导入';

-- ----------------------------
-- Table structure for t_lc_robot_task_his
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_robot_task_his`;
CREATE TABLE `t_lc_robot_task_his` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_id` bigint(20) NOT NULL COMMENT '机器人任务id',
  `robot_tast_id` bigint(20) NOT NULL COMMENT '机器人任务id',
  `create_by` bigint(5) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `task_name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `owner_name` varchar(255) DEFAULT NULL COMMENT '业务归属人 ',
  `transfer_type` varchar(255) DEFAULT NULL COMMENT '手别',
  `arrears_total` decimal(13,2) DEFAULT NULL COMMENT '委案金额',
  `speech_craft_name` varchar(255) DEFAULT NULL COMMENT '话术名称',
  `call_end_date` datetime DEFAULT NULL COMMENT '任务拨打结束时间',
  `robot_task_status` varchar(255) DEFAULT NULL COMMENT '机器人任务状态',
  `result_value_alias` varchar(255) DEFAULT NULL COMMENT '客户意向标签',
  `call_status` varchar(255) DEFAULT NULL COMMENT '通话状态',
  `call_len` varchar(255) DEFAULT NULL COMMENT '通话时长',
  `task_status` int(10) DEFAULT NULL COMMENT '任务状态',
  `task_type` int(10) DEFAULT NULL COMMENT '任务类型',
  `call_content` text COMMENT '通话内容',
  `call_radio` varchar(300) DEFAULT NULL,
  `call_start_date` datetime DEFAULT NULL COMMENT '拨打开始时间',
  `cur_name` varchar(10) DEFAULT NULL COMMENT '客户名称',
  `phone` varchar(11) DEFAULT NULL COMMENT '客户手机号',
  `org_id` varchar(255) DEFAULT NULL COMMENT '所属机构编号',
  `org_name` varchar(255) DEFAULT NULL COMMENT '所属机构名称',
  `robot` varchar(10) DEFAULT NULL COMMENT '所属机器人：BR、DXM',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案件编号',
  `is_recall` int(1) DEFAULT NULL COMMENT '呼叫类型,1：重复呼叫，2：停止呼叫，3：第二天呼叫',
  `continue_days` int(5) DEFAULT NULL COMMENT '连续呼叫天数',
  `continue_frequency` int(5) DEFAULT NULL COMMENT '当天连续呼叫次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_lc_robot_task_pandect_his
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_robot_task_pandect_his`;
CREATE TABLE `t_lc_robot_task_pandect_his` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `robot_task_id` int(11) DEFAULT NULL COMMENT '机器人任务id',
  `task_name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `speech_craft_name_id` int(11) DEFAULT NULL COMMENT '机器人话术id',
  `speech_craft_name` varchar(50) DEFAULT NULL COMMENT '机器人话术名称',
  `robot_task_status` varchar(50) DEFAULT NULL COMMENT '机器人任务状态',
  `org_id` int(11) DEFAULT NULL COMMENT '机构编号',
  `org_name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `robot` varchar(255) DEFAULT NULL COMMENT '所属机器人',
  `call_total_count` int(11) DEFAULT NULL COMMENT '任务拨打的号码总数',
  `call_done_count` int(11) DEFAULT NULL COMMENT '任务已完成拨打的号码总数',
  `call_called_count` int(11) DEFAULT NULL COMMENT '任务已完成呼通的号码总数',
  `call_rejected_count` int(11) DEFAULT NULL COMMENT '任务呼叫被拒接的号码总数',
  `call_unavailable_count` int(11) DEFAULT NULL COMMENT '任务呼叫无法接通的号码总数',
  `call_from_unavailable_count` int(11) DEFAULT NULL COMMENT '任务主叫号码不可用的号码总',
  `create_by` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `task_start_time` datetime DEFAULT NULL COMMENT '任务开始时间',
  `task_end_time` datetime DEFAULT NULL COMMENT '任务结束时间',
  `call_busy_count` int(11) DEFAULT NULL COMMENT '占线',
  `call_miss_count` int(11) DEFAULT NULL COMMENT '失联',
  `call_blank_count` int(11) DEFAULT NULL COMMENT '空号总数',
  `call_closed_count` int(11) DEFAULT NULL COMMENT '关机总数',
  `call_down_count` int(11) DEFAULT NULL COMMENT '停机总数',
  `call_black_count` int(11) DEFAULT NULL COMMENT '黑名单总数',
  `call_fail_count` int(11) DEFAULT NULL COMMENT '外呼失败总数',
  `call_loss_count` int(11) DEFAULT NULL COMMENT '呼损总数',
  `call_overdue_count` int(11) DEFAULT NULL COMMENT '主叫欠费总数',
  `phone_num` int(11) DEFAULT NULL COMMENT '任务实际可拨打的号码总数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_lc_select_record_his
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_select_record_his`;
CREATE TABLE `t_lc_select_record_his` (
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
  PRIMARY KEY (`id`),
  KEY `case_no_index` (`case_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1000918216 DEFAULT CHARSET=utf8 COMMENT='查找记录表';

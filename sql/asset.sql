/*
Navicat MySQL Data Transfer

Source Server         : 192.168.37.128
Source Server Version : 80018
Source Host           : 192.168.37.128:3306
Source Database       : asset

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-04-01 14:24:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for assets_connect_his_package
-- ----------------------------
DROP TABLE IF EXISTS `assets_connect_his_package`;
CREATE TABLE `assets_connect_his_package` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `assets_id` varchar(64) NOT NULL COMMENT '资产表id',
  `package_id` varchar(64) NOT NULL COMMENT '资产包表id',
  `create_by_his` varchar(64) DEFAULT NULL COMMENT '原创建人',
  `create_time_his` datetime DEFAULT NULL COMMENT '原创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产包与资产关系历史表';

-- ----------------------------
-- Records of assets_connect_his_package
-- ----------------------------

-- ----------------------------
-- Table structure for assets_connect_package
-- ----------------------------
DROP TABLE IF EXISTS `assets_connect_package`;
CREATE TABLE `assets_connect_package` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `assets_id` varchar(64) NOT NULL COMMENT '资产表id',
  `package_id` varchar(64) NOT NULL COMMENT '资产包表id',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产包与资产关系表';

-- ----------------------------
-- Records of assets_connect_package
-- ----------------------------

-- ----------------------------
-- Table structure for asset_package
-- ----------------------------
DROP TABLE IF EXISTS `asset_package`;
CREATE TABLE `asset_package` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `package_name` varchar(128) DEFAULT NULL COMMENT '资产包名称',
  `case_address` varchar(128) DEFAULT NULL COMMENT '案件所属地',
  `case_address_code` varchar(64) DEFAULT NULL COMMENT '案件所属地代码',
  `case_account_age` varchar(32) DEFAULT NULL COMMENT '案件帐龄标识',
  `last_code` varchar(32) DEFAULT NULL COMMENT '最后一次催收代码分类',
  `grade_org_name` varchar(32) DEFAULT NULL COMMENT '评分机构名称',
  `grade_value` varchar(20) DEFAULT NULL COMMENT '评分值',
  `package_amount` decimal(20,2) DEFAULT NULL COMMENT '资产包总金额',
  `package_num` int(11) DEFAULT NULL COMMENT '资产包总笔数',
  `is_null` char(1) DEFAULT NULL COMMENT '是否空包（0是,1否）',
  `is_close` char(1) DEFAULT NULL COMMENT '是否封包（0否,1是）',
  `allocation_by` varchar(32) DEFAULT NULL COMMENT '分发操作人',
  `allocation_time` datetime DEFAULT NULL COMMENT '分发时间',
  `start_org_id` varchar(64) DEFAULT NULL COMMENT '分发前机构id',
  `end_org_id` varchar(64) DEFAULT NULL COMMENT '分发后机构id',
  `expire_time` datetime DEFAULT NULL COMMENT '到期回收时间',
  `recover_amount` decimal(20,2) DEFAULT NULL COMMENT '回收金额',
  `recover_time` datetime DEFAULT NULL COMMENT '回收时间',
  `recover_way` varchar(64) DEFAULT NULL COMMENT '回收方式',
  `recover_by` varchar(32) DEFAULT NULL COMMENT '回收操作人',
  `recover_cause` varchar(255) DEFAULT NULL COMMENT '回收原因',
  `recover_status` char(1) DEFAULT NULL COMMENT '回收状态（0正常,1异常,2费件）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产包表';

-- ----------------------------
-- Records of asset_package
-- ----------------------------

-- ----------------------------
-- Table structure for cur_assets_package
-- ----------------------------
DROP TABLE IF EXISTS `cur_assets_package`;
CREATE TABLE `cur_assets_package` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `org_casNo` varchar(64) NOT NULL COMMENT '机构案件号',
  `org` varchar(20) DEFAULT NULL COMMENT '所属机构',
  `transferType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手别',
  `twentyFourCD` varchar(10) DEFAULT NULL COMMENT '24CD值',
  `BLK` varchar(64) DEFAULT NULL COMMENT 'BLK',
  `productLine` varchar(20) DEFAULT NULL COMMENT '产品线',
  `rmb_last_jkje` decimal(20,2) DEFAULT NULL COMMENT '人民币账户last缴款金额',
  `rmb_ye` decimal(20,2) DEFAULT NULL COMMENT '人民币账户余额',
  `rmb_yhfxzje` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还罚息总额',
  `rmb_cd` decimal(20,2) DEFAULT NULL COMMENT '人民币账户当前CD值',
  `rmb_zhycjkr` date DEFAULT NULL COMMENT '人民币账户最后一次缴款日',
  `rmb_zhhkbs` varchar(10) DEFAULT NULL COMMENT '人民币账户最后还款笔数',
  `rmb_yhlizje` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还利息总额',
  `rmb_yhbj1` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还本金1',
  `rmb_yhbj2` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还本金2',
  `rmb_yhbjzje` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还本金总额',
  `rmb_yhfx1` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还罚息1',
  `rmb_yhfx2` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还罚息2',
  `rmb_yhfy1` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还费用1',
  `rmb_yhfy2` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还费用2',
  `rmb_yhfyzje` decimal(20,2) DEFAULT NULL COMMENT '人民币账户应还费用总额',
  `rmb_zdyhje` decimal(20,2) DEFAULT NULL COMMENT '人民币账户最低应还金额',
  `rmb_qkzje` decimal(20,2) DEFAULT NULL COMMENT '人民币账户欠款总金额',
  `rmb_gded` decimal(20,2) DEFAULT NULL COMMENT '人民币账户额度固定额度',
  `code` varchar(64) DEFAULT NULL COMMENT '代码',
  `borrow_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '借款卡号',
  `borrow_blank` varchar(20) DEFAULT NULL COMMENT '借款卡银行',
  `borrow_ed` decimal(20,2) DEFAULT NULL COMMENT '借款额度',
  `zx_type` varchar(20) DEFAULT NULL COMMENT '债项类型',
  `stop_card` date DEFAULT NULL COMMENT '停卡日',
  `stop_jxrq` date DEFAULT NULL COMMENT '停止计息日期',
  `cs_company` varchar(20) DEFAULT NULL COMMENT '催收公司',
  `rcr` date DEFAULT NULL COMMENT '入催日',
  `gz_flag` varchar(10) DEFAULT NULL COMMENT '共债标识',
  `fz` varchar(10) DEFAULT NULL COMMENT '分值',
  `fq_flag` varchar(10) DEFAULT NULL COMMENT '分期标识',
  `syhx_qtfy` decimal(20,2) DEFAULT NULL COMMENT '剩余核销其他费用',
  `syhx_sxf` decimal(20,2) DEFAULT NULL COMMENT '剩余核销手续费',
  `syhx_bj` decimal(20,2) DEFAULT NULL COMMENT '剩余核销本金',
  `syhx_znf` decimal(20,2) DEFAULT NULL COMMENT '剩余核销滞纳费',
  `syhx_yqx` decimal(20,2) DEFAULT NULL COMMENT '剩余核销逾期息',
  `syhx_jehj` decimal(20,2) DEFAULT NULL COMMENT '剩余核销金额合计',
  `area_center` varchar(128) DEFAULT NULL COMMENT '区域中心',
  `cs_remark_his` varchar(255) DEFAULT NULL COMMENT '历史催收备注',
  `max_yqts_his` varchar(10) DEFAULT NULL COMMENT '历史最大逾期天数',
  `sum_yqts_his` varchar(10) DEFAULT NULL COMMENT '历史累计逾期天数',
  `sum_yqcs_his` varchar(10) DEFAULT NULL COMMENT '历史累计逾期次数',
  `csjggs_his` varchar(10) DEFAULT NULL COMMENT '历史经历催收机构个数',
  `qx_flag` varchar(10) DEFAULT NULL COMMENT '取现标识',
  `st_company` varchar(20) DEFAULT NULL COMMENT '受托公司',
  `accept_firm` varchar(20) DEFAULT NULL COMMENT '受理商户',
  `accept_city` varchar(20) DEFAULT NULL COMMENT '受理城市',
  `accept_wd` varchar(20) DEFAULT NULL COMMENT '受理网点',
  `accept_wd_code` varchar(20) DEFAULT NULL COMMENT '受理网点代码',
  `dzhxrq` date DEFAULT NULL COMMENT '呆账核销日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `wb_yhfxze` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还罚息总额',
  `wb_yhlxze` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还利息总额',
  `wb_yhbj1` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还本金1',
  `wb_yhbj2` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还本金2',
  `wb_yhbjzje` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还本金总额',
  `wb_yhfx1` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还罚息1',
  `wb_yhfx2` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还罚息2',
  `wb_yhfy1` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还费用1',
  `wb_yhfy2` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还费用2',
  `wb_yhfyze` decimal(20,2) DEFAULT NULL COMMENT '外币账户应还费用总额',
  `wb_zdyhe` decimal(20,2) DEFAULT NULL COMMENT '外币账户最低应还额',
  `wb_qkzje` decimal(20,2) DEFAULT NULL COMMENT '外币账户欠款总金额',
  `tx_flag` varchar(10) DEFAULT NULL COMMENT '套现标识',
  `ww_company_code` varchar(64) DEFAULT NULL COMMENT '委外公司代码',
  `ww_city_name` varchar(32) DEFAULT NULL COMMENT '委外城市名称',
  `ww_jh_enddate` date DEFAULT NULL COMMENT '委外计划截止日期',
  `ww_qsrq` date DEFAULT NULL COMMENT '委外起始日期',
  `ww_pc` varchar(10) DEFAULT NULL COMMENT '委托批次',
  `wa_ye` decimal(20,2) DEFAULT NULL COMMENT '委案余额',
  `bill_address` varchar(255) DEFAULT NULL COMMENT '帐单地址',
  `bill_address_postcode` varchar(20) DEFAULT NULL COMMENT '帐单地址邮编',
  `year_rates` varchar(20) DEFAULT NULL COMMENT '年利率',
  `yhk_date` date DEFAULT NULL COMMENT '应还款日',
  `start_yq_date` date DEFAULT NULL COMMENT '开始逾期日期',
  `kh_date` date DEFAULT NULL COMMENT '开户日',
  `ts_flag` varchar(10) DEFAULT NULL COMMENT '投诉标识',
  `credit_value` decimal(20,2) DEFAULT NULL COMMENT '授信额度',
  `tj_firm` varchar(32) DEFAULT NULL COMMENT '推荐商户',
  `tj_city` varchar(32) DEFAULT NULL COMMENT '推荐城市',
  `tj_wd` varchar(32) DEFAULT NULL COMMENT '推荐网点',
  `shhx_sxf` decimal(20,2) DEFAULT NULL COMMENT '收回核销手续费',
  `shhx_bj` decimal(20,2) DEFAULT NULL COMMENT '收回核销本金',
  `shhx_znf` decimal(20,2) DEFAULT NULL COMMENT '收回核销滞纳费',
  `shhx_fy` decimal(20,2) DEFAULT NULL COMMENT '收回核销费用',
  `shhx_yqx` decimal(20,2) DEFAULT NULL COMMENT '收回核销逾期息',
  `shhx_jehj` decimal(20,2) DEFAULT NULL COMMENT '收回核销金额合计',
  `zh_30_day` varchar(10) DEFAULT NULL COMMENT '整合30 DAY',
  `zh_x_day` varchar(10) DEFAULT NULL COMMENT '整合X DA',
  `is_zc` varchar(10) DEFAULT NULL COMMENT '是否仲裁',
  `is_zwcz` varchar(10) DEFAULT NULL COMMENT '是否债务重组',
  `is_jx` varchar(10) DEFAULT NULL COMMENT '是否计息',
  `is_sx` varchar(10) DEFAULT NULL COMMENT '是否诉讼',
  `zjyq_month` date DEFAULT NULL COMMENT '最近逾期月份',
  `hx_sxfcs` varchar(32) DEFAULT NULL COMMENT '核销手续费催收',
  `hx_shzt` varchar(10) DEFAULT NULL COMMENT '核销收回状态',
  `hx_bjcs` varchar(32) DEFAULT NULL COMMENT '核销本金催收',
  `hx_znfcs` varchar(32) DEFAULT NULL COMMENT '核销滞纳费催收',
  `hx_fycs` varchar(32) DEFAULT NULL COMMENT '核销费用催收',
  `hx_yqlxcs` varchar(32) DEFAULT NULL COMMENT '核销逾期息催收',
  `hx_jehjcs` varchar(32) DEFAULT NULL COMMENT '核销金额合计催收',
  `aj_hz_date` date DEFAULT NULL COMMENT '案件核准日期',
  `xfjzzh` varchar(32) DEFAULT NULL COMMENT '消费金融账号',
  `tsaj_type` varchar(32) DEFAULT NULL COMMENT '特殊案件类型',
  `day_rates` varchar(10) DEFAULT NULL COMMENT '日利率',
  `tj_date` date DEFAULT NULL COMMENT '统计日期',
  `dollar_ye` decimal(20,2) DEFAULT NULL COMMENT '美元余额',
  `dollar_CD` varchar(20) DEFAULT NULL COMMENT '美元当前CD值',
  `dollar_zdyjkje` decimal(20,2) DEFAULT NULL COMMENT '美元最低应缴款金额',
  `dollar_zhycjkr` date DEFAULT NULL COMMENT '美元最后一次缴款日',
  `dollar_zhycjkje` decimal(20,2) DEFAULT NULL COMMENT '美元最后一次缴款金额',
  `dollar_hkrhkbs` varchar(10) DEFAULT NULL COMMENT '美元还款日还款笔数',
  `hkfs` varchar(10) DEFAULT NULL COMMENT '获客方式',
  `hkqd` varchar(128) DEFAULT NULL COMMENT '获客渠道',
  `xwpf` varchar(10) DEFAULT NULL COMMENT '行方评分',
  `account_date` varchar(10) DEFAULT NULL COMMENT '账单日',
  `account_yebj` decimal(20,2) DEFAULT NULL COMMENT '账户余额本金',
  `account_jqrq` date DEFAULT NULL COMMENT '账户结清日期',
  `fzzb` varchar(20) DEFAULT NULL COMMENT '辅助组别',
  `revert_card_no` varchar(20) DEFAULT NULL COMMENT '还款卡号',
  `revert_card_blank` varchar(20) DEFAULT NULL COMMENT '还款卡银行',
  `jjqd` varchar(20) DEFAULT NULL COMMENT '进件渠道',
  `overdue_days` varchar(20) DEFAULT NULL COMMENT '逾期天数',
  `overdue_flag` varchar(20) DEFAULT NULL COMMENT '逾期标记',
  `cwcs` varchar(20) DEFAULT NULL COMMENT '长委次数',
  `quota_product` varchar(64) DEFAULT NULL COMMENT '额度产品',
  `quota_code` varchar(64) DEFAULT NULL COMMENT '额度代码',
  `quota_date` date DEFAULT NULL COMMENT '额度激活日期',
  `risk` varchar(128) DEFAULT NULL COMMENT '风险',
  `first_fk_date` date DEFAULT NULL COMMENT '首次放款日期',
  `first_yq_date` date DEFAULT NULL COMMENT '首次逾期日期',
  `first_yqjc_date` date DEFAULT NULL COMMENT '首次逾期解除日期',
  `first_yq_flag` varchar(10) DEFAULT NULL COMMENT '首逾标识',
  `cur_name` varchar(64) DEFAULT NULL COMMENT '客户名称',
  `cur_sex` varchar(10) DEFAULT NULL COMMENT '客户性别',
  `certificate_no` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `certificate_type` varchar(10) DEFAULT NULL COMMENT '证件类型',
  `certificate_address` varchar(255) DEFAULT NULL COMMENT '身份证地址',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `regist_address` varchar(255) DEFAULT NULL COMMENT '户籍地址',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `marriage` varchar(10) DEFAULT NULL COMMENT '婚姻状况',
  `education` varchar(10) DEFAULT NULL COMMENT '教育程度',
  `economy` varchar(10) DEFAULT NULL COMMENT '经济类型',
  `cur_income` decimal(20,2) DEFAULT NULL COMMENT '当前收入',
  `new_address` varchar(255) DEFAULT NULL COMMENT '新增地址',
  `customer_no` varchar(64) DEFAULT NULL COMMENT '客户号',
  `customer_mobile` varchar(12) DEFAULT NULL COMMENT '客户手机号码',
  `customer_home_tel` varchar(12) DEFAULT NULL COMMENT '客户家庭电话',
  `customer_home_address` varchar(128) DEFAULT NULL COMMENT '客户家庭地址',
  `customer_home_postcode` varchar(20) DEFAULT NULL COMMENT '客户家庭地址邮编',
  `card_post_address` varchar(255) DEFAULT NULL COMMENT '卡片寄送地址',
  `job` varchar(20) DEFAULT NULL COMMENT '职务',
  `dept_name` varchar(20) DEFAULT NULL COMMENT '部门名称',
  `indust` varchar(64) DEFAULT NULL COMMENT '行业性质',
  `work_name` varchar(32) DEFAULT NULL COMMENT '客户单位名称',
  `work_address` varchar(128) DEFAULT NULL COMMENT '客户单位地址',
  `work_tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位电话',
  `work_postcode` varchar(10) DEFAULT NULL COMMENT '单位邮编',
  `first_liaison_name` varchar(20) DEFAULT NULL COMMENT '第一联系人姓名',
  `first_liaison_relation` varchar(20) DEFAULT NULL COMMENT '第一联系人与客户关系',
  `first_liaison_mobile` varchar(20) DEFAULT NULL COMMENT '第一联系人手机',
  `first_liaison_tel` varchar(20) DEFAULT NULL COMMENT '第一联系人电话',
  `second_liaison_name` varchar(20) DEFAULT NULL COMMENT '第二联系人姓名',
  `second_liaison_relation` varchar(20) DEFAULT NULL COMMENT '第二联系人与客户关系',
  `second_liaison_mobile` varchar(20) DEFAULT NULL COMMENT '第二联系人手机',
  `second_liaison_tel` varchar(20) DEFAULT NULL COMMENT '第二联系人电话',
  `three_liaison_name` varchar(20) DEFAULT NULL COMMENT '第三联系人姓名',
  `three_liaison_relation` varchar(20) DEFAULT NULL COMMENT '第三联系人与客户关系',
  `three_liaison_mobile` varchar(20) DEFAULT NULL COMMENT '第三联系人手机',
  `three_liaison_tel` varchar(20) DEFAULT NULL COMMENT '第三联系人电话',
  `sjrq` date DEFAULT NULL COMMENT '数据日期',
  `hth` varchar(32) DEFAULT NULL COMMENT '合同号',
  `jjh` varchar(32) DEFAULT NULL COMMENT '借据号',
  `dqsyb_yj` varchar(64) DEFAULT NULL COMMENT '地区事业部(一级)',
  `dqsyb_ej` varchar(64) DEFAULT NULL COMMENT '地区事业部(二级)',
  `csjd` varchar(64) DEFAULT NULL COMMENT '催收节点',
  `wbjb` varchar(64) DEFAULT NULL COMMENT '外包经办',
  `ys_khjlmc` varchar(32) DEFAULT NULL COMMENT '原始客户经理名称',
  `khjlmc` varchar(32) DEFAULT NULL COMMENT '客户经理名称',
  `cpmc` varchar(64) DEFAULT NULL COMMENT '产品名称',
  `xfldycpmc` varchar(64) DEFAULT NULL COMMENT '新分类对应产品名称',
  `qxrq` date DEFAULT NULL COMMENT '起息日期',
  `dqrq` date DEFAULT NULL COMMENT '到期日期',
  `hk_type` varchar(32) DEFAULT NULL COMMENT '还款方式',
  `dkye` decimal(20,2) DEFAULT NULL COMMENT '贷款余额',
  `wbbs` varchar(10) DEFAULT NULL COMMENT '外包标的',
  `yqje` decimal(20,2) DEFAULT NULL COMMENT '逾期金额',
  `yqbj` decimal(20,2) DEFAULT NULL COMMENT '逾期本金',
  `yqlx` decimal(20,2) DEFAULT NULL COMMENT '逾期利息',
  `yqsxf` decimal(20,2) DEFAULT NULL COMMENT '逾期手续费',
  `znj` decimal(20,2) DEFAULT NULL COMMENT '滞纳金',
  `qdmc` varchar(64) DEFAULT NULL COMMENT '渠道名称',
  `wbsb` varchar(10) DEFAULT NULL COMMENT '外包手别',
  `wbqs` varchar(10) DEFAULT NULL COMMENT '外包期数',
  `fpsj` datetime DEFAULT NULL COMMENT '分配时间',
  `ajhssj` datetime DEFAULT NULL COMMENT '案件回收时间',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `package_flag` char(1) DEFAULT NULL COMMENT '打包标识(0,未打包，1,已打包)',
  `create_by` varchar(32) DEFAULT NULL COMMENT '导入人',
  `create_time` datetime DEFAULT NULL COMMENT '导入时间',
  `Expand1` varchar(32) DEFAULT NULL COMMENT 'Expand1',
  `Expand2` varchar(32) DEFAULT NULL COMMENT 'Expand2',
  `Expand3` varchar(32) DEFAULT NULL COMMENT 'Expand3',
  `Expand4` varchar(32) DEFAULT NULL COMMENT 'Expand4',
  `Expand5` varchar(32) DEFAULT NULL COMMENT 'Expand5',
  `Expand6` varchar(32) DEFAULT NULL COMMENT 'Expand6',
  `Expand7` varchar(32) DEFAULT NULL COMMENT 'Expand7',
  `Expand8` varchar(32) DEFAULT NULL COMMENT 'Expand8',
  `Expand9` varchar(32) DEFAULT NULL COMMENT 'Expand9',
  `Expand10` varchar(32) DEFAULT NULL COMMENT 'Expand10',
  `Expand11` varchar(32) DEFAULT NULL COMMENT 'Expand11',
  `Expand12` varchar(32) DEFAULT NULL COMMENT 'Expand12',
  `Expand13` varchar(32) DEFAULT NULL COMMENT 'Expand13',
  `Expand14` varchar(32) DEFAULT NULL COMMENT 'Expand14',
  `Expand15` varchar(32) DEFAULT NULL COMMENT 'Expand15',
  `Expand16` varchar(32) DEFAULT NULL COMMENT 'Expand16',
  `Expand17` varchar(32) DEFAULT NULL COMMENT 'Expand17',
  `Expand18` varchar(32) DEFAULT NULL COMMENT 'Expand18',
  `Expand19` varchar(32) DEFAULT NULL COMMENT 'Expand19',
  `Expand20` varchar(32) DEFAULT NULL COMMENT 'Expand20',
  `close_case` int(1) DEFAULT NULL COMMENT '结案状态0：未结案 1：已结案',
  `is_exit_collect` int(1) DEFAULT NULL COMMENT '是否出催1：是 2：否',
  `import_batch_no` varchar(20) DEFAULT NULL COMMENT '导入批次号，年月日时分秒生成',
  `close_case_date` datetime DEFAULT NULL COMMENT '结案日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户资产表';

-- ----------------------------
-- Records of cur_assets_package
-- ----------------------------

-- ----------------------------
-- Table structure for cur_assets_repayment_package
-- ----------------------------
DROP TABLE IF EXISTS `cur_assets_repayment_package`;
CREATE TABLE `cur_assets_repayment_package` (
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产还款';

-- ----------------------------
-- Records of cur_assets_repayment_package
-- ----------------------------

-- ----------------------------
-- Table structure for ext_phone
-- ----------------------------
DROP TABLE IF EXISTS `ext_phone`;
CREATE TABLE `ext_phone` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `agentId` varchar(64) DEFAULT NULL COMMENT '分机号码',
  `skillDesc` varchar(64) DEFAULT NULL COMMENT '坐席技能',
  `dialPrefix` varchar(64) DEFAULT NULL COMMENT '外呼前缀',
  `dialCaller` varchar(64) DEFAULT NULL COMMENT '外显号码',
  `proxyUrl` varchar(256) DEFAULT NULL COMMENT '代理地址',
  `isUsed` char(1) DEFAULT NULL COMMENT '是否启用(0,启用，1,停用)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_unique` (`agentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分机号码表';

-- ----------------------------
-- Records of ext_phone
-- ----------------------------

-- ----------------------------
-- Table structure for file_accessories_package
-- ----------------------------
DROP TABLE IF EXISTS `file_accessories_package`;
CREATE TABLE `file_accessories_package` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `file_name` varchar(64) DEFAULT NULL COMMENT '附件名称',
  `file_url` varchar(256) DEFAULT NULL COMMENT '附件url',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `conn_id` varchar(64) DEFAULT NULL COMMENT '关联主键（机构id或资产包id）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件附件表';

-- ----------------------------
-- Records of file_accessories_package
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表';

-- ----------------------------
-- Records of gen_table
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1486 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表字段';

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------

-- ----------------------------
-- Table structure for org_package
-- ----------------------------
DROP TABLE IF EXISTS `org_package`;
CREATE TABLE `org_package` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `org_name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  `org_code` varchar(64) DEFAULT NULL COMMENT '机构编码',
  `start_date` date DEFAULT NULL COMMENT '合作开始时间',
  `end_date` date DEFAULT NULL COMMENT '合作结束时间',
  `org_status` char(1) DEFAULT NULL COMMENT '机构状态',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '服务部门id',
  `dept_name` varchar(128) DEFAULT NULL COMMENT '服务部门名称',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `send_robot` int(11) DEFAULT NULL COMMENT '是否是用机器人1：是2否',
  `auto_start_robot_task` int(255) DEFAULT NULL COMMENT '是否自动开启机器人 任务1：是2否',
  `send_radio_qc` int(255) DEFAULT NULL COMMENT '是否推送到语音质检1：是2否',
  `send_rule_engine` int(11) DEFAULT NULL COMMENT '是否使用规则引擎来智能分案1：是2否',
  `auto_allocat_task` int(11) DEFAULT NULL COMMENT '是否自动分配任务1：是2否',
  `allocat_task_startegy` int(255) DEFAULT NULL COMMENT '分案策略 1：数量平均2金额平均',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目名称，调用语音质检系统使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of org_package
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'LAPTOP-NAMA6C551585720453563', '1585722273777', '15000');
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'localhost1582875201550', '1585722273519', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', null, '1585720460000', '-1', '5', 'PAUSED', 'CRON', '1585720453000', '0', null, '2', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', null, '1585720455000', '-1', '5', 'PAUSED', 'CRON', '1585720453000', '0', null, '2', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', null, '1585720460000', '-1', '5', 'PAUSED', 'CRON', '1585720453000', '0', null, '2', '');

-- ----------------------------
-- Table structure for repayment_templates_package
-- ----------------------------
DROP TABLE IF EXISTS `repayment_templates_package`;
CREATE TABLE `repayment_templates_package` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `name` varchar(64) NOT NULL COMMENT '模板名称',
  `url` varchar(255) NOT NULL COMMENT '模板URL',
  `head_row_num` varchar(32) DEFAULT NULL COMMENT '表头行',
  `data_row_num` varchar(32) DEFAULT NULL COMMENT '数据起始行',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款模板管理';

-- ----------------------------
-- Records of repayment_templates_package
-- ----------------------------

-- ----------------------------
-- Table structure for repayment_template_relation_package
-- ----------------------------
DROP TABLE IF EXISTS `repayment_template_relation_package`;
CREATE TABLE `repayment_template_relation_package` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `system_template_name` varchar(128) DEFAULT NULL COMMENT '系统模板名称',
  `customer_template_name` varchar(128) DEFAULT NULL COMMENT '客户模板名称',
  `template_id` varchar(64) DEFAULT NULL COMMENT '关联模板表id',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款模板关系';

-- ----------------------------
-- Records of repayment_template_relation_package
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES ('3', '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', 'MVP Group', '0', 'MVP', '18012345678', 'wl@mvpgroup.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-03-20 09:57:34');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES ('2', '2', '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES ('3', '3', '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES ('4', '1', '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES ('5', '2', '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES ('6', '1', '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('7', '2', '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('8', '1', '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('9', '2', '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('10', '1', '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '默认分组');
INSERT INTO `sys_dict_data` VALUES ('11', '2', '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统分组');
INSERT INTO `sys_dict_data` VALUES ('12', '1', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES ('13', '2', '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES ('14', '1', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES ('15', '2', '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES ('16', '1', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('17', '2', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES ('18', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES ('19', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES ('20', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES ('21', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES ('22', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES ('23', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES ('24', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES ('25', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES ('26', '9', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES ('27', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('28', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('100', '1', '身份证', '1', 'sys_car_type', '', '', 'Y', '0', 'admin', '2019-12-25 13:40:11', 'admin', '2019-12-25 13:51:27', '身份证');
INSERT INTO `sys_dict_data` VALUES ('101', '2', '护照', '2', 'sys_car_type', '', '', 'Y', '0', 'admin', '2019-12-25 13:40:49', 'admin', '2019-12-25 13:51:37', '护照');
INSERT INTO `sys_dict_data` VALUES ('102', '3', '军官证', '3', 'sys_car_type', '', '', 'Y', '0', 'admin', '2019-12-25 13:41:08', 'admin', '2019-12-25 13:51:47', '军官证');
INSERT INTO `sys_dict_data` VALUES ('103', '4', '港澳台居民往来大陆通行证', '4', 'sys_car_type', '', '', 'Y', '0', 'admin', '2019-12-25 13:41:33', 'admin', '2019-12-25 13:51:55', '港澳台居民往来大陆通行证');
INSERT INTO `sys_dict_data` VALUES ('104', '5', '户口簿', '5', 'sys_car_type', '', '', 'Y', '0', 'admin', '2019-12-25 13:42:19', 'admin', '2019-12-25 13:52:00', '户口簿');
INSERT INTO `sys_dict_data` VALUES ('105', '1', '未分配', '1', 'sys_task_state', null, null, 'Y', '0', 'admin', '2019-12-25 14:57:44', '', null, '未分配');
INSERT INTO `sys_dict_data` VALUES ('106', '2', '已分配', '2', 'sys_task_state', null, null, 'Y', '0', 'admin', '2019-12-25 14:58:20', '', null, '一已分配');
INSERT INTO `sys_dict_data` VALUES ('107', '3', '已结案', '3', 'sys_task_state', null, null, 'Y', '0', 'admin', '2019-12-25 14:58:46', '', null, '已结案');
INSERT INTO `sys_dict_data` VALUES ('108', '1', '初次生成', '1', 'sys_task_type', '', '', 'Y', '0', 'admin', '2019-12-25 15:04:58', 'admin', '2020-01-08 10:38:52', '初次生成');
INSERT INTO `sys_dict_data` VALUES ('109', '2', '重新分派', '2', 'sys_task_type', '', '', 'Y', '0', 'admin', '2019-12-25 15:05:15', 'admin', '2020-01-08 10:39:03', '重新分派');
INSERT INTO `sys_dict_data` VALUES ('110', '1', '承诺还款', '1', 'sys_task_case_status', null, null, 'Y', '0', 'admin', '2019-12-25 15:37:40', '', null, '承诺还款');
INSERT INTO `sys_dict_data` VALUES ('111', '2', '谈判', '2', 'sys_task_case_status', null, null, 'Y', '0', 'admin', '2019-12-25 15:38:03', '', null, '谈判');
INSERT INTO `sys_dict_data` VALUES ('112', '3', '半失连', '3', 'sys_task_case_status', null, null, 'Y', '0', 'admin', '2019-12-25 15:38:23', '', null, '半失连');
INSERT INTO `sys_dict_data` VALUES ('113', '4', '拒绝还款', '4', 'sys_task_case_status', null, null, 'Y', '0', 'admin', '2019-12-25 15:38:43', '', null, '拒绝还款');
INSERT INTO `sys_dict_data` VALUES ('114', '5', '失连', '5', 'sys_task_case_status', null, null, 'Y', '0', 'admin', '2019-12-25 15:39:08', '', null, '失连');
INSERT INTO `sys_dict_data` VALUES ('115', '6', '未处理', '6', 'sys_task_case_status', null, null, 'Y', '0', 'admin', '2019-12-25 15:39:41', '', null, '未处理');
INSERT INTO `sys_dict_data` VALUES ('116', '1', '未分配', '1', 'sys_case_status', '', '', 'Y', '0', 'admin', '2019-12-26 11:53:24', 'admin', '2020-03-15 12:10:41', '未分配');
INSERT INTO `sys_dict_data` VALUES ('117', '3', '已结案', '3', 'sys_case_status', '', '', 'Y', '0', 'admin', '2019-12-26 11:53:40', 'admin', '2020-01-13 13:26:03', '已结案');
INSERT INTO `sys_dict_data` VALUES ('118', '1', '本人', '1', 'sys_custom_contact_rela', '', '', 'Y', '0', 'admin', '2019-12-31 18:04:31', 'admin', '2019-12-31 18:05:16', '本人');
INSERT INTO `sys_dict_data` VALUES ('119', '2', '直系', '2', 'sys_custom_contact_rela', null, null, 'Y', '0', 'admin', '2019-12-31 18:04:45', '', null, '直系');
INSERT INTO `sys_dict_data` VALUES ('120', '3', '亲戚', '3', 'sys_custom_contact_rela', null, null, 'Y', '0', 'admin', '2019-12-31 18:04:57', '', null, '亲戚');
INSERT INTO `sys_dict_data` VALUES ('121', '4', '朋友', '4', 'sys_custom_contact_rela', null, null, 'Y', '0', 'admin', '2019-12-31 18:05:09', '', null, '朋友');
INSERT INTO `sys_dict_data` VALUES ('122', '1', '承诺还款', '1', 'sys_call_sign', null, null, 'Y', '0', 'admin', '2019-12-31 18:12:29', '', null, '承诺还款');
INSERT INTO `sys_dict_data` VALUES ('123', '2', '谈判', '2', 'sys_call_sign', null, null, 'Y', '0', 'admin', '2019-12-31 18:12:42', '', null, '谈判');
INSERT INTO `sys_dict_data` VALUES ('124', '3', '半失连', '3', 'sys_call_sign', null, null, 'Y', '0', 'admin', '2019-12-31 18:12:55', '', null, '半失连');
INSERT INTO `sys_dict_data` VALUES ('125', '4', '拒绝还款', '4', 'sys_call_sign', null, null, 'Y', '0', 'admin', '2019-12-31 18:13:12', '', null, '拒绝还款');
INSERT INTO `sys_dict_data` VALUES ('126', '5', '失连', '5', 'sys_call_sign', null, null, 'Y', '0', 'admin', '2019-12-31 18:13:24', '', null, '失连');
INSERT INTO `sys_dict_data` VALUES ('127', '3', '临时代理', '3', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-01-08 10:39:20', '', null, '临时代理');
INSERT INTO `sys_dict_data` VALUES ('128', '4', '协助催收', '4', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-01-08 10:39:36', '', null, '协助催收');
INSERT INTO `sys_dict_data` VALUES ('129', '5', '临时代理回收', '5', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-01-08 10:39:50', '', null, '临时代理回收');
INSERT INTO `sys_dict_data` VALUES ('130', '1', '电催', '1', 'sys_coll_type', null, null, 'Y', '0', 'admin', '2020-01-09 14:03:11', '', null, '电催');
INSERT INTO `sys_dict_data` VALUES ('131', '2', '委外', '2', 'sys_coll_type', null, null, 'Y', '0', 'admin', '2020-01-09 14:03:23', '', null, '委外');
INSERT INTO `sys_dict_data` VALUES ('132', '3', '机器人', '3', 'sys_coll_type', null, null, 'Y', '0', 'admin', '2020-01-09 14:03:39', '', null, '机器人');
INSERT INTO `sys_dict_data` VALUES ('133', '4', '法催', '4', 'sys_coll_type', null, null, 'Y', '0', 'admin', '2020-01-09 14:03:50', '', null, '法催');
INSERT INTO `sys_dict_data` VALUES ('134', '7', '结案转移', '7', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-03-02 10:33:33', '', null, '结案转移');
INSERT INTO `sys_dict_data` VALUES ('135', '10', '协助催收申请', '10', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-03-02 10:33:52', '', null, '协助催收申请');
INSERT INTO `sys_dict_data` VALUES ('136', '11', '停催申请', '11', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-03-02 10:34:21', '', null, '停催申请');
INSERT INTO `sys_dict_data` VALUES ('137', '12', '停止催收', '12', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-03-02 10:34:37', '', null, '停止催收');
INSERT INTO `sys_dict_data` VALUES ('138', '13', '停止催收激活', '13', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-03-02 10:34:56', '', null, '停止催收激活');
INSERT INTO `sys_dict_data` VALUES ('139', '14', '停止催收拒绝', '14', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-03-02 10:35:10', '', null, '停止催收拒绝');
INSERT INTO `sys_dict_data` VALUES ('140', '15', '拒绝协催', '15', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-03-02 10:35:23', '', null, '拒绝协催');
INSERT INTO `sys_dict_data` VALUES ('141', '16', '机器人协催', '16', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-03-02 10:35:37', '', null, '机器人协催');
INSERT INTO `sys_dict_data` VALUES ('142', '17', '机器人拉回', '17', 'sys_task_type', null, null, 'Y', '0', 'admin', '2020-03-02 10:35:50', '', null, '机器人拉回');
INSERT INTO `sys_dict_data` VALUES ('144', '1', '中银消金', 'ZY', 'auto_send_quality_check', null, null, 'Y', '0', 'admin', '2020-03-10 18:19:07', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('145', '1', 'A', 'A', 'sys_user_group', null, null, 'Y', '0', 'admin', '2020-03-13 17:39:15', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('146', '2', 'B', 'B', 'sys_user_group', null, null, 'Y', '0', 'admin', '2020-03-13 17:39:30', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('147', '3', 'C', 'C', 'sys_user_group', null, null, 'Y', '0', 'admin', '2020-03-13 17:39:37', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('148', '4', 'D', 'D', 'sys_user_group', null, null, 'Y', '0', 'admin', '2020-03-13 17:39:46', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('149', '5', 'E', 'E', 'sys_user_group', null, null, 'Y', '0', 'admin', '2020-03-13 17:39:55', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('152', '2', '已分配', '2', 'sys_case_status', null, null, 'Y', '0', 'admin', '2020-03-15 12:10:55', '', null, '已分配');
INSERT INTO `sys_dict_data` VALUES ('155', '1', '2', '1', 'call_interval_time', '', '', 'Y', '0', 'admin', '2020-03-16 11:32:45', 'admin', '2020-03-16 14:37:56', '间隔2小时');
INSERT INTO `sys_dict_data` VALUES ('156', '2', '3', '2', 'call_interval_time', '', '', 'Y', '0', 'admin', '2020-03-16 11:33:01', 'admin', '2020-03-16 14:38:01', '间隔3小时');
INSERT INTO `sys_dict_data` VALUES ('173', '1', '承诺还款', 'PTP', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:44:31', '', null, '承诺还款');
INSERT INTO `sys_dict_data` VALUES ('174', '2', '称已还', 'CYH', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:44:55', '', null, '称已还');
INSERT INTO `sys_dict_data` VALUES ('175', '3', '谈判沟通', 'TP', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:45:15', '', null, '谈判沟通');
INSERT INTO `sys_dict_data` VALUES ('176', '4', '无诚意', 'WCY', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:45:33', '', null, '无诚意');
INSERT INTO `sys_dict_data` VALUES ('177', '5', '还款困难', 'HKKN', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:45:49', '', null, '还款困难');
INSERT INTO `sys_dict_data` VALUES ('178', '6', '转告', 'ZG', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:46:07', '', null, '转告');
INSERT INTO `sys_dict_data` VALUES ('179', '7', '无人接听', 'WRJT', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:46:31', '', null, '无人接听');
INSERT INTO `sys_dict_data` VALUES ('180', '8', '拒接', 'JJ', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:46:48', '', null, '拒接');
INSERT INTO `sys_dict_data` VALUES ('181', '9', '占线忙音', 'ZX', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:47:07', '', null, '占线忙音');
INSERT INTO `sys_dict_data` VALUES ('182', '10', '关机', 'GJ', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:47:24', '', null, '关机');
INSERT INTO `sys_dict_data` VALUES ('183', '11', '挂断', 'GD', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:47:41', '', null, '挂断');
INSERT INTO `sys_dict_data` VALUES ('184', '12', '空号', 'KH', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:47:55', '', null, '空号');
INSERT INTO `sys_dict_data` VALUES ('185', '13', '不在', 'BZ', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:48:12', '', null, '不在');
INSERT INTO `sys_dict_data` VALUES ('186', '15', '无此人', 'WCR', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:48:28', '', null, '无此人');
INSERT INTO `sys_dict_data` VALUES ('187', '15', '无回应', 'WHY', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:48:43', '', null, '无回应');
INSERT INTO `sys_dict_data` VALUES ('188', '16', '失联', 'SL', 'call_record_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:49:01', '', null, '失联');
INSERT INTO `sys_dict_data` VALUES ('189', '1', '已还款', 'ALPA', 'action_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:49:45', '', null, '已还款');
INSERT INTO `sys_dict_data` VALUES ('190', '2', '承诺还款', 'CNHK', 'action_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:49:58', '', null, '承诺还款');
INSERT INTO `sys_dict_data` VALUES ('191', '3', '暂停跟进', 'ZTGJ', 'action_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:50:11', '', null, '暂停跟进');
INSERT INTO `sys_dict_data` VALUES ('192', '4', '投诉', 'TS', 'action_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:50:27', '', null, '投诉');
INSERT INTO `sys_dict_data` VALUES ('193', '5', '未达成还款', 'NXT', 'action_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:50:46', '', null, '未达成还款');
INSERT INTO `sys_dict_data` VALUES ('194', '6', '答应转告', 'DYZG', 'action_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:51:04', '', null, '答应转告');
INSERT INTO `sys_dict_data` VALUES ('195', '7', '再次联系', 'ZCLX', 'action_code', null, null, 'Y', '0', 'admin', '2020-03-25 11:51:17', '', null, '再次联系');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES ('2', '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES ('3', '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES ('4', '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES ('5', '任务分组', 'sys_job_group', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES ('6', '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES ('7', '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES ('8', '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES ('9', '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES ('10', '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');
INSERT INTO `sys_dict_type` VALUES ('100', '证件类型', 'sys_car_type', '0', 'admin', '2019-12-25 13:37:52', 'admin', '2019-12-25 13:38:04', '证件类型列表');
INSERT INTO `sys_dict_type` VALUES ('101', '任务状态', 'sys_task_state', '0', 'admin', '2019-12-25 14:04:44', '', null, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES ('102', '任务分类', 'sys_task_type', '0', 'admin', '2019-12-25 15:04:37', '', null, '任务分类列表');
INSERT INTO `sys_dict_type` VALUES ('103', '任务标记', 'sys_task_case_status', '0', 'admin', '2019-12-25 15:37:10', '', null, '任务标记列表');
INSERT INTO `sys_dict_type` VALUES ('104', '案件状态', 'sys_case_status', '0', 'admin', '2019-12-26 11:52:58', '', null, '案件状态列表');
INSERT INTO `sys_dict_type` VALUES ('105', '客户联系人关系列表', 'sys_custom_contact_rela', '0', 'admin', '2019-12-31 18:04:01', '', null, '客户联系人关系列表');
INSERT INTO `sys_dict_type` VALUES ('106', '通话结果标记列表', 'sys_call_sign', '0', 'admin', '2019-12-31 18:12:13', '', null, '通话标记');
INSERT INTO `sys_dict_type` VALUES ('107', '催收方式列表', 'sys_coll_type', '0', 'admin', '2020-01-09 14:02:35', '', null, '催收方式列表');
INSERT INTO `sys_dict_type` VALUES ('111', '用户组别', 'sys_user_group', '0', 'admin', '2020-03-13 17:38:51', '', null, '用户组别');
INSERT INTO `sys_dict_type` VALUES ('112', '电话码', 'call_record_code', '0', 'admin', '2020-03-14 18:13:07', '', null, '电话码');
INSERT INTO `sys_dict_type` VALUES ('114', '通话间隔时间', 'call_interval_time', '0', 'admin', '2020-03-16 11:32:23', '', null, '通话间隔时间 以小时为单位');
INSERT INTO `sys_dict_type` VALUES ('115', '行动码', 'action_code', '0', 'admin', '2020-03-25 11:49:29', '', null, '行动码');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES ('2', '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES ('3', '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=545 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2586 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2086 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', '#', '', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', '#', '', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', '#', '', 'M', '1', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统工具目录');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', '/system/user', '', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', '/system/role', '', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', '/system/menu', '', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', '/system/dept', '', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', '/system/post', '', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', '/system/dict', '', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', '/system/config', '', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', '/system/notice', '', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', '#', '', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', '/monitor/online', '', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', '/monitor/job', '', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', '/monitor/data', '', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', '3', '/monitor/server', '', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES ('113', '表单构建', '3', '1', '/tool/build', '', 'C', '1', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('114', '代码生成', '3', '2', '/tool/gen', '', 'C', '1', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES ('115', '系统接口', '3', '3', '/tool/swagger', '', 'C', '1', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', '/monitor/operlog', '', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('1000', '用户查询', '100', '1', '#', '', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '100', '2', '#', '', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1002', '用户修改', '100', '3', '#', '', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1003', '用户删除', '100', '4', '#', '', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1004', '用户导出', '100', '5', '#', '', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1005', '用户导入', '100', '6', '#', '', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1006', '重置密码', '100', '7', '#', '', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1007', '角色查询', '101', '1', '#', '', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1008', '角色新增', '101', '2', '#', '', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1009', '角色修改', '101', '3', '#', '', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1010', '角色删除', '101', '4', '#', '', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1011', '角色导出', '101', '5', '#', '', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1012', '菜单查询', '102', '1', '#', '', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1013', '菜单新增', '102', '2', '#', '', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1014', '菜单修改', '102', '3', '#', '', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1015', '菜单删除', '102', '4', '#', '', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1016', '部门查询', '103', '1', '#', '', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1017', '部门新增', '103', '2', '#', '', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1018', '部门修改', '103', '3', '#', '', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1019', '部门删除', '103', '4', '#', '', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1020', '岗位查询', '104', '1', '#', '', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1021', '岗位新增', '104', '2', '#', '', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1022', '岗位修改', '104', '3', '#', '', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1023', '岗位删除', '104', '4', '#', '', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1024', '岗位导出', '104', '5', '#', '', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1025', '字典查询', '105', '1', '#', '', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1026', '字典新增', '105', '2', '#', '', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1027', '字典修改', '105', '3', '#', '', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1028', '字典删除', '105', '4', '#', '', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1029', '字典导出', '105', '5', '#', '', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1030', '参数查询', '106', '1', '#', '', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1031', '参数新增', '106', '2', '#', '', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1032', '参数修改', '106', '3', '#', '', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1033', '参数删除', '106', '4', '#', '', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1034', '参数导出', '106', '5', '#', '', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1035', '公告查询', '107', '1', '#', '', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1036', '公告新增', '107', '2', '#', '', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1037', '公告修改', '107', '3', '#', '', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1038', '公告删除', '107', '4', '#', '', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1039', '操作查询', '500', '1', '#', '', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1040', '操作删除', '500', '2', '#', '', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1041', '详细信息', '500', '3', '#', '', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1042', '日志导出', '500', '4', '#', '', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', '1', '#', '', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', '2', '#', '', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1045', '日志导出', '501', '3', '#', '', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1046', '账户解锁', '501', '4', '#', '', 'F', '0', 'monitor:logininfor:unlock', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1047', '在线查询', '109', '1', '#', '', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1048', '批量强退', '109', '2', '#', '', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1049', '单条强退', '109', '3', '#', '', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1050', '任务查询', '110', '1', '#', '', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1051', '任务新增', '110', '2', '#', '', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1052', '任务修改', '110', '3', '#', '', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1053', '任务删除', '110', '4', '#', '', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1054', '状态修改', '110', '5', '#', '', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1055', '任务详细', '110', '6', '#', '', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1056', '任务导出', '110', '7', '#', '', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1057', '生成查询', '114', '1', '#', '', 'F', '1', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1058', '生成修改', '114', '2', '#', '', 'F', '1', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1059', '生成删除', '114', '3', '#', '', 'F', '1', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1060', '预览代码', '114', '4', '#', '', 'F', '1', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1061', '生成代码', '114', '5', '#', '', 'F', '1', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('2000', '资产管理', '0', '5', '#', 'menuItem', 'M', '0', '', 'glyphicon glyphicon-yen', 'admin', '2019-12-25 10:33:32', 'admin', '2020-01-09 15:39:52', '');
INSERT INTO `sys_menu` VALUES ('2001', '催收管理', '0', '6', '#', 'menuItem', 'M', '0', '', 'fa fa-bell', 'admin', '2019-12-25 10:34:39', 'admin', '2019-12-27 18:28:36', '');
INSERT INTO `sys_menu` VALUES ('2002', '资产导入', '2036', '1', '/import/assets', 'menuItem', 'C', '0', 'import:assets:view', '#', 'admin', '2019-12-25 10:37:20', 'admin', '2020-01-22 13:31:40', '');
INSERT INTO `sys_menu` VALUES ('2003', '任务管理', '2001', '2', '/collect/task/view', 'menuItem', 'C', '0', 'collect:task:view', 'fa fa-check', 'admin', '2019-12-25 10:38:32', 'admin', '2020-01-19 09:15:13', '');
INSERT INTO `sys_menu` VALUES ('2004', '查询', '2002', '1', '#', 'menuItem', 'F', '0', 'import:assets:list', '#', 'admin', '2019-12-25 15:54:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('2006', '案件综合查询', '2001', '12', '/collect/duncase/view', 'menuItem', 'C', '0', 'collect:duncase:view', '#', 'admin', '2019-12-25 17:08:52', 'admin', '2020-01-19 09:04:11', '');
INSERT INTO `sys_menu` VALUES ('2007', '新增', '2002', '2', '#', 'menuItem', 'F', '0', 'import:assets:add', '#', 'admin', '2019-12-25 17:56:35', '', null, '');
INSERT INTO `sys_menu` VALUES ('2008', '修改', '2002', '3', '#', 'menuItem', 'F', '0', 'import:assets:edit', '#', 'admin', '2019-12-25 17:57:38', '', null, '');
INSERT INTO `sys_menu` VALUES ('2009', '删除', '2002', '4', '#', 'menuItem', 'F', '0', 'import:assets:remove', '#', 'admin', '2019-12-25 17:58:13', '', null, '');
INSERT INTO `sys_menu` VALUES ('2011', '委托方管理', '2000', '1', '/assetspackage/org', 'menuItem', 'C', '0', 'assetspackage:org:view', '#', 'admin', '2019-12-27 15:22:38', 'admin', '2020-01-06 18:59:13', '');
INSERT INTO `sys_menu` VALUES ('2012', '新增', '2011', '2', '#', 'menuItem', 'F', '0', 'assetspackage:org:add', '#', 'admin', '2019-12-27 15:25:53', 'admin', '2019-12-27 15:26:18', '');
INSERT INTO `sys_menu` VALUES ('2013', '查询', '2011', '1', '#', 'menuItem', 'F', '0', 'assetspackage:org:list', '#', 'admin', '2019-12-27 15:27:04', '', null, '');
INSERT INTO `sys_menu` VALUES ('2014', '修改', '2011', '3', '#', 'menuItem', 'F', '0', 'assetspackage:org:edit', '#', 'admin', '2019-12-27 15:27:43', '', null, '');
INSERT INTO `sys_menu` VALUES ('2015', '删除', '2011', '4', '#', 'menuItem', 'F', '0', 'assetspackage:org:remove', '#', 'admin', '2019-12-27 15:28:16', '', null, '');
INSERT INTO `sys_menu` VALUES ('2016', '导出', '2011', '5', '#', 'menuItem', 'F', '0', 'assetspackage:org:export', '#', 'admin', '2019-12-27 15:28:49', '', null, '');
INSERT INTO `sys_menu` VALUES ('2019', '模板管理', '2000', '2', '/assetspackage/template', 'menuItem', 'C', '0', 'assetspackage:template:view', '#', 'admin', '2020-01-02 10:07:30', 'admin', '2020-01-22 13:41:10', '');
INSERT INTO `sys_menu` VALUES ('2020', '导入', '2002', '5', '#', 'menuItem', 'F', '0', 'import:assets:upload', '#', 'admin', '2020-01-03 14:43:37', '', null, '');
INSERT INTO `sys_menu` VALUES ('2021', '导出', '2002', '6', '#', 'menuItem', 'F', '0', 'import:assets:export', '#', 'admin', '2020-01-03 14:44:11', '', null, '');
INSERT INTO `sys_menu` VALUES ('2022', '新增', '2019', '2', '#', 'menuItem', 'F', '0', 'assetspackage:template:add', '#', 'admin', '2020-01-03 14:59:37', 'admin', '2020-01-03 15:01:56', '');
INSERT INTO `sys_menu` VALUES ('2023', '修改', '2019', '3', '#', 'menuItem', 'F', '0', 'assetspackage:template:edit', '#', 'admin', '2020-01-03 15:00:19', 'admin', '2020-01-03 15:01:11', '');
INSERT INTO `sys_menu` VALUES ('2024', '删除', '2019', '4', '#', 'menuItem', 'F', '0', 'assetspackage:template:remove', '#', 'admin', '2020-01-03 15:01:00', '', null, '');
INSERT INTO `sys_menu` VALUES ('2025', '查询', '2019', '1', '#', 'menuItem', 'F', '0', 'assetspackage:template:list', '#', 'admin', '2020-01-03 15:02:38', '', null, '');
INSERT INTO `sys_menu` VALUES ('2026', '资产包管理', '2000', '4', '/assetspackage/package', 'menuItem', 'C', '0', 'assetspackage:package:view', '#', 'admin', '2020-01-06 15:44:45', 'admin', '2020-01-22 13:33:02', '');
INSERT INTO `sys_menu` VALUES ('2027', '查询', '2026', '1', '#', 'menuItem', 'F', '0', 'assetspackage:package:list', '#', 'admin', '2020-01-06 15:45:31', '', null, '');
INSERT INTO `sys_menu` VALUES ('2028', '新增', '2026', '2', '#', 'menuItem', 'F', '0', 'assetspackage:package:add', '#', 'admin', '2020-01-06 15:46:16', '', null, '');
INSERT INTO `sys_menu` VALUES ('2029', '修改', '2026', '3', '#', 'menuItem', 'F', '0', 'assetspackage:package:edit', '#', 'admin', '2020-01-06 15:46:38', '', null, '');
INSERT INTO `sys_menu` VALUES ('2030', '删除', '2026', '4', '#', 'menuItem', 'F', '0', 'assetspackage:package:remove', '#', 'admin', '2020-01-06 15:47:02', '', null, '');
INSERT INTO `sys_menu` VALUES ('2031', '导出', '2026', '5', '#', 'menuItem', 'F', '0', 'assetspackage:package:export', '#', 'admin', '2020-01-06 16:06:59', '', null, '');
INSERT INTO `sys_menu` VALUES ('2032', '组包', '2026', '6', '#', 'menuItem', 'F', '0', 'assetspackage:package:assemble', '#', 'admin', '2020-01-09 09:15:55', '', null, '');
INSERT INTO `sys_menu` VALUES ('2036', '资产导入管理', '2000', '3', '#', 'menuItem', 'M', '0', '', '#', 'admin', '2020-01-13 09:00:06', 'admin', '2020-01-16 17:20:13', '');
INSERT INTO `sys_menu` VALUES ('2037', '还款导入管理', '2000', '7', '#', 'menuItem', 'M', '1', '', '#', 'admin', '2020-01-13 10:31:04', 'admin', '2020-01-22 13:40:38', '');
INSERT INTO `sys_menu` VALUES ('2038', '还款模板管理', '2000', '8', '/assetspackage/repaymentTemplate', 'menuItem', 'C', '1', 'assetspackage:repaymentTemplate:view', '#', 'admin', '2020-01-13 10:52:59', 'admin', '2020-01-22 13:38:29', '');
INSERT INTO `sys_menu` VALUES ('2039', '查询', '2038', '1', '#', 'menuItem', 'F', '0', 'assetspackage:repaymentTemplate:list', '#', 'admin', '2020-01-13 10:53:47', '', null, '');
INSERT INTO `sys_menu` VALUES ('2040', '新增', '2038', '2', '#', 'menuItem', 'F', '0', 'assetspackage:repaymentTemplate:add', '#', 'admin', '2020-01-13 10:55:11', '', null, '');
INSERT INTO `sys_menu` VALUES ('2041', '修改', '2038', '3', '#', 'menuItem', 'F', '0', 'assetspackage:repaymentTemplate:edit', '#', 'admin', '2020-01-13 10:55:44', '', null, '');
INSERT INTO `sys_menu` VALUES ('2042', '删除', '2038', '4', '#', 'menuItem', 'F', '0', 'assetspackage:repaymentTemplate:remove', '#', 'admin', '2020-01-13 10:56:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('2043', '还款导入', '2036', '2', '/assetspackage/repaymentImport', 'menuItem', 'C', '0', 'assetspackage:repaymentImport:view', '#', 'admin', '2020-01-13 15:21:34', 'admin', '2020-01-22 13:31:05', '');
INSERT INTO `sys_menu` VALUES ('2044', '查询', '2043', '1', '#', 'menuItem', 'F', '0', 'assetspackage:repaymentImport:list', '#', 'admin', '2020-01-13 15:22:03', '', null, '');
INSERT INTO `sys_menu` VALUES ('2045', '新增', '2043', '2', '#', 'menuItem', 'F', '0', 'assetspackage:repaymentImport:add', '#', 'admin', '2020-01-13 15:23:26', '', null, '');
INSERT INTO `sys_menu` VALUES ('2046', '修改', '2043', '3', '#', 'menuItem', 'F', '0', 'ssetspackage:repaymentImport:edit', '#', 'admin', '2020-01-13 15:23:53', '', null, '');
INSERT INTO `sys_menu` VALUES ('2047', '删除', '2043', '4', '#', 'menuItem', 'F', '0', 'assetspackage:repaymentImport:remove', '#', 'admin', '2020-01-13 15:24:24', '', null, '');
INSERT INTO `sys_menu` VALUES ('2048', '导入', '2043', '5', '#', 'menuItem', 'F', '0', 'assetspackage:repaymentImport:import', '#', 'admin', '2020-01-13 15:25:27', 'admin', '2020-01-14 17:13:46', '');
INSERT INTO `sys_menu` VALUES ('2049', '导出', '2043', '6', '#', 'menuItem', 'F', '0', 'assetspackage:repaymentImport:export', '#', 'admin', '2020-01-13 15:25:49', 'admin', '2020-01-14 17:14:22', '');
INSERT INTO `sys_menu` VALUES ('2062', '模板管理目录', '2000', '6', '#', 'menuItem', 'M', '1', '', '#', 'admin', '2020-01-16 17:17:55', 'admin', '2020-01-22 13:40:27', '');
INSERT INTO `sys_menu` VALUES ('2063', '我的任务', '2001', '1', '/collect/task/myTask', 'menuItem', 'C', '0', 'collect:task:myTask', '#', 'admin', '2020-01-19 09:11:41', '', null, '');
INSERT INTO `sys_menu` VALUES ('2064', '协催审批', '2001', '3', '/collect/task/helpCollApprove', 'menuItem', 'C', '0', 'collect:task:helpCollApprove', '#', 'admin', '2020-01-19 09:13:41', '', null, '');
INSERT INTO `sys_menu` VALUES ('2065', '停催管理', '2001', '4', '/collect/task/stopColl', 'menuItem', 'M', '0', 'collect:task:stopColl', '#', 'admin', '2020-01-19 09:17:00', 'admin', '2020-01-21 17:10:46', '');
INSERT INTO `sys_menu` VALUES ('2066', '我的任务查询', '2063', '1', '#', '', 'F', '0', 'collect:task:myTaskList', '#', 'admin', '2020-01-19 10:17:25', 'ry', '2020-01-19 10:17:33', '');
INSERT INTO `sys_menu` VALUES ('2067', '上传', '2019', '5', '#', 'menuItem', 'F', '0', 'assetspackage:template:upload', '#', 'admin', '2020-01-20 14:48:59', '', null, '');
INSERT INTO `sys_menu` VALUES ('2068', '停催审批', '2065', '1', '/collect/task/stopColl?type=1', 'menuItem', 'C', '0', 'collect:task:stopColl', '#', 'admin', '2020-01-21 17:13:54', 'admin', '2020-01-21 17:18:29', '');
INSERT INTO `sys_menu` VALUES ('2069', '停催激活', '2065', '2', '/collect/task/stopColl?type=2', 'menuItem', 'C', '0', 'collect:task:stopColl', '#', 'admin', '2020-01-21 17:14:50', '', null, '');
INSERT INTO `sys_menu` VALUES ('2070', '查看导入流水', '2036', '3', '/assetspackage/stream', 'menuItem', 'C', '1', 'assetspackage:stream:view', '#', 'admin', '2020-01-22 13:44:04', 'admin', '2020-03-24 10:42:30', '');
INSERT INTO `sys_menu` VALUES ('2071', '查询', '2070', '1', '#', 'menuItem', 'F', '0', 'assetspackage:stream:list', '#', 'admin', '2020-01-22 13:44:49', '', null, '');
INSERT INTO `sys_menu` VALUES ('2072', '导出', '2070', '2', '#', 'menuItem', 'F', '0', 'assetspackage:stream:export', '#', 'admin', '2020-01-22 13:45:45', '', null, '');
INSERT INTO `sys_menu` VALUES ('2073', '呼叫策略配置', '2001', '15', '/call/config', 'menuItem', 'C', '0', 'call:config:view', '#', 'admin', '2020-02-06 19:03:04', '', null, '');
INSERT INTO `sys_menu` VALUES ('2074', '综合信息查询', '2000', '5', '/assetspackage/info', 'menuItem', 'C', '0', 'assetspackage:info:view', '#', 'admin', '2020-02-06 21:46:13', 'admin', '2020-02-06 21:46:40', '');
INSERT INTO `sys_menu` VALUES ('2076', '机器人任务管理', '2001', '17', '/collect/robot/view', 'menuItem', 'C', '0', 'collect:robot:view', '#', 'admin', '2020-02-10 14:46:38', 'admin', '2020-02-10 15:02:32', '');
INSERT INTO `sys_menu` VALUES ('2078', '分机号码管理', '2001', '18', '/agent/phone', 'menuItem', 'C', '0', 'agent:phone:view', '#', 'admin', '2020-03-03 09:16:55', '', null, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` mediumblob COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1922 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1', '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `user_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户组别',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '100', 'admin', '管理员', '00', 'ry@163.com', '15888888888', '1', '/profile/avatar/2020/03/18/38187e01d307ad33b42102e53de97153.png', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', '2020-04-01 13:54:29', 'admin', '2018-03-16 11:33:00', 'ry', '2020-04-01 13:54:29', '管理员', null);

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='在线用户记录';

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('fdc80254-c911-4c3e-85de-5fb2f03a327e', 'admin', '甲方01-项目组', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', 'on_line', '2020-04-01 13:54:26', '2020-04-01 14:24:08', '1800000');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for templates_package
-- ----------------------------
DROP TABLE IF EXISTS `templates_package`;
CREATE TABLE `templates_package` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `name` varchar(64) NOT NULL COMMENT '模板名称',
  `url` varchar(255) NOT NULL COMMENT '模板URL',
  `head_row_num` varchar(32) DEFAULT NULL COMMENT '表头行',
  `data_row_num` varchar(32) DEFAULT NULL COMMENT '数据起始行',
  `type` char(1) DEFAULT NULL COMMENT '模板类型(1,资产，2,还款)',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  `delflag` char(1) DEFAULT NULL COMMENT '删除标识（0,未删除，1,已删除）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_unique` (`name`,`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板管理';

-- ----------------------------
-- Records of templates_package
-- ----------------------------

-- ----------------------------
-- Table structure for template_relation_package
-- ----------------------------
DROP TABLE IF EXISTS `template_relation_package`;
CREATE TABLE `template_relation_package` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `system_template_name` varchar(128) DEFAULT NULL COMMENT '系统模板名称',
  `customer_template_name` varchar(128) DEFAULT NULL COMMENT '客户模板名称',
  `template_id` varchar(64) DEFAULT NULL COMMENT '关联模板表id',
  `org_id` varchar(64) DEFAULT NULL COMMENT '委托方id',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板关系';

-- ----------------------------
-- Records of template_relation_package
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_call_record
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_call_record`;
CREATE TABLE `t_lc_call_record` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `certificate_no` varchar(30) NOT NULL COMMENT '客户证件号码',
  `contact_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人姓名',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `contact_relation` int(11) DEFAULT NULL COMMENT '和本人关系 1：本人，2：',
  `call_start_time` datetime DEFAULT NULL COMMENT '通话开始时间',
  `call_end_time` datetime DEFAULT NULL COMMENT '通话结束时间',
  `call_len` varchar(19) DEFAULT NULL COMMENT '通话时长',
  `call_sign` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通话结果标记 1:承诺还款 2:谈判 3:半失连 4:拒绝还款 5:失连',
  `call_result` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通话结果',
  `call_radio_location` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通话录音存放位置',
  `call_radio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通话录音',
  `create_time` datetime(6) DEFAULT NULL COMMENT '插入时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `case_no` varchar(255) DEFAULT NULL COMMENT '案件编号',
  `remark` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8 COMMENT='通话结果记录表';

-- ----------------------------
-- Records of t_lc_call_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_call_strategy_config
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_call_strategy_config`;
CREATE TABLE `t_lc_call_strategy_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `business_scene` int(11) DEFAULT NULL COMMENT '业务场景 1：新案；2：协催；3：旧案',
  `call_frequency_day` int(11) DEFAULT NULL COMMENT '每天呼叫次数',
  `continue_call_days` int(11) DEFAULT NULL COMMENT '连续呼叫天数',
  `stop_call_cur_day_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '当天停止呼叫条件',
  `stop_call_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '停止呼叫任务条件',
  `start_call_date` timestamp NULL DEFAULT NULL COMMENT '呼叫起始时间',
  `stop_call_date` timestamp NULL DEFAULT NULL COMMENT '停止呼叫时间',
  `status` int(1) DEFAULT NULL COMMENT '是否启用 0：不生效，1：生效',
  `org_id` varchar(4) DEFAULT NULL COMMENT '所属机构id',
  `org_name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `speechcraft_id` int(4) DEFAULT NULL COMMENT '机器人话术 id',
  `speechcraft_name` varchar(255) DEFAULT NULL COMMENT '机器人话术列表',
  `call_interval_time_id` varchar(2) DEFAULT NULL COMMENT '通话间隔时间',
  `call_interval_time` varchar(2) DEFAULT NULL,
  `call_line_id` varchar(11) DEFAULT NULL COMMENT '呼叫线路id，多条线路id用逗号分隔',
  `call_line_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '呼叫线路名称',
  `scene_def_id` int(11) DEFAULT NULL COMMENT '场景id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lc_call_strategy_config
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_custinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_custinfo`;
CREATE TABLE `t_lc_custinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `custom_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户编号',
  `custom_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户姓名',
  `custom_sex` int(11) DEFAULT NULL COMMENT '客户性别 0：男1：女',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `certificate_type` int(11) DEFAULT NULL COMMENT '证件类型',
  `certificate_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证件编号',
  `certificate_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证件地址',
  `profession` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职业',
  `city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所在城市',
  `education` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `tel` varchar(50) DEFAULT NULL COMMENT '家庭电话',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮件',
  `income_year` decimal(19,2) DEFAULT NULL COMMENT '年收入',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现住址',
  `marrage_status` int(11) DEFAULT NULL COMMENT '是否已婚 1：是，2：否',
  `has_child` int(11) DEFAULT NULL COMMENT '是否有孩 1：是，2：否',
  `has_house` int(11) DEFAULT NULL COMMENT '是否有房 1：是，2：否',
  `has_car` int(11) DEFAULT NULL COMMENT '是否有车 1：是，2：否',
  `validate_status` int(11) DEFAULT '1' COMMENT '是否有效 1：是，2：否',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `org_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属机构',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  `census_address` varchar(255) DEFAULT NULL COMMENT '户籍地址',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案件编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=286 DEFAULT CHARSET=utf8 COMMENT='客户信息表';

-- ----------------------------
-- Records of t_lc_custinfo
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_cust_contact
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_cust_contact`;
CREATE TABLE `t_lc_cust_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `certificate_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户证件号码',
  `contact_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人姓名',
  `relation` int(100) DEFAULT NULL COMMENT '和本人关系 1：本人，2：直系，3：亲戚，4：朋友',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `tel` varchar(50) DEFAULT NULL COMMENT '固定电话',
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人家庭地址',
  `origin` int(11) DEFAULT NULL COMMENT '联系人数据来源0：资产导入1：页面添加',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `validate_status` int(11) DEFAULT '1' COMMENT '是否有效 1：是，2：否',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案件编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=904 DEFAULT CHARSET=utf8 COMMENT='客户联系人信息表';

-- ----------------------------
-- Records of t_lc_cust_contact
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_cust_job
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_cust_job`;
CREATE TABLE `t_lc_cust_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `custom_code` varchar(100) DEFAULT NULL COMMENT '客户编号',
  `certificate_no` varchar(255) DEFAULT NULL COMMENT '客户身份证号',
  `profession` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职业',
  `company_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位名称',
  `company_tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位电话',
  `company_address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位地址',
  `company_postcode` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `company_industry` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位所属行业',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `validate_status` int(11) DEFAULT '1' COMMENT '是否有效 1：是，2：否',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案件编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8 COMMENT='客户工作单位信息表';

-- ----------------------------
-- Records of t_lc_cust_job
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_duncase
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_duncase`;
CREATE TABLE `t_lc_duncase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案件编号',
  `custom_no` varchar(100) DEFAULT NULL COMMENT '客户编号',
  `custom_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户姓名',
  `certificate_type` int(11) DEFAULT NULL COMMENT '证件类型',
  `certificate_no` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `custom_phone` varchar(100) DEFAULT NULL COMMENT '客户手机号',
  `month_repay_day` int(11) DEFAULT NULL COMMENT '账单日',
  `first_overdue_time` datetime DEFAULT NULL COMMENT '首次逾期时间',
  `overdue_days` bigint(11) DEFAULT NULL COMMENT '逾期天数',
  `overdue_aging` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '逾期账龄',
  `max_overdue_day` int(11) DEFAULT NULL COMMENT '历史最大逾期天数',
  `repay_account_no` varchar(100) DEFAULT NULL COMMENT '还款账号',
  `repay_bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '还款银行',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '业务归属人',
  `owner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属人名称',
  `org_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属机构',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  `hand_separation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手别',
  `repay_date` date DEFAULT NULL COMMENT '应还日期',
  `credit_line` decimal(13,2) DEFAULT NULL COMMENT '授信额度',
  `borrow_line` decimal(13,2) DEFAULT NULL COMMENT '放款金额 ',
  `borrow_card_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '借款卡号',
  `borrow_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '借款卡银行',
  `last_repay_amount_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户最后缴款金额',
  `balance_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户余额',
  `current_cd_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '人民币账户当前CD值',
  `last_repay_date_rmb` date DEFAULT NULL COMMENT '人民币账户最后一次缴款日',
  `last_repay_num` int(11) DEFAULT NULL COMMENT '人民币账户最后还款笔数',
  `total_interest_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还利息总额',
  `principal_one_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还本金1',
  `principal_two_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还本金2',
  `total_principal_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还本金总额',
  `default_interest_one_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还罚息1',
  `default_interest_two_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还罚息2',
  `total_default_interest_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还罚息总额',
  `expenses_payable_one_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还费用1',
  `expenses_payable_two_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还费用2',
  `total_expenses_payable_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户应还费用总金额',
  `lowest_payment_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户最低应还金额',
  `total_debt_amount_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户欠款总金额',
  `fix_limit_rmb` decimal(13,2) DEFAULT NULL COMMENT '人民币账户额度固定额度',
  `default_interest_one_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还罚息1',
  `default_interest_two_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还罚息2',
  `total_default_interest_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还罚息总额',
  `total_interest_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还利息总额',
  `principal_one_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还本金1',
  `principal_two_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还本金2',
  `total_principal_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还本金总额',
  `expenses_payable_one_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还费用1',
  `expenses_payable_two_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还费用2',
  `total_expenses_payable_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户应还费用总金额',
  `lowest_payment_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户最低应还金额',
  `total_debt_amount_fc` decimal(13,2) DEFAULT NULL COMMENT '外币账户欠款总金额',
  `appoint_case_balance` decimal(13,2) DEFAULT NULL COMMENT '委案余额即欠款总额',
  `case_status` int(1) DEFAULT NULL COMMENT '案件关闭状态 1：未分配 2：催收中 3: 已结案',
  `validate_status` int(11) DEFAULT '1' COMMENT '是否有效 1：是，2：否',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `allocat_type` int(11) DEFAULT NULL COMMENT '1 手动 2 委外 3 机器人 4法催',
  `transfer_type` varchar(10) DEFAULT NULL COMMENT '手别',
  `enter_coll_date` date DEFAULT NULL COMMENT '入催日',
  `close_case_yhje` decimal(13,2) DEFAULT NULL COMMENT '结案应还金额',
  `recently_allot_date` datetime DEFAULT NULL COMMENT '最近分配日期',
  `recently_follow_up_date` datetime DEFAULT NULL COMMENT '最近跟进时间',
  `overdue_fine` decimal(13,2) DEFAULT NULL COMMENT '滞纳金',
  `city` varchar(255) DEFAULT NULL COMMENT '所属城市',
  `area` varchar(255) DEFAULT NULL COMMENT '所属区域',
  `recommend_vendor` varchar(255) DEFAULT NULL COMMENT '推荐商户',
  `recommend_website` varchar(255) DEFAULT NULL COMMENT '推荐网点',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `repay_method` varchar(255) DEFAULT NULL COMMENT '还款方式',
  `aging_periods` varchar(255) DEFAULT NULL COMMENT '分期期数',
  `bill_address` varchar(255) DEFAULT NULL COMMENT '账单地址',
  `year_interest_rate` varchar(255) DEFAULT NULL COMMENT '年利率',
  `day_interest_rate` varchar(255) DEFAULT NULL COMMENT '日利率',
  `first_overdue_sign` varchar(255) DEFAULT NULL COMMENT '首逾标识',
  `total_overdue_day` int(255) DEFAULT NULL COMMENT '累计逾期天数',
  `overdue_frequency` int(11) DEFAULT NULL COMMENT '逾期次数',
  `is_exit_collect` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否出催1：是 2：否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8 COMMENT='案件表';

-- ----------------------------
-- Records of t_lc_duncase
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_duncase_action_record
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_duncase_action_record`;
CREATE TABLE `t_lc_duncase_action_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主鍵ID',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案件号',
  `task_id` varchar(100) DEFAULT NULL COMMENT '任务编号',
  `repay_date` date DEFAULT NULL COMMENT '还款日期',
  `repay_amount` decimal(65,2) DEFAULT NULL COMMENT '还款金额',
  `recheck_date` date DEFAULT NULL COMMENT '复核日期',
  `recontact_date` datetime DEFAULT NULL COMMENT '再次联系时间',
  `action_code` varchar(100) DEFAULT NULL COMMENT '行动码',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `validate_status` int(11) DEFAULT '1' COMMENT '是否有效 1：是，2：否',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8 COMMENT='案件行动码记录表';

-- ----------------------------
-- Records of t_lc_duncase_action_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_duncase_assign
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_duncase_assign`;
CREATE TABLE `t_lc_duncase_assign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `custom_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户姓名',
  `certificate_no` varchar(100) DEFAULT NULL COMMENT '证件号',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案件编号',
  `task_id` varchar(100) DEFAULT NULL COMMENT '任务编号',
  `operation_id` bigint(20) DEFAULT NULL COMMENT '操作员ID',
  `operation_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作员姓名',
  `transfer_type` int(11) DEFAULT NULL COMMENT '转移类型(0：初次导入，1：分派转移，2：委上转移，3：结案转移，4：协助催收，5：临时代理)',
  `task_status` int(11) DEFAULT NULL COMMENT '任务状态',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '业务归属人',
  `org_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属机构',
  `collect_team_id` varchar(10) DEFAULT NULL COMMENT '催收组ID',
  `collect_team_name` varchar(100) DEFAULT NULL COMMENT '催收组名称',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `validate_status` int(11) DEFAULT '1' COMMENT '是否有效 1：是，2：否',
  `remark` text COMMENT '备注',
  `owner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属人名称',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=797 DEFAULT CHARSET=utf8 COMMENT='案件轨迹表';

-- ----------------------------
-- Records of t_lc_duncase_assign
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_import_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_import_flow`;
CREATE TABLE `t_lc_import_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `import_batch_no` varchar(20) NOT NULL COMMENT '导入批次号，年月日时分秒生成',
  `org_id` varchar(10) DEFAULT NULL COMMENT '委托方id',
  `org_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '委托方名称',
  `total_money` decimal(13,2) DEFAULT NULL COMMENT '总金额',
  `total_num` int(11) DEFAULT NULL COMMENT '总笔数',
  `import_type` int(11) DEFAULT NULL COMMENT '导入类型，资产导入 还款导入',
  `create_by` int(10) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lc_import_flow
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_robot_call_analyse_result
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_robot_call_analyse_result`;
CREATE TABLE `t_lc_robot_call_analyse_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scene_instance_result_id` bigint(20) DEFAULT NULL COMMENT '通话记录结果 Id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id',
  `call_job_id` int(11) DEFAULT NULL COMMENT '任务 ID',
  `inbound_instance_id` bigint(20) DEFAULT NULL COMMENT '通 话 记 录 Id( 对 应sceneInstanceId)',
  `result_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '通话记录结果类型名',
  `result_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '通话记录结果值',
  `artificial_result_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '通话结果人工标注值（一般指人工标注意向等级）',
  `artificial_changed_value` int(11) DEFAULT NULL COMMENT '是否进行过人工标注修改，1：是，2：否',
  `result_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '结果描述',
  `result_value_alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分析结果别名(resultName 为【客户意向等级】 时标注值为意向级别 A,B,C,D,E,F)',
  `result_labels` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'IntegerStringBO 对象中存储一个 int 类型参数， 一个 String类型参数， resultName 为【客户标签】 时存储客户标签',
  `result_value_new` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '客户意向等级的表述（文案与crm 对应）',
  `sign` varchar(255) DEFAULT NULL COMMENT '回调签名（需联系开通）',
  `date_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GMT 格式日期',
  `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '呼入回调标识：INBOUND_CALL_INSTANCE_RESULT',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=308 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_lc_robot_call_analyse_result
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_robot_call_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_robot_call_detail`;
CREATE TABLE `t_lc_robot_call_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scene_instance_log_id` bigint(20) DEFAULT NULL COMMENT '通话记录日志 Id',
  `inbound_instance_id` bigint(20) DEFAULT NULL COMMENT '通话记录 Id（对应 scene_instance_id)',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id',
  `robot_def_id` int(11) DEFAULT NULL COMMENT '机器人id',
  `decision_id` int(11) DEFAULT NULL COMMENT '对应决策 Id',
  `speaker` varchar(255) DEFAULT NULL COMMENT '说话人 ME： 用户 AI:机器人',
  `content` varchar(255) DEFAULT NULL COMMENT '说话内容',
  `user_mean` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户说话语义,客户说话内容命中的话术节点分支或知识库问题',
  `user_mean_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户说话语义详情,客户说话内容命中详情（包括命中的用户说话内容和话术节点分支或知识库分支问题）',
  `ai_unknown` int(11) DEFAULT NULL COMMENT '是否是 ai 无法应答的问题， 1-是，0-否',
  `answer_status` int(11) DEFAULT NULL COMMENT '回答问题状态： 0-分支， 1-问题，2-忽略， 表示命中流程分支或者知识库问题',
  `study_status` int(11) DEFAULT NULL COMMENT '学习状态： 0-未学习， 1-已学习，在问题学习板块里面的问题学习状态（默认为空）',
  `start_time` datetime DEFAULT NULL COMMENT '说话的开始时间,本句话在录音中的结束时间',
  `end_time` datetime DEFAULT NULL COMMENT '说话的结束时间,本句话在录音中的结束时间',
  `correction_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '通话记录纠错内容， 通话记录中的人工纠错功能的纠错内容',
  `luyin_oss_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '通话记录录音',
  `sign` varchar(255) DEFAULT NULL COMMENT '回调签名（需联系开通）',
  `date_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GMT 格式日期',
  `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '呼入回调标识：INBOUND_CALL_INSTANCE_RESULT',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_lc_robot_call_detail
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_robot_call_record_mete_data
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_robot_call_record_mete_data`;
CREATE TABLE `t_lc_robot_call_record_mete_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '呼入回调标识：INBOUND_CALL_INSTANCE_RESULT',
  `inbound_instance_id` bigint(20) DEFAULT NULL COMMENT '通话记录 id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  `call_job_id` int(11) DEFAULT NULL COMMENT '任务 ID',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户 Id',
  `customer_telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '客户手机号',
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '电话实例状态 0： 未开始 1： 进行中 2： 已完成',
  `finish_status` int(11) DEFAULT NULL COMMENT '已完成通话状态枚举',
  `duration` int(11) DEFAULT NULL COMMENT '通话时长',
  `chat_round` int(11) DEFAULT NULL COMMENT '通话轮次',
  `start_time` datetime DEFAULT NULL COMMENT '通话开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '通话结束时间',
  `callee_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '被叫号码',
  `luyin_oss_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '通话录音（包含 Ai 和客户）',
  `user_luyin_oss_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '通话录音（只包含客户）',
  `properties` varchar(255) DEFAULT NULL COMMENT '通话记录携带的参数(json 字符串)，包含话术变量和自定义参数， 用户可以传入自己的变量， 回调会传回给用户',
  `read_status` int(11) DEFAULT NULL COMMENT '是否已读， 产品中的通话记录已读未读状态 0： 未读 1： 已读',
  `robot_def_id` int(11) DEFAULT NULL COMMENT '机器人 Id',
  `scene_def_id` int(11) DEFAULT NULL COMMENT '场景 Id',
  `scene_record_id` int(11) DEFAULT NULL COMMENT '场景录音 id',
  `transfer_status` int(11) DEFAULT NULL COMMENT '转人工状态:0-无转接,1-成功,2-失败',
  `transfer_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '转人工详情',
  `user_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户级别',
  `hang_up` int(11) DEFAULT NULL COMMENT '挂机人 0： AI 1： 用户',
  `callbacked` int(11) DEFAULT NULL COMMENT '是否回调',
  `properties_map` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL COMMENT '回调签名（需联系开通)',
  `date_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GMT 格式日期',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_lc_robot_call_record_mete_data
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_robot_task
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_robot_task`;
CREATE TABLE `t_lc_robot_task` (
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lc_robot_task
-- ----------------------------

-- ----------------------------
-- Table structure for t_lc_task
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_task`;
CREATE TABLE `t_lc_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主鍵ID',
  `case_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '案件号',
  `certificate_no` varchar(100) DEFAULT NULL COMMENT '证件号',
  `certificate_type` int(11) DEFAULT NULL COMMENT '证件类型',
  `custom_code` varchar(100) DEFAULT NULL COMMENT '客户编号',
  `custom_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户姓名',
  `arrears_total` decimal(13,2) DEFAULT NULL COMMENT '委案金额',
  `task_status` int(11) DEFAULT NULL COMMENT '任务状态 1：未分配 2：催收中 3: 已结案',
  `overdue_days` bigint(11) DEFAULT NULL COMMENT '逾期天数',
  `overdue_aging` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '逾期账龄',
  `collect_time_limit` bigint(20) DEFAULT NULL COMMENT '催收限时天数',
  `collect_last_time` datetime DEFAULT NULL COMMENT '最后催记时间',
  `collect_team_id` bigint(10) DEFAULT NULL COMMENT '催收组ID',
  `collect_team_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '催收组名称',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '业务归属人',
  `owner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属人',
  `org_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务归属机构',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属机构',
  `close_date` datetime(6) DEFAULT NULL COMMENT '结案日期',
  `in_collect_days` int(10) DEFAULT NULL COMMENT '在催收员名下天数',
  `old_owner_id` bigint(20) DEFAULT NULL COMMENT '原先的业务归属人',
  `task_type` int(11) DEFAULT NULL COMMENT '任务类型1：初次生成(导入)，2：重新分派，3：临时代理，4：协助催收',
  `allot_type` int(11) DEFAULT NULL COMMENT '分配类型1：手动，2：委外，3：机器人，4：法催',
  `validate_status` int(11) DEFAULT '1' COMMENT '是否有效 1：是，2：否',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_owner_time` datetime DEFAULT NULL COMMENT '修改任务归属人时间：用来记录在催收员名下天数使用',
  `old_owner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原先的业务归属人名称(催收员)',
  `robot_task_id` bigint(20) DEFAULT NULL COMMENT '机器人任务id，当分配类型是机器人时必填',
  `robot_call_strategy_id` int(11) DEFAULT NULL COMMENT '机器人呼叫策略id',
  `transfer_type` varchar(10) DEFAULT NULL COMMENT '手别',
  `enter_coll_date` date DEFAULT NULL COMMENT '入催日',
  `close_case_yhje` decimal(13,2) DEFAULT NULL COMMENT '结案应还金额',
  `recently_allot_date` datetime DEFAULT NULL COMMENT '最近分配日期',
  `recently_follow_up_date` datetime DEFAULT NULL COMMENT '最近跟进时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2167 DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
-- Records of t_lc_task
-- ----------------------------

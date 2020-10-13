INSERT INTO `sys_dict_type` VALUES ('141', '减免', 'reduction', '0', 'admin', '2020-09-22 09:55:09', '', null, null);
INSERT INTO `sys_dict_type` VALUES ('142', '对公入账', 'company', '0', 'admin', '2020-09-22 10:32:10', '', null, null);
INSERT INTO `sys_dict_type` VALUES ('143', '逾期划扣', 'buckle', '0', 'admin', '2020-09-22 10:37:00', 'admin', '2020-09-22 10:56:26', '');
INSERT INTO `sys_dict_data` VALUES ('331', '1', 'reduction_zy', '208', 'reduction', '', '', 'Y', '0', 'admin', '2020-09-22 09:55:44', 'admin', '2020-09-22 10:23:22', '');
INSERT INTO `sys_dict_data` VALUES ('332', '2', 'reduction_jx', '212', 'reduction', '', '', 'Y', '0', 'admin', '2020-09-22 09:57:50', 'admin', '2020-09-22 10:23:00', '');
INSERT INTO `sys_dict_data` VALUES ('333', '3', 'reduction_xy', '213', 'reduction', '', '', 'Y', '0', 'admin', '2020-09-22 09:59:23', 'admin', '2020-09-22 10:21:02', '');
INSERT INTO `sys_dict_data` VALUES ('335', '1', 'company_xy', '213', 'company', null, null, 'Y', '0', 'admin', '2020-09-22 10:32:43', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('336', '2', 'company_zy', '208', 'company', null, null, 'Y', '0', 'admin', '2020-09-22 10:33:07', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('337', '1', 'buckle_xy', '213', 'buckle', '', '', 'Y', '0', 'admin', '2020-09-22 10:37:39', 'admin', '2020-09-22 10:56:46', '');
INSERT INTO `sys_dict_data` VALUES ('338', '2', 'buckle_zy', '208', 'buckle', '', '', 'Y', '0', 'admin', '2020-09-22 10:37:59', 'admin', '2020-09-22 10:56:39', '');

ALTER TABLE t_lc_inforeporting_company ADD deposit_date datetime COMMENT '存入日期';
INSERT INTO `t_lc_inforeporting_set` VALUES ('89', '213', '兴业消金催收项目', '3', '减免', 'cpmc', '产品', '1', '0', '0', 'class=\'form-control\'', 'productName', '1', '0', 'admin', '2020-10-09 09:51:31', 'admin', '2020-10-09 09:51:31', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('90', '213', '兴业消金催收项目', '3', '减免', 'email', '借据号', '2', '0', '0', 'class=\'form-control\'', 'caseNo', '1', '0', 'admin', '2020-10-09 09:51:52', 'admin', '2020-10-09 09:51:52', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('92', '213', '兴业消金催收项目', '3', '减免', 'curName', '客户姓名', '3', '30', '0', 'class=\'form-control\'', 'customName', '1', '0', 'admin', '2020-10-09 09:52:35', 'admin', '2020-10-09 09:52:35', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('93', '213', '兴业消金催收项目', '3', '减免', 'draweeName', '付款人姓名', '4', '30', '0', 'class=\'form-control\'', 'draweeName', '1', '1', 'admin', '2020-10-09 10:11:09', 'admin', '2020-10-09 10:11:09', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('94', '213', '兴业消金催收项目', '3', '减免', 'relationship', '付款人与客户关系', '5', '30', '0', 'class=\'form-control\'', 'relationship', '1', '1', 'admin', '2020-10-09 10:11:47', 'admin', '2020-10-09 10:11:47', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('95', '213', '兴业消金催收项目', '3', '减免', 'topFourCards', '付款卡前四位', '6', '4', '1', 'class=\'form-control\'', 'topFourCards', '1', '1', 'admin', '2020-10-09 10:12:12', 'admin', '2020-10-09 10:12:12', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('96', '213', '兴业消金催收项目', '3', '减免', 'lastFourCards', '付款卡后四位', '7', '4', '1', 'class=\'form-control\'', 'lastFourCards', '1', '1', 'admin', '2020-10-09 10:12:25', 'admin', '2020-10-09 10:32:05', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('97', '213', '兴业消金催收项目', '3', '减免', 'payingBank', '付款银行', '8', '50', '0', 'class=\'form-control\'', 'payingBank', '1', '1', 'admin', '2020-10-09 10:12:55', 'admin', '2020-10-09 10:12:55', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('98', '213', '兴业消金催收项目', '3', '减免', 'depositAmount', '存入金额', '9', '20', '0', 'class=\'form-control\' ismoney=true', 'depositAmount', '1', '1', 'admin', '2020-10-09 10:13:18', 'admin', '2020-10-09 10:13:18', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('99', '213', '兴业消金催收项目', '3', '减免', 'amountOfDeduction', '扣款金额', '10', '20', '0', 'class=\'form-control\' ismoney=true', 'amountOfDeduction', '1', '1', 'admin', '2020-10-09 10:14:31', 'admin', '2020-10-09 10:14:31', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('100', '213', '兴业消金催收项目', '3', '减免', 'reasons', '非本人对公还款原因', '11', '256', '0', 'class=\'form-control\'', 'reasons', '1', '1', 'admin', '2020-10-09 10:14:58', 'admin', '2020-10-09 10:14:58', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('101', '213', '兴业消金催收项目', '3', '减免', 'applicationTime', '申请时间', '12', '0', '0', 'class=\'form-control time-input\'', 'applicationTime', '1', '1', 'admin', '2020-10-09 10:16:09', 'admin', '2020-10-09 10:16:09', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('102', '213', '兴业消金催收项目', '3', '减免', 'remarks', '备注', '14', '256', '0', 'class=\'form-control\'', 'remarks', '1', '1', 'admin', '2020-10-09 10:16:32', 'admin', '2020-10-09 10:16:32', '0');
INSERT INTO `t_lc_inforeporting_set` VALUES ('103', '213', '兴业消金催收项目', '3', '减免', 'depositDate', '存入日期', '13', '0', '0', 'class=\'form-control time-input\'', 'depositDate', '1', '1', 'admin', '2020-10-09 11:26:02', 'admin', '2020-10-09 11:26:02', '0');

CREATE TABLE t_lc_task_1009 SELECT * FROM t_lc_task;
alter table t_lc_task drop column collect_time_limit;
alter table t_lc_task drop column collect_last_time;
alter table t_lc_task drop column collect_team_id;
alter table t_lc_task drop column collect_team_name;
alter table t_lc_task drop column in_collect_days;
alter table t_lc_task drop column validate_status;
alter table t_lc_task drop column modify_owner_time;

CREATE TABLE t_lc_duncase_1009 SELECT * FROM t_lc_duncase;
alter table t_lc_duncase drop column custom_no;
alter table t_lc_duncase drop column certificate_type;
alter table t_lc_duncase drop column repay_account_no;
alter table t_lc_duncase drop column repay_bank;
alter table t_lc_duncase drop column owner_id;
alter table t_lc_duncase drop column owner_name;
alter table t_lc_duncase drop column hand_separation;
alter table t_lc_duncase drop column credit_line;
alter table t_lc_duncase drop column last_repay_amount_rmb;
alter table t_lc_duncase drop column balance_rmb;
alter table t_lc_duncase drop column current_cd_value;
alter table t_lc_duncase drop column last_repay_date_rmb;
alter table t_lc_duncase drop column last_repay_num;
alter table t_lc_duncase drop column principal_one_rmb;
alter table t_lc_duncase drop column principal_two_rmb;
alter table t_lc_duncase drop column default_interest_one_rmb;
alter table t_lc_duncase drop column default_interest_two_rmb;
alter table t_lc_duncase drop column expenses_payable_one_rmb;
alter table t_lc_duncase drop column expenses_payable_two_rmb;
alter table t_lc_duncase drop column fix_limit_rmb;
alter table t_lc_duncase drop column default_interest_one_fc;
alter table t_lc_duncase drop column default_interest_two_fc;
alter table t_lc_duncase drop column total_default_interest_fc;
alter table t_lc_duncase drop column total_interest_fc;
alter table t_lc_duncase drop column principal_one_fc;
alter table t_lc_duncase drop column principal_two_fc;
alter table t_lc_duncase drop column total_principal_fc;
alter table t_lc_duncase drop column expenses_payable_one_fc;
alter table t_lc_duncase drop column expenses_payable_two_fc;
alter table t_lc_duncase drop column total_expenses_payable_fc;
alter table t_lc_duncase drop column lowest_payment_fc;
alter table t_lc_duncase drop column total_debt_amount_fc;
alter table t_lc_duncase drop column case_status;
alter table t_lc_duncase drop column validate_status;
alter table t_lc_duncase drop column allocat_type;
alter table t_lc_duncase drop column recently_allot_date;
alter table t_lc_duncase drop column recently_follow_up_date;
alter table t_lc_duncase drop column is_exit_collect;
alter table t_lc_duncase drop column hit_rule;
alter table t_lc_duncase drop column hit_rule_desc;
alter table t_lc_duncase drop column distribution_strategy;
alter table t_lc_duncase drop column action_code;
alter table t_lc_duncase drop column action_code_value;
alter table t_lc_duncase drop column call_sign;
alter table t_lc_duncase drop column call_sign_value;
alter table t_lc_duncase drop column phone;
alter table t_lc_duncase drop column task_type;


alter TABLE t_lc_task_his add COLUMN phoneStatus char(1) DEFAULT '0' COMMENT '案件号码状态：0不可联，1可联';
alter TABLE t_lc_duncase_his add COLUMN remark varchar(255) DEFAULT NULL COMMENT '备注';

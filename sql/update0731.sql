alter TABLE t_lc_duncase_his add COLUMN score int(11) DEFAULT NULL COMMENT '催收评分';

alter table cur_assets_package_his MODIFY COLUMN is_exit_collect int(1) DEFAULT NULL COMMENT '结案类型：1出催结案，2回收结案';
alter table cur_assets_package_his MODIFY COLUMN wa_ye decimal(20,2) DEFAULT NULL COMMENT '结案应还金额';
alter table cur_assets_package_his MODIFY COLUMN rmb_ye decimal(20,2) DEFAULT NULL COMMENT '委案金额';

alter table cur_assets_package_his add COLUMN update_time datetime DEFAULT NULL COMMENT '修改时间';
alter table cur_assets_package_his add COLUMN update_by varchar(64) DEFAULT NULL COMMENT '修改人';

alter table t_lc_task_his add COLUMN close_type int(1) DEFAULT NULL COMMENT '结案类型：1出催结案，2回收结案，3到期结案';

delete from t_lc_report_day_process where report_date = '2020-07-20';
delete from t_lc_report_day_process where report_date = '2020-07-21';
delete from t_lc_report_day_process where report_date = '2020-07-22';
delete from t_lc_report_day_process where report_date = '2020-07-23';

alter table t_lc_report_day_process MODIFY COLUMN call_connected_recovery varchar(50) DEFAULT NULL COMMENT '电话接通率';

INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801312', '杜永卫', '2405', NULL, '30', '0.0', NULL, NULL, '', NULL, '7', '30', NULL, '212', '23400863.23');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801334-ztb', '李林巍', '2016', NULL, '370', '2.75', '1', NULL, '0.0054054054054', NULL, '223', '3', '0', '212', '17949159.36');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801444', '李之秋', '835', NULL, '26', '0.68', '5', NULL, '0.038461538462', NULL, '19', '1', '0', '211', '35377544.73');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801336-ztb', '郭号号', '774', NULL, NULL, '', NULL, NULL, '', NULL, NULL, NULL, NULL, '211', '33708109.38');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801324', '韩朝晖', '1125', NULL, '519', '13.47', '7', '1', '0.036964980545', NULL, '108', '13', '0', '208', '8126196.71');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801339', '刘明凯', '1092', NULL, '528', '93.92', '3', '5', '0.089147286822', NULL, '114', '94', '0', '208', '7794689.96');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801334-zy', '李林巍', '1118', NULL, '125', '24.11', '2', '3', '0.12389380531', NULL, '28', '24', '0', '208', '8012490.39');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801344', '颜明月', '1114', NULL, '510', '26.48', '20', '1', '0.043824701195', NULL, '352', '26', '0', '208', '8055790.55');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801330', '牟舟波', '1096', NULL, NULL, '', NULL, NULL, '', NULL, NULL, NULL, NULL, '208', '7842139.60');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801338', '刘涛', '991', NULL, '556', '57.58', '14', NULL, '0.034990791897', NULL, '282', '58', '0', '208', '7559083.02');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801314', '商业博', '1122', NULL, '384', '47.71', '46', '5', '0.14247311828', NULL, '252', '48', '0', '208', '8013366.94');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801411', '孙萌', '1117', NULL, '207', '43.68', '12', NULL, '0.081632653061', NULL, '97', '44', '0', '208', '8010001.59');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-21', 'A', '801325', '孙文凯', '1109', NULL, '616', '64.17', '22', '9', '0.069965870307', NULL, '267', '64', '0', '208', '7948943.68');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801444', '李之秋', '818', NULL, '13', '0.0', NULL, NULL, '', NULL, '6', '0', NULL, '211', '34000028.96');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801336-ztb', '郭号号', '755', NULL, '10', '4.75', '2', NULL, '0.2', NULL, '6', '5', '0', '211', '32143958.40');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801312', '杜永卫', '2405', NULL, NULL, '', NULL, NULL, '', NULL, NULL, NULL, NULL, '212', '23400863.23');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801334-ztb', '李林巍', '2016', NULL, NULL, '', NULL, NULL, '', NULL, NULL, NULL, NULL, '212', '17949159.36');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801324', '韩朝晖', '1161', '1', '528', '16.11', '8', '1', '0.04752851711', NULL, '253', '16', '0', '208', '9023266.71');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801339', '刘明凯', '1128', NULL, '535', '37.5', '8', NULL, '0.062381852552', NULL, '162', '38', '0', '208', '8684930.56');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801334-zy', '李林巍', '1152', '34', '136', '14.1', '6', NULL, '0.076923076923', NULL, '89', '14', '0', '208', '8842182.93');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801344', '颜明月', '1150', NULL, '314', '3.25', '4', NULL, '0.025889967638', NULL, '239', '3', '0', '208', '8945554.19');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801330', '牟舟波', '1132', NULL, '326', '27.36', '9', NULL, '0.046296296296', NULL, '94', '27', '0', '208', '8733930.20');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801338', '刘涛', '1025', NULL, '525', '40.01', '8', '13', '0.045977011494', NULL, '196', '40', '0', '208', '8388867.92');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801314', '商业博', '1157', NULL, '352', '38.37', '31', '1', '0.10404624277', NULL, '226', '38', '0', '208', '8877122.55');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801411', '孙萌', '1153', NULL, '230', '41.43', '18', NULL, '0.087719298246', NULL, '184', '41', '0', '208', '8900310.66');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-22', 'A', '801325', '孙文凯', '1145', NULL, '628', '28.32', '54', NULL, '0.095238095238', NULL, '449', '28', '0', '208', '8840156.60');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801444', '李之秋', '818', NULL, '4', '0.0', '1', NULL, '', NULL, '3', '0', NULL, '211', '34000028.96');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801336-ztb', '郭号号', '755', NULL, '11', '0.0', NULL, NULL, '', NULL, '7', '0', NULL, '211', '32143958.40');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801312', '杜永卫', '2405', NULL, '65', '9.51', NULL, NULL, '0.031746031746', NULL, '13', '10', '0', '212', '23401375.89');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801334-ztb', '李林巍', '2016', NULL, '135', '4.2', '1', NULL, '0.015384615385', NULL, '53', '4', '0', '212', '17949159.36');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801324', '韩朝晖', '1192', NULL, '448', '14.5', '287', '10', '0.7052154195', NULL, '237', '15', '1', '208', '9740704.65');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801339', '刘明凯', '1165', '1', '235', '1.61', '13', NULL, '0.40186915888', NULL, '112', '2', '0', '208', '9497783.19');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801334-zy', '李林巍', '1099', NULL, '124', '92.15', '37', '6', '0.50961538462', NULL, '53', '92', '1', '208', '7999088.12');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801344', '颜明月', '1185', NULL, '276', '3.71', '99', '17', '0.43703703704', NULL, '152', '4', '0', '208', '9733295.63');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801330', '牟舟波', '1128', NULL, '149', '3.29', '26', '2', '0.30281690141', NULL, '95', '3', '0', '208', '8814713.04');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801338', '刘涛', '1024', NULL, '78', '0.21', '10', '2', '0.18571428571', NULL, '56', '0', '0', '208', '8713340.56');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801314', '商业博', '1175', NULL, '290', '18.12', '106', '5', '0.52192982456', NULL, '129', '18', '1', '208', '9249928.21');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801411', '孙萌', '1164', NULL, '192', '8.88', '16', '11', '0.33714285714', NULL, '61', '9', '0', '208', '9202402.42');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-23', 'A', '801325', '孙文凯', '1188', NULL, '324', '23.52', '75', '16', '0.32214765101', NULL, '192', '24', '0', '208', '9742345.37');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801444', '李之秋', '835', NULL, '4', '1.19', '1', NULL, '0.33333333333', NULL, '4', '1', '0', '211', '35377544.73');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801336-ztb', '郭号号', '774', NULL, NULL, '', NULL, NULL, '', NULL, NULL, NULL, NULL, '211', '33708109.38');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801312', '杜永卫', '2405', NULL, '39', '4.68', NULL, NULL, '0.10810810811', NULL, '10', '5', '0', '212', '23400863.23');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801334-ztb', '李林巍', '2016', NULL, '102', '27.580000000000002', '3', NULL, '0.059405940594', NULL, '37', '28', '0', '212', '17949159.36');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801324', '韩朝晖', '1060', NULL, '620', '23.0', '28', NULL, '0.066235864297', NULL, '488', '23', '0', '208', '6713111.61');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801339', '刘明凯', '1023', NULL, NULL, '', NULL, NULL, '', NULL, NULL, NULL, NULL, '208', '6373367.39');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801334-zy', '李林巍', '1055', NULL, '329', '34.9', '34', '2', '0.13636363636', NULL, '195', '35', '0', '208', '6634686.93');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801344', '颜明月', '1043', '6', '659', '21.1', '29', NULL, '0.044006069803', NULL, '373', '21', '0', '208', '6590287.18');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801330', '牟舟波', '1055', NULL, '463', '28.18', '25', NULL, '0.0670995671', NULL, '302', '28', '0', '208', '6626267.53');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801338', '刘涛', '919', NULL, '467', '62.31', '23', NULL, '0.054704595186', NULL, '222', '62', '0', '208', '6167351.64');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801314', '商业博', '1054', NULL, '438', '67.2', '51', '4', '0.13689095128', NULL, '285', '67', '0', '208', '6631306.97');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801411', '孙萌', '1051', NULL, '515', '40.64', '20', '6', '0.061143984221', NULL, '270', '41', '0', '208', '6561866.09');
INSERT INTO `t_lc_report_day_process` (`report_date`, `seat_group`, `seat_num`, `seat_name`, `deal_with_consumer_count`, `action_code_num`, `call_code_num`, `call_len`, `self_effective_call_code_num`, `third_effective_call_code_num`, `call_connected_recovery`, `complaint_num`, `user_cover_num`, `call_num`, `connected_call_num`, `org_id`, `colling_case_money`) VALUES ('2020-07-20', 'A', '801325', '孙文凯', '1043', NULL, '699', '76.41', '63', '6', '0.12752721617', NULL, '367', '76', '0', '208', '6543772.84');

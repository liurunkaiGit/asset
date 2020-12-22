CREATE PROCEDURE `his_data_migrate`(in days int(2))
begin
-- 插入资产历史表
INSERT INTO `cur_assets_package_his` (`id`, `org_casNo`, `org`, `transferType`, `rmb_ye`, `rmb_yhfxzje`, `rmb_yhlizje`, `rmb_yhbjzje`, `rmb_yhfyzje`, `rmb_zdyhje`, `rmb_qkzje`, `borrow_no`, `borrow_blank`, `borrow_ed`, `rcr`, `fz`, `area_center`, `max_yqts_his`, `sum_yqts_his`, `sum_yqcs_his`, `ww_city_name`, `wa_ye`, `bill_address`, `year_rates`, `tj_firm`, `tj_wd`, `day_rates`, `account_date`, `overdue_days`, `first_yq_date`, `first_yqjc_date`, `first_yq_flag`, `cur_name`, `cur_sex`, `certificate_no`, `certificate_address`, `regist_address`, `email`, `marriage`, `education`, `customer_mobile`, `customer_home_tel`, `customer_home_address`, `work_name`, `work_address`, `work_tel`, `first_liaison_name`, `first_liaison_relation`, `first_liaison_mobile`, `first_liaison_tel`, `second_liaison_name`, `second_liaison_relation`, `second_liaison_mobile`, `second_liaison_tel`, `three_liaison_name`, `three_liaison_relation`, `three_liaison_mobile`, `three_liaison_tel`, `cpmc`, `hk_type`, `znj`, `ajhssj`, `org_id`, `package_flag`, `create_by`, `create_time`, `close_case`, `is_exit_collect`, `import_batch_no`, `close_case_date`, `package_id`, `account_age`, `la_flag`, `fx_flag`, `htlx`, `jmbq`, `fcbq`, `fxsfbh`, `remark`, `tar`, `jkrq`, `zhychkr`, `mqhkje`, `dqqkje`, `ljyhje`, `sfje`, `zdhkzh1`, `zdhkzh2`, `zdhkzhhm1`, `zdhkzhhm2`, `zdhkqd1`, `zdhkqd2`, `khmb`, `spjg`, `dklx`, `jkbs`, `spxx`, `wacs`, `ykqs`, `work_dept`, `customer_mobile2`, `customer_mobile3`, `customer_mobile4`, `fourth_liaison_name`, `fourth_liaison_relation`, `fourth_liaison_mobile`, `fifth_liaison_name`, `fifth_liaison_relation`, `fifth_liaison_mobile`, `dqyhje`)
SELECT `id`, `org_casNo`, `org`, `transferType`, `rmb_ye`, `rmb_yhfxzje`, `rmb_yhlizje`, `rmb_yhbjzje`, `rmb_yhfyzje`, `rmb_zdyhje`, `rmb_qkzje`, `borrow_no`, `borrow_blank`, `borrow_ed`, `rcr`, `fz`, `area_center`, `max_yqts_his`, `sum_yqts_his`, `sum_yqcs_his`, `ww_city_name`, `wa_ye`, `bill_address`, `year_rates`, `tj_firm`, `tj_wd`, `day_rates`, `account_date`, `overdue_days`, `first_yq_date`, `first_yqjc_date`, `first_yq_flag`, `cur_name`, `cur_sex`, `certificate_no`, `certificate_address`, `regist_address`, `email`, `marriage`, `education`, `customer_mobile`, `customer_home_tel`, `customer_home_address`, `work_name`, `work_address`, `work_tel`, `first_liaison_name`, `first_liaison_relation`, `first_liaison_mobile`, `first_liaison_tel`, `second_liaison_name`, `second_liaison_relation`, `second_liaison_mobile`, `second_liaison_tel`, `three_liaison_name`, `three_liaison_relation`, `three_liaison_mobile`, `three_liaison_tel`, `cpmc`, `hk_type`, `znj`, `ajhssj`, `org_id`, `package_flag`, `create_by`, `create_time`, `close_case`, `is_exit_collect`, `import_batch_no`, `close_case_date`, `package_id`, `account_age`, `la_flag`, `fx_flag`, `htlx`, `jmbq`, `fcbq`, `fxsfbh`, `remark`, `tar`, `jkrq`, `zhychkr`, `mqhkje`, `dqqkje`, `ljyhje`, `sfje`, `zdhkzh1`, `zdhkzh2`, `zdhkzhhm1`, `zdhkzhhm2`, `zdhkqd1`, `zdhkqd2`, `khmb`, `spjg`, `dklx`, `jkbs`, `spxx`, `wacs`, `ykqs`, `work_dept`, `customer_mobile2`, `customer_mobile3`, `customer_mobile4`, `fourth_liaison_name`, `fourth_liaison_relation`, `fourth_liaison_mobile`, `fifth_liaison_name`, `fifth_liaison_relation`, `fifth_liaison_mobile`, `dqyhje` from `cur_assets_package` where close_case = 1 AND NOW() >= (SELECT date_sub(close_case_date, INTERVAL - days DAY));
-- 删除资产表
DELETE from cur_assets_package where close_case = 1 AND NOW() >= (SELECT date_sub(close_case_date, INTERVAL - days DAY));
-- 插入案件历史表
INSERT INTO `t_lc_duncase_his` (`case_no`, `custom_name`, `certificate_no`, `custom_phone`, `month_repay_day`, `first_overdue_time`, `overdue_days`, `overdue_aging`, `max_overdue_day`, `org_id`, `org_name`, `repay_date`, `borrow_line`, `borrow_card_no`, `borrow_bank`, `total_interest_rmb`, `total_principal_rmb`, `total_default_interest_rmb`, `total_expenses_payable_rmb`, `lowest_payment_rmb`, `total_debt_amount_rmb`, `appoint_case_balance`, `create_time`, `create_by`, `modify_time`, `modify_by`, `transfer_type`, `enter_coll_date`, `close_case_yhje`, `overdue_fine`, `city`, `area`, `recommend_vendor`, `recommend_website`, `product_name`, `repay_method`, `aging_periods`, `bill_address`, `year_interest_rate`, `day_interest_rate`, `first_overdue_sign`, `total_overdue_day`, `overdue_frequency`, `import_batch_no`, `pack_no`, `back_case_date`, `loan_type`, `stay_case_flag`, `risk_flag`, `contract_type`, `reduction_flag`, `legal_flag`, `ljyhje`)
SELECT
	t.case_no,
	t.custom_name,
	t.certificate_no,
	t.custom_phone,
	t.month_repay_day,
	t.first_overdue_time,
	t.overdue_days,
	t.overdue_aging,
	t.max_overdue_day,
	t.org_id,
	t.org_name,
	t.repay_date,
	t.borrow_line,
	t.borrow_card_no,
	t.borrow_bank,
	t.total_interest_rmb,
	t.total_principal_rmb,
	t.total_default_interest_rmb,
	t.total_expenses_payable_rmb,
	t.lowest_payment_rmb,
	t.total_debt_amount_rmb,
	t.appoint_case_balance,
	t.create_time,
	t.create_by,
	t.modify_time,
	t.modify_by,
	t.transfer_type,
	t.enter_coll_date,
	t.close_case_yhje,
	t.overdue_fine,
	t.city,
	t.area,
	t.recommend_vendor,
	t.recommend_website,
	t.product_name,
	t.repay_method,
	t.aging_periods,
	t.bill_address,
	t.year_interest_rate,
	t.day_interest_rate,
	t.first_overdue_sign,
	t.total_overdue_day,
	t.overdue_frequency,
	t.import_batch_no,
	t.pack_no,
	t.back_case_date,
	t.loan_type,
	t.stay_case_flag,
	t.risk_flag,
	t.contract_type,
	t.reduction_flag,
	t.legal_flag,
	t.ljyhje
FROM
	t_lc_duncase t,t_lc_task ta
where t.case_no = ta.case_no and t.import_batch_no = ta.import_batch_no and t.org_id = ta.org_id and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 删除案件表
DELETE t from t_lc_duncase t,t_lc_task ta
where t.case_no = ta.case_no and t.import_batch_no = ta.import_batch_no and t.org_id = ta.org_id and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 插入客户信息历史表
INSERT INTO `t_lc_custinfo_his` (`custom_code`, `custom_name`, `custom_sex`, `birthday`, `certificate_type`, `certificate_no`, `certificate_address`, `profession`, `city`, `education`, `phone`, `tel`, `email`, `income_year`, `address`, `marrage_status`, `has_child`, `has_house`, `has_car`, `validate_status`, `create_time`, `modify_time`, `create_by`, `modify_by`, `org_id`, `org_name`, `census_address`, `case_no`, `import_batch_no`)
SELECT
	t.custom_code,
	t.custom_name,
	t.custom_sex,
	t.birthday,
	t.certificate_type,
	t.certificate_no,
	t.certificate_address,
	t.profession,
	t.city,
	t.education,
	t.phone,
	t.tel,
	t.email,
	t.income_year,
	t.address,
	t.marrage_status,
	t.has_child,
	t.has_house,
	t.has_car,
	t.validate_status,
	t.create_time,
	t.modify_time,
	t.create_by,
	t.modify_by,
	t.org_id,
	t.org_name,
	t.census_address,
	t.case_no,
	t.import_batch_no
FROM
	`t_lc_custinfo` t,
	t_lc_task ta
WHERE
	t.case_no = ta.case_no AND t.import_batch_no = ta.import_batch_no AND t.org_id = ta.org_id AND ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 删除客户信息表
DELETE t from t_lc_custinfo t,t_lc_task ta
where t.case_no = ta.case_no and t.import_batch_no = ta.import_batch_no and t.org_id = ta.org_id and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 插入客户工作信息历史表
INSERT INTO `t_lc_cust_job_his` (`custom_code`, `certificate_no`, `profession`, `company_name`, `company_tel`, `company_address`, `company_postcode`, `company_industry`, `create_time`, `modify_time`, `create_by`, `modify_by`, `validate_status`, `case_no`, `org_id`, `org_name`, `import_batch_no`)
select
	t.custom_code,
	t.certificate_no,
	t.profession,
	t.company_name,
	t.company_tel,
	t.company_address,
	t.company_postcode,
	t.company_industry,
	t.create_time,
	t.modify_time,
	t.create_by,
	t.modify_by,
	t.validate_status,
	t.case_no,
	t.org_id,
	t.org_name,
	t.import_batch_no
FROM
	`t_lc_cust_job` t,
	t_lc_task ta
WHERE
	t.case_no = ta.case_no AND t.import_batch_no = ta.import_batch_no AND t.org_id = ta.org_id AND ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 删除客户信息表
DELETE t from t_lc_cust_job t,t_lc_task ta
where t.case_no = ta.case_no and t.import_batch_no = ta.import_batch_no and t.org_id = ta.org_id and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 插入客户联系人历史表
INSERT INTO `t_lc_cust_contact_his` (`certificate_no`, `contact_name`, `relation`, `phone`, `tel`, `address`, `origin`, `create_time`, `modify_time`, `create_by`, `modify_by`, `validate_status`, `case_no`, `org_id`, `org_name`, `import_batch_no`, `is_close`)
select
	t.certificate_no,
	t.contact_name,
	t.relation,
	t.phone,
	t.tel,
	t.address,
	t.origin,
	t.create_time,
	t.modify_time,
	t.create_by,
	t.modify_by,
	t.validate_status,
	t.case_no,
	t.org_id,
	t.org_name,
	t.import_batch_no,
	t.is_close
FROM
	`t_lc_cust_contact` t,
	t_lc_task ta
WHERE
	t.case_no = ta.case_no AND t.import_batch_no = ta.import_batch_no AND t.org_id = ta.org_id AND ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 删除客户联系人表
DELETE t from t_lc_cust_contact t,t_lc_task ta
where t.case_no = ta.case_no and t.import_batch_no = ta.import_batch_no and t.org_id = ta.org_id and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 插入电催记录历史表
INSERT INTO `t_lc_call_record_his` (`contact_name`, `phone`, `contact_relation`, `call_start_time`, `call_end_time`, `call_len`, `call_sign`, `call_result`, `call_radio_location`, `call_radio`, `create_time`, `create_by`, `case_no`, `remark`, `platform`, `find_date`, `agent_name`, `star`, `type`, `make_call_time`)
select
	t.contact_name,
	t.phone,
	t.contact_relation,
	t.call_start_time,
	t.call_end_time,
	t.call_len,
	t.call_sign,
	t.call_result,
	t.call_radio_location,
	t.call_radio,
	t.create_time,
	t.create_by,
	t.case_no,
	t.remark,
	t.platform,
	t.find_date,
	t.agent_name,
	t.star,
	t.type,
	t.make_call_time
from t_lc_call_record t,t_lc_task ta
where t.case_no = ta.case_no and t.create_time <= ta.close_date and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 删除电催记录表
DELETE t from t_lc_call_record t,t_lc_task ta where t.case_no = ta.case_no and t.create_time <= ta.close_date and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 插入行动码历史表
INSERT INTO `t_lc_duncase_action_record_his` (`case_no`, `task_id`, `repay_date`, `repay_amount`, `recheck_date`, `recontact_date`, `action_code`, `remark`, `validate_status`, `create_time`, `modify_time`, `create_by`, `modify_by`)
select
t.case_no,
t.task_id,
t.repay_date,
t.repay_amount,
t.recheck_date,
t.recontact_date,
t.action_code,
t.remark,
t.validate_status,
t.create_time,
t.modify_time,
t.create_by,
t.modify_by
from t_lc_duncase_action_record t,t_lc_task ta where t.task_id = ta.id and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 删除行动码表
DELETE t from t_lc_duncase_action_record t,t_lc_task ta where t.task_id = ta.id and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 插入案件轨迹历史表
INSERT INTO `t_lc_duncase_assign_his` (`custom_name`, `certificate_no`, `case_no`, `task_id`, `operation_id`, `operation_name`, `transfer_type`, `task_status`, `owner_id`, `org_id`, `collect_team_id`, `collect_team_name`, `create_time`, `create_by`, `validate_status`, `remark`, `owner_name`, `org_name`)
select
t.custom_name,
t.certificate_no,
t.case_no,
t.task_id,
t.operation_id,
t.operation_name,
t.transfer_type,
t.task_status,
t.owner_id,
t.org_id,
t.collect_team_id,
t.collect_team_name,
t.create_time,
t.create_by,
t.validate_status,
t.remark,
t.owner_name,
t.org_name
from t_lc_duncase_assign t,t_lc_task ta where t.case_no = ta.case_no and  t.create_time <= ta.close_date and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 删除案件轨迹表
DELETE t from t_lc_duncase_assign t,t_lc_task ta where t.case_no = ta.case_no and  t.create_time <= ta.close_date and ta.task_status = 3 AND NOW() >= (SELECT date_sub(ta.close_date, INTERVAL - days DAY));
-- 插入任务历史表
INSERT INTO `t_lc_task_his` (`case_no`, `certificate_no`, `certificate_type`, `custom_code`, `custom_name`, `arrears_total`, `task_status`, `overdue_days`, `overdue_aging`, `owner_id`, `owner_name`, `org_id`, `org_name`, `close_date`, `old_owner_id`, `task_type`, `allot_type`, `create_time`, `modify_time`, `create_by`, `modify_by`, `old_owner_name`, `robot_task_id`, `robot_call_strategy_id`, `transfer_type`, `enter_coll_date`, `close_case_yhje`, `recently_allot_date`, `recently_follow_up_date`, `hit_rule`, `hit_rule_desc`, `distribution_strategy`, `import_batch_no`, `action_code`, `action_code_value`, `call_sign`, `call_sign_value`, `phone`, `dqyhje`)
SELECT
	case_no,
	certificate_no,
	certificate_type,
	custom_code,
	custom_name,
	arrears_total,
	task_status,
	overdue_days,
	overdue_aging,
	owner_id,
	owner_name,
	org_id,
	org_name,
	close_date,
	old_owner_id,
	task_type,
	allot_type,
	create_time,
	modify_time,
	create_by,
	modify_by,
	old_owner_name,
	robot_task_id,
	robot_call_strategy_id,
	transfer_type,
	enter_coll_date,
	close_case_yhje,
	recently_allot_date,
	recently_follow_up_date,
	hit_rule,
	hit_rule_desc,
	distribution_strategy,
	import_batch_no,
	action_code,
	action_code_value,
	call_sign,
	call_sign_value,
	phone,
	dqyhje
FROM
	t_lc_task
where task_status = 3 AND NOW() >= (SELECT date_sub(close_date, INTERVAL - days DAY));
-- 删除任务表
DELETE from t_lc_task where task_status = 3 AND NOW() >= (SELECT date_sub(close_date, INTERVAL - days DAY));
END
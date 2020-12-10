-- 资产表
UPDATE cur_assets_package set org_casNo = CONCAT(org_casNo,'001') where org_id = 213;
-- 资产临时表
UPDATE cur_assets_package_temp set org_casNo = CONCAT(org_casNo,'001') where org_id = 213;

-- 还款表
UPDATE cur_assets_repayment_package set org_casNo = CONCAT(org_casNo,'001') where org_id = 213;
-- 还款临时表
UPDATE cur_assets_repayment_package_temp set org_casNo = CONCAT(org_casNo,'001') where org_id = 213;

-- 自由导入表
UPDATE free_import set org_casNo = CONCAT(org_casNo,'001') where org_id = 213;

-- 号码状态表
UPDATE phone_status set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 批量外呼表
UPDATE t_lc_batch_call set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 催记表
UPDATE t_lc_call_record set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 催记导入表
-- UPDATE t_lc_collectionrecordimprot set org_caseNo = CONCAT(org_caseNo,'001') where org_id = 213;
-- 催记导入临时表
UPDATE t_lc_collectionrecordimprot_temp set org_caseNo = CONCAT(org_caseNo,'001') where org_id = 213;

-- 客户联系人表
UPDATE t_lc_cust_contact set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 客户工作信息表
UPDATE t_lc_cust_job set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 客户基本信息表
UPDATE t_lc_custinfo set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 案件表
UPDATE t_lc_duncase set case_no = CONCAT(case_no,'001') where org_id = 213;
-- 案件轨迹表
UPDATE t_lc_duncase_assign set case_no = CONCAT(case_no,'001') where org_id = 213;

UPDATE t_lc_inforeporting_buckle set case_no = CONCAT(case_no,'001') where org_id = 213;
UPDATE t_lc_inforeporting_company set case_no = CONCAT(case_no,'001') where org_id = 213;
UPDATE t_lc_inforeporting_reduction set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 信函表
UPDATE t_lc_letter set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 机器人黑名单表
UPDATE t_lc_robot_black set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 催收评分表
UPDATE t_lc_score set org_casno = CONCAT(org_casno,'001') where org_id = 213;

-- 查找记录表
UPDATE t_lc_select_record re,t_lc_task ta set re.case_no = CONCAT(re.case_no,'001') where ta.org_id = 213 and re.case_no = ta.case_no;

-- 任务表
UPDATE t_lc_task set case_no = CONCAT(case_no,'001') where org_id = 213;

-- 兴业资产出催统计表
UPDATE t_lc_urge set org_casNo = CONCAT(org_casNo,'001') where org_id = 213;
-- 兴业资产出催统计临时表
UPDATE t_lc_urge_temp set org_casNo = CONCAT(org_casNo,'001') where org_id = 213;

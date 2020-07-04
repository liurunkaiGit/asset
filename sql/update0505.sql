ALTER TABLE t_lc_duncase ADD action_code varchar(100) COMMENT '行动码键值';
ALTER TABLE t_lc_duncase ADD action_code_value varchar(100) COMMENT '行动码中文';
ALTER TABLE t_lc_duncase ADD call_sign varchar(100) COMMENT '电话码键值';
ALTER TABLE t_lc_duncase ADD call_sign_value varchar(100) COMMENT '电话码中文';
ALTER TABLE t_lc_duncase ADD phone varchar(100) COMMENT '客户手机号码';
ALTER TABLE t_lc_duncase ADD task_type int(5) COMMENT '任务类型';

ALTER TABLE t_lc_task ADD action_code varchar(100) COMMENT '行动码键值';
ALTER TABLE t_lc_task ADD action_code_value varchar(100) COMMENT '行动码中文';
ALTER TABLE t_lc_task ADD call_sign varchar(100) COMMENT '电话码键值';
ALTER TABLE t_lc_task ADD call_sign_value varchar(100) COMMENT '电话码中文';
ALTER TABLE t_lc_task ADD phone varchar(100) COMMENT '客户手机号码';

-- 修改任务表的电话码key值
update t_lc_task t1,(SELECT t.call_sign,t.case_no FROM t_lc_call_record t where t.id in (
select max(id) from t_lc_call_record t group by t.case_no
)) t2
set t1.call_sign = t2.call_sign where t1.case_no = t2.case_no

-- 修改任务表的电话码value值
update t_lc_task t1,sys_dict_data t2
set t1.call_sign_value = t2.dict_label where t1.call_sign = t2.dict_value

-- 修改任务表的行动码key值
update t_lc_task t1,(SELECT t.action_code,t.case_no FROM t_lc_duncase_action_record t where t.id in (
select max(id) from t_lc_duncase_action_record t group by t.case_no
)) t2
set t1.action_code = t2.action_code where t1.case_no = t2.case_no

-- 修改任务表的行动码value值
update t_lc_task t1,sys_dict_data t2
set t1.action_code_value = t2.dict_label where t1.action_code = t2.dict_value

-- 修改任务表中客户联系人手机号
update t_lc_task t1,t_lc_custinfo t2
set t1.phone = t2.phone where t1.case_no = t2.case_no

-- 修改案件表的电话码key值
update t_lc_duncase t1,(SELECT t.call_sign,t.case_no FROM t_lc_call_record t where t.id in (
select max(id) from t_lc_call_record t group by t.case_no
)) t2
set t1.call_sign = t2.call_sign where t1.case_no = t2.case_no

-- 修改案件表的电话码value值
update t_lc_duncase t1,sys_dict_data t2
set t1.call_sign_value = t2.dict_label where t1.call_sign = t2.dict_value

-- 修改案件表的行动码key值
update t_lc_duncase t1,(SELECT t.action_code,t.case_no FROM t_lc_duncase_action_record t where t.id in (
select max(id) from t_lc_duncase_action_record t group by t.case_no
)) t2
set t1.action_code = t2.action_code where t1.case_no = t2.case_no

-- 修改案件表的行动码value值
update t_lc_duncase t1,sys_dict_data t2
set t1.action_code_value = t2.dict_label where t1.action_code = t2.dict_value

-- 修改案件表中客户联系人手机号
update t_lc_duncase t1,t_lc_custinfo t2
set t1.phone = t2.phone where t1.case_no = t2.case_no

-- 修改案件表中任务类型
update t_lc_duncase t1,t_lc_task t2
set t1.task_type = t2.task_type where t1.case_no = t2.case_no and t1.import_batch_no = t2.import_batch_no

alter table cur_assets_package modify column remark varchar(2000);
alter table cur_assets_package modify column bill_address varchar(500);
alter table cur_assets_package modify column tj_firm varchar(200);
alter table cur_assets_package modify column tj_city varchar(200);
alter table cur_assets_package modify column tj_wd varchar(200);
alter table cur_assets_package modify column hkfs varchar(200);
alter table cur_assets_package modify column certificate_address varchar(500);
alter table cur_assets_package modify column regist_address varchar(500);
alter table cur_assets_package modify column email varchar(200);
alter table cur_assets_package modify column customer_mobile varchar(50);
alter table cur_assets_package modify column customer_home_tel varchar(50);
alter table cur_assets_package modify column customer_home_address varchar(500);
alter table cur_assets_package modify column card_post_address varchar(500);
alter table cur_assets_package modify column work_address varchar(500);
alter table cur_assets_package modify column work_name varchar(200);
alter table cur_assets_package modify column work_tel varchar(50);
alter table cur_assets_package modify column first_liaison_mobile varchar(50);
alter table cur_assets_package modify column first_liaison_tel varchar(50);
alter table cur_assets_package modify column second_liaison_mobile varchar(50);
alter table cur_assets_package modify column second_liaison_tel varchar(50);
alter table cur_assets_package modify column three_liaison_mobile varchar(50);
alter table cur_assets_package modify column three_liaison_tel varchar(50);

alter table cur_assets_package_temp modify column remark varchar(2000);
alter table cur_assets_package_temp modify column bill_address varchar(500);
alter table cur_assets_package_temp modify column tj_firm varchar(200);
alter table cur_assets_package_temp modify column tj_city varchar(200);
alter table cur_assets_package_temp modify column tj_wd varchar(200);
alter table cur_assets_package_temp modify column hkfs varchar(200);
alter table cur_assets_package_temp modify column certificate_address varchar(500);
alter table cur_assets_package_temp modify column regist_address varchar(500);
alter table cur_assets_package_temp modify column email varchar(200);
alter table cur_assets_package_temp modify column customer_mobile varchar(50);
alter table cur_assets_package_temp modify column customer_home_tel varchar(50);
alter table cur_assets_package_temp modify column customer_home_address varchar(500);
alter table cur_assets_package_temp modify column card_post_address varchar(500);
alter table cur_assets_package_temp modify column work_address varchar(500);
alter table cur_assets_package_temp modify column work_name varchar(200);
alter table cur_assets_package_temp modify column work_tel varchar(50);
alter table cur_assets_package_temp modify column first_liaison_mobile varchar(50);
alter table cur_assets_package_temp modify column first_liaison_tel varchar(50);
alter table cur_assets_package_temp modify column second_liaison_mobile varchar(50);
alter table cur_assets_package_temp modify column second_liaison_tel varchar(50);
alter table cur_assets_package_temp modify column three_liaison_mobile varchar(50);
alter table cur_assets_package_temp modify column three_liaison_tel varchar(50);

alter table t_lc_custinfo modify column certificate_address varchar(500);
alter table t_lc_custinfo modify column address varchar(500);
alter table t_lc_custinfo modify column census_address varchar(500);

alter table t_lc_duncase modify column custom_phone varchar(50);
alter table t_lc_duncase modify column bill_address varchar(500);
alter table t_lc_duncase modify column phone varchar(50);

alter table t_lc_task modify column phone varchar(50);
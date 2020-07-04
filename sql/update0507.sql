alter table t_lc_call_strategy_config modify column start_call_date VARCHAR(20) COMMENT '呼叫开始时间';
alter table t_lc_call_strategy_config modify column stop_call_date VARCHAR(20) COMMENT '呼叫结束时间';

ALTER TABLE t_lc_custinfo ADD import_batch_no varchar(100) COMMENT '导入批次号';

ALTER TABLE t_lc_cust_job ADD org_id varchar(100) COMMENT '机构编码';
ALTER TABLE t_lc_cust_job ADD org_name varchar(100) COMMENT '机构名称';
ALTER TABLE t_lc_cust_job ADD import_batch_no varchar(100) COMMENT '导入批次号';

ALTER TABLE t_lc_cust_contact ADD org_id varchar(100) COMMENT '机构编码';
ALTER TABLE t_lc_cust_contact ADD org_name varchar(100) COMMENT '机构名称';
ALTER TABLE t_lc_cust_contact ADD import_batch_no varchar(100) COMMENT '导入批次号';

UPDATE t_lc_custinfo t,t_lc_duncase t2 set t.import_batch_no = t2.import_batch_no where t.case_no = t2.case_no;

UPDATE t_lc_cust_job t,t_lc_duncase t2 set t.org_id = t2.org_id,t.org_name = t2.org_name,t.import_batch_no = t2.import_batch_no where t.case_no = t2.case_no;

UPDATE t_lc_cust_contact t,t_lc_duncase t2 set t.org_id = t2.org_id,t.org_name = t2.org_name,t.import_batch_no = t2.import_batch_no where t.case_no = t2.case_no;



alter table cur_assets_package add column package_id varchar(64) DEFAULT NULL COMMENT '资产包id';
alter table cur_assets_package_temp modify column is_exception char(1) DEFAULT '0' COMMENT '是否异常（0新增，1异常，2修改）';
UPDATE cur_assets_package t,assets_connect_package a set t.package_id = a.package_id WHERE t.id= a.assets_id;




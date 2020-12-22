DELIMITER //
DROP PROCEDURE IF EXISTS batch_call_his_data_migrate;
CREATE PROCEDURE `batch_call_his_data_migrate`(in days int(2))
BEGIN
	-- 插入批量外呼历史表
	INSERT INTO t_lc_batch_call_his (
		id,
		batch_no,
		case_no,
		phone,
		contact_name,
		contact_relation,
		exon_num,
		org_id,
		import_batch_no,
		phone_type,
		task_status,
		create_time,
		create_by,
		remark
	) select
		id,
		batch_no,
		case_no,
		phone,
		contact_name,
		contact_relation,
		exon_num,
		org_id,
		import_batch_no,
		phone_type,
		task_status,
		create_time,
		create_by,
		remark
	from t_lc_batch_call where NOW() >= (SELECT date_sub(create_time, INTERVAL - days DAY)) and (task_status = 3 or task_status = 4);
	-- 删除批量外呼表
	delete from t_lc_batch_call where NOW() >= (SELECT date_sub(create_time, INTERVAL - days DAY)) and (task_status = 3 or task_status = 4);
END
//
DELIMITER ;
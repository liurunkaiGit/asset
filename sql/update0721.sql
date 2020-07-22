alter table cur_assets_package MODIFY COLUMN is_exit_collect int(1) DEFAULT NULL COMMENT '结案类型：1出催结案，2回收结案，3到期结案';
alter table cur_assets_package MODIFY COLUMN wa_ye decimal(20,2) DEFAULT NULL COMMENT '结案应还金额';
alter table cur_assets_package MODIFY COLUMN rmb_ye decimal(20,2) DEFAULT NULL COMMENT '委案金额';

alter table cur_assets_package add COLUMN update_time datetime DEFAULT NULL COMMENT '修改时间';
alter table cur_assets_package add COLUMN update_by varchar(64) DEFAULT NULL COMMENT '修改人';

alter table t_lc_task add COLUMN close_type int(1) DEFAULT NULL COMMENT '结案类型：1出催结案，2回收结案，3到期结案';

alter TABLE t_lc_task add COLUMN color char(1) DEFAULT '0' COMMENT '案件颜色：1金，2木，3水，4火，5土';
alter TABLE t_lc_duncase add COLUMN phoneStatus char(1) DEFAULT '0' COMMENT '案件号码状态：0不可联，1可联';
alter TABLE t_lc_duncase add COLUMN remark varchar(255) DEFAULT NULL COMMENT '备注';
update sys_config set config_value='45' where config_key='hisDataMigrateDays';
alter table cur_assets_repayment_package_temp  modify column org_casNo varchar(100)  DEFAULT NULL COMMENT '机构案件号';
alter table cur_assets_package_temp  modify column org_casNo varchar(100)  DEFAULT NULL COMMENT '机构案件号';
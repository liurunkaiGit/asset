alter table cur_assets_package modify COLUMN three_liaison_name varchar(50) DEFAULT NULL COMMENT '第三联系人姓名';
alter table cur_assets_package_his modify COLUMN three_liaison_name varchar(50) DEFAULT NULL COMMENT '第三联系人姓名';
alter table cur_assets_package_temp modify COLUMN three_liaison_name varchar(50) DEFAULT NULL COMMENT '第三联系人姓名';
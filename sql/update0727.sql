ALTER TABLE cur_assets_repayment_package add transferType varchar(20) DEFAULT NULL COMMENT '手别';
ALTER TABLE cur_assets_repayment_package_temp add transferType varchar(20) DEFAULT NULL COMMENT '手别';
ALTER TABLE cur_assets_repayment_package_his add transferType varchar(20) DEFAULT NULL COMMENT '手别';

ALTER TABLE cur_assets_repayment_package add owner_name varchar(255) DEFAULT NULL COMMENT '业务归属人';
ALTER TABLE cur_assets_repayment_package_temp add owner_name varchar(255) DEFAULT NULL COMMENT '业务归属人';
ALTER TABLE cur_assets_repayment_package_his add owner_name varchar(255) DEFAULT NULL COMMENT '业务归属人';

ALTER TABLE cur_assets_repayment_package add rmb_ye decimal(20,2) DEFAULT NULL COMMENT '委案金额';
ALTER TABLE cur_assets_repayment_package_temp add rmb_ye decimal(20,2) DEFAULT NULL COMMENT '委案金额';
ALTER TABLE cur_assets_repayment_package_his add rmb_ye decimal(20,2) DEFAULT NULL COMMENT '委案金额';

ALTER TABLE cur_assets_repayment_package add asset_batch_no varchar(50) DEFAULT NULL COMMENT '资产导入批次号';
ALTER TABLE cur_assets_repayment_package_temp add asset_batch_no varchar(50) DEFAULT NULL COMMENT '资产导入批次号';
ALTER TABLE cur_assets_repayment_package_his add asset_batch_no varchar(50) DEFAULT NULL COMMENT '资产导入批次号';


INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('回款明细导入', (SELECT t.menu_id from sys_menu t where t.menu_name='资产管理'), '11', '/assetspackage/xyRepayment', 'menuItem', 'C', '0', 'assetspackage:xyRepayment:view', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name='回款明细导入'), '1', '#', 'menuItem', 'F', '0', 'assetspackage:xyRepayment:list', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('导入', (SELECT t.menu_id from sys_menu t where t.menu_name='回款明细导入'), '2', '#', 'menuItem', 'F', '0', 'assetspackage:xyRepayment:import', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('导出', (SELECT t.menu_id from sys_menu t where t.menu_name='回款明细导入'), '3', '#', 'menuItem', 'F', '0', 'assetspackage:xyRepayment:export', '#', 'admin', now(), 'admin', now(), '');


insert into sys_config ( config_name, config_key, config_value, config_type, create_by, create_time )values( '催记报表特殊处理机构id', 'orgId', '207', 'N', 'admin', sysdate() )
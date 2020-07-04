-- alter table cur_assets_package add free_import text COMMENT '自由导入';
alter table cur_assets_package_temp add free_import text COMMENT '自由导入';

alter table cur_assets_package_temp modify COLUMN is_exception char(1) DEFAULT '0' COMMENT '是否异常（0正常，1异常，2修改，3不存在）';

drop table if exists free_import;
create table free_import
(
   id                   bigint not null AUTO_INCREMENT comment '主键',
   org_casno            varchar(64) comment '机构案件号',
   org_id               varchar(64),
   import_batch_no      varchar(64),
   value                text comment '自由导入数据',
   create_by            varchar(64) comment '创建人',
   create_time          datetime comment '创建时间',
   primary key (id)
);
alter table free_import comment '自由导入';


INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('更新信息导入', (SELECT t.menu_id from sys_menu t where t.menu_name='资产导入管理'), '5', '/import/assets/updateAssets', 'menuItem', 'C', '0', null, '#', 'admin', '2020-06-08 10:50:25', 'admin', '2020-06-08 10:50:25', '');
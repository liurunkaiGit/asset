alter TABLE cur_assets_package add COLUMN is_compare char(1) DEFAULT NULL COMMENT '是否比较(0未比较,1已比较)';

drop table if exists file_statistics;
create table file_statistics
(
   id                   bigint not null AUTO_INCREMENT comment '主键',
   name                 varchar(64) comment '文件名称',
   size                 int comment '文件大小(kb)',
   org_id               varchar(64),
   import_batch_no      varchar(64),
   create_by            varchar(64) comment '创建人',
   create_time          datetime comment '创建时间',
   primary key (id)
);
alter table file_statistics comment '文件统计表';

drop table if exists t_lc_import_flow_xy;
CREATE TABLE `t_lc_import_flow_xy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `import_batch_no` varchar(20) NOT NULL COMMENT '导入批次号，年月日时分秒生成',
  `org_id` varchar(10) DEFAULT NULL COMMENT '委托方id',
  `org_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '委托方名称',
  `total_money` decimal(13,2) DEFAULT NULL COMMENT '总金额',
  `total_num` int(11) DEFAULT NULL COMMENT '总笔数',
  `add_num` int(11) DEFAULT NULL COMMENT '新增案件数',
  `modify_num` int(11) DEFAULT NULL COMMENT '更新案件数',
  `urge_num` int(11) DEFAULT NULL COMMENT '出催案件数',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=utf8;
alter table t_lc_import_flow_xy comment '兴业资产导入流水表';


drop table if exists t_lc_urge;
CREATE TABLE `t_lc_urge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `import_batch_no` varchar(20) NOT NULL COMMENT '导入批次号，年月日时分秒生成',
  `org_id` varchar(10) DEFAULT NULL COMMENT '委托方id',
  `org_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '委托方名称',
  `org_casNo` varchar(64) DEFAULT NULL COMMENT '机构案件号',
  `custome_name` varchar(64) DEFAULT NULL COMMENT '客户姓名',
  `waje` decimal(20,2) DEFAULT NULL COMMENT '委案金额',
  `dqyhje` decimal(20,2) DEFAULT NULL COMMENT '当前已还金额',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '业务归属人id',
  `owner_name` varchar(64) DEFAULT NULL COMMENT '业务归属人名称',
  `type` char(1) DEFAULT NULL COMMENT '类型(1预测结清,2部分还款)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=utf8;
alter table t_lc_urge comment '兴业资产出催统计表';

INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('兴业消金资产导入', (SELECT t.menu_id from sys_menu t where t.menu_name='资产管理'), '9', '/xyImport/assets', 'menuItem', 'C', '0', null, '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('出催统计', (SELECT t.menu_id from sys_menu t where t.menu_name='资产管理'), '10', '/xyImport/assets/urge', 'menuItem', 'C', '0', null, '#', 'admin', now(), 'admin', now(), '');

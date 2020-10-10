alter TABLE t_lc_urge add COLUMN transferType varchar(20) DEFAULT NULL COMMENT '手别';
alter TABLE t_lc_urge add COLUMN rcr date DEFAULT NULL COMMENT '入催日';
alter TABLE t_lc_urge add COLUMN login_name varchar(20) DEFAULT NULL COMMENT '业务归属人登录名称';

DROP TABLE IF EXISTS `t_lc_urge_temp`;
CREATE TABLE `t_lc_urge_temp` (
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
  `transferType` varchar(20) DEFAULT NULL COMMENT '手别',
  `rcr` date DEFAULT NULL COMMENT '入催日',
  `login_name` varchar(20) DEFAULT NULL COMMENT '业务归属人登录名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='兴业资产出催统计临时表';
alter table t_lc_cust_contact add phone_status varchar(16) DEFAULT NULL  COMMENT '手机号码状态';

CREATE TABLE `phone_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `case_no` varchar(64) DEFAULT NULL COMMENT '案件编号',
  `waje` decimal(13,2) DEFAULT NULL COMMENT '委案金额',
  `jayhje` decimal(13,2) DEFAULT NULL COMMENT '结案应还金额',
  `phone` varchar(64) DEFAULT NULL COMMENT '电话号码',
  `relation` int(100) DEFAULT NULL COMMENT '与本人关系',
  `phoneStatus` varchar(64) DEFAULT NULL COMMENT '号码状态(为百融号码状态，-1表示失败)',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` datetime DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='号码状态表';
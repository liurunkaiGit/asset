-- ----------------------------
-- Table structure for t_lc_report_call_len_num
-- ----------------------------
DROP TABLE IF EXISTS `t_lc_report_call_len_num`;
CREATE TABLE `t_lc_report_call_len_num` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `report_date` date DEFAULT NULL COMMENT '报表日期',
  `seat_group` varchar(10) DEFAULT NULL COMMENT '用户组别',
  `seat_num` varchar(10) DEFAULT NULL COMMENT '坐席工号',
  `seat_name` varchar(10) DEFAULT NULL COMMENT '坐席名称',
  `zero_nine_call_len` varchar(255) DEFAULT NULL COMMENT '0-9通时',
  `nine_ten_call_len` varchar(255) DEFAULT NULL COMMENT '9-10通时',
  `ten_eleven_call_len` varchar(255) DEFAULT NULL COMMENT '10-11通时',
  `eleven_twelve_call_len` varchar(255) DEFAULT NULL COMMENT '11-12通时',
  `twelve_thirteen_call_len` varchar(255) DEFAULT NULL COMMENT '12-13通时',
  `thirteen_fourteen_call_len` varchar(255) DEFAULT NULL COMMENT '13-14通时',
  `fourteen_fifteen_call_len` varchar(255) DEFAULT NULL COMMENT '14-15通时',
  `fifteen_sixteen_call_len` varchar(255) DEFAULT NULL COMMENT '15-16通时',
  `sixteen_seventeen_call_len` varchar(255) DEFAULT NULL COMMENT '16-17通时',
  `seventeen_eighteen_call_len` varchar(255) DEFAULT NULL COMMENT '17-18通时',
  `eighteen_ninteen_call_len` varchar(255) DEFAULT NULL COMMENT '18-19通时',
  `ninteen_twenty_call_len` varchar(255) DEFAULT NULL COMMENT '19-20通时',
  `twenty_twentyfour_call_len` varchar(255) DEFAULT NULL COMMENT '20-24通时',
  `total_call_len` varchar(255) DEFAULT NULL COMMENT '合计通时',
  `zero_nine_call_num` int(5) DEFAULT NULL COMMENT '0-9拨打',
  `nine_ten_call_num` int(5) DEFAULT NULL COMMENT '9-10拨打',
  `ten_eleven_call_num` int(5) DEFAULT NULL COMMENT '10-11拨打',
  `eleven_twelve_call_num` int(5) DEFAULT NULL COMMENT '11-12拨打',
  `twelve_thirteen_call_num` int(5) DEFAULT NULL COMMENT '12-13拨打',
  `thirteen_fourteen_call_num` int(5) DEFAULT NULL COMMENT '13-14拨打',
  `fourteen_fifteen_call_num` int(5) DEFAULT NULL COMMENT '14-15拨打',
  `fifteen_sixteen_call_num` int(5) DEFAULT NULL COMMENT '15-16拨打',
  `sixteen_seventeen_call_num` int(5) DEFAULT NULL COMMENT '16-17拨打',
  `seventeen_eighteen_call_num` int(5) DEFAULT NULL COMMENT '17-18拨打',
  `eighteen_ninteen_call_num` int(5) DEFAULT NULL COMMENT '18-19拨打',
  `ninteen_twenty_call_num` int(5) DEFAULT NULL COMMENT '19-20拨打',
  `twenty_twentyfour_call_num` int(5) DEFAULT NULL COMMENT '20-24拨打',
  `total_call_num` int(5) DEFAULT NULL COMMENT '合计拨打',
  `zero_nine_call_in_num` int(5) DEFAULT NULL COMMENT '0-9通次',
  `nine_ten_call_in_num` int(5) DEFAULT NULL COMMENT '9-10通次',
  `ten_eleven_call_in_num` int(5) DEFAULT NULL COMMENT '10-11通次',
  `eleven_twelve_call_in_num` int(5) DEFAULT NULL COMMENT '11-12通次',
  `twelve_thirteen_call_in_num` int(5) DEFAULT NULL COMMENT '12-13通次',
  `thirteen_fourteen_call_in_num` int(5) DEFAULT NULL COMMENT '13-14通次',
  `fourteen_fifteen_call_in_num` int(5) DEFAULT NULL COMMENT '14-15通次',
  `fifteen_sixteen_call_in_num` int(5) DEFAULT NULL COMMENT '15-16通次',
  `sixteen_seventeen_call_in_num` int(5) DEFAULT NULL COMMENT '16-17通次',
  `seventeen_eighteen_call_in_num` int(5) DEFAULT NULL COMMENT '17-18通次',
  `eighteen_ninteen_call_in_num` int(5) DEFAULT NULL COMMENT '18-19通次',
  `ninteen_twenty_call_in_num` int(5) DEFAULT NULL COMMENT '19-20通次',
  `twenty_twentyfour_call_in_num` int(5) DEFAULT NULL COMMENT '20-24通次',
  `total_call_in_num` int(5) DEFAULT NULL COMMENT '合计通次',
  `org_id` varchar(64) DEFAULT NULL COMMENT '机构id',
  `org_name` varchar(64) DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通时通次报表';
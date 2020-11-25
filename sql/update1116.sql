-- 脱敏
alter table org_package add COLUMN is_desensitization char(1) DEFAULT '0' COMMENT '是否脱敏：0不脱敏,1全部脱敏,2仅职场脱敏,3职场外脱敏';
insert into sys_config ( config_name, config_key, config_value, config_type, create_by, remark, create_time )values( '不脱敏的用户', 'notDesensitizationUser', 'admin', 'Y', 'admin', '多个用逗号隔开', sysdate() );

--手机号码查询案件菜单
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('手机号码查询案件', (SELECT t.menu_id from sys_menu t where t.menu_name='催收管理'), '19', '/collect/selectCaseByPhone', 'menuItem', 'C', '0', 'collect:selectCaseByPhone:view', '#', 'admin', now(), 'admin', now(), '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('查询', (SELECT t.menu_id from sys_menu t where t.menu_name='手机号码查询案件'), '1', '#', 'menuItem', 'F', '0', 'collect:selectCaseByPhone:list', '#', 'admin', now(), 'admin', now(), '');



-- 区号配置
CREATE TABLE `area_code_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `area_code` varchar(11) NOT NULL COMMENT '区号',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区号配置表';

INSERT INTO `area_code_config` VALUES (1, '010', '北京市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (2, '021', '上海市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (3, '022', '天津市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (4, '023', '重庆市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (5, '020', '广州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (6, '024', '沈阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (7, '025', '南京', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (8, '027', '武汉', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (9, '028', '成都', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (10, '029', '西安（含咸阳）', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (11, '0311', '石家庄', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (12, '0312', '保定', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (13, '0313', '张家口', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (14, '0314', '承德', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (15, '0315', '唐山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (16, '0316', '廊坊', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (17, '0317', '沧州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (18, '0318', '衡水', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (19, '0319', '邢台', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (20, '0310', '邯郸', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (21, '0335', '秦皇岛', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (22, '0349', '朔州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (23, '0351', '太原', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (24, '0352', '大同', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (25, '0353', '阳泉', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (26, '0354', '晋中', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (27, '0355', '长治', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (28, '0356', '晋城', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (29, '0357', '临汾', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (30, '0358', '吕梁', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (31, '0359', '运城', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (32, '0350', '忻州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (33, '0371', '郑州、开封', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (34, '0372', '安阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (35, '0373', '新乡', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (36, '0374', '许昌', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (37, '0375', '平顶山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (38, '0376', '信阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (39, '0377', '南阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (40, '0379', '洛阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (41, '0370', '商丘', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (42, '0391', '焦作、济源', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (43, '0392', '鹤壁', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (44, '0393', '濮阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (45, '0394', '周口', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (46, '0395', '漯河', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (47, '0396', '驻马店', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (48, '0398', '三门峡', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (49, '0411', '大连', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (50, '0412', '鞍山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (51, '0415', '丹东', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (52, '0416', '锦州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (53, '0417', '营口', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (54, '0418', '阜新', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (55, '0419', '辽阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (56, '0421', '朝阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (57, '0427', '盘锦', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (58, '0429', '葫芦岛', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (59, '0431', '长春', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (60, '0432', '吉林', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (61, '0433', '延边', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (62, '0434', '四平', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (63, '0435', '通化', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (64, '0436', '白城', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (65, '0437', '辽源', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (66, '0438', '松原', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (67, '0439', '白山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (68, '0451', '哈尔滨', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (69, '0452', '齐齐哈尔', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (70, '0453', '牡丹江', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (71, '0454', '佳木斯', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (72, '0455', '绥化', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (73, '0456', '黑河', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (74, '0457', '大兴安岭地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (75, '0458', '伊春', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (76, '0459', '大庆', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (77, '0464', '七台河', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (78, '0467', '鸡西', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (79, '0468', '鹤岗', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (80, '0469', '双鸭山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (81, '0471', '呼和浩特', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (82, '0472', '包头', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (83, '0473', '乌海', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (84, '0474', '乌兰察布', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (85, '0475', '通辽', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (86, '0476', '赤峰', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (87, '0477', '鄂尔多斯', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (88, '0478', '巴彦淖尔', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (89, '0479', '锡林郭勒盟', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (90, '0470', '呼伦贝尔', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (91, '0482', '兴安盟', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (92, '0483', '阿拉善盟', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (93, '0511', '镇江', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (94, '0512', '苏州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (95, '0513', '南通', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (96, '0514', '扬州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (97, '0515', '盐城', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (98, '0516', '徐州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (99, '0517', '淮安', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (100, '0518', '连云港', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (101, '0519', '常州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (102, '0510', '无锡', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (103, '0523', '泰州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (104, '0527', '宿迁', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (105, '0531', '济南', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (106, '0532', '青岛', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (107, '0533', '淄博', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (108, '0534', '德州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (109, '0535', '烟台', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (110, '0536', '潍坊', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (111, '0537', '济宁', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (112, '0538', '泰安', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (113, '0539', '临沂', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (114, '0530', '菏泽', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (115, '0543', '滨州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (116, '0546', '东营', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (117, '0631', '威海', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (118, '0632', '枣庄', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (119, '0633', '日照', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (120, '0634', '莱芜', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (121, '0635', '聊城', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (122, '0551', '合肥', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (123, '0552', '蚌埠', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (124, '0553', '芜湖', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (125, '0554', '淮南', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (126, '0555', '马鞍山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (127, '0556', '安庆', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (128, '0557', '宿州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (129, '0558', '阜阳、亳州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (130, '0559', '黄山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (131, '0550', '滁州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (132, '0561', '淮北', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (133, '0562', '铜陵', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (134, '0563', '宣城', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (135, '0564', '六安', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (136, '0566', '池州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (137, '0571', '杭州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (138, '0572', '湖州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (139, '0573', '嘉兴', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (140, '0574', '宁波', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (141, '0575', '绍兴', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (142, '0576', '台州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (143, '0577', '温州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (144, '0578', '丽水', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (145, '0579', '金华', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (146, '0570', '衢州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (147, '0580', '舟山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (148, '0591', '福州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (149, '0592', '厦门', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (150, '0593', '宁德', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (151, '0594', '莆田', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (152, '0595', '泉州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (153, '0596', '漳州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (154, '0597', '龙岩', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (155, '0598', '三明', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (156, '0599', '南平', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (157, '0631', '威海市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (158, '0632', '枣庄市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (159, '0633', '日照市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (160, '0634', '莱芜市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (161, '0635', '聊城市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (162, '0662', '阳江市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (163, '0663', '揭阳市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (164, '0668', '茂名市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (165, '0660', '汕尾市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (166, '0760', '中山市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (167, '0691', '景洪市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (168, '0692', '潞西市', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (169, '0711', '鄂州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (170, '0712', '孝感', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (171, '0713', '黄冈', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (172, '0714', '黄石', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (173, '0715', '咸宁', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (174, '0716', '荆州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (175, '0717', '宜昌', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (176, '0718', '恩施土家族苗族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (177, '0719', '十堰、神农架林区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (178, '0710', '襄阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (179, '0722', '随州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (180, '0724', '荆门', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (181, '0728', '仙桃', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (182, '0731', '长沙', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (183, '0734', '衡阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (184, '0735', '郴州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (185, '0736', '常德', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (186, '0737', '益阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (187, '0738', '娄底', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (188, '0739', '邵阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (189, '0730', '岳阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (190, '0743', '湘西土家族苗族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (191, '0744', '张家界', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (192, '0745', '怀化', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (193, '0746', '永州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (194, '0751', '韶关', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (195, '0752', '惠州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (196, '0753', '梅州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (197, '0754', '汕头', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (198, '0755', '深圳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (199, '0756', '珠海', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (200, '0757', '佛山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (201, '0758', '肇庆', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (202, '0759', '湛江', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (203, '0750', '江门', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (204, '0762', '河源', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (205, '0763', '清远', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (206, '0766', '云浮', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (207, '0768', '潮州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (208, '0769', '东莞', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (209, '0760', '中山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (210, '0662', '阳江', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (211, '0663', '揭阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (212, '0668', '茂名', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (213, '0660', '汕尾', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (214, '0771', '南宁、崇左', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (215, '0772', '柳州、来宾', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (216, '0773', '桂林', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (217, '0774', '梧州、贺州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (218, '0776', '百色', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (219, '0777', '钦州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (220, '0778', '河池', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (221, '0779', '北海', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (222, '0775', '贵港、玉林', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (223, '0770', '防城港', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (224, '0791', '南昌', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (225, '0792', '九江', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (226, '0793', '上饶', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (227, '0794', '抚州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (228, '0795', '宜春', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (229, '0796', '吉安', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (230, '0797', '赣州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (231, '0798', '景德镇', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (232, '0799', '萍乡', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (233, '0790', '新余', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (234, '0701', '鹰潭', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (235, '0812', '攀枝花', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (236, '0813', '自贡', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (237, '0816', '绵阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (238, '0817', '南充', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (239, '0818', '达州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (240, '0825', '遂宁', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (241, '0826', '广安', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (242, '0827', '巴中', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (243, '0831', '宜宾', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (244, '0832', '内江', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (245, '0833', '乐山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (246, '0834', '凉山彝族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (247, '0835', '雅安', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (248, '0836', '甘孜藏族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (249, '0837', '阿坝藏族羌族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (250, '0838', '德阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (251, '0839', '广元', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (252, '0830', '泸州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (253, '0851', '贵阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (254, '0852', '遵义', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (255, '0853', '安顺', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (256, '0854', '黔南布依族苗族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (257, '0855', '黔东南苗族侗族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (258, '0856', '铜仁', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (259, '0857', '毕节', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (260, '0858', '六盘水', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (261, '0859', '黔西南布依族苗族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (262, '0871', '昆明', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (263, '0872', '大理白族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (264, '0873', '红河哈尼族彝族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (265, '0874', '曲靖', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (266, '0875', '保山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (267, '0876', '文山壮族苗族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (268, '0877', '玉溪', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (269, '0878', '楚雄彝族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (270, '0879', '普洱', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (271, '0870', '昭通', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (272, '0883', '临沧', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (273, '0886', '怒江傈僳族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (274, '0887', '迪庆藏族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (275, '0888', '丽江', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (276, '0691', '西双版纳傣族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (277, '0692', '德宏傣族景颇族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (278, '0891', '拉萨', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (279, '0892', '日喀则地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (280, '0893', '山南地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (281, '0894', '林芝地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (282, '0895', '昌都地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (283, '0896', '那曲地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (284, '0897', '阿里地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (285, '0898', '海南', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (286, '0911', '延安', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (287, '0912', '榆林', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (288, '0913', '渭南', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (289, '0914', '商洛', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (290, '0915', '安康', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (291, '0916', '汉中', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (292, '0917', '宝鸡', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (293, '0919', '铜川', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (294, '0931', '兰州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (295, '0932', '定西', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (296, '0933', '平凉', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (297, '0934', '庆阳', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (298, '0935', '武威、金昌', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (299, '0936', '张掖', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (300, '0937', '酒泉、嘉峪关', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (301, '0938', '天水', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (302, '0939', '陇南', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (303, '0930', '临夏回族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (304, '0941', '甘南藏族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (305, '0943', '白银', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (306, '0951', '银川', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (307, '0952', '石嘴山', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (308, '0953', '吴忠', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (309, '0954', '固原', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (310, '0955', '中卫', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (311, '0971', '西宁', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (312, '0972', '海东地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (313, '0973', '黄南藏族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (314, '0974', '海南藏族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (315, '0975', '果洛藏族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (316, '0976', '玉树藏族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (317, '0977', '海西蒙古族藏族自治州─德令哈', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (318, '0979', '海西蒙古族藏族自治州─格尔木', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (319, '0970', '海北藏族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (320, '0991', '乌鲁木齐', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (321, '0992', '伊犁哈萨克自治州─奎屯', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (322, '0993', '石河子', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (323, '0994', '昌吉回族自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (324, '0995', '吐鲁番地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (325, '0996', '巴音郭楞蒙古自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (326, '0997', '阿克苏地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (327, '0998', '喀什地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (328, '0999', '伊犁哈萨克自治州─伊宁', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (329, '0990', '克拉玛依', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (330, '0901', '塔城地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (331, '0902', '哈密地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (332, '0903', '和田地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (333, '0906', '阿勒泰地区', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (334, '0908', '克孜勒苏柯尔克孜自治州', 'admin', '2020-11-16 18:19:07');
INSERT INTO `area_code_config` VALUES (335, '0909', '博尔塔拉蒙古自治州', 'admin', '2020-11-16 18:19:07');

-- 查询条件
update t_lc_column_query set column_value='Fresh-新任务|Search1-联系方式有效|Search2-联系方式无效|Found1-找到本人|Found2-找到联系人|Talking-谈判中|PTP-承诺还款|Check-检查付款|TS-投诉|Transfer-建议转移|QT-其它' where table_name = 't_lc_task' and column_name = 'action_code' and column_type = 'dict';
update t_lc_column_query set column_value='Fresh-新任务|Search1-联系方式有效|Search2-联系方式无效|Found1-找到本人|Found2-找到联系人|Talking-谈判中|PTP-承诺还款|Check-检查付款|TS-投诉|Transfer-建议转移|QT-其它' where table_name = 't_lc_duncase' and column_name = 'action_code' and column_type = 'dict';

-- 回款导入新增员工工号
alter TABLE cur_assets_repayment_package_temp add COLUMN job_no varchar(64) DEFAULT NULL COMMENT '员工工牌号';
alter TABLE cur_assets_repayment_package add COLUMN job_no varchar(64) DEFAULT NULL COMMENT '员工工牌号';
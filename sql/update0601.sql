update sys_config SET config_value='theme-light' WHERE config_key='sys.index.sideTheme' AND config_name='主框架页-侧边栏主题';
update cur_assets_package set rmb_ye = rmb_qkzje WHERE org_id='212' AND create_time >'2020-05-30 00:00:00';
alter table t_lc_call_record add star int DEFAULT 0 COMMENT '关注度';
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('现场监控', '0', '8', '#', 'menuItem', 'M', '0', '', 'fa fa-binoculars', 'admin', '2020-06-03 19:27:15', 'admin', '2020-06-03 19:27:15', '');
-- INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('录音查听', '2158', '1', '/call/record/listenRecord', 'menuItem', 'C', '0', 'call:record:view', '#', 'admin', '2020-06-03 19:27:15', 'admin', '2020-06-03 19:27:15', '');
INSERT INTO sys_menu ( `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)VALUES ('录音查听', (SELECT t.menu_id from sys_menu t where t.menu_name='现场监控'), '1', '/call/record/listenRecord', 'menuItem', 'C', '0', 'call:record:view', '#', 'admin', '2020-06-03 19:27:15', 'admin', '2020-06-03 19:27:15', '');


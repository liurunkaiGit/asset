INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('21', '案件回收', '21', 'sys_task_type', NULL, NULL, 'Y', '0', 'zhang', '2020-09-07 16:06:54', '', NULL, '案件回收');
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('4', '未分配-回收', '4', 'sys_task_state', NULL, NULL, 'Y', '0', 'zhang', '2020-09-07 16:08:26', '', NULL, '未分配-回收');

INSERT INTO `sys_config` (`config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('捷信机构id', 'jxOrgId', '212', 'Y', 'zhang', '2020-09-07 19:31:39', '', NULL, '捷信机构id');

update t_lc_column_query
set column_value = '1:初次生成|2:重新分派|3:临时代理|4:协助催收|5:临时代理回收|7:结案转移|10:协助催收申请|11:停催申请|12:停止催收|13:停止催收激活|14:停止催收拒绝|15:拒绝协催|16:机器人协催|17:机器人拉回|21:案件回收'
where column_name = 'task_type';

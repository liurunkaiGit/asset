alter table t_lc_exon_num add call_platform char(20) DEFAULT NULL COMMENT '话务平台';
update t_lc_exon_num set call_platform='PA';

alter table ext_phone add call_platform char(20) DEFAULT NULL COMMENT '话务平台';
update ext_phone set call_platform='PA';
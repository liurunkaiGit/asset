update t_lc_column_query t set table_prefix = 'd' where t.table_name = 't_lc_duncase' and  table_prefix = 't';
update t_lc_column_query t set table_prefix = 't' where t.table_name = 't_lc_duncase' and  table_prefix = 'ta';


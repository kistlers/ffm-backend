alter table t_player modify column image mediumblob;
update t_player set image = null where true;
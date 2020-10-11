alter table t_player modify column image blob;
update t_player set image = null where true;
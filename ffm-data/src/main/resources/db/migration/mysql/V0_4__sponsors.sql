create table t_sponsor
(
    pk_sponsor_id int(10) auto_increment not null primary key,
    name          varchar(200)           not null,
    url           varchar(500),
    image         mediumblob,
    ordering      int                    not null
);
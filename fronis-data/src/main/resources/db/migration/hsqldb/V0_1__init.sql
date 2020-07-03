SET DATABASE SQL SYNTAX MYS TRUE;

CREATE TABLE t_player
(
    pk_player_id  INT(10) IDENTITY NOT NULL,
    first_name    VARCHAR(100) NOT NULL,
    last_name     VARCHAR(100) NOT NULL,
    player_number INT(10),
    PRIMARY KEY (pk_player_id)
);

replace into t_player(first_name, last_name, player_number)
values ('Severin', 'Kistler', 29);
replace into t_player(first_name, last_name, player_number)
values ('Nicolas', 'Kistler', 13);
replace into t_player(first_name, last_name, player_number)
values ('Thomas', 'Stüssi', 7);
replace into t_player(first_name, last_name, player_number)
values ('Serge', 'Brun', 22);
replace into t_player(first_name, last_name, player_number)
values ('Manfred', 'Berzel', 17);
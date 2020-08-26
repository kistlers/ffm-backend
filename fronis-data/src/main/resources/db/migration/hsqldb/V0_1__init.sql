SET DATABASE SQL SYNTAX MYS TRUE;

CREATE TABLE t_player
(
    pk_player_id  INT(10) AUTO_INCREMENT NOT NULL,
    first_name    VARCHAR(100)           NOT NULL,
    last_name     VARCHAR(100)           NOT NULL,
    short_name    VARCHAR(100),
    player_number INT(10),
    position      VARCHAR(100),
    year_of_birth INT(20),
    image         MEDIUMTEXT,
    PRIMARY KEY (pk_player_id)
);

replace into t_player(first_name, last_name, short_name, player_number, position, year_of_birth, image)
values ('Severin', 'Kistler', 'Sevi', 29, 'FIELD', 1997, null);
replace into t_player(first_name, last_name, short_name, player_number, position, year_of_birth, image)
values ('Nicolas', 'Kistler', 'Nici', 13, 'FIELD', 1992, null);
replace into t_player(first_name, last_name, short_name, player_number, position, year_of_birth, image)
values ('Thomas', 'Stüssi', 'Stü', 7, 'FIELD', 1988, null);
replace into t_player(first_name, last_name, short_name, player_number, position, year_of_birth, image)
values ('Remo', 'Eberle', 'Showgoalie', 1, 'GOAL', 1985, null);
replace into t_player(first_name, last_name, short_name, player_number, position, year_of_birth, image)
values ('Jack', 'Hösli', 'Ebi', null, 'STAFF', 1960, null);
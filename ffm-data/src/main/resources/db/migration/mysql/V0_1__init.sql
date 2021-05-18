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
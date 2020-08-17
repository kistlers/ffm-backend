CREATE TABLE t_player
(
    pk_player_id  INT(10) AUTO_INCREMENT NOT NULL,
    first_name    VARCHAR(100)           NOT NULL,
    last_name     VARCHAR(100)           NOT NULL,
    short_name     VARCHAR(100),
    player_number VARCHAR(10),
    year_of_birth VARCHAR(4),
    image VARCHAR,
    PRIMARY KEY (pk_player_id)
);


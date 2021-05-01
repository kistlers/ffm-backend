CREATE TABLE t_news
(
    pk_news_id INT(10) AUTO_INCREMENT NOT NULL,
    title      VARCHAR(255)           NOT NULL,
    text       TEXT                   NOT NULL,
    created_at DATETIME               NOT NULL,
    image      MEDIUMBLOB,
    PRIMARY KEY (pk_news_id)
) DEFAULT CHARSET = utf8mb4
  COLLATE utf8mb4_unicode_ci;
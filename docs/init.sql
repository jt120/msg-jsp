DROP DATABASE IF EXISTS msg;
CREATE DATABASE msg;
USE msg;
CREATE TABLE t_user (
  id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(100)     NOT NULL,
  password    VARCHAR(100)     NOT NULL,
  nickname    VARCHAR(100)     NOT NULL,
  status      TINYINT UNSIGNED NOT NULL,
  type        TINYINT UNSIGNED NOT NULL,
  create_time TIMESTAMP        NOT NULL,
  KEY create_time_idx (create_time)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

CREATE TABLE t_msg (
  id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  title       VARCHAR(255) NOT NULL,
  content     TEXT         NOT NULL,
  create_time TIMESTAMP    NOT NULL,
  update_time TIMESTAMP    NOT NULL,
  user_id     INT UNSIGNED NOT NULL,
  KEY create_time_idx (create_time),
  KEY user_id_idx (user_id)

)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

CREATE TABLE t_comment (
  id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  content     TEXT         NOT NULL,
  create_time TIMESTAMP    NOT NULL,
  user_id     INT UNSIGNED NOT NULL,
  msg_id      INT UNSIGNED NOT NULL,
  KEY create_time_idx (create_time),
  KEY user_id_idx (user_id),
  KEY msg_id_idx (msg_id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;
GRANT ALL ON msg.* TO 'hello'@'localhost'
IDENTIFIED BY 'hello';
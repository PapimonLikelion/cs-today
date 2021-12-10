CREATE TABLE `content` (
    `id` bigint not null auto_increment,
    `text` LONGTEXT,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
    `id` bigint not null auto_increment,
    `active` bit(1) not null,
    `content_id` bigint,
    `date_passed_from_last_mail` integer not null,
    `email` varchar(255),
    `mail_interval` integer not null,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

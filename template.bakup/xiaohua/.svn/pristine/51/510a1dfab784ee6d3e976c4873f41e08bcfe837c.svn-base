CREATE TABLE `advertiser` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`client_app` VARCHAR(50) NOT NULL,
	`platform` VARCHAR(50) NOT NULL,
	`app` VARCHAR(50) NOT NULL,
	`price` FLOAT NOT NULL DEFAULT '0',
	`app_host` VARCHAR(100) NOT NULL,
	`app_id` VARCHAR(100) NOT NULL,
	`campaign` VARCHAR(100) NOT NULL,
	`key` VARCHAR(100) NOT NULL,
	`valid` TINYINT(4) NOT NULL DEFAULT '1',
	PRIMARY KEY (`id`),
	INDEX `app_id_unique` (`campaign`, `app_id`, `valid`)
)
COMMENT='广告表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

INSERT INTO `advertiser` (`id`, `platform`, `app`, `price`, `app_host`, `app_id`, `campaign`, `key`, `valid`) VALUES (1, '爱普动力', '有你找的', 1.5, 'http://114.215.179.200:91/', '2434', '2529', '76cedd08bf3023bfc23359a0c0b66c8a', 1);

CREATE TABLE `promotion` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_app` VARCHAR(50) NULL DEFAULT NULL,
	`campaign` VARCHAR(100) NOT NULL,
	`app_id` VARCHAR(100) NULL DEFAULT NULL,
	`app_name` VARCHAR(100) NULL DEFAULT NULL,
	`uinfo` VARCHAR(100) NULL DEFAULT NULL,
	`openudid` VARCHAR(100) NULL DEFAULT NULL,
	`idfa` VARCHAR(100) NULL DEFAULT NULL,
	`confirm_url` VARCHAR(100) NULL DEFAULT NULL,
	`user` INT(11) NULL DEFAULT NULL,
	`price` FLOAT NOT NULL DEFAULT '0',
	`status` TINYINT(4) NOT NULL DEFAULT '0',
	`sync_status` TINYINT(4) NOT NULL DEFAULT '0',
	`latest_sync_time` DATETIME NULL DEFAULT NULL,
	`sync_times` TINYINT(4) NOT NULL DEFAULT '0',
	`create_time` DATETIME NOT NULL,
	`active_time` DATETIME NULL DEFAULT NULL,
	`active_msg` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='推广记录'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;


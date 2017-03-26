--
-- 应用互推功能：需要推荐的目标应用的 包名称
-- 2014-06-09
--
ALTER TABLE `channel`
	ADD COLUMN `package_name` VARCHAR(100) NULL DEFAULT NULL AFTER `name`;

--
-- 企业开发账号的设备的UDID
-- 2014-06-22
--
ALTER TABLE `user`
	ADD COLUMN `udid` VARCHAR(50) NULL DEFAULT NULL AFTER `token`;

--
-- 企业版 每个设备号每个广告只能做一次任务
-- 20140625
--
ALTER TABLE `integral`
	ADD COLUMN `udid` VARCHAR(100) NULL DEFAULT NULL AFTER `ip`;



--
-- 增加图片服务器接口（baidu-pics 模块）需要的表
--  2014-06-29
--
CREATE TABLE `category` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`unique_key` INT(11) NULL DEFAULT NULL,
	`name` VARCHAR(50) NOT NULL,
	`data_nsclick` VARCHAR(200) NULL DEFAULT NULL,
	`color` VARCHAR(10) NOT NULL DEFAULT '#FFFFFF',
	`source` VARCHAR(200) NULL DEFAULT NULL,
	`parent` INT(11) NULL DEFAULT NULL,
	`sub_selector` VARCHAR(50) NULL DEFAULT NULL,
	`pics_sum` INT(11) NOT NULL DEFAULT '0',
	`valid` INT(11) NOT NULL DEFAULT '1',
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=MyISAM;

CREATE TABLE `images` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`bid` VARCHAR(50) NOT NULL,
	`abs` VARCHAR(100) NULL DEFAULT NULL,
	`description` VARCHAR(500) NULL DEFAULT NULL,
	`tags` VARCHAR(100) NULL DEFAULT NULL,
	`image_url` VARCHAR(255) NULL DEFAULT NULL,
	`image_widht` SMALLINT(6) NOT NULL DEFAULT '0',
	`image_height` SMALLINT(6) NOT NULL DEFAULT '0',
	`download_url` VARCHAR(255) NULL DEFAULT NULL,
	`thumbnail_url` VARCHAR(255) NULL DEFAULT NULL,
	`thumbnail_width` SMALLINT(6) NOT NULL DEFAULT '0',
	`thumbnail_height` SMALLINT(6) NOT NULL DEFAULT '0',
	`thumb_large_url` VARCHAR(255) NULL DEFAULT NULL,
	`thumb_large_widht` SMALLINT(6) NOT NULL DEFAULT '0',
	`thumb_large_height` SMALLINT(6) NOT NULL DEFAULT '0',
	`obj_url` VARCHAR(255) NULL DEFAULT NULL,
	`from_url` VARCHAR(255) NULL DEFAULT NULL,
	`share_url` VARCHAR(255) NULL DEFAULT NULL,
	`like_number` INT(11) NOT NULL DEFAULT '0',
	`download_num` INT(11) NOT NULL DEFAULT '0',
	`collect_num` INT(11) NOT NULL DEFAULT '0',
	`valid` TINYINT(4) NOT NULL DEFAULT '1',
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `img_category` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`img_id` BIGINT(20) NOT NULL,
	`c_id` INT(11) NOT NULL,
	`valid` INT(11) NOT NULL DEFAULT '1',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `Index 2` (`img_id`, `c_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `user_imgs` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`app_id` VARCHAR(50) NOT NULL DEFAULT '0',
	`user_id` INT(11) NOT NULL DEFAULT '0',
	`img_id` BIGINT(20) NOT NULL DEFAULT '0',
	`collected` TINYINT(4) NOT NULL DEFAULT '0',
	`downloaded` TINYINT(4) NOT NULL DEFAULT '0',
	`liked` TINYINT(4) NOT NULL DEFAULT '0',
	`payed` TINYINT(4) NOT NULL DEFAULT '0',
	`score` INT(3) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `Index 2` (`app_id`, `user_id`, `img_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;




ALTER TABLE `channel`
	ADD COLUMN `code_int` SMALLINT NULL DEFAULT NULL AFTER `code`;

update channel c set c.code_int=c.id;commit;



CREATE TABLE `channel_group` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`app_id` VARCHAR(50) NOT NULL,
	`code` VARCHAR(50) NOT NULL,
	`name` VARCHAR(50) NOT NULL,
	`info` VARCHAR(50) NULL DEFAULT NULL,
	`valid` TINYINT(4) NOT NULL DEFAULT '1',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `code_unique_key` (`app_id`, `code`)
)
COMMENT='渠道组'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

ALTER TABLE `channel`
ADD COLUMN `group_code` VARCHAR(50) NULL DEFAULT NULL AFTER `app_id`,
ADD COLUMN `keys` VARCHAR(200) NULL DEFAULT NULL AFTER `platform_sec`;


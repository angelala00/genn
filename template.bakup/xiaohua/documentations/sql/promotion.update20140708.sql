ALTER TABLE `advertiser`
	ADD COLUMN `type` TINYINT NOT NULL AFTER `key`,
	CHANGE COLUMN `valid` `valid` TINYINT(4) NOT NULL DEFAULT '0' AFTER `type`;
--
-- “关于我们” 的message，返回个client做view展示
-- 2041-07-07
--
CREATE TABLE `about` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(50) NOT NULL DEFAULT '0',
	`content` VARCHAR(200) NOT NULL DEFAULT '0',
	`valid` TINYINT(4) NOT NULL DEFAULT '1',
	PRIMARY KEY (`id`),
	INDEX `Index 2` (`valid`)
)
 COLLATE='utf8_general_ci'
  ENGINE=InnoDB;

--
-- 支持针对应用定制“关于我们”页面，直接能使用服务器页面
-- 2041-07-07
--
ALTER TABLE `app_client`
	ADD COLUMN `about_page` VARCHAR(100) NULL DEFAULT NULL AFTER `update_url`;


	--
	-- 醉拳押注信息：role修改为tinyint类型
	-- 2014-07-07
	--
	ALTER TABLE `zuiquan_bet`
	ALTER `role` DROP DEFAULT;
ALTER TABLE `zuiquan_bet`
	CHANGE COLUMN `role` `role` TINYINT NOT NULL COMMENT '1 为庄2为闲' AFTER `last_bonus`;

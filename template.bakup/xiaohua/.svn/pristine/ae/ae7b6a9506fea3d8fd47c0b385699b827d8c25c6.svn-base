--
-- font-app table add column policy
-- 2014-05-20
--
ALTER TABLE `app_client`
ADD COLUMN `policy` TINYINT NOT NULL DEFAULT '0' AFTER `update_url`;

--
--  ip 地址库模型更新： 改成使用新浪的 ip 接口；只需要国家，省，城市和 isp 4个关键字段
-- 2014-05-29
--
ALTER TABLE `ip_location_library`
ALTER `area` DROP DEFAULT,
ALTER `region` DROP DEFAULT,
ALTER `city` DROP DEFAULT,
ALTER `county` DROP DEFAULT,
ALTER `isp` DROP DEFAULT,
ALTER `country_id` DROP DEFAULT,
ALTER `area_id` DROP DEFAULT,
ALTER `region_id` DROP DEFAULT,
ALTER `city_id` DROP DEFAULT,
ALTER `county_id` DROP DEFAULT,
ALTER `isp_id` DROP DEFAULT;
ALTER TABLE `ip_location_library`
CHANGE COLUMN `area` `area` VARCHAR(100) NULL
AFTER `country`,
CHANGE COLUMN `region` `region` VARCHAR(100) NOT NULL
AFTER `area`,
CHANGE COLUMN `city` `city` VARCHAR(100) NOT NULL
AFTER `region`,
CHANGE COLUMN `county` `county` VARCHAR(100) NULL
AFTER `city`,
CHANGE COLUMN `isp` `isp` VARCHAR(100) NULL
AFTER `county`,
CHANGE COLUMN `country_id` `country_id` VARCHAR(50) NULL
AFTER `isp`,
CHANGE COLUMN `area_id` `area_id` VARCHAR(50) NULL
AFTER `country_id`,
CHANGE COLUMN `region_id` `region_id` VARCHAR(50) NULL
AFTER `area_id`,
CHANGE COLUMN `city_id` `city_id` VARCHAR(50) NULL
AFTER `region_id`,
CHANGE COLUMN `county_id` `county_id` VARCHAR(50) NULL
AFTER `city_id`,
CHANGE COLUMN `isp_id` `isp_id` VARCHAR(50) NULL
AFTER `county_id`;

ALTER TABLE `daily_region_integral_stat`
ALTER `region` DROP DEFAULT,
ALTER `city` DROP DEFAULT;
ALTER TABLE `daily_region_integral_stat`
CHANGE COLUMN `region` `region` VARCHAR(50) NULL
AFTER `app_id`,
CHANGE COLUMN `city` `city` VARCHAR(50) NULL
AFTER `region_name`;

ALTER TABLE `daily_region_integral_limit`
ALTER `region` DROP DEFAULT,
ALTER `city` DROP DEFAULT;
ALTER TABLE `daily_region_integral_limit`
CHANGE COLUMN `region` `region` VARCHAR(50) NULL
AFTER `app_id`,
CHANGE COLUMN `city` `city` VARCHAR(50) NULL
AFTER `region_name`;

--
-- 渠道是否在使用的标记字段：默认为在使用状态 1；
-- 2014-05-29
--
ALTER TABLE `channel`
ADD COLUMN `used` TINYINT NOT NULL DEFAULT '1' AFTER `code`;
-- channel table index update
ALTER TABLE `channel`
DROP INDEX `name_UNIQUE`;

ALTER TABLE `channel`
-- DROP INDEX `code_UNIQUE`,
ADD UNIQUE INDEX `code_query_UNIQUE` (`app_id`, `code`),
ADD INDEX `used_query_index` (`app_id`, `used`),
ADD INDEX `valid_query_index` (`app_id`, `is_valid`),
ADD INDEX `valid2_query_index` (`app_id`, `is_valid_`);

-- integral table fk update
ALTER TABLE `integral`
DROP FOREIGN KEY `fk_integral_channel`;
-- integral table index update
ALTER TABLE `integral`
-- DROP INDEX `fk_integral_type_idx`,
-- DROP INDEX `user_id_`,
ADD INDEX `user_id_query_index` (`app_id`, `user_id`),
DROP INDEX `fk_integral_channel_idx`,
ADD INDEX `fk_integral_channel_idx` (`app_id`, `channel`),
DROP INDEX `queryindex_userid_channel_adid_createtime`,
ADD INDEX `queryindex_userid_channel_adid_createtime` (`app_id`, `user_id`, `channel`, `ad_id`, `order_id`, `create_time`);

-- ip_user_limit index update
ALTER TABLE `ip_user_limit`
DROP INDEX `un_ip_userid`,
ADD INDEX `un_ip_userid` (`app_id`, `ip`, `user_id`, `tag`),
DROP INDEX `fk_userid_user_idx`,
ADD INDEX `fk_userid_user_idx` (`app_id`, `user_id`);
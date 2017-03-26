ALTER TABLE `about`
ADD COLUMN `app_id`  varchar(255) CHARACTER SET utf8 NULL AFTER `id`;
ALTER TABLE `user`
ADD COLUMN `last_login_time`  datetime NULL AFTER `udid`;

ALTER TABLE `zuiquan_user_task`
ADD COLUMN `status`  char(20) NULL COMMENT '状态  完成 （finished） 未完成（unfinished）' AFTER `create_time`;
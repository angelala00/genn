
CREATE TABLE `zuiquan_basic_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stage` int(10) DEFAULT NULL COMMENT '期',
  `winner` varchar(255) DEFAULT NULL COMMENT '赢者(1庄，2闲)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `banker_result` varchar(255) DEFAULT NULL COMMENT '庄结果',
  `player_result` varchar(255) DEFAULT NULL COMMENT '闲结果',
  `lottery_time` datetime DEFAULT NULL COMMENT '开奖时间',
  `total_bet` int(255) DEFAULT '0' COMMENT '总共压的注数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


CREATE TABLE `zuiquan_bet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bet` int(255) DEFAULT NULL COMMENT '注',
  `bonus` int(255) DEFAULT NULL COMMENT '奖金',
  `last_bonus` int(255) DEFAULT NULL COMMENT '最后获取的奖金',
  `role` varchar(255) DEFAULT NULL COMMENT '1 为庄2为闲',
  `zuiquan_basic_info_id` int(11) DEFAULT NULL,
  `result` tinyint(255) DEFAULT NULL COMMENT '赢或输  1赢2输',
  `create_time` datetime DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `additional_bonus` int(255) DEFAULT '0' COMMENT '附加分',
  PRIMARY KEY (`id`),
  KEY `zuiquan_basic_info` (`zuiquan_basic_info_id`),
  KEY `user` (`user_id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `zuiquan_basic_info` FOREIGN KEY (`zuiquan_basic_info_id`) REFERENCES `zuiquan_basic_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;


CREATE TABLE `zuiquan_liveness` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `liveness` int(11) DEFAULT '0' COMMENT '活跃度',
  `reward` int(255) DEFAULT '0' COMMENT '奖励',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `zuiquan_prop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '类型  liquor ， armor',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `zuiquan_prop_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `introduction` varchar(255) DEFAULT NULL COMMENT '描述',
  `zuiquan_prop_id` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT '1',
  `price` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `zuiquan_prop2` (`zuiquan_prop_id`),
  CONSTRAINT `zuiquan_prop2` FOREIGN KEY (`zuiquan_prop_id`) REFERENCES `zuiquan_prop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `zuiquan_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zuiquan_task_type_id` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `liveness` int(255) DEFAULT NULL COMMENT '活跃度',
  PRIMARY KEY (`id`),
  KEY `zuiquan_task_type` (`zuiquan_task_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `zuiquan_task_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `zuiquan_user_liveness_reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(255) DEFAULT NULL,
  `zuiquan_liveness_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user3` (`user_id`),
  KEY `zuiquan_liveness` (`zuiquan_liveness_id`),
  CONSTRAINT `user3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `zuiquan_liveness` FOREIGN KEY (`zuiquan_liveness_id`) REFERENCES `zuiquan_liveness` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `zuiquan_user_prop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `zuiquan_prop_id` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `zuiquan_prop` (`zuiquan_prop_id`),
  KEY `users` (`user_id`),
  CONSTRAINT `users` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `zuiquan_prop` FOREIGN KEY (`zuiquan_prop_id`) REFERENCES `zuiquan_prop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=sjis;


CREATE TABLE `zuiquan_user_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `zuiquan_task_id` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user111` (`user_id`),
  KEY `zuiquan_task1` (`zuiquan_task_id`),
  CONSTRAINT `zuiquan_task1` FOREIGN KEY (`zuiquan_task_id`) REFERENCES `zuiquan_task` (`id`),
  CONSTRAINT `user111` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `zuiquan_user_use_prop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `zuiquan_bet_id` int(11) DEFAULT NULL,
  `zuiquan_prop_id` int(11) DEFAULT NULL,
  `use_number` int(11) DEFAULT NULL COMMENT '使用次数',
  `create_time` datetime DEFAULT NULL,
  `zuiquan_basic_info_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user1` (`user_id`),
  KEY `zuiquan_bet` (`zuiquan_bet_id`),
  KEY `zuiquan_prop1` (`zuiquan_prop_id`),
  KEY `zuiquan_basic_info1` (`zuiquan_basic_info_id`),
  CONSTRAINT `user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `zuiquan_basic_info1` FOREIGN KEY (`zuiquan_basic_info_id`) REFERENCES `zuiquan_basic_info` (`id`),
  CONSTRAINT `zuiquan_bet` FOREIGN KEY (`zuiquan_bet_id`) REFERENCES `zuiquan_bet` (`id`),
  CONSTRAINT `zuiquan_prop1` FOREIGN KEY (`zuiquan_prop_id`) REFERENCES `zuiquan_prop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

ALTER TABLE `user`
ADD COLUMN `liveness`  int(255) NULL,
ADD COLUMN `qq`  varchar(255) NULL;
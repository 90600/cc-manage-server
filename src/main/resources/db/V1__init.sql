DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_a_user`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `create_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    `modify_time`  datetime     DEFAULT NULL COMMENT '修改时间',
    `deleted_flag` tinyint(1) DEFAULT NULL COMMENT '有效标识',
    `username`     varchar(255) DEFAULT NULL COMMENT '用户名',
    `password`     varchar(255) DEFAULT NULL COMMENT '名称',
    `nickname`     varchar(255) DEFAULT NULL COMMENT '昵称',
    `email`        varchar(255) DEFAULT NULL COMMENT '邮箱',
    `secret`       varchar(255) DEFAULT NULL COMMENT '邮箱',
    `user_pic`      varchar(255) DEFAULT NULL COMMENT '用户头像地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB CHARSET=utf8mb4  COMMENT='用户表';

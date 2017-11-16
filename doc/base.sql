
-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父资源ID',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `fun_name` varchar(64) DEFAULT '' COMMENT '菜单名称',
  `app_id` varchar(20) DEFAULT '' COMMENT '应用id',
  `type` tinyint(1) DEFAULT '0' COMMENT '0:功能，1:资源',
  `url` varchar(128) DEFAULT '',
  `description` varchar(256) DEFAULT '',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0已停用1已开启)',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '新增时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('0', '1', '一级菜单1', 'taobao', '0', '', '一级菜单1', '1', '1', '2017-08-31 00:00:00', '2017-08-31 00:00:00');
INSERT INTO `sys_resource` VALUES ('0', '2', '一级菜单2', 'taobao', '0', '', '一级菜单2', '1', '2', '2017-08-31 00:00:00', '2017-08-31 00:00:00');
INSERT INTO `sys_resource` VALUES ('1', '3', '一级子菜单1', 'taobao', '1', '/test', '一级菜单-子菜单1', '1', '1', '2017-08-31 00:00:00', '2017-08-31 00:00:00');
INSERT INTO `sys_resource` VALUES ('1', '4', '一级子菜单2', 'taobao', '1', '/test', '一级菜单-子菜单2', '1', '2', '2017-08-31 00:00:00', '2017-08-31 00:00:00');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) NOT NULL COMMENT '角色',
  `description` varchar(256) DEFAULT '' COMMENT '角色描述',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0删除 1正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '管理员', '1', '2017-08-31 00:00:00', '2017-08-31 00:00:00');
INSERT INTO `sys_role` VALUES ('2', 'test', 'test', '1', '2017-08-31 00:00:00', '2017-08-31 00:00:00');

-- ----------------------------
-- Table structure for sys_role_resource_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource_rel`;
CREATE TABLE `sys_role_resource_rel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色id',
  `resource_id` int(11) NOT NULL DEFAULT '0' COMMENT '对应的操作资源ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色资源关系表';

-- ----------------------------
-- Records of sys_role_resource_rel
-- ----------------------------
INSERT INTO `sys_role_resource_rel` VALUES ('1', '1', '1', '2017-08-31 00:00:00', '2017-08-31 00:00:00');
INSERT INTO `sys_role_resource_rel` VALUES ('2', '1', '2', '2017-08-31 00:00:00', '2017-08-31 00:00:00');
INSERT INTO `sys_role_resource_rel` VALUES ('3', '1', '3', '2017-08-31 00:00:00', '2017-08-31 00:00:00');
INSERT INTO `sys_role_resource_rel` VALUES ('4', '1', '4', '2017-08-31 00:00:00', '2017-08-31 00:00:00');

-- ----------------------------
-- Table structure for sys_third_party_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_third_party_user`;
CREATE TABLE `sys_third_party_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `type` int(4) NOT NULL COMMENT '平台类型：1.Github，2.支付宝',
  `login_name` varchar(32) DEFAULT NULL COMMENT '登录用户名',
  `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
  `third_party_id` bigint(16) NOT NULL COMMENT '第三方平台用户ID',
  `auth_app_id` bigint(20) DEFAULT NULL COMMENT '授权商户的AppId',
  `app_auth_token` varchar(64) DEFAULT NULL COMMENT '商户授权令牌',
  `expires_in` datetime DEFAULT NULL COMMENT '令牌有效期',
  `re_expires_in` datetime DEFAULT NULL COMMENT '刷新令牌有效期',
  `app_refresh_token` varchar(64) DEFAULT NULL COMMENT '刷新令牌时使用',
  `auth_methods` varchar(512) DEFAULT NULL COMMENT '授权接口列表',
  `auth_start` datetime DEFAULT NULL COMMENT '授权生效时间',
  `auth_end` datetime DEFAULT NULL COMMENT '授权结束时间',
  `status` varchar(16) DEFAULT NULL COMMENT '状态(valid：有效状态；invalid：无效状态)',
  `url` varchar(128) DEFAULT NULL COMMENT '用户首页',
  `detail_url` varchar(128) DEFAULT NULL COMMENT '详情页',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `created_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `third_party_id_type` (`third_party_id`,`type`) USING BTREE,
  KEY `third_party_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_third_party_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `type` int(4) DEFAULT '0' COMMENT '用户类型(0.系统用户，1.GitHub)',
  `salt` varchar(32) DEFAULT NULL COMMENT '生成密码时的盐值',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) DEFAULT '' COMMENT '邮箱',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0停用 1启用',
  `login_time` datetime NOT NULL COMMENT '最后登录时间',
  `login_ip` varchar(64) DEFAULT '' COMMENT '最后登录ip',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `error_times` int(2) DEFAULT '0' COMMENT '登录时连续输入密码次数',
  `lock_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `sys_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 0, null, null, '', '1', '2017-08-31 00:00:00', '127.0.0.1', '2017-08-31 00:00:00', '2017-08-31 00:00:00', '0', null);
INSERT INTO `sys_user` VALUES ('2', 'test', '21232f297a57a5a743894a0e4a801fc3', 0, null, null, '', '1', '2017-08-31 00:00:00', '', '2017-08-31 00:00:00', '2017-08-31 00:00:00', '0', null);

-- ----------------------------
-- Table structure for sys_user_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_ref`;
CREATE TABLE `sys_user_role_ref` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '角色id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role_ref
-- ----------------------------
INSERT INTO `sys_user_role_ref` VALUES ('1', '1', '1', '2017-08-31 00:00:00', '2017-08-31 00:00:00');
INSERT INTO `sys_user_role_ref` VALUES ('2', '1', '2', '2017-08-31 00:00:00', '2017-08-31 00:00:00');
INSERT INTO `sys_user_role_ref` VALUES ('3', '2', '2', '2017-08-31 00:00:00', '2017-08-31 00:00:00');

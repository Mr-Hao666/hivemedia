/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : hive_media

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2018-11-21 17:21:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('hivemediaScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('hivemediaScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('hivemediaScheduler', 'PC-20150827GVTU1542790135560', '1542791519018', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('2', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '人人开源集团', '0', '0');
INSERT INTO `sys_dept` VALUES ('2', '1', '长沙分公司', '1', '0');
INSERT INTO `sys_dept` VALUES ('3', '1', '上海分公司', '2', '0');
INSERT INTO `sys_dept` VALUES ('4', '3', '技术部', '0', '0');
INSERT INTO `sys_dept` VALUES ('5', '3', '销售部', '1', '0');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `type` varchar(100) NOT NULL COMMENT '字典类型',
  `code` varchar(100) NOT NULL COMMENT '字典码',
  `value` varchar(1000) NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '性别', 'sex', '0', '女', '0', null, '0');
INSERT INTO `sys_dict` VALUES ('2', '性别', 'sex', '1', '男', '1', null, '0');
INSERT INTO `sys_dict` VALUES ('3', '性别', 'sex', '2', '未知', '3', null, '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('3', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":162,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"用户管理\",\"type\":0,\"icon\":\"fa-user-o\",\"orderNum\":1}', '70', '0:0:0:0:0:0:0:1', '2018-11-21 16:39:05');
INSERT INTO `sys_log` VALUES ('4', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":162,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"用户管理\",\"type\":0,\"icon\":\"fa-user-circle-o\",\"orderNum\":1}', '52', '0:0:0:0:0:0:0:1', '2018-11-21 16:51:38');
INSERT INTO `sys_log` VALUES ('5', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":162,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"用户管理\",\"type\":0,\"icon\":\"fa fa-user-o\",\"orderNum\":1}', '37', '0:0:0:0:0:0:0:1', '2018-11-21 16:52:52');
INSERT INTO `sys_log` VALUES ('6', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":162,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"用户管理\",\"type\":0,\"icon\":\"fa fa-user-circle-o\",\"orderNum\":1}', '45', '0:0:0:0:0:0:0:1', '2018-11-21 16:53:29');
INSERT INTO `sys_log` VALUES ('7', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":163,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"商品管理\",\"type\":0,\"icon\":\"fa fa-cart-arrow-down fa-3x\",\"orderNum\":2}', '34', '0:0:0:0:0:0:0:1', '2018-11-21 16:56:30');
INSERT INTO `sys_log` VALUES ('8', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":163,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"商品管理\",\"type\":0,\"icon\":\"fa fa-cart-arrow-down fa-2x\",\"orderNum\":2}', '73', '0:0:0:0:0:0:0:1', '2018-11-21 16:56:46');
INSERT INTO `sys_log` VALUES ('9', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":162,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"用户管理\",\"type\":0,\"icon\":\"fa fa-user-circle-o fa-2x\",\"orderNum\":1}', '33', '0:0:0:0:0:0:0:1', '2018-11-21 16:56:54');
INSERT INTO `sys_log` VALUES ('10', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":1,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"系统管理\",\"type\":0,\"icon\":\"fa fa-cog fa-2x\",\"orderNum\":0}', '38', '0:0:0:0:0:0:0:1', '2018-11-21 16:57:02');
INSERT INTO `sys_log` VALUES ('11', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":164,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"订单管理\",\"type\":0,\"icon\":\"fa fa-sort-amount-asc fa-x2\",\"orderNum\":3}', '37', '0:0:0:0:0:0:0:1', '2018-11-21 16:58:05');
INSERT INTO `sys_log` VALUES ('12', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":163,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"商品管理\",\"type\":0,\"icon\":\"fa fa-cart-arrow-down fa-2x\",\"orderNum\":2}', '44', '0:0:0:0:0:0:0:1', '2018-11-21 16:58:24');
INSERT INTO `sys_log` VALUES ('13', 'admin', '修改菜单', 'SysMenuController.update()', '{\"menuId\":164,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"订单管理\",\"type\":0,\"icon\":\"fa fa-sort-amount-asc fa-2x\",\"orderNum\":3}', '43', '0:0:0:0:0:0:0:1', '2018-11-21 16:58:32');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog fa-2x', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员管理', 'modules/sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', null, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-bug', '4');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'modules/sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '6');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'modules/oss/oss.html', 'sys:oss:all', '1', 'fa fa-file-image-o', '6');
INSERT INTO `sys_menu` VALUES ('31', '1', '部门管理', 'modules/sys/dept.html', null, '1', 'fa fa-file-code-o', '1');
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'sys:dept:list,sys:dept:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'sys:dept:save,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'sys:dept:update,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'sys:dept:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('36', '1', '字典管理', 'modules/sys/dict.html', null, '1', 'fa fa-bookmark-o', '6');
INSERT INTO `sys_menu` VALUES ('37', '36', '查看', null, 'sys:dict:list,sys:dict:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('38', '36', '新增', null, 'sys:dict:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('39', '36', '修改', null, 'sys:dict:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('40', '36', '删除', null, 'sys:dict:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('61', '1', '数据字典管理', 'modules/sys/datadictionary.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('62', '61', '查看', null, 'sys:datadictionary:list,sys:datadictionary:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('63', '61', '新增', null, 'sys:datadictionary:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('64', '61', '修改', null, 'sys:datadictionary:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('65', '61', '删除', null, 'sys:datadictionary:delete', '2', null, '6');
-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `src_type` tinyint(4) DEFAULT '1' COMMENT '数据来源 1:后台 2:前台',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型 1:头像 2:商品...',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e', '123456@qq.com', '13612345678', '1', '1', '2016-11-11 11:11:11');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_dictionary`;
CREATE TABLE `tb_data_dictionary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据字典表主键',
  `data_type` tinyint(4) DEFAULT NULL COMMENT '数据类型(保留)',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `resKey` varchar(50) DEFAULT NULL COMMENT 'key',
  `level` int(11) DEFAULT NULL COMMENT '排序',
  `ishide` tinyint(4) DEFAULT '0' COMMENT '0:不隐藏 1:隐藏',
  `desc` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of tb_data_dictionary
-- ----------------------------
INSERT INTO `tb_data_dictionary` VALUES ('1', null, '湖北省', '0', 'PROVINCE', null, '0', '湖北省');
INSERT INTO `tb_data_dictionary` VALUES ('2', null, '武汉市', '1', 'CITY', null, '0', '武汉市');
INSERT INTO `tb_data_dictionary` VALUES ('3', null, '洪山区', '2', 'AREA', null, '0', '洪山区');
INSERT INTO `tb_data_dictionary` VALUES ('4', null, '江岸区', '2', 'AREA', null, '1', '江岸区');
INSERT INTO `tb_data_dictionary` VALUES ('5', null, '江汉区', '2', 'AREA', null, '0', '江汉区');
INSERT INTO `tb_data_dictionary` VALUES ('6', null, '硚口区', '2', 'AREA', null, '1', '硚口区');
INSERT INTO `tb_data_dictionary` VALUES ('7', null, '汉阳区', '2', 'AREA', null, '1', '汉阳区');
INSERT INTO `tb_data_dictionary` VALUES ('8', null, '武昌区', '2', 'AREA', null, '0', '武昌区');
INSERT INTO `tb_data_dictionary` VALUES ('9', null, '青山区', '2', 'AREA', null, '1', '青山区');
INSERT INTO `tb_data_dictionary` VALUES ('10', null, '东西湖区', '2', 'AREA', null, '1', '东西湖区');
INSERT INTO `tb_data_dictionary` VALUES ('11', null, '蔡甸区', '2', 'AREA', null, '1', '蔡甸区');
INSERT INTO `tb_data_dictionary` VALUES ('12', null, '江夏区', '2', 'AREA', null, '1', '江夏区');
INSERT INTO `tb_data_dictionary` VALUES ('13', null, '黄陂区', '2', 'AREA', null, '1', '黄陂区');
INSERT INTO `tb_data_dictionary` VALUES ('14', null, '新洲区', '2', 'AREA', null, '1', '新洲区');
INSERT INTO `tb_data_dictionary` VALUES ('15', null, '汉南区', '2', 'AREA', null, '1', '汉南区');
INSERT INTO `tb_data_dictionary` VALUES ('16', null, '武汉经济技术开发区', '2', 'AREA', null, '1', '武汉经济技术开发区');
INSERT INTO `tb_data_dictionary` VALUES ('17', null, '积玉桥街道', '8', 'STREET', null, '0', '积玉桥街道');
INSERT INTO `tb_data_dictionary` VALUES ('18', null, '徐家棚街道', '8', 'STREET', null, '0', '徐家棚街道');
INSERT INTO `tb_data_dictionary` VALUES ('19', null, '梁道街街道', '8', 'STREET', null, '0', '梁道街街道');
INSERT INTO `tb_data_dictionary` VALUES ('20', null, '中华路街道', '8', 'STREET', null, '0', '中华路街道');
INSERT INTO `tb_data_dictionary` VALUES ('21', null, '黄鹤楼街道', '8', 'STREET', null, '0', '黄鹤楼街道');
INSERT INTO `tb_data_dictionary` VALUES ('22', null, '白沙洲街道', '8', 'STREET', null, '0', '白沙洲街道');
INSERT INTO `tb_data_dictionary` VALUES ('23', null, '首义路街道', '8', 'STREET', null, '0', '首义路街道');
INSERT INTO `tb_data_dictionary` VALUES ('24', null, '中南路街道', '8', 'STREET', null, '0', '中南路街道');
INSERT INTO `tb_data_dictionary` VALUES ('25', null, '水果湖街道', '8', 'STREET', null, '0', '水果湖街道');
INSERT INTO `tb_data_dictionary` VALUES ('26', null, '珞珈山街道', '8', 'STREET', null, '0', '珞珈山街道');
INSERT INTO `tb_data_dictionary` VALUES ('27', null, '紫阳街道', '8', 'STREET', null, '0', '紫阳街道');
INSERT INTO `tb_data_dictionary` VALUES ('28', null, '杨园街道', '8', 'STREET', null, '0', '杨园街道');
INSERT INTO `tb_data_dictionary` VALUES ('29', null, '南湖街道', '8', 'STREET', null, '0', '南湖街道');
INSERT INTO `tb_data_dictionary` VALUES ('30', null, '新河街道', '8', 'STREET', null, '0', '新河街道');
INSERT INTO `tb_data_dictionary` VALUES ('31', null, '城区', '5', 'STREET', null, '1', '城区');
INSERT INTO `tb_data_dictionary` VALUES ('32', null, '永安镇', '11', 'STREET', null, '1', '永安镇');
INSERT INTO `tb_data_dictionary` VALUES ('33', null, '奓山镇', '11', 'STREET', null, '1', '奓山镇');
INSERT INTO `tb_data_dictionary` VALUES ('34', null, '侏儒镇', '11', 'STREET', null, '1', '侏儒镇');
INSERT INTO `tb_data_dictionary` VALUES ('35', null, '消泗乡', '11', 'STREET', null, '1', '消泗乡');
INSERT INTO `tb_data_dictionary` VALUES ('36', null, '索河镇', '11', 'STREET', null, '1', '索河镇');
INSERT INTO `tb_data_dictionary` VALUES ('37', null, '张湾镇', '11', 'STREET', null, '1', '张湾镇');
INSERT INTO `tb_data_dictionary` VALUES ('38', null, '城区以内', '11', 'STREET', null, '1', '城区以内');
INSERT INTO `tb_data_dictionary` VALUES ('39', null, '城区', '3', 'STREET', null, '0', '城区');
INSERT INTO `tb_data_dictionary` VALUES ('40', null, '左岭镇', '3', 'STREET', null, '0', '左岭镇');
INSERT INTO `tb_data_dictionary` VALUES ('41', null, '花山镇', '3', 'STREET', null, '0', '花山镇');
INSERT INTO `tb_data_dictionary` VALUES ('42', null, '黄家湖大学城', '3', 'STREET', null, '0', '黄家湖大学城');
INSERT INTO `tb_data_dictionary` VALUES ('43', null, '城区', '12', 'STREET', null, '1', '城区');
INSERT INTO `tb_data_dictionary` VALUES ('44', null, '豹解镇', '12', 'STREET', null, '1', '豹解镇');
INSERT INTO `tb_data_dictionary` VALUES ('45', null, '龙泉镇', '12', 'STREET', null, '1', '龙泉镇');
INSERT INTO `tb_data_dictionary` VALUES ('46', null, '金口街道', '12', 'STREET', null, '1', '金口街道');
INSERT INTO `tb_data_dictionary` VALUES ('47', null, '乌龙泉镇', '12', 'STREET', null, '1', '乌龙泉镇');
INSERT INTO `tb_data_dictionary` VALUES ('48', null, '郑店街道', '12', 'STREET', null, '1', '郑店街道');
INSERT INTO `tb_data_dictionary` VALUES ('49', null, '五里界镇', '12', 'STREET', null, '1', '五里界镇');
INSERT INTO `tb_data_dictionary` VALUES ('50', null, '金水街道', '12', 'STREET', null, '1', '金水街道');
INSERT INTO `tb_data_dictionary` VALUES ('51', null, '安山街道', '12', 'STREET', null, '1', '安山街道');
INSERT INTO `tb_data_dictionary` VALUES ('52', null, '山坡镇', '12', 'STREET', null, '1', '山坡镇');
INSERT INTO `tb_data_dictionary` VALUES ('53', null, '滨湖镇', '12', 'STREET', null, '1', '滨湖镇');
INSERT INTO `tb_data_dictionary` VALUES ('54', null, '法泗镇', '12', 'STREET', null, '1', '法泗镇');
INSERT INTO `tb_data_dictionary` VALUES ('55', null, '湖泗镇', '12', 'STREET', null, '1', '湖泗镇');
INSERT INTO `tb_data_dictionary` VALUES ('56', null, '舒安乡', '12', 'STREET', null, '1', '舒安乡');
INSERT INTO `tb_data_dictionary` VALUES ('57', null, '江夏区梁子湖风景区', '12', 'STREET', null, '1', '江夏区梁子湖风景区');
INSERT INTO `tb_data_dictionary` VALUES ('58', null, '邾城城区', '14', 'STREET', null, '1', '邾城城区');
INSERT INTO `tb_data_dictionary` VALUES ('59', null, '凤凰镇', '14', 'STREET', null, '1', '凤凰镇');
INSERT INTO `tb_data_dictionary` VALUES ('60', null, '徐古镇', '14', 'STREET', null, '1', '徐古镇');
INSERT INTO `tb_data_dictionary` VALUES ('61', null, '辛冲镇', '14', 'STREET', null, '1', '辛冲镇');
INSERT INTO `tb_data_dictionary` VALUES ('62', null, '涨渡湖镇', '14', 'STREET', null, '1', '涨渡湖镇');
INSERT INTO `tb_data_dictionary` VALUES ('63', null, '双柳镇', '14', 'STREET', null, '1', '双柳镇');
INSERT INTO `tb_data_dictionary` VALUES ('64', null, '仓埠镇', '14', 'STREET', null, '1', '仓埠镇');
INSERT INTO `tb_data_dictionary` VALUES ('65', null, '李集镇', '14', 'STREET', null, '1', '李集镇');
INSERT INTO `tb_data_dictionary` VALUES ('66', null, '汪集镇', '14', 'STREET', null, '1', '汪集镇');
INSERT INTO `tb_data_dictionary` VALUES ('67', null, '三店镇', '14', 'STREET', null, '1', '三店镇');
INSERT INTO `tb_data_dictionary` VALUES ('68', null, '潘塘乡', '14', 'STREET', null, '1', '潘塘乡');
INSERT INTO `tb_data_dictionary` VALUES ('69', null, '旧街乡', '14', 'STREET', null, '1', '旧街乡');
INSERT INTO `tb_data_dictionary` VALUES ('70', null, '阳逻经济开发区', '14', 'STREET', null, '1', '阳逻经济开发区');
INSERT INTO `tb_data_dictionary` VALUES ('71', null, '道观河风景旅游区', '14', 'STREET', null, '1', '道观河风景旅游区');
INSERT INTO `tb_data_dictionary` VALUES ('72', null, '滠口镇', '13', 'STREET', null, '1', '滠口镇');
INSERT INTO `tb_data_dictionary` VALUES ('73', null, '三里桥镇', '13', 'STREET', null, '1', '三里桥镇');
INSERT INTO `tb_data_dictionary` VALUES ('74', null, '汉口北商贸物流枢纽区管委会', '13', 'STREET', null, '1', '汉口北商贸物流枢纽区管委会');
INSERT INTO `tb_data_dictionary` VALUES ('75', null, '盘龙城经济开发区', '13', 'STREET', null, '1', '盘龙城经济开发区');
INSERT INTO `tb_data_dictionary` VALUES ('76', null, '王家河镇', '13', 'STREET', null, '1', '王家河镇');
INSERT INTO `tb_data_dictionary` VALUES ('77', null, '罗汉寺镇', '13', 'STREET', null, '1', '罗汉寺镇');
INSERT INTO `tb_data_dictionary` VALUES ('78', null, '祁家湾镇', '13', 'STREET', null, '1', '祁家湾镇');
INSERT INTO `tb_data_dictionary` VALUES ('79', null, '蔡家榨镇', '13', 'STREET', null, '1', '蔡家榨镇');
INSERT INTO `tb_data_dictionary` VALUES ('80', null, '六指店镇', '13', 'STREET', null, '1', '六指店镇');
INSERT INTO `tb_data_dictionary` VALUES ('81', null, '前川镇', '13', 'STREET', null, '1', '前川镇');
INSERT INTO `tb_data_dictionary` VALUES ('82', null, '李家集镇', '13', 'STREET', null, '1', '李家集镇');
INSERT INTO `tb_data_dictionary` VALUES ('83', null, '武湖农场', '13', 'STREET', null, '1', '武湖农场');
INSERT INTO `tb_data_dictionary` VALUES ('84', null, '长轩岭镇', '13', 'STREET', null, '1', '长轩岭镇');
INSERT INTO `tb_data_dictionary` VALUES ('85', null, '姚家集镇', '13', 'STREET', null, '1', '姚家集镇');
INSERT INTO `tb_data_dictionary` VALUES ('86', null, '蔡店镇', '13', 'STREET', null, '1', '蔡店镇');
INSERT INTO `tb_data_dictionary` VALUES ('87', null, '天河镇', '13', 'STREET', null, '1', '天河镇');
INSERT INTO `tb_data_dictionary` VALUES ('88', null, '横店镇', '13', 'STREET', null, '1', '横店镇');
INSERT INTO `tb_data_dictionary` VALUES ('89', null, '大谭原种场', '13', 'STREET', null, '1', '大谭原种场');
INSERT INTO `tb_data_dictionary` VALUES ('90', null, '木兰乡', '13', 'STREET', null, '1', '木兰乡');
INSERT INTO `tb_data_dictionary` VALUES ('91', null, '木兰风景区', '13', 'STREET', null, '1', '木兰风景区');
INSERT INTO `tb_data_dictionary` VALUES ('92', null, '城区', '9', 'STREET', null, '1', '城区');
INSERT INTO `tb_data_dictionary` VALUES ('93', null, '纱帽镇', '15', 'STREET', null, '1', '纱帽镇');
INSERT INTO `tb_data_dictionary` VALUES ('94', null, '湘口镇', '15', 'STREET', null, '1', '湘口镇');
INSERT INTO `tb_data_dictionary` VALUES ('95', null, '东荆镇', '15', 'STREET', null, '1', '东荆镇');
INSERT INTO `tb_data_dictionary` VALUES ('96', null, '邓南镇', '15', 'STREET', null, '1', '邓南镇');
INSERT INTO `tb_data_dictionary` VALUES ('97', null, '北京', '0', 'PROVINCE', null, '1', '北京');
INSERT INTO `tb_data_dictionary` VALUES ('98', null, '上海', '0', 'PROVINCE', null, '1', '上海');
INSERT INTO `tb_data_dictionary` VALUES ('99', null, ' 天津', '0', 'PROVINCE', null, '1', ' 天津');
INSERT INTO `tb_data_dictionary` VALUES ('100', null, '重庆', '0', 'PROVINCE', null, '1', '重庆');
INSERT INTO `tb_data_dictionary` VALUES ('101', null, '河北', '0', 'PROVINCE', null, '1', '河北');
INSERT INTO `tb_data_dictionary` VALUES ('102', null, '山西', '0', 'PROVINCE', null, '1', '山西');
INSERT INTO `tb_data_dictionary` VALUES ('103', null, '河南', '0', 'PROVINCE', null, '1', '河南');
INSERT INTO `tb_data_dictionary` VALUES ('104', null, '辽宁', '0', 'PROVINCE', null, '1', '辽宁');
INSERT INTO `tb_data_dictionary` VALUES ('105', null, '吉林', '0', 'PROVINCE', null, '1', '吉林');
INSERT INTO `tb_data_dictionary` VALUES ('106', null, '黑龙江', '0', 'PROVINCE', null, '1', '黑龙江');
INSERT INTO `tb_data_dictionary` VALUES ('107', null, '内蒙古', '0', 'PROVINCE', null, '1', '内蒙古');
INSERT INTO `tb_data_dictionary` VALUES ('108', null, '江苏', '0', 'PROVINCE', null, '1', '江苏');
INSERT INTO `tb_data_dictionary` VALUES ('109', null, '山东', '0', 'PROVINCE', null, '1', '山东');
INSERT INTO `tb_data_dictionary` VALUES ('110', null, '安徽', '0', 'PROVINCE', null, '1', '安徽');
INSERT INTO `tb_data_dictionary` VALUES ('111', null, '浙江', '0', 'PROVINCE', null, '1', '浙江');
INSERT INTO `tb_data_dictionary` VALUES ('112', null, '福建', '0', 'PROVINCE', null, '1', '福建');
INSERT INTO `tb_data_dictionary` VALUES ('113', null, '湖南', '0', 'PROVINCE', null, '1', '湖南');
INSERT INTO `tb_data_dictionary` VALUES ('114', null, '广东', '0', 'PROVINCE', null, '1', '广东');
INSERT INTO `tb_data_dictionary` VALUES ('115', null, '广西', '0', 'PROVINCE', null, '1', '广西');
INSERT INTO `tb_data_dictionary` VALUES ('116', null, '江西', '0', 'PROVINCE', null, '1', '江西');
INSERT INTO `tb_data_dictionary` VALUES ('117', null, '四川', '0', 'PROVINCE', null, '1', '四川');
INSERT INTO `tb_data_dictionary` VALUES ('118', null, '湖南', '0', 'PROVINCE', null, '1', '湖南');
INSERT INTO `tb_data_dictionary` VALUES ('119', null, '贵州', '0', 'PROVINCE', null, '1', '贵州');
INSERT INTO `tb_data_dictionary` VALUES ('120', null, '云南', '0', 'PROVINCE', null, '1', '云南');
INSERT INTO `tb_data_dictionary` VALUES ('121', null, '西藏', '0', 'PROVINCE', null, '1', '西藏');
INSERT INTO `tb_data_dictionary` VALUES ('122', null, '陕西', '0', 'PROVINCE', null, '1', '陕西');
INSERT INTO `tb_data_dictionary` VALUES ('123', null, '甘肃', '0', 'PROVINCE', null, '1', '甘肃');
INSERT INTO `tb_data_dictionary` VALUES ('124', null, '青海', '0', 'PROVINCE', null, '1', '青海');
INSERT INTO `tb_data_dictionary` VALUES ('125', null, '宁夏', '0', 'PROVINCE', null, '1', '宁夏');
INSERT INTO `tb_data_dictionary` VALUES ('126', null, '新疆', '0', 'PROVINCE', null, '1', '新疆');
INSERT INTO `tb_data_dictionary` VALUES ('127', null, '台湾', '0', 'PROVINCE', null, '1', '台湾');
INSERT INTO `tb_data_dictionary` VALUES ('128', null, '香港', '0', 'PROVINCE', null, '1', '香港');
INSERT INTO `tb_data_dictionary` VALUES ('129', null, '澳门', '0', 'PROVINCE', null, '1', '澳门');
INSERT INTO `tb_data_dictionary` VALUES ('130', null, '钓鱼岛', '0', 'PROVINCE', null, '1', '钓鱼岛');
INSERT INTO `tb_data_dictionary` VALUES ('131', null, '密云区', '97', 'AREA', null, '1', '密云区');
INSERT INTO `tb_data_dictionary` VALUES ('132', null, '朝阳区', '97', 'AREA', null, '1', '朝阳区');
INSERT INTO `tb_data_dictionary` VALUES ('133', null, '昌平区', '97', 'AREA', null, '1', '昌平区');
INSERT INTO `tb_data_dictionary` VALUES ('134', null, '平谷区', '97', 'AREA', null, '1', '平谷区');
INSERT INTO `tb_data_dictionary` VALUES ('135', null, '海淀区', '97', 'AREA', null, '1', '海淀区');
INSERT INTO `tb_data_dictionary` VALUES ('136', null, '西城区', '97', 'AREA', null, '1', '西城区');
INSERT INTO `tb_data_dictionary` VALUES ('137', null, '东城区', '97', 'AREA', null, '1', '东城区');
INSERT INTO `tb_data_dictionary` VALUES ('138', null, '崇文区', '97', 'AREA', null, '1', '崇文区');
INSERT INTO `tb_data_dictionary` VALUES ('139', null, '玄武区', '97', 'AREA', null, '1', '玄武区');
INSERT INTO `tb_data_dictionary` VALUES ('140', null, '丰台区', '97', 'AREA', null, '1', '丰台区');
INSERT INTO `tb_data_dictionary` VALUES ('141', null, '石景山区', '97', 'AREA', null, '1', '石景山区');
INSERT INTO `tb_data_dictionary` VALUES ('142', null, '门头沟', '97', 'AREA', null, '1', '门头沟');
INSERT INTO `tb_data_dictionary` VALUES ('143', null, '房山区', '97', 'AREA', null, '1', '房山区');
INSERT INTO `tb_data_dictionary` VALUES ('144', null, '通州区', '97', 'AREA', null, '1', '通州区');
INSERT INTO `tb_data_dictionary` VALUES ('145', null, '延庆县', '97', 'AREA', null, '1', '延庆县');
INSERT INTO `tb_data_dictionary` VALUES ('146', null, '大兴区', '97', 'AREA', null, '1', '大兴区');
INSERT INTO `tb_data_dictionary` VALUES ('147', null, '顺义区', '97', 'AREA', null, '1', '顺义区');
INSERT INTO `tb_data_dictionary` VALUES ('148', null, '怀柔区', '97', 'AREA', null, '1', '怀柔区');
INSERT INTO `tb_data_dictionary` VALUES ('149', null, '静安区', '98', 'AREA', null, '1', '静安区');
INSERT INTO `tb_data_dictionary` VALUES ('150', null, '闸北区', '98', 'AREA', null, '1', '闸北区');
INSERT INTO `tb_data_dictionary` VALUES ('151', null, '虹口区', '98', 'AREA', null, '1', '虹口区');
INSERT INTO `tb_data_dictionary` VALUES ('152', null, '杨浦区', '98', 'AREA', null, '1', '杨浦区');
INSERT INTO `tb_data_dictionary` VALUES ('153', null, '宝山区', '98', 'AREA', null, '1', '宝山区');
INSERT INTO `tb_data_dictionary` VALUES ('154', null, '闵行区', '98', 'AREA', null, '1', '闵行区');
INSERT INTO `tb_data_dictionary` VALUES ('155', null, '嘉定区', '98', 'AREA', null, '1', '嘉定区');
INSERT INTO `tb_data_dictionary` VALUES ('156', null, '浦东新区', '98', 'AREA', null, '1', '浦东新区');
INSERT INTO `tb_data_dictionary` VALUES ('157', null, '青浦区', '98', 'AREA', null, '1', '青浦区');
INSERT INTO `tb_data_dictionary` VALUES ('158', null, '松江区', '98', 'AREA', null, '1', '松江区');
INSERT INTO `tb_data_dictionary` VALUES ('159', null, '金山区', '98', 'AREA', null, '1', '金山区');
INSERT INTO `tb_data_dictionary` VALUES ('160', null, '奉贤区', '98', 'AREA', null, '1', '奉贤区');
INSERT INTO `tb_data_dictionary` VALUES ('161', null, '普陀区', '98', 'AREA', null, '1', '普陀区');
INSERT INTO `tb_data_dictionary` VALUES ('162', null, '黄浦区', '98', 'AREA', null, '1', '黄浦区');
INSERT INTO `tb_data_dictionary` VALUES ('163', null, '崇明县', '98', 'AREA', null, '1', '崇明县');
INSERT INTO `tb_data_dictionary` VALUES ('164', null, '徐汇区', '98', 'AREA', null, '1', '徐汇区');
INSERT INTO `tb_data_dictionary` VALUES ('165', null, '长宁区', '98', 'AREA', null, '1', '长宁区');
INSERT INTO `tb_data_dictionary` VALUES ('166', null, '黄石市', '1', 'CITY', null, '0', '黄石市');
INSERT INTO `tb_data_dictionary` VALUES ('167', null, '黄石区', '166', 'AREA', null, '0', '黄石区');

-- ----------------------------
-- Table structure for tb_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_token`;
CREATE TABLE `tb_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

-- ----------------------------
-- Records of tb_token
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
create table t_order(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL COMMENT '单位ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `group_id` bigint(20) NULL COMMENT '小组ID',
  `blog_id` bigint(20) NOT NULL COMMENT '博主ID',
  `platform_id` bigint(20) NOT NULL COMMENT '平台ID',
  `order_no` varchar(65) NULL COMMENT '订单编号',
  `publish_date` datetime NULL COMMENT '发布日期',
  `account_id` bigint(20) NULL COMMENT '账号ID',
  `account_type` int(10) NULL COMMENT '账号类型',
  `publish_type` int(10) NULL COMMENT '发布方式',
  `supply` varchar(65) NULL COMMENT '类型补充',
  `item_type` int(10) NULL COMMENT '项目类型',
  `item_name` varchar(65) NULL COMMENT '项目名称',
  `url` varchar(1024) NULL COMMENT '链接',
  `price` decimal(10, 2) default 0.00 NULL COMMENT '报价',
  `tax` decimal(10, 2) default 0.00 NULL COMMENT '扣税（按9.72%）',
  `microtask_cost` decimal(10, 2) default 0.00 NULL COMMENT '微任务成本',
  `external_cost` decimal(10, 2) default 0.00 NULL COMMENT '外签/外部成本',
  `fans_cost` decimal(10, 2) default 0.00 NULL COMMENT '粉丝头条',
  `gross_profit` decimal(10, 2) default 0.00 NULL COMMENT '计算提成（毛利润）金额',
  `other_cost` decimal(10, 2) default 0.00 NULL COMMENT '其他（如承担博主差旅费）',
  `proportion` decimal(10, 2) default 0.00 NULL COMMENT '提成比率',
  `royalty` decimal(10, 2) default 0.00 NULL COMMENT '比例提成金额',
  `fixed_royalty` decimal(10, 2) default 0.00 NULL COMMENT '固定提成金额',
  `total_royalty` decimal(10, 2) default 0.00 NULL COMMENT '总提成金额',
  `status` tinyint default 0 NOT NULL COMMENT '状态：0 市场部新建订单；1 运营部完成待确认；2 市场部已确认；3 财务已结清 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息';

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES ('1', '订单信息', 'modules/order/order.html', NULL, '1', 'fa fa-file-code-o', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '查看', null, 'order:order:list,order:order:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '新增', null, 'order:order:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '修改', null, 'order:order:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, '删除', null, 'order:order:delete', '2', null, '6';

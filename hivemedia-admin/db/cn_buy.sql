/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : cn_buy

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
INSERT INTO `sys_menu` VALUES ('41', '162', '用户优惠券管理', 'modules/user/usercoupon.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('42', '41', '查看', null, 'user:usercoupon:list,sys:usercoupon:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('43', '41', '新增', null, 'user:usercoupon:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('44', '41', '修改', null, 'user:usercoupon:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('45', '41', '删除', null, 'user:usercoupon:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('46', '162', '用户提交商品', 'modules/user/usergoods.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('47', '46', '查看', null, 'user:usergoods:list,user:usergoods:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('48', '46', '新增', null, 'user:usergoods:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('49', '46', '修改', null, 'user:usergoods:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('50', '46', '删除', null, 'user:usergoods:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('51', '162', '用户消息信息', 'modules/user/usermessage.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('52', '51', '查看', null, 'user:usermessage:list,user:usermessage:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('53', '51', '新增', null, 'user:usermessage:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('54', '51', '修改', null, 'user:usermessage:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('55', '51', '删除', null, 'user:usermessage:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('56', '1', '优惠券管理', 'modules/sys/coupon.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('57', '56', '查看', null, 'sys:coupon:list,sys:coupon:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('58', '56', '新增', null, 'sys:coupon:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('59', '56', '修改', null, 'sys:coupon:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('60', '56', '删除', null, 'sys:coupon:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('61', '1', '数据字典管理', 'modules/sys/datadictionary.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('62', '61', '查看', null, 'sys:datadictionary:list,sys:datadictionary:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('63', '61', '新增', null, 'sys:datadictionary:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('64', '61', '修改', null, 'sys:datadictionary:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('65', '61', '删除', null, 'sys:datadictionary:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('66', '162', '意见反馈', 'modules/user/feedback.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('67', '66', '查看', null, 'user:feedback:list,user:feedback:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('68', '66', '新增', null, 'user:feedback:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('69', '66', '修改', null, 'user:feedback:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('70', '66', '删除', null, 'user:feedback:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('71', '163', '商品管理', 'modules/goods/goods.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('72', '71', '查看', null, 'goods:goods:list,goods:goods:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('73', '71', '新增', null, 'goods:goods:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('74', '71', '修改', null, 'goods:goods:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('75', '71', '删除', null, 'goods:goods:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('76', '163', '商品分类管理', 'modules/goods/goodscategory.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('77', '76', '查看', null, 'goods:goodscategory:list,goods:goodscategory:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('78', '76', '新增', null, 'goods:goodscategory:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('79', '76', '修改', null, 'goods:goodscategory:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('80', '76', '删除', null, 'goods:goodscategory:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('81', '163', '商品详情管理', 'modules/goods/goodsdetail.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('82', '81', '查看', null, 'goods:goodsdetail:list,sys:goodsdetail:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('83', '81', '新增', null, 'goods:goodsdetail:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('84', '81', '修改', null, 'goods:goodsdetail:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('85', '81', '删除', null, 'goods:goodsdetail:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('86', '163', '首页推荐banner', 'modules/goods/goodsrecbanner.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('87', '86', '查看', null, 'goods:goodsrecbanner:list,goods:goodsrecbanner:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('88', '86', '新增', null, 'goods:goodsrecbanner:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('89', '86', '修改', null, 'goods:goodsrecbanner:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('90', '86', '删除', null, 'goods:goodsrecbanner:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('91', '163', '商品尺码关联', 'modules/goods/goodssize.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('92', '91', '查看', null, 'goods:goodssize:list,goods:goodssize:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('93', '91', '新增', null, 'goods:goodssize:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('94', '91', '修改', null, 'goods:goodssize:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('95', '91', '删除', null, 'goods:goodssize:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('96', '1', '关键字设置表', 'modules/sys/keywordconfig.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('97', '96', '查看', null, 'sys:keywordconfig:list,sys:keywordconfig:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('98', '96', '新增', null, 'sys:keywordconfig:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('99', '96', '修改', null, 'sys:keywordconfig:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('100', '96', '删除', null, 'sys:keywordconfig:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('101', '1', '消息推送管理', 'modules/sys/message.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('102', '162', '用户地址信息管理', 'modules/user/useraddress.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('103', '102', '查看', null, 'user:useraddress:list,user:useraddress:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('104', '102', '新增', null, 'user:useraddress:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('105', '102', '修改', null, 'user:useraddress:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('106', '102', '删除', null, 'user:useraddress:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('107', '161', '查看', null, 'sys:message:list,sys:message:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('108', '161', '新增', null, 'sys:message:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('109', '161', '修改', null, 'sys:message:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('110', '161', '删除', null, 'sys:message:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('111', '164', '订单信息管理', 'modules/order/order.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('112', '111', '查看', null, 'order:order:list,order:order:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('113', '111', '新增', null, 'order:order:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('114', '111', '修改', null, 'order:order:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('115', '111', '删除', null, 'order:order:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('116', '164', '订单详情管理', 'modules/order/ordergoodsdetail.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('117', '116', '查看', null, 'order:ordergoodsdetail:list,order:ordergoodsdetail:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('118', '116', '新增', null, 'order:ordergoodsdetail:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('119', '116', '修改', null, 'order:ordergoodsdetail:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('120', '116', '删除', null, 'order:ordergoodsdetail:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('121', '162', '账户明细日志', 'modules/user/useraccountdataillog.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('122', '121', '查看', null, 'user:useraccountdataillog:list,user:useraccountdataillog:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('123', '121', '新增', null, 'user:useraccountdataillog:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('124', '121', '修改', null, 'user:useraccountdataillog:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('125', '121', '删除', null, 'user:useraccountdataillog:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('126', '162', '用户账户信息', 'modules/user/useraccount.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('127', '126', '查看', null, 'user:useraccount:list,user:useraccount:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('128', '126', '新增', null, 'user:useraccount:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('129', '126', '修改', null, 'user:useraccount:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('130', '126', '删除', null, 'user:useraccount:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('131', '162', '用户信息管理', 'modules/user/user.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('132', '131', '查看', null, 'user:user:list,user:user:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('133', '131', '新增', null, 'user:user:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('134', '131', '修改', null, 'user:user:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('135', '131', '删除', null, 'user:user:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('136', '162', '用户Token', 'modules/user/token.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('137', '136', '查看', null, 'user:token:list,sys:token:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('138', '136', '新增', null, 'user:token:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('139', '136', '修改', null, 'user:token:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('140', '136', '删除', null, 'user:token:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('141', '163', '尺码信息管理', 'modules/goods/size.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('142', '141', '查看', null, 'goods:size:list,goods:size:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('143', '141', '新增', null, 'goods:size:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('144', '141', '修改', null, 'goods:size:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('145', '141', '删除', null, 'goods:size:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('146', '164', '订单退货管理', 'modules/order/orderreturns.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('147', '146', '查看', null, 'order:orderreturns:list,order:orderreturns:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('148', '146', '新增', null, 'order:orderreturns:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('149', '146', '修改', null, 'order:orderreturns:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('150', '146', '删除', null, 'order:orderreturns:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('151', '164', '订单物流信息', 'modules/sys/orderlogistics.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('152', '151', '查看', null, 'sys:orderlogistics:list,sys:orderlogistics:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('153', '151', '新增', null, 'sys:orderlogistics:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('154', '151', '修改', null, 'sys:orderlogistics:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('155', '151', '删除', null, 'sys:orderlogistics:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('156', '163', '商品分类管理', 'modules/goods/category.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('157', '156', '查看', null, 'goods:category:list,goods:category:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('158', '156', '新增', null, 'goods:category:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('159', '156', '修改', null, 'goods:category:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('160', '156', '删除', null, 'goods:category:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('161', '1', '消息列表', 'modules/sys/message.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('162', '0', '用户管理', null, null, '0', 'fa fa-user-circle-o fa-2x', '1');
INSERT INTO `sys_menu` VALUES ('163', '0', '商品管理', null, null, '0', 'fa fa-cart-arrow-down fa-2x', '2');
INSERT INTO `sys_menu` VALUES ('164', '0', '订单管理', null, null, '0', 'fa fa-sort-amount-asc fa-2x', '3');

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
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) DEFAULT NULL COMMENT '父分类ID',
  `pic_id` int(11) DEFAULT NULL COMMENT '图标ID',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updaet_by` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '数据状态 0:正常 1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- ----------------------------
-- Records of tb_category
-- ----------------------------

-- ----------------------------
-- Table structure for tb_coupon
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon`;
CREATE TABLE `tb_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '优惠券名',
  `use_amount` decimal(10,2) DEFAULT '0.00' COMMENT '使用条件的值',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠券金额',
  `total` int(11) DEFAULT NULL COMMENT '发放总数',
  `issused_num` int(11) DEFAULT '0' COMMENT '已领取数量',
  `cur_state` tinyint(4) DEFAULT '1' COMMENT '状态 1:有效 2:无效 3，删除；',
  `number` int(11) DEFAULT '0' COMMENT '一段时间内有效的天数',
  `type` int(11) DEFAULT '1' COMMENT '生效类型  1：有效期内生效，2：领取一段时间内有效',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `summary` text COMMENT '简介描述',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='优惠券表';

-- ----------------------------
-- Records of tb_coupon
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
-- Table structure for tb_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedback`;
CREATE TABLE `tb_feedback` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `content` varchar(2000) NOT NULL COMMENT '返回内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见反馈表';

-- ----------------------------
-- Records of tb_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `shop_id` int(11) DEFAULT '0' COMMENT '店铺id(保留)',
  `tab_id` tinyint(4) NOT NULL COMMENT 'tab',
  `name` varchar(255) NOT NULL COMMENT '商品名',
  `desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `detail` text COMMENT '商品详情(保留字段,图文)',
  `art_no` varchar(50) NOT NULL COMMENT '商品货号(商品编号),唯一',
  `keyword` varchar(255) DEFAULT NULL COMMENT '商品关键字',
  `pic_show` bigint(20) NOT NULL COMMENT '商品展示图(主图)',
  `pic_detail1` bigint(20) DEFAULT NULL COMMENT '商品详情展示图1',
  `pic_detail2` bigint(20) DEFAULT NULL COMMENT '商品详情展示图2',
  `pic_detail3` bigint(20) DEFAULT NULL COMMENT '商品详情展示图3',
  `pic_detail4` bigint(20) DEFAULT NULL COMMENT '商品详情展示图4',
  `pic_detail5` bigint(20) DEFAULT NULL COMMENT '商品详情展示图5',
  `release_date` date DEFAULT NULL COMMENT '发售日期(暂时只到天,后期改)',
  `brand` varchar(50) DEFAULT NULL COMMENT '所属品牌名称(便于搜索)',
  `gender_for` tinyint(4) DEFAULT '2' COMMENT '适用性别：0:不限，1:男，2:女',
  `sales_volume` int(11) DEFAULT '0' COMMENT '销量',
  `latest_deal_price` decimal(10,2) DEFAULT NULL COMMENT '最新成交价(单位:元人民币)',
  `unit` tinyint(4) DEFAULT '1' COMMENT '单位计量: 1:件 2...',
  `weight` varchar(100) DEFAULT NULL COMMENT '重量',
  `inventory` int(11) DEFAULT '9999' COMMENT '库存',
  `is_on_sale` tinyint(4) DEFAULT '1' COMMENT '是否上架销售 1:是 0:否',
  `limit_times` int(11) DEFAULT '9999' COMMENT '单次购买上限数',
  `sort_no` int(11) DEFAULT '0' COMMENT '排序',
  `label` tinyint(4) DEFAULT '1' COMMENT '标签：1:无标签；2:热卖；3:包邮；4:折扣',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人id',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人id',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '数据状态 0:正常 1:删除',
  `audit_by` int(11) DEFAULT NULL COMMENT '审核人id',
  `audit_state` tinyint(4) DEFAULT '1' COMMENT '审核状态 1:待审核 2:审核通过 3:审核不通过',
  `audit_remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  PRIMARY KEY (`id`),
  KEY `index_goods_keyword` (`keyword`),
  KEY `index_goods_name` (`name`),
  KEY `index_goods_art_no` (`art_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of tb_goods
-- ----------------------------

-- ----------------------------
-- Table structure for tb_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_category`;
CREATE TABLE `tb_goods_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- ----------------------------
-- Records of tb_goods_category
-- ----------------------------

-- ----------------------------
-- Table structure for tb_goods_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_detail`;
CREATE TABLE `tb_goods_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `size_id` int(11) DEFAULT NULL COMMENT '尺码id',
  `cash_pledge` decimal(11,2) DEFAULT NULL COMMENT '押金',
  `cur_state` tinyint(4) DEFAULT '1' COMMENT '单间商品状态：1，待售；2，已定；3，已售；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods_detail
-- ----------------------------

-- ----------------------------
-- Table structure for tb_goods_rec_banner
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_rec_banner`;
CREATE TABLE `tb_goods_rec_banner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tab_id` tinyint(4) NOT NULL DEFAULT '1' COMMENT 'tab  1:球鞋 2:潮服 3:背包 4:其他',
  `pic_id` bigint(20) DEFAULT NULL COMMENT '图片id',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `url` varchar(255) DEFAULT NULL COMMENT '跳转链接',
  `sort_no` int(11) DEFAULT '0' COMMENT '排序',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '数据状态 0:正常 1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品推荐banner表';

-- ----------------------------
-- Records of tb_goods_rec_banner
-- ----------------------------

-- ----------------------------
-- Table structure for tb_goods_size
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_size`;
CREATE TABLE `tb_goods_size` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `size_id` bigint(20) NOT NULL COMMENT '尺码id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `expect_max_price` decimal(10,2) DEFAULT NULL COMMENT '最高求购价格',
  `expect_min_price` decimal(10,2) DEFAULT NULL COMMENT '最低售价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品尺码关联表';

-- ----------------------------
-- Records of tb_goods_size
-- ----------------------------

-- ----------------------------
-- Table structure for tb_keyword_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_keyword_config`;
CREATE TABLE `tb_keyword_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `keyword` varchar(200) NOT NULL COMMENT '关键字',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '数据状态 0:有效 1:无效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_keyword` (`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关键字设置表';

-- ----------------------------
-- Records of tb_keyword_config
-- ----------------------------

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` tinyint(4) DEFAULT '1' COMMENT '类型',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `content` text NOT NULL COMMENT '消息内容',
  `to_user_id` text COMMENT '接收人id(多个以逗号隔开)',
  `begin_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '预计发送时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '推送状态 1:等待推送；2:推送中；3:推送失败；4:推送成功',
  `level` tinyint(4) DEFAULT '1' COMMENT '1:系统通知 2:服务通知 3:活动通知(优先级更高,优先推送) ...',
  `is_read` tinyint(4) DEFAULT '0' COMMENT '是否已读 0:未读 1:已读',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人id',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '数据状态 0:有效 1:无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息推送表';

-- ----------------------------
-- Records of tb_message
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sale_user_id` int(11) unsigned DEFAULT NULL COMMENT '卖家用户id',
  `buy_user_id` int(11) unsigned DEFAULT NULL COMMENT '买家用户id',
  `order_no` varchar(100) NOT NULL COMMENT '订单单号(供查询)',
  `order_status` tinyint(4) DEFAULT '1' COMMENT '订单状态 1:待付款 2:已付款 3:已发货 4:已签收 5:退货申请中 6:退货中 7:已完成退货 8:取消交易',
  `cancel_reason` tinyint(11) DEFAULT NULL COMMENT '仅针对订单状态为8,订单取消原因：1、拍错了；2、商品不发货；3、协商一致退款；4、其他原因''',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品项目数量(不是商品的数量)',
  `amount_total` decimal(10,2) DEFAULT NULL COMMENT '商品总价',
  `order_amount_total` decimal(10,2) DEFAULT NULL COMMENT '实际付款金额',
  `logistics_fee` decimal(10,2) DEFAULT NULL COMMENT '运费金额',
  `make_out_invoice` tinyint(4) DEFAULT '0' COMMENT '是否开发票 0:否 1:是 (保留字段)',
  `invoice_no` varchar(100) DEFAULT NULL COMMENT '发票编号 (保留字段)',
  `address_id` bigint(20) DEFAULT NULL COMMENT '订单收货地址id',
  `logistics_id` bigint(20) DEFAULT NULL COMMENT '物流id',
  `pay_channel` tinyint(4) DEFAULT '1' COMMENT '支付渠道 1:支付宝 2:微信...',
  `out_trade_no` varchar(100) DEFAULT NULL COMMENT '第三方支付流水号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间(下单时间)',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `cur_state` tinyint(4) DEFAULT '1' COMMENT '订单状态 1:有效 2:无效',
  `coupon_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_goods_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_goods_detail`;
CREATE TABLE `tb_order_goods_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称(防止商品信息被删除)',
  `goods_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格(防止商品信息被删除)',
  `number` int(11) DEFAULT NULL COMMENT '购买数量',
  `subtotal` decimal(10,2) DEFAULT NULL COMMENT '小计金额',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
-- Records of tb_order_goods_detail
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_logistics
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_logistics`;
CREATE TABLE `tb_order_logistics` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `express_no` varchar(100) NOT NULL COMMENT '物流单号',
  `consignee_realname` varchar(20) NOT NULL COMMENT '收货人姓名(更保险)',
  `consignee_telphone` varchar(20) NOT NULL COMMENT '联系电话(更保险)',
  `consignee_address` varchar(1000) DEFAULT NULL COMMENT '收货地址',
  `consignee_zip` varchar(50) DEFAULT NULL COMMENT '邮政编码',
  `logistics_type` tinyint(4) DEFAULT '1' COMMENT '物流方式 1:ems ...',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单物流信息表';

-- ----------------------------
-- Records of tb_order_logistics
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_returns
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_returns`;
CREATE TABLE `tb_order_returns` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `returns_no` varchar(100) NOT NULL COMMENT '退货编号',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `express_no` varchar(100) NOT NULL COMMENT '物流单号',
  `consignee_realname` varchar(20) NOT NULL COMMENT '收货人姓名',
  `consignee_telphone` varchar(20) NOT NULL COMMENT '联系电话',
  `consignee_address` varchar(100) NOT NULL COMMENT '收货地址',
  `consignee_zip` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `logistics_type` tinyint(4) DEFAULT '1' COMMENT '物流方式 1:ems...',
  `logistics_fee` decimal(10,2) DEFAULT NULL COMMENT '退货运费',
  `orderlogistics_status` tinyint(4) DEFAULT '1' COMMENT '物流状态',
  `orderlogistics_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '物流描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单退货表';

-- ----------------------------
-- Records of tb_order_returns
-- ----------------------------

-- ----------------------------
-- Table structure for tb_size
-- ----------------------------
DROP TABLE IF EXISTS `tb_size`;
CREATE TABLE `tb_size` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `desc` varchar(100) NOT NULL COMMENT '尺码描述',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '数据状态 0:有效 1:无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='尺码信息表';

-- ----------------------------
-- Records of tb_size
-- ----------------------------

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
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `id_card_no` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `login_pwd` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `avatar_id` bigint(20) DEFAULT NULL COMMENT '头像id',
  `gender` tinyint(4) DEFAULT '0' COMMENT '性别 0:未知 1:男 2:女',
  `open_id` varchar(200) DEFAULT NULL COMMENT 'open_id(保留字段)',
  `token` varchar(200) DEFAULT NULL COMMENT 'token(保留字段)',
  `real_name_authorized` tinyint(4) DEFAULT '0' COMMENT '是否实名认证 0:未认证 1:认证成功 2:认证失败',
  `latest_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `latest_login_ip` varchar(255) DEFAULT NULL COMMENT '最近登录ip',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `cur_state` tinyint(4) DEFAULT '1' COMMENT '状态 1:正常 2:禁用 3:删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_account`;
CREATE TABLE `tb_user_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `remaining_money` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
  `state` tinyint(4) DEFAULT '0' COMMENT '状态 0:有效 1:无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户信息';

-- ----------------------------
-- Records of tb_user_account
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_account_datail_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_account_datail_log`;
CREATE TABLE `tb_user_account_datail_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `change_type` tinyint(4) DEFAULT '1' COMMENT '变动类型 1:退换至微信 2:定金退回 3:支付保证金 4:微信转账...',
  `change_money` decimal(10,2) NOT NULL COMMENT '变动金额',
  `before_change_money` decimal(10,2) DEFAULT NULL COMMENT '变动前金额',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `state` tinyint(4) DEFAULT '0' COMMENT '状态 0:有效 1:无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户明细日志表';

-- ----------------------------
-- Records of tb_user_account_datail_log
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_address`;
CREATE TABLE `tb_user_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `province_id` int(11) DEFAULT NULL COMMENT '省id',
  `city_id` int(11) DEFAULT NULL COMMENT '市id',
  `area_id` int(11) DEFAULT NULL COMMENT '区id',
  `detail_address` varchar(1000) DEFAULT NULL COMMENT '详细地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_default` tinyint(4) DEFAULT '1' COMMENT '是否默认 0:否 1:是(同一个用户只能有一个默认地址)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户地址信息表';

-- ----------------------------
-- Records of tb_user_address
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_coupon`;
CREATE TABLE `tb_user_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户优惠券关联表id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint(20) DEFAULT NULL COMMENT '满额送优惠券的子订单ID',
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券ID',
  `use_amount` decimal(10,2) DEFAULT '0.00' COMMENT '使用条件的值',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠券金额',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态：0，待发放；1，未使用 ；2，已使用；3，已过期；9，已作废；',
  `type` tinyint(4) DEFAULT '1' COMMENT '生效类型  1：有效期内生效，2：领取一段时间内有效',
  `begin_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `summary` text COMMENT '简介描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户—优惠券关联表';

-- ----------------------------
-- Records of tb_user_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_goods`;
CREATE TABLE `tb_user_goods` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sale_time` datetime DEFAULT NULL,
  `sex` int(11) DEFAULT NULL COMMENT '性别：0男，1女，2不限',
  `number` int(11) DEFAULT NULL COMMENT '货号',
  `picture` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `sales_volume` varchar(255) DEFAULT NULL,
  `classification` varchar(255) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `size` double DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_goods
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_message`;
CREATE TABLE `tb_user_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `message_id` bigint(20) DEFAULT NULL COMMENT '消息id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `type` varchar(255) DEFAULT NULL COMMENT '消息类型 1、订单；2、商品；3、链接',
  `target_id` int(11) DEFAULT NULL COMMENT '目标id',
  `target_url` varchar(255) DEFAULT NULL COMMENT '目标链接',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `status` varchar(255) DEFAULT NULL COMMENT '状态：1、未读；2、已读；3、删除；',
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户消息信息表';

-- ----------------------------
-- Records of tb_user_message
-- ----------------------------

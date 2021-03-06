/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : jrjx_base

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 06/09/2021 11:01:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jr_personnel_parameters
-- ----------------------------
DROP TABLE IF EXISTS `jr_personnel_parameters`;
CREATE TABLE `jr_personnel_parameters`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '人事参数id',
  `parameter_category` char(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '参数类别    1-岗位 ，2-技术级别，3-加班类型，4-请假类型，5-员工学历，6-职务，7-人事，8-状态',
  `parameter_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '参数值',
  `parameter_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '参数名称',
  `parameter_sort` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `parameter_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `parameter_state` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '状态   1-启用，2-禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '人事参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jr_personnel_parameters
-- ----------------------------
INSERT INTO `jr_personnel_parameters` VALUES (7, '1', '车间主任', '车间主任', 1, '11', '1', '2021-09-06 10:19:02');

-- ----------------------------
-- Table structure for t_data_permission_test
-- ----------------------------
DROP TABLE IF EXISTS `t_data_permission_test`;
CREATE TABLE `t_data_permission_test`  (
  `FIELD1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIELD2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIELD3` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIELD4` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DEPT_ID` int(11) NOT NULL,
  `CREATE_TIME` datetime(0) NOT NULL,
  `ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户权限测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_data_permission_test
-- ----------------------------
INSERT INTO `t_data_permission_test` VALUES ('小米', '小米10Pro', '4999', '珍珠白', 1, '2020-04-14 15:00:38', 1);
INSERT INTO `t_data_permission_test` VALUES ('腾讯', '黑鲨游戏手机3', '3799', '铠甲灰', 2, '2020-04-14 15:01:36', 2);
INSERT INTO `t_data_permission_test` VALUES ('华为', '华为P30', '3299', '天空之境', 1, '2020-04-14 15:03:11', 3);
INSERT INTO `t_data_permission_test` VALUES ('华为', '华为P40Pro', '6488', '亮黑色', 3, '2020-04-14 15:04:31', 4);
INSERT INTO `t_data_permission_test` VALUES ('vivo', 'Vivo iQOO 3', '3998', '拉力橙', 4, '2020-04-14 15:05:55', 5);
INSERT INTO `t_data_permission_test` VALUES ('一加', '一加7T', '3199', '冰际蓝', 5, '2020-04-14 15:06:53', 6);
INSERT INTO `t_data_permission_test` VALUES ('三星', '三星Galaxy S10', '4098', '浩玉白', 6, '2020-04-14 15:08:25', 7);
INSERT INTO `t_data_permission_test` VALUES ('苹果', 'iPhone 11 pro max', '9198', '暗夜绿', 4, '2020-04-14 15:09:20', 8);

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `DEPT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级部门ID',
  `DEPT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`DEPT_ID`) USING BTREE,
  INDEX `t_dept_parent_id`(`PARENT_ID`) USING BTREE,
  INDEX `t_dept_dept_name`(`DEPT_NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, 0, '开发部', 1, '2019-06-14 20:56:41', NULL);
INSERT INTO `t_dept` VALUES (2, 1, '开发一部', 1, '2019-06-14 20:58:46', NULL);
INSERT INTO `t_dept` VALUES (3, 1, '开发二部', 2, '2019-06-14 20:58:56', NULL);
INSERT INTO `t_dept` VALUES (4, 0, '采购部', 2, '2019-06-14 20:59:56', NULL);
INSERT INTO `t_dept` VALUES (5, 0, '财务部', 3, '2019-06-14 21:00:08', NULL);
INSERT INTO `t_dept` VALUES (6, 0, '销售部', 4, '2019-06-14 21:00:15', NULL);
INSERT INTO `t_dept` VALUES (7, 0, '工程部', 5, '2019-06-14 21:00:42', NULL);
INSERT INTO `t_dept` VALUES (8, 0, '行政部', 6, '2019-06-14 21:00:49', NULL);
INSERT INTO `t_dept` VALUES (9, 0, '人力资源部', 8, '2019-06-14 21:01:14', '2019-06-14 21:01:34');
INSERT INTO `t_dept` VALUES (10, 0, '系统部', 7, '2019-06-14 21:01:31', NULL);

-- ----------------------------
-- Table structure for t_eximport
-- ----------------------------
DROP TABLE IF EXISTS `t_eximport`;
CREATE TABLE `t_eximport`  (
  `FIELD1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段1',
  `FIELD2` int(11) NOT NULL COMMENT '字段2',
  `FIELD3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段3',
  `CREATE_TIME` datetime(0) NOT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Excel导入导出测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_eximport
-- ----------------------------
INSERT INTO `t_eximport` VALUES ('字段1', 1, 'mrbird0@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 2, 'mrbird1@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 3, 'mrbird2@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 4, 'mrbird3@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 5, 'mrbird4@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 6, 'mrbird5@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 7, 'mrbird6@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 8, 'mrbird7@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 9, 'mrbird8@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 10, 'mrbird9@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 11, 'mrbird10@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 12, 'mrbird11@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 13, 'mrbird12@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 14, 'mrbird13@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 15, 'mrbird14@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 16, 'mrbird15@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 17, 'mrbird16@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 18, 'mrbird17@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 19, 'mrbird18@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 20, 'mrbird19@gmail.com', '2019-06-13 03:14:06');

-- ----------------------------
-- Table structure for t_generator_config
-- ----------------------------
DROP TABLE IF EXISTS `t_generator_config`;
CREATE TABLE `t_generator_config`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `base_package` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '基础包名',
  `entity_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'entity文件存放路径',
  `mapper_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'mapper文件存放路径',
  `mapper_xml_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'mapper xml文件存放路径',
  `service_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'servcie文件存放路径',
  `service_impl_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'serviceImpl文件存放路径',
  `controller_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'controller文件存放路径',
  `is_trim` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否去除前缀 1是 0否',
  `trim_value` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前缀内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_generator_config
-- ----------------------------
INSERT INTO `t_generator_config` VALUES (1, 'qiufeng', 'com.erp.personnel', 'entity', 'mapper', 'mapper', 'service', 'service.impl', 'controller', '1', 'jr_');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`  (
  `JOB_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `BEAN_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名',
  `PARAMS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `CRON_EXPRESSION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'cron表达式',
  `STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务状态  0：正常  1：暂停',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`JOB_ID`) USING BTREE,
  INDEX `t_job_create_time`(`CREATE_TIME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_job
-- ----------------------------
INSERT INTO `t_job` VALUES (1, 'testTask', 'test', 'mrbird', '0/1 * * * * ?', '1', '有参任务调度测试~~', '2018-02-24 16:26:14');
INSERT INTO `t_job` VALUES (2, 'testTask', 'test1', NULL, '0/10 * * * * ?', '1', '无参任务调度测试', '2018-02-24 17:06:23');
INSERT INTO `t_job` VALUES (3, 'testTask', 'test', 'hello world', '0/1 * * * * ?', '1', '有参任务调度测试,每隔一秒触发', '2018-02-26 09:28:26');
INSERT INTO `t_job` VALUES (11, 'testTask', 'test2', NULL, '0/5 * * * * ?', '1', '测试异常', '2018-02-26 11:15:30');

-- ----------------------------
-- Table structure for t_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_job_log`;
CREATE TABLE `t_job_log`  (
  `LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `JOB_ID` bigint(20) NOT NULL COMMENT '任务id',
  `BEAN_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名',
  `PARAMS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `ERROR` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '失败信息',
  `TIMES` decimal(11, 0) NULL DEFAULT NULL COMMENT '耗时(单位：毫秒)',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LOG_ID`) USING BTREE,
  INDEX `t_job_log_create_time`(`CREATE_TIME`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2562 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `OPERATION` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作内容',
  `TIME` decimal(11, 0) NULL DEFAULT NULL COMMENT '耗时',
  `METHOD` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作方法',
  `PARAMS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法参数',
  `IP` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者IP',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `t_log_create_time`(`CREATE_TIME`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1135 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES (1011, 'mrbird', '新增菜单/按钮', 12, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=179, parentId=0, menuName=人事管理, url=, perms=, icon=layui-icon-user, type=0, orderNum=6, createTime=Thu Sep 02 12:05:15 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 12:05:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1012, 'mrbird', '修改角色', 77, 'com.erp.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Thu Sep 02 12:08:35 CST 2021, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,127,128,129,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169,178,179)\"', '192.168.0.80', '2021-09-02 12:08:35', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1013, 'mrbird', '新增菜单/按钮', 10, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=180, parentId=179, menuName=人事参数, url=, perms=, icon=layui-icon-time-circle, type=0, orderNum=1, createTime=Thu Sep 02 12:11:00 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 12:11:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1014, 'mrbird', '修改角色', 41, 'com.erp.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Thu Sep 02 12:12:11 CST 2021, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,127,128,129,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169,178,179,180)\"', '192.168.0.80', '2021-09-02 12:12:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1015, 'mrbird', '新增菜单/按钮', 8, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=181, parentId=180, menuName=人事参数, url=, perms=, icon=layui-icon-YUAN, type=0, orderNum=1, createTime=Thu Sep 02 12:12:48 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 12:12:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1016, 'mrbird', '修改角色', 49, 'com.erp.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Thu Sep 02 12:13:12 CST 2021, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,127,128,129,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169,178,179,180,181)\"', '192.168.0.80', '2021-09-02 12:13:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1017, 'mrbird', '删除菜单/按钮', 53, 'com.erp.system.controller.MenuController.deleteMenus()', ' menuIds: \"181\"', '192.168.0.80', '2021-09-02 14:25:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1018, 'mrbird', '修改菜单/按钮', 27, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=180, parentId=179, menuName=人事参数, url=, perms=, icon=layui-icon-chrome-fill, type=0, orderNum=1, createTime=null, modifyTime=Thu Sep 02 14:28:28 CST 2021)\"', '192.168.0.80', '2021-09-02 14:28:28', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1019, 'mrbird', '新增菜单/按钮', 13, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=182, parentId=179, menuName=员工档案, url=, perms=, icon=layui-icon-reconciliation, type=0, orderNum=2, createTime=Thu Sep 02 14:30:30 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 14:30:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1020, 'mrbird', '新增菜单/按钮', 14, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=183, parentId=179, menuName=员工请假, url=, perms=, icon=layui-icon-tags-fill, type=0, orderNum=3, createTime=Thu Sep 02 14:34:37 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 14:34:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1021, 'mrbird', '新增菜单/按钮', 11, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=184, parentId=179, menuName=员工领取记录, url=, perms=, icon=layui-icon-gift, type=0, orderNum=4, createTime=Thu Sep 02 14:35:21 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 14:35:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1022, 'mrbird', '新增菜单/按钮', 8, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=185, parentId=179, menuName=员工合同, url=, perms=, icon=layui-icon-solution, type=0, orderNum=5, createTime=Thu Sep 02 14:36:03 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 14:36:04', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1023, 'mrbird', '修改菜单/按钮', 17, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=179, parentId=179, menuName=宿舍管理, url=, perms=, icon=layui-icon-home, type=0, orderNum=6, createTime=null, modifyTime=Thu Sep 02 14:46:35 CST 2021)\"', '192.168.0.80', '2021-09-02 14:46:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1024, 'mrbird', '新增菜单/按钮', 7, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=186, parentId=0, menuName=人事管理, url=, perms=6, icon=layui-icon-user, type=0, orderNum=null, createTime=Thu Sep 02 14:49:41 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 14:49:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1025, 'mrbird', '修改菜单/按钮', 13, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=186, parentId=0, menuName=人事管理, url=, perms=, icon=layui-icon-user, type=0, orderNum=6, createTime=null, modifyTime=Thu Sep 02 14:50:01 CST 2021)\"', '192.168.0.80', '2021-09-02 14:50:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1026, 'mrbird', '修改角色', 47, 'com.erp.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Thu Sep 02 14:51:12 CST 2021, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,127,128,129,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169,178,186)\"', '192.168.0.80', '2021-09-02 14:51:13', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1027, 'mrbird', '新增菜单/按钮', 9, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=187, parentId=179, menuName=员工异动记录, url=, perms=, icon=layui-icon-team, type=0, orderNum=7, createTime=Thu Sep 02 14:55:49 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 14:55:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1028, 'mrbird', '新增菜单/按钮', 13, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=188, parentId=187, menuName=调岗记录, url=, perms=, icon=layui-icon-container, type=0, orderNum=1, createTime=Thu Sep 02 14:57:05 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 14:57:06', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1029, 'mrbird', '新增菜单/按钮', 14, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=189, parentId=187, menuName=调薪记录, url=, perms=, icon=layui-icon-database, type=0, orderNum=2, createTime=Thu Sep 02 14:57:41 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 14:57:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1030, 'mrbird', '新增菜单/按钮', 8, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=190, parentId=187, menuName=奖罚记录, url=, perms=, icon=layui-icon-sever, type=0, orderNum=3, createTime=Thu Sep 02 14:58:12 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-02 14:58:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1031, 'mrbird', '修改角色', 44, 'com.erp.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Thu Sep 02 14:59:01 CST 2021, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,127,128,129,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169,178,179,180,182,183,184,185,186,187,188,189,190)\"', '192.168.0.80', '2021-09-02 14:59:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1032, 'mrbird', '修改GeneratorConfig', 16, 'com.erp.generator.controller.GeneratorConfigController.updateGeneratorConfig()', ' generatorConfig: GeneratorConfig(id=1, author=qiufeng, basePackage=com.erp.personnel, entityPackage=entity, mapperPackage=mapper, mapperXmlPackage=mapper, servicePackage=service, serviceImplPackage=service.impl, controllerPackage=controller, isTrim=1, trimValue=jr_, javaPath=/src/main/java/, resourcesPath=src/main/resources, date=2021-09-03 15:04:18, tableName=null, tableComment=null, className=null, hasDate=false, hasBigDecimal=false, columns=null)', '192.168.0.80', '2021-09-03 15:04:18', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1033, 'mrbird', '修改菜单/按钮', 48, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=180, parentId=179, menuName=人事参数, url=personnel/parameters, perms=, icon=layui-icon-chrome-fill, type=0, orderNum=1, createTime=null, modifyTime=Fri Sep 03 16:12:08 CST 2021)\"', '192.168.0.80', '2021-09-03 16:12:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1034, 'mrbird', '修改菜单/按钮', 47, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=180, parentId=179, menuName=人事参数, url=personnel/personnelParameters, perms=personnel:view, icon=layui-icon-chrome-fill, type=0, orderNum=1, createTime=null, modifyTime=Fri Sep 03 16:18:20 CST 2021)\"', '192.168.0.80', '2021-09-03 16:18:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1035, 'mrbird', '修改角色', 56, 'com.erp.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Fri Sep 03 16:18:47 CST 2021, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,127,128,129,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169,178,179,180,182,183,184,185,186,187,188,189,190)\"', '192.168.0.80', '2021-09-03 16:18:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1036, 'mrbird', '修改菜单/按钮', 45, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=180, parentId=179, menuName=人事参数, url=personnel/parameters, perms=personnel:view, icon=layui-icon-chrome-fill, type=0, orderNum=1, createTime=null, modifyTime=Fri Sep 03 16:23:16 CST 2021)\"', '192.168.0.80', '2021-09-03 16:23:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1037, 'mrbird', '修改菜单/按钮', 42, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=180, parentId=179, menuName=人事参数, url=personnel/personnelParameters, perms=personnel:view, icon=layui-icon-chrome-fill, type=0, orderNum=1, createTime=null, modifyTime=Fri Sep 03 16:35:26 CST 2021)\"', '192.168.0.80', '2021-09-03 16:35:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1038, 'mrbird', '修改菜单/按钮', 48, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=180, parentId=179, menuName=人事参数, url=/personnel/personnelParameters, perms=personnel:view, icon=layui-icon-chrome-fill, type=0, orderNum=1, createTime=null, modifyTime=Fri Sep 03 16:41:01 CST 2021)\"', '192.168.0.80', '2021-09-03 16:41:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1039, 'mrbird', '新增菜单/按钮', 17, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=191, parentId=180, menuName=新增, url=null, perms=personnelParameters:add, icon=null, type=1, orderNum=null, createTime=Fri Sep 03 17:20:47 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-03 17:20:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1040, 'mrbird', '修改角色', 81, 'com.erp.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Fri Sep 03 17:21:25 CST 2021, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,127,128,129,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169,178,179,180,191,182,183,184,185,186,187,188,189,190)\"', '192.168.0.80', '2021-09-03 17:21:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1041, 'mrbird', '新增用户', 63, 'com.erp.system.controller.UserController.addUser()', ' user: \"User(userId=9, username=wwww, password=a34846536bb01ae105fdf4b3462ab00a, deptId=1, email=15011080327@163.com, mobile=15011080327, status=1, createTime=Fri Sep 03 17:33:34 CST 2021, modifyTime=null, lastLoginTime=null, sex=0, avatar=default.jpg, theme=black, isTab=1, description=, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=1, roleName=null, deptIds=1, stringPermissions=null, roles=null)\"', '192.168.0.80', '2021-09-03 17:33:35', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1042, 'mrbird', '修改用户', 46, 'com.erp.system.controller.UserController.updateUser()', ' user: \"User(userId=9, username=null, password=null, deptId=1, email=15011080327@163.com, mobile=15011080327, status=0, createTime=null, modifyTime=Fri Sep 03 17:33:58 CST 2021, lastLoginTime=null, sex=0, avatar=null, theme=null, isTab=null, description=, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=1, roleName=null, deptIds=1, stringPermissions=null, roles=null)\"', '192.168.0.80', '2021-09-03 17:33:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1043, 'mrbird', '修改菜单/按钮', 52, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=180, parentId=179, menuName=人事参数, url=/personnel/personnelParameters, perms=personnelParameters:view, icon=layui-icon-chrome-fill, type=0, orderNum=1, createTime=null, modifyTime=Fri Sep 03 17:51:17 CST 2021)\"', '192.168.0.80', '2021-09-03 17:51:18', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1044, 'mrbird', '新增用户', 28242, 'com.erp.system.controller.UserController.addUser()', ' user: \"User(userId=10, username=wwwww, password=b981b0c921e34511e47c20e0d125bc2f, deptId=2, email=15011080327@163.com, mobile=15011080327, status=1, createTime=Fri Sep 03 18:05:11 CST 2021, modifyTime=null, lastLoginTime=null, sex=0, avatar=default.jpg, theme=black, isTab=1, description=, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=1, roleName=null, deptIds=, stringPermissions=null, roles=null)\"', '192.168.0.80', '2021-09-03 18:05:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1045, 'mrbird', '删除用户', 40, 'com.erp.system.controller.UserController.deleteUsers()', ' userIds: \"10\"', '192.168.0.80', '2021-09-03 18:05:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1046, 'mrbird', '新增PersonnelParameters', 7717, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=2, parameterCategory=1, parameterValue=212, parameterName=21212, parameterSort=2, parameterRemarks=, parameterState=1, createTime=null)\"', '192.168.0.80', '2021-09-03 18:28:59', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1047, 'mrbird', '新增PersonnelParameters', 9, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=3, parameterCategory=2, parameterValue=飞升, parameterName=飞升, parameterSort=3, parameterRemarks=, parameterState=2, createTime=null)\"', '192.168.0.80', '2021-09-03 18:31:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1048, 'mrbird', '新增菜单/按钮', 23, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=192, parentId=180, menuName=删除, url=null, perms=personnelParameters:delete, icon=null, type=1, orderNum=null, createTime=Sat Sep 04 09:04:11 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-04 09:04:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1049, 'mrbird', '修改角色', 81, 'com.erp.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Sat Sep 04 09:04:25 CST 2021, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,127,128,129,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169,178,179,180,191,192,182,183,184,185,186,187,188,189,190)\"', '192.168.0.80', '2021-09-04 09:04:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1050, 'mrbird', '删除PersonnelParameters', 19, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"1\"', '192.168.0.80', '2021-09-04 09:16:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1051, 'mrbird', '删除PersonnelParameters', 14, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"2\"', '192.168.0.80', '2021-09-04 09:28:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1052, 'mrbird', '删除PersonnelParameters', 11, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"3\"', '192.168.0.80', '2021-09-04 09:29:18', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1053, 'mrbird', '新增PersonnelParameters', 16, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=4, parameterCategory=1, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 09:33:09 CST 2021)\"', '192.168.0.80', '2021-09-04 09:33:10', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1054, 'mrbird', '删除PersonnelParameters', 21, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"4\"', '192.168.0.80', '2021-09-04 09:35:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1055, 'mrbird', '新增PersonnelParameters', 15, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=5, parameterCategory=1, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=, parameterState=2, createTime=Sat Sep 04 09:35:56 CST 2021)\"', '192.168.0.80', '2021-09-04 09:35:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1056, 'mrbird', '删除PersonnelParameters', 11, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"5\"', '192.168.0.80', '2021-09-04 09:36:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1057, 'mrbird', '新增PersonnelParameters', 15, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=6, parameterCategory=1, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=, parameterState=2, createTime=Sat Sep 04 09:36:29 CST 2021)\"', '192.168.0.80', '2021-09-04 09:36:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1058, 'mrbird', '删除PersonnelParameters', 12, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"6\"', '192.168.0.80', '2021-09-04 09:36:35', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1059, 'mrbird', '新增PersonnelParameters', 13, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=7, parameterCategory=1, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 09:36:48 CST 2021)\"', '192.168.0.80', '2021-09-04 09:36:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1060, 'mrbird', '新增PersonnelParameters', 9, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=8, parameterCategory=1, parameterValue=总经理, parameterName=总经理, parameterSort=1, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 09:37:39 CST 2021)\"', '192.168.0.80', '2021-09-04 09:37:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1061, 'mrbird', '删除PersonnelParameters', 12, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"8\"', '192.168.0.80', '2021-09-04 09:38:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1062, 'mrbird', '新增PersonnelParameters', 16, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=9, parameterCategory=1, parameterValue=, parameterName=, parameterSort=null, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 10:05:00 CST 2021)\"', '192.168.0.80', '2021-09-04 10:05:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1063, 'mrbird', '删除PersonnelParameters', 18, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"9\"', '192.168.0.80', '2021-09-04 10:05:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1064, 'mrbird', '新增PersonnelParameters', 13, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=10, parameterCategory=1, parameterValue=技术员, parameterName=技术员, parameterSort=2, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 10:12:41 CST 2021)\"', '192.168.0.80', '2021-09-04 10:12:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1065, 'mrbird', '新增PersonnelParameters', 5, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=11, parameterCategory=1, parameterValue=事假, parameterName=事假, parameterSort=3, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 10:13:57 CST 2021)\"', '192.168.0.80', '2021-09-04 10:13:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1066, 'mrbird', '删除PersonnelParameters', 4, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"11\"', '192.168.0.80', '2021-09-04 10:14:10', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1067, 'mrbird', '新增PersonnelParameters', 13, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=12, parameterCategory=4, parameterValue=病假, parameterName=病假, parameterSort=3, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 10:17:01 CST 2021)\"', '192.168.0.80', '2021-09-04 10:17:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1068, 'mrbird', '新增PersonnelParameters', 7, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=13, parameterCategory=6, parameterValue=销售内勤, parameterName=销售内勤, parameterSort=2, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 10:20:00 CST 2021)\"', '192.168.0.80', '2021-09-04 10:20:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1069, 'mrbird', '新增用户', 10822, 'com.erp.system.controller.UserController.addUser()', ' user: \"User(userId=10, username=ssss, password=6f39ff0f9aa09053cecc1fe82630ba7b, deptId=4, email=150@163.com, mobile=15011080324, status=1, createTime=Sat Sep 04 10:21:48 CST 2021, modifyTime=null, lastLoginTime=null, sex=1, avatar=default.jpg, theme=black, isTab=1, description=, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=1, roleName=null, deptIds=7, stringPermissions=null, roles=null)\"', '192.168.0.80', '2021-09-04 10:21:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1070, 'mrbird', '删除用户', 34, 'com.erp.system.controller.UserController.deleteUsers()', ' userIds: \"10\"', '192.168.0.80', '2021-09-04 10:22:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1071, 'mrbird', '删除PersonnelParameters', 11, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"10\"', '192.168.0.80', '2021-09-04 11:31:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1072, 'mrbird', '新增PersonnelParameters', 13, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=14, parameterCategory=3, parameterValue=45, parameterName=45, parameterSort=1, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 11:31:53 CST 2021)\"', '192.168.0.80', '2021-09-04 11:31:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1073, 'mrbird', '删除PersonnelParameters', 10, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"14\"', '192.168.0.80', '2021-09-04 11:31:59', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1074, 'mrbird', '删除PersonnelParameters', 25, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"12\"', '192.168.0.80', '2021-09-04 11:34:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1075, 'mrbird', '新增PersonnelParameters', 13, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=15, parameterCategory=1, parameterValue=212, parameterName=212, parameterSort=1, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 11:34:57 CST 2021)\"', '192.168.0.80', '2021-09-04 11:34:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1076, 'mrbird', '删除PersonnelParameters', 15, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"15\"', '192.168.0.80', '2021-09-04 11:35:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1077, 'mrbird', '删除PersonnelParameters', 12, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"13\"', '192.168.0.80', '2021-09-04 11:35:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1078, 'mrbird', '新增PersonnelParameters', 15, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=16, parameterCategory=1, parameterValue=213, parameterName=21, parameterSort=21, parameterRemarks=212, parameterState=1, createTime=Sat Sep 04 11:35:47 CST 2021)\"', '192.168.0.80', '2021-09-04 11:35:47', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1079, 'mrbird', '新增PersonnelParameters', 11, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=17, parameterCategory=3, parameterValue=12, parameterName=12, parameterSort=12, parameterRemarks=12, parameterState=2, createTime=Sat Sep 04 11:35:57 CST 2021)\"', '192.168.0.80', '2021-09-04 11:35:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1080, 'mrbird', '删除PersonnelParameters', 14, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"17\"', '192.168.0.80', '2021-09-04 11:36:04', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1081, 'mrbird', '新增PersonnelParameters', 15, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=18, parameterCategory=1, parameterValue=212, parameterName=2121, parameterSort=2121, parameterRemarks=, parameterState=1, createTime=Sat Sep 04 15:05:27 CST 2021)\"', '192.168.0.80', '2021-09-04 15:05:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1082, 'mrbird', '新增PersonnelParameters', 17, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=19, parameterCategory=1, parameterValue=12, parameterName=212, parameterSort=121, parameterRemarks=21, parameterState=1, createTime=Sat Sep 04 15:05:36 CST 2021)\"', '192.168.0.80', '2021-09-04 15:05:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1083, 'mrbird', '新增PersonnelParameters', 15, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=20, parameterCategory=1, parameterValue=12, parameterName=12, parameterSort=12, parameterRemarks=212, parameterState=1, createTime=Sat Sep 04 15:05:41 CST 2021)\"', '192.168.0.80', '2021-09-04 15:05:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1084, 'mrbird', '删除PersonnelParameters', 16, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"18,19\"', '192.168.0.80', '2021-09-04 15:05:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1085, 'mrbird', '删除PersonnelParameters', 3, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"16,20\"', '192.168.0.80', '2021-09-04 15:06:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1086, 'mrbird', '新增PersonnelParameters', 13, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=21, parameterCategory=1, parameterValue=212, parameterName=212, parameterSort=212, parameterRemarks=21, parameterState=1, createTime=Sat Sep 04 15:06:49 CST 2021)\"', '192.168.0.80', '2021-09-04 15:06:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1087, 'mrbird', '删除PersonnelParameters', 11, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"21\"', '192.168.0.80', '2021-09-04 15:06:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1088, 'mrbird', '新增菜单/按钮', 18, 'com.erp.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=193, parentId=180, menuName=修改, url=null, perms=personnelParameters:update, icon=null, type=1, orderNum=null, createTime=Sat Sep 04 16:10:30 CST 2021, modifyTime=null)\"', '192.168.0.80', '2021-09-04 16:10:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1089, 'mrbird', '修改角色', 86, 'com.erp.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Sat Sep 04 16:11:07 CST 2021, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,127,128,129,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169,178,179,180,191,192,193,182,183,184,185,186,187,188,189,190)\"', '192.168.0.80', '2021-09-04 16:11:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1090, 'mrbird', '修改PersonnelParameters', 22, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=7, parameterCategory=2, parameterValue=车间主任2, parameterName=车间主任1, parameterSort=2, parameterRemarks=, parameterState=2, createTime=Sat Sep 04 16:13:37 CST 2021)\"', '192.168.0.80', '2021-09-04 16:13:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1091, 'mrbird', '修改PersonnelParameters', 44, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=7, parameterCategory=1, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=11, parameterState=1, createTime=Sat Sep 04 16:16:24 CST 2021)\"', '192.168.0.80', '2021-09-04 16:16:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1092, 'mrbird', '修改PersonnelParameters', 14, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=7, parameterCategory=3, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=11, parameterState=1, createTime=Sat Sep 04 16:23:16 CST 2021)\"', '192.168.0.80', '2021-09-04 16:23:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1093, 'mrbird', '修改PersonnelParameters', 19, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=7, parameterCategory=1, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=11, parameterState=1, createTime=Sat Sep 04 16:23:21 CST 2021)\"', '192.168.0.80', '2021-09-04 16:23:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1094, 'mrbird', '新增PersonnelParameters', 19, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=22, parameterCategory=3, parameterValue=212, parameterName=21, parameterSort=212, parameterRemarks=1, parameterState=2, createTime=Sat Sep 04 16:38:44 CST 2021)\"', '192.168.0.80', '2021-09-04 16:38:45', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1095, 'mrbird', '修改PersonnelParameters', 32, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=22, parameterCategory=3, parameterValue=212111111111111, parameterName=21, parameterSort=212, parameterRemarks=1, parameterState=2, createTime=Sat Sep 04 16:38:52 CST 2021)\"', '192.168.0.80', '2021-09-04 16:38:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1096, 'mrbird', '修改PersonnelParameters', 20, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=22, parameterCategory=3, parameterValue=21211, parameterName=21, parameterSort=212, parameterRemarks=1, parameterState=2, createTime=Sat Sep 04 16:48:09 CST 2021)\"', '192.168.0.80', '2021-09-04 16:48:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1097, 'mrbird', '修改PersonnelParameters', 11, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=22, parameterCategory=3, parameterValue=21211, parameterName=21, parameterSort=212, parameterRemarks=1, parameterState=2, createTime=Sat Sep 04 16:48:30 CST 2021)\"', '192.168.0.80', '2021-09-04 16:48:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1098, 'mrbird', '修改PersonnelParameters', 17, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=7, parameterCategory=1, parameterValue=车间主任1, parameterName=车间主任, parameterSort=1, parameterRemarks=11, parameterState=1, createTime=Sat Sep 04 17:19:19 CST 2021)\"', '192.168.0.80', '2021-09-04 17:19:20', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1099, 'mrbird', '修改PersonnelParameters', 21, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=7, parameterCategory=1, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=11, parameterState=1, createTime=Sat Sep 04 17:19:26 CST 2021)\"', '192.168.0.80', '2021-09-04 17:19:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1100, 'mrbird', '修改菜单/按钮', 38, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=191, parentId=180, menuName=新增, url=null, perms=, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Sat Sep 04 17:57:11 CST 2021)\"', '192.168.0.80', '2021-09-04 17:57:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1101, 'mrbird', '修改菜单/按钮', 15, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=191, parentId=180, menuName=新增, url=null, perms=personnelParameters:add, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Sat Sep 04 17:58:10 CST 2021)\"', '192.168.0.80', '2021-09-04 17:58:10', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1102, 'mrbird', '修改菜单/按钮', 19, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=191, parentId=180, menuName=新增, url=null, perms=, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Sat Sep 04 18:03:52 CST 2021)\"', '192.168.0.80', '2021-09-04 18:03:52', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1103, 'mrbird', '修改菜单/按钮', 23, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=191, parentId=180, menuName=新增, url=null, perms=personnelParameters:add, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Sat Sep 04 18:04:33 CST 2021)\"', '192.168.0.80', '2021-09-04 18:04:33', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1104, 'mrbird', '新增PersonnelParameters', 14, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=23, parameterCategory=1, parameterValue=212, parameterName=212, parameterSort=12, parameterRemarks=21, parameterState=1, createTime=Sat Sep 04 18:06:18 CST 2021)\"', '192.168.0.80', '2021-09-04 18:06:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1105, 'mrbird', '新增PersonnelParameters', 19, 'com.erp.personnel.controller.PersonnelParametersController.addPersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=24, parameterCategory=7, parameterValue=212, parameterName=21, parameterSort=21, parameterRemarks=21, parameterState=1, createTime=Sat Sep 04 18:06:44 CST 2021)\"', '192.168.0.80', '2021-09-04 18:06:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1106, 'mrbird', '删除PersonnelParameters', 24, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"24\"', '192.168.0.80', '2021-09-04 18:06:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1107, 'mrbird', '修改菜单/按钮', 21, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=191, parentId=180, menuName=新增, url=null, perms=, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Sat Sep 04 18:09:41 CST 2021)\"', '192.168.0.80', '2021-09-04 18:09:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1108, 'mrbird', '修改菜单/按钮', 17, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=191, parentId=180, menuName=新增, url=null, perms=personnelParameters:add, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Sat Sep 04 18:10:01 CST 2021)\"', '192.168.0.80', '2021-09-04 18:10:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1109, 'mrbird', '删除PersonnelParameters', 10, 'com.erp.personnel.controller.PersonnelParametersController.deletePersonnelParameters()', ' ids: \"22,23\"', '192.168.0.80', '2021-09-04 18:16:13', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1110, 'mrbird', '修改菜单/按钮', 16, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=192, parentId=180, menuName=删除, url=null, perms=, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Sat Sep 04 18:16:38 CST 2021)\"', '192.168.0.80', '2021-09-04 18:16:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1111, 'mrbird', '修改菜单/按钮', 31, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=192, parentId=180, menuName=删除, url=null, perms=personnelParameters:delete, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Sat Sep 04 18:17:01 CST 2021)\"', '192.168.0.80', '2021-09-04 18:17:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1112, 'mrbird', '修改PersonnelParameters', 29, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=7, parameterCategory=2, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=11, parameterState=1, createTime=Mon Sep 06 10:18:56 CST 2021)\"', '192.168.0.80', '2021-09-06 10:18:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1113, 'mrbird', '修改PersonnelParameters', 18, 'com.erp.personnel.controller.PersonnelParametersController.updatePersonnelParameters()', ' personnelParameters: \"PersonnelParameters(id=7, parameterCategory=1, parameterValue=车间主任, parameterName=车间主任, parameterSort=1, parameterRemarks=11, parameterState=1, createTime=Mon Sep 06 10:19:02 CST 2021)\"', '192.168.0.80', '2021-09-06 10:19:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1114, 'mrbird', '修改菜单/按钮', 41, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=10, parentId=2, menuName=系统日志, url=/monitor/log, perms=, icon=, type=0, orderNum=2, createTime=null, modifyTime=Mon Sep 06 10:21:06 CST 2021)\"', '192.168.0.80', '2021-09-06 10:21:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1115, 'mrbird', '修改菜单/按钮', 10, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=10, parentId=2, menuName=系统日志, url=/monitor/log, perms=log:view, icon=, type=0, orderNum=2, createTime=null, modifyTime=Mon Sep 06 10:23:57 CST 2021)\"', '192.168.0.80', '2021-09-06 10:23:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1116, 'mrbird', '删除菜单/按钮', 30, 'com.erp.system.controller.MenuController.deleteMenus()', ' menuIds: \"175\"', '192.168.0.80', '2021-09-06 10:24:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1117, 'mrbird', '删除菜单/按钮', 56, 'com.erp.system.controller.MenuController.deleteMenus()', ' menuIds: \"115\"', '192.168.0.80', '2021-09-06 10:25:18', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1118, 'mrbird', '修改菜单/按钮', 29, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=179, parentId=0, menuName=人事管理, url=, perms=, icon=layui-icon-user, type=0, orderNum=5, createTime=null, modifyTime=Mon Sep 06 10:25:39 CST 2021)\"', '192.168.0.80', '2021-09-06 10:25:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1119, 'mrbird', '修改菜单/按钮', 32, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=137, parentId=0, menuName=代码生成, url=, perms=, icon=layui-icon-verticalright, type=0, orderNum=6, createTime=null, modifyTime=Mon Sep 06 10:26:04 CST 2021)\"', '192.168.0.80', '2021-09-06 10:26:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1120, 'mrbird', '修改菜单/按钮', 16, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=1, parentId=0, menuName=系统管理, url=, perms=, icon=layui-icon-setting, type=0, orderNum=7, createTime=null, modifyTime=Mon Sep 06 10:26:24 CST 2021)\"', '192.168.0.80', '2021-09-06 10:26:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1121, 'mrbird', '修改菜单/按钮', 15, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=179, parentId=0, menuName=人事管理, url=, perms=, icon=layui-icon-user, type=0, orderNum=1, createTime=null, modifyTime=Mon Sep 06 10:26:36 CST 2021)\"', '192.168.0.80', '2021-09-06 10:26:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1122, 'mrbird', '修改菜单/按钮', 17, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=2, parentId=0, menuName=系统监控, url=, perms=, icon=layui-icon-alert, type=0, orderNum=8, createTime=null, modifyTime=Mon Sep 06 10:27:08 CST 2021)\"', '192.168.0.80', '2021-09-06 10:27:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1123, 'mrbird', '修改菜单/按钮', 22, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=101, parentId=0, menuName=任务调度, url=, perms=, icon=layui-icon-time-circle, type=0, orderNum=9, createTime=null, modifyTime=Mon Sep 06 10:27:16 CST 2021)\"', '192.168.0.80', '2021-09-06 10:27:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1124, 'mrbird', '修改菜单/按钮', 8, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=137, parentId=0, menuName=代码生成, url=, perms=, icon=layui-icon-verticalright, type=0, orderNum=10, createTime=null, modifyTime=Mon Sep 06 10:27:22 CST 2021)\"', '192.168.0.80', '2021-09-06 10:27:23', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1125, 'mrbird', '修改菜单/按钮', 23, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=1, parentId=0, menuName=系统管理, url=, perms=, icon=layui-icon-setting, type=0, orderNum=2, createTime=null, modifyTime=Mon Sep 06 10:27:37 CST 2021)\"', '192.168.0.80', '2021-09-06 10:27:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1126, 'mrbird', '修改菜单/按钮', 16, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=2, parentId=0, menuName=系统监控, url=, perms=, icon=layui-icon-alert, type=0, orderNum=3, createTime=null, modifyTime=Mon Sep 06 10:27:49 CST 2021)\"', '192.168.0.80', '2021-09-06 10:27:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1127, 'mrbird', '修改菜单/按钮', 17, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=101, parentId=0, menuName=任务调度, url=, perms=, icon=layui-icon-time-circle, type=0, orderNum=4, createTime=null, modifyTime=Mon Sep 06 10:27:54 CST 2021)\"', '192.168.0.80', '2021-09-06 10:27:55', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1128, 'mrbird', '修改菜单/按钮', 8, 'com.erp.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=137, parentId=0, menuName=代码生成, url=, perms=, icon=layui-icon-verticalright, type=0, orderNum=5, createTime=null, modifyTime=Mon Sep 06 10:27:58 CST 2021)\"', '192.168.0.80', '2021-09-06 10:27:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1129, 'mrbird', '删除用户', 29, 'com.erp.system.controller.UserController.deleteUsers()', ' userIds: \"5\"', '192.168.0.80', '2021-09-06 10:34:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1130, 'mrbird', '删除用户', 22, 'com.erp.system.controller.UserController.deleteUsers()', ' userIds: \"9\"', '192.168.0.80', '2021-09-06 10:34:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1131, 'mrbird', '删除用户', 14, 'com.erp.system.controller.UserController.deleteUsers()', ' userIds: \"8\"', '192.168.0.80', '2021-09-06 10:34:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1132, 'mrbird', '删除用户', 25, 'com.erp.system.controller.UserController.deleteUsers()', ' userIds: \"7\"', '192.168.0.80', '2021-09-06 10:35:13', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1133, 'mrbird', '删除角色', 31, 'com.erp.system.controller.RoleController.deleteRoles()', ' roleIds: \"79\"', '192.168.0.80', '2021-09-06 10:36:23', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (1134, 'mrbird', '删除菜单/按钮', 27, 'com.erp.system.controller.MenuController.deleteMenus()', ' menuIds: \"101\"', '192.168.0.80', '2021-09-06 10:37:15', '内网IP|0|0|内网IP|内网IP');

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `LOGIN_TIME` datetime(0) NOT NULL COMMENT '登录时间',
  `LOCATION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `IP` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `SYSTEM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `BROWSER` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `t_login_log_login_time`(`LOGIN_TIME`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 146 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES (70, 'mrbird', '2021-09-02 10:26:13', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (71, 'mrbird', '2021-09-02 10:26:34', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (72, 'mrbird', '2021-09-02 11:37:40', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (73, 'mrbird', '2021-09-02 11:42:40', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (74, 'mrbird', '2021-09-02 11:44:33', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (75, 'mrbird', '2021-09-02 11:49:09', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (76, 'mrbird', '2021-09-02 11:50:36', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (77, 'mrbird', '2021-09-02 12:04:25', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (78, 'admin', '2021-09-02 13:47:02', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (79, 'mrbird', '2021-09-02 13:51:26', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (80, 'mrbird', '2021-09-02 14:15:24', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (81, 'mrbird', '2021-09-02 14:21:24', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (82, 'mrbird', '2021-09-02 15:00:58', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (83, 'mrbird', '2021-09-02 15:10:22', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (84, 'mrbird', '2021-09-02 15:14:12', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (85, 'mrbird', '2021-09-02 15:19:55', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (86, 'mrbird', '2021-09-02 15:41:06', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (87, 'mrbird', '2021-09-02 15:41:54', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (88, 'mrbird', '2021-09-02 15:42:37', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (89, 'mrbird', '2021-09-02 15:44:34', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (90, 'mrbird', '2021-09-02 15:53:13', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (91, 'mrbird', '2021-09-02 15:54:02', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (92, 'mrbird', '2021-09-02 16:26:49', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (93, 'mrbird', '2021-09-02 16:31:54', '内网IP|0|0|内网IP|内网IP', '192.168.112.57', 'Linux', 'Chrome 87');
INSERT INTO `t_login_log` VALUES (94, 'mrbird', '2021-09-03 09:55:56', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (95, 'mrbird', '2021-09-03 10:01:50', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (96, 'mrbird', '2021-09-03 10:15:58', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (97, 'mrbird', '2021-09-03 10:25:55', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (98, 'mrbird', '2021-09-03 10:33:10', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (99, 'mrbird', '2021-09-03 11:27:51', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (100, 'mrbird', '2021-09-03 11:34:02', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (101, 'mrbird', '2021-09-03 14:01:41', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (102, 'mrbird', '2021-09-03 14:11:05', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (103, 'mrbird', '2021-09-03 14:37:46', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (104, 'mrbird', '2021-09-03 14:56:29', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (105, 'mrbird', '2021-09-03 15:24:28', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (106, 'mrbird', '2021-09-03 15:39:43', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (107, 'mrbird', '2021-09-03 16:06:51', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (108, 'mrbird', '2021-09-03 16:17:42', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (109, 'mrbird', '2021-09-03 16:20:50', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (110, 'mrbird', '2021-09-03 16:35:16', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (111, 'mrbird', '2021-09-03 16:38:30', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (112, 'mrbird', '2021-09-03 16:50:10', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (113, 'mrbird', '2021-09-03 16:53:33', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (114, 'mrbird', '2021-09-03 17:18:56', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (115, 'mrbird', '2021-09-03 17:26:11', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (116, 'mrbird', '2021-09-03 17:28:28', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (117, 'mrbird', '2021-09-03 17:50:58', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (118, 'mrbird', '2021-09-03 17:52:22', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (119, 'mrbird', '2021-09-03 18:09:36', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (120, 'mrbird', '2021-09-03 18:17:17', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (121, 'mrbird', '2021-09-03 18:21:02', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (122, 'mrbird', '2021-09-04 08:41:06', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (123, 'mrbird', '2021-09-04 08:51:36', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (124, 'mrbird', '2021-09-04 09:02:52', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (125, 'mrbird', '2021-09-04 09:16:32', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (126, 'mrbird', '2021-09-04 09:31:17', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (127, 'mrbird', '2021-09-04 09:43:22', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (128, 'mrbird', '2021-09-04 11:34:23', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (129, 'mrbird', '2021-09-04 13:58:26', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (130, 'mrbird', '2021-09-04 14:01:33', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (131, 'mrbird', '2021-09-04 14:38:16', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (132, 'mrbird', '2021-09-04 14:56:35', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (133, 'mrbird', '2021-09-04 15:03:00', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (134, 'mrbird', '2021-09-04 15:20:42', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (135, 'mrbird', '2021-09-04 16:09:45', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (136, 'mrbird', '2021-09-04 16:38:19', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (137, 'mrbird', '2021-09-06 08:14:31', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (138, 'mrbird', '2021-09-06 08:21:03', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (139, 'mrbird', '2021-09-06 10:04:09', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (140, 'mrbird', '2021-09-06 10:18:34', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (141, 'scott', '2021-09-06 10:31:21', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (142, 'micaela', '2021-09-06 10:32:17', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (143, 'micaela', '2021-09-06 10:32:49', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (144, 'Jana', '2021-09-06 10:33:39', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');
INSERT INTO `t_login_log` VALUES (145, 'mrbird', '2021-09-06 10:33:59', '内网IP|0|0|内网IP|内网IP', '192.168.0.80', 'Windows ', 'Chrome 92');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
  `URL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `PERMS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限标识',
  `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0菜单 1按钮',
  `ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`MENU_ID`) USING BTREE,
  INDEX `t_menu_parent_id`(`PARENT_ID`) USING BTREE,
  INDEX `t_menu_menu_id`(`MENU_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 194 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 0, '系统管理', '', '', 'layui-icon-setting', '0', 2, '2017-12-27 16:39:07', '2021-09-06 10:27:37');
INSERT INTO `t_menu` VALUES (2, 0, '系统监控', '', '', 'layui-icon-alert', '0', 3, '2017-12-27 16:45:51', '2021-09-06 10:27:50');
INSERT INTO `t_menu` VALUES (3, 1, '用户管理', '/system/user', 'user:view', '', '0', 1, '2017-12-27 16:47:13', '2019-12-04 16:46:50');
INSERT INTO `t_menu` VALUES (4, 1, '角色管理', '/system/role', 'role:view', '', '0', 2, '2017-12-27 16:48:09', '2019-06-13 08:57:19');
INSERT INTO `t_menu` VALUES (5, 1, '菜单管理', '/system/menu', 'menu:view', '', '0', 3, '2017-12-27 16:48:57', '2019-06-13 08:57:34');
INSERT INTO `t_menu` VALUES (6, 1, '部门管理', '/system/dept', 'dept:view', '', '0', 4, '2017-12-27 16:57:33', '2019-06-14 19:56:00');
INSERT INTO `t_menu` VALUES (8, 2, '在线用户', '/monitor/online', 'online:view', '', '0', 1, '2017-12-27 16:59:33', '2019-06-13 14:30:31');
INSERT INTO `t_menu` VALUES (10, 2, '系统日志', '/monitor/log', 'log:view', '', '0', 2, '2017-12-27 17:00:50', '2021-09-06 10:23:58');
INSERT INTO `t_menu` VALUES (11, 3, '新增用户', NULL, 'user:add', NULL, '1', NULL, '2017-12-27 17:02:58', NULL);
INSERT INTO `t_menu` VALUES (12, 3, '修改用户', NULL, 'user:update', NULL, '1', NULL, '2017-12-27 17:04:07', NULL);
INSERT INTO `t_menu` VALUES (13, 3, '删除用户', NULL, 'user:delete', NULL, '1', NULL, '2017-12-27 17:04:58', NULL);
INSERT INTO `t_menu` VALUES (14, 4, '新增角色', NULL, 'role:add', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (15, 4, '修改角色', NULL, 'role:update', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (16, 4, '删除角色', NULL, 'role:delete', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (17, 5, '新增菜单', NULL, 'menu:add', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (18, 5, '修改菜单', NULL, 'menu:update', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (19, 5, '删除菜单', NULL, 'menu:delete', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (20, 6, '新增部门', NULL, 'dept:add', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (21, 6, '修改部门', NULL, 'dept:update', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (22, 6, '删除部门', NULL, 'dept:delete', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (23, 8, '踢出用户', NULL, 'user:kickout', NULL, '1', NULL, '2017-12-27 17:11:13', NULL);
INSERT INTO `t_menu` VALUES (24, 10, '删除日志', NULL, 'log:delete', NULL, '1', NULL, '2017-12-27 17:11:45', '2019-06-06 05:56:40');
INSERT INTO `t_menu` VALUES (127, 2, '请求追踪', '/monitor/httptrace', 'httptrace:view', '', '0', 6, '2019-05-27 19:06:38', '2019-06-13 14:36:43');
INSERT INTO `t_menu` VALUES (128, 2, '系统信息', NULL, NULL, NULL, '0', 7, '2019-05-27 19:08:23', NULL);
INSERT INTO `t_menu` VALUES (129, 128, 'JVM信息', '/monitor/jvm', 'jvm:view', '', '0', 1, '2019-05-27 19:08:50', '2019-06-13 14:36:51');
INSERT INTO `t_menu` VALUES (131, 128, '服务器信息', '/monitor/server', 'server:view', '', '0', 3, '2019-05-27 19:10:07', '2019-06-13 14:37:04');
INSERT INTO `t_menu` VALUES (136, 2, '登录日志', '/monitor/loginlog', 'loginlog:view', '', '0', 3, '2019-05-29 15:56:15', '2019-06-13 14:35:56');
INSERT INTO `t_menu` VALUES (137, 0, '代码生成', '', '', 'layui-icon-verticalright', '0', 5, '2019-06-03 15:35:58', '2021-09-06 10:27:58');
INSERT INTO `t_menu` VALUES (138, 137, '生成配置', '/generator/configure', 'generator:configure:view', NULL, '0', 1, '2019-06-03 15:38:36', NULL);
INSERT INTO `t_menu` VALUES (139, 137, '代码生成', '/generator/generator', 'generator:view', '', '0', 2, '2019-06-03 15:39:15', '2019-06-13 14:31:38');
INSERT INTO `t_menu` VALUES (160, 3, '密码重置', NULL, 'user:password:reset', NULL, '1', NULL, '2019-06-13 08:40:13', NULL);
INSERT INTO `t_menu` VALUES (161, 3, '导出Excel', NULL, 'user:export', NULL, '1', NULL, '2019-06-13 08:40:34', NULL);
INSERT INTO `t_menu` VALUES (162, 4, '导出Excel', NULL, 'role:export', NULL, '1', NULL, '2019-06-13 14:29:00', '2019-06-13 14:29:11');
INSERT INTO `t_menu` VALUES (163, 5, '导出Excel', NULL, 'menu:export', NULL, '1', NULL, '2019-06-13 14:29:32', NULL);
INSERT INTO `t_menu` VALUES (164, 6, '导出Excel', NULL, 'dept:export', NULL, '1', NULL, '2019-06-13 14:29:59', NULL);
INSERT INTO `t_menu` VALUES (165, 138, '修改配置', NULL, 'generator:configure:update', NULL, '1', NULL, '2019-06-13 14:32:09', '2019-06-13 14:32:20');
INSERT INTO `t_menu` VALUES (166, 139, '生成代码', NULL, 'generator:generate', NULL, '1', NULL, '2019-06-13 14:32:51', NULL);
INSERT INTO `t_menu` VALUES (170, 10, '导出Excel', NULL, 'log:export', NULL, '1', NULL, '2019-06-13 14:34:55', NULL);
INSERT INTO `t_menu` VALUES (171, 136, '删除日志', NULL, 'loginlog:delete', NULL, '1', NULL, '2019-06-13 14:35:27', '2019-06-13 14:36:08');
INSERT INTO `t_menu` VALUES (172, 136, '导出Excel', NULL, 'loginlog:export', NULL, '1', NULL, '2019-06-13 14:36:26', NULL);
INSERT INTO `t_menu` VALUES (179, 0, '人事管理', '', '', 'layui-icon-user', '0', 1, '2021-09-02 14:49:42', '2021-09-06 10:26:36');
INSERT INTO `t_menu` VALUES (180, 179, '人事参数', '/personnel/personnelParameters', 'personnelParameters:view', 'layui-icon-chrome-fill', '0', 1, '2021-09-02 12:11:01', '2021-09-03 17:51:18');
INSERT INTO `t_menu` VALUES (182, 179, '员工档案', '', '', 'layui-icon-reconciliation', '0', 2, '2021-09-02 14:30:30', NULL);
INSERT INTO `t_menu` VALUES (183, 179, '员工请假', '', '', 'layui-icon-tags-fill', '0', 3, '2021-09-02 14:34:37', NULL);
INSERT INTO `t_menu` VALUES (184, 179, '员工领取记录', '', '', 'layui-icon-gift', '0', 4, '2021-09-02 14:35:21', NULL);
INSERT INTO `t_menu` VALUES (185, 179, '员工合同', '', '', 'layui-icon-solution', '0', 5, '2021-09-02 14:36:04', NULL);
INSERT INTO `t_menu` VALUES (186, 179, '宿舍管理', '', '', 'layui-icon-home', '0', 6, '2021-09-02 12:05:16', '2021-09-02 14:46:36');
INSERT INTO `t_menu` VALUES (187, 179, '员工异动记录', '', '', 'layui-icon-team', '0', 7, '2021-09-02 14:55:50', NULL);
INSERT INTO `t_menu` VALUES (188, 187, '调岗记录', '', '', 'layui-icon-container', '0', 1, '2021-09-02 14:57:06', NULL);
INSERT INTO `t_menu` VALUES (189, 187, '调薪记录', '', '', 'layui-icon-database', '0', 2, '2021-09-02 14:57:42', NULL);
INSERT INTO `t_menu` VALUES (190, 187, '奖罚记录', '', '', 'layui-icon-sever', '0', 3, '2021-09-02 14:58:12', NULL);
INSERT INTO `t_menu` VALUES (191, 180, '新增', NULL, 'personnelParameters:add', NULL, '1', NULL, '2021-09-03 17:20:48', '2021-09-04 18:10:01');
INSERT INTO `t_menu` VALUES (192, 180, '删除', NULL, 'personnelParameters:delete', NULL, '1', NULL, '2021-09-04 09:04:11', '2021-09-04 18:17:01');
INSERT INTO `t_menu` VALUES (193, 180, '修改', NULL, 'personnelParameters:update', NULL, '1', NULL, '2021-09-04 16:10:31', NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '系统管理员', '系统管理员，拥有所有操作权限 ^_^', '2019-06-14 16:23:11', '2021-09-04 16:11:07');
INSERT INTO `t_role` VALUES (2, '注册账户', '注册账户，拥有查看，新增权限（新增用户除外）和导出Excel权限', '2019-06-14 16:00:15', '2019-08-18 17:36:02');
INSERT INTO `t_role` VALUES (77, 'Redis监控员', '负责Redis模块', '2019-06-14 20:49:22', NULL);
INSERT INTO `t_role` VALUES (78, '系统监控员', '负责整个系统监控模块', '2019-06-14 20:50:07', NULL);
INSERT INTO `t_role` VALUES (80, '开发人员', '拥有代码生成模块的权限', '2019-06-14 20:51:26', NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
  `MENU_ID` bigint(20) NOT NULL COMMENT '菜单/按钮ID',
  INDEX `t_role_menu_menu_id`(`MENU_ID`) USING BTREE,
  INDEX `t_role_menu_role_id`(`ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (77, 2);
INSERT INTO `t_role_menu` VALUES (78, 2);
INSERT INTO `t_role_menu` VALUES (78, 8);
INSERT INTO `t_role_menu` VALUES (78, 23);
INSERT INTO `t_role_menu` VALUES (78, 10);
INSERT INTO `t_role_menu` VALUES (78, 24);
INSERT INTO `t_role_menu` VALUES (78, 170);
INSERT INTO `t_role_menu` VALUES (78, 136);
INSERT INTO `t_role_menu` VALUES (78, 171);
INSERT INTO `t_role_menu` VALUES (78, 172);
INSERT INTO `t_role_menu` VALUES (78, 127);
INSERT INTO `t_role_menu` VALUES (78, 128);
INSERT INTO `t_role_menu` VALUES (78, 129);
INSERT INTO `t_role_menu` VALUES (78, 131);
INSERT INTO `t_role_menu` VALUES (80, 137);
INSERT INTO `t_role_menu` VALUES (80, 138);
INSERT INTO `t_role_menu` VALUES (80, 165);
INSERT INTO `t_role_menu` VALUES (80, 139);
INSERT INTO `t_role_menu` VALUES (80, 166);
INSERT INTO `t_role_menu` VALUES (2, 1);
INSERT INTO `t_role_menu` VALUES (2, 3);
INSERT INTO `t_role_menu` VALUES (2, 161);
INSERT INTO `t_role_menu` VALUES (2, 4);
INSERT INTO `t_role_menu` VALUES (2, 14);
INSERT INTO `t_role_menu` VALUES (2, 162);
INSERT INTO `t_role_menu` VALUES (2, 5);
INSERT INTO `t_role_menu` VALUES (2, 17);
INSERT INTO `t_role_menu` VALUES (2, 163);
INSERT INTO `t_role_menu` VALUES (2, 6);
INSERT INTO `t_role_menu` VALUES (2, 20);
INSERT INTO `t_role_menu` VALUES (2, 164);
INSERT INTO `t_role_menu` VALUES (2, 2);
INSERT INTO `t_role_menu` VALUES (2, 8);
INSERT INTO `t_role_menu` VALUES (2, 10);
INSERT INTO `t_role_menu` VALUES (2, 170);
INSERT INTO `t_role_menu` VALUES (2, 136);
INSERT INTO `t_role_menu` VALUES (2, 172);
INSERT INTO `t_role_menu` VALUES (2, 127);
INSERT INTO `t_role_menu` VALUES (2, 128);
INSERT INTO `t_role_menu` VALUES (2, 129);
INSERT INTO `t_role_menu` VALUES (2, 131);
INSERT INTO `t_role_menu` VALUES (2, 137);
INSERT INTO `t_role_menu` VALUES (2, 138);
INSERT INTO `t_role_menu` VALUES (2, 139);
INSERT INTO `t_role_menu` VALUES (1, 1);
INSERT INTO `t_role_menu` VALUES (1, 3);
INSERT INTO `t_role_menu` VALUES (1, 11);
INSERT INTO `t_role_menu` VALUES (1, 12);
INSERT INTO `t_role_menu` VALUES (1, 13);
INSERT INTO `t_role_menu` VALUES (1, 160);
INSERT INTO `t_role_menu` VALUES (1, 161);
INSERT INTO `t_role_menu` VALUES (1, 4);
INSERT INTO `t_role_menu` VALUES (1, 14);
INSERT INTO `t_role_menu` VALUES (1, 15);
INSERT INTO `t_role_menu` VALUES (1, 16);
INSERT INTO `t_role_menu` VALUES (1, 162);
INSERT INTO `t_role_menu` VALUES (1, 5);
INSERT INTO `t_role_menu` VALUES (1, 17);
INSERT INTO `t_role_menu` VALUES (1, 18);
INSERT INTO `t_role_menu` VALUES (1, 19);
INSERT INTO `t_role_menu` VALUES (1, 163);
INSERT INTO `t_role_menu` VALUES (1, 6);
INSERT INTO `t_role_menu` VALUES (1, 20);
INSERT INTO `t_role_menu` VALUES (1, 21);
INSERT INTO `t_role_menu` VALUES (1, 22);
INSERT INTO `t_role_menu` VALUES (1, 164);
INSERT INTO `t_role_menu` VALUES (1, 2);
INSERT INTO `t_role_menu` VALUES (1, 8);
INSERT INTO `t_role_menu` VALUES (1, 23);
INSERT INTO `t_role_menu` VALUES (1, 10);
INSERT INTO `t_role_menu` VALUES (1, 24);
INSERT INTO `t_role_menu` VALUES (1, 170);
INSERT INTO `t_role_menu` VALUES (1, 136);
INSERT INTO `t_role_menu` VALUES (1, 171);
INSERT INTO `t_role_menu` VALUES (1, 172);
INSERT INTO `t_role_menu` VALUES (1, 127);
INSERT INTO `t_role_menu` VALUES (1, 128);
INSERT INTO `t_role_menu` VALUES (1, 129);
INSERT INTO `t_role_menu` VALUES (1, 131);
INSERT INTO `t_role_menu` VALUES (1, 137);
INSERT INTO `t_role_menu` VALUES (1, 138);
INSERT INTO `t_role_menu` VALUES (1, 165);
INSERT INTO `t_role_menu` VALUES (1, 139);
INSERT INTO `t_role_menu` VALUES (1, 166);
INSERT INTO `t_role_menu` VALUES (1, 179);
INSERT INTO `t_role_menu` VALUES (1, 180);
INSERT INTO `t_role_menu` VALUES (1, 191);
INSERT INTO `t_role_menu` VALUES (1, 192);
INSERT INTO `t_role_menu` VALUES (1, 193);
INSERT INTO `t_role_menu` VALUES (1, 182);
INSERT INTO `t_role_menu` VALUES (1, 183);
INSERT INTO `t_role_menu` VALUES (1, 184);
INSERT INTO `t_role_menu` VALUES (1, 185);
INSERT INTO `t_role_menu` VALUES (1, 186);
INSERT INTO `t_role_menu` VALUES (1, 187);
INSERT INTO `t_role_menu` VALUES (1, 188);
INSERT INTO `t_role_menu` VALUES (1, 189);
INSERT INTO `t_role_menu` VALUES (1, 190);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `DEPT_ID` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '最近访问时间',
  `SSEX` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `IS_TAB` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否开启tab，0关闭 1开启',
  `THEME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `AVATAR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`USER_ID`) USING BTREE,
  INDEX `t_user_username`(`USERNAME`) USING BTREE,
  INDEX `t_user_mobile`(`MOBILE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'mrbird', 'cb62ad1497597283961545d608f80241', 1, 'mrbird@qq.com', '17788888888', '1', '2019-06-14 20:39:22', '2021-09-06 10:41:23', '2021-09-06 10:34:20', '0', '1', 'black', 'cnrhVkzwxjPwAaCfPbdc.png', '111');
INSERT INTO `t_user` VALUES (2, 'Scott', '1d685729d113cfd03872f154939bee1c', 10, 'scott@gmail.com', '17722222222', '1', '2019-06-14 20:55:53', '2019-06-14 21:05:43', '2021-09-06 10:31:22', '0', '1', 'black', 'gaOngJwsRYRaVAuXXcmB.png', '我是scott。');
INSERT INTO `t_user` VALUES (3, 'Reina', '1461afff857c02afbfb768aa3771503d', 4, 'Reina@hotmail.com', '17711111111', '0', '2019-06-14 21:07:38', '2019-06-14 21:09:06', '2019-06-14 21:08:26', '1', '1', 'black', '5997fedcc7bd4cffbd350b40d1b5b987.jpg', '由于公款私用，已被封禁。');
INSERT INTO `t_user` VALUES (4, 'Micaela', '9f2daa2c7bed6870fcbb5b9a51d6300e', 10, 'Micaela@163.com', '17733333333', '1', '2019-06-14 21:10:13', '2019-06-14 21:11:26', '2021-09-06 10:32:49', '0', '0', 'white', '20180414165909.jpg', '我叫米克拉');
INSERT INTO `t_user` VALUES (6, 'Georgie', 'dffc683378cdaa015a0ee9554c532225', 3, 'Georgie@qq.com', '17766666666', '0', '2019-06-14 21:15:09', '2019-06-14 21:16:25', '2019-06-14 21:16:11', '2', '0', 'black', 'BiazfanxmamNRoxxVxka.png', '生产执行rm -rf *，账号封禁T T');

-- ----------------------------
-- Table structure for t_user_data_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_user_data_permission`;
CREATE TABLE `t_user_data_permission`  (
  `USER_ID` bigint(20) NOT NULL,
  `DEPT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`, `DEPT_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户数据权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_data_permission
-- ----------------------------
INSERT INTO `t_user_data_permission` VALUES (1, 1);
INSERT INTO `t_user_data_permission` VALUES (1, 2);
INSERT INTO `t_user_data_permission` VALUES (1, 3);
INSERT INTO `t_user_data_permission` VALUES (1, 4);
INSERT INTO `t_user_data_permission` VALUES (1, 5);
INSERT INTO `t_user_data_permission` VALUES (1, 6);
INSERT INTO `t_user_data_permission` VALUES (2, 1);
INSERT INTO `t_user_data_permission` VALUES (2, 2);
INSERT INTO `t_user_data_permission` VALUES (3, 4);
INSERT INTO `t_user_data_permission` VALUES (4, 5);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
  INDEX `t_user_role_user_id`(`USER_ID`) USING BTREE,
  INDEX `t_user_role_role_id`(`ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1);
INSERT INTO `t_user_role` VALUES (2, 2);
INSERT INTO `t_user_role` VALUES (3, 77);
INSERT INTO `t_user_role` VALUES (4, 78);
INSERT INTO `t_user_role` VALUES (6, 80);

SET FOREIGN_KEY_CHECKS = 1;

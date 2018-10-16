/*==============================================================*/
/* Table: SYS_META_METHOD 系统元方法表                                      				*/
/*==============================================================*/
CREATE TABLE `sys_meta_method` (
  `META_METHOD_ID_` bigint(20) NOT NULL COMMENT '主键ID',
  `TENANT_ID_` tinyint(4) DEFAULT NULL COMMENT '租户ID',
  `CLASS_NAME_` varchar(256) DEFAULT NULL COMMENT '类名(格式为：com.hginfo.xx.xxClass)',
  `METHOD_NAME_` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '方法名(格式为：xxMethod)',
  `ARGS_NAME_` varchar(256) DEFAULT NULL COMMENT '参数类型(格式为：java.lang.String,java.lang.Integer,...)',
  `METHOD_CODE_` varchar(256) DEFAULT NULL COMMENT '方法编码',
  `METHOD_ZH_NAME_` varchar(256) DEFAULT NULL COMMENT '方法中文名称',
  `METHOD_ENG_NAME_` varchar(256) DEFAULT NULL COMMENT '方法英文名称',
  `LOG_LEVEL_` tinyint(4) DEFAULT NULL COMMENT '日志级别(1:DEBUG;2:INFO;)',
  `LOG_TYPE_` tinyint(4) DEFAULT NULL COMMENT '日志类型(1:业务日志;0:操作日志)',
  `LOG_REMARK_CLASS_` varchar(256) DEFAULT NULL COMMENT '指定方法参数中需要存储日志的类(格式为：com.hginfo.xx.xxClass，若未指定，除..外，所有参数都要存储到日志内容中)',
  `DESCR_` text COMMENT '描述',
  `IS_VALID_` tinyint(4) DEFAULT '1' COMMENT '方法是否在用(1:在用;0:不再用 与类中方法不再匹配时不再用)',
  `IS_DELETE_` tinyint(4) DEFAULT '0' COMMENT '删除标识(1:已删除;0:正常)',
  `IS_FINAL_` tinyint(4) DEFAULT '0' COMMENT '是否不可修改(1:不可修改;0:可修改)',
  `CRT_TIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `UPD_TIME_` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '数据最后修改时间',
  `CRT_USER_ID_` bigint(20) DEFAULT NULL COMMENT '数据创建用户编号',
  `UPD_USER_ID_` bigint(20) DEFAULT NULL COMMENT '数据修改用户编号',
  PRIMARY KEY (`META_METHOD_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统元方法表';

-- 系统方法 菜单权限
INSERT INTO `sys_auth` (`AUTH_ID_`, `AUTH_TYPE_`, `AUTH_CODE_`, `AUTH_NAME_`, `AUTH_ENNAME_`, `AUTH_URI_`, `AUTH_ICON_`, `ORDER_ID_`, `DESCR_`, `IS_DELETE_`, `IS_FINAL_`, `CRT_TIME_`, `UPD_TIME_`, `CRT_USERID_`, `UPD_USERID_`, `OPER_ID_`, `FID_`, `FIDS_`, `LEVEL_ID_`, `IS_VISIBLE_`) VALUES ('1000103', '1', 'sys:metaMethod', '系统方法', '', '/sys/sysMetaMethod/showSysMetaMethod', '', '1', '系统方法', '0', '0', now(), now(), '1', '1', NULL, '3', '1/3', '3', '1');
INSERT INTO `sys_auth` (`AUTH_ID_`, `AUTH_TYPE_`, `AUTH_CODE_`, `AUTH_NAME_`, `AUTH_ENNAME_`, `AUTH_URI_`, `AUTH_ICON_`, `ORDER_ID_`, `DESCR_`, `IS_DELETE_`, `IS_FINAL_`, `CRT_TIME_`, `UPD_TIME_`, `CRT_USERID_`, `UPD_USERID_`, `OPER_ID_`, `FID_`, `FIDS_`, `LEVEL_ID_`, `IS_VISIBLE_`) VALUES ('1004607', '2', 'metaMethod:init', '一键初始化Controller方法', '', '/', '', '1', '一键初始化Controller方法', '0', '0', now(), now(), '1', '1', '1', '1000103', '1/3/1000103', '4', '1');
INSERT INTO `sys_auth` (`AUTH_ID_`, `AUTH_TYPE_`, `AUTH_CODE_`, `AUTH_NAME_`, `AUTH_ENNAME_`, `AUTH_URI_`, `AUTH_ICON_`, `ORDER_ID_`, `DESCR_`, `IS_DELETE_`, `IS_FINAL_`, `CRT_TIME_`, `UPD_TIME_`, `CRT_USERID_`, `UPD_USERID_`, `OPER_ID_`, `FID_`, `FIDS_`, `LEVEL_ID_`, `IS_VISIBLE_`) VALUES ('1000111', '2', 'metaMethod:delete', '删除方法', '', '/', '', '1', '删除方法', '0', '0', now(), now(), '1', '1', '3', '1000103', '1/3/1000103', '4', '1');
INSERT INTO `sys_auth` (`AUTH_ID_`, `AUTH_TYPE_`, `AUTH_CODE_`, `AUTH_NAME_`, `AUTH_ENNAME_`, `AUTH_URI_`, `AUTH_ICON_`, `ORDER_ID_`, `DESCR_`, `IS_DELETE_`, `IS_FINAL_`, `CRT_TIME_`, `UPD_TIME_`, `CRT_USERID_`, `UPD_USERID_`, `OPER_ID_`, `FID_`, `FIDS_`, `LEVEL_ID_`, `IS_VISIBLE_`) VALUES ('1000109', '2', 'metaMethod:edit', '修改方法', '', '/', '', '1', '修改方法', '0', '0', now(), now(), '1', '1', '2', '1000103', '1/3/1000103', '4', '1');
INSERT INTO `sys_auth` (`AUTH_ID_`, `AUTH_TYPE_`, `AUTH_CODE_`, `AUTH_NAME_`, `AUTH_ENNAME_`, `AUTH_URI_`, `AUTH_ICON_`, `ORDER_ID_`, `DESCR_`, `IS_DELETE_`, `IS_FINAL_`, `CRT_TIME_`, `UPD_TIME_`, `CRT_USERID_`, `UPD_USERID_`, `OPER_ID_`, `FID_`, `FIDS_`, `LEVEL_ID_`, `IS_VISIBLE_`) VALUES ('1000107', '2', 'metaMethod:add', '新建方法', '', '/', '', '1', '新建方法', '0', '0', now(), now(), '1', '1', '1', '1000103', '1/3/1000103', '4', '1');
INSERT INTO `sys_auth` (`AUTH_ID_`, `AUTH_TYPE_`, `AUTH_CODE_`, `AUTH_NAME_`, `AUTH_ENNAME_`, `AUTH_URI_`, `AUTH_ICON_`, `ORDER_ID_`, `DESCR_`, `IS_DELETE_`, `IS_FINAL_`, `CRT_TIME_`, `UPD_TIME_`, `CRT_USERID_`, `UPD_USERID_`, `OPER_ID_`, `FID_`, `FIDS_`, `LEVEL_ID_`, `IS_VISIBLE_`) VALUES ('1000105', '2', 'metaMethod:view', '查看方法', '', '/', '', '1', '查看方法', '0', '0', now(), now(), '1', '1', '4', '1000103', '1/3/1000103', '4', '1');

INSERT INTO `sys_role_auth_join` (`ROLE_ID_`, `AUTH_ID_`, `IS_FINAL_`, `CRT_TIME_`, `CRT_USERID_`) VALUES ('1', '1000103', '0', now(), '1');
INSERT INTO `sys_role_auth_join` (`ROLE_ID_`, `AUTH_ID_`, `IS_FINAL_`, `CRT_TIME_`, `CRT_USERID_`) VALUES ('1', '1004607', '0', now(), '1');
INSERT INTO `sys_role_auth_join` (`ROLE_ID_`, `AUTH_ID_`, `IS_FINAL_`, `CRT_TIME_`, `CRT_USERID_`) VALUES ('1', '1000111', '0', now(), '1');
INSERT INTO `sys_role_auth_join` (`ROLE_ID_`, `AUTH_ID_`, `IS_FINAL_`, `CRT_TIME_`, `CRT_USERID_`) VALUES ('1', '1000109', '0', now(), '1');
INSERT INTO `sys_role_auth_join` (`ROLE_ID_`, `AUTH_ID_`, `IS_FINAL_`, `CRT_TIME_`, `CRT_USERID_`) VALUES ('1', '1000107', '0', now(), '1');
INSERT INTO `sys_role_auth_join` (`ROLE_ID_`, `AUTH_ID_`, `IS_FINAL_`, `CRT_TIME_`, `CRT_USERID_`) VALUES ('1', '1000105', '0', now(), '1');

-- 系统元方法-日志级别系统设定
INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysMetaMethod_logLevel','1','系统方法日志级别(1:DEBUG;2:INFO;)',1,NOW(),1,0,1);

-- 系统元方法-日志级别字典组、字典项
INSERT INTO sys_datadic_group(GROUP_ID_,GROUP_CODE_,GROUP_NAME_,GROUP_DESC_,ORDER_ID_,IS_DELETE_,IS_FINAL_,CRT_TIME_,UPD_TIME_,CRT_USERID_,UPD_USERID_)
VALUES(5,'SYSMETAMETHOD_LOG_LEVEL','系统方法日志级别','1:DEBUG;2:INFO;',5,0,0,NOW(),NOW(),1,1);

INSERT INTO SYS_DATADIC_ITEM (ITEM_ID_, GROUP_ID_, ITEM_CODE_, ITEM_NAME_, ITEM_VALUE_, ITEM_DESC_, ORDER_ID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
VALUES
(43, 5, 'DEBUG', 'DEBUG', '2', NULL, 1, 0, 0, NOW(), NOW(), 1, 1),
(44, 5, 'INFO', 'INFO', '1', NULL, 2, 0, 0, NOW(), NOW(), 1, 1);

-- 系统元方法-日志类型字典组、字典项
INSERT INTO sys_datadic_group(GROUP_ID_,GROUP_CODE_,GROUP_NAME_,GROUP_DESC_,ORDER_ID_,IS_DELETE_,IS_FINAL_,CRT_TIME_,UPD_TIME_,CRT_USERID_,UPD_USERID_)
VALUES(6,'SYSMETAMETHOD_LOG_TYPE','系统方法日志类型','0:操作日志;1:业务日志;',6,0,0,NOW(),NOW(),1,1);

INSERT INTO SYS_DATADIC_ITEM (ITEM_ID_, GROUP_ID_, ITEM_CODE_, ITEM_NAME_, ITEM_VALUE_, ITEM_DESC_, ORDER_ID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
VALUES
(45, 6, 'OPERATION', '操作日志', '0', NULL, 1, 0, 0, NOW(), NOW(), 1, 1),
(46, 6, 'BUSINESS', '业务日志', '1', NULL, 2, 0, 0, NOW(), NOW(), 1, 1);
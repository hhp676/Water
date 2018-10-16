-- -----------------sys_user---------------------------------
ALTER TABLE sys_user ADD COLUMN `LAST_CHANGED_PWD_`  timestamp NULL DEFAULT NULL COMMENT '最后修改密码时间';
ALTER TABLE sys_user ADD COLUMN `LAST_LOCK_TIME_`  timestamp NULL DEFAULT NULL COMMENT '最后锁定时间';

-- ----------------密码过期和不能与最近几次相同-------------------------------
INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('updPassqord_switch','0','密码定期更换标识，0有效，1无效',1,NOW(),1,0,1);

INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysPasswordChangeCycle','90','密码定期更换周期(天)',1,NOW(),1,0,1);

INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysPwdChangeCanNotUsedRecent','1','密码不能与最近几次修改的相同-开关(0:禁用;1:启用)',1,NOW(),1,0,1);

INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysPwdChangeCanNotUsedCount','3','密码不能与最近几次修改的相同-次数',1,NOW(),1,0,1);

-- --------------账号锁定---------------------------------------------------------
INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysLoginCheckUserLock','1','登录时检查用户锁定状态-开关(0:禁用;1:启用)',1,NOW(),1,0,1);

INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysLoginUserLock','1','登录自动锁定账号-开关(0:禁用;1:启用)：登录时，同一账号（或同账号同IP），在密码试错时间范围内，若密码尝试次数超限制，系统将自动锁定该账号（或该账号在该IP的状态）;在设置的自动解锁时间后，将自动解锁，恢复使用',0,NOW(),1,0,1);

INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysLoginUserLockType','0','登录自动锁定账号-方式(0:账号;1:账号IP)：0账号锁定:在设置的时间范围(min)内，某账号，无论使用什么ip登录，只要密码输入错误超上限，便锁定账号；锁定之后，所有ip下均不能登录;1账号IP锁定:在设置的时间范围(min)内，同账号同ip，若密码输入错误超上限，便锁定；锁定之后，该账号仅在该ip下不能登录',1,NOW(),1,0,1);


INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysUserPassAttemptsTimeLimit','10','登录自动锁定账号-密码试错时间范围(min)：设为0时，无时间限制，密码尝试次数累计达上限即可锁定','10',NOW(),1,0,1);

INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysUserPassAttemptsCountsLimit','3','登录自动锁定账号-密码尝试次数限制：在设置的次数内，仍未登录成功，则锁定',10,NOW(),1,0,1);

INSERT INTO sys_config(CONFIG_KEY_,CONFIG_VALUE_,CONFIG_DESC_,DEFAULT_VALUE_,UPD_TIME_,UPD_USERID_,IS_FINAL_,IS_VISIBLE_)
VALUES('sysUserLockingTime','1','登录自动锁定账号-自动解锁时间(min)',0,NOW(),1,0,1);

#用户密码变更记录表
CREATE TABLE `sys_user_password_change` (
  `PWD_CHANGE_ID_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户密码记录ID',
  `ACCOUNT_ID_` bigint(20) DEFAULT NULL COMMENT '登录账号ID',
  `OLD_LOGIN_PWD_` varchar(256) DEFAULT NULL COMMENT '旧登录密码',
  `NEW_LOGIN_PWD_` varchar(256) DEFAULT NULL COMMENT '新登录密码',
  `CHANGE_REASON_` tinyint(4) unsigned DEFAULT '0' COMMENT '变更原因(0:普通修改;1:过期修改;2:用户管理修改;3忘记密码找回修改;4:新用户修改初始密码)',
  `IS_DELETE_` tinyint(4) DEFAULT '0' COMMENT '删除标识(1:已删除;0:正常)',
  `IS_FINAL_` tinyint(4) DEFAULT '0' COMMENT '是否不可修改(1:不可修改;0:可修改)',
  `CRT_TIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `UPD_TIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据最后修改时间',
  `CRT_USER_ID_` bigint(20) DEFAULT NULL COMMENT '数据创建用户编号',
  `UPD_USER_ID_` bigint(20) DEFAULT NULL COMMENT '数据修改用户编号',
  PRIMARY KEY (`PWD_CHANGE_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户密码变更记录表';

#用户状态变更表
CREATE TABLE `sys_user_state_change` (
  `STATE_CHANGE_ID_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '状态变更记录ID',
  `ACCOUNT_ID_` bigint(20) NOT NULL COMMENT '账号ID',
  `TYPE_` tinyint(4) DEFAULT NULL COMMENT '状态类型(0:账号状态;1:[账号IP]状态)',
  `IP_` varchar(64) DEFAULT NULL COMMENT '客户端IP(LOCK_TYPE_=1时使用)',
  `CRT_TIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ORIGIN_STATE_` tinyint(4) DEFAULT NULL COMMENT '原状态(0:正常;1:锁定)',
  `CURRENT_STATE_` tinyint(4) DEFAULT NULL COMMENT '现状态(0:正常;1:锁定)',
  `IS_CURRENT_` tinyint(4) DEFAULT NULL COMMENT '是否当前状态(1:是;0:否)',
  `DESCR_` text COMMENT '描述',
  `CRT_TYPE_` tinyint(4) DEFAULT NULL COMMENT '数据创建类型(0:系统自动;1:手动配置)',
  `IS_DELETE_` tinyint(4) DEFAULT '0' COMMENT '删除标识(1:已删除;0:正常)',
  `IS_FINAL_` tinyint(4) DEFAULT '0' COMMENT '是否不可修改',
  `UPD_TIME_` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '数据最后修改时间',
  `CRT_USER_ID_` bigint(20) DEFAULT NULL COMMENT '数据创建用户编号',
  `UPD_USER_ID_` bigint(20) DEFAULT NULL COMMENT '数据修改用户编号',
  PRIMARY KEY (`STATE_CHANGE_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户状态变更表';

#用户登录日志表
CREATE TABLE `sys_user_login_log` (
  `LOGIN_LOG_ID_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '登录日志ID',
  `ACCOUNT_ID_` bigint(20) DEFAULT NULL COMMENT '账号ID',
  `LOGIN_TYPE_` varchar(32) DEFAULT NULL COMMENT '登录类型(general;token;rest;sso;cas)',
  `OPER_CODE_` varchar(128) DEFAULT NULL COMMENT '操作编码',
  `OPER_NAME_` varchar(256) DEFAULT NULL COMMENT '中文操作名称',
  `ENG_NAME_` varchar(256) DEFAULT NULL COMMENT '英文操作名称',
  `REQ_URI_` varchar(256) DEFAULT NULL COMMENT '请求URI',
  `OPER_IP_` varchar(64) DEFAULT NULL COMMENT '调用方IP',
  `OPER_RES_` tinyint(4) DEFAULT NULL COMMENT '操作结果(0:失败;1:成功)',
  `EXCEPTION_NAME_` varchar(256) DEFAULT NULL COMMENT '异常名',
  `IS_ACCUMULATE_LOGIN_TIMES_` tinyint(4) DEFAULT NULL COMMENT '是否累计登陆次数(用于账号锁定和IP锁定查询最近n次累计登陆次数的登陆记录)',
  `CONTENT_` text COMMENT '日志内容(可放置json格式请求参数)',
  `REQ_TYPE_` tinyint(4) DEFAULT NULL COMMENT '请求类型(0:非ajax;1:ajax)',
  `REQ_METHOD_` varchar(64) DEFAULT NULL COMMENT '请求方式(如:GET)',
  `BROWSER_TYPE_` varchar(64) DEFAULT NULL COMMENT '浏览器类型',
  `REQ_USER_AGENT_` varchar(256) DEFAULT NULL COMMENT '请求Request Headers的User-Agent',
  `LOGIN_LOCATION_` varchar(256) DEFAULT NULL COMMENT '登录地点',
  `IS_FINAL_` tinyint(4) DEFAULT '0' COMMENT '是否不可修改',
  `CRT_TIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CRT_USER_ID_` bigint(20) NOT NULL DEFAULT '1' COMMENT '数据创建用户编号',
  PRIMARY KEY (`LOGIN_LOG_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录日志表';

#添加用户登录日志表
insert into SYS_AUTH(AUTH_ID_,AUTH_TYPE_,AUTH_CODE_,AUTH_NAME_,AUTH_ENNAME_,AUTH_URI_
,AUTH_ICON_,ORDER_ID_,DESCR_,IS_DELETE_,IS_FINAL_,CRT_TIME_,UPD_TIME_,CRT_USERID_,UPD_USERID_
,OPER_ID_,FID_,FIDS_,LEVEL_ID_,IS_VISIBLE_)
VALUES
(39,1,'sys:userLoginLog','用户登录日志','sys userLoginLog','/sys/SysUserLoginLog/showSysUserLoginLog','page_white_text',1,'用户登录日志',0,1,now(),now(),1,1,null,3,'1/3',3,1),
(40,1,'sys:userStateChange','用户状态变更表','sys userStateChange','/sys/SysUserStateChange/showSysUserStateChange','page_white_text',1,'用户状态变更表',0,1,now(),now(),1,1,null,3,'1/3',3,1),
(391,2,'userLoginLog:view','查看详情','userLoginLog view','/','',1,'查看详情',0,1,now(),now(),1,1,1,39,'1/3/39',4,1), 
(401,2,'userStateChange:view','查看详情','userStateChange view','/','',1,'查看详情',0,1,now(),now(),1,1,1,40,'1/3/40',4,1), 
(216,2,'user:lock','锁定用户','user lock','/','',1,'锁定用户',0,1,now(),now(),1,1,4,21,'1/2/21',4,1),
(217,2,'user:unlock','解锁用户','user unlock','/','',1,'解锁用户',0,1,now(),now(),1,1,4,21,'1/2/21',4,1);

insert into SYS_ROLE_AUTH_JOIN ( ROLE_ID_ ,AUTH_ID_ , IS_FINAL_ , CRT_TIME_ , CRT_USERID_ ) VALUES (1 ,401 , 0 ,now() ,1);
insert into SYS_ROLE_AUTH_JOIN ( ROLE_ID_ ,AUTH_ID_ , IS_FINAL_ , CRT_TIME_ , CRT_USERID_ ) VALUES (1 ,40 , 0 ,now() ,1);
insert into SYS_ROLE_AUTH_JOIN ( ROLE_ID_ ,AUTH_ID_ , IS_FINAL_ , CRT_TIME_ , CRT_USERID_ ) VALUES (1 ,39 , 0 ,now() ,1);
insert into SYS_ROLE_AUTH_JOIN ( ROLE_ID_ ,AUTH_ID_ , IS_FINAL_ , CRT_TIME_ , CRT_USERID_ ) VALUES (1 ,391 , 0 ,now() ,1);
insert into SYS_ROLE_AUTH_JOIN ( ROLE_ID_ ,AUTH_ID_ , IS_FINAL_ , CRT_TIME_ , CRT_USERID_ ) VALUES (1 ,216 , 0 ,now() ,1);
insert into SYS_ROLE_AUTH_JOIN ( ROLE_ID_ ,AUTH_ID_ , IS_FINAL_ , CRT_TIME_ , CRT_USERID_ ) VALUES (1 ,217 , 0 ,now() ,1);


#添加数据字典
insert into SYS_DATADIC_GROUP (GROUP_ID_, GROUP_CODE_, GROUP_NAME_, GROUP_DESC_, ORDER_ID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
VALUES
(8, 'LOGIN_STATE', '登录结果', null, 4, 0, 1, now(), now(), 1, 1),
(9, 'PASS_CHANGE_REASON','用户密码变更原因',null,9,0,1,now(),now(),1,1);

insert into SYS_DATADIC_ITEM (ITEM_ID_, GROUP_ID_, ITEM_CODE_, ITEM_NAME_, ITEM_VALUE_, ITEM_DESC_, ORDER_ID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
VALUES
(81, 8, 'SUCCESS', '登录成功', '1', null, 1, 0, 1, now(), now(), 1, 1),
(82, 8, 'FALIUER', '登录失败', '0', null, 2, 0, 1, now(), now(), 1, 1),
(91, 9, 'DEFAULT', '普通修改', '0', null, 2, 0, 1, now(), now(), 1, 1),
(92, 9, 'EXPIRED', '过期修改', '1', null, 2, 0, 1, now(), now(), 1, 1),
(93, 9, 'ADMIN', '用户管理修改', '2', null, 2, 0, 1, now(), now(), 1, 1),
(94, 9, 'FORGET', '忘记密码找回-修改密码', '3', null, 2, 0, 1, now(), now(), 1, 1),
(95, 9, 'INITIAL', '新用户修改初始密码', '4', null, 2, 0, 1, now(), now(), 1, 1);
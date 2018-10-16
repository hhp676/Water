/*==============================================================*/
/* 初始化系统操作类型信息                                                                                                                                                                    */
/*==============================================================*/  
INSERT INTO SYS_AUTH_OPER(OPER_ID_,OPER_CODE_,OPER_NAME_,OPER_ENNAME_,OPER_ICON_
,ORDER_ID_,DESCR_,IS_DELETE_,IS_FINAL_,CRT_TIME_,UPD_TIME_,CRT_USERID_,UPD_USERID_)
VALUES(1,'add','新建','add','',1,'新建类型',0,1,now(),now(),1,1),
(2,'edit','修改','edit','',1,'修改类型',0,1,now(),now(),1,1),
(3,'delete','删除','delete','',2,'删除类型',0,1,now(),now(),1,1),
(4,'view','查看','view','',3,'查看类型',0,1,now(),now(),1,1);

/*==============================================================*/
/* 初始化系统权限信息                                                                                                                                                                    */
/*==============================================================*/ 
insert into SYS_AUTH(AUTH_ID_,AUTH_TYPE_,AUTH_CODE_,AUTH_NAME_,AUTH_ENNAME_,AUTH_URI_
,AUTH_ICON_,ORDER_ID_,DESCR_,IS_DELETE_,IS_FINAL_,CRT_TIME_,UPD_TIME_,CRT_USERID_,UPD_USERID_
,OPER_ID_,FID_,FIDS_,LEVEL_ID_,IS_VISIBLE_)
VALUES(1,1,'root','管理导航','root','/','',1,'管理导航根节点',0,1,now(),now(),1,1,null,0,'',1,1),  
(2,1,'sys:manage','系统管理','sys manage','/','icon-cog',1,'系统管理',0,1,now(),now(),1,1,null,1,'1',2,1), 
(21,1,'sys:user','用户管理','user manage','/sys/sysUser/showSysUser','person1',1,'用户管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 
(22,1,'sys:role','角色管理','role manage','/sys/role/showSysRole','star',1,'角色管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 
(23,1,'sys:auth','权限管理','auth manage','/sys/auth/showSysAuth','tag_green',1,'权限管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 
(24,1,'sys:userGroup','用户组管理','userGroup manage','/sys/userGroup/showSysUserGroup','group',1,'用户组管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 
(25,1,'sys:department','部门管理','depart manage','/sys/department/showSysDepartment','pkg',1,'部门管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 

(3,1,'sys:maintain','系统运维','sys maintain','/','icon-tasks',1,'系统运维',0,1,now(),now(),1,1,null,1,'1',2,1), 
(31,1,'sys:variable','系统变量','sys variable','/sys/var/showSysVar','pilcrow',1,'系统变量',0,1,now(),now(),1,1,null,3,'1/3',3,1),
(32,1,'sys:datadic','数据字典','sys datadic','/sys/datadic/item/showSysDatadicItem','folder_table',1,'数据字典',0,1,now(),now(),1,1,null,3,'1/3',3,1), 
(33,1,'sys:monitor','系统监控','sys monitor','/sys/info/showSysInfo','world',1,'系统监控',0,1,now(),now(),1,1,null,3,'1/3',3,1), 
(34,1,'sys:config','系统设定','sys config','/sys/cfg/showSysCfg','setting',1,'系统设定',0,1,now(),now(),1,1,null,3,'1/3',3,1), 
(35,1,'sys:userLog','用户日志','sys config','/sys/userLog/showSysUserLog','page_white_text',1,'用户日志',0,1,now(),now(),1,1,null,3,'1/3',3,1),

(4,1,'feature:demo','DEMO管理','sys maintain','/','icon-globe',4,'DEMO管理',0,1,now(),now(),1,1,null,1,'1',2,1),
(41,1,'feature:gis','地图','feature gis','/feature/gis/show','world',41,'地图',0,1,now(),now(),1,1,null,4,'1/4',3,1),

(211,2,'user:add','新建用户','user add','/','',1,'新建用户',0,1,now(),now(),1,1,1,21,'1/2/21',4,1), 
(212,2,'user:edit','修改用户','user edit','/','',1,'修改用户',0,1,now(),now(),1,1,2,21,'1/2/21',4,1), 
(213,2,'user:delete','删除用户','user delete','/','',1,'删除用户',0,1,now(),now(),1,1,3,21,'1/2/21',4,1), 
(214,2,'user:view','查看用户','user view','/','',1,'查看用户',0,1,now(),now(),1,1,4,21,'1/2/21',4,1),

(221,2,'role:add','新建角色','role add','/','',1,'新建角色',0,1,now(),now(),1,1,1,22,'1/2/22',4,1), 
(222,2,'role:edit','修改角色','role edit','/','',1,'修改角色',0,1,now(),now(),1,1,2,22,'1/2/22',4,1), 
(223,2,'role:delete','删除角色','role delete','/','',1,'删除角色',0,1,now(),now(),1,1,3,22,'1/2/22',4,1), 
(224,2,'role:view','查看角色','role view','/','',1,'查看角色',0,1,now(),now(),1,1,4,22,'1/2/22',4,1),

(231,2,'auth:add','新建权限','auth add','/','',1,'新建权限',0,1,now(),now(),1,1,1,23,'1/2/23',4,1), 
(232,2,'auth:edit','修改权限','auth edit','/','',1,'修改权限',0,1,now(),now(),1,1,2,23,'1/2/23',4,1), 
(233,2,'auth:delete','删除权限','auth delete','/','',1,'删除权限',0,1,now(),now(),1,1,3,23,'1/2/23',4,1), 
(234,2,'auth:view','查看权限','auth view','/','',1,'查看权限',0,1,now(),now(),1,1,4,23,'1/2/23',4,1),

(235,1,'sys:oper','操作类型','sys oper','/','',1,'操作类型',0,1,now(),now(),1,1,1,23,'1/3/23',4,1),
(2351,2,'oper:add','新建操作类型','oper add','/','',1,'新建操作类型',0,1,now(),now(),1,1,1,235,'1/2/235',5,1), 
(2352,2,'oper:edit','修改操作类型','oper edit','/','',1,'修改操作类型',0,1,now(),now(),1,1,2,235,'1/2/235',5,1), 
(2353,2,'oper:delete','删除操作类型','oper delete','/','',1,'删除操作类型',0,1,now(),now(),1,1,3,235,'1/2/235',5,1), 
(2354,2,'oper:view','查看操作类型','oper view','/','',1,'查看操作类型',0,1,now(),now(),1,1,4,235,'1/2/235',5,1),

(241,2,'userGroup:add','新建用户组','userGroup add','/','',1,'新建用户组',0,1,now(),now(),1,1,1,24,'1/2/24',4,1), 
(242,2,'userGroup:edit','修改用户组','userGroup edit','/','',1,'修改用户组',0,1,now(),now(),1,1,2,24,'1/2/24',4,1), 
(243,2,'userGroup:delete','删除用户组','userGroup delete','/','',1,'删除用户组',0,1,now(),now(),1,1,3,24,'1/2/24',4,1), 
(244,2,'userGroup:view','查看用户组','userGroup view','/','',1,'查看用户组',0,1,now(),now(),1,1,4,24,'1/2/24',4,1),

(251,2,'department:add','新建部门','department add','/','',1,'新建部门',0,1,now(),now(),1,1,1,25,'1/2/25',4,1), 
(252,2,'department:edit','修改部门','department edit','/','',1,'修改部门',0,1,now(),now(),1,1,2,25,'1/2/25',4,1), 
(253,2,'department:delete','删除部门','department delete','/','',1,'删除部门',0,1,now(),now(),1,1,3,25,'1/2/25',4,1), 
(254,2,'department:view','查看部门','department view','/','',1,'查看部门',0,1,now(),now(),1,1,4,25,'1/2/25',4,1),

(311,2,'variable:add','新建变量','variable add','/','',1,'新建变量',0,1,now(),now(),1,1,1,31,'1/3/31',4,1), 
(312,2,'variable:edit','修改变量','variable edit','/','',1,'修改变量',0,1,now(),now(),1,1,2,31,'1/3/31',4,1), 
(313,2,'variable:delete','删除变量','variable delete','/','',1,'删除变量',0,1,now(),now(),1,1,3,31,'1/3/31',4,1), 
(314,2,'variable:view','查看变量','variable view','/','',1,'查看变量',0,1,now(),now(),1,1,4,31,'1/3/31',4,1),

(321,2,'datadic:add','新建字典项','datadic add','/','',1,'新建字典项',0,1,now(),now(),1,1,1,32,'1/3/32',4,1), 
(322,2,'datadic:edit','修改字典项','datadic edit','/','',1,'修改字典项',0,1,now(),now(),1,1,2,32,'1/3/32',4,1), 
(323,2,'datadic:delete','删除字典项','datadic delete','/','',1,'删除字典项',0,1,now(),now(),1,1,3,32,'1/3/32',4,1), 
(324,2,'datadic:view','查看字典项','datadic view','/','',1,'查看字典项',0,1,now(),now(),1,1,4,32,'1/3/32',4,1),

(325,1,'sys:datadicGroup','数据字典组','sys datadicGroup','/','',1,'数据字典组',0,1,now(),now(),1,1,1,32,'1/3/32',4,1), 
(3251,2,'datadicGroup:add','新建字典组','datadicGroup add','/','',1,'新建字典组',0,1,now(),now(),1,1,1,325,'1/3/32',5,1), 
(3252,2,'datadicGroup:edit','修改字典组','datadicGroup edit','/','',1,'修改字典组',0,1,now(),now(),1,1,2,325,'1/3/32',5,1), 
(3253,2,'datadicGroup:delete','删除字典组','datadicGroup delete','/','',1,'删除字典组',0,1,now(),now(),1,1,3,325,'1/3/32',5,1), 
(3254,2,'datadicGroup:view','查看字典组','datadicGroup view','/','',1,'查看字典组',0,1,now(),now(),1,1,4,325,'1/3/32',5,1);


--
--(211,2,'user:add','新建用户','add','/','',1,'新建用户',0,1,now(),now(),1,1), 
--(212,2,'edit','修改用户','edit','/','',1,'修改用户',0,1,now(),now(),1,1), 
--(213,2,'delete','删除用户','delete','/','',1,'删除用户',0,1,now(),now(),1,1), 
--(214,2,'view','查看用户','view','/','',1,'查看用户',0,1,now(),now(),1,1),


/*==============================================================*/
/* 初始化角色信息                                                                                                                                                                    */
/*==============================================================*/   
INSERT INTO SYS_ROLE (ROLE_ID_, ROLE_NAME_, ROLE_CODE_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_, DESCR_)
    VALUES(1, '超级管理员', 'superAdmin', 0, 1,now(), now(), 1, 1, '超级管理员');
/*
INSERT INTO SYS_ROLE (ROLE_ID_, ROLE_NAME_, ROLE_CODE_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_, DESCR_)
    VALUES(2, '普通管理员', 'normalAdmin', 0, 1,now(), now(), 1, 1, '普通管理员');    
*/

    
/*==============================================================*/
/* 初始化角色权限关联信息                                                                                                                                                                    */
/*==============================================================*/       
/* 超级管理员角色对应的权限 */
call PROC_INIT_AUTH();

/* 普通管理员角色对应的权限   */ 



/*==============================================================*/
/* 初始化用户信息                                                                                                                                                                    */
/*==============================================================*/   
insert into SYS_USER (USER_ID_, USER_NAME_, ENG_NAME_, LOGIN_NAME_, LOGIN_PWD_, SEX_, BIRTHDAY_, MOBILE_, EMAIL_, ADDRESS_, TELEPHONE_, DESCR_, IS_VALID_, IS_LOCK_, LAST_LOGIN_IP_, LAST_LOGIN_TIME_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
values (1, '超级管理员', 'super', 'super', '8462d0480e2232443268decc78f920a0880579d1', 1, null, null, null, null, null, '超级管理员', 1, 0, null, null, 0, 1, null, null, 1, 1),
(2, '普通管理员', 'admin', 'admin', '55516a952f026f086ffc38f73348b34e58e97756', 1, null, null, null, null, null, '普通管理员', 1, 0, null, null, 0, 1, null, null, 1, 1);


/*==============================================================*/
/* 初始化用户角色关联表                                                                                                                                                                        */
/*==============================================================*/   
insert into sys_user_role_join (user_id_, role_id_, is_final_, crt_time_, crt_userid_) 
values (1, 1, 1, now(), 1);


/*==============================================================*/
/* 初始化用户权限关联表                                                                                                                                                                        */
/*==============================================================*/   
insert into sys_user_auth_join (user_id_, auth_id_, is_final_, crt_time_, crt_userid_, auth_type_)
values (1, 1, 1, now(), 1, 1);



/*==============================================================*/
/* 初始化系统设置信息                                                                                                                                                                    */
/*==============================================================*/ 
insert into SYS_CONFIG (CONFIG_KEY_, CONFIG_VALUE_, DEFAULT_VALUE_, CONFIG_DESC_, CONFIG_TYPE_, UPD_TIME_, UPD_USERID_, IS_VISIBLE_, IS_FINAL_)
VALUES('system_version', '1.0', '1.0', '系统版本', null, now(), 1, 1, 1),
('cache_switch', '1', '1', '缓存开关', null, now(), 1, 1, 0),
('system_lang', 'zh', 'zh', '系统语言', null, now(), 1, 0, 1),
('log_level', 'debug', 'debug', '日志等级', null, now(), 1, 1, 1),
('session_timeout', '60', '60', 'session失效时间', null, now(), 1, 1, 1),
('cluster_switch', '1', '1', '集群开关', null, now(), 1, 0, 1),
('login_verifcode', '1', '1', '登录验证码开关', null, now(), 1, 1, 0),
('next_db_id', '10000', '10000', '下一个数据库斌编号', null, now(), 1, 1, 1),
('active_timeout', '1800', '0', '登录用户非活跃自动退出时间(秒)', null, now(), 1, 1, 0);


/*==============================================================*/
/* 数据字典组、项                                                                                                                                                                    */
/*==============================================================*/ 
insert into SYS_DATADIC_GROUP (GROUP_ID_, GROUP_CODE_, GROUP_NAME_, GROUP_DESC_, ORDER_ID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
VALUES( 1, 'LOGIC_TAG', '逻辑标识', null, 1, 0, 1, now(), now(), 1, 1),
(2, 'AUTH_TYPE', '权限类型', null, 2, 0, 1, now(), now(), 1, 1),
(3, 'SEX', '性别', null, 3, 0, 1, now(), now(), 1, 1);

insert into SYS_DATADIC_ITEM (ITEM_ID_, GROUP_ID_, ITEM_CODE_, ITEM_NAME_, ITEM_VALUE_, ITEM_DESC_, ORDER_ID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
VALUES( 11, 1, 'YES', '是', '1', null, 1, 0, 1, now(), now(), 1, 1),
(12, 1, 'NO', '否', '0', null, 2, 0, 1, now(), now(), 1, 1),
(21, 2, 'MODULE_AUTH', '模块权限', '1', null, 2, 0, 1, now(), now(), 1, 1),
(22, 2, 'OPER_AUTH', '操作权限', '2', null, 3, 0, 1, now(), now(), 1, 1),
(23, 2, 'APP_AUTH', '应用权限', '0', null, 1, 0, 1, now(), now(), 1, 1),
(31, 3, 'M', '男', '1', null, 1, 0, 1, now(), now(), 1, 1),
(32, 3, 'F', '女', '0', null, 2, 0, 1, now(), now(), 1, 1);




/*==============================================================*/
/* 初始化部门信息                                                                                                                                                                    */
/*==============================================================*/ 
insert into sys_department (DEPART_ID_, DEPART_NAME_, DEPART_FULLNAME_, DEPART_CODE_, ENGNAME_, FID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
values (0, '0', '', '鸿冠', '', null, 0, 1, null, null, null, null);

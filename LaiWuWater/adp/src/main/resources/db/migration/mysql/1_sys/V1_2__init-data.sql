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
(2,1,'sys:manage','系统管理','sys manage','/','m-icon-computer',1,'系统管理',0,1,now(),now(),1,1,null,1,'1',2,1), 
(21,1,'sys:user','用户管理','user manage','/sys/sysUser/showSysUser','person1',1,'用户管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 
(22,1,'sys:role','角色管理','role manage','/sys/role/showSysRole','star',1,'角色管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 
(23,1,'sys:auth','权限管理','auth manage','/sys/auth/showSysAuth','tag_green',1,'权限管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 
(24,1,'sys:userGroup','用户组管理','userGroup manage','/sys/userGroup/showSysUserGroup','group',1,'用户组管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 
(25,1,'sys:department','部门管理','depart manage','/sys/department/showSysDepartment','pkg',1,'部门管理',0,1,now(),now(),1,1,null,2,'1/2',3,1), 

(3,1,'sys:maintain','系统运维','sys maintain','/','m-icon-drive',1,'系统运维',0,1,now(),now(),1,1,null,1,'1',2,1), 
(31,1,'sys:variable','系统变量','sys variable','/sys/var/showSysVar','pilcrow',1,'系统变量',0,1,now(),now(),1,1,null,3,'1/3',3,1),
(32,1,'sys:datadic','数据字典','sys datadic','/sys/datadic/item/showSysDatadicItem','folder_table',1,'数据字典',0,1,now(),now(),1,1,null,3,'1/3',3,1), 
(33,1,'sys:monitor','系统监控','sys monitor','/sys/info/showSysInfo','world',1,'系统监控',0,1,now(),now(),1,1,null,3,'1/3',3,1), 
(34,1,'sys:config','系统设定','sys config','/sys/cfg/showSysCfg','setting',1,'系统设定',0,1,now(),now(),1,1,null,3,'1/3',3,1), 
(35,1,'sys:userLog','用户日志','sys config','/sys/userLog/showSysUserLog','page_white_text',1,'用户日志',0,1,now(),now(),1,1,null,3,'1/3',3,1),
(36,1,'sys:notice','系统公告','sys notice','/sys/notice/showSysNotice','photos',1,'系统公告',0,1,now(),now(),1,1,null,3,'1/3',3,1),
(38,1,'sys:notify','用户通知','sys notify','/sys/notify/showSysNotify','bell',1,'用户通知',0,1,now(),now(),1,1,null,3,'1/3',3,1),

(211,2,'user:add','新建用户','user add','/','',1,'新建用户',0,1,now(),now(),1,1,1,21,'1/2/21',4,1), 
(212,2,'user:edit','修改用户','user edit','/','',1,'修改用户',0,1,now(),now(),1,1,2,21,'1/2/21',4,1), 
(213,2,'user:delete','删除用户','user delete','/','',1,'删除用户',0,1,now(),now(),1,1,3,21,'1/2/21',4,1), 
(214,2,'user:view','查看用户','user view','/','',1,'查看用户',0,1,now(),now(),1,1,4,21,'1/2/21',4,1),
(215,2,'user:import','批量导入','user import','/','',1,'批量导入',0,1,now(),now(),1,1,4,21,'1/2/21',4,1),

(221,2,'role:add','新建角色','role add','/','',1,'新建角色',0,1,now(),now(),1,1,1,22,'1/2/22',4,1), 
(222,2,'role:edit','修改角色','role edit','/','',1,'修改角色',0,1,now(),now(),1,1,2,22,'1/2/22',4,1), 
(223,2,'role:delete','删除角色','role delete','/','',1,'删除角色',0,1,now(),now(),1,1,3,22,'1/2/22',4,1), 
(224,2,'role:view','查看角色','role view','/','',1,'查看角色',0,1,now(),now(),1,1,4,22,'1/2/22',4,1),

(231,2,'auth:add','新建权限','auth add','/','',1,'新建权限',0,1,now(),now(),1,1,1,23,'1/2/23',4,1), 
(232,2,'auth:edit','修改权限','auth edit','/','',1,'修改权限',0,1,now(),now(),1,1,2,23,'1/2/23',4,1), 
(233,2,'auth:delete','删除权限','auth delete','/','',1,'删除权限',0,1,now(),now(),1,1,3,23,'1/2/23',4,1), 
(234,2,'auth:view','查看权限','auth view','/','',1,'查看权限',0,1,now(),now(),1,1,4,23,'1/2/23',4,1),

(235,1,'sys:oper','操作类型','sys oper','/','',1,'操作类型',0,1,now(),now(),1,1,1,23,'1/3/23',4,0),
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

(325,1,'sys:datadicGroup','数据字典组','sys datadicGroup','/','',1,'数据字典组',0,1,now(),now(),1,1,1,32,'1/3/32',4,0), 
(3251,2,'datadicGroup:add','新建字典组','datadicGroup add','/','',1,'新建字典组',0,1,now(),now(),1,1,1,325,'1/3/32',5,1), 
(3252,2,'datadicGroup:edit','修改字典组','datadicGroup edit','/','',1,'修改字典组',0,1,now(),now(),1,1,2,325,'1/3/32',5,1), 
(3253,2,'datadicGroup:delete','删除字典组','datadicGroup delete','/','',1,'删除字典组',0,1,now(),now(),1,1,3,325,'1/3/32',5,1), 
(3254,2,'datadicGroup:view','查看字典组','datadicGroup view','/','',1,'查看字典组',0,1,now(),now(),1,1,4,325,'1/3/32',5,1),

(361,2,'notice:add','新建公告','notice add','/','',1,'新建公告',0,1,now(),now(),1,1,1,36,'1/3/36',4,1), 
(362,2,'notice:edit','修改公告','notice edit','/','',1,'修改公告',0,1,now(),now(),1,1,2,36,'1/3/36',4,1), 
(363,2,'notice:delete','删除公告','notice delete','/','',1,'删除公告',0,1,now(),now(),1,1,3,36,'1/3/36',4,1), 
(364,2,'notice:view','查看公告','notice view','/','',1,'查看公告',0,1,now(),now(),1,1,4,36,'1/3/36',4,1),

(381,2,'notify:view','查看通知','notify view','/','',1,'查看通知',0,1,now(),now(),1,1,4,38,'1/3/38',4,1),
(382,2,'sys:notifyTemplate','通知模板','sys notifyTemplate','/','',1,'通知模板',0,1,now(),now(),1,1,1,38,'1/3/38',4,1), 
(3821,2,'notifyTemplate:add','新建通知模板','notifyTemplate add','/','',1,'新建通知模板',0,1,now(),now(),1,1,1,382,'1/3/38/382',5,1), 
(3822,2,'notifyTemplate:edit','修改通知模板','notifyTemplate edit','/','',1,'修改通知模板',0,1,now(),now(),1,1,2,382,'1/3/38/382',5,1), 
(3823,2,'notifyTemplate:delete','删除通知模板','notifyTemplate delete','/','',1,'删除通知模板',0,1,now(),now(),1,1,3,382,'1/3/38/382',5,1), 
(3824,2,'notifyTemplate:view','查看通知模板','notifyTemplate view','/','',1,'查看通知模板',0,1,now(),now(),1,1,4,382,'1/3/38/382',5,1),

(100001,1,'oa:process','我的流程','my process','/','acc_icon_workflow',1,'我的流程',0,0,now(), now(),1,1,null,1,'1',2,1),
(100011,1,'process:showToStart','流程列表','process showStart','/workflow/process/show','start',1,'发起流程',0,0,now(), now(),1,1,null,100001,'1/100001',3,1),
(100012,1,'process:showRunnings','运行的流程','process showRunnings','/workflow/process-instance/running/show','running',1,'活动中的流程',0,0,now(),now(),1,1,null,100001,'1/100001',3,1),
(100013,1,'process:showFinishs','办结的流程','process showDones','/workflow/process-instance/finished/show','finish',1,'办结的流程',0,0,now(),now(),1,1,null,100001,'1/100001',3,1),
(100014,1,'process:showJoins','参与的流程','process showJoins','/workflow/process-instance/joined/show','takepartin',1,'参与的流程',0,0,now(),now(),1,1,null,100001,'1/100001',3,1),
/*(100015,1,'process:showDraft','草稿箱','process showDraft','/workflow/process/draft/show','draft',1,'草稿箱',0,0,now(),now(),1,1,null,100001,'1/100001',3,1),*/

(100002,1,'oa:task','我的任务','my task','/','acc_icon_task16',1,'我的任务',0,0,now(),now(),1,1,null,1,'1',2,1),
(100021,1,'task:showTodos','待办任务','task showTodos','/workflow/task/toDo/show','todo',1,'待办任务',0,0,now(),now(),1,1,null,100002,'1/100002',3,1),
(100022,1,'task:showToclaims','待领任务','task showToclaims','/workflow/task/toClaim/show','receive',1,'待领任务',0,0,now(),now(),1,1,null,100002,'1/100002',3,1),
(100023,1,'task:showDones','已办任务','task showDones','/workflow/task/done/show','done',1,'已办任务',0,0,now(),now(),1,1,null,100002,'1/100002',3,1);
/*(100024,1,'task:showAgents','代理中的任务','task showAgents','/workflow/task/agent/show','proxy',1,'代理中的任务',0,0,now(),now(),1,1,null,100002,'1/100002',3,1),*/
/*(100025,1,'task:showDelegateSet','自动委托设置','task showDelegateSet','/workflow/task/agent/showSet','change',1,'自动委托设置',0,0,now(),now(),1,1,null,100002,'1/100002',3,1),*/


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
values (1, '超级管理员', 'super', 'super', '37dbf3cc91a5e0004b160e8ad6c3463191a1e500', 1, null, null, null, null, null, '超级管理员', 1, 0, null, null, 0, 1, null, null, 1, 1),
(2, '普通管理员', 'admin', 'admin', '5cabfc502dc95d81cf38e1ed932f461c178a8efc', 1, null, null, null, null, null, '普通管理员', 1, 0, null, null, 0, 1, null, null, 1, 1);


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
('next_db_id', '1000000', '1000000', '下一个数据库斌编号', null, now(), 1, 1, 1),
('active_timeout', '1800', '0', '登录用户非活跃自动退出时间(秒)', null, now(), 1, 1, 0);


/*==============================================================*/
/* 数据字典组、项                                                                                                                                                                    */
/*==============================================================*/ 
insert into SYS_DATADIC_GROUP (GROUP_ID_, GROUP_CODE_, GROUP_NAME_, GROUP_DESC_, ORDER_ID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
VALUES( 1, 'LOGIC_TAG', '逻辑标识', null, 1, 0, 1, now(), now(), 1, 1),
(2, 'AUTH_TYPE', '权限类型', null, 2, 0, 1, now(), now(), 1, 1),
(3, 'SEX', '性别', null, 3, 0, 1, now(), now(), 1, 1),
(4, 'PUB_STATUS', '发布状态', null, 4, 0, 1, now(), now(), 1, 1);

insert into SYS_DATADIC_ITEM (ITEM_ID_, GROUP_ID_, ITEM_CODE_, ITEM_NAME_, ITEM_VALUE_, ITEM_DESC_, ORDER_ID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
VALUES( 11, 1, 'YES', '是', '1', null, 1, 0, 1, now(), now(), 1, 1),
(12, 1, 'NO', '否', '0', null, 2, 0, 1, now(), now(), 1, 1),
(21, 2, 'MODULE_AUTH', '模块权限', '1', null, 2, 0, 1, now(), now(), 1, 1),
(22, 2, 'OPER_AUTH', '操作权限', '2', null, 3, 0, 1, now(), now(), 1, 1),
(23, 2, 'APP_AUTH', '应用权限', '0', null, 1, 0, 1, now(), now(), 1, 1),
(31, 3, 'M', '男', '1', null, 1, 0, 1, now(), now(), 1, 1),
(32, 3, 'F', '女', '0', null, 2, 0, 1, now(), now(), 1, 1),
(41, 4, 'N', '未发布', '0', null, 1, 0, 1, now(), now(), 1, 1),
(42, 4, 'Y', '已发布', '1', null, 2, 0, 1, now(), now(), 1, 1);




/*==============================================================*/
/* 初始化部门信息                                                                                                                                                                    */
/*==============================================================*/ 
insert into sys_department (DEPART_ID_, DEPART_NAME_, DEPART_FULLNAME_, DEPART_CODE_, ENGNAME_, FID_, IS_DELETE_, IS_FINAL_, CRT_TIME_, UPD_TIME_, CRT_USERID_, UPD_USERID_)
values (0, '0', '', '鸿冠', '', null, 0, 1, null, null, null, null);

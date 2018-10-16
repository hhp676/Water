/*==============================================================*/
/* Table: SYS_AUTH                                              */
/*==============================================================*/
create table SYS_AUTH 
(
   AUTH_ID_             NUMBER               not null,
   AUTH_TYPE_           NUMBER(2)            default 0 not null,
   AUTH_CODE_           VARCHAR(64),
   AUTH_NAME_           VARCHAR(256),
   AUTH_ENNAME_          VARCHAR(256),
   AUTH_URI_            VARCHAR(256),
   AUTH_ICON_           VARCHAR(256),
   ORDER_ID_            NUMBER(5),
   DESCR_               VARCHAR(500),
   IS_DELETE_           NUMBER(2)            default 0,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   UPD_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   UPD_USERID_          NUMBER,
   OPER_ID_             NUMBER,
   FID_                 NUMBER,
   FIDS_                varchar(256),
   LEVEL_ID_            NUMBER(5),
   IS_VISIBLE_          NUMBER(2)             default 0,   
   constraint PK_SYS_AUTH primary key (AUTH_ID_),
   constraint AK_KEY_2_SYS_AUTH unique (AUTH_CODE_)
);

comment on table SYS_AUTH is
'系统权限表';

comment on column SYS_AUTH.AUTH_ID_ is
'权限编号';

comment on column SYS_AUTH.AUTH_TYPE_ is
'权限类别(0:应用;1:模块;2:操作)';

comment on column SYS_AUTH.AUTH_CODE_ is
'编码';

comment on column SYS_AUTH.AUTH_NAME_ is
'权限名称';

comment on column SYS_AUTH.AUTH_ENNAME_ is
'英文名';

comment on column SYS_AUTH.AUTH_URI_ is
'URI';

comment on column SYS_AUTH.AUTH_ICON_ is
'图标';

comment on column SYS_AUTH.ORDER_ID_ is
'排序ID';

comment on column SYS_AUTH.DESCR_ is
'描述';

comment on column SYS_AUTH.IS_DELETE_ is
'删除标识(1:已删除;0:正常)';

comment on column SYS_AUTH.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_AUTH.CRT_TIME_ is
'数据创建时间';

comment on column SYS_AUTH.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_AUTH.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_AUTH.UPD_USERID_ is
'数据修改用户编号';

comment on column SYS_AUTH.FID_ is
'父ID';

comment on column SYS_AUTH.FIDS_ is
'父IDS(1/2/4)';

comment on column SYS_AUTH.LEVEL_ID_ is
'等级ID';

comment on column SYS_AUTH.IS_VISIBLE_ is
'是否可见';


/*==============================================================*/
/* Table: SYS_AUTH_OPER                                         */
/*==============================================================*/
create table SYS_AUTH_OPER 
(
   OPER_ID_             NUMBER               not null,
   OPER_CODE_           varchar(256),
   OPER_NAME_           varchar(256),
   OPER_ENNAME_         varchar(256),
   OPER_ICON_           varchar(256),
   ORDER_ID_            NUMBER(5),
   DESCR_               varchar(256),
   IS_DELETE_           NUMBER(2)            default 0,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   UPD_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   UPD_USERID_          NUMBER,
   constraint PK_SYS_AUTH_OPER primary key (OPER_ID_),
   constraint AK_KEY_2_SYS_AUH unique (OPER_CODE_)
);

comment on table SYS_AUTH_OPER is
'系统操作类型表';

comment on column SYS_AUTH_OPER.OPER_ID_ is
'操作编号';

comment on column SYS_AUTH_OPER.OPER_CODE_ is
'编码';

comment on column SYS_AUTH_OPER.OPER_NAME_ is
'操作名称';

comment on column SYS_AUTH_OPER.OPER_ENNAME_ is
'英文名';

comment on column SYS_AUTH_OPER.OPER_ICON_ is
'图标';

comment on column SYS_AUTH_OPER.ORDER_ID_ is
'排序ID';

comment on column SYS_AUTH_OPER.IS_DELETE_ is
'删除标识(1:已删除;0:正常)';

comment on column SYS_AUTH_OPER.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_AUTH_OPER.CRT_TIME_ is
'数据创建时间';

comment on column SYS_AUTH_OPER.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_AUTH_OPER.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_AUTH_OPER.UPD_USERID_ is
'数据修改用户编号';


/*==============================================================*/
/* Table: SYS_DATADIC_GROUP                                     */
/*==============================================================*/
create table SYS_DATADIC_GROUP 
(
   GROUP_ID_            NUMBER               not null,
   GROUP_CODE_          VARCHAR(64)          not null,
   GROUP_NAME_          VARCHAR(64),
   GROUP_DESC_          VARCHAR(256),
   ORDER_ID_            NUMBER,
   IS_DELETE_           NUMBER(2)            default 0,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   UPD_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   UPD_USERID_          NUMBER,
   constraint PK_SYS_DATADIC_GROUP primary key (GROUP_ID_),
   constraint AK_KEY_2_SYS_DATA unique (GROUP_CODE_)
);

comment on table SYS_DATADIC_GROUP is
'数据字典组表';

comment on column SYS_DATADIC_GROUP.GROUP_ID_ is
'组ID';

comment on column SYS_DATADIC_GROUP.GROUP_CODE_ is
'组编码';

comment on column SYS_DATADIC_GROUP.GROUP_NAME_ is
'组名';

comment on column SYS_DATADIC_GROUP.GROUP_DESC_ is
'组描述';

comment on column SYS_DATADIC_GROUP.ORDER_ID_ is
'排序值';

comment on column SYS_DATADIC_GROUP.IS_DELETE_ is
'删除标识(1:已删除;0:正常)';

comment on column SYS_DATADIC_GROUP.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_DATADIC_GROUP.CRT_TIME_ is
'数据创建时间';

comment on column SYS_DATADIC_GROUP.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_DATADIC_GROUP.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_DATADIC_GROUP.UPD_USERID_ is
'数据修改用户编号';

/*==============================================================*/
/* Table: SYS_DATADIC_ITEM                                      */
/*==============================================================*/
create table SYS_DATADIC_ITEM 
(
   ITEM_ID_             NUMBER               not null,
   GROUP_ID_            NUMBER,
   ITEM_CODE_           VARCHAR(64)          not null,
   ITEM_NAME_           VARCHAR(256),
   ITEM_VALUE_          VARCHAR(256),
   ITEM_DESC_           VARCHAR(256),
   ORDER_ID_            NUMBER,
   IS_DELETE_           NUMBER(2)            default 0,
   IS_FINAL_            NUMBER               default 1,
   CRT_TIME_            TIMESTAMP,
   UPD_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   UPD_USERID_          NUMBER,
   constraint PK_SYS_DATADIC_ITEM primary key (ITEM_ID_),
   constraint AK_KEY_2_SYS_ITEM unique (ITEM_CODE_)
);

comment on table SYS_DATADIC_ITEM is
'数据字典项表';

comment on column SYS_DATADIC_ITEM.ITEM_ID_ is
'用户ID';

comment on column SYS_DATADIC_ITEM.GROUP_ID_ is
'组ID';

comment on column SYS_DATADIC_ITEM.ITEM_CODE_ is
'项编码';

comment on column SYS_DATADIC_ITEM.ITEM_NAME_ is
'项名';

comment on column SYS_DATADIC_ITEM.ITEM_VALUE_ is
'项值';

comment on column SYS_DATADIC_ITEM.ITEM_DESC_ is
'描述';

comment on column SYS_DATADIC_ITEM.ORDER_ID_ is
'排序ID';

comment on column SYS_DATADIC_ITEM.IS_DELETE_ is
'删除标识(1:已删除;0:正常)';

comment on column SYS_DATADIC_ITEM.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_DATADIC_ITEM.CRT_TIME_ is
'数据创建时间';

comment on column SYS_DATADIC_ITEM.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_DATADIC_ITEM.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_DATADIC_ITEM.UPD_USERID_ is
'数据修改用户编号';

/*==============================================================*/
/* Table: SYS_DEPARTMENT                                        */
/*==============================================================*/
create table SYS_DEPARTMENT 
(
   DEPART_ID_           NUMBER               not null,
   DEPART_NAME_         VARCHAR(256),
   DEPART_FULLNAME_     VARCHAR2(256),
   DEPART_CODE_         VARCHAR(256),
   ENGNAME_             VARCHAR(256),
   FID_                 NUMBER,
   IS_DELETE_           NUMBER(2)            default 0,
   IS_FINAL_            NUMBER               default 1,
   CRT_TIME_            TIMESTAMP,
   UPD_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   UPD_USERID_          NUMBER,
   constraint PK_SYS_DEPARTMENT primary key (DEPART_ID_),
   constraint AK_KEY_2_SYS_DEPA unique (DEPART_CODE_)
);

comment on table SYS_DEPARTMENT is
'组织架构(部门)表';

comment on column SYS_DEPARTMENT.DEPART_ID_ is
'部门编号';

comment on column SYS_DEPARTMENT.DEPART_NAME_ is
'部门名称';

comment on column SYS_DEPARTMENT.DEPART_FULLNAME_ is
'部门全名';

comment on column SYS_DEPARTMENT.DEPART_CODE_ is
'部门编码(默认为编号)';

comment on column SYS_DEPARTMENT.ENGNAME_ is
'部门英文名称';

comment on column SYS_DEPARTMENT.FID_ is
'父部门';

comment on column SYS_DEPARTMENT.IS_DELETE_ is
'删除标识(1:已删除;0:正常)';

comment on column SYS_DEPARTMENT.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_DEPARTMENT.CRT_TIME_ is
'数据创建时间';

comment on column SYS_DEPARTMENT.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_DEPARTMENT.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_DEPARTMENT.UPD_USERID_ is
'数据修改用户编号';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE 
(
   ROLE_ID_             NUMBER               not null,
   ROLE_NAME_           VARCHAR(256)         not null,
   ROLE_CODE_           VARCHAR(256)          not null,
   IS_DELETE_           NUMBER(2)            default 0,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   UPD_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   UPD_USERID_          NUMBER,
   DESCR_               varchar(500),
   constraint PK_SYS_ROLE primary key (ROLE_ID_),
   constraint AK_KEY_2_SYS_ROLE unique (ROLE_CODE_)
);

comment on table SYS_ROLE is
' 系统角色表';

comment on column SYS_ROLE.ROLE_ID_ is
'角色编号';

comment on column SYS_ROLE.ROLE_NAME_ is
'角色名称';

comment on column SYS_ROLE.ROLE_CODE_ is
'角色编码(默认值为编号)';

comment on column SYS_ROLE.IS_DELETE_ is
'删除标识(1:已删除;0:正常)';

comment on column SYS_ROLE.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_ROLE.CRT_TIME_ is
'数据创建时间';

comment on column SYS_ROLE.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_ROLE.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_ROLE.UPD_USERID_ is
'数据修改用户编号';

comment on column SYS_ROLE.DESCR_ is
'描述';

/*==============================================================*/
/* Table: SYS_ROLE_AUTH_JOIN                                    */
/*==============================================================*/
create table SYS_ROLE_AUTH_JOIN 
(
   ROLE_ID_             NUMBER               not null,
   AUTH_ID_             NUMBER               not null,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   constraint PK_SYS_ROLE_AUTH_JOIN primary key (ROLE_ID_, AUTH_ID_)
);

comment on table SYS_ROLE_AUTH_JOIN is
' 系统角色权限关系表';

comment on column SYS_ROLE_AUTH_JOIN.ROLE_ID_ is
'角色编号';

comment on column SYS_ROLE_AUTH_JOIN.AUTH_ID_ is
'权限编号';

comment on column SYS_ROLE_AUTH_JOIN.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_ROLE_AUTH_JOIN.CRT_TIME_ is
'数据创建时间';

comment on column SYS_ROLE_AUTH_JOIN.CRT_USERID_ is
'数据创建用户编号';

/*==============================================================*/
/* Table: SYS_ROLE_UGROUP_JOIN                                  */
/*==============================================================*/
create table SYS_ROLE_UGROUP_JOIN 
(
   ROLE_ID_             NUMBER               not null,
   GROUP_ID_            NUMBER               not null,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   constraint PK_SYS_ROLE_UGROUP_JOIN primary key (ROLE_ID_, GROUP_ID_)
);

comment on table SYS_ROLE_UGROUP_JOIN is
'系统角色用户组关系表';

comment on column SYS_ROLE_UGROUP_JOIN.ROLE_ID_ is
'角色ID';

comment on column SYS_ROLE_UGROUP_JOIN.GROUP_ID_ is
'用户组编号';

comment on column SYS_ROLE_UGROUP_JOIN.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_ROLE_UGROUP_JOIN.CRT_TIME_ is
'数据创建时间';

comment on column SYS_ROLE_UGROUP_JOIN.CRT_USERID_ is
'数据创建用户编号';

/*==============================================================*/
/* Table: SYS_UGROUP_AUTH_JOIN                                  */
/*==============================================================*/
create table SYS_UGROUP_AUTH_JOIN 
(
   GROUP_ID_            NUMBER               not null,
   AUTH_ID_             NUMBER               not null,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   AUTH_TYPE_           NUMBER(2)            default 1,
   constraint PK_SYS_UGROUP_AUTH_JOIN primary key (GROUP_ID_, AUTH_ID_)
);

comment on table SYS_UGROUP_AUTH_JOIN is
' 系统权限用户组关系表';

comment on column SYS_UGROUP_AUTH_JOIN.GROUP_ID_ is
'用户组编号';

comment on column SYS_UGROUP_AUTH_JOIN.AUTH_ID_ is
'权限编号';

comment on column SYS_UGROUP_AUTH_JOIN.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_UGROUP_AUTH_JOIN.CRT_TIME_ is
'数据创建时间';

comment on column SYS_UGROUP_AUTH_JOIN.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_UGROUP_AUTH_JOIN.AUTH_TYPE_ is
'授权类型（1:正授权;0:负授权）';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER 
(
   USER_ID_             NUMBER               not null,
   USER_NAME_           varchar(64)         not null,
   ENG_NAME_            VARCHAR(64),
   LOGIN_NAME_          varchar(64)         not null,
   LOGIN_PWD_           varchar(64)          not null,
   SEX_                 NUMBER(2)            default 0,
   BIRTHDAY_            VARCHAR(32),
   MOBILE_              varchar(32),
   EMAIL_               varchar(64),
   ADDRESS_             VARCHAR(256),
   TELEPHONE_           varchar(32),
   DESCR_               VARCHAR(256),
   IS_VALID_            NUMBER(2)            default 1,
   IS_LOCK_             NUMBER(2)            default 0,
   LAST_LOGIN_IP_       varchar(15),
   LAST_LOGIN_TIME_     TIMESTAMP,
   IS_DELETE_           NUMBER(2)            default 0,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   UPD_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   UPD_USERID_          NUMBER,
   constraint PK_SYS_USER primary key (USER_ID_),
   constraint AK_KEY_2_SYS_USER unique (LOGIN_NAME_)
);

comment on table SYS_USER is
'系统用户表';

comment on column SYS_USER.USER_ID_ is
'用户ID';

comment on column SYS_USER.USER_NAME_ is
'用户名';

comment on column SYS_USER.ENG_NAME_ is
'英文名';

comment on column SYS_USER.LOGIN_NAME_ is
'登录名';

comment on column SYS_USER.LOGIN_PWD_ is
'密码';

comment on column SYS_USER.SEX_ is
'性别(1:男;0:女)';

comment on column SYS_USER.BIRTHDAY_ is
'生日';

comment on column SYS_USER.MOBILE_ is
'手机';

comment on column SYS_USER.EMAIL_ is
'邮箱';

comment on column SYS_USER.ADDRESS_ is
'地址';

comment on column SYS_USER.TELEPHONE_ is
'联系电话';

comment on column SYS_USER.DESCR_ is
'描述';

comment on column SYS_USER.IS_VALID_ is
'是否有效(1:有效;0:无效)';

comment on column SYS_USER.IS_LOCK_ is
'是否被锁定(1:锁定;0:正常)';

comment on column SYS_USER.LAST_LOGIN_IP_ is
'最后登录IP';

comment on column SYS_USER.LAST_LOGIN_TIME_ is
'最后登录时间';

comment on column SYS_USER.IS_DELETE_ is
'删除标识(1:已删除;0:正常)';

comment on column SYS_USER.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_USER.CRT_TIME_ is
'数据创建时间';

comment on column SYS_USER.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_USER.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_USER.UPD_USERID_ is
'数据修改用户编号';

/*==============================================================*/
/* Table: SYS_USER_AUTH_JOIN                                    */
/*==============================================================*/
create table SYS_USER_AUTH_JOIN 
(
   USER_ID_             NUMBER               not null,
   AUTH_ID_             NUMBER               not null,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   AUTH_TYPE_           NUMBER(2)            default 1,
   constraint PK_SYS_USER_AUTH_JOIN primary key (USER_ID_, AUTH_ID_)
);

comment on table SYS_USER_AUTH_JOIN is
' 系统用户权限关联表';

comment on column SYS_USER_AUTH_JOIN.USER_ID_ is
'用户ID';

comment on column SYS_USER_AUTH_JOIN.AUTH_ID_ is
'权限编号';

comment on column SYS_USER_AUTH_JOIN.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_USER_AUTH_JOIN.CRT_TIME_ is
'数据创建时间';

comment on column SYS_USER_AUTH_JOIN.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_USER_AUTH_JOIN.AUTH_TYPE_ is
'授权类型（1:正授权;0:负授权）';

/*==============================================================*/
/* Table: SYS_USER_GROUP                                        */
/*==============================================================*/
create table SYS_USER_GROUP 
(
   GROUP_ID_            NUMBER               not null,
   DEPART_ID_           NUMBER,
   GROUP_NAME_          VARCHAR(100),
   GROUP_CODE_          VARCHAR(50)          not null,
   DESCR_               VARCHAR(500),
   FID_                 NUMBER,
   IS_DELETE_           NUMBER(2)            default 0,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   UPD_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   UPD_USERID_          NUMBER,
   constraint PK_SYS_USER_GROUP primary key (GROUP_ID_),
   constraint AK_KEY_2_SYS_GROUP unique (GROUP_CODE_)
);

comment on table SYS_USER_GROUP is
'系统用户组表';

comment on column SYS_USER_GROUP.GROUP_ID_ is
'用户组编号';

comment on column SYS_USER_GROUP.DEPART_ID_ is
'部门编号';

comment on column SYS_USER_GROUP.GROUP_NAME_ is
'用户组名称';

comment on column SYS_USER_GROUP.GROUP_CODE_ is
'用户组编码(默认值为编号)';

comment on column SYS_USER_GROUP.DESCR_ is
'描述';

comment on column SYS_USER_GROUP.FID_ is
'父组编号';

comment on column SYS_USER_GROUP.IS_DELETE_ is
'删除标识(1:已删除;0:正常)';

comment on column SYS_USER_GROUP.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_USER_GROUP.CRT_TIME_ is
'数据创建时间';

comment on column SYS_USER_GROUP.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_USER_GROUP.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_USER_GROUP.UPD_USERID_ is
'数据修改用户编号';

/*==============================================================*/
/* Table: SYS_USER_LOG                                          */
/*==============================================================*/
create table SYS_USER_LOG 
(
   LOG_ID_              NUMBER               not null,
   LOG_TYPE_            NUMBER(2)            default 0,
   USER_ID_             NUMBER,
   OPER_CODE_           varchar(256),
   OPER_NAME_           VARCHAR(256),
   OPER_IP_             VARCHAR(15),
   REMARK_              varchar(4000),
   CRT_TIME_            TIMESTAMP(6),
   constraint PK_SYS_USER_LOG primary key (LOG_ID_)
);

comment on table SYS_USER_LOG is
'系统用户日志表';

comment on column SYS_USER_LOG.LOG_ID_ is
'日志ID';

comment on column SYS_USER_LOG.LOG_TYPE_ is
'类型(1:业务日志;0:操作日志)';

comment on column SYS_USER_LOG.USER_ID_ is
'用户ID';

comment on column SYS_USER_LOG.OPER_CODE_ is
'操作编码（如：USER_ADD）';

comment on column SYS_USER_LOG.OPER_NAME_ is
'操作名称（如：新增用户）';

comment on column SYS_USER_LOG.OPER_IP_ is
'操作时的IP';

comment on column SYS_USER_LOG.REMARK_ is
'备注';

comment on column SYS_USER_LOG.CRT_TIME_ is
'创建时间';

/*==============================================================*/
/* Table: SYS_USER_ROLE_JOIN                                    */
/*==============================================================*/
create table SYS_USER_ROLE_JOIN 
(
   USER_ID_             NUMBER               not null,
   ROLE_ID_             NUMBER               not null,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   constraint PK_SYS_USER_ROLE_JOIN primary key (USER_ID_, ROLE_ID_)
);

comment on table SYS_USER_ROLE_JOIN is
' 系统用户角色关系表';

comment on column SYS_USER_ROLE_JOIN.USER_ID_ is
'用户ID';

comment on column SYS_USER_ROLE_JOIN.ROLE_ID_ is
'角色ID';

comment on column SYS_USER_ROLE_JOIN.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_USER_ROLE_JOIN.CRT_TIME_ is
'数据创建时间';

comment on column SYS_USER_ROLE_JOIN.CRT_USERID_ is
'数据创建用户编号';

/*==============================================================*/
/* Table: SYS_USER_UGROUP_JOIN                                  */
/*==============================================================*/
create table SYS_USER_UGROUP_JOIN 
(
   USER_ID_             NUMBER               not null,
   GROUP_ID_            NUMBER               not null,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   constraint PK_SYS_USER_UGROUP_JOIN primary key (USER_ID_, GROUP_ID_)
);

comment on table SYS_USER_UGROUP_JOIN is
' 系统用户与用户组关系表';

comment on column SYS_USER_UGROUP_JOIN.USER_ID_ is
'用户ID';

comment on column SYS_USER_UGROUP_JOIN.GROUP_ID_ is
'用户组编号';

comment on column SYS_USER_UGROUP_JOIN.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_USER_UGROUP_JOIN.CRT_TIME_ is
'数据创建时间';

comment on column SYS_USER_UGROUP_JOIN.CRT_USERID_ is
'数据创建用户编号';

/*==============================================================*/
/* Table: SYS_VARIABLE                                          */
/*==============================================================*/
create table SYS_VARIABLE 
(
   VAR_ID_              NUMBER               not null,
   VAR_TYPE_            VARCHAR(64),
   VAR_NAME_            varchar(256)         not null,
   VAR_CODE_            varchar(256)         not null,
   VAR_VALUE_           varchar(256)         not null,
   DESCR_               varchar(500),
   IS_DELETE_           NUMBER(2)            default 0,
   IS_FINAL_            NUMBER(2)            default 1,
   CRT_TIME_            TIMESTAMP,
   UPD_TIME_            TIMESTAMP,
   CRT_USERID_          NUMBER,
   UPD_USERID_          NUMBER,
   constraint PK_SYS_VARIABLE primary key (VAR_ID_),
   constraint AK_KEY_2_SYS_VARI unique (VAR_NAME_)
);

comment on table SYS_VARIABLE is
'系统变量表';

comment on column SYS_VARIABLE.VAR_ID_ is
'参数ID';

comment on column SYS_VARIABLE.VAR_TYPE_ is
'类型';

comment on column SYS_VARIABLE.VAR_NAME_ is
'名称';

comment on column SYS_VARIABLE.VAR_VALUE_ is
'值';

comment on column SYS_VARIABLE.DESCR_ is
'描述';

comment on column SYS_VARIABLE.IS_DELETE_ is
'删除标识(1:已删除;0:正常)';

comment on column SYS_VARIABLE.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_VARIABLE.CRT_TIME_ is
'数据创建时间';

comment on column SYS_VARIABLE.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_VARIABLE.CRT_USERID_ is
'数据创建用户编号';

comment on column SYS_VARIABLE.UPD_USERID_ is
'数据修改用户编号';


create table SYS_CONFIG 
(
   CONFIG_KEY_          VARCHAR(64)          not null,
   CONFIG_VALUE_        VARCHAR(256),
   CONFIG_DESC_         varchar(256),
   CONFIG_TYPE_         varchar(32),
   DEFAULT_VALUE_       VARCHAR(256),
   UPD_TIME_            TIMESTAMP,
   UPD_USERID_          NUMBER,
   IS_FINAL_            NUMBER(2)            default 1,
   IS_VISIBLE_			NUMBER(2)            default 1,
   constraint PK_SYS_CONFIG primary key (CONFIG_KEY_)
);

comment on table SYS_CONFIG is
'系统设置表';

comment on column SYS_CONFIG.CONFIG_KEY_ is
'设置KEY';

comment on column SYS_CONFIG.CONFIG_VALUE_ is
'设置值';

comment on column SYS_CONFIG.CONFIG_DESC_ is
'设置描述';

comment on column SYS_CONFIG.CONFIG_TYPE_ is
'设置类型';

comment on column SYS_CONFIG.DEFAULT_VALUE_ is
'默认值';

comment on column SYS_CONFIG.UPD_TIME_ is
'数据最后修改时间';

comment on column SYS_CONFIG.UPD_USERID_ is
'数据修改用户编号';

comment on column SYS_CONFIG.IS_FINAL_ is
'是否不可修改(1:不可修改;0:可修改)';

comment on column SYS_CONFIG.IS_VISIBLE_ is
'是否可见(1:可见;0:不可见)';


alter table SYS_AUTH
   add constraint FK_SYS_AUTH_REFERENCE_SYS_AUTH foreign key (OPER_ID_)
      references SYS_AUTH_OPER (OPER_ID_);

alter table SYS_DATADIC_ITEM
   add constraint FK_SYS_DATA_REFERENCE_SYS_DATA foreign key (GROUP_ID_)
      references SYS_DATADIC_GROUP (GROUP_ID_);

alter table SYS_ROLE_AUTH_JOIN
   add constraint FK_SYS_ROLE_REFERENCE_SYS_AUTH foreign key (AUTH_ID_)
      references SYS_AUTH (AUTH_ID_);

alter table SYS_ROLE_AUTH_JOIN
   add constraint FK_SYS_ROLE_AUTH_REF_SYS_ROLE foreign key (ROLE_ID_)
      references SYS_ROLE (ROLE_ID_);

alter table SYS_ROLE_UGROUP_JOIN
   add constraint FK_SYS_ROLEUGROUP_REF_SYS_ROLE foreign key (ROLE_ID_)
      references SYS_ROLE (ROLE_ID_);

alter table SYS_ROLE_UGROUP_JOIN
   add constraint FK_SYS_ROLE_REFERENCE_SYS_USER foreign key (GROUP_ID_)
      references SYS_USER_GROUP (GROUP_ID_);

alter table SYS_UGROUP_AUTH_JOIN
   add constraint FK_SYS_UGRO_REFERENCE_SYS_USER foreign key (GROUP_ID_)
      references SYS_USER_GROUP (GROUP_ID_);

alter table SYS_UGROUP_AUTH_JOIN
   add constraint FK_SYS_UGRO_REFERENCE_SYS_AUTH foreign key (AUTH_ID_)
      references SYS_AUTH (AUTH_ID_);

alter table SYS_USER_AUTH_JOIN
   add constraint FK_SYS_USER_AUTH_REF_SYS_USER foreign key (USER_ID_)
      references SYS_USER (USER_ID_);

alter table SYS_USER_AUTH_JOIN
   add constraint FK_SYS_USER_REFERENCE_SYS_AUTH foreign key (AUTH_ID_)
      references SYS_AUTH (AUTH_ID_);

alter table SYS_USER_GROUP
   add constraint FK_SYS_USER_REFERENCE_SYS_DEPA foreign key (DEPART_ID_)
      references SYS_DEPARTMENT (DEPART_ID_);

alter table SYS_USER_LOG
   add constraint FK_SYS_USER_LOG_REF_SYS_USER foreign key (USER_ID_)
      references SYS_USER (USER_ID_);

alter table SYS_USER_ROLE_JOIN
   add constraint FK_SYS_USER_ROLE_REF_SYS_USER foreign key (USER_ID_)
      references SYS_USER (USER_ID_);

alter table SYS_USER_ROLE_JOIN
   add constraint FK_SYS_USER_REFERENCE_SYS_ROLE foreign key (ROLE_ID_)
      references SYS_ROLE (ROLE_ID_);

alter table SYS_USER_UGROUP_JOIN
   add constraint FK_SYS_USER_UG_REF_SYS_USER_G foreign key (GROUP_ID_)
      references SYS_USER_GROUP (GROUP_ID_);

alter table SYS_USER_UGROUP_JOIN
   add constraint FK_SYS_USER_UG_REF_SYS_USER foreign key (USER_ID_)
      references SYS_USER (USER_ID_);

    
/*==============================================================*/
/* 存储过程                                                                                                                                                                   */
/*==============================================================*/  
-- PL/SQL block
create or replace procedure PROC_INIT_AUTH is
begin
  delete from SYS_ROLE_AUTH_JOIN where ROLE_ID_=1;
  for c in (select auth_id_ from sys_auth)
  loop
      insert into SYS_ROLE_AUTH_JOIN (ROLE_ID_, AUTH_ID_, IS_FINAL_, CRT_TIME_, CRT_USERID_) 
      select 1,c.auth_id_,1,SYSDATE,1 from dual; 
  end loop;
end PROC_INIT_AUTH;
/
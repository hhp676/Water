/*==============================================================*/
/* Database name:  hgdf3.0                                     */
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/2 18:58:46                           */
/*==============================================================*/

/*==============================================================*/
/* Database: db_hgadp                                           */
/*==============================================================*/
drop table if exists SYS_AUTH;
drop table if exists SYS_AUTH_OPER;
drop table if exists SYS_CONFIG;
drop table if exists SYS_DATADIC_GROUP;
drop table if exists SYS_DATADIC_ITEM;
drop table if exists SYS_DEPARTMENT;
drop table if exists SYS_ROLE;
drop table if exists SYS_ROLE_AUTH_JOIN;
drop table if exists SYS_ROLE_UGROUP_JOIN;
drop table if exists SYS_UGROUP_AUTH_JOIN;
drop table if exists SYS_USER;
drop table if exists SYS_USER_AUTH_JOIN;
drop table if exists SYS_USER_GROUP;
drop table if exists SYS_USER_LOG;
drop table if exists SYS_USER_ROLE_JOIN;
drop table if exists SYS_USER_UGROUP_JOIN;
drop table if exists SYS_USER_UGROUP_JOIN;
drop table if exists SYS_VARIABLE;
drop table if exists sys_notify_template;
drop table if exists sys_notify;
drop table if exists sys_notice;


/*==============================================================*/
/* Table: SYS_AUTH                                              */
/*==============================================================*/
create table SYS_AUTH
(
   AUTH_ID_             int(10) not null auto_increment comment '权限编号',
   AUTH_TYPE_           int(1) not null default 0 comment '权限类别(0:应用;1:模块;2:操作)',
   AUTH_CODE_           varchar(64) comment '编码',
   AUTH_NAME_           varchar(64) comment '权限名称',
   AUTH_ENNAME_          varchar(64) comment '英文名',
   AUTH_URI_            varchar(256) comment 'URI',
   AUTH_ICON_           varchar(256) comment '图标',
   ORDER_ID_            int(10) comment '排序ID',
   DESCR_               varchar(256) comment '描述',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   OPER_ID_             int(5),
   FID_                 int(10),
   FIDS_                varchar(256),
   LEVEL_ID_            int(1),
   IS_VISIBLE_          int(1)             default 0,   
   primary key (AUTH_ID_),
   unique KEY AK_Key_2 (AUTH_CODE_) 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_AUTH comment '系统权限表';

/*==============================================================*/
/* Table: SYS_AUTH_OPER                                         */
/*==============================================================*/
create table SYS_AUTH_OPER
(
   OPER_ID_             int(10) not null auto_increment comment '操作编号',
   OPER_CODE_           varchar(64) comment '编码',
   OPER_NAME_           varchar(64) comment '权限名称',
   OPER_ENNAME_         varchar(256) comment '英文名',
   OPER_ICON_           varchar(256) comment '图标',
   ORDER_ID_            int(5) comment '排序ID',
   DESCR_               varchar(256) comment '描述',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   primary key (OPER_ID_),
   unique KEY AK_Key_2 (OPER_CODE_)   
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_AUTH_OPER comment '系统操作权限表';

/*==============================================================*/
/* Table: SYS_CONFIG                                            */
/*==============================================================*/
create table SYS_CONFIG
(
   CONFIG_KEY_          varchar(32) not null comment '设置KEY',
   CONFIG_VALUE_        varchar(256) comment '设置值',
   CONFIG_DESC_         varchar(256) comment '设置描述',
   CONFIG_TYPE_         varchar(32) comment '设置类型',
   DEFAULT_VALUE_       varchar(256) comment '默认值',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   IS_VISIBLE_          int(1) default 1 comment '是否可见(1:可见;0:不可见)',
   primary key (CONFIG_KEY_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_CONFIG comment '系统设置表';

/*==============================================================*/
/* Table: SYS_DATADIC_GROUP                                     */
/*==============================================================*/
create table SYS_DATADIC_GROUP
(
   GROUP_ID_            int(10) not null auto_increment comment '组ID',
   GROUP_CODE_          varchar(32) not null comment '组编码',
   GROUP_NAME_          varchar(64) comment '组名',
   GROUP_DESC_          varchar(256) comment '组描述',
   ORDER_ID_            int(5) comment '排序值',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   primary key (GROUP_ID_),
   unique KEY AK_Key_2 (GROUP_CODE_) 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_DATADIC_GROUP comment '数据字典组表';

/*==============================================================*/
/* Table: SYS_DATADIC_ITEM                                      */
/*==============================================================*/
create table SYS_DATADIC_ITEM
(
   ITEM_ID_             int(10) not null auto_increment comment '用户ID',
   GROUP_ID_            int(10) comment '组ID',
   ITEM_CODE_           varchar(32) not null comment '项编码',
   ITEM_NAME_           varchar(32) comment '项名',
   ITEM_VALUE_          varchar(32) comment '项值',
   ITEM_DESC_           varchar(256) comment '描述',
   ORDER_ID_            int(5) comment '排序ID',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(1) comment '数据创建用户编号',
   UPD_USERID_          int(1) comment '数据修改用户编号',
   primary key (ITEM_ID_),
   unique KEY AK_Key_2 (GROUP_ID_,ITEM_CODE_) 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_DATADIC_ITEM comment '数据字典项表';

/*==============================================================*/
/* Table: SYS_DEPARTMENT                                        */
/*==============================================================*/
create table SYS_DEPARTMENT
(
   DEPART_ID_           int(10) not null auto_increment comment '部门编号',
   DEPART_NAME_         varchar(256) comment '部门名称',
   DEPART_FULLNAME_     varchar(256) comment '部门全名',
   DEPART_CODE_         varchar(128) comment '部门编码(默认为编号)',
   ENGNAME_             varchar(256) comment '部门英文名称',
   FID_                 int(10) comment '父部门',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(10) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   primary key (DEPART_ID_),
   unique KEY AK_Key_2 (DEPART_CODE_) 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_DEPARTMENT comment '组织架构(部门)表';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE
(
   ROLE_ID_             int(10) not null auto_increment comment '角色编号',
   ROLE_NAME_           varchar(256) not null comment '角色名称',
   ROLE_CODE_           varchar(128) not null comment '角色编码(默认值为编号)',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   DESCR_               varchar(256) comment '描述',
   primary key (ROLE_ID_),
   unique KEY AK_Key_2 (ROLE_CODE_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_ROLE comment ' 系统角色表';

/*==============================================================*/
/* Table: SYS_ROLE_AUTH_JOIN                                    */
/*==============================================================*/
create table SYS_ROLE_AUTH_JOIN
(
   ROLE_ID_             int(10) not null auto_increment comment '角色编号',
   AUTH_ID_             int(10) not null comment '权限编号',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   primary key (ROLE_ID_, AUTH_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_ROLE_AUTH_JOIN comment ' 系统角色权限关系表';

/*==============================================================*/
/* Table: SYS_ROLE_UGROUP_JOIN                                  */
/*==============================================================*/
create table SYS_ROLE_UGROUP_JOIN
(
   ROLE_ID_             int(10) not null auto_increment comment '角色ID',
   GROUP_ID_            int(10) not null comment '用户组编号',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   primary key (ROLE_ID_, GROUP_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_ROLE_UGROUP_JOIN comment '系统角色用户组关系表';

/*==============================================================*/
/* Table: SYS_UGROUP_AUTH_JOIN                                  */
/*==============================================================*/
create table SYS_UGROUP_AUTH_JOIN
(
   GROUP_ID_            int(10) not null auto_increment comment '用户组编号',
   AUTH_ID_             int(10) not null comment '权限编号',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   AUTH_TYPE_           int(1) default 1 comment '授权类型（1:正授权;0:负授权）',
   primary key (GROUP_ID_, AUTH_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_UGROUP_AUTH_JOIN comment ' 系统权限用户组关系表';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
   USER_ID_             int(10) not null auto_increment comment '用户ID',
   USER_NAME_           varchar(256) not null comment '用户名',
   ENG_NAME_            varchar(256) comment '英文名',
   LOGIN_NAME_          varchar(128) not null comment '登录名',
   LOGIN_PWD_           varchar(256) not null comment '密码',
   SEX_                 int(1) default 0 comment '性别(1:男;0:女)',
   BIRTHDAY_            varchar(32) comment '生日',
   MOBILE_              varchar(50) comment '手机',
   EMAIL_               varchar(256) comment '邮箱',
   ADDRESS_             varchar(256) comment '地址',
   TELEPHONE_           varchar(50) comment '联系电话',
   DESCR_               text comment '描述',
   IS_VALID_            int(1) default 1 comment '是否有效(1:有效;0:无效)',
   IS_LOCK_             int(1) default 0 comment '是否被锁定(1:锁定;0:正常)',
   LAST_LOGIN_IP_       varchar(15) comment '最后登录IP',
   LAST_LOGIN_TIME_     timestamp comment '最后登录时间',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   primary key (USER_ID_),
   unique KEY AK_Key_2 (LOGIN_NAME_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_USER comment '系统用户表';

/*==============================================================*/
/* Table: SYS_USER_AUTH_JOIN                                    */
/*==============================================================*/
create table SYS_USER_AUTH_JOIN
(
   USER_ID_             int(10) not null auto_increment comment '用户ID',
   AUTH_ID_             int(10) not null comment '权限编号',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   AUTH_TYPE_           int(1) default 1 comment '授权类型（1:正授权;0:负授权）',
   primary key (USER_ID_, AUTH_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_USER_AUTH_JOIN comment ' 系统用户权限关联表';

/*==============================================================*/
/* Table: SYS_USER_GROUP                                        */
/*==============================================================*/
create table SYS_USER_GROUP
(
   GROUP_ID_            int(10) not null auto_increment comment '用户组编号',
   DEPART_ID_           int(10) comment '部门编号',
   GROUP_NAME_          varchar(64) comment '用户组名称',
   GROUP_CODE_          varchar(64) not null comment '用户组编码(默认值为编号)',
   DESCR_               varchar(256) comment '描述',
   FID_                 int(10) comment '父组编号',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   primary key (GROUP_ID_),
   unique KEY AK_Key_2 (GROUP_CODE_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_USER_GROUP comment '系统用户组表';

/*==============================================================*/
/* Table: SYS_USER_LOG                                          */
/*==============================================================*/
create table SYS_USER_LOG
(
   LOG_ID_              int(10) not null auto_increment comment '日志ID',
   LOG_TYPE_            int(1) default 0 comment '类型(1:业务日志;0:操作日志)',
   USER_ID_             int(10) comment '用户ID',
   OPER_CODE_           varchar(128) comment '操作编码（如：USER_ADD）',
   OPER_NAME_           varchar(256) comment '操作名称（如：新增用户）',
   OPER_IP_             varchar(15) comment '操作时的IP',
   REMARK_              varchar(4000) comment '备注',
   CRT_TIME_            timestamp comment '创建时间',
   primary key (LOG_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_USER_LOG comment '系统用户日志表';

/*==============================================================*/
/* Table: SYS_USER_ROLE_JOIN                                    */
/*==============================================================*/
create table SYS_USER_ROLE_JOIN
(
   USER_ID_             int(10) not null auto_increment comment '用户ID',
   ROLE_ID_             int(10) not null comment '角色ID',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   primary key (USER_ID_, ROLE_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_USER_ROLE_JOIN comment ' 系统用户角色关系表';

/*==============================================================*/
/* Table: SYS_USER_UGROUP_JOIN                                  */
/*==============================================================*/
create table SYS_USER_UGROUP_JOIN
(
   USER_ID_             int(10) not null auto_increment comment '用户ID',
   GROUP_ID_            int(10) not null comment '用户组编号',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   primary key (USER_ID_, GROUP_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_USER_UGROUP_JOIN comment ' 系统用户与用户组关系表';

/*==============================================================*/
/* Table: SYS_VARIABLE                                          */
/*==============================================================*/
create table SYS_VARIABLE
(
   VAR_ID_              int(10) not null auto_increment comment '参数ID',
   VAR_TYPE_            varchar(32) comment '类型',
   VAR_NAME_            varchar(32) not null comment '名称',
   VAR_VALUE_           varchar(64) not null comment '值',
   DESCR_               varchar(256) comment '描述',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '数据创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   primary key (VAR_ID_),
   unique KEY AK_Key_2 (VAR_NAME_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SYS_VARIABLE comment '系统变量表';

/*==============================================================*/
/* Table: sys_notify_template                                   */
/*==============================================================*/
create table sys_notify_template
(
   TEMP_ID_             int(10) not null auto_increment comment '模板ID',
   NAME_                varchar(64) comment '模板名',
   MODULE_              varchar(64) comment '模板所属模块',
   TITLE_TEMPLATE_      varchar(256) comment '通知标题',
   CONTENT_TEMPLATE_    varchar(2000) comment '通知内容模板',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   primary key (TEMP_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table sys_notify_template comment '系统通知模板表';

/*==============================================================*/
/* Table: sys_notify                                            */
/*==============================================================*/
create table sys_notify
(
   NOTIFY_ID_           int(10) not null auto_increment comment '通知ID',
   USER_ID_             int(10) comment '用户ID',
   TEMP_ID_             int(10) comment '模板ID',
   TITLE_               varchar(256) comment '通知标题',
   CONTENT_             varchar(2000) comment '通知内容',
   CRT_TIME_            timestamp comment '创建时间',
   IS_READ_             int(1) comment '是否读取',
   READ_TIME_           timestamp comment '阅读时间',
   primary key (NOTIFY_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table sys_notify comment '系统通知表';

/*==============================================================*/
/* Table: sys_notice                                            */
/*==============================================================*/
create table sys_notice
(
   NOTIFY_ID_           int(10) not null auto_increment comment '通知ID',
   STATUS_              int(1) comment '状态（1:发布|0:未发布）',
   AUTO_PUB_            int(1) comment '自动发布（1:是|0:否）',
   START_TIME_          timestamp comment '起始时间',
   END_TIME_            timestamp comment '截止时间',
   TITLE_               VARCHAR(50) comment '标题',
   CONTENT_             text comment '内容',
   SORT_ID_             int(5) comment '排序号',
   IS_DELETE_           int(1) default 0 comment '删除标识(1:已删除;0:正常)',
   IS_FINAL_            int(1) default 1 comment '是否不可修改(1:不可修改;0:可修改)',
   CRT_TIME_            timestamp comment '创建时间',
   UPD_TIME_            timestamp comment '数据最后修改时间',
   CRT_USERID_          int(10) comment '数据创建用户编号',
   UPD_USERID_          int(10) comment '数据修改用户编号',
   primary key (NOTIFY_ID_)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table sys_notice comment '系统公告表';


alter table SYS_AUTH add constraint FK_SYS_AUTHOPER_REF_SYS_AUTH foreign key (OPER_ID_)
      references SYS_AUTH_OPER (OPER_ID_) on delete restrict on update restrict;

alter table SYS_DATADIC_ITEM add constraint FK_Reference_26 foreign key (GROUP_ID_)
      references SYS_DATADIC_GROUP (GROUP_ID_) on delete restrict on update restrict;

alter table SYS_ROLE_AUTH_JOIN add constraint FK_Reference_19 foreign key (AUTH_ID_)
      references SYS_AUTH (AUTH_ID_) on delete restrict on update restrict;

alter table SYS_ROLE_AUTH_JOIN add constraint FK_SYS_ROLE_AUTH_REF_SYS_ROLE foreign key (ROLE_ID_)
      references SYS_ROLE (ROLE_ID_) on delete restrict on update restrict;

alter table SYS_ROLE_UGROUP_JOIN add constraint FK_SYS_ROLEUGROUP_REF_SYS_ROLE foreign key (ROLE_ID_)
      references SYS_ROLE (ROLE_ID_) on delete restrict on update restrict;

alter table SYS_ROLE_UGROUP_JOIN add constraint FK_Reference_18 foreign key (GROUP_ID_)
      references SYS_USER_GROUP (GROUP_ID_) on delete restrict on update restrict;

alter table SYS_UGROUP_AUTH_JOIN add constraint FK_Reference_23 foreign key (GROUP_ID_)
      references SYS_USER_GROUP (GROUP_ID_) on delete restrict on update restrict;

alter table SYS_UGROUP_AUTH_JOIN add constraint FK_Reference_24 foreign key (AUTH_ID_)
      references SYS_AUTH (AUTH_ID_) on delete restrict on update restrict;

alter table SYS_USER_AUTH_JOIN add constraint FK_SYS_USER_AUTH_REF_SYS_USER foreign key (USER_ID_)
      references SYS_USER (USER_ID_) on delete restrict on update restrict;

alter table SYS_USER_AUTH_JOIN add constraint FK_Reference_22 foreign key (AUTH_ID_)
      references SYS_AUTH (AUTH_ID_) on delete restrict on update restrict;

alter table SYS_USER_GROUP add constraint FK_Reference_13 foreign key (DEPART_ID_)
      references SYS_DEPARTMENT (DEPART_ID_) on delete restrict on update restrict;

alter table SYS_USER_LOG add constraint FK_SYS_USER_LOG_REF_SYS_USER foreign key (USER_ID_)
      references SYS_USER (USER_ID_) on delete restrict on update restrict;

alter table SYS_USER_ROLE_JOIN add constraint FK_SYS_USER_ROLE_REF_SYS_USER foreign key (USER_ID_)
      references SYS_USER (USER_ID_) on delete restrict on update restrict;

alter table SYS_USER_ROLE_JOIN add constraint FK_Reference_16 foreign key (ROLE_ID_)
      references SYS_ROLE (ROLE_ID_) on delete restrict on update restrict;

alter table SYS_USER_UGROUP_JOIN add constraint FK_SYS_USER_UG_REF_SYS_USER_G foreign key (GROUP_ID_)
      references SYS_USER_GROUP (GROUP_ID_) on delete restrict on update restrict;

alter table SYS_USER_UGROUP_JOIN add constraint FK_SYS_USER_UG_REF_SYS_USER foreign key (USER_ID_)
      references SYS_USER (USER_ID_) on delete restrict on update restrict;

alter table sys_notify add constraint FK_Reference_71 foreign key (USER_ID_)
      references sys_user (USER_ID_) on delete restrict on update restrict;

alter table sys_notify add constraint FK_Reference_81 foreign key (TEMP_ID_)
      references sys_notify_template (TEMP_ID_) on delete restrict on update restrict;
      
/*==============================================================*/
/* 存储过程                                                                                                                                                                   */
/*==============================================================*/  
DELIMITER $$

DROP PROCEDURE IF EXISTS `PROC_INIT_AUTH`$$

CREATE PROCEDURE `PROC_INIT_AUTH`()
BEGIN
DECLARE v_auth_id_ INT;
DECLARE LOOP_FLAG INT;
DECLARE csr CURSOR FOR SELECT auth_id_ FROM sys_auth;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET LOOP_FLAG=1;
delete from SYS_ROLE_AUTH_JOIN where ROLE_ID_=1;
OPEN csr;
LOOP_LABEL:LOOP
    FETCH csr INTO v_auth_id_;
    IF LOOP_FLAG=1 THEN
        LEAVE LOOP_LABEL;
    END IF;
    INSERT INTO SYS_ROLE_AUTH_JOIN (ROLE_ID_, AUTH_ID_, IS_FINAL_, CRT_TIME_, CRT_USERID_) 
      values(1,v_auth_id_,1,NOW(),1);
END LOOP;
CLOSE csr;
END$$

DELIMITER ;
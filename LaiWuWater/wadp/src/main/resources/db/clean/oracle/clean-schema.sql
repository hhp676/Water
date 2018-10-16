alter table SYS_AUTH
   drop constraint FK_SYS_AUTH_REFERENCE_SYS_AUTH;

   alter table SYS_AUTH
   drop constraint FK_SYS_AUTH_REFERENCE_SYS_AUTH;

alter table SYS_ROLE_AUTH_JOIN
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_AUTH;

alter table SYS_UGROUP_AUTH_JOIN
   drop constraint FK_SYS_UGRO_REFERENCE_SYS_AUTH;

alter table SYS_USER_AUTH_JOIN
   drop constraint FK_SYS_USER_REFERENCE_SYS_AUTH;

alter table SYS_DATADIC_ITEM
   drop constraint FK_SYS_DATA_REFERENCE_SYS_DATA;

alter table SYS_ROLE_AUTH_JOIN
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_AUTH;

alter table SYS_ROLE_AUTH_JOIN
   drop constraint FK_SYS_ROLE_AUTH_REF_SYS_ROLE;

alter table SYS_ROLE_UGROUP_JOIN
   drop constraint FK_SYS_ROLEUGROUP_REF_SYS_ROLE;

alter table SYS_ROLE_UGROUP_JOIN
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_USER;

alter table SYS_UGROUP_AUTH_JOIN
   drop constraint FK_SYS_UGRO_REFERENCE_SYS_USER;

alter table SYS_UGROUP_AUTH_JOIN
   drop constraint FK_SYS_UGRO_REFERENCE_SYS_AUTH;

alter table SYS_USER_AUTH_JOIN
   drop constraint FK_SYS_USER_AUTH_REF_SYS_USER;

alter table SYS_USER_AUTH_JOIN
   drop constraint FK_SYS_USER_REFERENCE_SYS_AUTH;

alter table SYS_USER_GROUP
   drop constraint FK_SYS_USER_REFERENCE_SYS_DEPA;

alter table SYS_USER_LOG
   drop constraint FK_SYS_USER_LOG_REF_SYS_USER;

alter table SYS_USER_ROLE_JOIN
   drop constraint FK_SYS_USER_ROLE_REF_SYS_USER;

alter table SYS_USER_ROLE_JOIN
   drop constraint FK_SYS_USER_REFERENCE_SYS_ROLE;

alter table SYS_USER_UGROUP_JOIN
   drop constraint FK_SYS_USER_UG_REF_SYS_USER_G;

alter table SYS_USER_UGROUP_JOIN
   drop constraint FK_SYS_USER_UG_REF_SYS_USER;

drop table SYS_AUTH cascade constraints;

drop table SYS_AUTH_OPER cascade constraints;

drop table SYS_DATADIC_GROUP cascade constraints;

drop table SYS_DATADIC_ITEM cascade constraints;

drop table SYS_DEPARTMENT cascade constraints;

drop table SYS_ROLE cascade constraints;

drop table SYS_ROLE_AUTH_JOIN cascade constraints;

drop table SYS_ROLE_UGROUP_JOIN cascade constraints;

drop table SYS_UGROUP_AUTH_JOIN cascade constraints;

drop table SYS_USER cascade constraints;

drop table SYS_USER_AUTH_JOIN cascade constraints;

drop table SYS_USER_GROUP cascade constraints;

drop table SYS_USER_LOG cascade constraints;

drop table SYS_USER_ROLE_JOIN cascade constraints;

drop table SYS_USER_UGROUP_JOIN cascade constraints;

drop table SYS_VARIABLE cascade constraints;

drop table SYS_CONFIG cascade constraints;


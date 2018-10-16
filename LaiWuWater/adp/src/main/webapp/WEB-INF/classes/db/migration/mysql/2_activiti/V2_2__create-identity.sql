/*==============================================================*/
/* Database: activiti 5.16                                      */
/*==============================================================*/
-- create table ACT_ID_GROUP (
--    ID_ varchar(64),
--    REV_ integer,
--    NAME_ varchar(255),
--    TYPE_ varchar(255),
--    primary key (ID_)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;
--
-- create table ACT_ID_MEMBERSHIP (
--    USER_ID_ varchar(64),
--    GROUP_ID_ varchar(64),
--    primary key (USER_ID_, GROUP_ID_)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;
--
-- create table ACT_ID_USER (
--    ID_ varchar(64),
--    REV_ integer,
--    FIRST_ varchar(255),
--    LAST_ varchar(255),
--    EMAIL_ varchar(255),
--    PWD_ varchar(255),
--    PICTURE_ID_ varchar(64),
--    primary key (ID_)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table ACT_ID_INFO (
    ID_ varchar(64),
    REV_ integer,
    USER_ID_ varchar(64),
    TYPE_ varchar(64),
    KEY_ varchar(255),
    VALUE_ varchar(255),
    PASSWORD_ LONGBLOB,
    PARENT_ID_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

-- alter table ACT_ID_MEMBERSHIP 
--    add constraint ACT_FK_MEMB_GROUP 
--    foreign key (GROUP_ID_) 
--    references ACT_ID_GROUP (ID_);
--
-- alter table ACT_ID_MEMBERSHIP 
--    add constraint ACT_FK_MEMB_USER 
--    foreign key (USER_ID_) 
--    references ACT_ID_USER (ID_);




/*==============================================================*/
/* 创建同名视图替换到activiti的用户相关表                                                                                                                      */
/*==============================================================*/
CREATE
    VIEW `act_id_user` 
    AS
(SELECT LOGIN_NAME_ AS ID_, '1' AS REV_, USER_NAME_ AS FIRST_, ENG_NAME_ AS LAST_, EMAIL_ AS EMAIL_, LOGIN_NAME_ AS PWD_, '1' AS PICTURE_ID_ FROM sys_user);

CREATE
    VIEW `act_id_group` 
    AS
(SELECT ROLE_CODE_ AS ID_, '1' AS REV_, ROLE_NAME_ AS NAME_, CASE DESCR_ when 'activiti-explorer-security' THEN 'security-role' else 'assignment' end AS TYPE_ FROM sys_role);

CREATE
    VIEW `act_id_membership` 
    AS
(SELECT su.LOGIN_NAME_ AS USER_ID_, sr.ROLE_CODE_ AS GROUP_ID_ FROM sys_user_role_join AS surj LEFT JOIN sys_user AS su ON surj.user_id_=su.user_id_ 
LEFT JOIN sys_role sr ON surj.role_id_=sr.role_id_ );
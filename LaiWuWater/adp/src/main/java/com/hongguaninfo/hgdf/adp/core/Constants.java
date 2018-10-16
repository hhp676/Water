package com.hongguaninfo.hgdf.adp.core;

import java.math.BigDecimal;

/**
 * 
 * @ClassName: Constants
 * @Description: 全局常量定义类
 * @author henry
 * @date 2014-2-19 上午11:24:11
 * 
 */

public class Constants {
    /**
     * web容器上下文路径名称
     */
    private static String contextPath = "";

    /**
     * 每页显示数
     */
    public static final int PAGE_SIZE = 100;

    /**
     * 是否删除 （0.有效）
     */
    public static int isDeleteVaild = 0;
    
    /**
     * 是否删除 （1.已删除）
     */
    public static int isDeleteDisVaild = 1;

    /**
     * config中的数据库ID存储key
     */
    public static final String NEXT_DB_ID = "next_db_id";
    
    /**
     * config中的验证码开关存储key
     */
    public static final String LOGIN_VERIFCODE = "login_verifcode";
    
    /**
     * config中的用户活跃超时时间
     */
    public static final String ACTIVE_TIMEOUT = "active_timeout";
    
    /**
     * config中的定期更换密码标识
     */
    public static final String UPDPASSWORD_SWITCH = "updPassqord_switch";
    
    /**
     * config中的密码定期更换周期(天)
     */
    public static final String UPDPASSWORD_CYCLE = "sysPasswordChangeCycle";
    
    /**
     * config中的密码不能与最近几次修改的相同-开关(0:禁用;1:启用)
     */
    public static final String UPDPASSWORD_NOTUSEDRECENT = "sysPwdChangeCanNotUsedRecent";
    
    /**
     * config中的密码不能与最近几次修改的相同-开关(1:启用)
     */
    public static final String UPDPASSWORD_NOTUSEDRECENT_USED = "1";
    
    /**
     * config中的密码不能与最近几次修改的相同-次数
     */
    public static final String UPDPASSWORD_NOTUSEDCOUNT = "sysPwdChangeCanNotUsedCount";
    
    /**
     * config中的登录时检查用户锁定状态-开关(0:禁用;1:启用)
     */
    public static final String LOGIN_CHECKUSERLOCK = "sysLoginCheckUserLock";
    
    /**
     * config中的登录自动锁定账号-开关(0:禁用;1:启用)：登录时，同一账号（或同账号同IP），
     * 在密码试错时间范围内，若密码尝试次数超限制，系统将自动锁定该账号（或该账号在该IP的状态）;
     * 在设置的自动解锁时间后，将自动解锁，恢复使用
     */
    public static final String LOGIN_USERLOCK = "sysLoginUserLock";
    
    /**
     * config中的登录自动锁定账号-方式(0:账号;1:账号IP)：
     * 0账号锁定:在设置的时间范围(min)内，某账号，无论使用什么ip登录，只要密码输入错误超上限，便锁定账号；锁定之后，所有ip下均不能登录;
     * 1账号IP锁定:在设置的时间范围(min)内，同账号同ip，若密码输入错误超上限，便锁定；锁定之后，该账号仅在该ip下不能登录
     */
    public static final String LOGIN_USERLOCKTYPE = "sysLoginUserLockType";
    
    /**
     * config中的登录自动锁定账号-密码试错时间范围(min)：设为0时，无时间限制，密码尝试次数累计达上限即可锁定
     */
    public static final String USERPASS_ATTEMPTSTIME_LIMIT = "sysUserPassAttemptsTimeLimit";
    
    /**
     * config中的登录自动锁定账号-密码尝试次数限制：在设置的次数内，仍未登录成功，则锁定
     */
    public static final String USERPASS_ATTEMPTSCOUNTS_LIMIT = "sysUserPassAttemptsCountsLimit";
    
    /**
     * config中的登录自动锁定账号-自动解锁时间(min)
     */
    public static final String USERLOCKING_TIME = "sysUserLockingTime";
    
    /**
     * 登录自动锁定账号-密码试错时间范围(min)：默认5min
     */
    public static final Integer SYS_USER_PASS_ATTEMPTS_TIME_LIMIT_DEFAULT   = 5;
    
    /**
     * 登录自动锁定账号-密码尝试次数限制：默认5次
     */
    public static final Integer SYS_USER_PASS_ATTEMPTS_COUNTS_LIMIT_DEFAULT = 5;
    
    /**
     * 登录自动锁定账号-自动解锁时间(min)：默认10min
     */
    public static final Integer SYS_USER_LOCKING_TIME_DEFAULT               = 10;
    
    /**
     * 登录自动锁定账号-默认策略(0:账号;1:账号IP)：
     */
    public static final Integer SYS_LOGIN_USER_LOCK_TYPE_DEFAULT            = 0;
    
    /**
     * 状态类型:0账号状态
     */
    public static final Integer TYPE_ACCOUNT                                = 0;
    
    /**
     * 状态类型:1账号IP状态
     */
    public static final Integer TYPE_ACCOUNT_IP                             = 1;
    
    /**
     * 系统自动：0
     */
    public static final Integer AUTO                                        = 0;
    
    /**
     * 手动配置：1
     */
    public static final Integer MANUAL                                      = 1;
    
    /**
     * 状态lock:1
     */
    public static final Integer LOCK                                        = 1;
    
    /**
     * 状态unlock:0
     */
    public static final Integer UNLOCK                                      = 0;
    
    /**
     * 当前状态:否：0
     */
    public static final Integer NOT_CURRENT                                        = 0;
    
    /**
     * 当前状态:是：1
     */
    public static final Integer IS_CURRENT                                      = 1;
    
    /**
     * 管理员编号
     */
    public static final Integer ADMIN_ID = 1;

    /**
     * 匿名用户编号
     */
    public static final Integer ANONYMOUS_ID = 0;

    /**
     * 匿名用户编号
     */
    public static final BigDecimal ROOT_MENU_ID = new BigDecimal(1);

    /**
     * 数值常量
     * */
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer TWO = 2;
    public static final Integer THREE = 3;
    public static final Integer FOUR = 4;
    public static final Integer FIVE = 5;
    public static final Integer SIX = 6;
    public static final Integer SEVEN = 7;
    public static final Integer EIGHT = 8;
    
    /**
     * 权限类型
     */
    public static final int AUTH_TYPE_MODULE = 1;
    
    /**
     * 用户日志类型
     * */
    public static final String BUSINESS = "操作日志";
    public static final String OPERATION = "业务日志";
    
    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPath) {
        Constants.contextPath = contextPath;
    }
    
    /**
     * 流程key定义
     */
    public static final String PROCESS_KEY_RECRUIT = "recruitProcess"; //招聘流程
    public static final String PROCESS_KEY_DIARY = "diaryProcess"; //日报流程
    
    /**
     * task定义
     */
    public static final String TASK_KEY_RECRUIT_DEPT_LEADER_AUDIT = "deptLeaderAudit"; //部门经理审批
    public static final String TASK_KEY_RECRUIT_ADJUST_RECRUIT = "adjustRecruit"; //调整招聘需求
    public static final String TASK_KEY_RECRUIT_HR_LEADER_AUDIT = "hrLeaderAudit"; //人事经理审批
    public static final String TASK_KEY_RECRUIT_HR_SUBMIT_INFO = "hrSubmitInfo"; //人事专员提交招聘情况
    public static final String TASK_KEY_RECRUIT_HR_LEADER_COMMAND = "hrLeaderCommand"; //人事经理批示
    
    public static final String TASK_KEY_DIARY_STAFF_FILLOUT = "staffFillout";//填写日报
    public static final String TASK_KEY_DIARY_LEADER_APPROVAL = "leaderApproval"; //领导审批
    public static final String TASK_KEY_DIARY_STAFF_REFILLOUT = "staffReFillout"; //重新调谐日报
    
    /**
     * 流程变量
     */  
    public static final String PROCESS_VARIABLE_STAFF = "staff";
    
    public static final String PROCESS_VARIABLE_BDEPTLEADERPASS = "bDeptLeaderPass";
    public static final String PROCESS_VARIABLE_BREAPPLY = "bReApply";
    public static final String PROCESS_VARIABLE_BHRLEADERPASS = "bHrLeaderPass";
    public static final String PROCESS_VARIABLE_BRECRUITFINISH = "bRecruitFinish";
    public static final String PROCESS_VARIABLE_BRECRUITCONTINUE = "bRecruitContinue";
    
    public static final String PROCESS_VARIABLE_PROJECTID = "projectId";
    public static final String PROCESS_VARIABLE_BLEADERAPPROVALPASS = "bLeaderApprovalPass";
       
    /*用户发起人变量*/
    public static final String PROCESS_VARIABLE_INITIATOR = "initiator";
    
    /**
     * 用户信息是否强制脱敏    0-不脱敏 1-普通界别 2-全部脱敏
     */
    public static final String USER_INFO_STAUTS_ZERO_LEVEL ="0";
    public static final String USER_INFO_STAUTS_ONE_LEVEL ="1";
    public static final String USER_INFO_STAUTS_TWO_LEVEL ="2";
    
    public static final String SYS_SECURITY_LEVEL="sysSecurityLevel";
    
 //密码变更记录表
    
    /**
     * 密码修改原因：0默认普通修改
     */
    public static final Integer PWD_CHANGE_REASON_DEFAULT = 0;
    
    /**
     * 密码修改原因：1过期修改
     */
    public static final Integer PWD_CHANGE_REASON_EXPIRED = 1;
    
    /**
     * 密码修改原因：2用户管理修改
     */
    public static final Integer PWD_CHANGE_REASON_ADMIN = 2;
    
    /**
     * 密码修改原因：3忘记密码找回-修改密码
     */
    public static final Integer PWD_CHANGE_REASON_FORGET = 3;
    
    /**
     * 密码修改原因：4新用户修改初始密码
     */
    public static final Integer PWD_CHANGE_REASON_INITIAL = 4;
    
    /**
     * 登陆成功
     */
    public static final Integer SUCCESS = 1;
    
    /**
     * 登录失败
     */
    public static final Integer FALIUER = 0;
    
    /**
     * 累计登陆次数
     */
    public static final Integer IS_ACCUMULATE_LOGIN_TIMES_ = 1;
    
    /**
     * 不累计登陆次数
     */
    public static final Integer NOT_ACCUMULATE_LOGIN_TIMES_ = 0;
    
    /**
     * 系统元方法日志级别-开关名称
     */
    public static final String  SYSMETAMETHOD_LOG_LEVEL = "sysMetaMethod_logLevel";
    
    /**
     * 系统元方法日志级别-字典组名称
     */
    public static final String  DICGROUP_SYSMETAMETHOD_LOG_LEVEL = "SYSMETAMETHOD_LOG_LEVEL";
    
    /**
     * 系统元方法日志类型-字典组名称
     */
    public static final String  DICGROUP_SYSMETAMETHOD_LOG_TYPE = "SYSMETAMETHOD_LOG_TYPE";
    
    /**
     * 系统元方法参数类型名分隔符。
     */
    public static final String  SYSMETAMETHOD_ARGTYPES_NAME_SEPARATOR = ",";
    
    /**
     * 系统元方法数据生成-排除含$的方法。
     */
    public static final String  SYSMETAMETHOD_NO_INIT_CONTAIN = "$";
    
    /**
     * 系统元方法通用分隔符,
     */
    public static final String SYSMETAMETHOD_SEPARATOR = ",";
    
    /**
     * 包名分隔符。	
     */
    public static final String PACKAGE_SEPARATOR = ".";
}

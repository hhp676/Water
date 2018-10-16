<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="navbar-header pull-left">
    <a href="${ctx}" class="navbar-brand"><small><i class="icon-leaf"></i> 应用管理平台</small> </a><!-- /.brand -->
</div><!-- /.navbar-header -->

<div class="navbar-header pull-right" role="navigation">
    <ul class="nav ace-nav">
        <li class="grey">
            <a  href="#"><i class="icon-tasks"></i> <span class="badge badge-grey">0</span></a>
        </li>

        <li class="purple">
            <a  href="#"><i class="icon-bell-alt"></i> <span class="badge badge-important">0</span></a>
        </li>
        <li class="green">
            <a  href="#"><i class="icon-envelope"></i> <span class="badge badge-success">0</span></a>
        </li>
        <li class="">
            <a  href="#"><i class="icon-inbox"></i> <span class="badge ">0</span></a>
        </li>
        <li class="light-blue">
            <a data-toggle="dropdown" href="#" class="dropdown-toggle"> <!-- <img class="nav-user-photo" src="/smeoa/Data/Files/emp_pic/1.jpeg?iCbIzJ"> --> <span class="user-info"> 管理员 </span> <i class="icon-caret-down"></i> </a>
            <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                <li>
                    <a href="#"> <i class="icon-user"></i> 用户资料 </a>
                </li>
                <li>
                    <a href="#"><i class="icon-lock"></i> 修改密码</a>
                </li>
                <li>
                    <a href="#"> <i class="icon-cog"></i> 用户设置 </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="${ctx}/logout" id="loginOut"> <i class="icon-off"></i> 安全退出 </a>
                </li>
            </ul>
        </li>
    </ul><!-- /.ace-nav -->
</div><!-- /.navbar-header -->

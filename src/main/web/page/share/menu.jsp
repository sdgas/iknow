<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 14-3-20
  Time: 下午5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/page/share/taglib.jsp" %>

<div id="head_line"></div>
<div id="head" style="width: 1050px">

    <a href="index.jsp" style="border: none;">
        <img src="<%=basePath%>img/logo.bmp" style="border: none;margin-right: 10px;width: 150px;height: 80px;"/>
    </a>

    <div style="width:400px;margin-top: 20px;margin-bottom: 0px;margin-left:340px;text-align: right">
        <c:if test="${session.person == null}">
            <%--<span style="font-size:15px; font-family:'微软雅黑'; color: #000000; position: absolute;top: 3px;right: 22%;">--%>
			<span style="font-size:15px; font-family:'微软雅黑'; color: #000000;">
				&nbsp&nbsp你好！&nbsp&nbsp <a href="<%=basePath%>login.jsp">登陆</a>
			</span>
        </c:if>
        <c:if test="${session.person != null}">
            <%--<span style="font-family:'微软雅黑'; color: #000000; position: absolute;top: 3px;right: 18%;">欢迎 ${person.position.positionName}&nbsp&nbsp${person.userName}&nbsp--%>
			<span style="font-family:'微软雅黑'; color: #000000;">欢迎
				${person.userName}&nbsp; <a
                        href="<%=basePath%>page/user/alterPwd.jsp">修改密码</a> <a
                        href="Login!loginOut.action">退出</a>
			</span>
        </c:if>
    </div>

    <%--导航菜单开始--%>
    <%--按钮1--%>
    <div class="btn-group" style="margin-top: 10px;">
        <%--btn-primary 颜色--%>
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            模板管理 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/template/addTemplate.jsp">新增模板</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="Template!findTemplateByField.action?msg=&used=1">模板查询</a>
            </li>
            <li class="divider"></li>
        </ul>
    </div>
    <%--按钮2--%>
    <div class="btn-group" style="margin-top: 10px;">
        <%--btn-primary 颜色--%>
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            公告栏 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/notice/addNotice.jsp"> 公告栏信息录入 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="Notice!findNoticeByField.action?msg=&noticeType="> 公告栏 </a>
            </li>
        </ul>
    </div>
    <%--按钮3--%>
    <div class="btn-group" style="margin-top: 10px;">
        <%--btn-primary 颜色--%>
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            楼宇收费标准 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/building/addInfo.jsp"> 新增楼宇收费标准 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="BuildingInfo!findBuildingInfoByField.action?msg="> 楼宇收费标准 </a>
            </li>
        </ul>
    </div>

    <%--按钮4--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            燃气具相关 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/furnace/addInfo.jsp"> 新增产品信息 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="ProductInfo!findProductByField.action?msg=">产品信息</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<%=basePath%>page/promotion/addInfo.jsp"> 新增促销活动 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="Promotion!findPromotionByField.action?promotion=">促销活动</a>
            </li>
        </ul>
    </div>

    <%--按钮5--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            相关文件(政府文件）<span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/gov/addInfo.jsp"> 新增政府文件 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="GovDoc!findByField.action?subject="> 政府文件 </a>
            </li>
        </ul>
    </div>

    <%--按钮56--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            户内安装收费标准 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/message/report.jsp">户内安装收费标准</a>
            </li>
        </ul>
    </div>

    <%--按钮57--%>
    <div class="btn-group" style="margin-top: 10px;">
        <button type="button" class="btn btn-default dropdown-toggle btn-primary" data-toggle="dropdown">
            燃气知识 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="<%=basePath%>page/message/standard.jsp">户内安装标准</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<%=basePath%>page/knowledge/addInfo.jsp"> 新增安检、隐患整改知识 </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="Knowledge!findByField.action?msg=&subject="> 安检、隐患整改知识 </a>
            </li>
        </ul>
    </div>
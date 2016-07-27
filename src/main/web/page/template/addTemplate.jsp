<%--
  Created by IntelliJ IDEA.
  User: 120378
  Date: 2014/7/25
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/page/share/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>新增模板</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="content－Type" content="text/html;charset=UTF-8">
    <meta http-equiv="window-target" content="_top">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>

    <link rel="stylesheet" href="<%=basePath%>css/base2.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery.pstrength.js"></script>
    <style type="text/css">
        tr {
            text-align: center;
        }

        td {
            width: 150px;
        }

        #content {
            margin-left: 350px;
        }
    </style>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="Template.action" method="post">
        <input type="hidden" value="${person.userName}" name="creator">
        <table>
            <tr>
                <td colspan="2" align="center">
                    <span style="font-size: x-large">新增信息模板</span>
                </td>
            </tr>
            <tr>
                <td>模板主题：</td>
                <td>
                    <input type="text" name="subject">
                </td>
            </tr>
            <tr>
                <td>模板内容：</td>
                <td>
                    <textarea rows="4" cols="50" name="content"></textarea>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="新增">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>

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
    <title>公告栏</title>
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
    </style>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="Notice.action" method="post">
        <input type="hidden" value="${person.userName}" name="creator">
        <table>
            <tr>
                <td colspan="2" align="center">
                    <span style="font-size: x-large">新增公告栏信息</span>
                </td>
            </tr>
            <tr>
                <td>公告类别：</td>
                <td align="left">
                        <select name="noticeType" class="input">
                            <option value="">--请选择--</option>
                            <option value="停气">停气通知</option>
                            <option value="报装及通气">报装及通气通知</option>
                            <option value="报装">报装通知</option>
                            <option value="充值退款">充值退款通知</option>
                            <option value="抢险报告">抢险报告注意事项</option>
                            <option value="市场部通知">市场部通知</option>
                            <option value="通气">通气通知</option>
                            <option value="温馨提醒">温馨提醒</option>
                            <option value="其他">其他</option>
                        </select>
                </td>
            </tr>
            <tr>
                <td>相关组别：</td>
                <td align="left">
                    <input type="text" name="noticeGroup">
                </td>
            </tr>
            <tr>
                <td>公告内容：</td>
                <td align="left">
                    <textarea rows="4" cols="50" name="noticeContent"></textarea>
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

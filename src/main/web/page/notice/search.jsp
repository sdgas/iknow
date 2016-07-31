<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/page/share/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>公告综合查询</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="content－Type" content="text/html;charset=UTF-8">
    <meta http-equiv="window-target" content="_top">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>

    <link rel="stylesheet" href="<%=basePath%>/css/base2.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=basePath%>/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">

    <script type="text/javascript">

        function topage(page) {
            var form = document.getElementById("myform");
            document.getElementById("page").setAttribute("value", page);
            form.submit();
        }

        function changebg(obj, cl) {
            var bgcolor;
            if (cl == "0") {
                bgcolor = "#E2EDFC";
            } else {
                bgcolor = "#F8FBFE";
            }
            obj.style.backgroundColor = bgcolor;
        }
    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="Notice!findNoticeByField.action" method="post">
        <div class="form-group" align="center">
            <table>
                <tr align="center">
                    <td>关键字</td>
                    <td>
                        <input type="text" name="msg">
                    </td>
                    <td>公告类别：</td>
                    <td>
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
                    <td>
                        <button type="submit" class="btn btn-default">查询</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <form action="Notice!findNoticeByField.action" method="post" id="myform">
        <input type="hidden" name="page" value="1" id="page"/>
        <input type="hidden" name="msg" value="${msg}">
        <input type="hidden" name="noticeType" value="${noticeType}">
        <table
                style="border: 1px #FFFFFF solid;margin: 20px auto 20px;opacity:0.9;font-family: '微软雅黑',serif;width:1000px;text-align: center;">
            <thead align="center">
            <tr>
                <td>
                    <h2>序 号</h2>
                </td>
                <td>
                    <h2>类 别</h2>
                </td>
                <td>
                    <h2>内 容</h2>
                </td>
                <td>
                    <h2>相关组别</h2>
                </td>
                <td>
                    <h2>公告人</h2>
                </td>
                <td>
                    <h2>公告时间</h2>
                </td>
            </tr>
            </thead>
            <s:iterator value="pageView.records" var="notice" status="n">
                <tr style="background-color: #F8FBFE"
                    onmousemove="changebg(this,0)" onmouseout="changebg(this,1)">
                    <td>
                            ${n.count}
                    </td>
                    <td>
                            ${notice.noticeType}
                    </td>
                    <td>
                        ${notice.noticeContent}
                    </td>
                    <td>
                            ${notice.noticeGroup}
                    </td>
                    <td>
                            ${notice.creator}
                    </td>
                    <td>
                        <fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd"/>
                    </td>
                </tr>
            </s:iterator>
        </table>
        <table align="center"
               style="font-family: '微软雅黑',serif;text-align: center">
            <tr>
                <td colspan="4" bgcolor="#114a93" align="right"
                    style="padding-right: 5px;height: 20px;">
                    <%@ include
                            file="/page/share/fenye.jsp" %>
                </td>
            </tr>
        </table>
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>

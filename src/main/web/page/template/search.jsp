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
    <title>模板综合查询</title>
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
    <form action="Template!findTemplateByField.action" method="post">
        <div class="form-group" align="center">
            <table>
                <tr align="center">
                    <td>关键字:</td>
                    <td>
                        <input type="text" name="msg">
                    </td>
                    <td>是否在用:</td>
                    <td>
                        <input type="radio" name="used" value="1" checked="checked">是
                        <input type="radio" name="used" value="0">否
                    </td>
                    <td>
                        <button type="submit" class="btn btn-default">查询</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <form action="Template!findTemplateByField.action" method="post" id="myform">
        <input type="hidden" name="page" value="1" id="page"/>
        <input type="hidden" name="msg" value="${msg}">
        <input type="hidden" name="used" value="${used}">
        <table
                style="border: 1px #FFFFFF solid;margin: 20px auto 20px;opacity:0.9;font-family: '微软雅黑',serif;width:1000px;text-align: center;">
            <thead align="center">
            <tr>
                <td>
                    <h2>序号</h2>
                </td>
                <td>
                    <h2>创建时间</h2>
                </td>
                <td>
                    <h2>模板主题</h2>
                </td>
                <td>
                    <h2>模板内容</h2>
                </td>
            </tr>
            </thead>
            <s:iterator value="pageView.records" var="template" status="t">
                <tr style="background-color: #F8FBFE"
                    onmousemove="changebg(this,0)" onmouseout="changebg(this,1)">
                    <td>
                            ${t.count}
                    </td>
                    <td>

                            <fmt:formatDate value="${template.createTime}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                            ${template.subject}
                    </td>
                    <td>
                            ${template.content}
                    </td>
                    <td>
                        <fmt:formatDate value="${contract.signContractDate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                            ${contract.contractOperator}
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

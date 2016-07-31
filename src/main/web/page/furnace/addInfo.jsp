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
    <title>燃气具产品信息</title>
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
    <script type="text/javascript">

        //获取目录对应格式
        function verifyFileName() {
            //解决中文乱码问题的方法
            var uploadFile = document.forms[0].uploadFile.value;
            document.forms[0].fileName.value = uploadFile;
            if ("" != uploadFile) {
                uploadFile = uploadFile.substring(uploadFile.lastIndexOf(".") + 1)
                        .toLowerCase();
                if (uploadFile != "jpg" && uploadFile != "png") {
                    document.forms[0].fileName.value = "";
                    alert("请上传jpg文件或png文件");
                }
            }
        }
    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="ProductInfo.action" method="post" enctype="multipart/form-data">>
        <table>
            <tr>
                <td colspan="2" align="center">
                    <span style="font-size: x-large">新增燃气具产品信息</span>
                </td>
            </tr>
            <tr>
                <td>燃气具型号：</td>
                <td align="left">
                        <input name="modelNumber" type="text">
                </td>
            </tr>
            <tr>
                <td>燃气具介绍：</td>
                <td align="left">
                    <textarea rows="4" cols="50" name="information"></textarea>
                </td>
            </tr>
            <tr>
                <td>样式图片：</td>
                <td align="left">
                    <input type="hidden" name="fileName">
                    <input type="file" name="uploadFile" size="14"
                           onchange="verifyFileName()">
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
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

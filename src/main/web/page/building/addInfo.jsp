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
    String realPath = request.getSession().getServletContext().getRealPath("/");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>楼宇收费标准</title>
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
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery.pstrength.js"></script>
    <style type="text/css">
        /*修改进度条长度*/
        .uploadify-queue {
            width: 300px;
        }

        .mergeTable {
            width: 100%;
            border: 1px solid;
            border-collapse: collapse;
            table-layout: fixed;
        }

        .mergeTable th, .mergeTable td {
            border: 1px solid;
            text-align: center;
        }
    </style>
    <script type="text/javascript">

        //获取目录对应格式
        function verifyFileName() {
            //解决中文乱码问题的方法
            var uploadFile = document.forms[0].uploadFile.value;
            document.forms[0].fileName.value = uploadFile;
            if ("" != uploadFile) {
                uploadFile = uploadFile.substring(uploadFile.lastIndexOf(".") + 1)
                        .toLowerCase();
                if (uploadFile != "xls" && uploadFile != "xlsx" && uploadFile != "pdf" ) {
                    document.forms[0].fileName.value = "";
                    alert("请上传excel文件或pdf文件");
                }
            }
        }
    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="BuildingInfo.action" method="post" enctype="multipart/form-data">
        <table class="mergeTable">
            <tr>
                <td colspan="4" align="center">
                    <span style="font-size: x-large">新增楼宇收费标准信息</span>
                </td>
            </tr>
            <tr>
                <td>地 点：</td>
                <td align="left">
                    <input name="street" type="text">
                </td>
                <td>楼宇名称：</td>
                <td align="left">
                    <input name="buildingName" type="text">
                </td>
            </tr>
            <tr>
                <td>地 址：</td>
                <td align="left">
                    <input name="address" type="text">
                </td>
                <td>新建项目编号：</td>
                <td align="left">
                    <input name="projectId" type="text">
                </td>
            </tr>
            <tr>
                <td>项目速记码：</td>
                <td align="left">
                    <input name="shorthandCode" type="text">
                </td>
                <td>合同签订户数：</td>
                <td align="left">
                    <input name="userNum" type="text">
                </td>
            </tr>
            <tr>
                <td>实际用气户数：</td>
                <td align="left">
                    <input name="actualUserNum" type="text">
                </td>
                <td>施工单位：</td>
                <td align="left">
                    <input name="constructionOrganization" type="text">
                </td>
            </tr>
            <tr>
                <td>气表安装：</td>
                <td align="left">
                    <input name="location" type="text">
                </td>
                <td>燃气表品牌及类别：</td>
                <td align="left">
                    <input name="meterType" type="text">
                </td>
            </tr>
            <tr>
                <td>气源类型：</td>
                <td align="left">
                    <input name="gasType" type="text">
                </td>
                <td>供气合同编号：</td>
                <td align="left">
                    <input name="contractId" type="text">
                </td>
            </tr>
            <tr>
                <td>合同签订时间：</td>
                <td align="left">
                    <input type="text" name="signatureDate" class="Wdate"
                           onclick="WdatePicker({skin:'whyGreen'})">
                </td>
                <td>庭院地管：</td>
                <td align="left">
                    <input name="courtyard" type="text">
                </td>
            </tr>
            <tr>
                <td>立管：</td>
                <td align="left">
                    <input name="standPipe" type="text">
                </td>
                <td>户内用气点(个)：</td>
                <td align="left">
                    <input name="gasPoints" type="text">
                </td>
            </tr>
            <tr>
                <td>户内已装管道设施情况（地管、户内镀锌管、阀门等）：</td>
                <td align="left">
                    <input name="installedInfo" type="text">
                </td>
                <td>15A总阀：</td>
                <td align="left">
                    <input name="mainValve" type="text">
                </td>
            </tr>
            <tr>
                <td>15C嘴阀(个)：</td>
                <td align="left">
                    <input name="valve" type="text">
                </td>
                <td>DN15镀锌管(米)：</td>
                <td align="left">
                    <input name="dn15" type="text">
                </td>
            </tr>
            <tr>
                <td>其它/我司需安装内容：</td>
                <td align="left">
                    <input name="others" type="text">
                </td>
                <td>容量气价：</td>
                <td align="left">
                    <input name="capacityGasPrice" type="text">
                </td>
            </tr>
            <tr>
                <td>合同约定的其它收费项目：</td>
                <td align="left">
                    <input name="otherPrice" type="text">
                </td>
                <td>报装时间：</td>
                <td align="left">
                    <input type="text" name="installationTime" class="Wdate"
                           onclick="WdatePicker({skin:'whyGreen'})">
                </td>
            </tr>
            <tr>
                <td>小区通气情况：</td>
                <td align="left">
                    <input name="useGas" type="text">
                </td>
                <td>项目跟进业务：</td>
                <td align="left">
                    <input name="followed" type="text">
                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td align="left" colspan="3">
                    <textarea name="remark" cols="65" rows="4"></textarea>
                </td>
            </tr>
            <tr>
                <td>楼宇户内安装明细：</td>
                <td colspan="4">
                    <input type="hidden" name="fileName">
                    <input type="file" name="uploadFile" size="16"
                           onchange="verifyFileName()">
                </td>
            </tr>
            <tr>
                <td colspan="4">
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

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
    <title>楼宇收费标准</title>
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
    <style type="text/css">
        #content {
            width: 1200px;
            margin: 0 0 0 0;
        }
    </style>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="BuildingInfo!findBuildingInfoByField.action" method="post">
        <div class="form-group" align="center">
            <table>
                <tr align="center">
                    <td>楼宇名称:</td>
                    <td>
                        <input type="text" name="msg">
                    </td>
                    <td>
                        <button type="submit" class="btn btn-default">查询</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <form action="BuildingInfo!findBuildingInfoByField.action" method="post" id="myform">
        <input type="hidden" name="page" value="1" id="page"/>
        <input type="hidden" name="msg" value="${msg}">
        <table
                style="border: 1px #FFFFFF solid;margin: 20px auto 20px;opacity:0.9;font-family: '微软雅黑',serif;width:100%;text-align: center;">
            <thead align="center">
            <tr>
                <td>
                    <h2>序号</h2>
                </td>
                <td>
                    <h2>地点</h2>
                </td>
                <td>
                    <h2>楼宇名称</h2>
                </td>
                <td>
                    <h2>地址</h2>
                </td>
                <td>
                    <h2>新建项目编号</h2>
                </td>
                <td>
                    <h2>项目速记码</h2>
                </td>
                <td>
                    <h2>合同签订户数</h2>
                </td>
                <td>
                    <h2>实际用气户数</h2>
                </td>
                <td>
                    <h2>施工单位</h2>
                </td>
                <td>
                    <h2>气表安装</h2>
                </td>
                <td>
                    <h2>燃气表品牌及类别</h2>
                </td>
                <td>
                    <h2>气源类型</h2>
                </td>
                <td>
                    <h2>供气合同编号</h2>
                </td>
                <td>
                    <h2>合同签订时间</h2>
                </td>
                <td>
                    <h2>庭院地管</h2>
                </td>
                <td>
                    <h2>立管</h2>
                </td>
                <td>
                    <h2>户内用气点(个)</h2>
                </td>
                <td>
                    <h2>户内已装管道设施情况（地管、户内镀锌管、阀门等）</h2>
                </td>
                <td>
                    <h2>15A总阀</h2>
                </td>
                <td>
                    <h2>15C嘴阀(个)</h2>
                </td>
                <td>
                    <h2>DN15镀锌管(米)</h2>
                </td>
                <td>
                    <h2>其它/我司需安装内容</h2>
                </td>
                <td>
                    <h2>容量气价</h2>
                </td>
                <td>
                    <h2>合同约定的其它收费项目</h2>
                </td>
                <td>
                    <h2>报装时间</h2>
                </td>
                <td>
                    <h2>小区通气情况</h2>
                </td>
                <td>
                    <h2>项目跟进业务</h2>
                </td>
                <td>
                    <h2>备注</h2>
                </td>
                <td>
                    <h2>户内安装明细</h2>
                </td>
            </tr>
            </thead>
            <s:iterator value="pageView.records" var="building" status="b">
                <tr style="background-color: #F8FBFE"
                    onmousemove="changebg(this,0)" onmouseout="changebg(this,1)">
                    <td>
                            ${b.count}
                    </td>
                    <td>
                            ${building.street}
                    </td>
                    <td>
                            ${building.buildingName}
                    </td>
                    <td>
                            ${building.address}
                    </td>
                    <td>
                            ${building.projectId}
                    </td>
                    <td>
                            ${building.shorthandCode}
                    </td>
                    <td>
                            ${building.userNum}
                    </td>
                    <td>
                            ${building.actualUserNum}
                    </td>
                    <td>
                            ${building.constructionOrganization}
                    </td>
                    <td>
                            ${building.location}
                    </td>
                    <td>
                            ${building.meterType}
                    </td>
                    <td>
                            ${building.gasType}
                    </td>
                    <td>
                            ${building.contractId}
                    </td>
                    <td>

                        <fmt:formatDate value="${building.signatureDate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                            ${building.courtyard}
                    </td>
                    <td>
                            ${building.standPipe}
                    </td>
                    <td>
                            ${building.gasPoints}
                    </td>
                    <td>
                            ${building.installedInfo}
                    </td>
                    <td>
                            ${building.mainValve}
                    </td>
                    <td>
                            ${building.valve}
                    </td>
                    <td>
                            ${building.dn15}
                    </td>
                    <td>
                            ${building.others}
                    </td>
                    <td>
                            ${building.capacityGasPrice}
                    </td>
                    <td>
                            ${building.otherPrice}
                    </td>
                    <td>
                        <fmt:formatDate value="${building.installationTime}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                            ${building.useGas}
                    </td>
                    <td>
                            ${building.followed}
                    </td>
                    <td>
                            ${building.remark}
                    </td>
                    <td>
                           <a href="FileDownload.action?fileName=${building.file}">${building.file}</a>
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
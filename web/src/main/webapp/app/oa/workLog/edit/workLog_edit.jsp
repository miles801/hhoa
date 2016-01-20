<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>编辑工作日志</title>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
    <style>
        .c {
            width: 1000px;
            margin: 0 auto;
            text-align: center;
        }

        .row {
            margin-top: 8px;
        }

        .wrap {
            border: 1px solid #dcdcdc;
            padding: 20px;
            margin-top: 30px;
        }

        h3 {
            color: #444;
        }

        .button-row {
            margin-top: 25px;
        }

        textarea {
            padding: 10px 15px;
        }
    </style>
</head>
<body>
<div class="main" ng-app="oa.workLog.edit" ng-controller="Ctrl" style="overflow: auto;">
    <div class="c">
        <form name="form" class="form-horizontal" role="form">
            <div style="display: none;">
                <input type="hidden" id="pageType" value="${pageType}"/>
                <input type="hidden" id="id" value="${id}"/>
            </div>
            <h3>工作日志</h3>
            <div class="wrap">
                <div class="row">
                    <div class="form-label col-3">
                        <label>日志内容：</label>
                    </div>
                    <textarea class="col-7-half" rows="10" name="content"
                              ng-model="beans.content" validate validate-required validate-max-length="2000"></textarea>
                </div>
                <div class="button-row">
                    <button type="button" class="btn btn-green btn-min" ng-click="save()"
                            ng-disabled="!form.$valid">
                        <span class="glyphicons disk_save"></span> 保存
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/workLog/workLog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/workLog/edit/workLog_edit.js"></script>
</html>
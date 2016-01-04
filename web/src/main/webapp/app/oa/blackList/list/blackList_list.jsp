<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>黑户</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body>
<div class="main condition-row-1" ng-app="oa.blackList.list" ng-controller="Ctrl">
    <div class="list-condition">
        <div class="block">
            <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons search"></span>
                </span>
                <span class="header-button">
                    <button type="button" class="btn btn-green btn-min" ng-click="">
                        <span class="glyphicons search"></span>
                        查询
                    </button>
                    <button type="button" class="btn btn-green btn-min" ng-click="">
                        <span class="glyphicons repeat"></span>
                        重置
                    </button>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>客户名称:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.name"/>

                        <div class="form-label col-1-half">
                            <label>客户类型:</label>
                        </div>
                        <select ng-model="beans.type" class="col-2-half"
                                ng-options="foo.value as foo.name for foo in x">
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="list-result ">
        <div class="block">
            <div class="block-header">
                <div class="header-text">
                    <span class="glyphicons list"></span>
                    <span>黑户</span>
                </div>
                <span class="header-button">
                        <a type="button" class="btn btn-green btn-min" ng-click="add()">
                            <span class="glyphicons plus"></span>
                            新建
                        </a>
                        <button type="button" class="btn btn-green btn-min" ng-click="">
                            <span class="glyphicons remove"></span>
                            删除
                        </button>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="table-responsive panel panel-table">
                        <table class="table table-striped table-hover">
                            <thead class="table-header">
                            <tr>
                                <td class="width-min">
                                    <input type="checkbox" style="height: 12px;" ng-model="checkAll"/>
                                </td>
                                <td>客户名称</td>
                                <td>客户类型</td>
                                <td>客户信息</td>
                                <td>原因</td>
                                <td>关键字</td>
                                <td>状态</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="8" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr ng-repeat="foo in beans.data" ng-cloak>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="list-pagination" eccrm-page="pager"></div>
</div>

</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/blackList/blackList.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/blackList/list/blackList_list.js"></script>
</html>
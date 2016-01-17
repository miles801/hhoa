<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>模块</title>
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
<div class="main condition-row-1" ng-app="oa.module.list" ng-controller="Ctrl">
    <div class="list-condition">
        <div class="block">
            <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons search"></span>
                </span>
                <span class="header-button">
                        <button type="button" class="btn btn-green btn-min" ng-click="query();">
                            <span class="glyphicons search"></span>
                            查询
                        </button>
                        <button type="button" class="btn btn-green btn-min" ng-click="reset();">
                            <span class="glyphicons repeat"></span>
                            重置
                        </button>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>模块名称:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="condition.name"/>

                        <div class="form-label col-1-half">
                            <label>模块类型:</label>
                        </div>
                        <select ng-model="condition.type" class="col-2-half"
                                ng-options="foo.value as foo.name for foo in types">
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
                    <span>模块</span>
                </div>
                <span class="header-button">
                        <a type="button" class="btn btn-green btn-min" ng-click="add();">
                            <span class="glyphicons plus"></span>
                            新建
                        </a>
                        <button type="button" class="btn btn-green btn-min" ng-click="remove();" ng-disabled="!anyone">
                            <span class="glyphicons remove"></span>
                            删除
                        </button>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="table-responsive panel panel-table">
                        <table class="table table-striped table-hover text-center">
                            <thead class="table-header">
                            <tr>
                                <td class="width-min">
                                    <div select-all-checkbox checkboxes="beans.data" selected-items="items"
                                         anyone-selected="anyone"></div>
                                </td>
                                <td style="width: 40px;">LOGO</td>
                                <td>模块名称</td>
                                <td>模块类型</td>
                                <td style="width: 40px;">序号</td>
                                <td>负责人</td>
                                <td>简介</td>
                                <td>描述</td>
                                <td>状态</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="10" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td><input type="checkbox" ng-model="foo.isSelected"/></td>
                                <td style="width: 40px;">
                                    <img ng-src="<%=contextPath%>/attachment/view?id={{foo.logo}}" alt="LOGO" width="40" height="40">
                                </td>
                                <td bo-text="foo.name"></td>
                                <td bo-text="foo.typeName"></td>
                                <td bo-text="foo.sequenceNo"></td>
                                <td bo-text="foo.ownerName"></td>
                                <td bo-text="foo.summary|substr:40"></td>
                                <td bo-text="foo.description|substr:20"></td>
                                <td bo-text="foo.statusName"></td>
                                <td>
                                    <a class="cp" title="编辑" ng-click="modify(foo.id);">
                                        <i class="icons edit"></i>
                                    </a>
                                    <a class="cp" title="删除" ng-click="remove(foo.id);">
                                        <i class="icons fork"></i>
                                    </a>
                                </td>
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
<script type="text/javascript" src="<%=contextPath%>/app/oa/module/module.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/module/list/module_list.js"></script>
</html>
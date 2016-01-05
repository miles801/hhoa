<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>知识</title>
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
<div class="main condition-row-2" ng-app="oa.knowledge.list" ng-controller="Ctrl">
    <div class="dn">
        <input type="hidden" id="type" value="${type}">
    </div>
    <div class="list-condition">
        <div class="block">
            <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons search"></span>
                </span>
                <span class="header-button">
                    <button class="btn btn-green btn-min" ng-click="query()">
                        <span class="glyphicons search"></span>
                        查询
                    </button>
                    <button type="button" class="btn btn-green btn-min" ng-href="reset()">
                        <span class="glyphicons repeat"></span>
                        重置
                    </button>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>标题:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="condition.title"/>

                        <div class="form-label col-1-half">
                            <label>关键字:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="condition.keywords"/>

                        <div class="form-label col-1-half">
                            <label>知识类型:</label>
                        </div>
                        <select ng-model="condition.type" class="col-2-half"
                                ng-options="foo.value as foo.name for foo in types">
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>内容:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="condition.content"/>

                        <div class="form-label col-1-half">
                            <label>状态:</label>
                        </div>
                        <select ng-model="condition.status" class="col-2-half"
                                ng-options="foo.value as foo.name for foo in status">
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
                    <span>知识列表</span>
                </div>
                <span class="header-button">
                        <a type="button" class="btn btn-green btn-min" ng-click="add();">
                            <span class="glyphicons plus"></span>
                            新建
                        </a>
                        <button type="button" class="btn btn-green btn-min" ng-click="remove()" ng-disabled="!anyone">
                            <span class="glyphicons remove"></span>
                            删除
                        </button>
                        <button type="button" class="btn btn-green btn-min" ng-click="batchActive();"
                                ng-disabled="!anyone">
                            启用
                        </button>
                        <button type="button" class="btn btn-green btn-min" ng-click="batchCancel();"
                                ng-disabled="!anyone">
                            注销
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
                                    <div select-all-checkbox checkboxes="beans.data" selected-items="items"
                                         anyone-selected="anyone"></div>
                                </td>
                                <td>标题</td>
                                <td style="width: 120px;">知识类型</td>
                                <td>关键字</td>
                                <td>内容</td>
                                <td style="width: 120px;">状态</td>
                                <td style="width: 100px;">操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="7" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td><input type="checkbox" ng-model="foo.isSelected"/></td>
                                <td>
                                    <a class="cp" ng-click="view(foo.id);" bo-text="foo.title"></a>
                                </td>
                                <td bo-text="foo.typeName"></td>
                                <td bo-text="foo.keywords|substr:20"></td>
                                <td bo-text="foo.content|substr:50"></td>
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
<script type="text/javascript" src="<%=contextPath%>/app/oa/knowledge/knowledge.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/knowledge/list/knowledge_list.js"></script>
</html>
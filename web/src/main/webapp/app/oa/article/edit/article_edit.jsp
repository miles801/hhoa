<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>编辑帖子</title>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/kindeditor-4.1.10/kindeditor-min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/kindeditor-4.1.10/lang/zh_CN.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/oa/module/module.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-upload.js"></script>
    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
</head>
<body>
<div class="main" ng-app="oa.article.edit" ng-controller="Ctrl">
    <div class="block">
        <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons info-sign"></span>
                </span>
                <span class="header-button">
                    <c:if test="${pageType eq 'add'}">
                        <button type="button" class="btn btn-green btn-min" ng-click="save()"
                                ng-disabled="!form.$valid">
                            <span class="glyphicons disk_save"></span> 保存
                        </button>
                    </c:if>
                    <c:if test="${pageType eq 'modify'}">
                        <button type="button" class="btn btn-green btn-min" ng-click="update()"
                                ng-disabled="!form.$valid">
                            <span class="glyphicons claw_hammer"></span> 更新
                        </button>
                    </c:if>
                    <a type="button" class="btn btn-green btn-min" ng-click="back();">
                        <span class="glyphicons message_forward"></span> 返回
                    </a>
                </span>
        </div>
        <div class="block-content">
            <div class="content-wrap">
                <form name="form" class="form-horizontal" role="form">
                    <div style="display: none;">
                        <input type="hidden" id="pageType" value="${pageType}"/>
                        <input type="hidden" id="id" value="${id}"/>
                        <input type="hidden" id="moduleId" value="${moduleId}"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.title">标题:</label>
                        </div>
                        <input class="col-6-half" name="title" type="text" ng-model="beans.title"
                               validate validate-required validate-max-length="100"/>
                        <div class="form-label col-1-half">
                            <label>所属板块:</label>
                        </div>
                        <div class="col-2-half">
                            <input type="text" class="col-12" ng-model="beans.moduleName" readonly
                                   ng-click="pickModule();" validate validate-required/>
                            <span class="add-on">
                                <i class="icons icon handle" ng-click="clearModule();"></i>
                            </span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.summary">摘要:</label>
                        </div>
                        <input class="col-10-half" name="summary" type="text" ng-model="beans.summary"
                               validate validate-max-length="200"/>
                    </div>
                    <div class="row" eccrm-upload="options"></div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>内容:</label>
                        </div>
                        <textarea class="col-10-half" id="navPageContent" rows="20"></textarea>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.keywords">关键字:</label>
                        </div>
                        <input class="col-10-half" type="text" name="keywords" ng-model="beans.keywords"
                               validate validate-max-length="40"
                               placeholder="多个关键字请使用逗号分隔..."/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>视频链接:</label>
                        </div>
                        <input class="col-10-half" type="text" ng-model="beans.videoUrl"
                               validate validate-max-length="200"/>
                    </div>
                    <div class="row">
                        <%--<div class="form-label col-1-half">
                            <label validate-error="form.status">状态:</label>
                        </div>
                        <select ng-model="beans.status" class="col-2-half" name="status"
                                ng-options="foo.value as foo.name for foo in status"
                                validate validate-required>
                        </select>--%>
                        <div class="form-label col-1-half">
                            <label>作者:</label>
                        </div>
                        <span class="col-2-half" ng-bind-template="{{beans.authorName}}"></span>
                        <div class="form-label col-1-half">
                            <label>发布时间:</label>
                        </div>
                        <span class="col-2-half" ng-bind-template="{{beans.publishTime}}"></span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/article/article.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/article/edit/article_edit.js"></script>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>编辑知识</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-upload.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/kindeditor-4.1.10/kindeditor-min.js" charset="utf-8" ></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/kindeditor-4.1.10/lang/zh_CN.js" charset="utf-8" ></script>
    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
</head>
<body>
<div class="main" ng-app="oa.knowledge.edit" ng-controller="Ctrl">
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
                        <button type="button" class="btn btn-green btn-min" ng-click="save(true)"
                                ng-disabled="!form.$valid">
                            <span class="glyphicons disk_open"></span> 保存并新建
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
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.title">标题:</label>
                        </div>
                        <input class="col-6-half" type="text" ng-model="beans.title" name="title"
                               validate validate-required validate-max-length="100"/>

                        <div class="form-label col-1-half">
                            <label validate-error="form.type">类型:</label>
                        </div>
                        <select ng-model="beans.type" class="col-2-half" name="type"
                                ng-options="foo.value as foo.name for foo in types"
                                validate validate-required>
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label >内容:</label>
                        </div>
                        <textarea class="col-10-half" id="content" rows="15" name="content"></textarea>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.keywords">关键字:</label>
                        </div>
                        <input class="col-10-half" type="text" ng-model="beans.keywords" name="keywords"
                               placeholder="多个关键字请使用逗号分隔..."
                               validate validate-required validate-max-length="100"/>
                    </div>
                    <div class="row" eccrm-upload="uploadOptions"></div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.url">外部链接:</label>
                        </div>
                        <input class="col-6-half" type="text" ng-model="beans.url" name="url"
                               validate validate-max-length="1000"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.status">状态:</label>
                        </div>
                        <select ng-model="beans.status" class="col-2-half" name="status"
                                ng-options="foo.value as foo.name for foo in status"
                                validate validate-required>
                        </select>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/knowledge/knowledge.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/knowledge/edit/knowledge_edit.js"></script>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>编辑模块</title>
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
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-upload.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/employee/employee-modal.js"></script>
    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
</head>
<body>
<div class="main" ng-app="oa.module.edit" ng-controller="Ctrl">
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
                            <label validate-error="form.name">模块名称:</label>
                        </div>
                        <input class="col-2-half" type="text" name="name" ng-model="beans.name"
                               validate validate-required validate-max-length="40"/>

                        <div class="form-label col-1-half">
                            <label validate-error="form.type">模块类型:</label>
                        </div>
                        <select ng-model="beans.type" class="col-2-half" name="type"
                                ng-options="foo.value as foo.name for foo in types"
                                validate validate-required>
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.ownerId">负责人:</label>
                        </div>
                        <div class="col-2-half">
                            <input type="text" name="ownerId" ng-model="beans.ownerName" class="col-12"
                                   validate validate-required readonly ng-click="pickEmp();"/>
                            <span class="add-on">
                                <i class="icons icon user" ng-click="clearEmp();"></i>
                            </span>
                        </div>

                        <div class="form-label col-1-half">
                            <label validate-error="form.sequenceNo">排序号:</label>
                        </div>
                        <input type="number" name="sequenceNo" ng-model="beans.sequenceNo" class="col-2-half"
                               validate validate-int validate-min-value="0" validate-max-value="1000"/>
                    </div>
                    <div class="row" eccrm-upload="options"></div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>&nbsp;</label>
                        </div>
                        <div id="logo"></div>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.video">视频连接:</label>
                        </div>
                        <input type="text" name="video" ng-model="beans.video" class="col-6-half"
                               validate validate-max-length="200"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.summary">简介:</label>
                        </div>
                        <textarea class="col-10-half" type="text" ng-model="beans.summary" rows="6"
                                  validate validate-max-length="1000"></textarea>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>描述:</label>
                        </div>
                        <textarea class="col-10-half" rows="6" type="text" ng-model="beans.description"
                                  validate validate-max-length="1000"></textarea>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/module/module.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/module/edit/module_edit.js"></script>
</html>
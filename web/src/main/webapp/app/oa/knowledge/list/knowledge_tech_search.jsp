<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>知识检索</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/base/attachment/attachment.js"></script>
    <style type="text/css">
        .kn-search {
            margin-top: 15px;
        }

        .searchbox {
            height: 40px;
            width: 500px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            position: relative;
            display: inline-block;
        }

        .searchbox:focus,
        .searchbox:hover {
            border-color: #0cadfe;
        }

        .searchbox > input {
            height: 38px;
            line-height: 38px;
            padding: 2px 10px;
            font-size: 14px;
            box-sizing: border-box;
            outline: none;
            white-space: nowrap;
            width: 100%;
            position: relative;
            margin: 0;
            border: 0;
        }

        .searchbox > span {
            position: absolute;
            top: 12px;
            right: 8px;
            background: url(<%=contextPath%>/app/oa/knowledge/list/x.png) no-repeat;
            width: 12px;
            height: 24px;
            cursor: pointer;
        }

        .searchbox > span:hover {
            background-position: 0 -32px !important;
        }

        .btn.btn-search {
            background-color: #3385ff;
            width: 100px;
            height: 40px;
            line-height: 40px;
            font-size: 18px;
            border: 1px solid #2d78f4;
            border-left: 0;
            box-sizing: border-box;
            text-align: center;
            color: #fff;
            margin: 0;
            padding: 0;
        }

        div.box {
            width: 800px;
            margin: 20px auto 0;
            padding: 0 10px;
        }

        div.box .title {
            height: 24px;
            line-height: 24px;
            font-size: medium;
            text-decoration: underline;
            word-wrap: break-word;
            color: rgb(0, 0, 204);
            cursor: pointer;
        }

        div.box .content {
            overflow: hidden;
            height: 120px;
            word-wrap: break-word;
            padding: 10px;
            border: 1px dashed #dcdcdc;
            box-sizing: border-box;
        }

        .nomapping {
            text-align: center;
            margin-top: 20px;
        }

        .nomapping span {
            font-size: 18px;
            font-family: microsoft yahei;
        }

        .nomapping span.error {
            color: #c00;
        }

        .search-logo {
            width: 100%;
            padding-right: 100px;
            text-align: center;
        }

        .search-logo span {
            margin-left: -150px;
            font-size: 26px;
            color: #333333;
        }

    </style>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body>
<div class="main" ng-app="oa.knowledge.searchTech" ng-controller="Ctrl" style="overflow: auto">
    <div class="dn">
        <input type="hidden" id="type" value="${type}">
    </div>
    <div>
        <div class="ta-c kn-search">
            <div class="search-logo">
                <span style="padding-left: 100px;">
                    <img src="<%=contextPath%>/app/main/images/logo3.png" width="50" height="50"/> 技术学堂
                </span>
            </div>
            <%--
            <span class="searchbox">
                <input type="text" id="keywordsOrTitle" ng-model="condition.keywordsOrTitle" size="20">
                <span ng-click="condition.keywordsOrTitle=null"></span>
            </span>
            <button class="btn btn-search" ng-click="query();">搜索</button>
            --%>
        </div>
        <div class="dn" eccrm-page="pager"></div>
    </div>
    <div class="nomapping" ng-cloak ng-if="noresult">
        <span>很抱歉，没有找到与“<span class="error">{{errorContent}}</span>”相关的知识。</span>
    </div>
    <div style="margin:15px auto;width:750px;text-align:center;" ng-cloak ng-if="!noresult">
        <div class="row" style="border: 1px solid #dcdcdc;">
            <div bindonce ng-repeat="at in attachments"
                 style="float:left;height: 50px;margin:10px 25px;cursor: pointer;">
                <img ng-src="<%=contextPath%>/style/standard/images/icons/{{at.fileRealType}}.png"
                     alt="{{at.fileName}}"/>
                <a ng-href="<%=contextPath%>/attachment/download?id={{at.id}}" bo-text="at.fileName"
                   target="_blank"></a>
            </div>
            &nbsp;
        </div>
        <div class="text-right" ng-cloak style="padding: 0 15px;height: 30px;line-height: 29px;font-size: 14px;">
            <span>共 {{attachments.length||0}} 个文件</span>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/knowledge/knowledge.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/knowledge/list/knowledge_tech_search.js"></script>
</html>
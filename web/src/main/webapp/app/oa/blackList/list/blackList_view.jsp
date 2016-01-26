<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>黑户查询</title>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
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
            margin: 15px auto 0;
            padding: 10px 20px;
            border: 1px solid #dcdcdc;
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

        .box .item {
            height: 30px;
            line-height: 30px;
        }

        .box .item .head {
            font-weight: 700;
            width: 80px;
            font-size: 14px;
            color: #444;
            text-align: right;
            display: inline-block;
        }

        .box .item .content {
            margin-left: 5px;
        }

        /* 覆写分页样式 */
        #pager .pager {
            height: 100%;
            line-height: 100%;
        }

        #pager .pager > * {
            height: 100%;
            line-height: 35px !important;
            box-sizing: border-box;
        }

        #pager .pager button {
            height: 28px;
            margin-top: 3px;
        }
    </style>
</head>
<body>
<div class="main" ng-app="oa.blackList.view" ng-controller="Ctrl" style="overflow: auto">
    <div>
        <div class="ta-c kn-search">
            <div class="search-logo">
                <img src="<%=contextPath%>/app/main/images/logo.png"/>
                <span>黑户查询</span>
            </div>
            <span class="searchbox">
                <input type="text" id="keywordsOrTitle" ng-model="keywords" size="20">
                <span ng-click="keywords=null"></span>
            </span>
            <button class="btn btn-search" ng-click="query();" ng-disabled="!keywords">搜索</button>
        </div>
    </div>
    <div class="nomapping" ng-cloak ng-if="noresult">
        <span>很抱歉，没有找到与“<span class="error">{{errorContent}}</span>”相关的知识。</span>
    </div>
    <div class="box" bindonce ng-cloak ng-repeat="foo in beans.data">
        <div class="item">
            <span class="head">客户名称：</span>
            <span class="content" bo-text="foo.name"></span>
        </div>
        <div class="item">
            <span class="head">客户信息：</span>
            <span class="content" bo-text="foo.info"></span>
        </div>
        <div class="item">
            <span class="head">原因：</span>
            <span class="content" bo-text="foo.reason"></span>
        </div>
    </div>
    <div id="pager" eccrm-page="pager" ng-cloak ng-show="beans.total>pager.limit"
         style="width: 800px; text-align: center; margin: 15px auto 15px;height: 40px;"></div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/blackList/blackList.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/blackList/list/blackList_view.js"></script>
</html>
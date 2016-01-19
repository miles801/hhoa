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
    <style>
        .main {
            width: 100%;
            margin: auto !important;
            border: 1px solid #DDDDDD;
            font-size: 14px;
            padding: 10px 20px;
            overflow: auto;
        }

        .main .wrap {
            border: 1px solid #dcdcdc;
        }

        .c {
            display: block;
            width: 100%;
            height: 100px;
            border-bottom: 1px dashed #DDDDDD;
            position: relative;
        }

        .c:last-child {
            border-bottom: 0;
        }

        .c .c1 {
            display: inline-block;
            width: 80px;
            height: 100%;
            text-align: center;
            position: absolute;
            top: 0;
            left: 0;
        }

        .c .c1 img {
            margin-top: 20px
        }

        .c .split {
            position: absolute;
            top: 20px;
            left: 75px;
            border-right: 1px solid #dcdcdc;
            height: 60px;
        }

        .c .c2 {
            height: 100%;
            padding: 10px 15px 10px 90px;
        }

    </style>
</head>
<body>
<div class="main" ng-app="oa.module.view" ng-controller="Ctrl">
    <div class="wrap">
        <div class="c" ng-repeat="foo in beans.data" ng-cloak>
            <div class="c1">
                <img ng-src="<%=contextPath%>/attachment/view?id={{foo.logo}}" alt="LOGO" width="60" height="60"/>
            </div>
            <div class="split"></div>
            <div class="c2">
                <div>
                    <span style="font-size: 14px;font-weight: 700;text-decoration: underline;cursor: pointer;color: #0cadfe;"
                          ng-click="viewT(foo.id,foo.name);">{{foo.name}}</span>
                    <span style="float: right;font-weight: 700;">{{foo.ownerName}}</span>
                </div>
                <div>
                    简介：{{foo.summary}}
                </div>
                <div>
                    描述：{{foo.description}}
                </div>
                <div>
                <span style="width: 100px;display: inline-block;">
                    帖数：{{foo.articleCounts||0}}
                </span>
                <span>
                    最后发表：{{foo.articleLastTime|eccrmDatetime}}
                </span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/module/module.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/module/list/module_view.js"></script>
</html>
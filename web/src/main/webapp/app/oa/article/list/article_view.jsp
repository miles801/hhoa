<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>帖子</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
    <style>
        .c {
            width: 100%;
            margin: auto !important;
            border: 1px solid #DDDDDD;
            font-size: 14px;
            padding: 10px 20px;
            overflow: auto;
        }

        .c > * {
            margin: 0;
            padding: 0;
        }

        .c .header {
            text-align: center;
            padding: 5px 20px;
            border: 1px solid #cdcdcd;
            border-bottom: 0;
            box-sizing: border-box;
        }

        .c .header #title {
            height: 35px;
            line-height: 35px;
            font-size: 16px;
            font-weight: 600;
            color: #444;
        }

        .c .header .btn {
            width: 75px;
            height: 35px;
            line-height: 35px;
            font-size: 18px;
            font-weight: 500;
        }

        .c .content {
            border: 1px solid #cdcdcd;
            border-bottom: 0;
        }

        .c .footer {

        }

        .c .content table {
            width: 100%;
            border-collapse: collapse;
            white-space: nowrap;
            margin: 0;
        }

        .c .content table thead {
            background-color: #f2f2f2;
            width: 100%;
            color: #444;
            font-size: 16px;
        }

        .c .content table thead tr {
            height: 30px;
            width: 100%;
            display: table-row;
        }


        .c .content table td {
            height: 30px;
            line-height: 30px;
            display: table-cell;
        }
    </style>
</head>
<body>
<div class="main" ng-app="oa.article.view" ng-controller="Ctrl">
    <div class="dn">
        <input type="hidden" id="mid" value="${param.mid}">
        <input type="hidden" id="mname" value="${param.name}">
    </div>
    <div class="c">
        <div class="header">
            <a href="javascript:window.history.back();" class="btn btn-blue" style="float: left;">&lt;&lt;</a>
            <span style="clear: left;"></span>
            <span ng-bind-template="{{moduleName}}" id="title"></span>
            <a type="button" class="btn btn-blue" ng-click="add();" style="float: right;">
                发帖
            </a>
            <span style="clear: right;"></span>
        </div>
        <div class="content">
            <table class="table text-center">
                <thead class="table-header">
                <tr>
                    <td class="text-left">主题</td>
                    <td>作者</td>
                    <td>发布时间</td>
                    <td>评论数/浏览量</td>
                    <td>最后发表</td>
                </tr>
                </thead>
                <tbody class="table-body">
                <tr ng-show="!beans || !beans.total">
                    <td colspan="5" class="text-center">暂无新帖！</td>
                </tr>
                <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                    <td class="text-left">
                        <a ng-click="view(foo.id)" bo-text="foo.title" class="cp"></a>
                    </td>
                    <td bo-text="foo.authorName"></td>
                    <td bo-text="foo.publishTime|eccrmDate"></td>
                    <td bo-text="(foo.commentCounts||0)+'/'+(foo.viewCounts||0)"></td>
                    <td>
                        <div>{{foo.lastCommentName}} <span style="color: #aaa;margin-left:8px;">{{foo.lastCommentTime|eccrmDatetime}}</span>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="footer">
            <div class="list-pagination" eccrm-page="pager" style="border-top:1px solid #B7C1CB!important;"></div>
        </div>
    </div>

</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/article/article.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/article/list/article_view.js"></script>
</html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>帖子</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/kindeditor-4.1.10/kindeditor-min.js"
            charset="utf-8"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/kindeditor-4.1.10/lang/zh_CN.js"
            charset="utf-8"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
    <style>
        * {
            font-family: "微软雅黑", "宋体", "Arial", "Helvetica", "sans-serif";
            font-size: 12px;
        }

        .main {
            margin: 0 auto;
            padding: 10px 20px;
            position: relative;
            text-align: center;
            background-color: #dcdcdc;
            height: 100%;
            overflow: auto !important;
        }

        .main .wrap {
            background-color: #FFFFF0;
            width: 1000px;
            border: 1px solid #dcdcdc;
            margin: auto;
            position: relative;
            padding: 5px 10px 10px 10px;

        }

        #article-head {
            font-size: 16px;
            font-weight: 700;
            padding-left: 16px;
            height: 40px;
            line-height: 40px;
            border-bottom: 3px solid #E5EDF2;
        }

        .author {
            color: #333;
            border-bottom: 1px dashed #ddd;
            padding: 5px 20px;
            height: 40px;
            box-sizing: border-box;
            line-height: 30px;
            font-weight: 700;
        }

        .article-head2 {
            border-bottom: 1px dashed #C2D5E3;
            height: 35px;
            line-height: 35px;
            font-size: 12px;
            width: 98%;
            font-style: normal;
            margin: 5px auto auto;
            color: #444;
        }

        #article-content {
            padding: 20px;
        }

        .back {
            font-size: 14px;
            background: url('<%=contextPath%>/app/oa/article/list/left.gif') no-repeat 4px 7px;
            border: 1px solid #C2D5E3;
            height: 30px;
            display: inline-block;
            line-height: 30px;
            width: 100px;
            padding-right: 10px;
            margin-top: 4px;
        }
    </style>
</head>
<body>
<div class="main" ng-app="oa.article.content" ng-controller="Ctrl">
    <div class="dn">
        <input type="hidden" id="id" value="${param.id}">
    </div>
    <div class="wrap">
        <div style="text-align: right;height: 40px;">
            <a href="javascript:history.back();" class="back">返回列表</a>
        </div>
        <div>
            <table style="border:1px solid #C2D5E3;width: 100%;">
                <thead>
                <tr>
                    <td style="background-color:#E5EDF2;width: 220px;border-right:1px solid #C2D5E3;vertical-align: top;">
                        <div class="author">{{beans.creatorName}}</div>
                    </td>
                    <td>
                        <div id="article-head" ng-bind="beans.title"></div>
                        <div class="article-head2">
                            发表于 <span style="margin-left: 8px;"
                                      ng-bind-template="{{beans.publishTime|eccrmDatetime}}"></span>
                        </div>
                        <div id="article-content"></div>
                    </td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="foo in comments">
                    <td style="background-color:#E5EDF2;width: 220px;border-right:1px solid #C2D5E3;vertical-align: top;border-top:3px solid #C2D5E3;">
                        <div class="author">{{foo.creatorName}}</div>
                    </td>
                    <td style="border-top:3px solid #E5EDF2;padding: 0 20px 15px;">
                        <div class="article-head2">
                            发表于 <span style="margin-left: 8px;"
                                      ng-bind-template="{{foo.createdDatetime|eccrmDatetime}}"></span>
                        </div>
                        <div eccrm-html="foo.content" style="padding: 20px 0;"></div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div style="margin-top: 15px;border:1px solid #C2D5E3;">
            <table style="width: 100%;">
                <thead>
                <tr>
                    <td style="background-color:#E5EDF2;width: 220px;border-right:1px solid #C2D5E3;"> &nbsp; </td>
                    <td>
                        <div style="width: 100%;padding: 10px 20px;">
                            <textarea id="comment" style="width: 100%!important;" rows="6"></textarea>
                        </div>
                        <div style="margin-top:8px;padding: 10px 20px 20px;">
                            <button class="btn btn-blue" ng-click="doComment();"
                                    style="width: 120px;height: 30px;line-height: 30px;">发表回复
                            </button>
                        </div>
                    </td>
                </tr>
                </thead>
            </table>
        </div>
    </div>

</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/article/article.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/article/list/article_content.js"></script>
</html>
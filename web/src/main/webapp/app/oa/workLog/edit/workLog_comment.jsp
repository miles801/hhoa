<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>日志评论</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
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
            padding-left: 2em;
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
<div class="main" ng-app="oa.workLog.comment" ng-controller="Ctrl" style="overflow: auto">
    <div class="box">
        <textarea style="height: 60px;width: 100%;padding: 10px 1em;border: 0;outline: none;" placeholder="我也说点什么"
                  ng-model="content" ng-focus="focused=true" ng-blur="delayFocus();"></textarea>
        <div style="text-align: right;padding:5px;border-top:1px solid #dcdcdc;" ng-show="focused" ng-cloak>
            <button class="btn btn-blue" ng-click="addWorkLog();">发表</button>
        </div>
    </div>
    <div class="box" ng-cloak ng-repeat="foo in beans.data">
        <div class="item">
            <span class="content" style="color: #0cadfe;font-weight: 700;">{{foo.creatorName}}</span>
            <span class="content" style="color: #aaa;">{{foo.occurDate|date:'yyyy-mm-dd HH:mm'}}</span>
        </div>
        <div style="padding: 10px 2em;border-bottom: 1px solid #dcdcdc;">
            <span class="content">{{foo.content}}</span>
        </div>
        <div ng-if="foo.commentCounts>0">
            <div ng-repeat="o in foo.comments" style="padding: 10px 1em;">
                <span style="color: #648F8B;">{{o.creatorName}} </span>：{{o.content}}
                <div style="color: #ccc;padding-left: 5px;">{{o.createdDatetime|date:'yyyy-mm-dd HH:mm'}}</div>
            </div>
        </div>
        <div ng-if="hasCommentRight==true">
            <div style="padding: 8px 20px;">
                <div style="border: 1px solid #e6e6e6; height: 30px; width: 100%; line-height: 30px; padding-left: 14px; color: #ddd;position: relative;"
                     ng-show="!foo.focused">
                    <input type="text" style="width: 100%;height: 100%;border: 0;" ng-focus="foo.focused=true"
                           placeholder="我也说一句"/>
                </div>
                <div ng-show="foo.focused">
                    <textarea ng-model="foo.comment" style="width: 100%;height: 80px;padding: 8px 15px;"
                              ng-blur="focusout(foo);" autofocus></textarea>
                    <div style="text-align: right;margin-top:5px;">
                        <button class="btn btn-blue" ng-click="addComment(foo);">发表</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="pager" eccrm-page="pager" ng-cloak ng-show="beans.total>pager.limit"
         style="width: 800px; text-align: center; margin: 15px auto 15px;height: 40px;"></div>
</div>

</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/workLog/workLog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/workLog/edit/workLog_comment.js"></script>
</html>
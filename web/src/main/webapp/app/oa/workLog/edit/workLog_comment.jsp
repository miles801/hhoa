<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>日志评论</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/moment/moment.min.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
    <style type="text/css">
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

        .box {
            text-align: left;
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
            word-break: break-all;
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

        .wrap {
            width: 1000px;
            margin: 0 auto;
            text-align: center;
            border: 8px solid #ddd;
            box-shadow: #444;
            height: 100%;
            background-color: #fff;
            overflow: auto;
        }
    </style>
</head>
<body>
<div class="main" ng-app="oa.workLog.comment" ng-controller="Ctrl"
     style="overflow: hidden;background-color: #ddd;">
    <div class="wrap">
        <div class="box" ng-cloak ng-if="hasCreateArticleRight">
        <textarea style="height: 60px;width: 100%;padding: 10px 1em;border: 0;outline: none;" placeholder="我也说点什么"
                  ng-model="content" ng-focus="focused=true" ng-blur="delayFocus();"></textarea>
            <div style="text-align: right;padding:5px;border-top:1px solid #dcdcdc;" ng-show="focused" ng-cloak>
                <button class="btn btn-blue" ng-click="addWorkLog();">发表</button>
            </div>
        </div>
        <h3 ng-cloak style="text-align: center; width: 800px; margin: auto; padding: 15px 0;display: none;">
            <span>{{date||'全部'}}</span>
            <span ng-cloak ng-show="date||condition.creatorId">
                <a ng-click="queryWithAuth();"
                   style="font-size: 14px; float: right; border: 1px solid #32bbca; padding: 5px 10px;cursor: pointer;">全部</a>
            </span>
        </h3>
        <div class="box" ng-cloak ng-repeat="foo in beans.data">
            <div class="item cp" ng-click="showContent(foo);">
                <span class="content" style="color: #0cadfe;font-weight: 700;cursor: pointer;"
                >{{foo.creatorName}}</span>
                <span class="content"
                      style="color: #0cadfe;cursor: pointer;">{{foo.occurDate|date:'yyyy-MM-dd HH:mm'}}</span>
            </div>
            <div ng-show="foo.show" ng-cloak>
                <div style="padding: 10px 2em;border-bottom: 1px solid #dcdcdc;word-break: break-all;">
                    <span class="content">{{foo.content}}</span>
                </div>
                <div ng-if="foo.commentCounts>0">
                    <div ng-repeat="o in foo.comments" style="padding: 10px 1em;">
                        <span style="color: #648F8B;">{{o.creatorName}} </span>：{{o.content}}
                        <div style="color: #ccc;padding-left: 5px;">{{o.createdDatetime|date:'yyyy-MM-dd HH:mm'}}</div>
                    </div>
                </div>
                <div ng-if="hasCommentRight==true">
                    <div style="padding: 8px 20px;word-break: break-all;">
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
        </div>
        <div id="pager" eccrm-page="pager" ng-cloak ng-show="beans.total>pager.limit"
             style="width: 800px; text-align: center; margin: 15px auto 15px;height: 40px;"></div>
    </div>
</div>

</body>
<script type="text/javascript" src="<%=contextPath%>/app/oa/workLog/workLog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/oa/workLog/edit/workLog_comment.js"></script>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en" >
<head >
    <title >海航OA协同办公系统</title >
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" >
    <![endif]-->
    <link rel="stylesheet" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css" />
    <link rel="stylesheet" href="<%=contextPath%>/app/main/css/main.css" />

    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js" ></script >
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js" ></script >
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js" ></script >
    <script type="text/javascript" src="<%=contextPath%>/app/main/js/nav.js" ></script >
    <script >
        window.angular.contextPathURL = "<%=contextPath%>";
    </script >
</head >
<body id="ng-app" ng-app="eccrm.main" >
<div id="container" ng-controller="MainController" >
    <input type="hidden" id="contextPath" value="<%=contextPath%>/" />
    <div id="header" >
        <div class="top" >
            <div class="logo" ></div >
            <div class="tool" style="width: 100px;" >
                <a href="<%=contextPath%>/logout" >
                    <img src="<%=contextPath%>/app/main/images/icon/h13.png" width="24" height="24" title="退出" >
                </a >
            </div >
        </div >
    </div >
    <div id="main" >
        <div class="leftbar" >
            <div class="LB_container" >
                <a bindonce bo-title="menu.name" ng-repeat="menu in menus" ng-repeat-finish bo-class="{'current':$index===0}" ng-click="showChildren(menu);" >
                    <img eccrm-img-loading="<%=contextPath%>/attachment/image?id={{menu.icon}}" ng-cloak bo-if="menu.icon" src="<%=contextPath%>/style/standard/images/loading.gif" width="48" height="50" />
                </a >
            </div >
            <div class="btnT" ></div >
            <div class="btnB" ></div >
        </div >
        <div class="mainRight" >
            <div id="accordian" >
                <ul >
                    <li bindonce ng-repeat="level1 in subMenus" ng-repeat-finish="subFinish" >
                        <h3 nav-click-slide=".nav_menus" >
							<span class="menu-text" >
                                <i class="icons-sj" ></i >
								<a ng-click="addTab(level1.name,level1.url)" bo-text="level1.name" ></a >
							</span >
							<span class="menu-children" bo-show="level1.children.length>0" >
								<span class="menu-children" style="position:relative" >
                                    <span style="position:absolute;color:#ffffff;" >&#9660;</span >
                                </span >
							</span >
                        </h3 >
                        <ul class="nav_menus" >
                            <li bindonce ng-repeat="level2 in level1.children" bindonce >
                                <div bo-if="level2.children && level2.children.length>0" >
                                    <a nav-click-slide="div" style="cursor: pointer;" ng-click="addTab(level2.name,level2.url)" >
                                        <span bo-text="level2.name" class="menu-text" ></span >
										<span class="menu-children" >
											<span style="color:#1893dd;" >&#9660;</span >
										</span >
                                    </a >

                                    <div style="margin-left: 10px;display: none;" bo-if="level2.children && level2.children.length>0" >
                                        <a ng-click="addTab(level3.name,level3.url)" style="cursor: pointer;" bindonce ng-repeat="level3 in level2.children" >
                                            <span style="margin-right:3px;color:#1893dd;" >&#8627;</span >
                                            <span bo-text="level3.name" class="menu-text" ></span >
                                        </a >
                                    </div >
                                </div >
                                <a bo-if="!level2.children || level2.children.length<1" ng-click="addTab(level2.name,level2.url)" >
                                    <span bo-text="level2.name" class="menu-text" ></span >
                                </a >
                            </li >
                        </ul >
                    </li >
                </ul >
            </div >
            <div id="colbar" >
                <div id="arrow" >
                    <i class="arrow-left" id="fold" title="收起" ></i >
                    <i class="arrow-right" id="expand" title="展开" style="display: none;" ></i >
                </div >
            </div >
            <div class="content-iframe" >
                <iframe id="iframe" style="display: none;" name="iframe" frameborder="0" ></iframe >
                <div id="tab" style="height: 100%;width: 100%;overflow: hidden;" ></div >
            </div >
        </div >
    </div >
    <div class="footer" id="footer" >
        <div class="left" >
            <span ><i class="icons user" title="当前用户" style="top:3px;" ></i ><span >${sessionScope.employeeName}</span ></span >
            <span style="margin-left: 15px;" ><i class="icons clock" title="登录时间" ></i ><span ng-cloak >{{${sessionScope.loginDatetime} | date:'yyyy-MM-dd HH:mm'}}</span ></span >
            <span style="margin-left: 15px;font-weight: 700;" ng-cloak eccrm-previlege="PMD" >消息：</span >
        </div >

        <div class="center" >
            <marquee direction="left" onmouseover="this.stop()" onmouseout="this.start()" >
                <span bindonce ng-repeat="foo in beans" ng-repeat-finish="scrollRenderFinish" ng-cloak >
                    <span style="padding: 0 10px;color:#ffffff" > 【<span bo-text="foo.categoryName" ></span >】
                        <a style="cursor: pointer;" ng-click="showNews(foo.id);" >
                            <span style="color: #4591CD" bo-text="foo.title | substr:20" ></span >
                        </a >
                    </span >
                </span >
            </marquee >
        </div >
        <div class="right" style="width: 100px;" >
            <span title="便签" >
                <i class="icons note" ng-click="showNote();" ></i >
            </span >
        </div >
    </div >
</body >
<script type="text/javascript" src="<%=contextPath%>/app/base/im/news/news.js" ></script >
<script src="<%=contextPath%>/app/main/js/main.js" type="text/javascript" ></script >
</html >
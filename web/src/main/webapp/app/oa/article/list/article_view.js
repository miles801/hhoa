/**
 * Created by Michael on 2016-01-18 05:32:13.
 */
(function (window, angular, $) {
    var app = angular.module('oa.article.view', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.article'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ArticleService, ModalFactory) {

        var mid = $('#mid').val();
        if (!mid) {
            AlertFactory.error(null, '错误的访问!');
            return;
        }
        var mname = $('#mname').val();
        if (mname) {
            $scope.moduleName = decodeURI(decodeURI(mname));
        }
        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.pager = {
            fetch: function () {
                var param = {start: this.start, limit: this.limit, moduleId: mid};
                $scope.beans = [];
                return CommonUtils.promise(function (defer) {
                    var promise = ArticleService.pageQuery(param, function (data) {
                        param = null;
                        $scope.beans = data.data || {total: 0};
                        defer.resolve($scope.beans);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            },
            finishInit: function () {
                this.query();
            }
        };

        $scope.add = function () {
            window.location.href = CommonUtils.contextPathURL('/oa/article/add?moduleId=' + mid);
        };

        // 查看
        $scope.view = function (id) {
            window.location.href = CommonUtils.contextPathURL('app/oa/article/list/article_content.jsp?id=' + id);
        };


    });
})(window, angular, jQuery);
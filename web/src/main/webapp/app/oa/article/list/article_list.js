/**
 * Created by Michael on 2016-01-18 05:32:13.
 */
(function (window, angular, $) {
    var app = angular.module('oa.article.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.article'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ArticleService, ModalFactory) {
        $scope.condition = {};

        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                $scope.beans = [];
                return CommonUtils.promise(function (defer) {
                    var promise = ArticleService.pageQuery(param, function (data) {
                        param = null;
                        $scope.beans = data.data || {total: 0};
                        defer.resolve($scope.beans);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            }
        };

        // 删除或批量删除
        $scope.remove = function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '确定要执行【批量删除】操作吗?',
                callback: function () {
                    var ids = id || getCheckedIds();
                    var promise = ArticleService.deleteByIds({ids: ids}, $scope.query);
                    CommonUtils.loading((promise));
                }
            });
        };

        // 新增
        $scope.add = function () {
            CommonUtils.addTab({
                title: '发帖',
                url: '/oa/article/add',
                onUpdate: $scope.query
            });
        };

        // 更新
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新帖子',
                url: '/oa/article/modify?id=' + id,
                onUpdate: $scope.query
            });
        };
        // 查看
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看帖子',
                url: '/oa/article/detail?id=' + id
            });
        };


        $scope.publish = function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '确定要执行【批量发布】操作吗?',
                callback: function () {
                    var ids = id || getCheckedIds();
                    var promise = ArticleService.publish({ids: ids}, function () {
                        AlertFactory.success(null, '发布成功!');
                        $scope.pager.load();
                    });
                    CommonUtils.loading((promise));
                }
            });
        };
    });
})(window, angular, jQuery);
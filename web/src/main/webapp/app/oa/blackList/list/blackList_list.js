/**
 * Created by Michael on 2016-01-05 00:16:23.
 */
(function (window, angular, $) {
    var app = angular.module('oa.blackList.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.blackList'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, BlackListService, ModalFactory, BlackListParam) {
        $scope.condition = {};

        // 黑户类型
        BlackListParam.type(function (data) {
            $scope.types = data || [];
            $scope.types.unshift({name: '全部'});
        });

        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        // 重置
        $scope.reset = function () {
            $scope.condition = {};
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                return CommonUtils.promise(function (defer) {
                    var promise = BlackListService.pageQuery(param, function (data) {
                        $scope.beans = data.data || {total: 0};
                        defer.resolve($scope.beans.total);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            }
        };

        // 新增黑户
        $scope.add = function () {
            CommonUtils.addTab({
                title: '新增黑户',
                url: '/oa/blackList/add',
                onUpdate: $scope.query
            });
        };

        // 删除黑户
        $scope.remove = function (id) {
            ModalFactory.remove($scope, function () {
                if (id === undefined) {
                    id = $.map($scope.items, function (o) {
                        return o.id;
                    }).join(',');
                }
                var promise = BlackListService.deleteByIds({ids: id}, $scope.query);
                CommonUtils.loading(promise);
            });
        };

        // 更新黑户信息
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新黑户',
                url: '/oa/blackList/modify?id=' + id,
                onUpdate: $scope.query
            })
        };
    });
})(window, angular, jQuery);
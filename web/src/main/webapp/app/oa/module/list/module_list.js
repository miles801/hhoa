/**
 * Created by Michael on 2016-01-05 13:30:15.
 */
(function (window, angular, $) {
    var app = angular.module('oa.module.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.module'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, ModuleService, ModuleParam, ModalFactory) {
        $scope.condition = {};

        // 模块类型
        ModuleParam.type(function (data) {
            $scope.types = data || [];
            $scope.types.unshift({name: '全部'});
        });

        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.reset = function () {
            $scope.condition = {};
        };
        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                return CommonUtils.promise(function (defer) {
                    var promise = ModuleService.pageQuery(param, function (data) {
                        $scope.beans = data.data || {total: 0};
                        defer.resolve($scope.beans);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            }
        };


        $scope.add = function () {
            CommonUtils.addTab({
                title: '新增模块',
                url: '/oa/module/add',
                onUpdate: $scope.query
            });
        };
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新模块',
                url: '/oa/module/modify?id=' + id,
                onUpdate: $scope.query
            });
        };
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看模块',
                url: '/oa/module/detail?id=' + id
            });
        };

        $scope.remove = function (id) {
            ModalFactory.remove($scope, function () {
                if (id === undefined) {
                    id = $.map($scope.items, function (o) {
                        return o.id;
                    }).join(',');
                }
                var promise = ModuleService.deleteByIds({ids: id}, $scope.query);
                CommonUtils.loading(promise);
            });
        };
    });
})(window, angular, jQuery);
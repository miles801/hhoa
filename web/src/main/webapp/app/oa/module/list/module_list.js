/**
 * Created by Michael on 2016-01-05 13:30:15.
 */
(function (window, angular, $) {
    var app = angular.module('oa.module.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.module'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, ModuleService, ModuleParam) {
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

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                var promise = ModuleService.pageQuery(param, function (data) {

                });
                CommonUtils.loading(promise, 'Loading...');
            }
        }
    });
})(window, angular, jQuery);
/**
 * Created by Michael on 2016-01-05 00:16:23.
 */
(function (window, angular, $) {
    var app = angular.module('oa.blackList.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.blackList'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, BlackListService, ModalFactory) {
        $scope.condition = {};
        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                var promise = BlackListService.pageQuery(param, function (data) {

                });
                CommonUtils.loading(promise, 'Loading...');
            }
        };

        $scope.add = function () {
            ModalFactory.create({
                template: CommonUtils.contextPathURL('app/home/news/onlinetalk/forwarding.tpl.html'),
                callback: function (scope) {
                    alert(111);
                }
            });
        };
    });
})(window, angular, jQuery);
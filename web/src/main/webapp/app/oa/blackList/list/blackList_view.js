/**
 * Created by Michael on 2016-01-05 00:16:23.
 */
(function (window, angular, $) {
    var app = angular.module('oa.blackList.view', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.blackList'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, BlackListService, ModalFactory, BlackListParam) {
        $scope.keywords = null;

        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.pager = {
            fetch: function () {
                var param = {start: this.start, limit: this.limit, keywords: $scope.keywords};
                return CommonUtils.promise(function (defer) {
                    $scope.beans = {};
                    var promise = BlackListService.pageQuery(param, function (data) {
                        $scope.beans = data.data || {total: 0};
                        $scope.noresult = $scope.beans.total == 0;
                        $scope.errorContent = $scope.keywords;
                        defer.resolve($scope.beans);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            }
        };

        // 绑定回车
        $('#keywordsOrTitle').on('keydown', function (e) {
            if ($(this).val() && e.keyCode == 13) {
                $scope.query();
            }
        });
    });
})(window, angular, jQuery);
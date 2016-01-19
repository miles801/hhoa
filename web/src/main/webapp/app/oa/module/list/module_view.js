/**
 * Created by Michael on 2016-01-05 13:30:15.
 */
(function (window, angular, $) {
    var app = angular.module('oa.module.view', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.module'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, ModuleService, ModuleParam, ModalFactory) {

        //查询数据
        $scope.query = function () {
            var promise = ModuleService.pageQuery({status: 'ACTIVE'}, function (data) {
                $scope.beans = data.data || {total: 0};
            });
            CommonUtils.loading(promise, 'Loading...');
        };

        // 查看帖子
        $scope.viewT = function (id, name) {
            window.location.href = CommonUtils.contextPathURL('/app/oa/article/list/article_view.jsp?mid=' + id + '&name=' + CommonUtils.encode(name));
        };


        $scope.query();
    });
})(window, angular, jQuery);
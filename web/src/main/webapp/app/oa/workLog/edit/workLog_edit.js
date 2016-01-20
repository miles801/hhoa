/**
 * Created by Michael on 2016-01-20 13:04:49.
 */
(function (window, angular, $) {
    var app = angular.module('oa.workLog.edit', [
        'oa.workLog',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, WorkLogService) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();

        // 保存
        $scope.save = function (createNew) {
            var promise = WorkLogService.save($scope.beans, function (data) {
                if (data && data['success'] == true) { //保存成功
                    AlertFactory.success(null, '保存成功!');
                    $scope.form.$setValidity('committed', false);
                } else {
                    AlertFactory.saveError($scope, data);
                }
            });
            CommonUtils.loading(promise, '保存中...');
        };

        // 加载数据
        $scope.load = function (id) {
            var promise = WorkLogService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
            });
            CommonUtils.loading(promise, 'Loading...');
        };


        if (pageType == 'add') {
            $scope.beans = {};
        } else if (pageType == 'view') {
            $scope.load(id);
            $('input,textarea,select').attr('disabled', 'disabled');
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);
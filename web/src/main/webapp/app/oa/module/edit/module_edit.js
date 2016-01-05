/**
 * Created by Michael on 2016-01-05 13:30:15.
 */
(function (window, angular, $) {
    var app = angular.module('oa.module.edit', [
        'oa.module',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModuleService) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();

        // 保存
        $scope.save = function (createNew) {
            var promise = ModuleService.save($scope.beans, function (data) {
                if (data && data['success'] == true) { //保存成功
                    if (createNew === true) {
                        $scope.beans = {};
                    } else {
                        $scope.form.$setValidity('committed', false);
                    }
                } else {
                    AlertFactory.saveError($scope, data);
                }
            });
            CommonUtils.promise(promise, '保存中...');
        };

        // 更新
        $scope.update = function () {
            var promise = ModuleService.update($scope.beans, function (data) {
                if (data && data['success'] == true) { // 更新成功
                    $scope.form.$setValidity('committed', false);
                }
                AlertFactory.updateError($scope, data);
            });
            CommonUtils.promise(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id) {
            var promise = ModuleService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
            });
            CommonUtils.promise(promise, 'Loading...');
        };


        if (pageType == 'add') {
            $scope.module = {};
        } else if (pageType == 'modify') {
            $scope.load(id);
        } else if (pageType == 'view') {
            $scope.load(id);
            $('input,textarea,select').attr('disabled', 'disabled');
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);
/**
 * Created by Michael on 2016-01-05 00:16:23.
 */
(function (window, angular, $) {
    var app = angular.module('oa.blackList.edit', [
        'oa.blackList',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, BlackListService, BlackListParam) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();
        // 黑户类型
        BlackListParam.type(function (data) {
            $scope.types = data || [];
            $scope.types.unshift({name: '请选择'});
        });

        // 保存
        $scope.save = function (createNew) {
            var promise = BlackListService.save($scope.beans, function (data) {
                if (data && data['success'] == true) { //保存成功
                    CommonUtils.addTab('update');
                    if (createNew === true) {
                        $scope.beans = {type: $scope.beans.type};
                    } else {
                        $scope.form.$setValidity('committed', false);
                        CommonUtils.back();
                    }
                } else {
                    AlertFactory.saveError($scope, data);
                }
            });
            CommonUtils.loading(promise, '保存中...');
        };

        // 更新
        $scope.update = function () {
            var promise = BlackListService.update($scope.beans, function (data) {
                if (data && data['success'] == true) { // 更新成功
                    CommonUtils.addTab('update');
                    $scope.form.$setValidity('committed', false);
                    CommonUtils.back();
                }
                AlertFactory.updateError($scope, data);
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id) {
            var promise = BlackListService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
            });
            CommonUtils.loading(promise, 'Loading...');
        };


        if (pageType == 'add') {
            $scope.beans = {};
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
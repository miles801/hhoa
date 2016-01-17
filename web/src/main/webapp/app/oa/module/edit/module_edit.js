/**
 * Created by Michael on 2016-01-05 13:30:15.
 */
(function (window, angular, $) {
    var app = angular.module('oa.module.edit', [
        'oa.module',
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree',
        'eccrm.base.employee.modal'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModuleService, ModuleParam, EmployeeModal) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();

        var defaults = {
            status: 'ACTIVE',
            sequenceNo: 1,
            ownerId: CommonUtils.loginContext().id,
            ownerName: CommonUtils.loginContext().employeeName
        };
        // 模块类型
        ModuleParam.type(function (data) {
            $scope.types = data;
            $scope.types.unshift({name: '请选择....'});
        });
        // 保存
        $scope.save = function (createNew) {
            var promise = ModuleService.save($scope.beans, function (data) {
                if (data && data['success'] == true) { //保存成功
                    if (createNew === true) {
                        $scope.beans = angular.extend({}, defaults);
                    } else {
                        $scope.form.$setValidity('committed', false);
                        CommonUtils.addTab('update');
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
            var promise = ModuleService.update($scope.beans, function (data) {
                if (data && data['success'] == true) { // 更新成功
                    $scope.form.$setValidity('committed', false);
                    CommonUtils.addTab('update');
                    CommonUtils.back();
                }
                AlertFactory.updateError($scope, data);
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id, callback) {
            var promise = ModuleService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
                if($scope.beans.logo) {
                    setLogo($scope.beans.logo);
                }
                angular.isFunction(callback) && callback();

            });
            CommonUtils.loading(promise, 'Loading...');
        };

        // 选择员工
        $scope.pickEmp = function () {
            EmployeeModal.pickEmployee({}, function (emp) {
                $scope.beans.ownerId = emp.id;
                $scope.beans.ownerName = emp.employeeName;
            });
        };

        // 清除所选员工
        $scope.clearEmp = function () {
            $scope.beans.ownerId = null;
            $scope.beans.ownerName = null;
        };

        var setLogo = function (id) {
            $scope.beans.attachmentIds = id;
            $scope.beans.logo = id;
            $('#logo').show().html('<img src="' + CommonUtils.contextPathURL('/attachment/view?id=' + id) + '" style="width: 50px;height: 50px;"/>');
        };
        // 附件
        $scope.options = {
            bid: id,
            showTable: false,
            swfOption: {
                fileTypeExts: '*.png;*.jpg;*.gif',
                fileTypeDesc: '图片文件'
            },
            labelText: 'LOGO',
            onSuccess: function (at) {
                var ids = $scope.options.getAttachment();
                if (ids.length > 1) {
                    $scope.options.remove(ids[0]);
                }
                setLogo(at.id);
            },
            btype: 'module_logo'
        };

        if (pageType == 'add') {
            $scope.beans = angular.extend({}, defaults);
        } else if (pageType == 'modify') {
            $scope.load(id);
        } else if (pageType == 'view') {
            $scope.load(id, function () {
                $('input,textarea,select').attr('disabled', 'disabled');
            });
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);
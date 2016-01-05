/**
 * Created by Michael on 2016-01-05 15:14:20.
 */
(function (window, angular, $) {
    var app = angular.module('oa.knowledge.edit', [
        'oa.knowledge',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, KnowledgeService, KnowledgeParam) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();

        var defaults = {
            status: 'ACTIVE'
        };
        // 富文本编辑器
        var editor = KindEditor.create('#content');

        $scope.uploadOptions = {
            bid: id,
            viewLink: true,
            maxFile: 10
        };

        // 知识类型
        KnowledgeParam.type(function (data) {
            $scope.types = data || [];
            $scope.types.unshift({name: '请选择'});
        });

        // 状态
        KnowledgeParam.status(function (data) {
            $scope.status = data || [];
        });

        var validAndWrap = function () {
            var content = editor.html();
            if (!content) {
                alert('内容不能为空!');
                return false;
            }
            $scope.beans.content = content;
            $scope.beans.attachmentIds = $scope.uploadOptions.getAttachment().join(',');
            return true;
        };

        // 保存
        $scope.save = function (createNew) {
            if (!validAndWrap()) {
                return;
            }
            var promise = KnowledgeService.save($scope.beans, function (data) {
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
            if (!validAndWrap()) {
                return;
            }
            var promise = KnowledgeService.update($scope.beans, function (data) {
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
            var promise = KnowledgeService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
                editor.html($scope.beans.content || ''); // 设置富文本内容

                angular.isFunction(callback) && callback();
            });
            CommonUtils.loading(promise, 'Loading...');
        };

        $scope.back = CommonUtils.back;


        if (pageType == 'add') {
            $scope.beans = angular.extend({}, defaults);
        } else if (pageType == 'modify') {
            $scope.load(id);
        } else if (pageType == 'detail') {
            $scope.uploadOptions.readonly = true;
            $scope.load(id, function () {
                $('input,textarea,select').attr('disabled', 'disabled');
                editor.readonly(true);
            });
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);
/**
 * Created by Michael on 2016-01-18 05:32:13.
 */
(function (window, angular, $) {
    var app = angular.module('oa.article.edit', [
        'oa.article',
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.module'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ArticleService, ArticleParam, ModuleService, ModuleModal) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();
        // 状态
        ArticleParam.status(function (data) {
            $scope.status = data;
        });

        var attachmentIds = [];
        // 初始化富文本编辑器
        var editor = KindEditor.create('#navPageContent', {
            uploadJson: CommonUtils.contextPathURL('/attachment/upload2?dataType=jsp&thumb=true&width=800&height=500'),
            afterUpload: function (url, obj) {
                $scope.$apply(function () {
                    attachmentIds.push(obj.id)
                });
            }
        });

        // 保存
        $scope.save = function () {
            $scope.beans.content = editor.html();
            $scope.form.$setValidity('committed', false);
            $scope.beans.attachmentIds = attachmentIds.join(',');
            var promise = ArticleService.save($scope.beans, function (data) {
                CommonUtils.addTab('update');
                CommonUtils.back();
            });
            CommonUtils.loading(promise, '保存中...');
        };

        $scope.back = CommonUtils.back;

        // 更新
        $scope.update = function () {
            $scope.form.$setValidity('committed', false);
            $scope.beans.content = editor.html();
            $scope.beans.attachmentIds = attachmentIds.join(',');
            var promise = ArticleService.update($scope.beans, function (data) {
                CommonUtils.addTab('update');
                CommonUtils.back();
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id, callback) {
            var promise = ArticleService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
                editor.html($scope.beans.content || '');
                angular.isFunction(callback) && callback();
            });
            CommonUtils.loading(promise, 'Loading...');
        };

        // 选择模块
        $scope.pickModule = function () {
            ModuleModal.pick(function (module) {
                $scope.beans.moduleId = module.id;
                $scope.beans.moduleName = module.name;
            });
        };

        // 清空模块
        $scope.clearModule = function () {
            $scope.beans.moduleId = null;
            $scope.beans.moduleName = null;
        };

        $scope.options = {
            maxFile: 50,
            bid: id,
            swfOption: {
                fileTypeDesc: '图片'
            },
            onSuccess: function (att) {
                attachmentIds.push(att.id)
            }
        };

        if (pageType == 'add') {
            $scope.beans = {
                authorId: CommonUtils.loginContext().id,
                authorName: CommonUtils.loginContext().employeeName,
                status: 'ACTIVE'
            };
            // 如果在新建的时候指定了所属版块
            var moduleId = $('#moduleId').val();
            if (moduleId) {
                var promise = ModuleService.get({id: moduleId}, function (data) {
                    data = data.data;
                    if (!data) {
                        AlertFactory.error('错误的访问:版块[' + moduleId + ']不存在!');
                        CommonUtils.delay(window.history.back, 3000);
                        return;
                    }
                    $scope.beans.moduleId = data.id;
                    $scope.beans.moduleName = data.name;
                });
                CommonUtils.loading(promise);

            }
        } else if (pageType == 'modify') {
            $scope.load(id);
        } else if (pageType == 'detail') {
            $scope.options.readonly = true;
            $scope.load(id, function () {
                $('input,textarea,select').attr('disabled', 'disabled');
                editor.readonly(true);
            });
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);
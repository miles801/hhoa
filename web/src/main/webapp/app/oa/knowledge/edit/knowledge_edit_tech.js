/**
 * Created by Michael on 2016-01-05 15:14:20.
 */
(function (window, angular, $) {
    var app = angular.module('oa.knowledge.tech', [
        'oa.knowledge',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, KnowledgeService,
                                     KnowledgeParam, ModalFactory) {

        var pageType = $scope.pageType = $('#pageType').val();
        var id = $('#id').val();

        var defaults = {
            type: 'technology',    // 业务系统
            status: 'ACTIVE',
            content: '_'
        };

        // 重置关键字
        var resetKeywords = function () {
            var names = [];
            var ids = [];
            angular.forEach($scope.attachments || [], function (o) {
                ids.push(o.id);
                names.push(o.fileName);
            });
            $scope.beans.content = ids.join(',');
            $scope.beans.keywords = names.join(',');
        };

        /**
         * 获取文件后缀（类型）
         * @param name 文件名称
         * @returns {*} 类型
         */
        var getFileExt = function (name) {
            name = name || '';
            var endName = name.substr(name.lastIndexOf('.') + 1);
            var fileExt;
            if (endName == 'doc' || endName == 'docx') {
                fileExt = "doc";
            } else if (endName == 'xls' || endName == 'xlsx') {
                fileExt = "xls";
            } else if (endName == 'ppt' || endName == 'pptx') {
                fileExt = "ppt";
            } else if (endName == 'pdf') {
                fileExt = "pdf";
            } else if (endName == 'txt') {
                fileExt = "txt";
            } else {
                fileExt = "file";
            }
            return fileExt;
        };

        $scope.uploadOptions = {
            bid: id,
            maxFile: 20,
            showTable: false,
            showLabel: false,
            fileSizeLimit: 2000 * 1000 * 10, // 20M
            swfOption: {
                buttonText: '上传文件',
                fileTypeExts: '*.xls;*.xlxs;*.pdf;*.doc;*.docx;*.ppt;*.pptx;*.txt'
            },
            onSuccess: function (attachment) {
                // 设置文件的图标
                var name = attachment.fileName;
                var ext = getFileExt(name);
                attachment.fileRealType = ext;
                $scope.attachments.push(attachment);
                // 加入关键字
                resetKeywords();
            }
        };

        // 保存附件
        $scope.attachments = [];

        // 移除附件
        $scope.removeAttachment = function (id, index) {
            ModalFactory.confirm({
                scope: $scope,
                content: '移除附件后无法恢复，请确认?',
                callback: function () {
                    $scope.uploadOptions.remove(id, function () {
                        $scope.attachments.splice(index, 1);
                        resetKeywords();
                    });
                }
            });
        };


        // 保存
        $scope.save = function (createNew) {
            var attachmentIds = $scope.uploadOptions.getAttachment();
            if (!attachmentIds || attachmentIds.length < 1) {
                AlertFactory.warning('请上传附件!');
                return;
            }
            resetKeywords();
            $scope.beans.attachmentIds = attachmentIds.join(',');
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
            resetKeywords();
            var attachmentIds = $scope.uploadOptions.getAttachment();
            if (!attachmentIds || attachmentIds.length < 1) {
                AlertFactory.warning('请上传附件!');
                return;
            }
            $scope.beans.attachmentIds = attachmentIds.join(',');
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
                var content = ($scope.beans.content || '').split(',');
                var names = ($scope.beans.keywords || '').split(',');
                angular.forEach(names, function (o, index) {
                    $scope.attachments.push({id: content[index], fileName: o, fileRealType: getFileExt(o)});
                });
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
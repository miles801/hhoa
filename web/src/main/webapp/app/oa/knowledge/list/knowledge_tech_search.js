/**
 * Created by Michael on 2016-01-05 15:14:20.
 */
(function (window, angular, $) {
    var app = angular.module('oa.knowledge.searchTech', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'base.attachment',
        'oa.knowledge'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, ModalFactory, AlertFactory, KnowledgeService,
                                     $sce, AttachmentService) {

        // 隐藏标题栏
        $('ul.nav.nav-tabs', window.parent.document).hide();

        $scope.condition = {
            status: 'ACTIVE',
            type: 'technology'
        };

        $('#keywordsOrTitle').on('keydown', function (e) {
            var keyCode = e.which || e.keyCode;
            if (keyCode == 13) {
                e.preventDefault();
                $('.btn-search').click();
            }
        });

        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        var setFileName = function (name) {
            var fileRealType;
            var endName = name.substr(name.lastIndexOf('.') + 1);
            if (endName == 'doc' || endName == 'docx') {
                fileRealType = "doc";
            } else if (endName == 'xls' || endName == 'xlsx') {
                fileRealType = "xls";
            } else if (endName == 'ppt' || endName == 'pptx') {
                fileRealType = "ppt";
            } else if (endName == 'pdf') {
                fileRealType = "pdf";
            } else if (endName == 'txt') {
                fileRealType = "txt";
            } else {
                fileRealType = "file";
            }
            return fileRealType;
        };


        $scope.pager = {
            limit: 50,
            fetch: function () {
                var param = angular.extend({start: this.start, limit: this.limit}, $scope.condition);
                $scope.beans = [];
                return CommonUtils.promise(function (defer) {
                    var promise = KnowledgeService.pageQuery(param, function (data) {
                        $scope.beans = data.data || {total: 0};
                        var beans = [];
                        defer.resolve($scope.beans);
                        angular.forEach($scope.beans.data || [], function (o) {
                            var ids = (o.content || '').split(',');
                            var names = (o.keywords || '').split(',');
                            angular.forEach(ids, function (id, index) {
                                beans.push({id: id, fileName: names[index], fileRealType: setFileName(names[index])});
                            })
                        });
                        $scope.attachments = beans;

                        // 没有结果
                        $scope.noresult = beans.length === 0;
                        $scope.errorContent = $scope.condition.keywords;
                    });
                    CommonUtils.loading(promise);
                });
            },
            finishInit: function () {
                this.query();
            }
        };

        // 重置查询条件
        $scope.reset = function () {
            $scope.condition = angular.extend({}, defaults);
        };

        // 查看知识
        $scope.view = function (id) {
            ModalFactory.create({
                template: CommonUtils.contextPathURL('app/oa/knowledge/list/knowledge_search.tpl.html'),
                callback: function () {
                    var promise = KnowledgeService.get({id: id}, function (data) {
                        $('.modal .modal-body').html((data.data || {}).content || '');
                    });
                    CommonUtils.loading(promise);
                }
            });
        };

        $scope.html = function (content) {
            return $sce.trustAsHtml(content);
        };

    });
})(window, angular, jQuery);
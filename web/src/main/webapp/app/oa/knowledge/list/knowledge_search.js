/**
 * Created by Michael on 2016-01-05 15:14:20.
 */
(function (window, angular, $) {
    var app = angular.module('oa.knowledge.search', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.knowledge'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, ModalFactory, AlertFactory, KnowledgeService, $sce) {

        // 隐藏标题栏
        $('ul.nav.nav-tabs', window.parent.document).hide();

        $scope.condition = {
            status: 'ACTIVE',
            type: 'business'
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

        $scope.pager = {
            limit: 5,
            fetch: function () {
                var param = angular.extend({start: this.start, limit: this.limit}, $scope.condition);
                return CommonUtils.promise(function (defer) {
                    var promise = KnowledgeService.pageQuery(param, function (data) {
                        if ($scope.pager.start == 0) {
                            $scope.beans = data.data || {total: 0};
                        } else {
                            angular.forEach(data.data.data || [], function (o) {
                                $scope.beans.data.push(o)
                            });
                        }

                        defer.resolve($scope.beans);
                        // 没有结果
                        $scope.noresult = $scope.beans.total == 0;
                        $scope.errorContent = $scope.condition.keywordsOrTitle;
                    });
                    CommonUtils.loading(promise);
                });
            }
        };


        // 重置查询条件
        $scope.reset = function () {
            $scope.condition = angular.extend({}, defaults);
        };

        $scope.loadMore = function () {
            $scope.pager.next();
        };

        // 查看知识
        $scope.view = function (id) {
            ModalFactory.create({
                template: CommonUtils.contextPathURL('app/oa/knowledge/list/knowledge_search.tpl.html'),
                callback: function (scope) {
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
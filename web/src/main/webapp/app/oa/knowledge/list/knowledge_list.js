/**
 * Created by Michael on 2016-01-05 15:14:20.
 */
(function (window, angular, $) {
    var app = angular.module('oa.knowledge.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.knowledge'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, ModalFactory, AlertFactory, KnowledgeService,KnowledgeParam) {
        var defaults = {};

        // 知识类型
        KnowledgeParam.type(function(data){
            $scope.types = data || [];
            $scope.types.unshift({name: '全部'});
        });
        // 状态
        KnowledgeParam.status(function(data){
            $scope.status = data || [];
            $scope.status.unshift({name: '全部'});
        });

        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                $scope.beans = [];
                return CommonUtils.promise(function (defer) {
                    var promise = KnowledgeService.pageQuery(param, function (data) {
                        param = null;
                        $scope.beans = data.data || {total: 0};
                        defer.resolve($scope.beans);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            }
        };

        // 重置查询条件
        $scope.reset = function () {
            $scope.condition = angular.extend({}, defaults);
        };

        /**
         * 返回被选中的行的ID
         */
        function getCheckedIds() {
            return $.map($scope.items, function (o) {
                return o.id;
            }).join(',');
        }

        $scope.remove = function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '确定要执行【批量启动】操作吗?',
                callback: function () {
                    var ids = id || getCheckedIds();
                    var promise = KnowledgeService.batchActive({ids: ids}, $scope.query);
                    CommonUtils.loading((promise));
                }
            });
        };

        // 批量启动
        $scope.batchActive = function () {
            ModalFactory.confirm({
                scope: $scope,
                content: '确定要执行【批量启动】操作吗?',
                callback: function () {
                    var ids = getCheckedIds();
                    var promise = KnowledgeService.batchActive({ids: ids}, $scope.query);
                    CommonUtils.loading((promise));
                }
            });
        };

        // 批量注销
        $scope.batchCancel = function () {
            ModalFactory.confirm({
                scope: $scope,
                content: '确定要执行【批量注销】操作吗?',
                callback: function () {
                    var ids = getCheckedIds();
                    var promise = KnowledgeService.batchActive({ids: ids}, $scope.query);
                    CommonUtils.loading((promise));
                }
            });
        };

        $scope.reset();

        // 新增知识
        $scope.add = function () {
            CommonUtils.addTab({
                title: '新增知识',
                url: '/oa/knowledge/add',
                onUpdate: $scope.query
            });
        };

        // 更新知识
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新知识',
                url: '/oa/knowledge/modify?id=' + id,
                onUpdate: $scope.query
            });
        };
        // 查看知识
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看知识',
                url: '/oa/knowledge/detail?id=' + id
            });
        };
    });
})(window, angular, jQuery);
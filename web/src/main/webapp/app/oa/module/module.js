/**
 * 模块
 * Created by Michael on 2016-01-05 13:30:15.
 */
(function (angular) {
    var app = angular.module('oa.module', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.base.param'
    ]);

    app.service('ModuleService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/oa/module/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save', attachmentIds: '@attachmentIds'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update', attachmentIds: '@attachmentIds'}, isArray: false},

            // 根据id查询信息
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 分页查询
            pageQuery: {
                method: 'POST',
                params: {method: 'pageQuery', limit: '@limit', start: '@start', orderBy: 'sequenceNo'},
                isArray: false
            },

            // 根据id字符串（使用逗号分隔多个值）
            deleteByIds: {method: 'DELETE', params: {method: 'delete', ids: '@ids'}, isArray: false}
        })
    });

    app.service('ModuleModal', function ($modal, CommonUtils, ModuleService) {
        return {
            pick: function (callback) {
                var modal = $modal({
                    template: CommonUtils.contextPathURL('/app/oa/module/list/modal-employee.ftl.html'),
                    backdrop: 'static'
                });
                var $scope = modal.$scope;
                $scope.pager = {
                    limit: 5,
                    fetch: function () {
                        return CommonUtils.promise(function (defer) {
                            var obj = angular.extend({}, $scope.condition, {
                                start: $scope.pager.start,
                                limit: $scope.pager.limit,
                                status: 'ACTIVE'
                            });
                            var promise = ModuleService.pageQuery(obj);
                            CommonUtils.loading(promise, '加载中...', function (data) {
                                data = data.data || {};
                                $scope.beans = data;
                                // set logo url
                                angular.forEach($scope.beans.data||[],function(o){
                                    if(o.logo) {
                                        o.logoUrl = CommonUtils.contextPathURL('/attachment/view?id=' + o.logo);
                                    }
                                });
                                defer.resolve(data.total);
                            }, $scope);
                        });
                    }
                };

                // 查询
                $scope.query = function () {
                    $scope.pager.query();
                };

                // 点击确认
                $scope.confirm = function () {
                    if (angular.isFunction(callback)) {
                        callback.call($scope, $scope.selected);
                        modal.hide();
                    }
                }
            }
        }
    });

    app.service('ModuleParam', function (ParameterLoader) {
        return {
            /**
             * 模块类型
             */
            type: function (callback) {
                ParameterLoader.loadBusinessParam('OA_MKLX', callback);
            }
        };
    });

})(angular);

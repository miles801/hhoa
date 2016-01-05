/**
 * 黑户
 * Created by Michael on 2016-01-05 00:16:23.
 */
(function (angular) {
    var app = angular.module('oa.blackList', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.base.param'
    ]);

    app.service('BlackListService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/oa/blackList/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update'}, isArray: false},

            // 根据id查询信息
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 分页查询
            pageQuery: {
                method: 'POST',
                params: {method: 'pageQuery', limit: '@limit', start: '@start'},
                isArray: false
            },

            // 根据id字符串（使用逗号分隔多个值）
            deleteByIds: {method: 'DELETE', params: {method: 'delete', ids: '@ids'}, isArray: false}
        })
    });

    app.service('BlackListParam', function (ParameterLoader) {
        return {
            /**
             * 黑户类型
             */
            type: function (callback) {
                ParameterLoader.loadBusinessParam('OA_HHLX', callback);
            }
        };
    });

})(angular);

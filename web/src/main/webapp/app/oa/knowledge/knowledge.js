/**
 * 知识
 * Created by Michael on 2016-01-05 15:14:20.
 */
(function (angular) {
    var app = angular.module('oa.knowledge', [
        'eccrm.base.param',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.service('KnowledgeService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/oa/knowledge/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save', attachmentIds: '@attachmentIds'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update', attachmentIds: '@attachmentIds'}, isArray: false},

            // 根据id查询信息
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 分页查询
            pageQuery: {
                method: 'POST',
                params: {method: 'pageQuery', limit: '@limit', start: '@start'},
                isArray: false
            },

            // 根据id字符串（使用逗号分隔多个值）
            deleteByIds: {method: 'DELETE', params: {method: 'delete', ids: '@ids'}, isArray: false},
            // 批量启用
            batchActive: {method: 'POST', params: {method: 'active', ids: '@ids'}, isArray: false},
            // 批量注销
            batchCancel: {method: 'POST', params: {method: 'cancel', ids: '@ids'}, isArray: false}
        })
    });

    app.service('KnowledgeParam', function (ParameterLoader) {
        return {
            /**
             * 状态
             */
            status: function (callback) {
                ParameterLoader.loadSysParam('SP_COMMON_STATE', callback);
            },
            /**
             * 知识类型
             */
            type: function (callback) {
                ParameterLoader.loadBusinessParam('KN_TYPE', callback);
            }
        };
    });

})(angular);

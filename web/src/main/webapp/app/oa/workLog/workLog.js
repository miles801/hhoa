/**
 * 工作日志
 * Created by Michael on 2016-01-20 13:04:49.
 */
(function (angular) {
    var app = angular.module('oa.workLog', [
        'ngResource',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.service('WorkLogService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/oa/workLog/:method'), {}, {
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

    // 工作日志评论
    app.service('WorkLogCommentService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/oa/workLog/:workLogId/comment/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save'}, isArray: false},

            // 查询指定工作日志的评论集合
            queryByWorkLog: {method: 'GET', params: {workLogId: '@workLogId'}, isArray: false}

        })
    });

    app.service('WorkLogParam', function (ParameterLoader) {
        return {};
    });

})(angular);

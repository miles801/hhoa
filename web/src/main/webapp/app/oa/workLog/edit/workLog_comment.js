/**
 * Created by Michael on 2016-01-20 13:04:49.
 */
(function (window, angular, $) {
    var app = angular.module('oa.workLog.comment', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.workLog'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, WorkLogService, $http, AlertFactory, WorkLogCommentService) {
        $scope.condition = {};

        $scope.hasCommentRight = false;

        // 查询是否具有评论权限
        $http.get(CommonUtils.contextPathURL('/auth/accreditFunc/hasPermission?code=OPERATE_WORK_LOG_COMMENT'))
            .success(function (data) {
                $scope.hasCommentRight = data.data;
            });

        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        // 延迟触发发日志时的blur
        $scope.delayFocus = function () {
            CommonUtils.delay(function () {
                $scope.focused = false;
                $scope.$apply();
            }, 100);
        };

        // 新增日志
        $scope.addWorkLog = function () {
            if (!$scope.content) {
                $scope.focused = true;
                return;
            }
            var promise = WorkLogService.save({content: $scope.content}, function (data) {
                if (data.success) {
                    AlertFactory.success(null, '发布成功!');
                    $scope.content = null;
                    $scope.query();
                } else {
                    AlertFactory.error(null, (data.error || data.fail || data.data || ''), '发布失败!');
                }
            });
            CommonUtils.loading(promise);

        };

        // 延迟触发评论时的blur
        $scope.focusout = function (foo) {
            CommonUtils.delay(function () {
                foo.focused = false;
                $scope.$apply();
            }, 100);
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                $scope.beans = [];
                return CommonUtils.promise(function (defer) {
                    var promise = WorkLogService.pageQuery(param, function (data) {
                        param = null;
                        $scope.beans = data.data || {total: 0};
                        // 加载评论
                        angular.forEach($scope.beans.data || [], function (o) {
                            if (o.commentCounts > 0) {
                                $scope.loadComment(o);
                            }
                        });
                        defer.resolve($scope.beans);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            },
            finishInit: function () {
                this.query();
            }
        };

        /**
         * 加载日志的评论
         * @param foo 原日志对象
         */
        $scope.loadComment = function (foo) {
            var promise = WorkLogCommentService.queryByWorkLog({workLogId: foo.id}, function (data) {
                foo.commentCounts = (foo.commentCounts || 0) + 1;
                foo.comments = data.data || [];
            });
            CommonUtils.loading(promise);
        };

        // 评论
        $scope.addComment = function (foo) {
            var comment = foo.comment;
            if (!comment) {
                AlertFactory.error('请输入评论内容!');
                return;
            }
            var promise = WorkLogCommentService.save({workLogId: foo.id, content: comment}, function (data) {
                if (data.success) {
                    AlertFactory.success(null, '评论成功!');
                    $scope.loadComment(foo.id);
                    $scope.focused = false;
                    foo.comment = null;
                    // 重新加载评论
                    $scope.loadComment(foo);
                } else {
                    AlertFactory.error(null, '评论失败!' + (data.error || data.fail || ''));
                }
            });
            CommonUtils.loading(promise);

        };


    });
})(window, angular, jQuery);
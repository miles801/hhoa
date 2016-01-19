/**
 * Created by Michael on 2016-01-18 05:32:13.
 */
(function (window, angular, $) {
    var app = angular.module('oa.article.content', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.article'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ArticleService, CommentService) {

        var id = $('#id').val();
        if (!id) {
            AlertFactory.error(null, '错误的访问!');
            return;
        }

        // 阅读文章
        var viewArticle = function () {
            var promise = ArticleService.view({id: id});
            CommonUtils.loading(promise);
        };

        // 查询文章的评论记录
        var queryComments = function () {
            $scope.comments = [];
            var promise = CommentService.pageQuery({articleId: id}, function (data) {
                data = data.data || {total: 0, data: []};
                $scope.comments = data.data;
            });
            CommonUtils.loading(promise);
        };

        // 查询数据
        var load = function () {
            var promise = ArticleService.get({id: id}, function (data) {
                // 获取文章内容
                $scope.beans = data.data;
                if (!$scope.beans) {
                    AlertFactory.error(null, '文章不存在或者已经被删除!');
                    CommonUtils.delay(window.history.back, 3000);
                    return;
                }
                $('#article-content').html($scope.beans.content);

                // 查询文章的评论
                queryComments();

                // 阅读文章
                viewArticle();
            });
            CommonUtils.loading(promise);
        };


        // 发表评论
        $scope.doComment = function () {
            var comments = editor.text();
            if (!comments) {
                AlertFactory.error(null, '请填写评论内容!');
                return;
            }
            // 保存评论
            var content = editor.html();
            var promise = CommentService.save({
                articleId: id,
                content: content
            }, function () {
                // 重置评论内容
                editor.html('');
                editor.readonly(true);

                // 重新加载评论列表
                queryComments();
            });
            CommonUtils.loading(promise);
        };
        // 加载
        load();

        // 初始化评论框
        var editor = KindEditor.create('#comment')

    });

    // 转换HTML
    app.directive('eccrmHtml', function ($parse) {
        return {
            scope: '=eccrmHtml',
            link: function (scope, elm, attr) {
                var content = attr['eccrmHtml'];
                var html = $parse(content)(scope);
                elm.html(html || '');
            }
        }

    });
})(window, angular, jQuery);
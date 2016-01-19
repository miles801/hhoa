/**
 * Created by Michael on 2016-01-18 05:32:13.
 */
(function (window, angular, $) {
    var app = angular.module('oa.article.content', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'oa.article'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ArticleService, ModalFactory) {

        var id = $('#id').val();
        if (!id) {
            AlertFactory.error(null, '错误的访问!');
            return;
        }
        //查询数据
        var load = function () {
            var promise = ArticleService.get({id: id}, function (data) {
                $scope.beans = data.data;
                if (!$scope.beans) {
                    AlertFactory.error(null, '文章不存在或者已经被删除!');
                    CommonUtils.delay(window.history.back, 3000);
                    return;
                }
                $('#article-content').html($scope.beans.content);

            });
            CommonUtils.loading(promise);
        };


        // 发表评论
        $scope.doComment = function () {
            var comments = editor.content();
            if (!comments) {
                AlertFactory.error(null, '请填写评论内容!');
                return;
            }
            var content = editor.html();
            // FIXME 保存评论
        };
        // 加载
        load();

        // 初始化评论框
        var editor = KindEditor.create('#comment')

    });
})(window, angular, jQuery);
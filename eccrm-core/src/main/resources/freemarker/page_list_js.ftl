/**
* Created by ${author!'CODE GENERATOR'} <#if current??>on ${current}</#if>.
*/
(function (window, angular, $) {
    var app = angular.module('${name}.${entity}.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        '${name}.${entity}'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, ${className}Service) {
        $scope.condition = { };
        //查询数据
        $scope.query = function() {
            $scope.pager.query();
        };

    <#if listPage.allowPager==true>
        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                var promise = ${className}Service.pageQuery(param, function(data){

                });
                CommonUtils.loading(promise, 'Loading...');
            }
        }
    </#if>
    });
})(window, angular, jQuery);
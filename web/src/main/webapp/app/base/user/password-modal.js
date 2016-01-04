/**
 * Created by miles on 14-3-14.
 * dependencies:
 *  angularstrap-v2.0
 *  usergroup.js
 */


(function (angular, window) {
    var app = angular.module('eccrm.base.user.password.modal', [
        'eccrm.base.user',
        'eccrm.base.passwordpolicy'
    ]);
    app.factory('PasswordModal', function ($modal, User, AlertFactory, PasswordPolicyService, $q, Debounce, PasswordPolicy, $filter, CommonUtils) {
            return {
                modifyPwd: function (cfg, callback) {
                    var defaults = {
                        scope: null,
                        callback: null//点击确定后要执行的函数
                    };
                    cfg = angular.extend({}, defaults, cfg);
                    var scope = cfg.scope;
                    if (!scope) throw '使用模态对话框时必须指定scope!';
                    var userId = CommonUtils.loginContext().id;
                    if (!userId) throw '没有获得用户ID!';
                    var modal = $modal({
                        backdrop: 'static',
                        scope: scope,
                        template: CommonUtils.contextPathURL('/app/base/user/template/password-modal-edit.tpl.html')
                    });
                    var that = this;
                    var $scope = modal.$scope;
                    //验证旧密码
                    $scope.checkOldPwd = {
                        delay: 1000,
                        validateMsg: '密码错误!',
                        validateType: 'passwordError',
                        validateFn: function (value) {
                            if (!value) return false;
                            var defer = $q.defer();
                            Debounce.delay(function () {
                                var result = User.checkPassword({
                                    password: $.md5(value)
                                });
                                AlertFactory.handle($scope, result, function (data) {
                                    defer.resolve(data.success || false);
                                })
                            }, 1000);
                            return defer.promise;
                        }
                    };

                    //获得密码策略
                    PasswordPolicyService.get(function (data) {
                        $scope.policy = data;
                        var p = PasswordPolicy.nextEndDate(data.effectivePeriod);
                        if (p === -1) {
                            $scope.endDate = '永久有效';
                        } else {
                            $scope.endDate = $filter('eccrmDate')(p);
                        }
                    });

                    //根据密码策略校验密码
                    $scope.policyCheck = {
                        validateMsg: '不合法的密码!',
                        validateType: 'invalidPassword',
                        validateFn: function (value, current) {
                            if (!value) return false;
                            var result = PasswordPolicy.checkPassword($scope.policy, value);
                            if (!result.success) {
                                current.validateMsg = result.message;
                                return false;
                            }
                            return true;
                        }
                    };
                    //强制校验password2
                    $scope.notifyCheck = function () {
                        $scope.form.password2.$setViewValue($scope.password2);
                    };

                    //密码一致性校验
                    $scope.validatePwd = {
                        validateMsg: '两次密码不一样!',
                        validateType: 'passwordNotMatch',
                        validateFn: function (value) {
                            if (!value) return false;
                            return $scope.password === value;
                        }
                    };
                    $scope.ok = function () {
                        if ($scope.form.$invalid) {
                            AlertFactory.error($scope, '不合法的表单信息!', $scope.form.$error.join(','));
                            return;
                        }
                        var result = User.updatePassword({password: $.md5($scope.password)});
                        AlertFactory.handle($scope, result, function (data) {
                            if (data.success) {
                                if (callback && angular.isFunction(callback)) {
                                    callback.call(that, arguments);
                                }
                                $scope.$hide();
                            }
                        })
                    };
                }
            }
        }
    )
    ;
})(angular, window);
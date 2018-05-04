/**
 * Created by Mr yu on 2017/1/5.
 */
'use strict';
//完成模块的声明
var loginModule = angular.module('loginModule',
    [
        'ng',
        'ui.bootstrap',
        'ngRoute',
        'ui.router',
        'LocalStorageModule',
        'oitozero.ngSweetAlert',
        'monospaced.qrcode'
    ]);

/*loginModule.factory('alertService', ['$rootScope', '$timeout', function ($rootScope, $timeout) {

 $rootScope.alerts = [];

 var alertService = {
 newAlert: function (title, type) {
 var tempMsg = title || "警告";
 if(!title && type){
 tempMsg = type == 'success' ? '成功' : '接口异常' ;
 }
 $rootScope.alerts = [];

 var tempType = type ? type : 'warning';
 var time = tempType == 'success' ? 3000 : 10000;

 var temp = {type: tempType, msg: tempMsg, isAble: true};
 $rootScope.alerts.push(temp);

 if (type != 'danger') {
 $timeout(function () {
 temp.isAble = false;
 $rootScope.alerts.splice(temp, 1);
 }, time);
 }
 },
 closeAlert: function (index) {
 $rootScope.alerts.splice(index, 1);
 },
 closeAll: function () {
 $rootScope.alerts = [];
 }
 };

 return alertService;
 }]);*/
loginModule.config(function($httpProvider,$stateProvider,$uiViewScrollProvider,$urlRouterProvider) {
//用于改变state时跳至顶部
// POST
//$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function (data) {
        /**
         * The workhorse; converts an object to x-www-form-urlencoded serialization.
         * @param {Object} obj
         * @return {String}
         */
        var param = function (obj) {
            var query = '';
            var name, value, fullSubName, subName, subValue, innerObj, i;

            for (name in obj) {
                value = obj[name];

                if (value instanceof Array) {
                    for (i = 0; i < value.length; ++i) {
                        subValue = value[i];
                        fullSubName = name + '[' + i + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value instanceof Object) {
                    for (subName in value) {
                        subValue = value[subName];
                        fullSubName = name + '[' + subName + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value !== undefined && value !== null) {
                    query += encodeURIComponent(name) + '='
                        + encodeURIComponent(value) + '&';
                }
            }

            return query.length ? query.substr(0, query.length - 1) : query;
        };

        return angular.isObject(data) && String(data) !== '[object File]'
            ? param(data)
            : data;
    }];

    $uiViewScrollProvider.useAnchorScroll();
    $urlRouterProvider.otherwise('/login');

    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'login.html',
            controller:'loginController'
        })
        .state('recoverPassword', {
            url: '/recoverPassword',
            templateUrl: 'recoverPassword.html',
            controller:'recoverPwdController'
        })

});


loginModule.controller('loginController',['$scope', '$http','localStorageService','$state','$rootScope','$location','$window','SweetAlert','$uibModal',
    function ($scope, $http, localStorageService,$state,$rootScope,$location,$window,SweetAlert,$uibModal) {
        $scope.loginSys=function () {
            var params = {
                account: $scope.account,
                pwd: $scope.pwd
            };
            $http.get('user/login',{params})
                .success(function(data,status,headers,config){
                    if (data !== -1) {
                        SweetAlert.swal("登录成功", "", "success");
                        localStorageService.set("userInfo", data);
                        $rootScope.user = data;
                       setTimeout(function () {
                            $window.location.href = '/web-logistics/logistics/index.html';
                        }, 1000);
                    }
                    else {
                        SweetAlert.swal("帐号或者密码输入错误请重新输入", "", "warning");
                    }
                })
                .error(function(data,status,headers,config){
                    SweetAlert.swal("帐号或者密码输入错误请重新输入", "", "warning");
                })
        }
           $scope.loginSystem=function () {
                if($scope.account!=""&&$scope.account!=null&&$scope.pwd!=""&&$scope.pwd!=null){
                    var params = {
                        account:$scope.account
                    };
                    $http.get('user/loginCheck',{params})
                        .success(function(data,status,headers,config){
                            if(data===1){
                                SweetAlert.swal(
                                    {title:"该账号已经登陆在操作，是否继续？",
                                        text:"本次登陆会造成之前的登录失效，请谨慎操作！",
                                        type:"warning",
                                        showCancelButton:true,
                                        confirmButtonColor:"#DD6B55",
                                        confirmButtonText:"是的，我要继续！",
                                        cancelButtonText:"让我再考虑一下…",
                                        closeOnConfirm:false,
                                    },
                                    function(isConfirm){
                                        if (isConfirm) {
                                            $scope.loginSys();
                                        }
                                    });
                            }else{
                                $scope.loginSys();
                            }
                        })
                        .error(function(data,status,headers,config){
                            SweetAlert.swal("登录失败", "", "error");
                        })
                }else{
                    swal({title:"提示信息！",
                            text:"账号或密码不能为空。",
                            type:"warning"
                        },function(){

                        }
                    )
                }

            }


    }]);


loginModule.controller('recoverPwdController',['$scope', '$http','localStorageService','$state','$rootScope','$location','$window','SweetAlert','$uibModal',
    function ($scope, $http, localStorageService,$state,$rootScope,$location,$window,SweetAlert,$uibModal) {

    console.log("忘记密码");





    }]);










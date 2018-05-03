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
            controllerUrl:'login',
            controller:'loginController'
        })
        .state('recoverPassword', {
            url: '/recoverPassword',
            templateUrl: 'recoverPassword.html',
            controllerUrl:'recoverPassword',
            controller:'recoverPwdController'
        })

});


loginModule.controller('loginController',['$scope', '$http','localStorageService','$state','$rootScope','$location','$window','SweetAlert','$uibModal',
    function ($scope, $http, localStorageService,$state,$rootScope,$location,$window,SweetAlert,$uibModal) {


        $scope.loginSys=function () {
            $window.location.href ='/web-logistics/logistics/index.html';
        };

      
  



    }]);


loginModule.controller('recoverPwdController',['$scope', '$http','localStorageService','$state','$rootScope','$location','$window','SweetAlert','$uibModal',
    function ($scope, $http, localStorageService,$state,$rootScope,$location,$window,SweetAlert,$uibModal) {

    console.log("忘记密码");





    }]);










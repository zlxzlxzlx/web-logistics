/**
 * Created by Administrator on 2017-07-07.
 */
//首页控制器
app.controller('indexController',['$scope', '$rootScope','$http','httpService','localStorageService','$interval','SweetAlert','$window','baseService',
    function($scope, $rootScope,$http,httpService,localStorageService,$interval,SweetAlert,$window,baseService){

   /*    $rootScope.user=localStorageService.get("userInfo");
        var checkLogin = function(){
            $http.get('/web-logistics/user/loginCheck',{params:{
                account:$rootScope.user.user_name,
            }}).success(function (data) {
                if(data===1){
                    $interval.cancel(timer);
                    SweetAlert.swal({
                        title:"下线通知",
                        text:"您的账号已在其它地方登录，返回登录页",
                        type:"error"
                    },function(){
                        $scope.exit();
                    });
                }
            });
        };
        var timer = $interval(checkLogin,3000);
        $scope.exit=function () {
            localStorageService.clearAll();
            SweetAlert.swal("退出成功", "", "success");
            $window.location.href ='/web-logistics';
        };
*/
    }]);
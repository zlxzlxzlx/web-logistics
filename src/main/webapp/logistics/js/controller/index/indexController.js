/**
 * Created by Administrator on 2017-07-07.
 */
//首页控制器
app.controller('indexController',['$scope', '$rootScope','$http','httpService','localStorageService','$interval','SweetAlert','$window','baseService',
    function($scope, $rootScope,$http,httpService,localStorageService,$interval,SweetAlert,$window,baseService){
     $scope.userInfo = localStorageService.get("userInfo");
     var param =
        {
            roleId:$scope.userInfo.mark
        };
     var menu = [];
    httpService.getOneRow(param,'role/getOneRole').then(
        function (result) {
            $scope.roleName = result.name;
            menu = result.powerId.split('/');
            for(var i=0;i<menu.length;i++){
                if(menu[i]=="用户管理"){
                    $scope.managerUserInfo = true;
                }
                if(menu[i]=="学校管理"){
                    $scope.school = true;
                }
                if(menu[i]=="学院管理"){
                    $scope.college = true;
                }
                if(menu[i]=="课程管理"){
                    $scope.course = true;
                }
                if(menu[i]=="课程信息"){
                    $scope.grade = true;
                }
                if(menu[i]=="课表查询"){
                    $scope.schedule = true;
                }
                if(menu[i]=="班级管理"){
                    $scope.manageClass = true;
                }
                if(menu[i]=="授课安排"){
                    $scope.teach = true;
                }
                if(menu[i]=="学生管理"){
                    $scope.student = true;
                }
                if(menu[i]=="课堂点名"){
                    $scope.ClassName = true;
                }
                if(menu[i]=="期末成绩"){
                    $scope.termGrade = true;
                }
                if(menu[i]=="考勤参数"){
                    $scope.SetParameters = true;
                }
                if(menu[i]=="教室管理"){
                    $scope.ClassRoom = true;
                }
                if(menu[i]=="角色权限管理"){
                    $scope.powerManage = true;
                }
                if(menu[i]=="数据字典"){
                    $scope.dataManage = true;
                }
                if(menu[i]=="菜单管理"){
                    $scope.menuManage = true;
                }
            }

        },function () {

        }
    );
        var param1 = {
                id:$scope.userInfo.id
            };
        httpService.getOneRow(param1,"user/getOneRow").then(
            function (result) {
                 console.log(result);
                 $scope.cname = result.cname;
                 $scope.sname = result.sname;
            },function () {

           }
        )

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
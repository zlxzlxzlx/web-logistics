/**
 * Created by zlx on 2018/5/2.
 */
app.controller('ClassNameManageCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope','httpService','$interval',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope,httpService,$interval) {

        $rootScope.user=localStorageService.get("userInfo");
        $scope.lateNum="";
        $scope.lateValue="";
        $scope.sickNum="";
        $scope.sickValue="";
        $scope.thinkNum="";
        $scope.thinkValue="";
        $scope.absenteeismNum="";
        $scope.absenteeismValue="";
        $scope.late="";
        $scope.showMark=1;
        $scope.getCourseForClass=function () {
            var params={
                user_id:$rootScope.user.id
            }
            httpService.getAll(params,'course/getCourseForClass').then(function (result) {
                $scope.Class=result;
            },function () {

            })
        };
        $scope.getCourseForClass();
        $scope.root=[];
       
        $scope.queryList=function () {

            var params={
                course_id:$scope.course_id
            };
            $http({
                method : 'POST',
                url : '../elective/getAllStudentByCourseIdForClass',
                data: params
            }).success(function(result, status, headers, config) {
               if(result.length>0){
                   $scope.root=result;
               }

        }).error(function(data, status, headers, config) {

            });
        };
        $scope.queryList();

        //调用时间
        var spanNode = document.getElementById("span");
        setInterval(function () {
          $scope.getTime();
        },1000)
       $scope.getTime=function () {
            var day = new Date();
            var year = day.getFullYear();
            var month = day.getMonth()+1;
            var dat = day.getDate();
            var hours = day.getHours();
            var minitues = day.getMinutes();
            var second = day.getSeconds();
            spanNode.innerHTML =year+"-"+month+"-"+dat+" "+hours+":"+minitues+":"+second;
        };

     $scope.Naming=function (_row,mark) {
         var value;
         if($scope.absenteeismNum==""){
             SweetAlert.swal("请选择一个考勤参数模版", "", "warning");
         }else{
             switch (mark){
                 case 1:
                     value=(1/$scope.absenteeismNum)*$scope.absenteeismValue;
                     break;
                 case 2:
                      value=(1/$scope.lateNum)*$scope.lateValue;
                     break;
                 case 3:
                      value=(1/$scope.sickNum)*$scope.sickValue;
                     break;
                 case 4:
                      value=(1/$scope.thinkNum)*$scope.thinkValue;
                     break;
                 default:
                      value=0;
             }
             var params={
                 elective_id:_row.id,
                 mark:mark,
                 value:value
             }
             $http({
                 method : 'POST',
                 url : '../elective/updateElectiveByClass',
                 data: params
             }).success(function(result, status, headers, config) {
                 if(result==1){
                     SweetAlert.swal("点名成功","","success");
                 }else{
                     SweetAlert.swal("点名失败","","error");
                 }
             }).error(function(data, status, headers, config) {
                 SweetAlert.swal("点名失败","","error");
             });
         }
         

     };
        $scope.getParams=function () {
            $http.get('../params/getAllParams').success(function (result) {
                $scope.AllParams=result;
            }).error(function (result) {

            })
        }
        $scope.getParams();
        $scope.detailParams=function () {
            $scope.showMark=2;
           var params={
               params_id:$scope.params_id
           }
            httpService.getAll(params,'params/getAllParamsById').then(function (result) {
                $scope.lateNum=result.lateNum;
                $scope.lateValue=result.lateValue;
                $scope.sickNum=result.sickNum;
                $scope.sickValue=result.sickValue;
                $scope.thinkNum=result.thinkNum;
                $scope.thinkValue=result.thinkValue;
                $scope.absenteeismNum=result.absenteeismNum;
                $scope.absenteeismValue=result.absenteeismValue;
                $scope.late=result.late;
            },function () {

            })
        };
    }]);
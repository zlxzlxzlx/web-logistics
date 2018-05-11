/**
 * Created by zlx on 2018/5/2.
 */
app.controller('ClassNameManageCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope','httpService','$interval',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope,httpService,$interval) {

        $rootScope.user=localStorageService.get("userInfo");
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
         var params={
             elective_id:_row.id,
             mark:mark
         }
         $http({
             method : 'POST',
             url : '../elective/updateElectiveByClass',
             data: params
         }).success(function(result, status, headers, config) {
         }).error(function(data, status, headers, config) {

         });

     };
    }]);
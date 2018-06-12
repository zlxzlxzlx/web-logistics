/**
 * Created by Administrator on 2018/5/2.
 */

app.controller('addcollegeModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','$uibModalInstance','httpService','SweetAlert','LoadingService','Upload','localStorageService',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,$uibModalInstance,httpService,SweetAlert,LoadingService,Upload,localStorageService) {
        $scope.userInfo = localStorageService.get("userInfo");
        $scope.title="添加学院";
        $scope.cancel=function () {
         $uibModalInstance.dismiss("cancel");
        };
        $scope.schools=[];
        $scope.college_code="";
        $scope.college_name="";
        $scope.college_detail="";

        $scope.submitForm=function () {
            if ($scope.submit_form.$valid) {
                var params={
                  college_code:$scope.college_code,
                  college_name:$scope.college_name,
                  college_detail:$scope.college_detail,
                  user_code:$scope.userInfo.code,
                  school_id:$scope.school_id
                    
                };
         httpService.addRow(params,'college/addCollege').then(function (result) {
                  if(result&&result!=null){
                       $uibModalInstance.dismiss('ok');
                       SweetAlert.swal("操作成功", "", "success");
                   }else{
                       SweetAlert.swal("操作失败", "", "error");
                   }
                },function () {
                  SweetAlert.swal("操作失败", "", "error");
                });
            }

        };
        $scope.getAllSchool=function () {
          
            $http.get('../school/getAllSchoolForSelect')
                .success(function(data,status,headers,config){
                $scope.schools=data;
            }).error(function(data,status,headers,config){
                })
        };
        $scope.getAllSchool();
    }]);
/**
 * Created by Administrator on 2018/5/2.
 */

app.controller('addmajorModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','$uibModalInstance','httpService','SweetAlert','LoadingService','Upload',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,$uibModalInstance,httpService,SweetAlert,LoadingService,Upload) {

        $scope.title="添加专业";
        $scope.cancel=function () {
         $uibModalInstance.dismiss("cancel");
        };
        $scope.major_code="";
        $scope.major_name="";
        $scope.major_detail="";

        $scope.submitForm=function () {
            if ($scope.submit_form.$valid) {
                var params={
                    major_code:$scope.major_code,
                    major_name:$scope.major_name,
                    major_detail:$scope.major_detail
                };
         httpService.addRow(params,'major/addMajor').then(function (result) {
                  if(result&&result!=null){
                       $uibModalInstance.dismiss('ok');
                       SweetAlert.swal("操作成功", "", "success");
                   }else{
                       SweetAlert.swal("操作成功", "", "error");
                   }
                },function () {
                  SweetAlert.swal("操作成功", "", "error");
                });
            }

        };
    }]);
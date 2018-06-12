/**
 * Created by Administrator on 2018/5/2.
 */

app.controller('addschoolModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','$uibModalInstance','httpService','SweetAlert','LoadingService','Upload',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,$uibModalInstance,httpService,SweetAlert,LoadingService,Upload) {

        $scope.title="添加学校";
        $scope.cancel=function () {
         $uibModalInstance.dismiss("cancel");
        };


        $scope.submitForm=function () {
            if ($scope.submit_form.$valid) {
                var params={
                  school_code:$scope.school_code,
                  school_name:$scope.school_name,
                  school_detail:$scope.school_detail
                };
             httpService.addRow(params,'school/addSchool').then(function (result) {
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
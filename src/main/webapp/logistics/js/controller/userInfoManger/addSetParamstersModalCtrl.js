/**
 * Created by Administrator on 2018/5/2.
 */

app.controller('addSetParamstersModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','$uibModalInstance','httpService','SweetAlert','LoadingService','Upload',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,$uibModalInstance,httpService,SweetAlert,LoadingService,Upload) {

        $scope.title="添加考勤参数模版";
        $scope.cancel=function () {
          $uibModalInstance.dismiss('cancel');
        };

        $scope.submitForm=function () {
            if ($scope.submit_form.$valid) {
                var params={
                    late_num:$scope.late_num,
                    late_value:$scope.late_value,
                    absenteeism_num:$scope.absenteeism_num,
                    absenteeism_value:$scope.absenteeism_value,
                    sick_num:$scope.sick_num,
                    sick_value:$scope.sick_value,
                    think_num:$scope.think_num,
                    think_value:$scope.think_value,
                    late:$scope.late
                };
                $http({
                    method:'POST',
                    url:'../params/addSetParams',
                    data:params
                }).success(function(result, status, headers, config) {
                   if(result&&result!=null){
                       $uibModalInstance.dismiss('ok');
                       SweetAlert.swal("操作成功", "", "success");
                   }else{
                       $uibModalInstance.dismiss('ok');
                       SweetAlert.swal("操作失败", "", "error");
                   }
                }).error(function(data, status, headers, config) {
                    $uibModalInstance.dismiss('ok');
                    SweetAlert.swal("操作失败", "", "error");
                });
            }

        };
       
    }]);
/**
 * Created by zlx on 2018/5/2.
 */
app.controller('updateClassRoomModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope','$uibModalInstance',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope,$uibModalInstance) {
        $scope.cancel=function () {
            $uibModalInstance.dismiss("cancel");
        };
        $scope.title="编辑教室";
        $scope.class=localStorageService.get("classRoom");
        $scope.updateRow=localStorageService.get("updateRow");
        $scope.className=$scope.updateRow.class_name;
        $scope.school_id=$scope.updateRow.school_id;
        $scope.submitForm=function () {
            if ($scope.submit_form.$valid) {
                var params={
                    class_name:$scope.className,
                    school_id:$scope.school,
                    id:$scope.updateRow.id
                };
                httpService.addRow(params,'classroom/updateClassRoom').then(function (result) {
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


    }]);
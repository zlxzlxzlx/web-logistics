/**
 * Created by zlx on 2018/5/2.
 */
app.controller('addClassRoomModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope','$uibModalInstance',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope,$uibModalInstance) {
        $scope.cancel=function () {
            $uibModalInstance.dismiss("cancel");
        };
        $scope.title="添加教室";
        $scope.class=localStorageService.get("classRoom");
        $scope.submitForm=function () {
            if ($scope.submit_form.$valid) {
                var params={
                    class_name:$scope.className,
                    school_id:$scope.school
                };
                httpService.addRow(params,'classroom/addClassRoom').then(function (result) {
                    if(result&&result!=null){
                        $uibModalInstance.dismiss('ok');
                        SweetAlert.swal("操作成功", "", "success");
                    }else{
                        SweetAlert.swal("操作失败", "", "error");
                    }
                },function () {
                    SweetAlert.swal("操作失败", "", "error");
                })
            }
        };


    }]);
/**
 * Created by zlx on 2018/5/2.
 */
app.controller('SetParametersController',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService) {

        $scope.root=[];
        $scope.pagination = {
            totalCount: 0,
            totalPage: 0,
            pageSize: 10,
            currentPage: 1,
            PageSizeList: [5, 10, 30]
        };
        //设置考勤参数模版
        $scope.add=function () {
            var modalInstance=$uibModal.open({
                backdrop:'static',
                keyboard:false,
                animation: true,
                templateUrl:'addSetParamstersForm.html',
                controller:'addSetParamstersModalCtrl'

            });
            modalInstance.result.then(function (result) {},function (reason) {
               
            });
        };

        $scope.queryList=function () {
            $http.get('../params/getAllParams').success(function (result) {
                $scope.pagination.totalCount = result.length;
                $scope.pagination.totalPage = result.length/$scope.pagination.pageSize+1;
               $scope.root.data=result;
                console.log(result);
            }).error(function (result) {

            })
        };
        $scope.queryList();
        
        $scope.delRow=function (id) {
            var params={
                id:id
            }
            SweetAlert.swal({
                title: "是否确认删除？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText:"取消",
                closeOnConfirm: false},function(isConfirm) {
                if (isConfirm) {
                    httpService.delRow(params,'params/delParams').then(function (result) {
                        if(result==1){
                            $scope.queryList();
                            SweetAlert.swal("删除成功","","success")
                        }else {
                            SweetAlert.swal("删除失败","","error");
                        }
                    },function () {
                        SweetAlert.swal("删除失败","","error");
                    })
                }
            });

        }
        
    }]);
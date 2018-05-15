/**
 * Created by zlx on 2018/5/2.
 */
app.controller('UserInfoController',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService) {

    $scope.root=[];
        $scope.pagination = {
            totalCount: 0,
            totalPage: 0,
            pageSize: 10,
            currentPage: 1,
            PageSizeList: [5, 10, 30]
        };

        $scope.queryList=function () {
            var params={
                username:$scope.username,
                pageNo: $scope.pagination.currentPage,
                pageSize: $scope.pagination.pageSize
            };
            httpService.getAll(params,'user/showUser').then(function (result) {
                $scope.pagination.totalCount=result.totalCount;
                $scope.pagination.totalPage = result.totalPage;
                $scope.pagination.pageSize = result.pageSize;
                $scope.pagination.currentPage = result.pageNo;
                $scope.root.data=result.data;
            },function () {
                
            })
        };
        $scope.queryList();
        $scope.reset=function () {
            $scope.username="";
            $scope.queryList();
        };
      /*  //新增
        $scope.addUserInfo=function () {
            var modalInstance=$uibModal.open({
                backdrop:'static',
                keyboard:false,
                animation: true,
                templateUrl:'userForm.html',
                controller:'adduserModalCtrl'

            });
            modalInstance.result.then(function (result) {},function (reason) {
                $scope.reset();
            });
        };*/
        //删除
        $scope.delRow=function (id) {
            var params = {
                id:id
            };
            SweetAlert.swal({
                title: "是否确认删除？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText:"取消",
                closeOnConfirm: false},function(isConfirm) {
                if (isConfirm) {
                    httpService.delRow(params, 'user/delUser').then(
                        function (result) {
                            if (result==1) {
                                $scope.reset();
                                SweetAlert.swal("操作成功", "", "success");
                            } else {
                                SweetAlert.swal("操作失败", "", "error");
                            }
                        },function(){
                            SweetAlert.swal("操作失败", "", "error");
                        });
                }
            });
        };

        //全选
        $scope.checked = [];
        $scope.selectAll = function () {
            if($scope.select_all) {
                $scope.checked = [];
                angular.forEach($scope.root.data, function (i) {
                    i.checked = true;
                    $scope.checked.push(i.id);
                });
            }else {
                angular.forEach($scope.root.data, function (i) {
                    i.checked = false;
                    $scope.checked = [];
                });
            }
        };
        //多选
        $scope.selectOne = function () {
            angular.forEach($scope.root.data , function (i) {
                var index = $scope.checked.indexOf(i.id);
                if(i.checked && index === -1) {
                    $scope.checked.push(i.id);
                }
                else if (!i.checked && index !== -1){
                    $scope.checked.splice(index, 1);
                }
            });

            if ($scope.root.data.length === $scope.checked.length) {
                $scope.select_all = true;
            } else {
                $scope.select_all = false;
            }
        };

        //批量删除
        $scope.delRows=function () {
            if($scope.checked.length<0||$scope.checked.length===0){
                SweetAlert.swal("至少选择一条记录，再做删除","","warning");
            }else{
                var params = {
                    ids:$scope.checked
                };
                SweetAlert.swal({
                    title: "是否确认删除？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确认",
                    cancelButtonText:"取消",
                    closeOnConfirm: false
                },function(isConfirm) {
                    if (isConfirm) {
                        httpService.delRows(params, 'user/delUsers').then(
                            function (result) {
                                if (result) {
                                    $scope.reset();
                                    $scope.select_all = false;
                                    SweetAlert.swal("操作成功", "", "success");
                                } else {
                                    SweetAlert.swal("操作失败", "", "error");
                                }
                            });
                    }
                });
            }
        };
    }]);
/**
 * Created by zlx on 2018/5/2.
 */
app.controller('electiveManageCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope) {

        $rootScope.user=localStorageService.get("userInfo");
        $scope.marks=[{id:1,name:"课程名称"},{id:2,name:"课程编号"},{id:3,name:"课程负责人"}];
        $scope.mark=1;
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
              user_id:$rootScope.user.id,
              pageNo: $scope.pagination.currentPage,
              pageSize: $scope.pagination.pageSize,
              keyWord:$scope.keyWord,
              mark:$scope.mark
          }
            $http({
                method : 'POST',
                url : '../elective/getAllElectiveByUserId',
                data: params
            }).success(function(result, status, headers, config) {
                $scope.pagination.totalCount=result.totalCount;
                $scope.pagination.totalPage = result.totalPage;
                $scope.pagination.pageSize = result.pageSize;
                $scope.pagination.currentPage = result.pageNo;
                $scope.root.data=result.data;
            }).error(function(data, status, headers, config) {

            });
        };
        $scope.queryList();
        //重置
        $scope.reset=function () {
            $scope.mark=1;
            $scope.keyWord="";
            $scope.queryList();
        };
        //退选
        $scope.delSelectElective=function (row) {
            SweetAlert.swal({
                title: "是否确认退选？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText:"取消",
                closeOnConfirm: false},function(isConfirm) {
                if (isConfirm) {
                    var params={
                        id:row.id
                    }
                    httpService.getAll(params,'elective/delSelectElective').then(function (result) {
                       if(result==1){
                           $scope.reset();
                           SweetAlert.swal("退选成功", "", "success");
                       }else{
                           SweetAlert.swal("选课失败", "", "error");
                       }
                    },function () {
                        SweetAlert.swal("选课失败", "", "error");
                    })
                }
            });

        };
    }]);
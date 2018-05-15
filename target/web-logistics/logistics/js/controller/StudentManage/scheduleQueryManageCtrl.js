/**
 * Created by zlx on 2018/5/2.
 */
app.controller('scheduleQueryManageCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope) {

        $rootScope.user=localStorageService.get("userInfo");
        $scope.marks=[{id:1,name:"课程名称"},{id:2,name:"课程编号"},{id:3,name:"课程负责人"}];
        $scope.mark=1;
        $scope.root=[];
        $scope.root1=[];
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
                college_id:$rootScope.user.college_id,
                school_id:$rootScope.user.school_id,
                pageNo: $scope.pagination.currentPage,
                pageSize: $scope.pagination.pageSize,
                keyWord:$scope.keyWord,
                mark:$scope.mark
            };

            $http({
                method : 'POST',
                url : '../course/getAllCourseForStudent',
                data: params
            }).success(function(result, status, headers, config) {
                $scope.pagination.totalCount=result.totalCount;
                $scope.pagination.totalPage = result.totalPage;
                $scope.pagination.pageSize = result.pageSize;
                $scope.pagination.currentPage = result.pageNo;
                $scope.root.data=result.data[0];
                $scope.root1.data=result.data[1];
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
    }]);
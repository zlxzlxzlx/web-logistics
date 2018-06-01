/**
 * Created by zlx on 2018/5/2.
 */
app.controller('ClassManageCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope','httpService',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope,httpService) {

        $rootScope.user=localStorageService.get("userInfo");
        $scope.root=[];
        $scope.pagination = {
            totalCount: 0,
            totalPage: 0,
            pageSize: 10,
            currentPage: 1,
            PageSizeList: [5, 10, 30]
        };
        $scope.getCourseForClass=function () {
          var params={
              user_id:$rootScope.user.id
          }
            httpService.getAll(params,'arrange/getCourseForClass').then(function (result) {
                console.log(111,result);
               $scope.Class=result;
            },function () {

            })
        };
        $scope.getCourseForClass();
        
        $scope.queryList=function () {
            var params={
                course_id:$scope.course_id,
                user_name:$scope.user_name,
                teacher_id:$scope.user.id,
                pageNo: 1,
                pageSize:10
            };
            $http({
                method : 'POST',
                url : '../studentCourse/getAllStudentByCourseId',
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
        $scope.reset=function () {
          $scope.user_name="";
            $scope.queryList();
        };
    }]);
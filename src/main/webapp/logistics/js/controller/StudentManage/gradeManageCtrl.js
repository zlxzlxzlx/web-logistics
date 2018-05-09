/**
 * Created by zlx on 2018/5/2.
 */
app.controller('gradeManageCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope) {

        $rootScope.user=localStorageService.get("userInfo");
        $scope.marks=[{id:1,name:"课程名称"},{id:2,name:"课程编号"},{id:3,name:"课程负责人"}];
        $scope.mark=1;
        $scope.college= $rootScope.user.college_id;
        $scope.colleges=[];
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
               school_id:$rootScope.user.school_id
           };
         httpService.getAll(params,'college/getAllCollegeForSelect').then(function (result) {
            $scope.colleges=result;
         },function () {
         });
           var params1={
                college:$scope.college,
                school_id:$rootScope.user.school_id,
                pageNo: $scope.pagination.currentPage,
                pageSize: $scope.pagination.pageSize,
                keyWord:$scope.keyWord,
                mark:$scope.mark
           };
           $http({
               method : 'POST',
               url : '../course/getAllCourseBySchoolId',
               data: params1
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
        //选课
        $scope.elective=function (row) {
            SweetAlert.swal({
                title: "是否确认选课？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText:"取消",
                closeOnConfirm: false},function(isConfirm) {
                if (isConfirm) {
                    var params={
                        course_id:row.id,
                        user_id:$rootScope.user.id
                    };
                    $http({
                        method:'POST',
                        url:'../elective/addElectiveForStudent',
                        data:params
                    }).success(function (result,status,headers,config) {
                       if(result==1){
                           SweetAlert.swal("选课成功", "", "success");
                       }if(result==2){
                            SweetAlert.swal("已选过该课程", "", "warning");
                        }
                    }).error(function (result,status,headers,config) {
                        SweetAlert.swal("选课失败", "", "error");
                    })
                }
            });
          
        };

        //重置
        $scope.reset=function () {
          $scope.mark=1;
          $scope.college= $rootScope.user.college_id;
            $scope.keyWord="";
            $scope.queryList();
        };
    }]);
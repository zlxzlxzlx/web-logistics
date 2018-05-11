/**
 * Created by zlx on 2018/5/2.
 */
app.controller('termGradeManageCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope','httpService','$interval',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope,httpService,$interval) {

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
            httpService.getAll(params,'course/getCourseForClass').then(function (result) {
                $scope.Class=result;
            },function () {

            })
        };
        $scope.getCourseForClass();


        $scope.queryList=function () {
            var params={
                course_id:$scope.course_id,
                user_name:$scope.user_name,
                pageNo: 1,
                pageSize:10
            };
            $http({
                method : 'POST',
                url : '../elective/getAllStudentByCourseId',
                data: params
            }).success(function(result, status, headers, config) {
                $scope.pagination.totalCount=result.totalCount;
                $scope.pagination.totalPage = result.totalPage;
                $scope.pagination.pageSize = result.pageSize;
                $scope.pagination.currentPage = result.pageNo;
                $scope.root.data=result.data;
                $scope.normal_proportion=result.data[0].normal_proportion;
            }).error(function(data, status, headers, config) {

            });
        };
        $scope.queryList();
        $scope.reset=function () {
            $scope.user_name="";
            $scope.queryList();
        };
          $scope.GeneratingFinalResults=function (_row,ordinary_grade,final_exam_garde,final_grade) {
              if($scope.normal_proportion!=undefined && final_grade!= undefined && final_exam_garde!=undefined){
                    var proportion1=$scope.normal_proportion/100;
                   var proportion2=1-proportion1;
                   var result=ordinary_grade*proportion1+final_exam_garde*proportion2;
                  _row.final_grade=result;
                  _row.normal_proportion=$scope.normal_proportion;
                  _row.ordinary_grade=ordinary_grade;
                  _row.final_exam_garde=final_exam_garde;
                  _row.final_exam_proportion=100*proportion2;
              }
        };
        $scope.save=function () {
            var params={
                data:$scope.root.data
            };
            console.log(1111,params);
           httpService.postJson(params,'elective/updateFinalGrade').then(function (result) {
               console.log(111,result);
           },function () {

           })
        
        };
    }]);
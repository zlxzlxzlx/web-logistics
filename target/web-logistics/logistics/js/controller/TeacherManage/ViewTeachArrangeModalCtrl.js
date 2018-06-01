/**
 * Created by zlx on 2018/5/2.
 */
app.controller('ViewTeachArrangeModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope','$uibModalInstance',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope,$uibModalInstance) {

        $scope.user=localStorageService.get("userInfo");
        $scope.ViewTeachArrange=localStorageService.get("ViewTeachArrange");
        $scope.className=$scope.ViewTeachArrange.name;
        $scope.college=$scope.ViewTeachArrange.college_name;
        $scope.userName= $scope.user.userName;
        $scope.week="";
        $scope.classTime="";
        $scope.switchTime="";
        $scope.class_place="";
        $scope.cancel=function () {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.title="查看授课安排";
        $scope.queryList=function () {
          var params={
              user_id:$scope.user.id,
              course_id:$scope.ViewTeachArrange.id
          }
            $http({
                method : 'POST',
                url : '../arrange/getAllArrange',
                data: params
            }).success(function(result, status, headers, config) {
                $scope.week=result[0].start_week+"--"+result[0].end_week;
                $scope.class_place=result[0].class_name;
               for(var i=0;i<result.length;i++){
                   $scope.switchDate(result[i].week);
                   $scope.classTime+= $scope.switchTime+result[i].section_number+"节"+"  "
               }
            }).error(function(data, status, headers, config) {

            });
        };
        $scope.queryList();

        $scope.switchDate=function (TmpWeek) {
            switch(TmpWeek)
            {
                case 1:
                    $scope.switchTime="星期一"
                    break;
                case 2:
                    $scope.switchTime="星期二"
                    break;
                case 3:
                    $scope.switchTime="星期三"
                    break;
                case 4:
                    $scope.switchTime="星期四"
                    break;
                case 5:
                    $scope.switchTime="星期五"
                    break;
                case 6:
                    $scope.switchTime="星期六"
                    break;
                case 7:
                    $scope.switchTime="星期日"
                    break;
                default:

            }
        }

    }]);
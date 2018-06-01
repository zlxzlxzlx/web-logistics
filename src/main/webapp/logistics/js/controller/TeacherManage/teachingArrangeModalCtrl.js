/**
 * Created by zlx on 2018/5/2.
 */
app.controller('teachingArrangeModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope','$uibModalInstance',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope,$uibModalInstance) {

        $scope.user=localStorageService.get("userInfo");
        $scope.title="授课安排";
        $scope.cancel=function () {
            $uibModalInstance.dismiss("cancel");
        };
        $scope.TRrow=localStorageService.get("TRrow");
       $scope.className= $scope.TRrow.name;
       $scope.college= $scope.TRrow.college_name;
       $scope.userName= $scope.user.userName;
        $scope.Weeks=[{id:1,name:"星期一"},{id:2,name:"星期二"},{id:3,name:"星期三"},{id:4,name:"星期四"},{id:5,name:"星期五"},{id:6,name:"星期六"},{id:7,name:"星期日"}];
        $scope.ClassRoom=[];
        $scope.Classes = [{week:"",class_time_start:"",class_time_end:"",isDel:false}];
        $scope.addClassTime=function ($index) {
            $scope.Classes.splice($index + 1, 0,
                {week:"",class_time_start:"",class_time_end:"",isDel:true});
        };
        $scope.delClassTime = function($index){
            $scope.Classes.splice($index, 1);
            $scope.getAllClassRoom();

        }
        //上课地点
        $scope.getAllClassRoom=function () {

            $scope.Week="";
            $scope.classTimeStart="";
            $scope.classTimeEnd="";
              for(var i=0;i<$scope.Classes.length;i++){
                  if($scope.Classes[i].week!=""&&$scope.Classes[i].class_time_start!=undefined&&$scope.Classes[i].class_time_end!=undefined&&$scope.Classes[i].week!=undefined&&$scope.Classes[i].class_time_start!=""&&$scope.Classes[i].class_time_end!="") {
                         $scope.Week += $scope.Classes[i].week + "/";
                         $scope.classTimeStart += $scope.Classes[i].class_time_start + "/";
                         $scope.classTimeEnd += $scope.Classes[i].class_time_end + "/";
                  }
              }
            if($scope.Week!=""&&$scope.classTimeStart!=""&&$scope.classTimeEnd!=""){
                var params={
                    school_id:$scope.TRrow.school_id,
                    week:$scope.Week,
                    classTimeStart:$scope.classTimeStart,
                    classTimeEnd:$scope.classTimeEnd
                }
                $http({
                    method : 'POST',
                    url : '../classroom/getAllClassRoomBSchoolId',
                    data: params
                }).success(function(result, status, headers, config) {
                     $scope.ClassRoom=result;
                }).error(function(data, status, headers, config) {

                });
            }

        };
        $scope.submitForm=function () {
            $scope.Week2="";
            $scope.classTimeStart2="";
            $scope.classTimeEnd2="";
            for(var i=0;i<$scope.Classes.length;i++){
                    $scope.Week2 += $scope.Classes[i].week + "/";
                    $scope.classTimeStart2 += $scope.Classes[i].class_time_start + "/";
                    $scope.classTimeEnd2 += $scope.Classes[i].class_time_end + "/";
            }
            if ($scope.submit_form.$valid) {
                var params = {
                    class_id: $scope.class_place,
                    course_id: $scope.TRrow.id,
                    start_week: $scope.start_time,
                    end_week: $scope.end_time,
                    week: $scope.Week2,
                    classTimeStart: $scope.classTimeStart2,
                    classTimeEnd: $scope.classTimeEnd2,
                    user_id:  $scope.user.id
                }
                $http({
                    method:'POST',
                    url:'../arrange/teachingManage',
                    data:params
                }).success(function (result,status,headers,config) {
                 if(result==1){
                       $uibModalInstance.dismiss("ok");
                        SweetAlert.swal("操作成功", "", "success");
                    }if(result==0){
                        $uibModalInstance.dismiss("ok");
                    SweetAlert.swal("操作失败", "", "error");
                  }
                }).error(function (result,status,headers,config) {
                    $uibModalInstance.dismiss("ok");
                    SweetAlert.swal("操作失败", "", "error");
                })
            }
        };

    }]);
/**
 * Created by Administrator on 2018/5/2.
 */
app.controller('addCourseCtrl',['$scope','$http','httpService','$uibModalInstance','SweetAlert','$rootScope',
    function ($scope,$http,httpService,$uibModalInstance,SweetAlert,$rootScope) {
        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.title='添加课程信息';
        $scope.type=0;
        $scope.disabled = true;
        $scope.teacherInfo = [];
        $scope.majorInfo = [];
        //var re = "";
        $scope.school_id="";
        $scope.teacher_id="";
        $scope.element_id="";
       // $scope.re = [];
        $scope.getTeacherAndMajor = function () {
            httpService.getAll(null,'course/getAllTeacherAndMajor').then(
                function (result) {
                    $scope.re = result;
                    $scope.schoolInfo = result[2];
                },function () {
                    console.log("获取教师(开课单位)失败");
                }
            );
        };
        $scope.getTeacherAndMajor();//同时获取教师和开课单位
        $scope.change=function () {
            $scope.majorInfo=[];
            $scope.teacherInfo=[];
            try {
                if ($scope.school_id!=null){
                    for(var i in $scope.re[1]){
                        if($scope.school_id ==$scope.re[1][i].school_id){
                            $scope.majorInfo.push($scope.re[1][i]);
                        }
                    }
                }
            }catch (e){
            }
        }
       $scope.changeCollege = function () {
               $scope.teacherInfo=[];
               try {
                   if ($scope.element_id!=null){
                       for(var i in $scope.re[0]){
                           if($scope.element_id ==$scope.re[0][i].college_id){
                               $scope.teacherInfo.push($scope.re[0][i]);
                           }
                       }
                   }

               }catch (e){
               }
       }

        $scope.submitForm=function () {
           // LoadingService.show();

            if ($scope.submit_form.$valid) {
                var params = {
                    code:$scope.code,
                    name:$scope.name,
                    class_hour:$scope.class_hour,
                     major_id:$scope.element_id,
                    school_id:$scope.school_id
                };
                console.log("op",params);
                httpService.addRow(params,'course/addCourse').then(
                    function(result){
                        $scope.result=result;
                        $scope.cancel();
                       // LoadingService.hide();
                        SweetAlert.swal("操作成功", "", "success");
                    },function(){
                        SweetAlert.swal("操作失败", "", "error");
                    });
            }

            else {
                SweetAlert.swal("操作失败", "", "error");
            }
        };

    }]);
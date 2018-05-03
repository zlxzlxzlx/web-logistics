/**
 * Created by Administrator on 2018/5/3.
 */
app.controller('updateCourseCtrl',['$scope','data','$uibModalInstance','SweetAlert','httpService',
    function ($scope,data,$uibModalInstance,SweetAlert,httpService) {
    console.log("da",data);
    $scope.title='编辑课程信息';
    $scope.code = data.code?data.code:"";
    $scope.name = data.name?data.name:"";
    $scope.teacher_name = data.teacherName?data.teacherName:"";
    $scope.class_hour = data.classHour?data.classHour:"";
    $scope.type = data.type?data.type:"";
    $scope.start_time = data.startTime?data.startTime:"";
    $scope.end_time = data.endTime?data.endTime:"";
    $scope.class_time = data.classTime?data.classTime:"";
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.submitForm = function () {
        if ($scope.submit_form.$valid){
            var params = {
                id:data.id,
                code:$scope.code,
                name:$scope.name,
                class_hour:$scope.class_hour,
                type:$scope.type,
                teacher_name:$scope.teacher_name,
                class_time:$scope.class_time,
                start_time:$scope.start_time,
                end_time:$scope.end_time,
            };
            console.log("params:",params);
            httpService.updateRow(params,'course/updateCourse').then(
                function (result) {
                    $scope.result = result;
                    $scope.cancel();
                    if (result==1)
                    {
                        SweetAlert.swal("修改成功","","success");
                    }
                  else
                    {
                        SweetAlert.swal("修改失败","","error");
                    }
                },function () {
                    SweetAlert.swal("修改失败","","error");
                }
            );

        }else{
            SweetAlert.swal("修改失败","","error");
        }
    };
}]);
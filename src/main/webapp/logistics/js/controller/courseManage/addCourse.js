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
        $scope.submitForm=function () {
           // LoadingService.show();
            if ($scope.submit_form.$valid) {
                var params = {
                    code:$scope.code,
                    name:$scope.name,
                    class_hour:$scope.class_hour,
                    type:$scope.type,
                    teacher_name:$scope.teacher_name,
                    class_time:$scope.class_time,
                    start_time:$scope.start_time,
                    end_time:$scope.end_time,
                };
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
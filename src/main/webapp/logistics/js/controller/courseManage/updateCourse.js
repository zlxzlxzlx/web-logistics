/**
 * Created by Administrator on 2018/5/3.
 */
app.controller('updateCourseCtrl',['$scope','data','$uibModalInstance','SweetAlert','httpService',
    function ($scope,data,$uibModalInstance,SweetAlert,httpService) {
    console.log("da",data);
    $scope.title='编辑课程信息';
    $scope.code = data.code?data.code:"";
    $scope.name = data.name?data.name:"";
  //  $scope.teacher_name = data.teacherName?data.teacherName:"";
    $scope.class_hour = data.class_hour?data.class_hour:"";
    $scope.type = data.type?data.type:"";
    $scope.start_time = data.start_time?data.start_time:"";
    $scope.end_time = data.end_time?data.end_time:"";
    $scope.class_time = data.class_time?data.class_time:"";
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    try {
        $scope.school_id= data.school_id?data.school_id:"";
        $scope.teacher_id=data.teacher_id?data.teacher_id:"";
        $scope.element_id=data.college_id?data.college_id:"";
    }catch (e){}


    // $scope.re = [];
    $scope.getTeacherAndMajor = function () {
        $scope.majorInfo=[];
        $scope.teacherInfo=[];
        httpService.getAll(null,'course/getAllTeacherAndMajor').then(
            function (result) {
                $scope.re = result;
                //$scope.teacherInfo = result[0];
                //$scope.majorInfo = result[1];
                $scope.schoolInfo = result[2];
                if ($scope.school_id!=null){
                    for(var i in $scope.re[1]){
                        if($scope.school_id ==$scope.re[1][i].school_id){
                            $scope.majorInfo.push($scope.re[1][i]);
                        }
                    }
                }
                if ($scope.element_id!=null){
                    for(var i in $scope.re[0]){
                        if($scope.element_id ==$scope.re[0][i].college_id){
                            $scope.teacherInfo.push($scope.re[0][i]);
                        }
                    }
                }
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
            console.log("e",e);
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
            console.log(1,$scope.teacherInfo)
        }catch (e){
            console.log("e",e);
        }
    }
    $scope.submitForm = function () {
        if ($scope.submit_form.$valid){
            var params = {
                id:data.id,
                code:$scope.code,
                name:$scope.name,
                class_hour:$scope.class_hour,
                type:$scope.type,
                teacher_id:$scope.teacher_id,
                major_id:$scope.element_id,
                school_id:$scope.school_id,
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
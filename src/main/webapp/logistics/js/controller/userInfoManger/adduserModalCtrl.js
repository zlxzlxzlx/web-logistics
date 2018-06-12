/**
 * Created by Administrator on 2018/5/2.
 */
app.controller('adduserModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','$uibModalInstance','httpService','SweetAlert','LoadingService','Upload','localStorageService',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,$uibModalInstance,httpService,SweetAlert,LoadingService,Upload,localStorageService) {

        $scope.title="添加用户";
        //$scope.Marks = [{ id: 1, name: '学生' }, { id: 2, name: '教师' }];
        $scope.mark=1;
        $scope.cancel=function () {
         $uibModalInstance.dismiss("cancel");
        };
       $scope.show = false;
        $scope.getRole = function () {
            httpService.getAll(null,'role/getAll').then(
                function (result) {
                    console.log(123,result);
                    $scope.role = result;
                },function () {
                    swal("查询失败","error");
                }
            )
        };
        $scope.getRole();
        //图片预览
        $scope.reader = new FileReader();   //创建一个FileReader接口
        $scope.img_upload = function(files) {       //单次提交图片的函数
            $scope.reader.readAsDataURL(files[0]);  //FileReader的方法，把图片转成base64
            $scope.reader.onload = function (ev) {
                $scope.$apply(function () {
                    $scope.path=ev.target.result; //接收base64
                });
            };
        };
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
           // $scope.teacherInfo=[];
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
        };
        $scope.changeShow = function () {
            var param =
                {
                    roleId:$scope.mark
                }
            httpService.getOneRow(param,'role/getOneRole').then(
                function (result) {
                    if(result.name.toString()=="教师"){
                        $scope.show = true;
                       console.log("ss",$scope.show)
                    }else{
                        $scope.show = false;
                    }
                },function () {

                })
        };
        $scope.submitForm=function () {
            if ($scope.submit_form.$valid) {
                var params={
                  username:$scope.username,
                  mark:$scope.mark,
                  phone:$scope.phone,
                  code:$scope.code,
                  school:$scope.school_id,
                  college:$scope.element_id
                };
                httpService.addRow(params,'user/addUser').then(function (result) {
                    Upload.upload({
                        //服务端接收
                        url: '/web-logistics/user/uploadImage',
                        data:{'id':result.id},
                        file: $scope.file
                    });
                    $uibModalInstance.dismiss('ok');
                    SweetAlert.swal("操作成功", "", "success");
                },function () {
                });
            }

        };
    }]);
/**
 * Created by Administrator on 2018/5/2.
 */

app.controller('adduserModalCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','$uibModalInstance','httpService','SweetAlert','LoadingService',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,$uibModalInstance,httpService,SweetAlert,LoadingService) {

        $scope.title="添加用户";
        $scope.Marks = [{ id: 1, name: '学生' }, { id: 2, name: '教师' }];
        $scope.mark=1;
        $scope.cancel=function () {
         $uibModalInstance.dismiss("cancel");
        };

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
        $scope.submitForm=function () {
            if ($scope.submit_form.$valid) {
                var params={
                  username:$scope.username,
                  mark:$scope.mark
                };
                httpService.addRow(params,'user/addUser').then(function (result) {
                    $uibModalInstance.dismiss('ok');
                    SweetAlert.swal("操作成功", "", "success");
                },function () {
                })
            }

        };
    }]);
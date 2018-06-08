/**
 * Created by Administrator on 2018/6/6.
 */
app.controller('updateUserInfoCtrl',['$scope','data','$uibModalInstance','httpService','Upload','SweetAlert',function ($scope,data,$uibModalInstance,httpService,Upload,SweetAlert) {
      $scope.title="修改用户";
      $scope.cancel=function () {
        $uibModalInstance.dismiss("cancel");
      };
      $scope.mark = data.mark;
      $scope.username = data.user_name;
      $scope.phone = data.phone;
      $scope.code = data.code;
      $scope.path = data.image_url;
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
                mark:$scope.mark,
                phone:$scope.phone,
                code:$scope.code,
                id:data.id
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
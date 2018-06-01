/**
 * Created by Administrator on 2018/6/1.
 */
app.controller('updateMenuCtrl',['$scope','data','httpService','SweetAlert','$uibModalInstance',function ($scope,data,httpService,SweetAlert,$uibModalInstance) {

    $scope.title = "修改菜单";
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.parentCode = data.parent_code;
    $scope.code=parseInt(data.code.substring(data.parent_code.length));
    $scope.name = data.name;
    $scope.submitForm = function () {
        var params = {
            code:$scope.parentCode+$scope.code,
            name:$scope.name,
            id:data.id,
            flag:0
        };
        httpService.addRow(params,'menu/addMenu').then(
            function (result) {
                if (result==1){
                    SweetAlert.swal("修改成功","","success");
                }else {
                    SweetAlert.swal("修改失败","","error");
                }
            },function () {
                SweetAlert.swal("修改成功","","success");
            }
        );

        $scope.cancel();
    }
}]);
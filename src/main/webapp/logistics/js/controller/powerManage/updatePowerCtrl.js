/**
 * Created by Administrator on 2018/6/1.
 */
app.controller('updatePowerCtrl',['$scope','data','$uibModalInstance','httpService',function ($scope,data,$uibModalInstance,httpService) {
    console.log("sa1",data);
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.title = "角色权限信息";
    $scope.name = data.name == null?"":data.name;
    $scope.remarks = data.remarks == null?"": data.remarks;
    $scope.MenuName = data.powerId == null?"": data.powerId;

    $scope.getTree = function () {
        var params = {
            menu:$scope.MenuName
        }
        httpService.getAll(params,'menu/getAllTree').then(
            function (result) {
                console.log(1,result);
                $scope.treeData = result;
            },function () {
                SweetAlert.swal("查询失败","error");
            }
        )
    };
    $scope.getTree();
}]);
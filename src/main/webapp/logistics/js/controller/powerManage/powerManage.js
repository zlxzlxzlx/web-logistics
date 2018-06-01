/**
 * Created by Administrator on 2018/5/25.
 */
app.controller('powerManageCtrl',['$uibModal','$scope','httpService','SweetAlert',function ($uibModal,$scope,httpService,SweetAlert) {
    $scope.addPower = function () {
        var modalInstance = $uibModal.open({
            size:'medium',
            backdrop:'static',
            keyboard:false,
            animation:true,
            templateUrl:'powerForm.html',
            controller:'addPowerCtrl'
        });
        modalInstance.result.then(function (result) {},function (reason) {
            $scope.roleQueryList();
        });
    };
    $scope.roleQueryList=function () {
        httpService.getAll(null,'role/getAll').then(
            function (result) {
                console.log(result);
                $scope.role = result;
            },function () {
                swal("查询失败","error");
            }
        )
    }
    $scope.roleQueryList();

    $scope.delRow = function (id) {
        var params={
            id:id ,
            flag:1
        };
        SweetAlert.swal({
                title:"是否确认删除？",
                type:"warning",
                showCancelButton:true,
                confirmButtonColor:"#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText:"取消",
                closeOnConfirm: false
            },function (isconfirm) {
                if (isconfirm){
                    httpService.delRow(params,'role/addRole').then(
                        function (result) {
                            $scope.result = result;
                            $scope.roleQueryList();
                            if (result==1){
                                SweetAlert.swal("删除成功","","success");
                            }else
                            {
                                SweetAlert.swal("删除失败","","error");
                            }

                        },function () {
                            SweetAlert.swal("删除失败","","error");
                        }
                    );
                }
            }
        );


    };

    $scope.editRow = function (row) {
        var modalInstance = $uibModal.open({
            size:'lg',
            backdrop:'static',
            keyboard:false,
            animation:true,
            templateUrl:'powerForm.html',
            controller:'updatePowerCtrl',
            resolve:{
                data:row
            }
        });
        modalInstance.result.then(function (result) {},function (reason) {
            $scope.roleQueryList();
        });
    };
}]);
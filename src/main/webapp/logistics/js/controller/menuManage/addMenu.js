/**
 * Created by Administrator on 2018/5/28.
 */
app.controller('addMenuCtrl',['$scope','node','$uibModalInstance','httpService','SweetAlert','localStorageService',function ($scope,node,$uibModalInstance,httpService,SweetAlert,localStorageService) {
    $scope.title = "添加菜单";
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.parentCode = node.code;
    $scope.code = 1;
    angular.forEach(localStorageService.get("data"), function(data,index,array){
        if(data.parent_code==node.code){
            $scope.code++;
        }
    });
    $scope.submitForm = function () {
        var params={
            code:node.code+$scope.code,
        };
        httpService.addRow(params,'menu/codeUnique').then(
            function (result) {
                if(result==1){
                    SweetAlert.swal("已存在该编码");
                }else{
                    var param = {
                        parentCode:node.code,
                        code:node.code+$scope.code,
                        name:$scope.name,
                        flag:0
                    };
                    httpService.addRow(param,'menu/addMenu').then(
                        function (result) {
                            if (result==1){
                                SweetAlert.swal("添加成功","","success");
                            }else {
                                SweetAlert.swal("添加失败","","error");
                            }
                        },function (error) {
                            SweetAlert.swal("添加失败","","error");
                        }
                    );
                    $scope.cancel();
                }
            },function () {

            }
        );

    };

}]);
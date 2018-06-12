/**
 * Created by Administrator on 2018/5/26.
 */
app.controller('dataManageCtrl',['$scope','SweetAlert','httpService',function ($scope,SweetAlert,httpService) {
   $scope.addRole=function () {
       SweetAlert.swal({
               title: "添加角色",
               text: "角色名：",
               type: "input",
               showCancelButton: true,
               closeOnConfirm: false,
               animation: "slide-from-top",
               inputPlaceholder: "请输入角色名",
               inputValue:""
           },
           function(inputValue){
              console.log(1111,inputValue);
               if (inputValue === false) return false;

               if (inputValue === "") {
                   swal.showInputError("请输入角色名");
                   return false;
               }
               var  params={
                   roleName:inputValue,
                   flag:0
               };
               httpService.addRow(params,'role/addRole').then(
                   function (result) {
                       swal("添加成功","success");
                       $scope.queryList();
                   },function () {
                       swal("添加失败","error");
                   }
               );

           });
   };
   
   $scope.queryList=function () {
       httpService.getAll(null,'role/getAll').then(
           function (result) {
               console.log(result);
               $scope.role = result;
           },function () {
               swal("查询失败","error");
           }
       );
   };
   $scope.queryList();
    /*编辑人员信息*/
    $scope.editRow = function (i) {
        SweetAlert.swal({
                title: "编辑角色",
                text: "角色名：",
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                animation: "slide-from-top",
                inputPlaceholder: "请输入角色名",
                inputValue:i.name
            },
            function(inputValue){
                console.log(1111,inputValue);
                if (inputValue === false) return false;

                if (inputValue === "") {
                    swal.showInputError("请输入角色名");
                    return false;
                }
                var  params={
                    roleName:inputValue,
                    id:i.id,
                    flag:0
                };
                console.log(11,params);
                httpService.addRow(params,'role/addRole').then(
                    function (result) {
                        swal("修改成功","success");
                        $scope.queryList();
                    },function () {
                        swal("修改失败","error");
                    }
                );

            });
    };
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
                            $scope.queryList();
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
}]);
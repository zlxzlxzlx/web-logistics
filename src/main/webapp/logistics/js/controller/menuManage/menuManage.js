/**
 * Created by Administrator on 2018/5/27.
 */
app.controller('menuManageCtrl',['$scope','httpService','SweetAlert','$uibModal','localStorageService',function ($scope,httpService,SweetAlert,$uibModal,localStorageService) {
    $scope.pagination = {
        totalCount: 0,
        totalPage: 0,
        pageSize: 10,
        currentPage: 1,
        PageSizeList: [5, 10, 30]
    };
    $scope.treeData=[];
    //改变每页显示数量
    $scope.changePageSize = function(_pageSize){
        $scope.pagination.pageSize = _pageSize;
        $scope.pagination.currentPage = 1;
        $scope.queryList();
    };
    
    $scope.getTree = function () {
        httpService.getAll(null,'menu/getAllTree').then(
            function (result) {
                $scope.treeData = result;
                console.log("tr",result);
            },function () {
                SweetAlert.swal("查询失败","error");
            }
        );
    };
    $scope.getTree();

    $scope.queryList = function () {
        var param={
            name:$scope.name,
            code:$scope.code,
            pageNo:$scope.pagination.currentPage,
            pageSize:$scope.pagination.pageSize
        };
        httpService.getAll(param,'menu/getAll').then(
            function (result) {
                console.log(11,result);
                $scope.root = result[1];
                $scope.pagination.totalCount=result[1].totalCount;
                $scope.pagination.totalPage = result[1].totalPage;
                $scope.pagination.pageSize = result[1].pageSize;
                $scope.pagination.currentPage = result[1].pageNo;
                localStorageService.set("data",result[0]);
            },function () {
                SweetAlert.swal("查询失败","","error");
            }
        );
    };
    $scope.queryList();

    $scope.reset = function () {
        $scope.name = '';
        $scope.code = '';
        $scope.queryList();
    };
    var node_value = null;
    $scope.itemClicked = function (node) {
        node_value = node;
        var param1 ={
            code:node.code,
            pageNo:$scope.pagination.currentPage,
            pageSize:$scope.pagination.pageSize
        };
        console.log(1212,param1);
        httpService.getAll(param1,'menu/isDelete').then(
            function (result) {
                $scope.root = result;
            },function () {
                SweetAlert.swal("查询失败","","error");
            }
        );
    };
    //添加菜单
    $scope.addRow = function () {
        if (node_value==null){
            SweetAlert.swal("请选择一个节点进行添加");
        }else {
            var modalInstance = $uibModal.open({
                size:'medium',
                backdrop:'static',
                keyboard:false,
                animation:true,
                templateUrl:'menuForm.html',
                controller:'addMenuCtrl',
                resolve:{
                    node:node_value
                }
            });
            modalInstance.result.then(function (result) {

            },function (reason) {
                $scope.queryList();
                $scope.getTree();
            });
        }
       
    };
   $scope.delRow=function (i) {

       var param = {
           id:i.id,
           flag:1
       };
       var param1={
           code:i.code,
           pageNo:$scope.pagination.currentPage,
           pageSize:$scope.pagination.pageSize
       };
       httpService.getAll(param1,'menu/isDelete').then(
         function (result) {
              if (result.data.length>1){
                  SweetAlert.swal("存在子节点，请先删除子节点");
              }else {
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
                          httpService.delRow(param, 'menu/addMenu').then(
                              function (result) {
                                  if (result == 1) {
                                      SweetAlert.swal("删除成功", "", "success");
                                  } else {
                                      SweetAlert.swal("删除失败", "", "error");
                                  }
                                  $scope.queryList();
                                  $scope.getTree();
                              }, function () {
                                  SweetAlert.swal("删除失败", "", "error");
                              }
                          );}
                  });
              }
         },function () {
               
           }  
       );

   };

    $scope.editRow = function (row) {
        console.log(1,row);
        var modalInstance = $uibModal.open({
            size:'medium',
            backdrop:'static',
            keyboard:false,
            animation:true,
            templateUrl:'menuForm.html',
            controller:'updateMenuCtrl',
            resolve:{
                data:row
            }
        });
        modalInstance.result.then(function (result) {},function (reason) {
            $scope.queryList();
        });
    };
}]);
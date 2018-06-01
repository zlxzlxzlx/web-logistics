/**
 * Created by Administrator on 2018/5/25.
 */
app.controller('addPowerCtrl',['$scope','httpService','$uibModalInstance','localStorageService','SweetAlert',function ($scope,httpService,$uibModalInstance,localStorageService,SweetAlert) {
   $scope.title="添加角色权限信息";
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.disabled = true;
    $scope.getTree = function () {
        httpService.getAll(null,'menu/getAllTree').then(
            function (result) {
                $scope.treeData = result;
                //$scope.recursiveQuery(result);
            },function () {
                SweetAlert.swal("查询失败","error");
            }
        )
    }
    $scope.getTree();

    //递归遍历树
    $scope.checkedMenu=[];
    $scope.checkedMenuCode=[];
   /* $scope.recursiveQuery = function (tree) {
        if(tree!=null && tree!=undefined){
            for(var i= 0;i<tree.length;i++){
                if(tree[i].checked){//选中
                    $scope.checkedMenuCode.push(tree[i].code);
                    $scope.checkedMenu.push(tree[i]);
                }
                $scope.recursiveQuery(tree[i].childItems);
            }
        }
        // $scope.selectAll();
    };*/
    //点击tree复选框
    $scope.itemCheckedChanged = function (changeItem) {
        if(changeItem.code!=="0"){
            var index = $scope.checkedMenuCode.indexOf(changeItem.code);
            if(changeItem.checked){//选中true
                if(index===-1){
                    $scope.checkedMenuCode.push(changeItem.code);
                    $scope.checkedMenu.push(changeItem);
                    console.log("1",$scope.checkedMenuCode);
                    console.log(2,$scope.checkedMenu);
                }
            }else{//取消选中false
                if(index!==-1){
                    $scope.checkedMenuCode.splice(index,1);
                    $scope.checkedMenu.splice(index,1);
                }
            }

        }
        $scope.linkageCheckbox(changeItem.childItems,changeItem.checked);
    };
    //递归
    $scope.linkageCheckbox = function (item,checkedType) {
        if(item!=null && item!=undefined){
            for(var i= 0;i<item.length;i++){
                var index = $scope.checkedMenuCode.indexOf(item[i].code);
                if(checkedType){//选中true
                    if(index===-1){
                        $scope.checkedMenuCode.push(item[i].code);
                        $scope.checkedMenu.push(item[i]);
                        console.log("111",$scope.checkedMenuCode);
                        console.log("222",$scope.checkedMenu);
                    }
                }else{//取消选中false
                    if(index!==-1){
                        $scope.checkedMenuCode.splice(index,1);
                        $scope.checkedMenu.splice(index,1);
                        console.log("1111",$scope.checkedMenuCode);
                        console.log("2222",$scope.checkedMenu);
                    }
                }
                $scope.linkageCheckbox(item[i].childItems,checkedType);
            }
        }
        $scope.selectAll();
    };

    /*全选*/
    $scope.selectAll = function () {
        $scope.MenuName="";
        $scope.MenuId="";
        $scope.menu="";
        angular.forEach($scope.checkedMenu,function (data) {
            if(data.parent_code!='0'){
                $scope.MenuName = $scope.MenuName + data.name+ "/";
                $scope.MenuId = $scope.MenuId+data.id+ "/";
            }
        });
        $scope.menu = $scope.MenuName+"——"+$scope.MenuId;
        console.log("me",$scope.menu);
    };
    $scope.submitForm = function () {
        var params = {
            roleName:$scope.name,
            remarks:$scope.remarks,
            menu:$scope.MenuName,
            flag:0
        }
        httpService.addRow(params,'role/addRole').then(
            function (result) {
                swal("添加成功","","success");
                $scope.cancel();
            },function () {
                swal("添加失败","","error");
            }
        )
    }
}]);
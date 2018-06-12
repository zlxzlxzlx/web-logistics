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
        };
        httpService.getAll(params,'menu/getAllTree').then(
            function (result) {
                console.log(1,result);
                $scope.treeData = result;
            },function () {
                SweetAlert.swal("查询失败","error");
            }
        );
    };
    $scope.getTree();
    //递归遍历树
    $scope.checkedMenu=[];
    $scope.checkedMenu = data.powerId.split('/');
    $scope.checkedMenuCode=[];
    //点击tree复选框
    $scope.itemCheckedChanged = function (changeItem) {
        if(changeItem.code!=="0"){
            var index = $scope.checkedMenu.indexOf(changeItem.name);
            if(changeItem.checked==true){//选中true
                if(index===-1){
                    $scope.checkedMenuCode.push(changeItem.code);
                    $scope.checkedMenu.push(changeItem.name);
                    console.log("menu1",$scope.checkedMenu);
                }
            }else{//取消选中false

                if(index!==-1){
                    $scope.checkedMenuCode.splice(index,1);
                    $scope.checkedMenu.splice(index,1);
                    console.log("menu2",$scope.checkedMenu);

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
                        $scope.checkedMenu.push(item[i].name);
                    }
                }else{//取消选中false
                    if(index!==-1){
                        $scope.checkedMenuCode.splice(index,1);
                        $scope.checkedMenu.splice(index,1);
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
      /*  angular.forEach($scope.checkedMenu,function (data) {
            if(data.parent_code!='0'){
                $scope.MenuName = $scope.MenuName + data+ "/";
              //  $scope.MenuId = $scope.MenuId+data.id+ "/";
            }
        });*/
        for(var i = 0;i<$scope.checkedMenu.length;i++){
            if($scope.checkedMenu[i].parent_code!='0'){
                if(i == $scope.checkedMenu.length-1){
                    $scope.MenuName = $scope.MenuName + $scope.checkedMenu[i];
                }else {
                    $scope.MenuName = $scope.MenuName + $scope.checkedMenu[i]+ "/";
                }
            }
        }
       // $scope.menu = $scope.MenuName+"——"+$scope.MenuId;
    };
    $scope.submitForm = function () {
        var params = {
            roleName:$scope.name,
            remarks:$scope.remarks,
            menu:$scope.MenuName,
            flag:0,
            id:data.id
        };
        httpService.addRow(params,'role/addRole').then(
            function (result) {
                swal("修改成功","","success");
                $scope.cancel();
            },function () {
                swal("修改失败","","error");
            }
        );
    };
}]);
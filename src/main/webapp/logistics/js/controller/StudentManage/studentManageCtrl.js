/**
 * Created by Administrator on 2018/6/5.
 */
app.controller('studentManageCtrl',['$scope','$uibModal','httpService','SweetAlert',function ($scope,$uibModal,httpService,SweetAlert) {

    $scope.root={};
    $scope.rowlist=[];
    $scope.pagination={
        totalCount:0,
        totalPage:0,
        pageSize:10,
        currentPage:1,
        PageSizeList:[5,10,30]
    };
    /*改变每页显示数量*/
    $scope.changePageSize = function (pageSize) {
        $scope.pagination.pageSize = pageSize;
        $scope.pagination.currentPage = 1;
        $scope.queryList();
    };
    $scope.queryList = function () {
        var params = {
            code:$scope.code,
            name:$scope.name,
            college_id:$scope.userInfo.college_id,
            school_id:$scope.userInfo.school_id,
            pageNo:$scope.pagination.currentPage,
            pageSize:$scope.pagination.pageSize,
        };
        httpService.getAll(params,'student/showStudent').then(
            function (result) {
                $scope.pagination.totalCount = result.totalCount;
                $scope.pagination.totalPage = result.totalPage;
                $scope.pagination.pageSize = result.pageSize;
                $scope.pagination.currentPage = result.pageNo;
                $scope.root.data = result.data;
                console.log(result.data)
            },function () {
            }
        );
    };
    $scope.queryList();
    $scope.reset = function () {
        $scope.name=null;
        $scope.code=null;
        $scope.queryList();
    };
    //添加学生
    $scope.addStudent = function () {
        var modalInstance = $uibModal.open({
            backdrop:'static',
            keyboard:false,
            animation:true,
            templateUrl:'studentForm.html',
            controller:'addStudentCtrl'
        });
        modalInstance.result.then(function (result) {},function (reason) {
            $scope.queryList();
        });
    };
    /*单条删除*/
    $scope.delRow = function (id) {
        var params={
            id:id,
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
                    httpService.delRow(params,'student/addStudent').then(
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
    //全选
    $scope.checked=[];
    $scope.selectAll = function () {
        if ($scope.select_all){
            $scope.checked = [];
            angular.forEach($scope.root.data,function (i) {
                i.checked = true;
                $scope.checked.push(i.id);
            });
        }else {
            angular.forEach($scope.root.data,function (i) {
                i.checked = false;
                $scope.checked=[];
            });
        }
    };
    //多选
    $scope.selectOne = function () {
        angular.forEach($scope.root.data,function (i) {
            var index = $scope.checked.indexOf(i.id);
            if(i.checked && index === -1){
                $scope.checked.push(i.id);
            }
            else if (!i.checked && index !==-1){
                $scope.checked.splice(index,1);
            }
        });
        if ($scope.root.data.length === $scope.checked.length){
            $scope.select_all=true;
        }else {
            $scope.select_all = false;
        }
    };
    //批量删除
    $scope.delRows = function () {
        if ($scope.checked.length<0||$scope.checked.length===0){
            SweetAlert.swal("至少选择一条记录，再做删除","","warning");
        }else{
            var params = {
                ids:$scope.checked
            };
            SweetAlert.swal({
                title:"是否确认删除？",
                type:"warning",
                showCancelButton:true,
                confirmButtonColor:"#DD6B55",
                confirmButtonText:"确认",
                cancelButtonText:"取消",
                closeOnConfirm:false
            },function (isConfirm) {
                if(isConfirm){
                    httpService.delRows(params,'student/delStudents').then(
                        function (result) {
                            $scope.result=result;
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
            });
        }

    };

    /*编辑学生信息*/
    $scope.editRow = function (id) {
        var modalInstance = $uibModal.open({
            size:'medium',
            backdrop:'static',
            keyboard:false,
            animation:true,
            templateUrl:'studentForm.html',
            controller:'updateStudentCtrl',
            resolve:{
                data:$scope.root.data[id]
            }
        });
        modalInstance.result.then(function (result) {},function (reason) {
            $scope.queryList();
        });
    };
}]);
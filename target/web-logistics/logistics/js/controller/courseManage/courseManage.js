/**
 * Created by acer on 2018/5/22.
 */
app.controller('courseManageCtrl',['$http','httpService','$scope','$uibModal','SweetAlert','$rootScope',
    function ($http,httpService,$scope,$uibModal,SweetAlert,$rootScope) {
        /*初始化数据*/
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
                pageNo:$scope.pagination.currentPage,
                pageSize:$scope.pagination.pageSize
            };
            httpService.getAll(params,'course/showCourse').then(
                function (result) {
                    $scope.pagination.totalCount = result.totalCount;
                    $scope.pagination.totalPage = result.totalPage;
                    $scope.pagination.pageSize = result.pageSize;
                    $scope.pagination.currentPage = result.pageNo;
                    $scope.root.data = result.data;
                },function () {
                }
            );
        };
        $scope.queryList();
        //添加课程
        $scope.addCourse = function () {
            var modalInstance = $uibModal.open({
                backdrop:'static',
                keyboard:false,
                animation:true,
                templateUrl:'courseForm.html',
                controller:'addCourseCtrl'
            });
            modalInstance.result.then(function (result) {},function (reason) {
                $scope.queryList();
            });
        };

        /*编辑人员信息*/
        $scope.editRow = function (id) {
            var modalInstance = $uibModal.open({
                size:'lg',
                backdrop:'static',
                keyboard:false,
                animation:true,
                templateUrl:'courseForm.html',
                controller:'updateCourseCtrl',
                resolve:{
                    data:$scope.root.data[id]
                }
            });
            modalInstance.result.then(function (result) {},function (reason) {
                $scope.queryList();
            });
        };
        $scope.reset = function () {
            $scope.name=null;
            $scope.code=null;
            $scope.queryList();
        };
        /*单条删除*/
        $scope.delRow = function (id) {
            var params={
                id:id
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
                        httpService.delRow(params,'course/delCourse').then(
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
                        httpService.delRows(params,'course/delCourses').then(
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
       /*





        /!*导入文件*!/
        $scope.importExcel = function () {
            var modalInstance = $uibModal.open({
                size:'lg',
                backdrop:'static',
                keyboard:false,
                animation: true,
                templateUrl: 'cuserImport.html',
                controller: 'importCUserModalCtrl',
            });
            modalInstance.result.then(function (result) {},function (reason) {
                $scope.queryList();
            });
        };
        /!*导出文件*!/
        $scope.exportExcel = function () {
            httpService.exportExcel('user/exportExcelC',adminid);
        };*/

    }]);
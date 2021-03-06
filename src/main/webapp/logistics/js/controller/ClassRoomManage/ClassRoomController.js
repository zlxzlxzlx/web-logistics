/**
 * Created by zlx on 2018/5/2.
 */
app.controller('ClassRoomController',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope) {

        $scope.userInfo=localStorageService.get("userInfo");

        $scope.schools=[];
        $scope.root=[];
        $scope.pagination = {
            totalCount: 0,
            totalPage: 0,
            pageSize: 10,
            currentPage: 1,
            PageSizeList: [5, 10, 30]
        };
        $scope.getAllSchool=function (){
            $http.get('../school/getAllSchoolForSelect')
                .success(function(data,status,headers,config){
                   $scope.schools=data;
                }).error(function(data,status,headers,config){

                });
        };
        $scope.getAllSchool();
        $scope.add=function () {
            localStorageService.set("classRoom",$scope.schools);
            var modalInstance=$uibModal.open({
                backdrop:'static',
                keyboard:false,
                animation: true,
                templateUrl:'classRoomForm.html',
                controller:'addClassRoomModalCtrl'

            });
            modalInstance.result.then(function (result) {},function (reason) {
                $scope.reset();
            });
        };
      $scope.getSchoolId=function () {
            var param ={
                code:$scope.userInfo.code
            };
            httpService.getAll(param,'school/getSchoolId').then(
                function (result) {
                    console.log(result);
                    try {
                        if (result.school_name=="学校"){
                            $scope.school_id=result.id;
                            $scope.queryList();
                        }
                    }catch (e){
                        console.log(e);
                    }
                },function () {
                }
            );
        };
        $scope.getSchoolId();
        $scope.queryList=function () {
          var params={
              school_id:$scope.school_id,
              keyWord:$scope.keyWord,
              pageNo: $scope.pagination.currentPage,
              pageSize: $scope.pagination.pageSize
          };

            $http({
                method : 'POST',
                url : '../classroom/getAllClassRoom',
                data: params
            }).success(function(result, status, headers, config) {
                console.log(111,result);
                $scope.pagination.totalCount=result.totalCount;
                $scope.pagination.totalPage = result.totalPage;
                $scope.pagination.pageSize = result.pageSize;
                $scope.pagination.currentPage = result.pageNo;
                $scope.root.data=result.data;
            }).error(function(data, status, headers, config) {

            });
        };
        $scope.queryList();
        $scope.reset=function () {
          $scope.keyWord="";
            $scope.queryList();
        };
        $scope.delRow=function (id) {
          var params={
              id:id
          };
            SweetAlert.swal({
                title: "是否确认删除？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText:"取消",
                closeOnConfirm: false},function(isConfirm) {
                if (isConfirm) {
                    $http.get('../classroom/delRow',{params:params}).success(function(result, status, headers, config) {

                         if(result==1){
                             $scope.reset();
                            SweetAlert.swal("删除成功", "", "success");
                        }else{
                            SweetAlert.swal("删除失败", "", "error");
                        }
                    }).error(function(data, status, headers, config) {
                        SweetAlert.swal("删除失败", "", "error");
                    });
                }
            });

        };
        $scope.editRow=function (_row) {
            localStorageService.set("classRoom",$scope.schools);
            localStorageService.set("updateRow",_row);
                var modalInstance=$uibModal.open({
                    backdrop:'static',
                    keyboard:false,
                    animation: true,
                    templateUrl:'classRoomForm.html',
                    controller:'updateClassRoomModalCtrl'
                });
                modalInstance.result.then(function (result) {},function (reason) {
                    $scope.reset();
                });
        };
    }]);
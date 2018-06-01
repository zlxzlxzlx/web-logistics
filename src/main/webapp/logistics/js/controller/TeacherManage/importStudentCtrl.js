/**
 * Created by zlx on 2018/5/2.
 */
app.controller('importStudentCtrl',['$scope','$http','$filter','$state','$stateParams','$uibModal','httpService','SweetAlert','LoadingService','localStorageService','$rootScope','$uibModalInstance',
    function($scope, $http,$filter,$state,$stateParams,$uibModal,httpService,SweetAlert,LoadingService,localStorageService,$rootScope,$uibModalInstance) {

        $scope.user=localStorageService.get("userInfo");
        $scope.importStudentRow=localStorageService.get("importStudentRow");
        console.log(141,$scope.importStudentRow);
        $scope.cancel=function () {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.title="添加学生";
        $scope.root=[];
        $scope.queryList=function () {
            var params={
                school_id:$scope.user.school_id
            }
            httpService.getAll(params,'user/getAllUerForImport').then(function (result) {
               $scope.root=result;
            },function () {

            })
        };
        $scope.queryList();
        //全选
        $scope.checked = [];
        $scope.selectAll = function () {
            if($scope.select_all) {
                $scope.checked = [];
                angular.forEach($scope.root, function (i) {
                    i.checked = true;
                    $scope.checked.push(i.id);
                });
            }else {
                angular.forEach($scope.root, function (i) {
                    i.checked = false;
                    $scope.checked = [];
                });
            }
        };
        //多选
        $scope.selectOne = function () {
            angular.forEach($scope.root , function (i) {
                var index = $scope.checked.indexOf(i.id);
                if(i.checked && index === -1) {
                    $scope.checked.push(i.id);
                }
                else if (!i.checked && index !== -1){
                    $scope.checked.splice(index, 1);
                }
            });

            if ($scope.root.length === $scope.checked.length) {
                $scope.select_all = true;
            } else {
                $scope.select_all = false;
            }
        };

        $scope.submitForm=function () {
            if ($scope.submit_form.$valid){
                var params={
                    teacher_id:$scope.user.id,
                    course_id:$scope.importStudentRow.id,
                    user_id:$scope.checked
                }
                httpService.postJson(params,'studentCourse/addStudentCourse').then(function (result) {
                    if(result==1){
                        $uibModalInstance.dismiss('ok');
                        SweetAlert.swal("添加成功", "", "success");
                    }else{
                        $uibModalInstance.dismiss('ok');
                        SweetAlert.swal("添加失败", "", "error");
                    }

                },function () {
                    $uibModalInstance.dismiss('ok');
                    SweetAlert.swal("添加失败", "", "error");
                })

            }
        };

    }]);
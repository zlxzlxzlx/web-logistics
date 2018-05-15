/**
 * Created by Administrator on 2018/5/13.
 */
app.controller('accountManageCtrl',['$scope','localStorageService',function ($scope,localStorageService) {
    console.log(11,localStorageService.get("userInfo"))
}])
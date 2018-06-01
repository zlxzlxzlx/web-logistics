/**
 * Created by 便便
 * tree结构指令
 */

//联动复选框(单选不包括子节点)
app.directive('treeView', function () {
    return {
      restrict: 'E',
      templateUrl: 'js/directive/tree/treeView.html',
      scope: {
        treeData: '=',
        canChecked: '=',
        textField: '@',
        itemClicked: '&',
        itemCheckedChanged: '&',
        itemTemplateUrl: '@'
      },
      controller:['$scope', function($scope){

        $scope.itemExpended = function(item, $event){
          item.$$isExpend = ! item.$$isExpend;
          $event.stopPropagation();
        };

        //设置icon
        $scope.getItemIcon = function(item){
          var isLeaf = $scope.isLeaf(item);
          if(isLeaf){
            return '';
        }
          return item.$$isExpend ? 'glyphicon glyphicon-minus': 'glyphicon glyphicon-plus';
        };
        //判断是否为叶子节点
        $scope.isLeaf = function(item){
          return !item.childItems || !item.childItems.length;
        };

        $scope.warpCallback = function(callback, item, $event){
          ($scope[callback] || angular.noop)({
            $item:item,
            $event:$event
          });

        };


      }]
    };


  });
//联动复选框(单选包括子节点)
app.directive('treeViewLeaf', function () {
    return {
      restrict: 'E',
      templateUrl: 'js/directive/tree/treeView.html',
      scope: {
        treeData: '=',
        canChecked: '=',
        textField: '@',
        itemClicked: '&',
        itemCheckedChanged: '&',
        itemTemplateUrl: '@'
      },
      controller:['$scope', function($scope){

        $scope.itemExpended = function(item, $event){
          item.$$isExpend = ! item.$$isExpend;
          $event.stopPropagation();
        };
        //设置icon
        $scope.getItemIcon = function(item){
          var isLeaf = $scope.isLeaf(item);
          if(isLeaf){
            return '';
        }
          return item.$$isExpend ? 'glyphicon glyphicon-minus': 'glyphicon glyphicon-plus';
        };
        //判断是否为叶子节点
        $scope.isLeaf = function(item){
          return !item.childItems || !item.childItems.length;
        };

        $scope.warpCallback = function(callback, item, $event){

          $scope.linkageCheckbox(item.childItems,item.checked);

          ($scope[callback] || angular.noop)({
            $item:item,
            $event:$event
          });
        };

       //选择框联动效果
        $scope.linkageCheckbox = function (item,checkedType) {
          if(item!=null && item!=undefined){
            for(var i= 0;i<item.length;i++){
              item[i].checked=checkedType;
              $scope.linkageCheckbox(item[i].childItems,checkedType);
            }
          }

        }




      }]
    };


  });


//无联动复选框
app.directive('onlyTreeView', function () {
      return {
          restrict: 'E',
          templateUrl: 'js/directive/tree/onlyTreeView.html',
          scope: {
              treeData: '=',
              canChecked: '=',
              textField: '@',
              itemClicked: '&',
              itemCheckedChanged: '&',
              itemTemplateUrl: '@'
          },
          controller:['$scope', function($scope){

              $scope.itemExpended = function(item, $event){
                  item.$$isExpend = ! item.$$isExpend;
                  $event.stopPropagation();
              };

              //设置icon
              $scope.getItemIcon = function(item){
                  var isLeaf = $scope.isLeaf(item);
                  if(isLeaf){
                      return '';
                  }
                  return item.$$isExpend ? 'glyphicon glyphicon-minus': 'glyphicon glyphicon-plus';
              };
              //判断是否为叶子节点
              $scope.isLeaf = function(item){
                  return !item.childItems || !item.childItems.length;
              };

              $scope.warpCallback = function(callback, item, $event){
                  ($scope[callback] || angular.noop)({
                      $item:item,
                      $event:$event
                  });

              };


          }]
      };

  });

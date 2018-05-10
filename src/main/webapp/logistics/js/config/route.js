

app.config(function($httpProvider,$stateProvider, $urlRouterProvider, $locationProvider, $uiViewScrollProvider) {
//用于改变state时跳至顶部
// POST
//$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
/*$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// Override $http service's default transformRequest
$httpProvider.defaults.transformRequest = [function(data) {
    /!**
     * The workhorse; converts an object to x-www-form-urlencoded serialization.
     * @param {Object} obj
     * @return {String}
     *!/
    var param = function(obj) {
        var query = '';
        var name, value, fullSubName, subName, subValue, innerObj, i;

        for (name in obj) {
            value = obj[name];

            if (value instanceof Array) {
                for (i = 0; i < value.length; ++i) {
                    subValue = value[i];
                    fullSubName = name + '[' + i + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            } else if (value instanceof Object) {
                for (subName in value) {
                    subValue = value[subName];
                    fullSubName = name + '[' + subName + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            } else if (value !== undefined && value !== null) {
                query += encodeURIComponent(name) + '='
                    + encodeURIComponent(value) + '&';
            }
        }

        return query.length ? query.substr(0, query.length - 1) : query;
    };

    return angular.isObject(data) && String(data) !== '[object File]'
        ? param(data)
        : data;
}];*/
$uiViewScrollProvider.useAnchorScroll();
$urlRouterProvider.otherwise('/#/index'); //找到相应路由调到制定的路由页面
$stateProvider

    //首页
    .state('index', {
        url: '/index',
        templateUrl: 'views/tpl/main.html',
        controller: 'indexController',
        params:{args:{}}
    })

    //测试页面
    .state('test', {
        url: '/test',
        templateUrl: 'views/systemAdmin/test.html'
       /* controller: 'indexController',
        params:{args:{}}*/
    })

    //系统管理/用户管理
    .state('managerUserInfo', {
        url: '/managerUserInfo',
        templateUrl: 'views/userInfoManger/userInfo.html',
       controller: 'UserInfoController',
        params:{args:{}}
    })

    //课程管理页面
    .state('courseManage', {
        url: '/courseManage',
        templateUrl: 'views/courseManage/courseManage.html',
         controller: 'courseManageCtrl',
         params:{args:{}}

    })
    //学校管理页面
    .state('managerSchool', {
        url: '/managerSchool',
        templateUrl: 'views/SchoolManage/schoolManage.html',
         controller: 'schoolManageCtrl',
         params:{args:{}}

    })
    //学院管理页面
    .state('managerCollege', {
        url: '/managerCollege',
        templateUrl: 'views/SchoolManage/collegeManage.html',
         controller: 'collegeManageCtrl',
         params:{args:{}}

    })
    //专业管理页面
    .state('managerMajor', {
        url: '/managerMajor',
        templateUrl: 'views/SchoolManage/majorManage.html',
         controller: 'majorManageCtrl',
         params:{args:{}}

    })
    //学生管理/课程信息
    .state('manageGrade', {
        url: '/manageGrade',
        templateUrl: 'views/StudentManage/gradeManage.html',
        controller: 'gradeManageCtrl',
        params:{args:{}}

    }) 
    //学生管理/选课信息
    .state('manageElective', {
        url: '/manageElective',
        templateUrl: 'views/StudentManage/electiveManage.html',
        controller: 'electiveManageCtrl',
        params:{args:{}}

    })  
    //学生管理/课表查询
    .state('scheduleQuery', {
        url: '/scheduleQuery',
        templateUrl: 'views/StudentManage/scheduleQuery.html',
        controller: 'scheduleQueryManageCtrl',
        params:{args:{}}

    })  
    //老师管理/班级管理
    .state('manageClass', {
        url: '/manageClass',
        templateUrl: 'views/TeacherManage/manageClass.html',
        controller: 'ClassManageCtrl',
        params:{args:{}}

    }) 
    //老师管理/课堂点名
    .state('ClassName', {
        url: '/ClassName',
        templateUrl: 'views/TeacherManage/ClassName.html',
        controller: 'ClassNameManageCtrl',
        params:{args:{}}

    })
    
    
})


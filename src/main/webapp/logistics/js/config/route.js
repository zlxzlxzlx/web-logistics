

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
    
    
});


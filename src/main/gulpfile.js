// 引入 gulp
var gulp = require('gulp');

//（1）淘宝镜像：npm install -g cnpm --registry=https://registry.npm.taobao.org
//（2）安装gulp: cnpm install -g gulp
//（3）gulp安装到项目本地：cnpm install --save-dev gulp
//（4）安装依赖：cnpm install gulp-jshint gulp-sass gulp-concat gulp-uglify gulp-rename --save-dev
//（5）引入组件
var jshint = require('gulp-jshint');//安装    cnpm install jshint gulp-jshint --save-dev
var ngAnnotate = require('gulp-ng-annotate');//安装   cnpm install gulp-ng-annotate --save-dev
var concat = require('gulp-concat');//安装    cnpm install gulp-concat --save-dev
var uglify = require('gulp-uglify');//安装    cnpm install gulp-uglify --save-dev
var rename = require('gulp-rename');//安装    cnpm install gulp-rename --save-dev
var minifyCss = require('gulp-minify-css');//安装     cnpm install gulp-minify-css --save-dev



gulp.task('lint', function() {
    gulp.src([
        './webapp/logistics/js/controller/**/*.js',
        './webapp/logistics/js/controller/**/*.js'

    ])
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});


//合并，压缩平台登录css文件
gulp.task('login_css', function() {
    gulp.src([				//需要操作的文件
       /* './webapp/common/css/default_login.css',*///登录整体全局css
        './webapp/common/css/login.css' ,//登录css
       /* './webapp/common/css/gotoSystem.css',*/ //选择系统css
        './webapp/common/css/sweetalert.css', //弹出框
        './webapp/common/css/loadingModal.css' //londing遮罩层

    ])
        .pipe(concat('all.min.css')) //合并成一个文件
        .pipe(minifyCss())			//压缩css成一行
        .pipe(gulp.dest('./webapp/common/dist')); //输出文件夹
});



//合并，压缩平台的js文件
gulp.task('logistics_scripts', function() {
    gulp.src([
        './webapp/logistics/js/config/app.js', //加载APP模块
        './webapp/logistics/js/config/route*.js', //加载route模块
        './webapp/logistics/js/config/config.js', //常量配置模块

    ])
        .pipe(ngAnnotate())
        .pipe(uglify())//压缩
        .pipe(concat('all.min.js'))
        .pipe(uglify({
            mangle: true,//类型：Boolean 默认：true 是否修改变量名
        }))
        .pipe(gulp.dest('./webapp/logistics/dist'));
});
//合并，压缩平台css文件
gulp.task('logistics_css', function() {
    gulp.src([				//需要操作的文件
        './webapp/logistics/css/**/*.css',//登录整体全局css
    ])
        .pipe(concat('all.min.css')) //合并成一个文件
        .pipe(minifyCss())			//压缩css成一行
        .pipe(gulp.dest('./webapp/logistics/dist'));   //输出文件夹
});
//合并，压缩平台的controller文件
gulp.task('logistics_controllers', function() {
    gulp.src([
        './webapp/logistics/js/controller/**/*.js', //加载angular模块
    ])
        .pipe(ngAnnotate())
        .pipe(uglify())//压缩
        .pipe(concat('controller.min.js'))
        .pipe(uglify({
            mangle: true,//类型：Boolean 默认：true 是否修改变量名
        }))
        .pipe(gulp.dest('./webapp/logistics/dist'));
});
//合并，压缩平台的service文件
gulp.task('logistics_services', function() {
    gulp.src([
        './webapp/logistics/js/service/*.js', //加载angular模块
    ])
        .pipe(ngAnnotate())
        .pipe(uglify())//压缩
        .pipe(concat('service.min.js'))
        .pipe(uglify({
            mangle: true,//类型：Boolean 默认：true 是否修改变量名
        }))
        .pipe(gulp.dest('./webapp/logistics/dist'));
});

//合并，压缩平台的directive文件
gulp.task('logistics_directive', function() {
    gulp.src([
        './webapp/logistics/js/directive/*.js', //加载angular模块
    ])
        .pipe(ngAnnotate())
        .pipe(uglify())//压缩
        .pipe(concat('directive.min.js'))
        .pipe(uglify({
            mangle: true,//类型：Boolean 默认：true 是否修改变量名
        }))
        .pipe(gulp.dest('./webapp/logistics/dist'));
});

//合并，压缩平台的filter文件
gulp.task('logistics_filters', function() {
    gulp.src([
        './webapp/logistics/js/filter/*.js' //加载angular模块
    ])
        .pipe(ngAnnotate())
        .pipe(uglify())//压缩
        .pipe(concat('filter.min.js'))
        .pipe(uglify({
            mangle: true//类型：Boolean 默认：true 是否修改变量名
        }))
        .pipe(gulp.dest('./webapp/logistics/dist'));
});


// 默认任务
gulp.task('default', function(){
    gulp.run('lint','login_css','logistics_scripts','logistics_css','logistics_services','logistics_directive','logistics_controllers','logistics_filters');

    //考勤系统平台登录css
    gulp.watch('./webapp/common/css/*.css',function () {
        gulp.run('login_css');
    });

    //监听all.min.js的变动
    gulp.watch('./webapp/logistics/js/**/*.js', function(){
        gulp.run('logistics_scripts');
    });

    //监听all.min.css的变动
    gulp.watch('./webapp/logistics/css/**/*.css', function(){
        gulp.run('logistics_css');
    });


    //监听service的变动
    gulp.watch('./webapp/logistics/js/service/*.js', function(){
        gulp.run('logistics_services');
    });

    //监听directive的变动
    gulp.watch('./webapp/logistics/js/directive/*.js', function(){
        gulp.run('logistics_directive');
    });

    //监听controller的变动
    gulp.watch('./webapp/logistics/js/controller/**/*.js', function(){
        gulp.run('logistics_controllers');
    });

    //监听filter的变动
    gulp.watch('./webapp/logistics/js/filter/**/*.js', function(){
        gulp.run('logistics_filters');
    });


});
'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');
var gutil = require('gulp-util');
var stripNgLog = require('gulp-strip-ng-log');

var $ = require('gulp-load-plugins')({
  pattern: ['gulp-*', 'main-bower-files', 'uglify-save-license', 'del']
});
gulp.task('srvUglify',['srvInject'],function () {
  var jsFilter = $.filter('**/*.js', { restore: true });

  var assets;

  return gulp.src(path.join(conf.paths.tmp, '/serve/*.html'))
    .pipe(assets = $.useref.assets())
    //File Revisioning
//    .pipe($.rev())
    .pipe(jsFilter)
    //.pipe($.sourcemaps.init())
    .pipe($.ngAnnotate())
    //remove $log lines
    .pipe(stripNgLog())
    //Minify js
    .pipe($.uglify({ preserveComments: $.uglifySaveLicense })).on('error', conf.errorHandler('Uglify'))
    //.pipe($.sourcemaps.write('maps'))
    .pipe(jsFilter.restore)
    .pipe($.rename({
    	basename: 'services',
    	prefix: "serpics-sdk-",
    	suffix: '.min'
    }))
    //.pipe(gulp.dest(path.join(conf.paths.distSrv, '/')))
    .pipe(gulp.dest(path.join(conf.paths.mgnSrv, '/')))
    .pipe($.size({ title: path.join(conf.paths.distSrv, '/'), showFiles: true }));
  });

gulp.task('srvOther', function () {
	  var fileFilter = $.filter(function (file) {
	    return file.stat.isFile();
	  });

	  return gulp.src([
	                   path.join(conf.paths.srv, '/*.config.js'),
	  ])
	  //.pipe(fileFilter)

	  .pipe(gulp.dest(path.join(conf.paths.distSrv, '/scripts')))
	  .pipe($.rename({
		  basename: 'services.config',
		  prefix: "serpics-sdk-"
			  }))
      .pipe(gulp.dest(path.join(conf.paths.mgnSrv, '/scripts')));
//	  .pipe($.size({ title: path.join(conf.paths.distSrv, '/scripts'), showFiles: true }));
	});

  //Primo task eseguito nella fase di default
gulp.task('srvClean', function () {
	$.util.log(gutil.colors.red('Cleaning Directory:')+JSON.stringify([path.join(conf.paths.dist, '/'), path.join(conf.paths.tmp, '/')]));
	return $.del([path.join(conf.paths.tmp, '/'),path.join(conf.paths.dist, '/')],{force: true});
});

gulp.task('srvTasks', ['srvUglify','srvOther']);

gulp.task('srv', ['clean'], function () {
	  gulp.start('srvTasks');
	});

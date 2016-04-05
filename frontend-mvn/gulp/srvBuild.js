'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');
var gutil = require('gulp-util');

var $ = require('gulp-load-plugins')({
  pattern: ['gulp-*', 'main-bower-files', 'uglify-save-license', 'del']
});
gulp.task('srvUglify',['srvInject'],function () {
  var jsFilter = $.filter('**/*.js', { restore: true });

  var assets;

  return gulp.src(path.join(conf.paths.tmp, '/serve/*.html'))
    .pipe(assets = $.useref.assets())
    .pipe($.rev())
    .pipe(jsFilter)
    //.pipe($.sourcemaps.init())
    .pipe($.ngAnnotate())
    .pipe($.uglify({ preserveComments: $.uglifySaveLicense })).on('error', conf.errorHandler('Uglify'))
    //.pipe($.sourcemaps.write('maps'))
    .pipe(jsFilter.restore)
    .pipe(gulp.dest(path.join(conf.paths.srv, '/')))
    .pipe(gulp.dest(path.join('../magnolia/serpics-magnoglia/magnolia-webapp/src/main/webapp/serpics/webresources/services', '/')))
    .pipe($.size({ title: path.join(conf.paths.srv, '/'), showFiles: true }));
  });
  
  //Primo task eseguito nella fase di default
gulp.task('srvClean', function () {
	$.util.log(gutil.colors.red('Cleaning Directory:')+JSON.stringify([path.join(conf.paths.dist, '/'), path.join(conf.paths.tmp, '/')]));
	return $.del([path.join(conf.paths.tmp, '/'),path.join(conf.paths.dist, '/')],{force: true});
});

gulp.task('srv', ['srvClean','srvUglify']);

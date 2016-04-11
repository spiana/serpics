'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var $ = require('gulp-load-plugins')();

var wiredep = require('wiredep').stream;
var _ = require('lodash');

var browserSync = require('browser-sync');
//file js da escludere 
var exJsFiles1 = path.join('!' + 'src/app/content/**/*jquery.prettyPhoto.js');
var exJsFiles2 = path.join('!' + 'src/app/content/**/*ngDialog.min.js');

gulp.task('inject-reload', ['inject'], function() {
  browserSync.reload();
});
//vengono iniettati i file js e css nel file index.html messo nella root della cartella src
gulp.task('inject', ['scripts'], function () {
  var injectStyles = gulp.src([
    path.join(conf.paths.src, '/app/**/*.css')
  ], { read: false });

  var injectScripts = gulp.src([
    path.join(conf.paths.src, '/app/**/*.module.js'),
    path.join(conf.paths.src, '/app/**/*.js'),
    path.join('!' + 'src/app/content/**/*jquery.prettyPhoto.js'),
    path.join('!' + 'src/app/content/**/*ngDialog.min.js'),
    path.join('!' + conf.paths.src, '/app/**/*.spec.js'),
    path.join('!' + conf.paths.src, '/app/**/*.mock.js'),
    path.join('!' + conf.paths.src, '/test/**/*')
  ])
  .pipe($.angularFilesort()).on('error', conf.errorHandler('AngularFilesort'));
//  .pipe($.ngAnnotate());

  var injectOptions = {
    ignorePath: [conf.paths.src, path.join(conf.paths.tmp, '/serve')],
    addRootSlash: false
  };

  return gulp.src(path.join(conf.paths.src, '/*.html'))
    .pipe($.inject(injectStyles, injectOptions))
    .pipe($.inject(injectScripts, injectOptions))
    .pipe(wiredep(_.extend({}, conf.wiredep)))
    .pipe(gulp.dest(path.join(conf.paths.tmp, '/serve')));
});

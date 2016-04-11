'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var $ = require('gulp-load-plugins')();

var wiredep = require('wiredep').stream;
var _ = require('lodash');

//vengono iniettati i file js e css nel file index.html messo nella root della cartella src
gulp.task('srvInject', ['srvScripts'], function () {
  //~ var injectStyles = gulp.src([
    //~ path.join(conf.paths.src, '/app/**/*.css')
  //~ ], { read: false });

  var injectScripts = gulp.src([
    path.join(conf.paths.srv, '/*.module.js'),
    path.join(conf.paths.srv, '/*Service.js'),
    path.join('!' + conf.paths.srv, '*.config.js'),
    path.join('!' + conf.paths.src, '/app/**/*.spec.js'),
    path.join('!' + conf.paths.src, '/app/**/*.mock.js'),
  ])
  .pipe($.angularFilesort()).on('error', conf.errorHandler('AngularFilesort'));

  var injectOptions = {
    ignorePath: [conf.paths.src, path.join(conf.paths.tmp, '/serve')],
    addRootSlash: false
  };

  return gulp.src(path.join(conf.paths.src, '/*.html'))
    //.pipe($.inject(injectStyles, injectOptions))
    .pipe($.inject(injectScripts, injectOptions))
    //.pipe(wiredep(_.extend({}, conf.wiredep)))
    .pipe(gulp.dest(path.join(conf.paths.tmp, '/serve')));
});

'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var $ = require('gulp-load-plugins')();

gulp.task('srvScripts', function() {
  return buildSrvScripts();
});

function buildSrvScripts() {
	//Controllo presenza errori nei services
  return gulp.src(path.join(conf.paths.src, '/app/**/*Service.js'))
    .pipe($.eslint())
    .pipe($.eslint.format())
    .pipe($.size())
};

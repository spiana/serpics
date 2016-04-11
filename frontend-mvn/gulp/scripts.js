'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var browserSync = require('browser-sync');

var $ = require('gulp-load-plugins')();
//File da escludere
var exJsFiles1 = path.join('!' + 'src/app/content/**/*jquery.prettyPhoto.js');
var exJsFiles2 = path.join('!' + 'src/app/content/**/*ngDialog.min.js');


gulp.task('scripts-reload', function() {
  return buildScripts()
    .pipe(browserSync.stream());
});

gulp.task('scripts', function() {
  return buildScripts();
});

function buildScripts() {
	//Controllo presenza errori
  return gulp.src([ path.join(conf.paths.src, '/app/**/*.js'),
                    exJsFiles1,
                    exJsFiles2])
    .pipe($.eslint())
    .pipe($.eslint.format())
    .pipe($.size())
};

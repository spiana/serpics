'use strict';
var path = require('path');
var gulp = require('gulp');

var $ = require('gulp-load-plugins')({
	  pattern: ['gulp-*', 'main-bower-files', 'uglify-save-license', 'del']
	});

var jsFiles = path.join('src/app/**/*.js');
var cssFiles = 'src/app/content/css/*.css';
var htmlFiles = 'src/app/**/*.html';
var exJsFiles1 = path.join('!' + 'src/app/content/**/*jquery.prettyPhoto.js');
var exJsFiles2 = path.join('!' + 'src/app/content/**/*ngDialog.min.js');

//General purpose linter
gulp.task('jshint', function() {
  //log('Analyzing source with JSHint and JSCS');

  return gulp
    .src([ jsFiles, exJsFiles1, exJsFiles2])
    //.pipe($.if(args.verbose, $.print()))
    .pipe($.jshint())
    .pipe($.jshint.reporter('jshint-stylish', { verbose: true }))
    .pipe($.jshint.reporter('fail'))
    .pipe($.jscs())
//    .pipe($.jscs.reporter())
//	.pipe($.jscs.reporter('fail'));

});

// Javascript/angular oriented
gulp.task('eslint', function() {
	  return buildScripts();
	});

	function buildScripts() {
		//Controllo presenza errori
	  return gulp.src([ jsFiles, exJsFiles1, exJsFiles2])
	    .pipe($.eslint())
	    .pipe($.eslint.format())
	    .pipe($.size())
	};

//Jscs code style oriented
gulp.task('jscs', function() {
		  //log('Analyzing source with JSHint and JSCS');
		  return gulp.src([ jsFiles, exJsFiles1, exJsFiles2])
		    .pipe($.jscs())
		    .pipe($.jscs.reporter())
			.pipe($.jscs.reporter('fail'));
		  });
		  
//CSSLint plugin for gulp
gulp.task('cssLint', function() {
  gulp.src(cssFiles)
    .pipe(csslint())
    .pipe(csslint.reporter());
});


//Hmtl Linter
gulp.task('htmlLint', function() {
return gulp.src(htmlFiles)
	.pipe($.htmllint({}, htmllintReporter));
});

function htmllintReporter(filepath, issues) {
if (issues.length > 0) {
	issues.forEach(function (issue) {
		$.util.log($.util.colors.cyan('[gulp-htmllint] ') + $.util.colors.white(filepath + ' [' + issue.line + ',' + issue.column + ']: ') + $.util.colors.red('(' + issue.code + ') ' + issue.msg));
	});

	process.exitCode = 1;
}
}
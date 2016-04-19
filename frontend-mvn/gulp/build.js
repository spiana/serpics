'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');
var gutil = require('gulp-util');
var cleanCss = require('gulp-clean-css');

var $ = require('gulp-load-plugins')({
  pattern: ['gulp-*', 'main-bower-files', 'uglify-save-license', 'del']
});

gulp.task('partials', function () {
  return gulp.src([
    path.join(conf.paths.src, '/app/**/*.html'),
    path.join(conf.paths.tmp, '/serve/app/**/*.html')
  ])
    .pipe($.minifyHtml({
      empty: true,
      spare: true,
      quotes: true
    }))
    .pipe($.angularTemplatecache('templateCacheHtml.js', {
      module: 'serpics',
      root: 'app'
    }))
    .pipe(gulp.dest(conf.paths.tmp + '/partials/'));
});

gulp.task('html', ['inject', 'partials'], function () {
  var partialsInjectFile = gulp.src(path.join(conf.paths.tmp, '/partials/templateCacheHtml.js'), { read: false });
  var partialsInjectOptions = {
    starttag: '<!-- inject:partials -->',
    ignorePath: path.join(conf.paths.tmp, '/partials'),
    addRootSlash: false
  };

  var htmlFilter = $.filter('*.html', { restore: true });
  var jsFilter = $.filter('**/*.js', { restore: true });
  var cssFilter = $.filter('**/*.css', { restore: true });
  var assets;

  return gulp.src(path.join(conf.paths.tmp, '/serve/*.html'))
    .pipe($.inject(partialsInjectFile, partialsInjectOptions))
    .pipe(assets = $.useref.assets())
    .pipe($.rev())
    .pipe(jsFilter)
    .pipe($.sourcemaps.init())
    .pipe($.ngAnnotate())
    .pipe($.uglify({ preserveComments: $.uglifySaveLicense })).on('error', conf.errorHandler('Uglify'))
    .pipe($.rename({
    	prefix: "serpics-sdk-",
    	suffix: '.min'
    }))
    .pipe($.sourcemaps.write('maps'))
    .pipe(jsFilter.restore)
    .pipe(cssFilter)
    .pipe($.sourcemaps.init())
//    .pipe($.minifyCss({ processImport: false }))
    .pipe($.cleanCss({processImport: false,rebase: true}))
    .pipe($.sourcemaps.write('maps'))
    .pipe(cssFilter.restore)
    .pipe(assets.restore())
    .pipe($.useref())
    .pipe($.revReplace())
    .pipe(htmlFilter)
//    .pipe($.minifyHtml({
//      empty: true,
//      spare: true,
//      quotes: true,
//      conditionals: true
//    }))
    .pipe($.htmlmin({
    	collapseWhitespace: true,
    	}))
    .pipe(htmlFilter.restore)
    .pipe(gulp.dest(path.join(conf.paths.dist, '/')))
    .pipe($.size({ title: path.join(conf.paths.dist, '/'), showFiles: true }));
  });

// Only applies for fonts from bower dependencies
// Custom fonts are handled by the "other" task
gulp.task('fonts', function () {
  return gulp.src($.mainBowerFiles())
    .pipe($.filter('**/*.{eot,svg,ttf,woff,woff2,otf}'))
    .pipe($.flatten())
    .pipe(gulp.dest(path.join(conf.paths.dist, '/fonts/')));
});

gulp.task('other', function () {
  var fileFilter = $.filter(function (file) {
    return file.stat.isFile();
  });

//File da escludere
//  var exJsFiles1 = path.join('src/app/content/**/*jquery.prettyPhoto.js');
//  var exJsFiles2 = path.join('src/app/content/**/*ngDialog.min.js');
  return gulp.src([
    path.join(conf.paths.src, '/**/*'),
    path.join('!' + conf.paths.src, '/**/*.{html,css,js}'),
    path.join('!' + conf.paths.src,'**/*.{eot,svg,ttf,woff,woff2,otf}'),
    path.join('!' + conf.paths.src,'/app/content/css/**/*'),
    path.join('!' + conf.paths.src, '/test/**/*')
//    ,
//    exJsFiles1,exJsFiles2
  ])
    .pipe(fileFilter)
    .pipe(gulp.dest(path.join(conf.paths.dist, '/')));
});
//Copy ccs images into dist directory
gulp.task('cssImages', function () {
	  var fileFilter = $.filter(function (file) {
	    return file.stat.isFile();
	  });

	  return gulp.src([
	    path.join(conf.paths.src, '/app/content/css/main/**/*.{jpg,png}'),
	    path.join('!' + conf.paths.src, '/**/*.{html,css,js}'),
	    path.join('!' + conf.paths.src,'**/*.{eot,svg,ttf,woff,woff2,otf}')
	    //path.join('!' + conf.paths.src,'/app/content/**/*')
	  ])
	    .pipe(fileFilter)
	    .pipe(gulp.dest(path.join(conf.paths.dist, '/styles')));
	});
//Primo task eseguito nella fase di default
gulp.task('clean', function () {
	$.util.log(gutil.colors.red('Cleaning Directory:')+JSON.stringify([path.join(conf.paths.dist, '/'), path.join(conf.paths.tmp, '/')]));
	return $.del([path.join(conf.paths.tmp, '/**/*'),path.join(conf.paths.dist, '/**/*')],{force: true});
});

gulp.task('build', ['html', 'fonts', 'other','cssImages']);

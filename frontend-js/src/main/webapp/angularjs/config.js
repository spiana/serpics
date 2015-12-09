var app = angular.module('serpics.config', [])
			.constant('APP_NAME','Serpics App')
			.constant('APP_VERSION','0.1')
			.constant('URL','http://localhost:9090')
			.constant('ENDPOINT','/jax-rs/customerService/')
			.constant('COOKIE_EXIPES','20') //[minuti]
			.constant('BREADCRUMBS','Home') //[serpicsApp constant]
			.constant('LOADINGTEXT','Serpics') //[rotating loadingtext label]
			.constant('TITLE','Serpics Platform Ecommerce') //[rotating loadingtext label]
			.constant('WELCOME','Welcome') //welcome for user logged in



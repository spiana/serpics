<!DOCTYPE html>
<html lang="en" ng-app="serpics.App">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link href="${ctx.contextPath}/.resources/serpics/webresources/css/main.css" rel="stylesheet">
<link href="${ctx.contextPath}/.resources/serpics/webresources/css/ngDialog.css" rel="stylesheet">

<!--[if lt IE 9]><script src="js/ie-emulation-modes-warning.js"></script><![endif]-->
<script src="js/ie-emulation-modes-warning.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.8/angular-cookies.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.8/angular-loader.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.8/angular-route.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.14/angular-ui-router.min.js"></script>

<link rel="apple-touch-icon-pre0composed" sizes="144x144" href="${ctx.contextPath}/.resources/serpics/webresources/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ctx.contextPath}/.resources/serpics/webresources/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ctx.contextPath}/.resources/serpics/webresources/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="${ctx.contextPath}/.resources/serpics/webresources/images/ico/apple-touch-icon-57-precomposed.png">

	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/config.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/serpicsApp.js"></script>
 <!-- 	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/router.js"></script> -->
	
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/ngDialog.js"></script>
	
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/serpicsInterceptor.js"></script>

	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/categoryController.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/brandController.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/productController.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/cartController.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/orderController.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/loginController.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/customerController.js"></script>
    
    <script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/customerService.js"></script>
    <script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/serpicsServices.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/categoryService.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/brandService.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/productService.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/cartService.js"></script>
	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/orderService.js"></script>
 <!-- 	<script src="${ctx.contextPath}/.resources/serpics/webresources/angularjs/directive.js"></script> -->

<title>Serpics Platform Ecommerce</title>
[@cms.page /]
</head>
<body class="ng-cloak" >

[#assign baseSite = cmsfn.asContentMap(model.root.node.parent)]
[#if baseSite == ""]
[#assign baseSite = model.root.content]
[/#if]					

 <!--  dinamyc html with custom directive -->
	
	
 <!-- <div loader-directive text-loading="SERPICS"></div> -->

<header id="header">
[@cms.area name="serpics-header" contextAttributes={"baseSite":baseSite}/]
</header>
	
[@cms.area name="serpics-main" contextAttributes={"baseSite":baseSite}/]
	
[@cms.area name="serpics-footer" contextAttributes={"baseSite":baseSite}/]
	<!-- JS -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx.contextPath}/.resources/serpics/webresources/js/jquery.prettyPhoto.js"></script>

</body>
</html>
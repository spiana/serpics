<!--header-middle-->
<div class="header-middle">
	<div class="container">
		<div class="row" ng-controller="loginController">
			<div class="col-sm-4">
				<div class="logo pull-left">
					<span class="current-user">
					<span  ng-show="currentUser.userType=='REGISTERED'">Welcome  {{currentUser.firstname + ' ' + currentUser.lastname}}</span>								
					<span  ng-show="currentUser.userType=='ANONYMOUS'">Guest Access</span>										
				</span>
				</div>				
			</div>
			<div class="col-sm-8">
				<div class="shop-menu pull-right">
					<ul class="nav navbar-nav">
						<li ng-show="currentUser.userType=='REGISTERED'"><a href="${ctx.contextPath}/${ctx.baseSite}/PersonalArea"><i class="fa fa-user"></i> Personal Area</a></li>
						<li ng-click="showModalOnSuccess()"><a href="${ctx.contextPath}/${ctx.baseSite}/CheckoutBilling"><i	class="fa fa-crosshairs"></i> Checkout</a></li>
						<li><a href="${ctx.contextPath}/${ctx.baseSite}/Cart"><i	class="fa fa-shopping-cart"></i> Cart</a></li>
						<li ng-show="currentUser.userType=='ANONYMOUS'"><a href="${ctx.contextPath}/${ctx.baseSite}/Login"><i ng-class="{ 'fa fa-sign-out': currentUser.userType=='REGISTERED', 'fa fa-sign-in': currentUser.userType=='ANONYMOUS' }" ></i> Login</a></li>
						<li ng-show="currentUser.userType=='REGISTERED'" ng-click="logout()"><a href=""><i class="fa fa-sign-out"></i> Logout</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!--/header-middle-->
<!--form-->
[#assign url = ""]
[#if model.root.content == "CheckoutLogin"]
[#assign url = "CheckoutShipping"]
[/#if]
<section id="form" ng-controller="loginController" ng-init="afterLoginUrl='${ctx.contextPath}/${ctx.baseSite}/${url}.html'">
	<div class="container">
		<div class="row">
			<div class="col-sm-8">
				<div class="login-form">
					<!--login form-->
					<h2>Login to your account</h2>
					<span class="help-block" ng-model="currentUser.message">{{currentUser.message}}</span>
					<form ng-model="action.funcionName" ng-submit="login(loginUser)">
						<input type="text" ng-model="loginUser.username"
							name="loginUser.username" placeholder="Name" required /> <input
							type="password" ng-model="loginUser.password"
							name="loginUser.password" placeholder="password" required /> 
							<span class="pull-right"><input type="checkbox" class="checkbox"> Keep me signed	in	</span>
						<div class="pull-left">
							<button type="submit" class="btn btn-default" id="submit">Login</button>
							<a href="${ctx.contextPath}/${ctx.baseSite}/Register.html" class="btn btn-default register" role="button">Register</a>
						</div>
					</form>
				</div>
				<!--/login form-->
			</div>
		</div>
	</div>
</section>
<!--/form-->
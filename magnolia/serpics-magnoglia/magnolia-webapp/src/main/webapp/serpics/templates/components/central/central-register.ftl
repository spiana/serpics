<!--form-->
<script type="text/ng-template" id="registerSuccessDialog">
   <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" ng-click="closeThisDialog()">&times;</button>
        <h4 class="modal-title">Serpics Platform Ecommerce</h4>
      	</div>
      	<div class="modal-body">
        <p><i class="fa fa-check"></i> User Registered Succesfully!!!. Back To Home Or Login Please!!!</p>
		</div>
		<div class="modal-footer">
		<a href="${ctx.contextPath}/${ctx.baseSite}/Home.html" class="btn btn-default" id="home" >Home</a>
		<a class="btn btn-default" id="modalGo" ng-click="closeThisDialog()">Login</a>
	</div>
 </div>          
</div>
</script>
[#assign url = ""]
[#if model.root.content == "CheckoutLogin"]
[#assign url = "Checkout"]
[/#if]
<section id="form" ng-controller="loginController" ng-init="afterRegisterUrl='${ctx.contextPath}/${ctx.baseSite}/${url}Login.html'">
	<div class="container">
		<div class="row">			
			<div class="col-sm-8">
				<div class="signup-form">
					<!--sign up form-->
					<h2>New User Signup!</h2>					
					<span class="help-block" ng-model="registerUser.registerMessage">{{currentUser.registerMessage}}</span>
					<form ng-submit="register(registerUser)">
						<input type="text" ng-model="registerUser.firstname" name="registerUser.firstname" placeholder="Name"  required/> 
						<input type="text" ng-model="registerUser.lastname" name="registerUser.lastname" placeholder="Last Name"  required/> 
						<input type="email" ng-model="registerUser.email" name="registerUser.email"	placeholder="Email" required/> 
						<input type="text" ng-model="registerUser.logonid" name="registerUser.logonid" placeholder="Username"  required/> 
						<input type="password" ng-model="registerUser.password" name="registerUser.password"	placeholder="Password" required/>
						<button type="submit" class="btn btn-default" id="submit">Register</button>
					</form>	
				</div>
				<!--/sign up form-->
			</div>
		</div>
	</div>
</section>
<!--/form-->
<script type="text/ng-template" id="registerSuccessDialog">
   <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" ng-click="closeThisDialog()">&times;</button>
        <h4 class="modal-title">Serpics Platform Ecommerce</h4>
      	</div>
      	<div class="modal-body">
        <p><i class="fa fa-check"></i> User Registered Succesfully!!!. Back To Home Or Login Please!!!</p>
		</div>
		<div class="modal-footer">
		<a href="${ctx.contextPath}/${ctx.baseSite}" class="btn btn-default" id="home" >Home</a>
		<a class="btn btn-default" id="modalGo" ng-click="closeThisDialog()">Login</a>
	</div>
 </div>          
</div>
</script>
<!--header-bottom-->
<div class="header-bottom">
	<div class="container">
		<div class="row">
			<div class="col-sm-9">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="mainmenu pull-left">
					<ul class="nav navbar-nav collapse navbar-collapse">
						<li><a href="${ctx.contextPath}/${ctx.baseSite}" class="active">Home</a></li>
						<li class="dropdown"><a href="#">Shop<i
								class="fa fa-angle-down"></i></a>
							<ul role="menu" class="sub-menu">								
								<li><a href="${ctx.contextPath}/${ctx.baseSite}/CheckoutBilling.html">Checkout</a></li>
								<li><a href="${ctx.contextPath}/${ctx.baseSite}/Cart.html">Cart</a></li>	
								<li><a href="${ctx.contextPath}/${ctx.baseSite}/Register.html">Register</a></li>	
							</ul>
						</li>						
					</ul>
				</div>
			</div>
			<div class="col-sm-3">
				<form action="${ctx.contextPath}/${ctx.baseSite}/Search.html">
					<div class="search_box input-group pull-right">
					    <input name="textSearch" type="text" class="pull-right form-control" ng-model="searchText" placeholder="Search" >
					    <span class="input-group-btn">
					    	<button class="btn btn-default" type="submit" ></button>
					    </span>
    				</div><!-- /input-group -->
    			</form>
			</div>
		</div>
	</div>
</div>
<!--/header-bottom-->

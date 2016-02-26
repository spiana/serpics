[#assign brandId = ctx.brandId!]
[#assign brandName = ctx.brandName!]
<div class="features_items" ng-controller="productController" ng-init="findByBrand(${brandId},$scope.page, $scope.size); cartUrl='${ctx.contextPath}/${ctx.baseSite}/Cart.html'"">
	<!--features_items-->
	<h2 class="title text-center">${brandName}</h2>
	<div ng-show="textSearch.length && product.totalElements > 0" style="margin-bottom: 15px;" >Found {{product.totalElements}} products for <i>{{textSearch}}</i>.
	</div>
	<div class="col-sm-12 clearfix">
		<div class="col-sm-9 clearfix">	
		<ul class="pagination">
		    <li><a href="" ng-click="findByBrand(${brandId},product.number - 1,product.size)" ng-show="product.number > 0">&laquo;</a></li>
		    <li ng-repeat="i in range(product.totalPages)" ng-class="{active: product.number == $index}"><a href="" ng-click="findByBrand(${brandId},$index,product.size)">{{$index+1}}</a></li>
		    <li><a href="" ng-click="findByBrand(${brandId},product.number + 1,product.size)" ng-show="product.number < product.totalPages - 1">&raquo;</a></li>
		</ul>
		</div>
		<div class="col-sm-3 clearfix">
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" >
				Products per page  <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li><a href=""  ng-click="findByBrand(${brandId},0,3)">3</a></li>
				<li><a href=""  ng-click="findByBrand(${brandId},0,6)">6</a></li>
				<li><a href=""  ng-click="findByBrand(${brandId},0,9)">9</a></li>
			</ul>
		</div>
	</div>

	<div class="col-sm-4" ng-repeat="product in product.content">
		<div class="product-image-wrapper">
			<div class="single-products">
				<div class="productinfo text-center">
					<img src="${ctx.contextPath}/.resources/serpics/webresources/images/home/product1.jpg" alt="" />
					<h2>&euro;{{product.price.currentPrice}}</h2>
					<p><a href="${ctx.contextPath}/${ctx.baseSite}/Product.html?productId={{product.id}}">{{product.code}}</a></p>
					<a class="btn btn-default add-to-cart" ng-click="addToCart(product.code,1)"><i
						class="fa fa-shopping-cart"></i>Add to cart</a>
				</div>
				<div class="product-overlay">
					<div class="overlay-content">
						<h2>&euro;{{product.price.currentPrice}}</h2>
						<p><a href="${ctx.contextPath}/${ctx.baseSite}/Product.html?productId={{product.id}}">{{product.code}}</a></p>				
						<a class="btn btn-default add-to-cart" ng-click="addToCart(product.code,1)"><i
							class="fa fa-shopping-cart"></i>Add to cart</a>
					</div>
				</div>
			</div>
			<div class="choose">
				<ul class="nav nav-pills nav-justified">
					<li><a href="#"><i class="fa fa-plus-square"></i>Add to
							wishlist</a></li>
					<li><a href="#"><i class="fa fa-plus-square"></i>Add to
							compare</a></li>
				</ul>
			</div>
		</div>
</div>
	
	<div ng-show="product.totalElements == 0"><h4>Nessun prodotto trovato!</h4></div>
	
</div>

<ul class="pagination">
    <li><a href="" ng-click="findByBrand(${brandId},product.number - 1,product.size)" ng-show="product.number > 0">&laquo;</a></li>
    <li ng-repeat="i in range(product.totalPages)" ng-class="{active: product.number == $index}"><a href="" ng-click="findByBrand(${brandId},$index,product.size)">{{$index+1}}</a></li>
    <li><a href="" ng-click="findByBrand(${brandId},product.number + 1,product.size)" ng-show="product.number < product.totalPages - 1">&raquo;</a></li>
</ul>
</div>
<!--features_items-->
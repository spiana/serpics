[#assign brandId = ctx.brandId!]
[#assign brandName = ctx.brandName!]
<div class="features_items" ng-controller="productController" ng-init="findByBrand(${brandId},$scope.page, $scope.size); cartUrl='${ctx.contextPath}/${ctx.baseSite}/Cart'"">
	<!--features_items-->
	<h2 class="title text-center">${brandName}</h2>
	<div ng-show="textSearch.length && product.totalElements > 0" style="margin-bottom: 15px;" >Found {{product.totalElements}} products for <i>{{textSearch}}</i>.
	</div>
	<div class="col-sm-12 clearfix">
		<div class="col-sm-9 clearfix">	
		<ul class="pagination">
		    <li><a href="" ng-click="findByBrand(${brandId},0,product.size)" ng-show="product.number > 0">&laquo;</a></li>
		    <li><a href="" ng-click="findByBrand(${brandId},product.number - 1,product.size)" ng-show="product.number > 0">&lsaquo;</a></li>
		    <li ng-repeat="i in range(product.totalPages)" ng-class="{active: product.number == $index}" ng-show="((((product.number - $index > -3) || $index < 5) && product.number < $index) || (((product.number - $index < 3) || $index > (product.totalPages - 6)) && product.number > $index) || (product.number == $index))"><a href="" ng-click="findByBrand(${brandId},$index,product.size)">{{$index+1}}</a></li>
		    <li><a href="" ng-click="findByBrand(${brandId},product.number + 1,product.size)" ng-show="product.number < product.totalPages - 1">&rsaquo;</a></li>
		    <li><a href="" ng-click="findByBrand(${brandId},product.totalPages - 1,product.size)" ng-show="product.number < product.totalPages - 1">&raquo;</a></li>
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

[@cms.area name="serpics-product-list" contextAttributes={"baseSite":ctx.baseSite}/]
	
</div>
		<ul class="pagination">
		    <li><a href="" ng-click="findByBrand(${brandId},0,product.size)" ng-show="product.number > 0">&laquo;</a></li>
		    <li><a href="" ng-click="findByBrand(${brandId},product.number - 1,product.size)" ng-show="product.number > 0">&lsaquo;</a></li>
		    <li ng-repeat="i in range(product.totalPages)" ng-class="{active: product.number == $index}" ng-show="((((product.number - $index > -3) || $index < 5) && product.number < $index) || (((product.number - $index < 3) || $index > (product.totalPages - 6)) && product.number > $index) || (product.number == $index))"><a href="" ng-click="findByBrand(${brandId},$index,product.size)">{{$index+1}}</a></li>
		    <li><a href="" ng-click="findByBrand(${brandId},product.number + 1,product.size)" ng-show="product.number < product.totalPages - 1">&rsaquo;</a></li>
		    <li><a href="" ng-click="findByBrand(${brandId},product.totalPages - 1,product.size)" ng-show="product.number < product.totalPages - 1">&raquo;</a></li>
		</ul>
</div>
<!--features_items-->
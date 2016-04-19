<div class="brands_products" ng-controller="BrandController">
<!--brands_products-->
	<h2>Brand</h2>
	<div class="brands-name">
		<ul class="nav nav-pills nav-stacked">
			<li ng-repeat="brand in brandData" ng-show="brand.brandProductNumber > 0"><a href="${ctx.contextPath}/${ctx.baseSite}/Brand?brandId={{brand.id}}&brandName={{brand.name}}"> <span class="pull-right">{{brand.brandProductNumber}}</span>{{brand.name || brand.code}}</a></li>					
		</ul>
	</div>
</div>
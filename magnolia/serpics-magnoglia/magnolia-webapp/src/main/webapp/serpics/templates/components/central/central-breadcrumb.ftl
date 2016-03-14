<div style="width:100%">
	<div class='row' ng-if="product" ng-init="getBreadcrumb()">
		<ul class='breadcrumb'>
			<li><a href="${ctx.contextPath}/${ctx.baseSite}">Home</a></li>
			<li ng-repeat="cat in breadcrumbCategories | orderBy:'-'"><a href="${ctx.contextPath}/${ctx.baseSite}/Category?categoryId={{cat.id}}&categoryName={{cat.code}}">{{cat.name || cat.code | lowercase}}</a></li>
			<li class="active">{{product.name || product.code | lowercase}}</li>
		</ul>
	</div>
</div>
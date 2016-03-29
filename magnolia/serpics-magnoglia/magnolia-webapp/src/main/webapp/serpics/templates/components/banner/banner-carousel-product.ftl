[#assign productCode = content.productCode!]
<div class="item" ng-class="{active: ('${content}'=='0')}">
							<div ng-controller="productController" ng-init="getProductByCode('${productCode}'); cartUrl='${ctx.contextPath}/${ctx.baseSite}/Cart'">
								<div class="col-sm-6">
									<h1><span>SERPICS</span></h1>
									<h2><a href="${ctx.contextPath}/${ctx.baseSite}/Product?productId={{product.id}}">{{product.name || product.code}}</a></h2>
									<p ng-bind-html="trustAsHtml(product.description)"></p>
									<button type="button" class="btn btn-default get" ng-click="addToCart(product.code,1)">Buy now</button>
								</div>
								<div class="col-sm-6">
									<a href="${ctx.contextPath}/${ctx.baseSite}/Product?productId={{product.id}}"><img ng-src="{{product.primaryImage.source}}" class="girl img-responsive" alt="" style="height: 441px; max-width: 100%; margin: auto"></a>
								</div>
							</div>
</div>
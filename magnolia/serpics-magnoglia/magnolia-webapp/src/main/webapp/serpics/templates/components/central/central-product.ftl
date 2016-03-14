[#assign productId = ctx.productId!]
					<div class="product-details" ng-controller="productController" ng-init="getProduct(${productId}); cartUrl='${ctx.contextPath}/${ctx.baseSite}/Cart'"><!--product-details-->
						<h2 class="title text-center">{{product.name}}</h2>
						<div class="col-sm-5">
							<div class="view-product">
							<h2 ng-hide="product.primaryImage.source"><i class="fa fa-spinner fa-spin fa-2x" style="background-image: url('');background-position: center;background-size: contain;background-repeat: no-repeat;"></i></h2>
							
									<div  ng-show="product.primaryImage.source" style="background-image: url('{{product.primaryImage.source}}');background-position: center;background-size: contain;background-repeat: no-repeat;height: 380px;"></div>
									<a href="{{product.primaryImage.source}}" rel="prettyPhoto" title="title here"><h3>ZOOM</h3></a>
							</div>
							<div id="similar-product" class="carousel slide" data-ride="carousel" ng-init="gallery = 0">
								
								  <!-- Wrapper for slides -->
								    <div class="carousel-inner" data-role="image">
										<div class="item active">
										  <div ng-repeat="i in range(3)" style="width:33%; float:left" ng-show="product.medias[i + gallery]">
										  <a href="{{product.medias[i + gallery].source}}"><div style="display: table;margin: 0 auto;background-image: url('{{product.medias[i + gallery].source}}');background-position: center;background-size: contain;background-repeat: no-repeat;height: 85px;width:85px" /></a>
										  </div>
										</div>
										
									</div>

								  <!-- Controls -->
								  <button style="padding: 0px; border: none;" class="left item-control" data-slide="prev" ng-click="gallery = gallery - 1" ng-hide="gallery < 1">
									<i class="fa fa-angle-left"></i>
								  </button>
								  <button style="padding: 0px; border: none;" class="right item-control" data-slide="next"  ng-click="gallery = gallery + 1" ng-hide="gallery > (product.medias.length - 4)">
									<i class="fa fa-angle-right"></i>
								  </button>
							</div>
						</div>
						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/new.jpg" class="newarrival" alt="" />
								<h2 id="productDescription" ng-bind-html="trustAsHtml(product.description)"></h2>
								<p id="productId">{{product.id}}</p>
								<img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/rating.png" alt="" />
								<span>
									<span id="producePrice">&euro; {{product.price.currentPrice}}</span>
									<label>Quantity:</label>
									<input type="text"  ng-pattern="/^[0-9]{1,2}$/" ng-model="defaultQuantity">
									<button type="button" class="btn btn-fefault cart" ng-click="addToCart(product.code,defaultQuantity)" >
										<i class="fa fa-shopping-cart"></i>
										Add to cart
									</button>
								</span>
								<p id="buyable"></p>
								<p><strong>Condition:</strong> New</p>
								<p id="productCategory"><strong>Category: {{product.categories[0].name || product.categories[0].code}}</strong></p>								
								<p id="productBrand"><strong>Brand: {{product.brand.name || product.brand.code}}</strong></p>
								<a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/share.png" class="share img-responsive"  alt="" /></a>
							</div><!--/product-information-->
						</div>
					</div><!--/product-details-->
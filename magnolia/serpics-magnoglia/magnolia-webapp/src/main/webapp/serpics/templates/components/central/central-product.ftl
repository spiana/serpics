[#assign productId = ctx.productId!]
					<div class="product-details" ng-controller="productController" ng-init="getProduct(${productId}); cartUrl='${ctx.contextPath}/${ctx.baseSite}/Cart'"><!--product-details-->
						<h2 class="title text-center">{{product.name}}</h2>
						<div class="col-sm-5">
							<div class="view-product">
	 								<a href="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/1.jpg" rel="prettyPhoto" title="title here">  
								<img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/1.jpg" alt="">
									</a>
								<h3>ZOOM</h3>
							</div>
							<div id="similar-product" class="carousel slide" data-ride="carousel">
								
								  <!-- Wrapper for slides -->
								    <div class="carousel-inner" data-role="image">
										<div class="item active">
										  <a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/similar1.jpg" alt=""></a>
										  <a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/similar2.jpg" alt=""></a>
										  <a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/similar3.jpg" alt=""></a>
										</div>
										<div class="item">
										  <a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/similar1.jpg" alt=""></a>
										  <a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/similar2.jpg" alt=""></a>
										  <a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/similar3.jpg" alt=""></a>
										</div>
										<div class="item">
										  <a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/similar1.jpg" alt=""></a>
										  <a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/similar2.jpg" alt=""></a>
										  <a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/similar3.jpg" alt=""></a>
										</div>
										
									</div>

								  <!-- Controls -->
								  <a class="left item-control" href="#similar-product" data-slide="prev">
									<i class="fa fa-angle-left"></i>
								  </a>
								  <a class="right item-control" href="#similar-product" data-slide="next">
									<i class="fa fa-angle-right"></i>
								  </a>
							</div>

						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/new.jpg" class="newarrival" alt="" />
								<h2 id="productDescription">{{product.description}}</h2>
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
								<p id="productCategory"><strong>Category: {{product.categories[0].code}}</strong></p>								
								<p id="productBrand"><strong>Brand: {{product.brand.name}}</strong></p>
								<a href=""><img src="${ctx.contextPath}/.resources/serpics/webresources/images/product-details/share.png" class="share img-responsive"  alt="" /></a>
							</div><!--/product-information-->
						</div>
					</div><!--/product-details-->
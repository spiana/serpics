<div ng-controller="cartController">
	<section id="cart_items" >
		<div class="container">
			<div>
				<h4 ng-hide="cart.orderItems.length">Carrello vuoto!<br><br></h4>
			</div>
			<div class="table-responsive cart_info" style="width: 73%; overflow-x: hidden;">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="quantity">Quantity</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="item in cart.orderItems">
							<td class="cart_product"><div style="background-image: url('{{item.product.primaryImage.source}}');background-position: center;background-size: contain;background-repeat: no-repeat;height: 110px; width: 110px;"></div></td>
							<td class="cart_description">
								<h4>
									<a href="${ctx.contextPath}/${ctx.baseSite}/Product?productId={{product.id}}"">{{item.sku}}</a>
									<p>Web ID: {{item.product.id}}</p>
								</h4>
							</td>
							<td class="cart_price">
								<p>&euro;{{item.skuNetPrice}}</p>
							</td>
							<td class="cart_quantity">
								<div class="cart_quantity_button">
								<a class="cart_quantity_down" ng-click="cartUpdate(item, item.quantity - 1)"> - </a>
									<input class="cart_quantity_input" ng-pattern="/^[0-9]{1,2}$/" ng-model="item.quantity" ng-change="cartUpdate(item, item.quantity)" type="text" autocomplete="off" size="2">
								<a class="cart_quantity_up" ng-click="cartUpdate(item, item.quantity + 1)"> + </a>
								</div>
							</td>
							<td class="cart_delete">
								<a class="cart_quantity_delete" ng-click="deleteItem(item.id)">
									<i class="fa fa-times"></i>
								</a>
							</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</section>
	<div class="col-sm-6">
					<div class="total_area">
						<ul>
							<li>Cart Sub Total <span>&euro;{{(cart.totalProduct | number:2) || 0}}</span></li>
							<li>Services Sub Total <span>&euro;{{(cart.totalService | number:2) || 0}}</span></li>
							<li>Tax Sub Total <span>&euro;{{(cart.totalTax | number:2) || 0}}</span></li>
							<li>Shipping Cost <span>&euro;{{(cart.totalShipping | number:2) || 0}}</span></li>
							<li id="total">Total <span>&euro;{{(cart.orderAmount | number:2) || (cart.totalProduct | number:2) || 0}}</span></li>
						</ul>
						<div>
							<a class="btn btn-default update" ng-click="getCurrentCart()">Update Cart</a>
							<a class="btn btn-default update" ng-click="deleteCart()">Delete Cart</a>  
						</div>
					</div>
	</div>
						<div class="col-sm-12" ng-show="cart.orderItems.length">
							<a class="btn btn-default check_out pull-right" href="${ctx.contextPath}/${ctx.baseSite}/CheckoutBilling" >Continue to Checkout</a>
						</div>
	</div>
	<!--/#cart_items-->
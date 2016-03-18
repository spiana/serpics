	<section id="cart_items" ng-controller="cartController" ng-init="shippingAddressUrl='${ctx.contextPath}/${ctx.baseSite}/CheckoutShipping'; shipmodeUrl='${ctx.contextPath}/${ctx.baseSite}/CheckoutShipmode'; paymentUrl='${ctx.contextPath}/${ctx.baseSite}/CheckoutPaymethod'; paypalCheckoutUrl='${ctx.contextPath}/${ctx.baseSite}/CheckoutPaymethodPaypal'">
		<div class="container">
		[#assign page = model.root.content]
			<div class="step-one">
				<h2 class="heading" ng-show="'${page.title}' == 'Checkout Billing Address' || '${page.title}' == 'Checkout Register' || '${page.title}' == 'Checkout Login'" >Step1</h2>
				<h2 class="heading" ng-show="'${page.title}' == 'Checkout Shipping Address'" >Step2</h2>
				<h2 class="heading" ng-show="'${page.title}' == 'Checkout Shipmode'" >Step3</h2>
				<h2 class="heading" ng-show="'${page.title}' == 'Checkout Paymethod'" >Step4</h2>
			</div>

			<div ng-show="currentUser.userType == 'ANONYMOUS'">
						
			<div class="checkout-options">
				<h3>New User</h3>
				<p>Checkout options</p>
				<ul class="nav">
					<li>
						<label><a href="CheckoutBilling" > Guest checkout</a></label>
					</li>
					<li>
						<label><a href="CheckoutLogin" > Login</a></label>
					</li>
					<li>
						<label><a href="CheckoutRegister" > Register</a></label>
					</li>
				</ul>
			</div><!--/checkout-options-->

			<div class="register-req">
				<p>Please use Register And Checkout to easily get access to your order history, or use Checkout as Guest</p>
			</div><!--/register-req-->
			
			</div>
			
			[@cms.area name="serpics-checkout" contextAttributes={"baseSite":ctx.baseSite}/]

			
			<div class="review-payment">
				<h2>Review</h2>
			</div>

			<div class="table-responsive cart_info" style=" overflow-x: hidden;">
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
									<a href="${ctx.contextPath}/${ctx.baseSite}/Product?productId={{product.id}}">{{item.sku}}</a>
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
							<td class="cart_delete"><a class="cart_quantity_delete"
								ng-click="deleteItem(item.id)"><i class="fa fa-times">  REMOVE</i></a></td>
						</tr>
						
												<tr>
							<td colspan="4">&nbsp;</td>
							<td colspan="2">
								<table class="table table-condensed total-result">
										<tr>
											<td>Cart Sub Total</td>
											<td><span>&euro;{{(cart.totalProduct | number:2) || 0}}</span></td>
										</tr>
										<tr>
											<td>Services Sub Total</td>
											<td><span>&euro;{{(cart.totalService | number:2) || 0}}</span></td>
										</tr>
										<tr>
											<td>Tax Sub Total</td>
											<td><span>&euro;{{(cart.totalTax | number:2) || 0}}</span></td>
										</tr>
										<tr>
											<td>Shipping Cost</td>
											<td><span>&euro;{{(cart.totalShipping | number:2) || 0}}</span></td>
										</tr>
										<tr>
											<td>Total</td>
											<td><span>&euro;{{(cart.orderAmount | number:2) || 0}}</span></td>
										</tr>										
									</table>
							</td>
						</tr>
						</tbody>
				</table>
			</div>
		
		</div>
	</section> <!--/#cart_items-->
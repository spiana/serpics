<div class="shopper-informations" ng-init="getShipmodeList()">
				<div class="row">
					<div class="col-sm-3">
						<div class="shopper-info">
							<p>Choose a Shipmode</p>
							<div style="width: 100%">
								<a href="${ctx.contextPath}/${ctx.baseSite}/CheckoutPaymethod" ><input ng-show="cart.shipmode" type="submit" id="submit" value="Continue" class="btn btn-primary"></a>
								<input ng-hide="cart.shipmode" type="submit" value="Continue" class="btn btn-primary" disabled>
								<a href="${ctx.contextPath}/${ctx.baseSite}/CheckoutPaymethod" ><input ng-hide="cart.shipmode" type="submit" id="submit" value="Continue without Shipmode" class="btn btn-primary"></a>
							</div>
						</div>
					</div>
					<div class="col-sm-8 clearfix">
						<div class="shopper-info">
							<p style="margin-bottom: 33px;">Shipmode List</p>
							<div class="total_area">
								<ul>
									
									<div ng-repeat="shipmode in shipmodeList">
										<a class="check_out" ng-click="addShipmode(shipmode.name)">{{shipmode.name}}</a>
										<li style="margin-bottom: 33px;"><i ng-class="{ 'fa fa-check fa-fw': cart.shipmode.name == shipmode.name, 'fa fa-fw' : cart.shipmode.name != shipmode.name}" ng-style="cart.shipmode && {'color':'red'}"></i>
										{{shipmode.description}}</li>
									</div>
									
								</ul>
							</div>
						</div>
					</div>
				</div>
</div>
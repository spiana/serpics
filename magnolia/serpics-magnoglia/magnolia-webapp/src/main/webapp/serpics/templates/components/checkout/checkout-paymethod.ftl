<div class="shopper-informations" ng-init="getPaymethodList()">
				<div class="row" >
					<div class="col-sm-3">
						<div class="shopper-info">
							<p>Choose a Payment Method</p>
							<div style="width: 100%" ng-model="cart" >
								<input ng-show="cart.paymethod" type="submit" id="submit" value="Pay" ng-click="createPayment()" class="btn btn-primary">
								<input ng-hide="cart.paymethod" type="submit" value="Create Payment" class="btn btn-primary" disabled>
								<a href="${ctx.contextPath}/${ctx.baseSite}/Complete" ><input ng-hide="cart.paymethod" type="submit" id="submit" value="Continue without Payment Method" href="${ctx.contextPath}/${ctx.baseSite}/Complete" class="btn btn-primary"></a>
							</div>
						</div>
					</div>
					<div class="col-sm-8 clearfix" >
						<div class="shopper-info">
							<p style="margin-bottom: 33px;">Payment Method List</p>
							<div class="total_area">
								<ul>
									
									<div ng-repeat="paymethod in paymethodList" >
										<a class="check_out" ng-click="addPaymethod(paymethod.name)">{{paymethod.name}} - {{cart.paymethod.name}}</a>
										<li style="margin-bottom: 33px;"><i ng-class="{ 'fa fa-check fa-fw': cart.paymethod.name == paymethod.name, 'fa fa-fw' : cart.paymethod.name != paymethod.name}" ng-style="cart.paymethod && {'color':'red'}"></i>
										{{paymethod.description}}</li>
									</div>
									
								</ul>
							</div>
						</div>
					</div>
				</div>
</div>
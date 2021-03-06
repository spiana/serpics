<section id="cart_items" ng-controller="customerController">
	<h2 class="title text-center">PERSONAL AREA</h2>
	<div  ng-show="currentUser.userType=='ANONYMOUS'">
		<div>Please <a href="${ctx.contextPath}/${ctx.baseSite}/Login">LOGIN</a> to acces to your Personal Area.</div>
	</div>
	<div  ng-if="currentUser.userType=='REGISTERED'" ng-init="getOrders(); getCountryList();">
		<div class="col-sm-12 clearfix" style="margin-bottom: 30px;">
						<div class="col-sm-4 clearfix">
							<div><h4>Your data:</h4></div>
							<input type="submit" value="Update personal data" class="btn btn-primary" ng-click="updateUserData(currentUser)">
						</div>
							<div class="col-sm-4 form-one clearfix" style="width: 33.33333333%;">
								<form>
									<input type="text" placeholder="First Name" name="firstname" ng-model="currentUser.firstname">
									<input type="text" placeholder="Last Name"name="lastname" ng-model="currentUser.lastname">
								</form>
							</div>
							<div class="col-sm-4 form-one clearfix" style="width: 33.33333333%;">
								<form>
									<input type="text" placeholder="E-Mail" name="email" ng-model="currentUser.email" >
									<input type="text" placeholder="Phone" name="phone" ng-model="currentUser.phone" >
								</form>
							</div>
		</div>
		<div class="col-sm-12 clearfix" style="margin-bottom: 30px;">
						<div class="col-sm-4 clearfix">
							<div><h4>Your contact address:</h4></div>
							<input ng-hide="modify != 'contact'" type="submit" value="Update contact address" class="btn btn-primary" ng-click="updateContactAddress(currentUser.contactAddress); modify = ''">
							<input ng-show="modify != 'contact'" type="submit" value="Modify contact address" class="btn btn-primary" ng-click="getDistrictByCountry(currentUser.contactAddress.country.id); modify = 'contact'">
						</div>
							<div class="col-sm-4 form-one clearfix" style="width: 33.33333333%;">
								<form>	
									<input ng-disabled="modify != 'contact'" type="text" name="company" placeholder="Company" ng-model="currentUser.contactAddress.company" >
									<input ng-disabled="modify != 'contact'" type="text" name="firstname" placeholder="First Name" ng-model="currentUser.contactAddress.firstname" >
									<input ng-disabled="modify != 'contact'" type="text" name="lastname" placeholder="Last Name" ng-model="currentUser.contactAddress.lastname" >
									<input ng-disabled="modify != 'contact'" type="text" name="address1" placeholder="Address" ng-model="currentUser.contactAddress.address1" >
									<input ng-disabled="modify != 'contact'" type="text" name="address2" placeholder="Address 2" ng-model="currentUser.contactAddress.address2" >
									<input ng-disabled="modify != 'contact'" type="text" name="streetNumber" placeholder="Street Number" ng-model="currentUser.contactAddress.streetNumber" >
									<input ng-disabled="modify != 'contact'" type="text" name="city" placeholder="City" ng-model="currentUser.contactAddress.city" >
								</form>
							</div>
							<div class="col-sm-4 form-two clearfix" style="margin-left: 0%;width: 33.33333333%;" >
								<form>
									<select ng-disabled="modify != 'contact'" ng-options="country as country.description for country in countries track by country.iso3Code" ng-model="currentUser.contactAddress.country" ng-change="getDistrictByCountry(currentUser.contactAddress.country.id)">
										<option value ="" disabled>-- Country --</option>
									</select>
<!-- 									<select ng-show="modify != 'contact'" ng-disabled="modify != 'contact'" ng-options="district as district.description for district in districts track by district.description" ng-model="currentUser.contactAddress.district" > -->
<!-- 										<option value = "" >{{currentUser.contactAddress.district.description || '-- District --'}}</option> -->
<!-- 									</select> -->
<!-- 									<select ng-show="modify === 'contact'" ng-options="district as district.description for district in districts track by district.description" ng-model="currentUser.contactAddress.district" > -->
<!-- 										<option value = "" >-- District --</option> -->
<!-- 									</select> -->
									<select ng-show="modify != 'contact'" ng-disabled="modify != 'contact'" >
										<option value = "" >{{currentUser.contactAddress.district.description || '-- District --'}}</option>
									</select>
									<select ng-show="modify === 'contact'" ng-options="district as district.description for district in districts track by district.description" ng-model="currentUser.contactAddress.district" >
										<option value = "" >-- District --</option>
									</select>
									<input ng-disabled="modify != 'contact'" type="text" name="zipcode" placeholder="Zip / Postal Code" ng-model="currentUser.contactAddress.zipcode" >
									<input ng-disabled="modify != 'contact'" type="text" name="email" placeholder="Email" ng-model="currentUser.contactAddress.email" >
									<input ng-disabled="modify != 'contact'" type="text" name="phone" placeholder="Phone" ng-model="currentUser.contactAddress.phone" >
									<input ng-disabled="modify != 'contact'" type="text" name="mobile" placeholder="Mobile" ng-model="currentUser.contactAddress.mobile" >
									<input ng-disabled="modify != 'contact'" type="text" name="fax" placeholder="Fax" ng-model="currentUser.contactAddress.fax" >
								</form>
							</div>
		</div>
		<div class="col-sm-12 clearfix" style="margin-bottom: 30px;" >
						<div class="col-sm-4 clearfix">
							<div><h4>Your bill address:</h4></div>
							<input ng-hide="modify != 'billing'" type="submit" value="Update billing address" class="btn btn-primary" ng-click="updateBillingAddress(currentUser.billingAddress); modify = ''">
							<input ng-show="modify != 'billing'" type="submit" value="Modify billing address" class="btn btn-primary" ng-click="getDistrictByCountry(currentUser.billingAddress.country.id); modify = 'billing'">
						</div>
							<div class="col-sm-4 form-one clearfix" style="width: 33.33333333%;">
								<form>	
									<input ng-disabled="modify != 'billing'" type="text" name="company" placeholder="Company" ng-model="currentUser.billingAddress.company" >
									<input ng-disabled="modify != 'billing'" type="text" name="firstname" placeholder="First Name" ng-model="currentUser.billingAddress.firstname" >
									<input ng-disabled="modify != 'billing'" type="text" name="lastname" placeholder="Last Name" ng-model="currentUser.billingAddress.lastname" >
									<input ng-disabled="modify != 'billing'" type="text" name="address1" placeholder="Address" ng-model="currentUser.billingAddress.address1" >
									<input ng-disabled="modify != 'billing'" type="text" name="address2" placeholder="Address 2" ng-model="currentUser.billingAddress.address2" >
									<input ng-disabled="modify != 'billing'" type="text" name="streetNumber" placeholder="Street Number" ng-model="currentUser.billingAddress.streetNumber" >
									<input ng-disabled="modify != 'billing'" type="text" name="city" placeholder="City" ng-model="currentUser.billingAddress.city" >
								</form>
							</div>
							<div class="col-sm-4 form-two clearfix" style="margin-left: 0%;width: 33.33333333%;" >
								<form>
									<select ng-disabled="modify != 'billing'" ng-options="country as country.description for country in countries track by country.description" ng-model="currentUser.billingAddress.country" ng-change="getDistrictByCountry(currentUser.billingAddress.country.id)">
										<option value ="" disabled>-- Country --</option>
									</select>
									<select ng-show="modify != 'billing'" ng-disabled="modify != 'contact'" >
										<option value = "" >{{currentUser.billingAddress.district.description || '-- District --'}}</option>
									</select>
									<select ng-show="modify === 'billing'" ng-options="district as district.description for district in districts track by district.description" ng-model="currentUser.billingAddress.district" >
										<option value = "" >-- District --</option>
									</select>
									<input ng-disabled="modify != 'billing'" type="text" name="zipcode" placeholder="Zip / Postal Code" ng-model="currentUser.billingAddress.zipcode" >
									<input ng-disabled="modify != 'billing'" type="text" name="email" placeholder="Email" ng-model="currentUser.billingAddress.email" >
									<input ng-disabled="modify != 'billing'" type="text" name="phone" placeholder="Phone" ng-model="currentUser.billingAddress.phone" >
									<input ng-disabled="modify != 'billing'" type="text" name="mobile" placeholder="Mobile" ng-model="currentUser.billingAddress.mobile" >
									<input ng-disabled="modify != 'billing'" type="text" name="fax" placeholder="Fax" ng-model="currentUser.billingAddress.fax" >
								</form>
							</div>
		</div>
		<div class="col-sm-12 clearfix" style="margin-bottom: 30px;" ng-init="$scope.shippingAddress = -1">
						<div><h4>Your ship address:</h4></div>
						<div style="margin-bottom: 59px;margin-top: 12px;" ng-click="modify = ''">
							<div class="col-sm-4 clearfix" >
								<input type="submit" value="New shipping address" class="btn btn-default btn-sm" ng-click="$scope.shippingAddress = -1" ng-class="{ success :$scope.shippingAddress === -1 }">
							</div>
							<div class="col-sm-8 clearfix">
								<input type="submit" value="Address {{$index + 1}}" class="btn btn-default btn-sm" ng-click="$scope.shippingAddress = $index" ng-repeat="address in currentUser.destinationAddress" ng-class="{ success :$scope.shippingAddress != -1 }">
							</div>
						</div>
						<div>
							<div class="col-sm-4 clearfix" ng-show="$scope.shippingAddress === -1">
								<input ng-hide="modify != 'shipping'" type="submit" value="Add new shipping address" class="btn btn-primary" ng-click="addDestinationAddress(currentUser.destinationAddress[$scope.shippingAddress]); modify = ''">
								<input ng-show="modify != 'shipping'" type="submit" value="Modify shipping address" class="btn btn-primary" ng-click="getDistrictByCountry(currentUser.destinationAddress[$scope.shippingAddress].country.id); modify = 'shipping'">							
							</div>
							<div class="col-sm-4 clearfix" ng-hide="$scope.shippingAddress === -1">
								<input ng-hide="modify != 'shipping'" type="submit" value="Update shipping address" class="btn btn-primary" ng-click="updateDestinationAddress(currentUser.destinationAddress[$scope.shippingAddress]); modify = ''">
								<input ng-show="modify != 'shipping'" type="submit" value="Modify shipping address" class="btn btn-primary" ng-click="getDistrictByCountry(currentUser.destinationAddress[$scope.shippingAddress].country.id); modify = 'shipping'">
								<input type="submit" value="Remove address" class="btn btn-primary" ng-click="deleteDestinationAddress(currentUser.destinationAddress[$scope.shippingAddress].id); $scope.shippingAddress = -1; modify = ''">
							</div>
								<div class="col-sm-4 form-one clearfix" style="width: 33.33333333%;">
									<form>
										<input ng-disabled="modify != 'shipping'" type="text" name="company" placeholder="Company" ng-model="currentUser.destinationAddress[$scope.shippingAddress].company" >
										<input ng-disabled="modify != 'shipping'" type="text" name="firstname" placeholder="First Name" ng-model="currentUser.destinationAddress[$scope.shippingAddress].firstname" >
										<input ng-disabled="modify != 'shipping'" type="text" name="lastname" placeholder="Last Name" ng-model="currentUser.destinationAddress[$scope.shippingAddress].lastname" >
										<input ng-disabled="modify != 'shipping'" type="text" name="address1" placeholder="Address" ng-model="currentUser.destinationAddress[$scope.shippingAddress].address1" >
										<input ng-disabled="modify != 'shipping'" type="text" name="address2" placeholder="Address 2" ng-model="currentUser.destinationAddress[$scope.shippingAddress].address2" >
										<input ng-disabled="modify != 'shipping'" type="text" name="streetNumber" placeholder="Street Number" ng-model="currentUser.destinationAddress[$scope.shippingAddress].streetNumber" >
										<input ng-disabled="modify != 'shipping'" type="text" name="city" placeholder="City" ng-model="currentUser.destinationAddress[$scope.shippingAddress].city" >
									</form>
								</div>
							<div class="col-sm-4 form-two clearfix" style="margin-left: 0%;width: 33.33333333%;" >
									<form>
									<select ng-disabled="modify != 'shipping'" ng-options="country as country.description for country in countries track by country.description" ng-model="currentUser.destinationAddress[$scope.shippingAddress].country" ng-change="getDistrictByCountry(currentUser.destinationAddress[$scope.shippingAddress].country.id)">
										<option value ="" disabled>-- Country --</option>
									</select>
									<select ng-show="modify != 'shipping'" ng-disabled="modify != 'contact'" >
										<option value = "" >{{currentUser.destinationAddress[$scope.shippingAddress].district.description || '-- District --'}}</option>
									</select>
									<select ng-show="modify === 'shipping'" ng-options="district as district.description for district in districts track by district.description" ng-model="currentUser.destinationAddress[$scope.shippingAddress].district" >
										<option value = "" >-- District --</option>
									</select>
										<input ng-disabled="modify != 'shipping'" type="text" name="zipcode" placeholder="Zip / Postal Code" ng-model="currentUser.destinationAddress[$scope.shippingAddress].zipcode" >
										<input ng-disabled="modify != 'shipping'" type="text" name="email" placeholder="Email" ng-model="currentUser.destinationAddress[$scope.shippingAddress].email" >
										<input ng-disabled="modify != 'shipping'" type="text" name="phone" placeholder="Phone" ng-model="currentUser.destinationAddress[$scope.shippingAddress].phone" >
										<input ng-disabled="modify != 'shipping'" type="text" name="mobile" placeholder="Mobile" ng-model="currentUser.destinationAddress[$scope.shippingAddress].mobile" >
										<input ng-disabled="modify != 'shipping'" type="text" name="fax" placeholder="Fax" ng-model="currentUser.destinationAddress[$scope.shippingAddress].fax" >
									</form>
								</div>
						</div>
		</div>
		<div><h4>Your orders:</h4></div>
		<div class="table-responsive cart_info" style="width: 50%; overflow-x: hidden;">
					<table class="table table-condensed">
						<thead>
							<tr class="cart_menu">
								<td class="description">Order number</td>
								<td class="price">Price</td>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="order in orders.content">
								<td class="cart_description">
									<h4>
										<p>{{order.id}}</p>
									</h4>
								</td>
								<td class="cart_price">
									<p>&euro;{{(order.totalProduct | number:2) || 0}}</p>
								</td>
							</tr>
	
						</tbody>
					</table>
		</div>		
	</div>
</section>
<div class="shopper-informations" ng-init="getCountryList()">
				<div class="row">
					<div class="col-sm-3">
						<div class="shopper-info">
							<label style="width: 100%"><input type="checkbox" ng-model="shippingToBill" ng-init="shippingToBill=true" ng-checked="true"> Shipping to bill address</label>
							<div style="width: 100%">
								<input ng-show="currentUser.billingAddress.firstname.length &&
								currentUser.billingAddress.lastname.length &&
								currentUser.billingAddress.address1.length &&
								currentUser.billingAddress.streetNumber.length &&
								currentUser.billingAddress.city.length &&
								currentUser.billingAddress.zipcode.length &&
								currentUser.billingAddress.email.length" type="submit" id="submit" value="Continue" ng-click="submitBillingForm(currentUser.billingAddress,shippingToBill)" class="btn btn-primary">
								<input ng-hide="currentUser.billingAddress.firstname.length &&
								currentUser.billingAddress.lastname.length &&
								currentUser.billingAddress.address1.length &&
								currentUser.billingAddress.streetNumber.length &&
								currentUser.billingAddress.city.length &&
								currentUser.billingAddress.zipcode.length &&
								currentUser.billingAddress.email.length" type="submit" value="Continue" class="btn btn-primary" disabled>
							</div>
						</div>
						<br>
						<div style="width: 100%" >* Required field</div>
					</div>
					<div class="col-sm-5 clearfix">
						<div class="bill-to">
							<p>Bill To</p>
							<div class="form-one">
								<form>
									<input type="text" name="company" placeholder="Company Name" ng-model="currentUser.billingAddress.company">
									<input type="text" name="firstname" placeholder="First Name *" ng-model="currentUser.billingAddress.firstname" required>
									<input type="text" name="lastname" placeholder="Last Name *" ng-model="currentUser.billingAddress.lastname" required>
									<input type="text" name="address1" placeholder="Address *" ng-model="currentUser.billingAddress.address1" required>
									<input type="text" name="address2" placeholder="Address 2" ng-model="currentUser.billingAddress.address2">
									<input type="text" name="streetNumber" placeholder="Street Number *" ng-model="currentUser.billingAddress.streetNumber" required>
									<input type="text" name="city" placeholder="City *" ng-model="currentUser.billingAddress.city" required>
								</form>
							</div>
							<div class="form-two">
								<form>
								<div ng-if="currentUser.userType == 'REGISTERED'" ng-init="getDistrictByCountry(currentUser.billingAddress.country.id)"></div>
									<select ng-options="country as country.description for country in countries track by country.description" ng-model="currentUser.billingAddress.country" ng-change="getDistrictByCountry(currentUser.billingAddress.country.id)">
										<option value ="" disabled>-- Country --</option>
									</select>
									<select ng-options="district as district.description for district in districts track by district.description" ng-model="currentUser.billingAddress.district" >
										<option value = "" >-- District --</option>
									</select>
									<input type="text" name="zipcode" placeholder="Zip / Postal Code *" ng-model="currentUser.billingAddress.zipcode" required>
									<input type="text" name="email" placeholder="Email *" ng-model="currentUser.billingAddress.email" required>
									<input type="text" name="phone" placeholder="Phone" ng-model="currentUser.billingAddress.phone" required>
									<input type="text" name="mobile" placeholder="Mobile Phone" ng-model="currentUser.billingAddress.mobile">
									<input type="text" name="fax" placeholder="Fax" ng-model="currentUser.billingAddress.fax">
								</form>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="order-message">
							<p>Shipping Order</p>
							<textarea name="message" name="field1" placeholder="Notes about your order, Special Notes for Delivery" ng-model="currentUser.billingAddress.field1" rows="16"></textarea>
						</div>	
					</div>					
				</div>
			</div>
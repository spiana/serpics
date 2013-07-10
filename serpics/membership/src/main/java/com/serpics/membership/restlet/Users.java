package com.serpics.membership.restlet;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.serpics.core.datatype.UserType;
import com.serpics.core.restlet.AbstractServerResource;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.persistence.AbstractAddress;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.MembershipService;
import com.serpics.util.gson.ExclusionStrategy;

public class Users extends AbstractServerResource {
	@Autowired(required = true)
	MembershipService membershipService;

	private String makejson(Object users) {
		Gson gson = new GsonBuilder()
				.excludeFieldsWithModifiers(Modifier.STATIC,
						Modifier.TRANSIENT, Modifier.VOLATILE)
				.setExclusionStrategies(new ExclusionStrategy())
				.serializeNulls().create();

		return gson.toJson(users);
	}
	

	@Get("html")
	public Representation rapresentHtml() {
		java.util.List<User> lista = new ArrayList<User>(0);

		return new StringRepresentation(makejson(lista));
	}

	@Post
	public Representation registerUser(Representation entity) {

		// Parse the given representation and retrieve pairs of
		// "name=value" tokens.
		
		Gson g = new Gson();
		try {
			User u = g.fromJson(entity.getReader(), User.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Form form = new Form(entity);
		String sessionId = form.getFirstValue("sid");
		CommerceSessionContext ctx = getSessionContext(sessionId);
		//TODO: if context null Error no connection open to commerce engine
		
		String firstName = form.getFirstValue("firstname");
		String lastName = form.getFirstValue("lastname");
		String email = form.getFirstValue("email");
		Long storeId = ctx.getUserPrincipal().getStoreId();

		// Address Info

		User user = new User();
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setEmail(email);
		user.setUserType(UserType.GUEST);
		user.setCreated(new Date());
		
		//user.getPermanentAddresses();
		user = membershipService.createUser(user);
		PermanentAddress address = makeAddressFromRequest(form);
	//	membershipService.addUserAddress(user, address);
		
		// Reload user to refresh address data
		//user = membershipService.fetchUser(user.getUserId());
		
		return new JsonRepresentation(makejson(user));
	}

	protected PermanentAddress makeAddressFromRequest(Form form) {

		String nickname  = form.getFirstValue("nickname") ;
		String firstname = form.getFirstValue("firstname");
		String lastname  = form.getFirstValue("lastname");
		String company  = form.getFirstValue("company");
		String address1 = form.getFirstValue("address1");
		String address2 = form.getFirstValue("address2");
		String address3 = form.getFirstValue("address3");
		String email =   form.getFirstValue("email");
		
		String zipcode = form.getFirstValue("zipcode");
		String city =  form.getFirstValue("city");
		String region = form.getFirstValue("region");
		String country  = form.getFirstValue("country");
		
		
		String phone  = form.getFirstValue("phone");
		String mobile  = form.getFirstValue("mobile");
		String fax  = form.getFirstValue("fax");
		
		String field1 = form.getFirstValue("field1");
		String field2 = form.getFirstValue("field2");
		BigDecimal field3 = new BigDecimal(form.getFirstValue("field3" , "0")) ;
		BigInteger field4 =  new BigInteger(form.getFirstValue("field3", "0")) ;
		
		
		String flag = "P" ;

		
		String vatcode = form.getFirstValue("vatcode");
		
		PermanentAddress a = new PermanentAddress(nickname, firstname, lastname, company, email, address1, address2, address3, zipcode, city, region, country, vatcode);
		a.setField1(field1);
		a.setField2(field2);
		a.setField3(field3);
		a.setField4(field4);
		a.setIsprimary(1);
		a.setPhone(phone);
		a.setMobile(mobile);
		a.setFax(fax);

		return a;
	}

}

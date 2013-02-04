import static org.junit.Assert.assertNotNull;

import com.serpics.base.services.BaseService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.CommerceEngineFactory;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.MembershipService;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		CommerceEngineFactory.init("config/applicationContext.xml");
		CommerceEngine ce = CommerceEngineFactory.getCommerceEngine();
		 MembershipService m = CommerceEngineFactory.getCurrentApplicationContext().getBean(MembershipService.class);
		BaseService b = CommerceEngineFactory.getCurrentApplicationContext().getBean(BaseService.class);
		
 b.initIstance();
		 
		 CommerceSessionContext context = ce.connect("default-store");
	
		 context = ce.bind(context.getSessionId());
		
		 /*
		 context = ce.connect("default-store", "superuser", "admin".toCharArray());
		 assertNotNull("not connect with context !", context);
		 
		 context = ce.connect(context, "superuser", "admin".toCharArray());
		 assertNotNull("not connect with context !", context);
		 */
		 User u = new User();
		 u.setLastname("test1");
		 UsersReg ur = new UsersReg();
		 ur.setLogonid("test1");
		 ur.setPassword("password");
		 PermanentAddress a = new PermanentAddress();
		 m.registerUser( u, ur, a);
		 

		 
		System.exit(0);
		}catch(SerpicsException e){
			e.printStackTrace();
		}
	
	}

}

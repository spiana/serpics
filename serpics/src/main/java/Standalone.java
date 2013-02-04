import com.serpics.base.services.BaseService;
import com.serpics.core.CommerceEngineFactory;
import com.serpics.membership.services.MembershipService;


public class Standalone {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommerceEngineFactory.init("config/applicationContext.xml");
		BaseService m = (BaseService)CommerceEngineFactory.getCommerceEngine().
				getApplicationContext().getBean("baseService");
		//if (!m.isInitialized())
			m.initIstance();	
			
	}

}

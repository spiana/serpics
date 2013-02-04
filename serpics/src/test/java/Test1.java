

import com.serpics.base.services.BaseService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.CommerceEngineFactory;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.services.MembershipService;

public class Test1 {

	
	class t1 extends Thread{
		@Override
		public void run() {
			CommerceEngine c = CommerceEngineFactory.getCommerceEngine();
			try {
				 c.connect("default-store");
				 CommerceSessionContext s = c.getCurrentContext();
				 System.out.println(s.getSessionId());
			} catch (SerpicsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	public void launch(){
		for(int i = 0 ;i<10 ;i++){
			t1 t = new t1();
			t.start();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommerceEngineFactory.init("config/applicationContext.xml");
		BaseService b = CommerceEngineFactory.getCurrentApplicationContext().getBean(BaseService.class);
		 b.initIstance();
	//	MembershipService m = (MembershipService)CommerceEngineFactory.getCommerceEngine().getApplicationContext().getBean("memberShip");
		 Test1 test = new Test1();
		test.launch();
	}

}

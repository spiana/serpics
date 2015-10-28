package com.serpics.jax.rs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;

//@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
//@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
//@TransactionConfiguration(defaultRollback = true)
//@RunWith(SpringJUnit4ClassRunner.class)
//@DirtiesContext
public class MembershipRestTest extends Assert {
	private final static String ENDPOINT_ADDRESS = "http://localhost:8080/rest";
	private final static String WADL_ADDRESS = ENDPOINT_ADDRESS + "?_wadl";
	private static Server server;

	@BeforeClass
	public static void initialize() throws Exception {
		startServer();
		waitForWADL();
	}

	private static void startServer() throws Exception {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(UserRestService.class);

		List<Object> providers = new ArrayList<Object>();
		// providers.add(new JacksonJsonProvider());
		// add custom providers if any
		sf.setProviders(providers);
		sf.setResourceProvider(UserRestService.class,
				new SingletonResourceProvider(new UserRestServiceImpl(), true));
		sf.setAddress(ENDPOINT_ADDRESS);

		server = sf.create();
		// Thread.currentThread().sleep(1000000000);
	}

	// Optional step - may be needed to ensure that by the time individual
	// tests start running the endpoint has been fully initialized
	private static void waitForWADL() throws Exception {
		WebClient client = WebClient.create(WADL_ADDRESS);
		// wait for 20 secs or so
		for (int i = 0; i < 20; i++) {
//			Thread.currentThread().sleep(1000);
			TimeUnit.SECONDS.sleep(1);
			Response response = client.get();
			if (response.getStatus() == 200) {
				System.out.print(response.getEntity().toString());
				break;
			}
		}
		// no WADL is available yet - throw an exception or give tests a chance
		// to run anyway
	}

	@Resource
	BaseService baseService;

	@AfterClass
	public static void destroy() throws Exception {
		server.stop();
		server.destroy();
	}

	@Before
	public void beforeTest() {
		// baseService.initIstance();
	}

	@Test
	public void prova1() throws SerpicsException {
		List<Object> providers = new ArrayList<Object>();
		// providers.add(new JacksonJsonProvider());
		UserRestService client = JAXRSClientFactory.create(ENDPOINT_ADDRESS,
				UserRestService.class, providers);

//		Page<User> ul = client.findAll(null);
	//	Assert.assertEquals(3, ul.getContent().size());
	}

	// @Test
	// public void prova() throws IOException, MessageProcessingException,
	// IllegalStateException, SerpicsException {
	// UserRestService client = JAXRSClientFactory.create(ENDPOINT_ADDRESS,
	// UserRestService.class);
	// org.apache.cxf.jaxrs.client.Client c = WebClient.client(client).accept(
	// MediaType.APPLICATION_JSON);
	// WebClient wc = WebClient.fromClient(c);
	// wc.path("/userService").path("/one");
	//
	// // UsersList r = client.getUsers();
	// // User u = client.getUser();
	// // Assert.assertEquals("uno", u.getLastname());
	// Response r = wc.get();
	// MappingJsonFactory factory = new MappingJsonFactory();
	// JsonParser parser = factory.createJsonParser((InputStream) r
	// .getEntity());
	// User output = parser.readValueAs(User.class);
	// assertEquals("uno", output.getFirstname());
	// }
	//
	// @Test
	// public void prova2() throws IOException, MessageProcessingException,
	// IllegalStateException, SerpicsException {
	// List<Object> providers = new ArrayList<Object>();
	// providers.add(new JacksonJsonProvider());
	// UserRestService client = JAXRSClientFactory.create(ENDPOINT_ADDRESS,
	// UserRestService.class , providers);
	// // org.apache.cxf.jaxrs.client.Client c =
	// // WebClient.client(client).accept(MediaType.APPLICATION_JSON);
	// User u = client.findOne(1L);
	// Assert.assertNotNull(u);
	//
	// }
}

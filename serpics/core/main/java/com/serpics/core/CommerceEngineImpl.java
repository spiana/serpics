package com.serpics.core;

import java.util.Date;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.serpics.core.scope.CommerceScopeContextHolder;
import com.serpics.core.security.StoreRealm;
import com.serpics.core.security.UserPrincipal;
import com.serpics.core.service.Membership;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.core.session.SessionContext;
import com.serpics.core.session.SessionManager;

public class CommerceEngineImpl implements CommerceEngine {

	private static final ThreadLocal<SessionContext> threadLocal = new ThreadLocal<SessionContext>();

	@Autowired(required = true)
	SessionManager sessionManager;

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	@Autowired(required = true)
	Membership membershipService;

	public Membership getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(Membership membershipService) {
		this.membershipService = membershipService;
	}

	private BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		this.beanFactory = arg0;

	}

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

	@Override
	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	@Override
	public CommerceSessionContext connect(String storeUUID) throws SerpicsException {
		StoreRealm s = membershipService.fetchStoreByUUID(storeUUID);
		SessionContext context = getSessionManager().createSessionContext(s);
		UserPrincipal user = membershipService.createAnonymous();
		context.setUserPrincipal(user);
		context.setLastAccess(new Date());
		threadLocal.set(context);
		CommerceScopeContextHolder.setThreadScopeAttributes(context.getCommerceScopeAttribute());
		return (CommerceSessionContext) context;
	}

	@Override
	public CommerceSessionContext connect(String storeUUID, String loginId, char[] password) throws SerpicsException {
		StoreRealm s = membershipService.fetchStoreByUUID(storeUUID);
		SessionContext context = getSessionManager().createSessionContext(s);
		context.setLastAccess(new Date());
		threadLocal.set(context);
		CommerceScopeContextHolder.setThreadScopeAttributes(context.getCommerceScopeAttribute());
		UserPrincipal user = membershipService.login(loginId, password);
		context.setUserPrincipal(user);

		return (CommerceSessionContext) context;
	}

	@Override
	public CommerceSessionContext connect(CommerceSessionContext context, String loginId, char[] password)
			throws SerpicsException {
		context.setLastAccess(new Date());
		threadLocal.set(context);
		CommerceScopeContextHolder.setThreadScopeAttributes(context.getCommerceScopeAttribute());
		UserPrincipal user = membershipService.login(loginId, password);
		context.setUserPrincipal(user);
		return context;
	}

	@Override
	public CommerceSessionContext bind(String sessionId) {
		SessionContext _s = this.sessionManager.getSessionContext(sessionId);
		_s.setLastAccess(new Date());
		threadLocal.set(_s);
		CommerceScopeContextHolder.setThreadScopeAttributes(_s.getCommerceScopeAttribute());
		return (CommerceSessionContext) _s;
	}

	@Override
	public Object getService(String serviceName) throws BeansException {
		return this.beanFactory.getBean(serviceName);
	}

	@Override
	public CommerceSessionContext getCurrentContext() {
		return (CommerceSessionContext) threadLocal.get();
	}

	@Override
	public void setCurrentContext(CommerceSessionContext context) {
		threadLocal.set(context);

	}

}

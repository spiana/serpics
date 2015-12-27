package com.serpics.core;

import java.security.Principal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import com.serpics.core.scope.SessionScopeContextHolder;
import com.serpics.core.scope.StoreScopeContextHolder;
import com.serpics.core.security.StoreRealm;
import com.serpics.core.security.UserDetail;
import com.serpics.core.service.Membership;
import com.serpics.core.session.SessionContext;
import com.serpics.core.session.SessionManager;


public abstract class AbstractEngine<T extends SessionContext> implements Engine<T> , InitializingBean{

    Logger logger = LoggerFactory.getLogger(AbstractEngine.class);

    private transient static final ThreadLocal<SessionContext> threadLocal = new ThreadLocal<SessionContext>();

    private Membership membershipService;
    
    private boolean reconnectEnable = true;
    
    @Resource
    SessionManager sessionManager;

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(final SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    private transient BeanFactory beanFactory;

    @Override
    public void setBeanFactory(final BeanFactory arg0) throws BeansException {
        this.beanFactory = arg0;

    }

    private transient ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext arg0) throws BeansException {
        this.applicationContext = arg0;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    @SuppressWarnings("unchecked")
	protected T doConnection(String storeName , String sessionId) throws SerpicsException{
    	final StoreRealm s = membershipService.fetchStoreByName(storeName);
        Assert.notNull(s , "invalid store ! connection rejected !");
        final SessionContext context = getSessionManager().createSessionContext(s , sessionId);
    	return (T) context;
    }
    @SuppressWarnings("unchecked")
	@Override
    public T connect(final String storeName) throws SerpicsException {
       
    	final SessionContext context = doConnection(storeName, null);
        final UserDetail user = membershipService.createAnonymous();
        context.setUserPrincipal(user);
        context.setLastAccess(new Date());
     
        bind(context);
        return (T) context;
    }

    protected T re_connect(final String storeName , String sessionId) throws SerpicsException {
    	final SessionContext context = doConnection(storeName, sessionId);
        final UserDetail user = membershipService.createAnonymous();
        context.setUserPrincipal(user);
        context.setLastAccess(new Date());
     
        bind(context);
        return (T) context;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public T connect(final String storeName, final String loginId, final char[] password) throws SerpicsException {
    	final SessionContext context = doConnection(storeName, null);
        final UserDetail user = membershipService.login(loginId, password);
        context.setUserPrincipal(user);
        bind(context);
        return (T) context;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T connect(final String storeName, final Principal principal) throws SerpicsException {
    	final SessionContext context = doConnection(storeName, null);
        final UserDetail user = membershipService.connect(principal);
        context.setUserPrincipal(user);
        bind(context);
        return (T) context;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T connect(final SessionContext context, final String loginId, final char[] password)
            throws SerpicsException {
        context.setLastAccess(new Date());
        final UserDetail user = membershipService.login(loginId, password);
        context.setUserPrincipal(user);
        bind(context);
        return (T) context;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T bind(final String sessionId) {
        final SessionContext _s = this.sessionManager.getSessionContext(sessionId);

        if (_s != null) {
            bind(_s);
            return (T) _s;
        } else {
            logger.warn("session id [{}] is expired !");
            if(reconnectEnable){
	            String[] tokens = sessionId.split("-");
	            if (tokens.length != 6){
	            	throw new RuntimeException("illegal sessionId format !");
	            }
	            String storeName = new String(Base64.decodeBase64(tokens[0]));
	            logger.info("try to reconnect to store {}" , storeName);
	            try {
					return re_connect(storeName , sessionId);
				} catch (SerpicsException e) {
					logger.error("could not connect to store {}" , storeName);
					
				}
            }
        }
            return null;
    }

    private void bind(final SessionContext context) {
        threadLocal.set(context);
        StoreScopeContextHolder.setCurrentStoreRealm(context.getRealm());
        SessionScopeContextHolder.setSessionScopeAttributes(context.getCommerceScopeAttribute());
        return;
    }

    @Override
    public Object getService(final String serviceName) throws BeansException {
        return this.beanFactory.getBean(serviceName);
    }

    
	@SuppressWarnings("unchecked")
	@Override
    public T getCurrentContext() {
		T context = (T) threadLocal.get();
		Assert.notNull(context ,"Session Context not initialized !");
        return context;
    }


    @Override
    public void disconnect(final String sessionId) {
        if (sessionId != null){
        	unbind();
            this.sessionManager.removeSessionContext(sessionId);
    }}

    @Override
    public void disconnect(final SessionContext sessionContext) {
        if (sessionContext != null)
            disconnect(sessionContext.getSessionId());
    }

    @Override
    public void unbind() {
    	SessionContext context = threadLocal.get();
    	if (context != null)
    		this.sessionManager.putSessionContext(context.getSessionId(), context);
        threadLocal.remove();
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
    	this.membershipService = (Membership) beanFactory.getBean("memberService");
    }
}

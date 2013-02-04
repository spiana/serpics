package com.serpics.core.scope;

public class CommerceScopeContextHolder {

	 private static final ThreadLocal<CommerceScopeAttributes> threadScopeAttributesHolder = 
				new InheritableThreadLocal<CommerceScopeAttributes>() {
			        protected CommerceScopeAttributes initialValue() {
			            return new CommerceScopeAttributes();
			        }
			    };

			    /**
			     * Gets <code>ThreadScopeAttributes</code>.
			     */
			    public static CommerceScopeAttributes getThreadScopeAttributes() {
			        return threadScopeAttributesHolder.get();
			    }

			    /**
			     * Sets <code>ThreadScopeAttributes</code>.
			     */
			    public static void setThreadScopeAttributes(CommerceScopeAttributes accessor) {
			        CommerceScopeContextHolder.threadScopeAttributesHolder.set(accessor);
			    }

			    /**
			     * Gets current <code>ThreadScopeAttributes</code>.
			     */
			    public static CommerceScopeAttributes currentThreadScopeAttributes() throws IllegalStateException {
			        CommerceScopeAttributes accessor = threadScopeAttributesHolder.get();
			        
			        if (accessor == null) {
			            throw new IllegalStateException("No thread scoped attributes.");
			        }
			        
			        return accessor;
			    }


}

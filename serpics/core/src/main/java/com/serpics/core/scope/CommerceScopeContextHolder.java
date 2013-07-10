package com.serpics.core.scope;

public class CommerceScopeContextHolder {

	 private static final ThreadLocal<CommerceScopeAttributes> commerceScopeAttributesHolder = 
				new InheritableThreadLocal<CommerceScopeAttributes>() {
			        protected CommerceScopeAttributes initialValue() {
			            return new CommerceScopeAttributes();
			        }
			    };

			    /**
			     * Gets <code>ThreadScopeAttributes</code>.
			     */
			    public static CommerceScopeAttributes getThreadScopeAttributes() {
			        return commerceScopeAttributesHolder.get();
			    }

			    /**
			     * Sets <code>ThreadScopeAttributes</code>.
			     */
			    public static void setThreadScopeAttributes(CommerceScopeAttributes accessor) {
			        CommerceScopeContextHolder.commerceScopeAttributesHolder.set(accessor);
			    }

			    /**
			     * Gets current <code>ThreadScopeAttributes</code>.
			     */
			    public static CommerceScopeAttributes currentThreadScopeAttributes() throws IllegalStateException {
			        CommerceScopeAttributes accessor = commerceScopeAttributesHolder.get();
			        
			        if (accessor == null) {
			            throw new IllegalStateException("No thread scoped attributes.");
			        }
			        
			        return accessor;
			    }


}

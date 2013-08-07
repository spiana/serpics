package com.serpics.core.scope;

public class SessionScopeContextHolder {

	private static final ThreadLocal<SessionScopeAttributes> commerceScopeAttributesHolder = new InheritableThreadLocal<SessionScopeAttributes>() {
		@Override
		protected SessionScopeAttributes initialValue() {
			return new SessionScopeAttributes();
		}
	};

	/**
	 * Gets <code>SessionScopeAttributes</code>.
	 */
	public static SessionScopeAttributes getSessionScopeAttributes() {
		return commerceScopeAttributesHolder.get();
	}

	/**
	 * Sets <code>SessionScopeAttributes</code>.
	 */
	public static void setSessionScopeAttributes(SessionScopeAttributes accessor) {
		SessionScopeContextHolder.commerceScopeAttributesHolder.set(accessor);
	}

	/**
	 * Gets current <code>SessionScopeAttributes</code>.
	 */
	public static SessionScopeAttributes currentSessionScopeAttributes() throws IllegalStateException {
		SessionScopeAttributes accessor = commerceScopeAttributesHolder.get();

		if (accessor == null) {
			throw new IllegalStateException("No Session scoped attributes.");
		}

		return accessor;
	}

}

package com.serpics.core.test.repositories;

import com.serpics.core.data.Interceptor;
import com.serpics.stereotype.ModelInterceptor;

@ModelInterceptor(value=TestInterceptor.class , order=10)
public class TestInterceptor implements Interceptor {

	
	
}

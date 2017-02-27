package com.serpics.catalog.data.interceptors;

import com.serpics.catalog.data.model.CtentryMedia;
import com.serpics.stereotype.ModelInterceptor;

@ModelInterceptor(value=CtentryMedia.class)
public class CtentryMediaSaveInterceptor extends AbstractCatalogEntrySaveInterceptor{

}

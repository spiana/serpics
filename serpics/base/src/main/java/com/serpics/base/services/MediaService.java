package com.serpics.base.services;

import java.io.IOException;

import com.serpics.base.data.model.Media;


public interface MediaService<T extends Media> {

	public T create(T media ) throws IOException;

}

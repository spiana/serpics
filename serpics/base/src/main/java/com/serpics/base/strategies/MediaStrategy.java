package com.serpics.base.strategies;

import java.io.IOException;

import com.serpics.base.data.model.Media;

public interface MediaStrategy<T extends Media> {

	public T create(T media) throws IOException;
	public String getMediaUrl(T media);
	public T update (T media) throws IOException;
}

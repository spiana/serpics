package com.serpics.base.strategies.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import com.serpics.base.data.model.Media;
import com.serpics.base.strategies.MediaStrategy;
import com.serpics.mediasupport.MediaSupportType;
import com.serpics.mediasupport.utils.MediaStoreUtil;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value="mediaStrategy")
public class DefaultMediaStategy implements MediaStrategy<Media> {

	@Resource
	private MediaStoreUtil mediaUtils;
	
	@Override
	public Media create(Media media) throws IOException {
		if (media.getType().equals(MediaSupportType.LOCAL)){
			File f = new File(media.getSourcePath());
			if (f.isFile()){
				FileInputStream is = new FileInputStream(f);
				String source = createLocalMedia(media.getSourcePath(), is);
			}
		}
		return media;
	}

	@Override
	public String getMediaUrl(Media media) {
		
		if (media.getType().equals(MediaSupportType.LOCAL)){
			String mediaWeb  = mediaUtils.getMediaWebPath();
			if (!mediaWeb.endsWith("/"))
				mediaWeb +="/";
			return mediaWeb+ media.getSource();
		}else{
			return media.getSource();
		}
		
	}

	@Override
	public Media update(Media media) throws IOException {
		if (media.getType().equals(MediaSupportType.LOCAL)){
			File f = new File(media.getSourcePath());
			if (f.isFile()){
				FileInputStream is = new FileInputStream(f);
				String source = createLocalMedia(media.getSourcePath(), is);
			}
		}
		return media;
	}

	protected String createLocalMedia(String fileName , InputStream is ) throws IOException{
		String destinationPath = mediaUtils.getDestinationPath(fileName);
		FileOutputStream fos = new FileOutputStream(destinationPath);
		BufferedInputStream bis = new BufferedInputStream(is);

		int b;
		byte buffer[] = new byte[1024];

		BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

		while ((b = bis.read(buffer, 0, 1024)) != -1) {
			bos.write(buffer, 0, b);
		}

		bos.close();
		bis.close();
	
		return destinationPath;
	}
	

}

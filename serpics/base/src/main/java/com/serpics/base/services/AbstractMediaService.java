package com.serpics.base.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.MediaSupportType;
import com.serpics.base.data.model.Media;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.Repository;

@Transactional(readOnly = true)
public abstract class AbstractMediaService<T extends Media>  implements MediaService<T> {
	@Resource
	private CommerceEngine engine;
	
	public abstract Repository<T, Long> getRepository();

	@Value("${media.base.path}")
	private String baseMediaPath;
	
	@Override
	@Transactional
	public T create(T media  ) throws IOException{
		if (media.getType().equals(MediaSupportType.LOCAL)){
			File f = new File(media.getSourcePath());
			if (f.isFile()){
				FileInputStream is = new FileInputStream(f);
				String source = createLocalMedia(media.getSourcePath(), is);
			}
		}
		return getRepository().saveAndFlush(media);
	}

	
	protected String createLocalMedia(String fileName , InputStream is ) throws IOException{
		String destinationPath = getDestinationPath(fileName);
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

	protected String getDestinationPath(String filePath){
		Long storeId= engine.getCurrentContext().getStoreId();
		String[] path = filePath.replace("\\", "/").split("/");
		String fileName= path[path.length-1];
		String md5String = "AAABBB";
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(fileName.getBytes(Charset.forName("UTF8")));
			byte[] resultByte = messageDigest.digest();
			md5String = new String(Hex.encodeHex(resultByte));
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
		
		String completepath =FilenameUtils.concat(baseMediaPath, storeId.toString());
		completepath=FilenameUtils.concat(completepath, md5String.substring(0,3).toUpperCase());
		completepath=FilenameUtils.concat(completepath, md5String.substring(3,6).toUpperCase());
		completepath=FilenameUtils.concat(completepath, fileName);
		
		return completepath;
	}


	public String getBaseMediaPath() {
		return baseMediaPath;
	}


	public void setBaseMediaPath(String baseMediaPath) {
		this.baseMediaPath = baseMediaPath;
	}

}

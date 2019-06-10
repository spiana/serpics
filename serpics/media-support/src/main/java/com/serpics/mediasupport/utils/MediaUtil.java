/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.mediasupport.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import com.serpics.mediasupport.data.model.MediaField;

/**
 * @author spiana
 *
 */
public class MediaUtil implements InitializingBean{

	private MediaPathResolver mediaPathResolver;
	
	@Required
	public void setMediaPathResolver(MediaPathResolver mediaPathResolver) {
		this.mediaPathResolver = mediaPathResolver;
	}
	private static MediaUtil instance;
	
	public static MediaUtil getInstance(){
		return instance;
	}
	
	/**
	 * Convert local File path removing baseMediaPath.
	 * 
	 * @param localPath
	 * @return the path to store media Object
	 */
	public String getMediaSourcePathFromLocal(String localPath){
		String returnPath = localPath.replaceFirst(mediaPathResolver.getBaseMediaPath(), "");
		if (!returnPath.startsWith("/"))
			returnPath = "/"+returnPath;
		return returnPath;
	}
	 
	public String getMediaStorePathFromSource(String sourcePath){
		String returnPath =mediaPathResolver.getBaseMediaPath();
		return FilenameUtils.concat(returnPath, sourcePath.substring(1));
	}
	/**
	 * Calculate the locale store path for a media object
	 * @param filePath
	 * @return the complete local path
	 */
	public String getDestinationPath(String filePath){
				
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
		
		String completepath = mediaPathResolver.getBaseMediaPath();
		completepath=FilenameUtils.concat(completepath, md5String.substring(0,3).toUpperCase());
		completepath=FilenameUtils.concat(completepath, md5String.substring(3,6).toUpperCase());
		
		File f = new File(completepath);
		if (!f.exists())
			f.mkdirs();

		completepath=FilenameUtils.concat(completepath, fileName);
		
		
		
		return completepath;
	}


	public String createLocalMedia(String inputFilePath , InputStream inputFileStream ) throws IOException{
		String destinationPath = getDestinationPath(inputFilePath);
		FileOutputStream fos = new FileOutputStream(destinationPath);
		BufferedInputStream bis = new BufferedInputStream(inputFileStream);

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
	
	
	public byte[] getLocalMedia(MediaField media) throws IOException{
		String sourceStorePath = getMediaStorePathFromSource(media.getSourcePath());
		File fileMedia = new File(sourceStorePath);
		if(fileMedia.exists()){
			return FileUtils.readFileToByteArray(fileMedia);
		}else{
			throw new FileNotFoundException(sourceStorePath);
		}
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		instance = this;
	}
	
	
	
}

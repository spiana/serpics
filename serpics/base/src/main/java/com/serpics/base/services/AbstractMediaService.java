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
package com.serpics.base.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.MediaSupportType;
import com.serpics.base.data.model.Media;
import com.serpics.base.utils.MediaStoreUtil;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.Repository;

@Transactional(readOnly = true)
public abstract class AbstractMediaService<T extends Media>  implements MediaService<T> {
	@Resource
	private CommerceEngine engine;
	
	@Resource
	private MediaStoreUtil mediaUtils;
	
	public abstract Repository<T, Long> getRepository();
	
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

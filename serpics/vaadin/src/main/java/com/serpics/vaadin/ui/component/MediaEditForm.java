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
package com.serpics.vaadin.ui.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import com.serpics.mediasupport.MediaSupportType;
import com.serpics.mediasupport.data.model.MediaField;
import com.serpics.mediasupport.utils.MediaUtil;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Property.ValueChangeEvent;

/**
 * @author spiana
 *
 */
public class MediaEditForm<T extends MediaField> extends MasterForm<T> {
	
	public interface MediaSourceChangeEventListener extends Listener{}
	
	@Resource(name= "mediaUtil")
	MediaUtil mediaUtil;
	
	class ImageUploader implements Receiver, SucceededListener {
	    public File file;

	    public OutputStream receiveUpload(String filename,
	                                      String mimeType) {
	        // Create upload stream
	        FileOutputStream fos = null; // Stream to write to
	        try {
	            // Open the file for writing.
	            file = new File(MediaUtil.getInstance().getDestinationPath(filename));
	            fos = new FileOutputStream(file);
	            
	        } catch (final java.io.FileNotFoundException e) {
	            new Notification("Could not open file<br/>",
	                             e.getMessage(),
	                             Notification.Type.ERROR_MESSAGE).show(UI.getCurrent().getPage());
	            return null;
	        }
	        return fos; // Return the output stream to write to
	    }

	    public void uploadSucceeded(SucceededEvent event) {
	    	getEntityItem().getItemProperty("source").setValue(mediaUtil.getMediaSourcePathFromLocal(file.getAbsolutePath()));
	    	getEntityItem().getItemProperty("contentType").setValue(event.getMIMEType());
	    	
	    	fieldGroup.bind(fieldGroup.getField("source"), "source");
	     	fieldGroup.bind(fieldGroup.getField("contentType"), "contentType");
	     
	    }
	};
	

	/**
	 * @param clazz
	 */
	public MediaEditForm(Class<T> clazz) {
		super(clazz);
	}

	public void fireMediaSourceChangeEvent(){
		fireComponentEvent();
	}
	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.MasterForm#buildContent()
	 */
	@Override
	protected void buildContent() {
		super.buildContent();
		
		
		if (!entityItem.isPersistent()){
			ImageUploader receiver = new ImageUploader();
			// Create the upload with a caption and set receiver later
			final Upload upload = new Upload(I18nUtils.getMessage("smc.mediaeditform.upload","Upload Image Here"), receiver);
			upload.setButtonCaption(I18nUtils.getMessage("smc.mediaeditform.upload.button","Start upload"));
			upload.addSucceededListener(receiver);
			addComponent(upload);
			upload.setVisible(false);
			fieldGroup.getField("type").setBuffered(false);
			fieldGroup.getField("type").addValueChangeListener(new Property.ValueChangeListener() {
				
				@Override
				public void valueChange(ValueChangeEvent event) {
					if (event.getProperty().getValue().equals(MediaSupportType.LOCAL))
						upload.setVisible(true);
					else
						upload.setVisible(false);
				}
				
			});
			fieldGroup.getField("source").addValueChangeListener(new Property.ValueChangeListener() {
				
				@Override
				public void valueChange(ValueChangeEvent event) {
					entityItem.getItemProperty("source").setValue(event.getProperty().getValue());
					fireMediaSourceChangeEvent();
				}
			});
			fieldGroup.getField("contentType").addValueChangeListener(new Property.ValueChangeListener() {
				
				@Override
				public void valueChange(ValueChangeEvent event) {
					entityItem.getItemProperty("contentType").setValue(event.getProperty().getValue());
					fireMediaSourceChangeEvent();
				}
			});
			
		}else{
			fieldGroup.getField("type").setReadOnly(true);
			fieldGroup.getField("contentType").setReadOnly(true);
			fieldGroup.getField("source").setReadOnly(true);
		}
		
		
	}
	
}

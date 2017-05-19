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

import com.serpics.mediasupport.MediaSupportType;
import com.serpics.mediasupport.data.model.MediaField;
import com.serpics.mediasupport.utils.MediaStoreUtil;
import com.serpics.vaadin.ui.EntityComponent.EntityFormComponent;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.component.MediaEditForm.MediaSourceChangeEventListener;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Video;

/**
 * @author spiana
 *
 */
public class MediaComponent<T extends MediaField> extends CustomComponent implements EntityFormComponent<T> {

	MediaEditForm<T> mediaForm ;
	
	HorizontalLayout main; 
	VerticalLayout left ;
	VerticalLayout right;
	
	public MediaComponent(Class<T> clazz) {
		super();
		this.mediaForm = new MediaEditForm<T>(clazz);
		this.mediaForm.setReadOnly(false);
	}
	
	protected void buildContent(){
		
		main = new HorizontalLayout();
		left = new VerticalLayout();
		right = new VerticalLayout();
		
		main.addComponent(left);
		main.addComponent(right);
		main.setExpandRatio(left, 0.25F);
		main.setExpandRatio(right, .75F);
		main.setSizeFull();
		right.addComponent(mediaForm);
		setCompositionRoot(main);
		
		left.setMargin(true);
		
		EntityItem<T> item = mediaForm.getEntityItem();
		displayMedia(item);
		
		MediaSourceChangeEventListener listener = new MediaSourceChangeEventListener() {

			@Override
			public void componentEvent(Event event) {
				MasterForm<T> f = (MasterForm<T>) event.getSource();
				displayMedia(f.getEntityItem());
			}
		};
		mediaForm.addListener(listener);
	}
	
	private void displayMedia(EntityItem<T> item){
		left.removeAllComponents();
		
		if (item.getItemProperty("contentType").getValue() != null){
			if (item.getItemProperty("contentType").getValue().toString().startsWith("image")){
				Image image  = new Image();
				image.setWidth("90%");
				left.addComponent(image);
				 image.setSource(loadResource(item));;
				 image.markAsDirty();
			}
			if (item.getItemProperty("contentType").getValue().toString().startsWith("video")){
				Video video  = new Video();
				video.setWidth("90%");
				left.addComponent(video);
				video.setSource(loadResource(item));
				video.setAutoplay(true);
				video.markAsDirty();
			}
		}	
		
	}
	
	private Resource loadResource(EntityItem<T> media ){
		 Resource resource = null;
		if (media != null && media.getItemProperty("source").getValue() != null){
			String source = media.getItemProperty("source").getValue().toString();
			if (media.getItemProperty("type").getValue().equals(MediaSupportType.REMOTE)){
				if (source != null){
					 resource = new ExternalResource(source);
				}
			}else{
				if (source != null){
					String mediaFilePath = MediaStoreUtil.getInstance().getMediaStorePathFromSource(source);
					resource = new FileResource(new File(mediaFilePath));
				}
			}
		}	
		return resource;
	}
	
	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.EntityComponent#init()
	 */
	@Override
	public void init() {
		mediaForm.init();
	}

	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.EntityComponent#isInitialized()
	 */
	@Override
	public boolean isInitialized() {
		return mediaForm.isInitialized();
	}

	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.EntityComponent#discard()
	 */
	@Override
	public void discard() {
		mediaForm.discard();
	}

	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.EntityComponent#save()
	 */
	@Override
	public void save() throws CommitException {
		mediaForm.save();
	}

	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.EntityComponent#getEntityType()
	 */
	@Override
	public Class<T> getEntityType() {
		return mediaForm.getEntityType();
	}

	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.EntityComponent.EntityFormComponent#isModifield()
	 */
	@Override
	public boolean isModifield() {
		return mediaForm.isModifield();
	}

	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.EntityComponent.EntityFormComponent#isValid()
	 */
	@Override
	public boolean isValid() {
		return mediaForm.isValid();
	}

	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.EntityComponent.EntityFormComponent#setEntityItem(com.vaadin.addon.jpacontainer.EntityItem)
	 */
	@Override
	public void setEntityItem(EntityItem<T> entityItem) {
		mediaForm.setEntityItem(entityItem);
		buildContent();
		
	}

	
	
}

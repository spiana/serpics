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
/**
 * 
 */
package com.serpics.vaadin.smc.ui.importexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.importexport.services.ImportCsvService;
import com.serpics.importexport.services.ImportCsvService.ImportProgressListener;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.data.utils.JPAUtils;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.VerticalLayout;

/**
 * @author christian
 * @param <T>
 * @param <T>
 *
 */
@VaadinComponent("customUploadComponent")
public class CustomUploadComponent extends CustomComponent {

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(CustomUploadComponent.class);

	@Resource
	private transient ImportCsvService importCsvService;
	
	private VerticalLayout layout;
	private HorizontalLayout wrap;
	private CssLayout cssLayout;
	private Upload upload;
	private String fileToLoad;
	private Class<?> mappedClass;
	private ProgressBar progress;
	private TextField text_f;
	private ComboBox class_c;
	private Button import_b;
	
	public CustomUploadComponent() {
		buildComponentLayout() ;
	}



	/**
	 * @return the mappedClass
	 */
	public Class<?> getMappedClass() {
		return mappedClass;
	}



	/**
	 * @param mappedClass the mappedClass to set
	 */
	public void setMappedClass(Class<?> mappedClass) {
		this.mappedClass = mappedClass;
	}

	
	@SuppressWarnings("serial")
	public Component importDatafromUploadForm() {

		upload = new Upload("Chose a file to import:*", new Upload.Receiver() {
			
			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
					if (!validateMimeType(mimeType)) {
					showNotification("Serpics Ecommerce Platform", 	I18nUtils.getMessage("smc.upload.zipfile.notvalid", ""), 
							Position.TOP_RIGHT, 6000, "failure");
					return null;
				} else {
					FileOutputStream fos = null;
					try {
						String basepath = I18nUtils.getMessage("smc.upload.file.path", "");
						fos = new FileOutputStream(FileUtils.getFile(FileUtils.getTempDirectoryPath(), filename));
						fileToLoad = FileUtils.getFile(FileUtils.getTempDirectoryPath(), filename).getAbsolutePath();
					} catch (final Exception e) {
						 new Notification("Could not open file<br/>",
	                             e.getMessage(),
	                             Notification.Type.ERROR_MESSAGE)
	                .show(Page.getCurrent());
					}
					return fos; // Return the output stream to write to
				}
			}
		});
		
	 upload.addStartedListener(new Upload.StartedListener() {
		 @Override
            public void uploadStarted(StartedEvent event) {
            	progress.setValue(0F);
            }
        });
	 
		upload.addProgressListener(new Upload.ProgressListener() {
			
			@Override
			public void updateProgress(long readBytes, long contentLength) {
				float p =((float)readBytes) /((float)contentLength) * 100;
         		 progress.setValue(p);	
        	}
		});
		
		upload.addFinishedListener(new Upload.FinishedListener() {
			@Override
			public void uploadFinished(Upload.FinishedEvent finishedEvent) {
				
				try {
					if (fileToLoad != null) {
						importCsvService.importFromZip(fileToLoad , new ImportProgressListener() {
							
							@Override
							public void process(int curentRecord, int maxrecord) {
								progress.setValue(Float.valueOf((curentRecord/maxrecord)*100));
							}
							
							@Override
							public void init() {
								progress.setValue(Float.valueOf(0));
								
							}
							
							@Override
							public void end() {
								progress.setValue(Float.valueOf(0));
								
							}
						});
						showNotification("Import process", I18nUtils.getMessage("smc.upload.succesfully", ""), Position.TOP_RIGHT, 6000, "success closable");
					}
				} catch (IOException e) {
					
					logger.error("Import failed!!!!!!!!",e);
					
	        	}
				//progress.setValue(0F);
			}
			
		});

		return upload;// return upload form object

	}

	public Component buildComponentLayout() {
		VerticalLayout layout = buildMainLayout("tests-valo-light");
		setCompositionRoot(layout);
		return layout;
	}
	
	
	public VerticalLayout buildMainLayout(String theme){
		
		layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.setSizeFull();
		layout.setMargin(true);
		
		layout.addStyleName(theme);

		Label title = new Label("Import/Export Component");
		title.addStyleName("h2");
		layout.addComponent(title);

		
		final FormLayout form = buildFieldFormLayout("Map to Class:","Or Choose From Here");

		layout.addComponent(form);

		HorizontalLayout wrap = buildHorizonthalLayout(form,"Paste your data here:","");
		form.addComponent(wrap);
		layout.addComponent(form);
		layout.addComponent(buildUploadContainer("Serpics Import/Export Upload Form"));
		layout.addComponent(buildUploadProgressBar("500px") );
		return layout;
	}
	
	
	
	
	@SuppressWarnings("serial")
	public HorizontalLayout buildHorizonthalLayout(final FormLayout form,String text,String start_v) {
		
		wrap = new HorizontalLayout();
		wrap.setSpacing(true);
		wrap.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

		final TextArea data = new TextArea(text);
		data.setValue(start_v);
		data.setWidth("100%");
		data.setRows(8);
		data.setImmediate(true);
		data.setBuffered(true);
		data.setWordwrap(false);
		form.addComponent(data);

		HorizontalLayout row = new HorizontalLayout();
		row.addStyleName("wrapping");
		row.setSpacing(true);

		import_b = new Button("Import", new ClickListener() {
			
			private static final long serialVersionUID = -1076658951375855490L;
			
			@Override
			public void buttonClick(ClickEvent event) {
				 String reader = data.getValue();
				if(!validateCsvStringFormForm(reader) || reader==null || reader.equals(""))
					showNotification("Serpics Ecommerce Platform",I18nUtils.getMessage("smc.upload.scv.string.notvalid", ""), Position.TOP_RIGHT, 6000, "failure closable");
				else{
					try{
						UI.getCurrent().setPollInterval(500);
						importCsvService.importCsv(new StringReader(reader), getMappedClass(), new ImportProgressListener() {
							@Override
							public void process(int curentRecord, int maxrecord) {
								float value = (float)(curentRecord*100)/(float)maxrecord;
								if(value % 10 == 0)
									progress.setValue(value/100);
							}
							
							@Override
							public void init() {
								progress.setValue(Float.valueOf(0));
								
							}
							
							@Override
							public void end() {
							//	progress.setValue(Float.valueOf(0));
							}
						});
						
						showNotification("Import process", I18nUtils.getMessage("smc.upload.string.csv.succesfully", ""), Position.TOP_RIGHT, 6000, "success closable");
						data.setValue("");
						text_f.setValue("");
					}catch(Exception e){
						showNotification("Import process !!",e.getCause().getMessage() , Position.TOP_RIGHT, 6000, "failure closable");
						logger.debug("Error importCsvService.importCsv():",e); 
					}finally{
						UI.getCurrent().setPollInterval(-1);
					}
					
				}
			}

		});
		
		 data.addFocusListener(new FocusListener() {
              public void focus(FocusEvent event) {
  				checkClassnameTextField(class_c.getValue().toString());
              }
		 });
		
		
		row.addComponent(import_b);
		wrap.addComponent(row);
		
		return wrap;
	}

	/**
	 * 
	 * @param title_n
	 * @param description_n
	 * @param pos
	 * @param delay
	 * @param style (success, system,humanized,tray,warning,closaeble,dark,success,failure,bar,small)
	 * @return
	 */
	public Notification buildUploadNotification(String title_n,String description_n,Position pos,int delay,String style) {
		 Notification notification = new Notification("",Notification.Type.HUMANIZED_MESSAGE);
		 notification.setCaption(title_n);
		 notification.setDescription(description_n);
         notification.setDelayMsec(delay);
         notification.setHtmlContentAllowed(true);
         notification.setStyleName(style);
         notification.setPosition(pos);
		return notification;
	}

	/**
	 * 
	 * @param title_n
	 * @param description_n
	 * @param pos
	 * @param delay
	 * @param style
	 */
	public void showNotification(String title_n,String description_n,Position pos,int delay,String style){
		
		Notification _n = buildUploadNotification(title_n,description_n,pos,delay,style);
		_n.show(Page.getCurrent());
	}
	
	public Component buildUploadProgressBar(String width) {
		
		progress = new ProgressBar();
        progress.setCaption("");
        progress.setWidth(width);
		return progress;
	}


	public FormLayout buildFieldFormLayout(String text_f_v, String combo_v){

		final FormLayout form = new FormLayout();
		form.setMargin(false);
		form.setWidth("800px");

		text_f = new TextField(text_f_v);
		text_f.setValue("");
		text_f.setWidth("50%");
		text_f.setRequired(true);
		
		text_f.setImmediate(true);
		text_f.setEnabled(false);
		
		class_c = new ComboBox(combo_v);
		class_c.setTextInputAllowed(true);
		class_c.setNullSelectionAllowed(false);
		
		Set<EntityType<?>> entities = JPAUtils.get().retriveEntityTypes();
		for (EntityType<?> entityType : entities) {
			class_c.addItem(entityType.getJavaType().getName());
			class_c.setItemCaption(entityType.getJavaType().getName(), entityType.getName());
		}	
		class_c.addStyleName("small");
		class_c.setWidth("10em");
		class_c.setEnabled(true);
		form.addComponent(text_f);
		form.addComponent(class_c);
		
		return form;
	}
	
	
	public  Component buildUploadContainer(String label){
		
		Label serpicsImport = new Label(label);
		serpicsImport.addStyleName("h2");

		cssLayout = new CssLayout();

		cssLayout.addComponent(serpicsImport);
		
		FormLayout upload = new FormLayout();
		upload.addComponent(importDatafromUploadForm());
		cssLayout.addComponent(upload);
		
		return cssLayout;
	}

	/**
	 * 
	 * @param val
	 */
	public void checkClassnameTextField(String val){
		
		String value = val;
		if(!attachClassnameValidationListner(value)){
			showNotification("Serpics Ecommerce Platform", I18nUtils.getMessage("smc.upload.load.classname.notvalid", ""), Position.TOP_RIGHT, 6000, "failure closable");
			import_b.setVisible(false);
			}else{
				import_b.setVisible(true);
			}
		text_f.setValue(value);
	}
	
	public boolean validateCsvStringFormForm(String csv){
		
		Pattern pattern = Pattern.compile("(?:^|,)(\\\"(?:[^\\\"]+|\\\"\\\")*\\\"|[^,]*)");
	    Matcher matcher = pattern.matcher(csv);

        if(!matcher.matches())
        	return false;
        
        return true;
	}

	/**
	 * 
	 * @param mimeType
	 * @return
	 */
	public boolean validateMimeType(String mimeType) {
		return (Pattern.matches(".*zip.*", mimeType) && Pattern.matches(".*application.*", mimeType));
	}
	
	/**
	 * 
	 * @param _class
	 * @return
	 */
	public boolean attachClassnameValidationListner(String _class){
	
		if(_class.equals("") || _class == null)
			return false;

			
			mappedClass = null;
		    			
			try {
				mappedClass = Class.forName(_class);
				logger.info("Mapped class: {}",mappedClass);
				setMappedClass(mappedClass);
			} catch (ClassNotFoundException e) {
	        	showNotification("Serpics Ecommerce Platform", I18nUtils.getMessage("smc.upload.load.classname.notvalid", ""), Position.TOP_RIGHT, 6000, "failure closable");
	        	logger.error("Not Found Mapped class");
	        	return false;
		}
		return true;
	}

}

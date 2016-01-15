/**
 * 
 */
package com.serpics.vaadin.ui.importexport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipFile;

import javax.annotation.Resource;

import org.apache.commons.io.output.NullOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.importexport.services.ImportCsvService;
import com.serpics.stereotype.VaadinComponent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

/**
 * @author christian
 * @param <T>
 * @param <T>
 *
 */
@VaadinComponent("customUploadComponent")
public class CustomUploadComponent extends CustomComponent{

	private static final long serialVersionUID = 1L;

	Logger LOG = LoggerFactory.getLogger(CustomUploadComponent.class);

	
	@Resource
	private ImportCsvService importCsvService;
	
	private Upload upload;
	private ZipFile fileToUpload;
	private File tempFile;
	public CustomUploadComponent() {
		init();
	}

	/**
	 * @return the upload
	 */
	public Upload getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	/**
	 * @return the csvFile
	 */
	public ZipFile fileToUpload() {
		return fileToUpload;
	}

	/**
	 * @param csvFile
	 *            the csvFile to set
	 */
	public void setFileToUpload(ZipFile fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@SuppressWarnings("serial")
	public Component importDatafromUploadForm() {
		upload = new Upload("Chose a file to import:*", new Upload.Receiver() {
			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				
				 String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath(); // /admin
			     String uploadDir = System.getProperty("uploadDir", basepath + "/WEB-INF/"); //admin/WEB-INF
				
				if(!validateMimeType(mimeType)){
					showNotificationAfterImport("You Must select only zip file!");
				}else{
					FileOutputStream fos;
					try {
						String path ="/Users/christian/develop/serpics/upload/";//da definire strategy path
						setFileToUpload(new ZipFile(path+filename));
						fos = new FileOutputStream(fileToUpload.getName());
					} catch (final Exception e) {
						e.printStackTrace();
						return null;
					}
					return fos; // Return the output stream to write to
				}
				upload.interruptUpload();
			    return new NullOutputStream(); // 
			}
		});

		upload.addFinishedListener(new Upload.FinishedListener() {
			@Override
			public void uploadFinished(Upload.FinishedEvent finishedEvent) {
				
				try {
					if (fileToUpload != null) {
						importCsvService.importFromZip(fileToUpload.getName());
						String[] name = fileToUpload.getName().split("/");
						showNotificationAfterImport(name[name.length-1]);
					}else{
						LOG.error("Import failed!!!!!!!!");
					}
				} catch (IOException e) {
					LOG.error("Upload failed: {} !!!");
					e.printStackTrace();
				}
				
			}
		});
		return upload;//return upload form object

	}

	
    
    /**
     * 
     * @param fileName
     * @param mimeType
     * @return
     */
	public File createTemporayFile(String fileName, final String mimeType) {
		try {
			tempFile = File.createTempFile(fileName, mimeType);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tempFile;
	}

	/**
	 * 
	 * @param mimeType
	 * @return
	 */
	public boolean validateMimeType(String mimeType) {
		return mimeType.equals("application/zip");
	}

	public void init() {
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		setCompositionRoot(layout);
		final Label l = new Label("Serpics Import/Export Action Form ");
		l.setWidth("80%");
		l.setHeight("100%");
		l.setStyleName("Apptitle");
		HorizontalLayout title = new HorizontalLayout();
		title.setMargin(true);
		title.setSizeFull();
		title.addComponent(l);
		layout.addComponent(l);
		layout.addComponent(builfForm());
	}

	/**
	 * 
	 * @return
	 */
	public Component builfForm() {
		FormLayout form = new FormLayout();
		form.addComponent(importDatafromUploadForm());
		return form;
	}
	

	/**
	 * 
	 * @param message
	 */
	public void showNotificationAfterImport(String message){
		Notification notification = new Notification("Commerce PlaTform Notification");
		notification.setHtmlContentAllowed(true);
		notification.setIcon(FontAwesome.CHECK);
		notification.setDescription(message);
		notification.setPosition(Position.MIDDLE_CENTER);
		notification.setDelayMsec(6000);
		notification.setStyleName("commerce-notification");
		notification.show(Page.getCurrent());
	}
}

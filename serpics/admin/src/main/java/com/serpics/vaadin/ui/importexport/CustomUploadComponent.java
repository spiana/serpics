/**
 * 
 */
package com.serpics.vaadin.ui.importexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

import javax.annotation.Resource;

import org.apache.commons.io.output.NullOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.importexport.services.ImportCsvService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
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

	Logger LOG = LoggerFactory.getLogger(CustomUploadComponent.class);

	@Resource
	private ImportCsvService importCsvService;
	
	private VerticalLayout layout;
	private HorizontalLayout wrap;
	private CssLayout cssLayout;
	private Upload upload;
	private ZipFile fileToUpload;
	private Class<?> mappedClass;
	private ProgressBar progress;
	private TextField text_f;
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
			

				if (!validateMimeType(mimeType)) {
					showNotification("Serpics Ecommerce Platform", 	I18nUtils.getMessage("smc.upload.zipfile.notvalid", ""), 
							Position.TOP_RIGHT, 6000, "failure");
				} else {
					FileOutputStream fos;
					try {
						String basepath = I18nUtils.getMessage("smc.upload.file.path", "");
						setFileToUpload(new ZipFile(basepath + filename));
						fos = new FileOutputStream(fileToUpload.getName());
					} catch (final Exception e) {
						return null;
					}
					return fos; // Return the output stream to write to
				}
				upload.interruptUpload(); //interrupt upload
				return new NullOutputStream(); // return outpustream null
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
				upload.setImmediate(true);
            	getUI().setPollInterval(600);
				 progress.setValue(((float)readBytes) /
                         ((float)contentLength));	
				 
			}
		});
		
		upload.addFinishedListener(new Upload.FinishedListener() {
			@Override
			public void uploadFinished(Upload.FinishedEvent finishedEvent) {
				
				try {
					if (fileToUpload != null) {
						importCsvService.importFromZip(fileToUpload.getName());
						showNotification("Serpics Ecommerce Platform", I18nUtils.getMessage("smc.upload.succesfully", ""), Position.TOP_RIGHT, 6000, "success closable");
					}
				} catch (IOException e) {
					LOG.error("Import failed!!!!!!!!");
					progress.setValue(0F);
	            	getUI().setPollInterval(-1);// fermo il thread
				}
            	getUI().setPollInterval(-1);// fermo il thread
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

		
		String[] combo_value =  I18nUtils.getMessage("smc.upload.valid.classname", "").split(",") ;
		final FormLayout form = buildFieldFormLayout("Map to Class:","Or Choose From Here",combo_value);

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
						importCsvService.importCsv(new StringReader(reader), getMappedClass());
						showNotification("Serpics Ecommerce Platform", I18nUtils.getMessage("smc.upload.string.csv.succesfully", ""), Position.TOP_RIGHT, 6000, "success closable");
						data.setValue("");
						text_f.setValue("");
					}catch(Exception e){
						showNotification("Serpics Ecommerce Platform", I18nUtils.getMessage("smc.upload.scv.string.notvalid", ""), Position.TOP_RIGHT, 6000, "failure closable");
					}
					
				}
			}

		});
		
		 data.addFocusListener(new FocusListener() {
              public void focus(FocusEvent event) {
  				checkClassnameTextField(text_f.getValue());
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


	public FormLayout buildFieldFormLayout(String text_f_v, String combo_v,String[] combo_item){

		final FormLayout form = new FormLayout();
		form.setMargin(false);
		form.setWidth("800px");

		text_f = new TextField(text_f_v);
		text_f.setValue("");
		text_f.setWidth("50%");
		text_f.setRequired(true);
		text_f.addStyleName("inline-icon");
		
		text_f.setImmediate(true);
		
		ComboBox class_c = new ComboBox(combo_v,Arrays.asList(combo_item));
		class_c.setTextInputAllowed(false);
		class_c.setNullSelectionAllowed(false);
		class_c.select("Product");
		class_c.addStyleName("small");
		class_c.setWidth("10em");
		class_c.setEnabled(false);
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
		return mimeType.equals("application/zip");
	}
	
	/**
	 * 
	 * @param _class
	 * @return
	 */
	public boolean attachClassnameValidationListner(String _class){
	
		if(_class.equals("") || _class == null)
			return false;

			String pack = I18nUtils.getMessage("scm.upload.package.name", "");		
			mappedClass = null;
		    			
			try {
				mappedClass = Class.forName(pack + "." +_class);
				LOG.info("Mapped class: {}",mappedClass);
				setMappedClass(mappedClass);
			} catch (ClassNotFoundException e) {
	        	showNotification("Serpics Ecommerce Platform", I18nUtils.getMessage("smc.upload.load.classname.notvalid", ""), Position.TOP_RIGHT, 6000, "failure closable");
	        	LOG.error("Not Found Mapped class");
	        	return false;
		}
		return true;
	}

}

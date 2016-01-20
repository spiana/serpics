/**
 * 
 */
package com.serpics.vaadin.ui.importexport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;

/**
 * @author christian
 *
 */
public class SerpicsNotification extends CustomComponent {

	
	private static final long serialVersionUID = 4184933353933617651L;
	private static Logger logger = LoggerFactory.getLogger(SerpicsNotification.class);

	private static SerpicsNotification instance;
	private Notification notification;
	
	
	private SerpicsNotification() {
	}


	/**
	 * 
	 * @return singleton
	 */
	public synchronized static SerpicsNotification getInstance() {

		if (instance == null) {
			synchronized (SerpicsNotification.class) {
				if (instance == null)
					instance = new SerpicsNotification();
			}
		}
		logger.info("created SerpicsNotification singleton: " + instance);
		return instance;
	}

	

	/**
	 * 
	 * @param caption title of notification
	 * @param description description of notification
	 * @param delay time after that notification fade out
	 * @param style (success,failure,warning)
	 * @param pos position of notofication 
	 * 										(Position.BOTTOM_CENTER,Position.BOTTOM_LEFT,Position.BOTTOM_RIGHT)
	 * 										(Position.TOP_CENTER,Position.TOP_LEFT,Position.TOP_RIGHT)
	 * 										(Position.MIDDLE_CENTER,Position.MIDDLE_LEFT,Position.MIDDLE_RIGHT)
	 * @param closable icon for close notification if true
	 * @return
	 */
	public Notification buildSimpleNotification(String caption, String description, int delay, String style,Position pos, boolean closable) {
		notification = new Notification(caption, Notification.Type.HUMANIZED_MESSAGE);
		notification.setDescription(description);
		notification.setDelayMsec(delay);
		notification.setHtmlContentAllowed(true);
		notification.setStyleName(style);
		notification.setPosition(pos);
		makeNotificationClosableWithIcon(closable);
		show();
		return notification;
	}
	
	/**
	 * 
	 * @param caption title of notification
	 * @param description description of notification
	 * @param delay time after that notification fade out
	 * @param style (success,failure,warning)
	 * @param pos position of notofication 
	 * 										(Position.BOTTOM_CENTER,Position.BOTTOM_LEFT,Position.BOTTOM_RIGHT)
	 * 										(Position.TOP_CENTER,Position.TOP_LEFT,Position.TOP_RIGHT)
	 * 										(Position.MIDDLE_CENTER,Position.MIDDLE_LEFT,Position.MIDDLE_RIGHT)
	 * @param closable icon for close notification if true
	 * @return
	 */
	public Notification buildBarNotification(String caption, String description, int delay, String style,Position pos, boolean closable) {
		notification = new Notification(caption, Notification.Type.HUMANIZED_MESSAGE);
		notification.setDescription(description);
		notification.setDelayMsec(delay);
		notification.setHtmlContentAllowed(true);
		notification.setStyleName(style + " bar");
		notification.setPosition(pos);
		makeNotificationClosableWithIcon(closable);
		show();
		return notification;
	}
	
	/**
	 * 
	 * @param caption title of notification
	 * @param description description of notification
	 * @param delay time after that notification fade out
	 * @param style (success,failure,warning)
	 * @param pos position of notofication 
	 * 										(Position.BOTTOM_CENTER,Position.BOTTOM_LEFT,Position.BOTTOM_RIGHT)
	 * 										(Position.TOP_CENTER,Position.TOP_LEFT,Position.TOP_RIGHT)
	 * 										(Position.MIDDLE_CENTER,Position.MIDDLE_LEFT,Position.MIDDLE_RIGHT)
	 * @param closable icon for close notification if true
	 * @return
	 */
	public Notification buildSystemNotification(String caption, String description, int delay, String style,Position pos, boolean closable) {
		notification = new Notification(caption, Notification.Type.HUMANIZED_MESSAGE);
		notification.setDescription(description);
		notification.setDelayMsec(delay);
		notification.setHtmlContentAllowed(true);
		notification.setStyleName(style + " system");
		notification.setPosition(pos);
		makeNotificationClosableWithIcon(closable);
		show();
		return notification;
	}

	public String makeNotificationClosableWithIcon(Boolean bool){
		if(bool)
			notification.setStyleName(notification.getStyleName().concat(" closable"));
		return null;
	}
	
	
	public void show(){
		notification.show(Page.getCurrent());
	}
}

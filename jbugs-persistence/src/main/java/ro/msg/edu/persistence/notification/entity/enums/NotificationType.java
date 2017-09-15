package ro.msg.edu.persistence.notification.entity.enums;

public enum NotificationType {
	WELCOME_NEW_USER("WELCOME_NEW_USER"), USER_UPDATED("USER_UPDATED"), USER_DELETED("USER_DELETED"), BUG_UPDATED(
			"BUG_UPDATED"), BUG_CLOSED("BUG_UPDATED, BUG_CLOSED"), BUG_STATUS_UPDATED(
					"BUG_STATUS_UPDATED"), USER_DEACTIVATED("USER_DEACTIVATED");

	private final String notification;

	private NotificationType(String notification) {
		this.notification = notification;
	}

	public String getNotification() {
		return notification;
	}
}

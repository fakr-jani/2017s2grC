package ro.msg.edu.business.notification.dto;

import java.sql.Timestamp;
import java.util.List;

import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.persistence.notification.entity.enums.NotificationType;
import ro.msg.edu.persistence.user.entity.User;

public class NotificationDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String bugURL;
	private String message;
	private NotificationType notificationType;
	private Timestamp timestamp;
	private BugDTO bug;
	private List<User> users;

	public String getBugURL() {
		return bugURL;
	}

	public void setBugURL(String bugURL) {
		this.bugURL = bugURL;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public BugDTO getBug() {
		return bug;
	}

	public void setBug(BugDTO bug) {
		this.bug = bug;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "NotificationDTO [message=" + message + ", notificationType=" + notificationType + ", timestamp="
				+ timestamp + "]";
	}

}

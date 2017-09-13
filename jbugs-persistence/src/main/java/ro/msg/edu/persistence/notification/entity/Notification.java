package ro.msg.edu.persistence.notification.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.common.entity.AbstractEntity;
import ro.msg.edu.persistence.user.entity.User;

@Entity
public class Notification extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNotification;

	private String bugURL;

	@Column
	private String message;

	@NotNull
	@Column
	private String notificationType;

	@Column
	private Timestamp timestamp;

	@ManyToOne
	private Bug bug;


	@Override
	public Long getId() {
		return idNotification;
	}


	@ManyToMany(mappedBy = "notifications")
	private List<User> users;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


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


	public String getNotificationType() {
		return notificationType;
	}


	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public Bug getBug() {
		return bug;
	}


	public void setBug(Bug bug) {
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
		return "Notification [idNotification=" + idNotification + ", notificationType=" + notificationType + "]";
	}
}

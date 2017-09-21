package ro.msg.edu.business.notification.boundary;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.notification.control.NotificationControl;
import ro.msg.edu.business.notification.dto.NotificationDTO;
import ro.msg.edu.business.user.dto.UserDTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class NotificationFacade implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private NotificationControl notificationControl;

	public List<NotificationDTO> getAllNotifications(UserDTO user) throws TechnicalException {
		return notificationControl.getAllNotifications(user);
	}

	public void createClosedBugNotification(BugDTO bug) throws TechnicalException {
		notificationControl.createClosedBugNotification(bug);

	}

	public List<NotificationDTO> findNotificationsNotReceived(UserDTO user) throws TechnicalException {
		return notificationControl.findNotificationsNotReceived(user);
	}

	public void setReceivedTrue(NotificationDTO notification) {
		notificationControl.setReceivedTrue(notification);
	}
}

package ro.msg.edu.business.notification.control;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dao.BugDAO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.notification.dao.NotificationDAO;
import ro.msg.edu.business.notification.dto.NotificationDTO;
import ro.msg.edu.business.notification.dto.mapper.NotificationDTOMapper;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.notification.entity.Notification;
import ro.msg.edu.persistence.notification.entity.enums.NotificationType;
import ro.msg.edu.persistence.user.entity.User;

@Stateless
public class NotificationControl implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private NotificationDTOMapper notificationMapper;

	@EJB
	private NotificationDAO notificationDAO;

	@EJB
	private UserDAO userDAO;

	@EJB
	private BugDAO bugDAO;

	@EJB
	private UserDTOMapper userMapper;

	public List<NotificationDTO> getAllNotifications(UserDTO user) throws TechnicalException {
		Optional<User> userEntity = userDAO.findUserByUsername(user.getUsername());
		if (userEntity.isPresent()) {
			return notificationMapper.mapToDTOs(notificationDAO.findNotificationsByUser(userEntity.get()));
		} else {
			throw new TechnicalException("Cannot find user in session. Please log in!");
		}
	}

	public NotificationDTO createClosedBugNotification(BugDTO bugDTO) throws TechnicalException {
		Bug bugEntity = bugDAO.findBugByTitle(bugDTO.getTitleBug());
		Notification notificationEntity = setNotification(bugEntity, NotificationType.BUG_CLOSED,
				"Bug" + bugEntity.getTitleBug() + "has been closed");
		notificationDAO.persistEntity(notificationEntity);
		Notification persistedNotification = notificationDAO.findEntity(notificationEntity.getId());
		return notificationMapper.mapToDTO(persistedNotification);

	}

	private Notification setNotification(Bug bug, NotificationType notificationType, String message) {
		Notification notificationEntity = new Notification();
		List<User> users = new ArrayList<User>();
		users.add(bug.getAssignedTo());
		users.add(bug.getCreatedBy());
		notificationEntity.setUsers(users);
		notificationEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));
		notificationEntity.setBug(bug);
		notificationEntity.setNotificationType(notificationType);
		notificationEntity.setMessage(message);
		return notificationEntity;

	}
}

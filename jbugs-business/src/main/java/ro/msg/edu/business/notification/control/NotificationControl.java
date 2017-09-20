package ro.msg.edu.business.notification.control;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.notification.dao.NotificationDAO;
import ro.msg.edu.business.notification.dto.NotificationDTO;
import ro.msg.edu.business.notification.dto.mapper.NotificationDTOMapper;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
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
	private UserDTOMapper userMapper;

	public List<NotificationDTO> getAllNotifications(UserDTO user) throws TechnicalException {
		Optional<User> userEntity = userDAO.findUserByUsername(user.getUsername());
		if (userEntity.isPresent()) {
			return notificationMapper.mapToDTOs(notificationDAO.findNotificationsByUser(userEntity.get()));
		} else {
			throw new TechnicalException("Cannot find user in session. Please log in!");
		}
	}
}

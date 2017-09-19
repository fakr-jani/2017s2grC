package ro.msg.edu.business.notification.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.notification.dao.NotificationDAO;
import ro.msg.edu.business.notification.dto.NotificationDTO;
import ro.msg.edu.business.user.control.UserCRUDControl;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
import ro.msg.edu.persistence.notification.entity.Notification;
import ro.msg.edu.persistence.notification.entity.enums.NotificationType;
import ro.msg.edu.persistence.user.entity.User;

public class NotificationControlTest extends AbstractIntegrationTest {

	@EJB
	private NotificationControl notificationControl;

	@EJB
	private UserCRUDControl userControl;

	@EJB
	private NotificationDAO notificationDAO;

	@EJB
	private UserDAO userDAO;
	@EJB
	private UserDTOMapper userMapper;

	@Test
	public void getNotifications_Succes() throws TechnicalException {

		Notification notification = new Notification();
		notification.setMessage("YELLOOOO");
		notification.setNotificationType(NotificationType.WELCOME_NEW_USER);
		notificationDAO.persistEntity(notification);

		User testUser = new User();
		List<Notification> notifications = new ArrayList<Notification>();

		testUser.setFirstname("CevaNume");
		testUser.setLastname("Smith");
		testUser.setUsername("SmithC");
		testUser.setPassword("123");
		testUser.setEmail("Ceva_nume@msggroup.com");
		testUser.setPhoneNumber("+407098884443");

		notifications.add(notification);
		testUser.setNotifications(notifications);
		userDAO.persistEntity(testUser);

		List<NotificationDTO> userNotifications = notificationControl
				.getAllNotifications(userMapper.mapToDTO(testUser));
		Assert.assertEquals("User should have one notification", 1, userNotifications.size());

	}
}

package ro.msg.edu.business.notification.dto.mapper;

import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.business.notification.dto.NotificationDTO;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.notification.entity.Notification;

/**
 * Mapper for {@link Notification} and {@link NotificationDTO}.
 * 
 * @author maresb
 *
 */
@Stateless
public class NotificationDTOMapper extends AbstractDTOMapper<Notification, NotificationDTO> {

	private static final long serialVersionUID = 1L;

	@Override
	public Notification getEntityInstance() {
		return new Notification();
	}

	@Override
	public NotificationDTO getDTOInstance() {
		return new NotificationDTO();
	}

	@Override
	protected void mapEntityToDTOFields(Notification notificationEntity, NotificationDTO notificationDTO) {
		notificationDTO.setMessage(notificationEntity.getMessage());
		notificationDTO.setNotificationType(notificationEntity.getNotificationType());
		notificationDTO.setTimestamp(notificationEntity.getTimestamp());
		notificationDTO.setBugURL(notificationEntity.getBugURL());
		notificationDTO.setUsers(notificationEntity.getUsers());

		Bug bugEntity = notificationEntity.getBug();
		if (bugEntity != null) {
			BugDTO bugDTO = new BugDTO();
			bugDTO.setId(bugEntity.getId());
			notificationDTO.setBug(bugDTO);
		}
		notificationDTO.setReceived(notificationEntity.isReceived());

	}

	@Override
	protected void mapDTOToEntityFields(NotificationDTO notificationDTO, Notification notificationEntity) {
		notificationEntity.setMessage(notificationDTO.getMessage());
		notificationEntity.setNotificationType(notificationDTO.getNotificationType());
		notificationEntity.setTimestamp(notificationDTO.getTimestamp());
		notificationEntity.setBugURL(notificationDTO.getBugURL());
		notificationEntity.setUsers(notificationDTO.getUsers());

		BugDTO bugDTO = notificationDTO.getBug();
		if (bugDTO != null) {
			Bug bugEntity = new Bug();
			bugEntity.setIdBug(bugDTO.getId());
			notificationEntity.setBug(bugEntity);
		}
		notificationEntity.setReceived(notificationDTO.isReceived());

	}

}

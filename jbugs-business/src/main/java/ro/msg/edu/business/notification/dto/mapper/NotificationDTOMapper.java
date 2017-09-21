package ro.msg.edu.business.notification.dto.mapper;

import javax.ejb.Stateless;

import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.business.notification.dto.NotificationDTO;
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
	protected void mapEntityToDTOFields(Notification entity, NotificationDTO dto) {
		dto.setMessage(entity.getMessage());
		dto.setNotificationType(entity.getNotificationType());
		dto.setTimestamp(entity.getTimestamp());
		dto.setBug(entity.getBug());
		dto.setBugURL(entity.getBugURL());
		dto.setUsers(entity.getUsers());
	}

	@Override
	protected void mapDTOToEntityFields(NotificationDTO dto, Notification entity) {
		entity.setMessage(dto.getMessage());
		entity.setNotificationType(dto.getNotificationType());
		entity.setTimestamp(dto.getTimestamp());
		entity.setBug(dto.getBug());
		entity.setBugURL(dto.getBugURL());
		entity.setUsers(dto.getUsers());
	}

}

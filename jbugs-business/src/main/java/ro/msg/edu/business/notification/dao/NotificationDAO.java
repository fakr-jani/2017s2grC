package ro.msg.edu.business.notification.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ro.msg.edu.business.common.dao.AbstractDao;
import ro.msg.edu.persistence.notification.entity.Notification;
import ro.msg.edu.persistence.user.entity.User;

@Stateless
public class NotificationDAO extends AbstractDao<Notification> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Notification> getEntityClass() {
		return Notification.class;
	}

	public List<Notification> findNotificationsByUser(User user) {

		Query query = em.createQuery("Select n from User u inner join u.notifications n where u.idUser=:idUser");
		query.setParameter("idUser", user.getId());
		return query.getResultList();
	}

	public List<Notification> findNotificationsNotReceived(User user) {
		Query query = em.createQuery(
				"Select n from User u inner join u.notifications n where u.idUser=:idUser and n.received='false'");
		query.setParameter("idUser", user.getId());
		return query.getResultList();
	}

}

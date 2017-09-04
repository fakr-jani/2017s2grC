package ro.msg.edu.business.user.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ro.msg.edu.business.common.dao.AbstractDao;
import ro.msg.edu.persistence.user.entity.User;

/**
 * 
 * @author maresb
 *
 */
@Stateless
public class UserDAO extends AbstractDao<User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	public User findUserByEmail(String email) {
		TypedQuery<User> query = this.em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class);
		query.setParameter("email", email);

		return getSingleResult(query);
	}

}

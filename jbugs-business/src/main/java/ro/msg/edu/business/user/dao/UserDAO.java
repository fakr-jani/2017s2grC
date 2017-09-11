package ro.msg.edu.business.user.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ro.msg.edu.business.common.dao.AbstractDao;
import ro.msg.edu.persistence.user.entity.User;

/**
 * DAO for {@link User} entity.
 * 
 * @author Andrei Floricel, msg systems ag
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

	public User findUserByUsername(String username) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}

	public boolean verifyUserExists(String username, String password) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username and u.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> users = query.getResultList();
		return users.isEmpty() == false;
	}

	public List<User> getAll() {
		Query query = em.createQuery("SELECT u FROM User u");
		List<User> users = query.getResultList();
		return users;
	}

}

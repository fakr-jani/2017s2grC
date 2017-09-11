package ro.msg.edu.business.user.dao;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

=======
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ro.msg.edu.business.common.dao.AbstractDao;
<<<<<<< HEAD
import ro.msg.edu.business.common.exception.TechnicalException;
=======
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
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

<<<<<<< HEAD
	public User findUserByEmail(String email) throws TechnicalException {
		TypedQuery<User> query = this.em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class);
		query.setParameter("email", email);

		try {
			return getSingleResult(query);

		} catch (Exception e) {
			throw new TechnicalException("Zero or more results", e.getCause());
		}
	}

	public Optional<User> findUserByUsername(String username) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
		query.setParameter("username", username);
		Optional<User> optional;
		try {
			return optional = Optional.ofNullable((User) query.getSingleResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(new User());
	}

	public boolean verifyUserExists(String username, String password) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username and u.password= :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> userList = query.getResultList();
		return userList.isEmpty() == false;

	}

	public List<User> getAllUser() {
		Query query = em.createQuery("SELECT u FROM User u ");
		return query.getResultList();
=======
	public User findUserByEmail(String email) {
		TypedQuery<User> query = this.em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class);
		query.setParameter("email", email);

		return getSingleResult(query);
	}

	public User findUserByUsername(String username) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
		query.setParameter("username", username);
		return (User) query.getSingleResult();
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
	}

}

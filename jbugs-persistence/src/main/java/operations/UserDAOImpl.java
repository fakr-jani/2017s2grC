package operations;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;

@Stateless(name = "UserDAOImpl")
public class UserDAOImpl implements UserDAO, Serializable {

	@Serializable
	@PersistenceContext(unitName = "jbugs-persistence")
	EntityManager em;

	@Override
	public void persistUser(User user) {
		em.persist(user);
	}

	@Override
	public void deleteUser(User user) {
		em.remove(user);
	}

	@Override
	public void updateUser(User user) {
		em.merge(user);
	}

	@Override
	public User findUser(int idUser) {
		return em.find(User.class, idUser);
	}

	@Override
	public List<User> listAllUsers() {
		List<User> users;
		users = em.createNamedQuery("User.findAllUsers").getResultList();
		return users;
	}

}

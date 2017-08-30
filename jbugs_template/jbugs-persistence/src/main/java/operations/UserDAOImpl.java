package operations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;

@Stateless(name = "UserDAOImpl", mappedName="UserDAOImpl")
public class UserDAOImpl implements UserDAO {

	@PersistenceContext(unitName = "jbugs-persistence")
	EntityManager em;

	@Override
	public void persistUser(User user) {
		// TODO Auto-generated method stub

		em.persist(user);

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

		em.remove(user);

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

		em.merge(user);

	}

	@Override
	public User findUser(int userId) {
		// TODO Auto-generated method stub

		User u = em.find(User.class, userId);
		return u;
	}

	@Override
	public List<User> listAllUsers() {
		// TODO Auto-generated method stub

		List<User> users = em.createNamedQuery("findAllUsers").getResultList();

		return users;
	}

}

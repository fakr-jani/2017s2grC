package operations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;

@Stateless(name="UserDAOImpl")
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext(unitName="jbugs-persistence")
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
		User u=em.find(User.class, idUser);
		return  u;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users=em.createNamedQuery("User.findAllUsers").getResultList();
		return users;
	}

}

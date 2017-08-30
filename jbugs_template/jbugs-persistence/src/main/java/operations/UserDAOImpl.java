package operations;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;

@Stateless(name="UserDAOImpl", mappedName="UserDAOImpl")
public class UserDAOImpl implements UserDAO, Serializable {

	@PersistenceContext(unitName = "jbugs-persistence")
	EntityManager entityManager;

	@Override
	public void persistUser(User user) {

		entityManager.persist(user);

	}

	@Override
	public void deleteUser(User user) {

		entityManager.remove(user);

	}

	@Override
	public User findUser(int idUser) {

		return entityManager.find(User.class, idUser);
	}

	@Override
	public void updateUser(User user) {

		entityManager.merge(user);

	}

	@Override
	public List<User> listAllUsers() {

		List<User> createQuery = entityManager.createNamedQuery("User.findAllUsers").getResultList();
		return createQuery;
	}

}

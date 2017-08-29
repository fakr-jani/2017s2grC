package operations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Role;

@Stateless(name = "RoleDAOImpl")
public class RoleDAOImpl implements RoleDAO {

	@PersistenceContext(unitName = "jbugs-persistence")
	EntityManager entityManager;

	@Override
	public void persistRole(Role role) {

		entityManager.persist(role);

	}

	@Override
	public void deleteRole(Role role) {

		entityManager.remove(role);

	}

	@Override
	public Role findRole(int idRole) {

		return entityManager.find(Role.class, idRole);
	}

	@Override
	public void updateRole(Role role) {

		entityManager.merge(role);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> listAllRoles() {

		Query q = entityManager.createQuery("select r from Role r");
		List<Role> roles = q.getResultList();
		return roles;
	}

}

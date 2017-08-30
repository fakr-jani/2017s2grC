package operations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Role;

@Stateless(name = "RoleDAOImpl", mappedName="RoleDAOImpl")
public class RoleDAOImpl implements RoleDAO {

	@PersistenceContext(unitName = "jbugs-persistence")
	EntityManager em;

	@Override
	public void persistRole(Role role) {
		// TODO Auto-generated method stub

		em.persist(role);

	}

	@Override
	public void deleteRole(Role role) {
		// TODO Auto-generated method stub

		em.remove(role);

	}

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub

		em.merge(role);

	}

	@Override
	public Role findRole(int roleId) {
		// TODO Auto-generated method stub

		Role r = em.find(Role.class, roleId);
		return r;
	}

	@Override
	public List<Role> listAllRoles() {
		// TODO Auto-generated method stub

		List<Role> roles;
		Query query = em.createQuery("select r from Role r");
		roles = query.getResultList();
		;
		return roles;
	}

}

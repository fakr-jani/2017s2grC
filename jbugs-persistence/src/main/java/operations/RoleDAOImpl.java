package operations;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Role;

@Stateless(name = "RoleDAOImpl")
public class RoleDAOImpl implements RoleDAO, Serializable {

	@PersistenceContext(unitName = "jbugs-persistence")
	EntityManager em;

	@Override
	public void persistRole(Role role) {
		em.persist(role);
	}

	@Override
	public void deleteRole(Role role) {
		em.remove(role);
	}

	@Override
	public void updateRole(Role role) {
		em.merge(role);
	}

	@Override
	public Role findRole(int idRole) {
		return em.find(Role.class, idRole);
	}

	@Override
	public List<Role> listAllRoles() {
		List<Role> roles;
		Query q = em.createQuery("select r from Role r");
		roles = q.getResultList();
		return roles;
	}

}

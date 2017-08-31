package operations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Role;

@Stateless(name="RoleDAOImpl")
public class RoleDAOImpl implements RoleDAO {
	
	@PersistenceContext(unitName="jbugs-persistence")
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
		Role u=em.find(Role.class, idRole);
		return  u;
	}

	@Override
	public List<Role> findAllRoles() {
		Query query =em.createQuery("select r from Role r");
		List<Role> roles=query.getResultList();
		return roles;
	}

}

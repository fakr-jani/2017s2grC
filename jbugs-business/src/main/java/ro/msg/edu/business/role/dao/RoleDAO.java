package ro.msg.edu.business.role.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ro.msg.edu.business.common.dao.AbstractDao;
import ro.msg.edu.persistence.user.entity.Role;

@Stateless
public class RoleDAO extends AbstractDao<Role> {

	@Override
	public Class<Role> getEntityClass() {
		// TODO Auto-generated method stub
		return Role.class;
	}

	public List<Role> getAllRoles() {
		Query query = em.createQuery("SELECT r FROM Role r ");
		return query.getResultList();
	}
}

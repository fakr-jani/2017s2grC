package ro.msg.edu.business.permission.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ro.msg.edu.business.common.dao.AbstractDao;
import ro.msg.edu.persistence.user.entity.Permission;

@Stateless
public class PermissionDAO extends AbstractDao<Permission> {

	@Override
	public Class<Permission> getEntityClass() {
		// TODO Auto-generated method stub
		return Permission.class;
	}

	public List<Permission> getAllPermissions() {
		Query query = em.createQuery("SELECT p FROM Permission p ");
		return query.getResultList();
	}

}

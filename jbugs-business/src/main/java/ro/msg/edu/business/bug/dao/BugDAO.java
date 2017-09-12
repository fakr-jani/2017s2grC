package ro.msg.edu.business.bug.dao;

import javax.ejb.Stateless;

import ro.msg.edu.business.common.dao.AbstractDao;
import ro.msg.edu.persistence.bug.entity.Bug;

/**
 * DAO for {@link Bug} entity.
 * 
 * @author Alex Noja
 *
 */
@Stateless
public class BugDAO extends AbstractDao<Bug> {

	@Override
	public Class<Bug> getEntityClass() {
		return Bug.class;
	}

}

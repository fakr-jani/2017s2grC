package ro.msg.edu.business.bug.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ro.msg.edu.business.common.dao.AbstractDao;
import ro.msg.edu.business.common.exception.TechnicalException;
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
	
	public Bug findBugByTitle(String titleBug) throws TechnicalException{
		TypedQuery<Bug> query = this.em.createNamedQuery(Bug.FIND_BUG_BY_TITLE, Bug.class);
		query.setParameter("title", titleBug);
		
		return getSingleResult(query);
	}
}

package ro.msg.edu.business.bug.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ro.msg.edu.business.common.dao.AbstractDao;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;

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

	// public Bug findBugByTitle(String title) throws TechnicalException {
	// TypedQuery<Bug> query = this.em.createNamedQuery(Bug.FIND_BUG_BY_TITLE,
	// Bug.class);
	// query.setParameter("title", title);
	//
	// Bug bug;
	// try {
	// bug = query.getSingleResult();
	// } catch (NoResultException e) {
	// throw new TechnicalException("There is no Bug with the given title!",
	// e.getCause());
	// } catch (NonUniqueResultException e1) {
	// throw new TechnicalException("More Bugs found with the given title!",
	// e1.getCause());
	// }
	// return bug;
	// }

	public Bug findBugByTitle(String title) throws TechnicalException {
		TypedQuery<Bug> query = this.em.createNamedQuery(Bug.FIND_BUG_BY_TITLE, Bug.class);
		query.setParameter("title", title);

		return getSingleResult(query);
	}

	public List<Bug> findAllBugs() {
		TypedQuery<Bug> query = this.em.createNamedQuery(Bug.FIND_ALL_BUGS, Bug.class);
		return query.getResultList();

	}

	public List<Bug> findBugsThatCanBeClosed() {
		Query query = em.createQuery("SELECT b FROM Bug b WHERE b.status = :fixed OR b.status = :rejected");
		query.setParameter("fixed", BugStatusType.FIXED);
		query.setParameter("rejected", BugStatusType.REJECTED);
		return query.getResultList();
	}

}

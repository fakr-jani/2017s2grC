package ro.msg.edu.business.bug.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ro.msg.edu.business.common.dao.AbstractDao;
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

	public Bug findBugByTitle(String title) {
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

	public List<String> findAssignerForFixedBug() {
		Query query = em.createQuery("SELECT a.username FROM Bug b INNER JOIN b.assignedTo a WHERE b.status = :fixed");
		query.setParameter("fixed", BugStatusType.FIXED);
		return query.getResultList();
	}

	public List<String> findCreatorForRejectedBugs() {
		Query query = em.createQuery("SELECT c.username FROM Bug b INNER JOIN b.createdBy c WHERE b.status= :rejected");
		query.setParameter("rejected", BugStatusType.REJECTED);
		return query.getResultList();
	}

}

package ro.msg.edu.business.common.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ro.msg.edu.persistence.common.entity.AbstractEntity;

public abstract class AbstractDao<E extends AbstractEntity> implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "jbugs-persistence")
	protected EntityManager em;

	public abstract Class<E> getEntityClass();

	public void persistEntity(E entity) {
		em.persist(entity);
		em.flush();
	}

	public void deleteEntity(E entity) {
		em.remove(entity);
	}

	public E findEntity(Long id) {
		return em.find(getEntityClass(), id);
	}

	public E getSingleResult(TypedQuery<E> query) {
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}

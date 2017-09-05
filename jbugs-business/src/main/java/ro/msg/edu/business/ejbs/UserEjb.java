package ro.msg.edu.business.ejbs;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ro.msg.edu.business.dao.UserDao;
import ro.msg.edu.business.dto.UserDTO;
import ro.msg.edu.business.dto.mapper.UserDTOMapper;
import ro.msg.edu.persistence.user.entity.User;

@Stateless
public class UserEjb {

	@EJB
	private UserDao userDao;

	@PersistenceContext(unitName = "jbugs-persistence")
	private EntityManager em;

	public UserDTO getUserByLastname(String lastname) {
		TypedQuery<User> query = em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class);
		query.setParameter("lastname", lastname);

		User user = query.getSingleResult();

		return new UserDTOMapper().mapToDTO(user);
	}

	public UserDTO createUser(UserDTO user) {
		User newUser = new User();
		new UserDTOMapper().mapToEntity(user, newUser);

		this.userDao.persistEntity(newUser);

		User persistedUser = this.userDao.findEntity(newUser.getId());

		return new UserDTOMapper().mapToDTO(persistedUser);
	}

}

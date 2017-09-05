package ro.msg.edu.business.user.boundary;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.control.UserCRUDControler;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.user.entity.User;

/**
 * Boundary for user component.
 * 
 * @author floricea
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserFacade {

	@EJB
	private UserCRUDControler userCRUD;

	public UserDTO createUser(UserDTO user) throws BusinessException {
		return userCRUD.createUser(user);
	}

	public User deleteUser(String username) {
		return userCRUD.deleteUser(username);

	}

	public User updateUser(String username, String firstname, String lastname, String email, String password,
			String phoneNumber) {
		return userCRUD.updateUser(username, firstname, lastname, email, password, phoneNumber);
	}

}

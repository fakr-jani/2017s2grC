package ro.msg.edu.business.user.boundary;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.control.UserCRUDControl;
import ro.msg.edu.business.user.dto.UserDTO;

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
	private UserCRUDControl userCRUDControl;

	public UserDTO createUser(UserDTO user) throws BusinessException {
		return userCRUDControl.createUser(user);
	}

	public UserDTO deleteUser(UserDTO userDTO) {
		return userCRUDControl.deleteUser(userDTO);

	}

	public UserDTO updateUser(UserDTO userDTO) throws BusinessException {
		return userCRUDControl.updateUser(userDTO);
	}

}

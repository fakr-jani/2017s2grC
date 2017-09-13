package ro.msg.edu.business.user.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.control.UserCRUDControl;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;

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

	@Inject
	private UserDTOMapper userDTOMapper;

	@EJB
	private UserDAO userDAO;


	public UserDTO createUser(UserDTO user) throws TechnicalException {
		return userCRUDControl.createUser(user);
	}


	public UserDTO deleteUser(UserDTO userDTO) throws TechnicalException {
		return userCRUDControl.deleteUser(userDTO);

	}


	public UserDTO activateUser(UserDTO userDTO) {
		return userCRUDControl.activateUser(userDTO);

	}


	public UserDTO updateUser(UserDTO userDTO) throws TechnicalException {
		return userCRUDControl.updateUser(userDTO);
	}


	public UserDTO findUserbyUsername(String username) {

		return userCRUDControl.findUserbyUsername(username);

	}


	public boolean verifyLoggedInUser(UserDTO user) {
		return userCRUDControl.verifyUserExists(user);

	}


	public List<UserDTO> findAllUsers() {
		return userCRUDControl.findAllUser();
	}

}

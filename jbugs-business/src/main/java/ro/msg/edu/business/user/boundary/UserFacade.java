package ro.msg.edu.business.user.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.control.UserCRUDControl;
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

	@EJB
	private UserDTOMapper userDTOMapper;

	public UserDTO createUser(UserDTO user, String[] selectedRoles) throws TechnicalException {
		return userCRUDControl.createUser(user, selectedRoles);
	}

	public UserDTO deleteUser(UserDTO userDTO) throws TechnicalException {
		return userCRUDControl.deleteUser(userDTO);

	}

	public UserDTO activateUser(UserDTO userDTO) {
		return userCRUDControl.activateUser(userDTO);

	}

	public UserDTO updateUser(UserDTO userDTO,List<String> updateRoles) throws TechnicalException {
		return userCRUDControl.updateUser(userDTO,updateRoles);
	}

	public UserDTO findUserbyUsername(String username) {

		return userCRUDControl.findUserbyUsername(username);

	}

	public boolean verifyLoggedInUser(UserDTO user) {
		return userCRUDControl.verifyUserExists(user);

	}

	public UserDTO setStatus(UserDTO user) {
		return userCRUDControl.setStatus(user);

	}

	public UserDTO resetStatus(UserDTO user) {
		return userCRUDControl.resetStatus(user);

	}

	public List<UserDTO> findAllUsers() {
		return userCRUDControl.findAllUser();
	}

	public boolean hasActiveTasks(UserDTO userDTO) {
		return userCRUDControl.hasActiveTasks(userDTO);
	}
	

}

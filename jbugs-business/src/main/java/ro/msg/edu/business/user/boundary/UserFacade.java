package ro.msg.edu.business.user.boundary;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
<<<<<<< HEAD
import javax.inject.Inject;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.control.UserCRUDControl;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
=======

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.control.UserCRUDControl;
import ro.msg.edu.business.user.dto.UserDTO;
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67

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

<<<<<<< HEAD
	@Inject
	private UserDTOMapper userDTOMapper;

	@EJB
	private UserDAO userDAO;

	public UserDTO createUser(UserDTO user) throws TechnicalException {
=======
	public UserDTO createUser(UserDTO user) throws BusinessException {
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
		return userCRUDControl.createUser(user);
	}

	public UserDTO deleteUser(UserDTO userDTO) {
		return userCRUDControl.deleteUser(userDTO);

	}

<<<<<<< HEAD
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

=======
	public UserDTO updateUser(UserDTO userDTO) throws BusinessException {
		return userCRUDControl.updateUser(userDTO);
	}

>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
}

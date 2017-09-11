package ro.msg.edu.business.user.control;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
import ro.msg.edu.business.user.validator.UserValidator;
import ro.msg.edu.persistence.user.entity.User;

/**
 * Controller for User component.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
@Stateless
public class UserCRUDControl {

	@Inject
	private UserDTOMapper userDTOMapper;

	@EJB
	private UserDAO userDAO;

	@Inject
	UserValidator userValidator;

	public UserDTO createUser(UserDTO user) throws BusinessException {
		userValidator.validateUserData(user);

		User userEntity = new User();
		userDTOMapper.mapToEntity(user, userEntity);

		userEntity.setActive(true);

		userDAO.persistEntity(userEntity);
		User persistedUser = userDAO.findEntity(userEntity.getId());
		return userDTOMapper.mapToDTO(persistedUser);
	}

	public UserDTO deleteUser(UserDTO userDTO) {
		Optional<User> userOptional = userDAO.findUserByUsername(userDTO.getUsername());
		User userEntity = userOptional.get();
		if (userValidator.checkIfUserHasActiveTasks(userEntity) == false) {
			userEntity.setActive(false);
		}
		return userDTOMapper.mapToDTO(userEntity);
	}

	public UserDTO updateUser(UserDTO userToUpdate) throws BusinessException {
		Optional<User> userOptional = userDAO.findUserByUsername(userToUpdate.getUsername());
		User entity = userOptional.get();

		if (userToUpdate.getEmail() != null && userValidator.validateEmail(userToUpdate.getEmail()))
			entity.setEmail(userToUpdate.getEmail());

		if (userToUpdate.getFirstname() != null)
			entity.setFirstname(userToUpdate.getFirstname());

		if (userToUpdate.getLastname() != null)
			entity.setLastname(userToUpdate.getLastname());

		if (userToUpdate.getPassword() != null)
			entity.setPassword(userToUpdate.getPassword());

		if (userToUpdate.getPhoneNumber() != null)
			entity.setPhoneNumber(userToUpdate.getPhoneNumber());

		return userDTOMapper.mapToDTO(entity);
	}

	public UserDTO findUserbyUsername(String username) {
		Optional<User> entity = userDAO.findUserByUsername(username);

		if (entity.isPresent()) {
			return userDTOMapper.mapToDTO(entity.get());
		} else
			return null;

	}

}

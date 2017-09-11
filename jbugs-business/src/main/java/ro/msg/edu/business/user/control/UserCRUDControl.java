package ro.msg.edu.business.user.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
import ro.msg.edu.business.user.validator.UserValidator;
import ro.msg.edu.persistence.user.entity.User;

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
		User userEntity = userDAO.findUserByUsername(userDTO.getUsername());
		if (userValidator.checkIfUserHasActiveTasks(userEntity) == false) {
			userEntity.setActive(false);
		}
		return userDTOMapper.mapToDTO(userEntity);
	}

	public UserDTO reactiveUser(UserDTO userDTO) {
		User userEntity = userDAO.findUserByUsername(userDTO.getUsername());
		if (userValidator.checkIfUserHasActiveTasks(userEntity) == false) {
			userEntity.setActive(true);
		}
		return userDTOMapper.mapToDTO(userEntity);
	}

	public UserDTO findUserByUsername(String username) {
		return userDTOMapper.mapToDTO(userDAO.findUserByUsername(username));
	}

	public boolean verifyUserExists(UserDTO user) {
		return userDAO.verifyUserExists(user.getUsername(), user.getPassword());
	}

	public UserDTO updateUser(UserDTO userToUpdate) throws BusinessException {
		User entity = userDAO.findUserByUsername(userToUpdate.getUsername());

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

	public List<UserDTO> getAll() {
		List<User> users = userDAO.getAll();
		List<UserDTO> listUserDTO = new ArrayList<>();
		for (User u : users) {
			listUserDTO.add(userDTOMapper.mapToDTO(u));
		}
		return listUserDTO;

	}
}

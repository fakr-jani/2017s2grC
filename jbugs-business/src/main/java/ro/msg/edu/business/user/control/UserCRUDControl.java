package ro.msg.edu.business.user.control;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.common.exception.TechnicalException;
=======
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ro.msg.edu.business.common.exception.BusinessException;
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
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

<<<<<<< HEAD
	@EJB
=======
	@Inject
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
	private UserDTOMapper userDTOMapper;

	@EJB
	private UserDAO userDAO;

<<<<<<< HEAD
	@EJB
	UserValidator userValidator;

	public UserDTO createUser(UserDTO user) throws TechnicalException {
		userValidator.validateUserData(user);
=======
	@Inject
	UserValidator userValidator;

	public UserDTO createUser(UserDTO user) throws BusinessException {
		validateUserData(user);
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67

		User userEntity = new User();
		userDTOMapper.mapToEntity(user, userEntity);

		userEntity.setActive(true);

<<<<<<< HEAD
		userEntity.setUsername(generateUsername(user.getFirstname(), user.getLastname()));
=======
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
		userDAO.persistEntity(userEntity);
		User persistedUser = userDAO.findEntity(userEntity.getId());
		return userDTOMapper.mapToDTO(persistedUser);
	}

	public UserDTO deleteUser(UserDTO userDTO) {
<<<<<<< HEAD
		Optional<User> userOptional = userDAO.findUserByUsername(userDTO.getUsername());
		User userEntity = userOptional.get();
=======
		User userEntity = userDAO.findUserByUsername(userDTO.getUsername());
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
		if (userValidator.checkIfUserHasActiveTasks(userEntity) == false) {
			userEntity.setActive(false);
		}
		return userDTOMapper.mapToDTO(userEntity);
	}

<<<<<<< HEAD
	public UserDTO activateUser(UserDTO userDTO) {
		Optional<User> userOptional = userDAO.findUserByUsername(userDTO.getUsername());
		User userEntity = userOptional.get();
		userEntity.setActive(true);
		return userDTOMapper.mapToDTO(userEntity);
	}

	public UserDTO updateUser(UserDTO userToUpdate) throws TechnicalException {
		Optional<User> userOptional = userDAO.findUserByUsername(userToUpdate.getUsername());
		User entity = userOptional.get();
=======
	public UserDTO updateUser(UserDTO userToUpdate) throws BusinessException {
		User entity = userDAO.findUserByUsername(userToUpdate.getUsername());
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67

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

<<<<<<< HEAD
	public UserDTO findUserbyUsername(String username) {
		Optional<User> entity = userDAO.findUserByUsername(username);

		if (entity.isPresent()) {
			return userDTOMapper.mapToDTO(entity.get());
		} else
			return null;

	}

	public boolean verifyUserExists(UserDTO user) {
		return userDAO.verifyUserExists(user.getUsername(), user.getPassword());
	}

	public List<UserDTO> findAllUser() {
		List<User> users = userDAO.getAllUser();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for (User u : users) {
			usersDTO.add(userDTOMapper.mapToDTO(u));
		}
		return usersDTO;
	}

	public String generateUsername(String firstname, String lastname) {

		StringBuilder username = new StringBuilder();
		int ok = 0;
		int i = 5;
		int j = 1;

		if (lastname.length() < 5) {
			i = lastname.length();
			j = 6 - i;
		}
		while (ok == 0) {

			username.append(lastname, 0, i);
			username.append(firstname, 0, j);
			if (userValidator.verifyUsernameExists(username) == false) {
				ok = 1;
			} else {
				username.delete(0, 6);
				i--;
				j++;
			}

		}
		return username.toString();
	}

=======
	private void validateUserData(UserDTO user) throws BusinessException {
		User existingUserWithSameEmail = userDAO.findUserByEmail(user.getEmail());
		if (existingUserWithSameEmail != null) {
			throw new BusinessException("User already exists with given email " + user.getEmail());
		}
	}
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
}

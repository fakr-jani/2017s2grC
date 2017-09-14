package ro.msg.edu.business.user.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.role.dao.RoleDAO;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
import ro.msg.edu.business.user.validator.UserValidator;
import ro.msg.edu.persistence.user.entity.Role;
import ro.msg.edu.persistence.user.entity.User;
import ro.msg.edu.persistence.user.entity.enums.RoleType;

/**
 * Controller for User component.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
@Stateless
public class UserCRUDControl {

	@EJB
	private UserDTOMapper userDTOMapper;

	@EJB
	private UserDAO userDAO;
	@EJB
	private RoleDAO roleDAO;

	@EJB
	UserValidator userValidator;

	public UserDTO createUser(UserDTO user, String[] selectedRoles) throws TechnicalException {
		userValidator.validateUserData(user);
		User userEntity = new User();
		userDTOMapper.mapToEntity(user, userEntity);

		userEntity.setActive(true);
		userEntity.setUsername(generateUsername(user.getFirstname(), user.getLastname()));
		List<Role> roles = new ArrayList<Role>();

		for (String role : selectedRoles) {

			roles.add(roleDAO.getRoleByName(RoleType.valueOf(role)).get(0));
		}
		userEntity.setRoles(roles);
		userDAO.persistEntity(userEntity);

		User persistedUser = userDAO.findEntity(userEntity.getId());

		return userDTOMapper.mapToDTO(persistedUser);
	}

	public UserDTO deleteUser(UserDTO userDTO) throws TechnicalException {
		Optional<User> userOptional = userDAO.findUserByUsername(userDTO.getUsername());
		User userEntity = userOptional.get();
		if (userEntity == null)
			throw new TechnicalException("User not found!");
		if (!userDAO.hasActiveTasks(userEntity)) {
			userEntity.setActive(false);
			return userDTOMapper.mapToDTO(userEntity);
		} else
			throw new TechnicalException("User has tasks assigned");

	}

	public UserDTO activateUser(UserDTO userDTO) {
		Optional<User> userOptional = userDAO.findUserByUsername(userDTO.getUsername());
		User userEntity = userOptional.get();
		userEntity.setActive(true);
		return userDTOMapper.mapToDTO(userEntity);
	}

	public UserDTO updateUser(UserDTO userToUpdate) throws TechnicalException {
		Optional<User> userOptional = userDAO.findUserByUsername(userToUpdate.getUsername());
		User entity = userOptional.get();
		userValidator.validateUserData(userToUpdate);

		entity.setEmail(userToUpdate.getEmail());
		entity.setFirstname(userToUpdate.getFirstname());
		entity.setLastname(userToUpdate.getLastname());
		entity.setPassword(userToUpdate.getPassword());
		entity.setPhoneNumber(userToUpdate.getPhoneNumber());
		entity.setActive(userToUpdate.isActive());

		return userDTOMapper.mapToDTO(entity);
	}

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
			if (userValidator.uniqueUsername(username) == false) {
				ok = 1;
			} else {
				username.delete(0, 6);
				i--;
				j++;
			}

		}
		return username.toString();
	}

	public boolean hasActiveTasks(UserDTO userDTO) {
		Optional<User> userOptional = userDAO.findUserByUsername(userDTO.getUsername());
		User entity = userOptional.get();

		return userDAO.hasActiveTasks(entity);
	}

}

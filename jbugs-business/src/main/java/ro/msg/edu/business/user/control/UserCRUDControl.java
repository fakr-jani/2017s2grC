package ro.msg.edu.business.user.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.permission.dao.PermissionDAO;
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

	@EJB
	private PermissionDAO permissionDAO;

	private static final int MAX_NUMBER_OF_TRIES = 4;
	private static final int MAX_CHARACTERTER_FROM_LASTNAME = 5;
	private static final int MAX_CHARACTERS_FOR_USERNAME = 6;

	public UserDTO createUser(UserDTO user, String[] selectedRoles) throws TechnicalException {
		userValidator.validateUserData(user);
		User userEntity = new User();
		userDTOMapper.mapToEntity(user, userEntity);

		userEntity.setActive(true);
		userEntity.setUsername(generateUsername(user.getFirstname(), user.getLastname()));
		List<Role> roles = new ArrayList<Role>();

		for (String role : selectedRoles) {

			roles.add(roleDAO.getRoleByName(RoleType.valueOf(role)));
		}
		userEntity.setRoles(roles);
		userDAO.persistEntity(userEntity);

		User persistedUser = userDAO.findEntity(userEntity.getId());

		return userDTOMapper.mapToDTO(persistedUser);
	}

	public UserDTO deleteUser(UserDTO userDTO) throws TechnicalException {
		Optional<User> userOptional = userDAO.findUserByUsername(userDTO.getUsername());
		if (isUserPresent(userOptional)) {
			User userEntity = userOptional.get();
			if (!userDAO.hasActiveTasks(userEntity)) {
				userEntity.setActive(false);
				return userDTOMapper.mapToDTO(userEntity);
			} else
				throw new TechnicalException("User has tasks assigned");
		} else
			throw new TechnicalException("User not found!");
	}

	public UserDTO activateUser(UserDTO userDTO) throws TechnicalException {
		Optional<User> userOptional = userDAO.findUserByUsername(userDTO.getUsername());
		if (isUserPresent(userOptional)) {
			User userEntity = userOptional.get();
			userEntity.setActive(true);
			return userDTOMapper.mapToDTO(userEntity);
		} else
			throw new TechnicalException("User not found!");
	}

	public UserDTO updateUser(UserDTO userToUpdate, List<String> updateRoles) throws TechnicalException {
		Optional<User> userOptional = userDAO.findUserByUsername(userToUpdate.getUsername());
		if (isUserPresent(userOptional)) {
			User entity = userOptional.get();
			List<Role> roleList = new ArrayList<>();

			for (String roleName : updateRoles) {
				roleList.add(roleDAO.getRoleByName(RoleType.valueOf(roleName)));
			}
			userValidator.validateUserData(userToUpdate);
			entity.setRoles(roleList);
			entity.setEmail(userToUpdate.getEmail());
			entity.setFirstname(userToUpdate.getFirstname());
			entity.setLastname(userToUpdate.getLastname());
			entity.setPassword(userToUpdate.getPassword());
			entity.setPhoneNumber(userToUpdate.getPhoneNumber());
			entity.setActive(userToUpdate.isActive());
			return userDTOMapper.mapToDTO(entity);
		} else
			throw new TechnicalException("User not found!");
	}

	public UserDTO findUserbyUsername(String username) {
		Optional<User> entity = userDAO.findUserByUsername(username);
		if (isUserPresent(entity)) {
			return userDTOMapper.mapToDTO(entity.get());
		} else
			return null;

	}

	public boolean verifyUserExists(UserDTO user) {
		return userDAO.verifyUserExists(user.getUsername(), user.getPassword());
	}

	public List<UserDTO> findAllUser() {
		List<User> users = userDAO.getAllUser();
		return userDTOMapper.mapToDTOs(users);

	}

	private String generateUsername(String firstname, String lastname) throws TechnicalException {

		int nrLastnameCharacters = calculateNrOfCharactersFromLastname(lastname);
		int nrFirstnameCharacters = calculateNrOfCharactersFromFirstname(lastname);

		while (nrLastnameCharacters > 0 && nrFirstnameCharacters < MAX_CHARACTERS_FOR_USERNAME) {
			StringBuilder username = new StringBuilder();
			username.append(lastname, 0, nrLastnameCharacters);
			username.append(firstname, 0, nrFirstnameCharacters);
			boolean usernameNotExists = userValidator.uniqueUsername(username);
			if (usernameNotExists) {
				return username.toString();
			}
			nrLastnameCharacters--;
			nrFirstnameCharacters++;
		}
		throw new TechnicalException("Cannot generate username. Please insert your middle name");
	}

	private int calculateNrOfCharactersFromLastname(String lastname) {
		if (lastname.length() < MAX_CHARACTERTER_FROM_LASTNAME) {

			return lastname.length();
		}
		return MAX_CHARACTERTER_FROM_LASTNAME;

	}

	private int calculateNrOfCharactersFromFirstname(String lastname) {
		if (lastname.length() < MAX_CHARACTERTER_FROM_LASTNAME) {
			return MAX_CHARACTERS_FOR_USERNAME - lastname.length();
		}
		return 1;
	}

	public boolean hasActiveTasks(UserDTO userDTO) {
		Optional<User> userOptional = userDAO.findUserByUsername(userDTO.getUsername());
		if (isUserPresent(userOptional)) {
			User entity = userOptional.get();
			return userDAO.hasActiveTasks(entity);
		} else
			return false;
	}

	public UserDTO setStatus(UserDTO userDTO) {

		Optional<User> entity = userDAO.findUserByUsername(userDTO.getUsername());
		if (isUserPresent(entity)) {
			User userEntity = entity.get();
			userEntity.setCounter(userEntity.getCounter() + 1);
			if (userEntity.getCounter() > MAX_NUMBER_OF_TRIES) {
				userEntity.setActive(false);
			}

			return userDTOMapper.mapToDTO(userEntity);
		} else
			return userDTO;

	}

	public UserDTO resetStatus(UserDTO userDTO) {

		Optional<User> entity = userDAO.findUserByUsername(userDTO.getUsername());
		if (isUserPresent(entity)) {
			User userEntity = entity.get();
			userEntity.setCounter(0);
			return userDTOMapper.mapToDTO(userEntity);
		} else {
			return userDTO;
		}
	}

	private boolean isUserPresent(Optional<User> optionalUser) {
		return optionalUser.isPresent();
	}

}

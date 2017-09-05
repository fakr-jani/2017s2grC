package ro.msg.edu.business.user.control;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
import ro.msg.edu.persistence.user.entity.User;

/**
 * Controller for User component.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
@Stateless
public class UserCRUDControler {

	@Inject
	private UserDTOMapper userDTOMapper;

	@EJB
	private UserDAO userDAO;

	public UserDTO createUser(UserDTO user) throws BusinessException {
		validateUserData(user);

		User userEntity = new User();
		userDTOMapper.mapToEntity(user, userEntity);

		userEntity.setActive(true);

		userDAO.persistEntity(userEntity);
		User persistedUser = userDAO.findEntity(userEntity.getId());
		return userDTOMapper.mapToDTO(persistedUser);
	}

	public User deleteUser(String username) {
		User userEntity = userDAO.findUserByUsername(username);
		userEntity.setActive(false);
		userDAO.update(userEntity);
		User persistedUser = userDAO.findEntity(userEntity.getId());
		return persistedUser;
	}

	public User updateUser(String username, String firstname, String lastname, String email, String password,
			String phoneNumber) {
		User userEntity = userDAO.findUserByUsername(username);
		userEntity.setFirstname(firstname);
		userEntity.setLastname(lastname);
		userEntity.setEmail(email);
		userEntity.setPassword(password);
		userEntity.setPhoneNumber(phoneNumber);
		userDAO.update(userEntity);
		User persistedUser = userDAO.findEntity(userEntity.getId());
		return persistedUser;

	}

	private void validateUserData(UserDTO user) throws BusinessException {
		User existingUserWithSameEmail = userDAO.findUserByEmail(user.getEmail());
		if (existingUserWithSameEmail != null) {
			throw new BusinessException("User already exists with given email " + user.getEmail());
		}
	}

}

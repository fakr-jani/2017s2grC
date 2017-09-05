package ro.msg.edu.business.user.control;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.common.validator.UserValidator;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
import ro.msg.edu.persistence.user.entity.User;

/**
 * 
 * @author maresb
 *
 */
@Stateless
public class UserCRUDContol {

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

		userEntity.setUsername(generateUsername(user.getFirstname(), user.getLastname()));
		userEntity.setActive(true);

		userDAO.persistEntity(userEntity);
		User persistedUser = userDAO.findEntity(userEntity.getId());
		return userDTOMapper.mapToDTO(persistedUser);
	}

	public UserDTO deleteUser(UserDTO user) throws BusinessException {

		User userEntity = userDAO.findEntity(user.getId());

		if (userValidator.checkIfUserHasActiveTasks(userEntity) == false) {

			userEntity.setActive(false);
			return userDTOMapper.mapToDTO(userEntity);

		} else
			throw new BusinessException("User has not finished tasks");

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

	private String generateUsername(String lastname, String firstname) {
		StringBuilder username = new StringBuilder(6);

		// init i=5 j=1
		// get the first i letters from lastname get j letters form firstname
		// verify
		// if exist i--, j++

		return null;

	}

}

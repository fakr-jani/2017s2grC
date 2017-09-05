package ro.msg.edu.business.common.validator;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.user.entity.User;

@Dependent
public class UserValidator {

	@EJB
	private UserDAO userDAO;

	public boolean checkIfUserHasActiveTasks(User entity) {

		// get list of users bugs
		// if null return false
		// else check for every bug if status not equals CLOSED then return true
		return false;
	}

	public void validateUserData(UserDTO user) throws BusinessException {
		User existingUserWithSameEmail = userDAO.findUserByEmail(user.getEmail());
		if (existingUserWithSameEmail != null) {
			throw new BusinessException("User already exists with given email " + user.getEmail());
		}
	}

	public boolean validateEmail(String email) throws BusinessException {
		User existingUserWithSameEmail = userDAO.findUserByEmail(email);
		if (existingUserWithSameEmail != null) {
			throw new BusinessException("User already exists with given email " + email);
		}
		return true;
	}
}

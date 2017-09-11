package ro.msg.edu.business.user.validator;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.user.entity.User;

@Stateless
public class UserValidator {

	@EJB
	private UserDAO userDAO;

	public boolean checkIfUserHasActiveTasks(User entity) {

		// get list of users bugs
		// if null return false
		// else check for every bug if status not equals CLOSED then return true
		return false;
	}

	public void validateUserData(UserDTO user) throws TechnicalException {
		User existingUserWithSameEmail = userDAO.findUserByEmail(user.getEmail());
		if (existingUserWithSameEmail != null) {
			throw new TechnicalException("User already exists with given email " + user.getEmail());
		}
		if (user.getEmail().endsWith("@msggroup.com") == false) {
			throw new TechnicalException("Email does not have the standard format " + user.getEmail());
		}
	}

	public boolean validateEmail(String email) throws TechnicalException {
		User existingUserWithSameEmail = userDAO.findUserByEmail(email);
		if (existingUserWithSameEmail != null) {
			throw new TechnicalException("User already exists with given email " + email);
		}
		if (email.endsWith("@msggroup.com") == false) {
			throw new TechnicalException("Email does not have the standard format " + email);
		}
		return true;
	}

	public boolean verifyUsernameExists(StringBuilder username) {
		Optional<User> user = userDAO.findUserByUsername(username.toString());
		if (user.get().getId() == null) {
			return false;
		} else
			return true;
	}

}

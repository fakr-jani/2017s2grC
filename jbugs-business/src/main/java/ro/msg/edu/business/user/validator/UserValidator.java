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


	public void validateUserData(UserDTO userDTO) throws TechnicalException {
		validateEmail(userDTO);
		validateFirstName(userDTO);
		validateLastName(userDTO);
	}


	public void validateEmail(UserDTO userDTO) throws TechnicalException {
		User existingUserWithSameEmail = userDAO.findUserByEmail(userDTO.getEmail());
		if (existingUserWithSameEmail != null && existingUserWithSameEmail.getId() != userDTO.getId()) {
			throw new TechnicalException("User already exists with given email " + userDTO.getEmail());
		} else if (userDTO.getEmail().endsWith("@msggroup.com") == false) {
			throw new TechnicalException("Email does not have the standard format " + userDTO.getEmail());
		}

	}


	public void validateFirstName(UserDTO userDTO) throws TechnicalException {
		if (userDTO.getFirstname() == null)
			throw new TechnicalException("Firstname cannot be null!");
	}


	public void validateLastName(UserDTO userDTO) throws TechnicalException {
		if (userDTO.getLastname() == null)
			throw new TechnicalException("Lastname cannot be null!");
	}


	public void validatePhoneNumber(UserDTO userDTO) throws TechnicalException {
		if (!(userDTO.getPhoneNumber().startsWith("+40") || userDTO.getPhoneNumber().startsWith("+49"))) {
			throw new TechnicalException("Not a valid phone number");
		}

	}


	public boolean uniqueUsername(StringBuilder username) {
		Optional<User> user = userDAO.findUserByUsername(username.toString());
		if (user.get().getId() == null) {
			return false;
		} else
			return true;
	}

}

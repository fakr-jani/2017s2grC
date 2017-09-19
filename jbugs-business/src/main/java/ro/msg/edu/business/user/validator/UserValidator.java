package ro.msg.edu.business.user.validator;

import java.io.Serializable;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.user.entity.User;

@Stateless
public class UserValidator implements Serializable{

	@EJB
	private UserDAO userDAO;
	private static final String MATCHER_PHONE_GERMANY = "+49";
	private static final String MATCHER_PHONE_ROMANIA = "+40";
	private static final String MATCHER_EMAIL = "@msggroup.com";
	private static final String REGEX = "^(\\+)[0-9]+";
	private static final int MIN_NUMBER_CHARACTERS_FOR_NAME = 3;

	public void validateUserData(UserDTO userDTO) throws TechnicalException {
		validateFirstName(userDTO);
		validateLastName(userDTO);
		validateEmail(userDTO);
		validatePhoneNumber(userDTO);
	}

	public void validateEmail(UserDTO userDTO) throws TechnicalException {
		User existingUserWithSameEmail = userDAO.findUserByEmail(userDTO.getEmail());
		if (existingUserWithSameEmail != null && existingUserWithSameEmail.getId() != userDTO.getId()) {
			throw new TechnicalException("User already exists with given email " + userDTO.getEmail());
		} else if (!userDTO.getEmail().endsWith(MATCHER_EMAIL)) {
			throw new TechnicalException("Email does not have the standard format " + userDTO.getEmail());
		}

	}

	public void validateFirstName(UserDTO userDTO) throws TechnicalException {
		if (userDTO.getFirstname() == null || userDTO.getFirstname().length() < MIN_NUMBER_CHARACTERS_FOR_NAME)
			throw new TechnicalException("Invalid Firstname");
	}

	public void validateLastName(UserDTO userDTO) throws TechnicalException {
		if (userDTO.getLastname() == null || userDTO.getLastname().length() < MIN_NUMBER_CHARACTERS_FOR_NAME)
			throw new TechnicalException("Invalid Lastname");
	}

	public void validatePhoneNumber(UserDTO userDTO) throws TechnicalException {
		if (!userDTO.getPhoneNumber().startsWith(MATCHER_PHONE_ROMANIA)
				&& !userDTO.getPhoneNumber().startsWith(MATCHER_PHONE_GERMANY)) {
			throw new TechnicalException("Phone number is invalid! Please insert a germany phone or romania");
		} else if (!userDTO.getPhoneNumber().matches(REGEX)) {
			throw new TechnicalException("Phone number must have only digits");

		}

	}

	public boolean uniqueUsername(StringBuilder username) {
		Optional<User> user = userDAO.findUserByUsername(username.toString());
		return user.isPresent() && user.get().getId() != null;
	}
}

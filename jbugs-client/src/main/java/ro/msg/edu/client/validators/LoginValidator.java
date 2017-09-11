package ro.msg.edu.client.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("usernameValidator")
public class LoginValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {

		String stringValue = arg2.toString();

		if (stringValue.length() != 6) {
			FacesMessage message = new FacesMessage("Username must have 6 characters");
			throw new ValidatorException(message);
		}

	}

}

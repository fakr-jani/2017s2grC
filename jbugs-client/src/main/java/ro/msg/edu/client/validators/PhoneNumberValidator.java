package ro.msg.edu.client.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("phoneNumberValidator")
public class PhoneNumberValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String stringValue = value.toString();
<<<<<<< HEAD
=======

>>>>>>> Id8: Edit user
		if (stringValue.length() < 10 && stringValue.length() > 15 && !(stringValue.startsWith("+49"))
				&& !(stringValue.startsWith("+40"))) {
			String phoneValidationMessage = context.getApplication().evaluateExpressionGet(context,
					"#{msg['validator.phone]}", String.class);
			FacesMessage message = new FacesMessage(phoneValidationMessage);
			throw new ValidatorException(message);
		}

	}

}

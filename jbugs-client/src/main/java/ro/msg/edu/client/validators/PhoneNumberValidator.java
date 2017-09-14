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
		System.err.println(stringValue + "lkfhdkdgfkhs gbcrgubtgheehcwebgfyhh");

		if (stringValue.length() < 10 && stringValue.length() > 15 && !(stringValue.startsWith("+49"))
				&& !(stringValue.startsWith("+40"))) {
			FacesMessage message = new FacesMessage("Not a valid phone number");
			throw new ValidatorException(message);
		}

	}

}

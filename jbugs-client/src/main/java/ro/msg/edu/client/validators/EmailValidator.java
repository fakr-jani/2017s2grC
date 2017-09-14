package ro.msg.edu.client.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String stringValue = value.toString();

		if (!(stringValue.endsWith("@msggroup.com"))) {
			FacesMessage message = new FacesMessage("Not a valid email address!");
			throw new ValidatorException(message);

		}
	}
}

package ro.msg.edu.client.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("nameValidator")
public class NameValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String name = value.toString();
		if (name == null || name.length() < 3) {
			String nameValidationMessage = context.getApplication().evaluateExpressionGet(context,
					"#{msg['validator.name]}", String.class);
			FacesMessage message = new FacesMessage(nameValidationMessage);
			context.addMessage(null, message);
		}
	}

}

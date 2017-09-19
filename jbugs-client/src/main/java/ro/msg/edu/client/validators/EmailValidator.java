package ro.msg.edu.client.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) {
		String stringValue = value.toString();
		if (!(stringValue.endsWith("@msggroup.com"))) {
			String emailValidationMessage = context.getApplication().evaluateExpressionGet(context,
					"#{msg['validator.email']}", String.class);
			FacesMessage message = new FacesMessage(emailValidationMessage);
			context.addMessage(null, message);
		}
	}
}

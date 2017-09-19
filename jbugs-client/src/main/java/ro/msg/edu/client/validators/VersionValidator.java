/**
 * 
 */
package ro.msg.edu.client.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

/**
 * 
 * @author Alex Noja
 * 
 */
@FacesValidator("versionValidator")
public class VersionValidator implements Validator {

	public static final String VALID_BUG_VERSION_REGEX = "^[a-zA-Z0-9]{2,100}[.]{1}[a-zA-Z0-9]{1,100}$";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) {
		String stringValue = value.toString();
		if (!stringValue.matches(VALID_BUG_VERSION_REGEX)) {
			String versionValidationMessage= context.getApplication().evaluateExpressionGet(context,
					"#{msg['validator.version']}", String.class);
			FacesMessage message = new FacesMessage(versionValidationMessage);
			context.addMessage(null, message);
		}

	}

}

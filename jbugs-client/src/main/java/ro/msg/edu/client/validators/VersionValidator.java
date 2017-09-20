/**
 * 
 */
package ro.msg.edu.client.validators;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * 
 * @author Alex Noja
 * 
 */
@FacesValidator("versionValidator")
public class VersionValidator implements Validator {

	public static final String VALID_BUG_VERSION_REGEX = "^[0-9]{0,15}.[0-9]{0,15}$";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) {
		String stringValue = value.toString();

		boolean res = Pattern.matches(VALID_BUG_VERSION_REGEX, stringValue);
		if(!res){
			String versionValidationMessage = context.getApplication().evaluateExpressionGet(context, "#{msg['validator.version']}", String.class);
			FacesMessage message = new FacesMessage(versionValidationMessage);
			throw new ValidatorException(message);
		}

}



}

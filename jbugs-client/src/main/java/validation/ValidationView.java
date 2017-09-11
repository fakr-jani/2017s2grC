package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validationView")
public class ValidationView implements Validator {

	private static final String USERNAME_PATTERN = "^[A-Za-z0-9]+$";

	private Pattern pattern;
	private Matcher matcher;

	public ValidationView() {
		pattern = Pattern.compile(USERNAME_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {

			FacesMessage msg = new FacesMessage("Username validation failed.", "Invalid username format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}
}
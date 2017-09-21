package ro.msg.edu.client.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

@FacesValidator("phoneNumberValidator")
public class PhoneNumberValidator implements Validator {
	private static final String REGEX = "^(\\+)" + "[0-9]$";
	private static final String MATCHER_PHONE_GERMANY = "+49";
	private static final String MATCHER_PHONE_ROMANIA = "+40";
	private static final int MIN_NUMBER_DIGITS_PHONE = 10;
	private static final int MAX_NUMBER_DIGITS_PHONE = 15;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) {
		String stringValue = value.toString();
		if (stringValue.length() >= MIN_NUMBER_DIGITS_PHONE && stringValue.length() <= MAX_NUMBER_DIGITS_PHONE
				&& !(stringValue.startsWith(MATCHER_PHONE_GERMANY)) && !(stringValue.startsWith(MATCHER_PHONE_ROMANIA))
				&& !(stringValue.matches(REGEX))) {
			String phoneValidationMessage = context.getApplication().evaluateExpressionGet(context,
					"#{msg['validator.phone']}", String.class);
			FacesMessage message = new FacesMessage(phoneValidationMessage);
			context.addMessage(null, message);
		}

	}

}

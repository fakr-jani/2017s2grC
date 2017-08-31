package interceptor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserAuthenticatorAnnotated {

	public boolean loginUserNoParam() {
		return true;
	}

	@NotNullable
	public boolean loginUserWithOneParam(Object user) {
		return true;
	}

	public boolean loginUserWithMultipleParam(Object user, boolean secured) {
		return true;
	}
}

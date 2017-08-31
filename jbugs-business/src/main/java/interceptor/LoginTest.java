package interceptor;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginTest {

	@Inject
	UserAuthenticatorAnnotated userAuthenticator;

	@Schedule(second = "10", minute = "*", hour = "*")
	public void loginWithOneParamNotNull() {
		System.out.println("loginWithOneParamNotNull::");
		userAuthenticator.loginUserWithOneParam(new Object());
		System.out.println("::loginWithOneParamNotNull");
	}

	@Schedule(second = "20", minute = "*", hour = "*")
	public void loginWithOneParamNull() {
		System.out.println("loginWithOneParamNull::");
		userAuthenticator.loginUserWithOneParam(null);
		System.out.println("::loginWithOneParamNull");
	}

	@Schedule(second = "30", minute = "*", hour = "*")
	public void loginWithNoParam() {
		System.out.println("loginWithNoParam::");
		userAuthenticator.loginUserNoParam();
		System.out.println("::loginWithNoParam");
	}

	@Schedule(second = "40", minute = "*", hour = "*")
	public void loginUserWithMultipleParam() {
		System.out.println("loginUserWithMultipleParam::");
		userAuthenticator.loginUserWithMultipleParam(new Object(), false);
		System.out.println("::loginUserWithMultipleParam");
	}
}

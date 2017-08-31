package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@NotNullable
@Interceptor
public class NotNullableInterceptor {

	@AroundInvoke
	public Object checkObjectForNull(InvocationContext ic) {
		Object[] parameters = ic.getParameters();
		System.out.println("INTERCEPTOR CALLED");
		if (parameters.length == 0 || parameters[0] == null) {
			throw new NullPointerException("Parameter should not be empty or null");
		}
		try {
			return ic.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

import java.util.Properties;

import entities.User;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import operations.UserDAO;

public class Test {

	public static void main(String[] args) throws NamingException {
		getRemote();
	}

	public static void getRemote() throws NamingException {
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
		Context ctx = new InitialContext(jndiProps);
		Object o = ctx.lookup("UserDAOImpl");
		UserDAO u = (UserDAO) PortableRemoteObject.narrow(o, UserDAO.class);
		User uu = new User();
		uu.setEmail("kudsh");
		uu.setUserName("blablabla");
		u.persistUser(uu);
	}
}

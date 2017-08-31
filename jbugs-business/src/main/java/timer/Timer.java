package timer;

import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import operations.RoleDAO;

@Stateless
@Startup
public class Timer {
	@EJB
	RoleDAO rdi;

	// @Schedule(second = "*/10", minute = "*", hour = "*")
	// public void doShowTime() {
	// System.out.println("Role list: " + rdi.listAllRoles());
	// }

}

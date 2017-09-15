package ro.msg.edu.business.role.control;

import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;

public class RoleControlTest extends AbstractIntegrationTest {

	@EJB
	private RoleControl roleControl;

	@Test
	public void viewPermissions_Succes() {
		String roleName = "ADMINISTRATOR";
		List<String> permissions = roleControl.viewPermissions(roleName);
		Assert.assertEquals(permissions.size(), 1);
	}
}

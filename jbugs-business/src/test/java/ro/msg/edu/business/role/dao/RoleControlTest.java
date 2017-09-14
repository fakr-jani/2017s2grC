package ro.msg.edu.business.role.dao;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.persistence.user.entity.Role;

public class RoleControlTest extends AbstractIntegrationTest {

	@EJB
	private RoleDAO roleDAO;

	@Test
	public void findRoleByName_Success() {
		String testRoleName = "TESTER";
		Role role = roleDAO.findRoleByName(testRoleName);
		Assert.assertEquals(role.getRoleName(), testRoleName);
	}
}

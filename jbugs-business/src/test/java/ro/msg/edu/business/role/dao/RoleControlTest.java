package ro.msg.edu.business.role.dao;

import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.persistence.user.entity.Role;
import ro.msg.edu.persistence.user.entity.enums.RoleType;

public class RoleControlTest extends AbstractIntegrationTest {

	@EJB
	private RoleDAO roleDAO;

	@Test
	public void findRoleByName_Success() {
		Role role = roleDAO.getRoleByName(RoleType.TESTER);
		Assert.assertEquals(role.getRoleName(), RoleType.TESTER);
	}
}

package ro.msg.edu.business.permission.dao;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.persistence.user.entity.Permission;

public class PermissionControlTest extends AbstractIntegrationTest {

	@EJB
	private PermissionDAO permissionDAO;

	@Test
	public void findPermissionByName_Success() {
		String testPermissionName = "PERMISSION_MANAGMENT";
		Permission permission = permissionDAO.findPermissionByName(testPermissionName);
		Assert.assertEquals(permission.getNamePermission(), testPermissionName);
	}
}

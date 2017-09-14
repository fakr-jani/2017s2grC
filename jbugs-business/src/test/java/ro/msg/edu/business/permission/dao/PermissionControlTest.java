package ro.msg.edu.business.permission.dao;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.persistence.user.entity.Permission;
import ro.msg.edu.persistence.user.entity.enums.PermissionType;

public class PermissionControlTest extends AbstractIntegrationTest {

	@EJB
	private PermissionDAO permissionDAO;

	@Test
	public void findPermissionByName_Success() {
		Permission permission = permissionDAO.findPermissionByName(PermissionType.BUG_CLOSE);
		Assert.assertEquals(permission.getNamePermission(), PermissionType.BUG_CLOSE);
	}
}

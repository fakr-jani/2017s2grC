
package ro.msg.edu.business.user.dao;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.persistence.user.entity.enums.PermissionType;

public class UserDAOTest extends AbstractIntegrationTest {

	@EJB
	UserDAO sut;

	@Test
	public void hasPermission_Success() {
		String username = "SmithS";
		String permission = "USER_MANAGEMENT";
		boolean result = sut.hasPermission(username, PermissionType.valueOf(permission));
		Assert.assertEquals(true, result);
	}

	@Test
	public void hasPermission_Failer() {
		String username = "SmithK";
		String permission = "USER_MANAGEMENT";
		boolean result = sut.hasPermission(username, PermissionType.valueOf(permission));
		Assert.assertEquals(false, result);
	}
}
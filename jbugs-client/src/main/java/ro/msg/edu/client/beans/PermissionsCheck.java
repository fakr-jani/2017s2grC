package ro.msg.edu.client.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.persistence.user.entity.enums.PermissionType;

@Stateless
public class PermissionsCheck {
	@EJB
	UserFacade userFacade;

	public boolean verifyPermissionRendered(String userName, String permissionType) {
		if (userName == null) {
			return false;
		}
		return userFacade.hasPermission(userName, PermissionType.valueOf(permissionType));
	}
}

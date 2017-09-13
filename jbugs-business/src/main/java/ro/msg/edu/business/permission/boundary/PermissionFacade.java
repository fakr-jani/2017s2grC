package ro.msg.edu.business.permission.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ro.msg.edu.business.permission.control.PermissionControl;
import ro.msg.edu.business.permission.dto.PermissionDTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PermissionFacade {

	@EJB
	private PermissionControl permissionControl;

	public List<PermissionDTO> findAllPermissions() {
		return permissionControl.findAllPermission();
	}
}

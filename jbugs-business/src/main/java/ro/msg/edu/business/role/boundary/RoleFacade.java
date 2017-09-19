package ro.msg.edu.business.role.boundary;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ro.msg.edu.business.role.control.RoleControl;
import ro.msg.edu.business.role.dto.RoleDTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class RoleFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private RoleControl roleControl;

	public List<RoleDTO> findAllRoles() {
		return roleControl.findAllRoles();
	}

	public RoleDTO addPermissions(String selectedRole, String[] selectedPermissions) {
		return roleControl.addPermissions(selectedRole, selectedPermissions);
	}

	public List<String> viewPermissions(String selectedRole) {
		return roleControl.viewPermissions(selectedRole);
	}
}

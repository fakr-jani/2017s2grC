package ro.msg.edu.business.role.dto;

import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.persistence.user.entity.enums.RoleType;

public class RoleDTO extends AbstractDTO {
	private RoleType roleName;

	public RoleType getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleType rolename) {
		this.roleName = rolename;
	}

	@Override
	public String toString() {
		return "Role Name: " + roleName;
	}
}

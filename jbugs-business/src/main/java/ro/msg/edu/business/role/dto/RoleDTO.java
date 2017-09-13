package ro.msg.edu.business.role.dto;

import ro.msg.edu.business.common.dto.AbstractDTO;

public class RoleDTO extends AbstractDTO {
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String rolename) {
		this.roleName = rolename;
	}

	@Override
	public String toString() {
		return roleName;
	}
}

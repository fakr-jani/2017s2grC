package ro.msg.edu.business.permission.dto;

import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.persistence.user.entity.enums.PermissionType;

public class PermissionDTO extends AbstractDTO {

	private Long idPermission;
	private PermissionType namePermission;
	private String detailPermission;

	public Long getIdPermission() {
		return idPermission;
	}

	public void setIdPermission(Long idPermission) {
		this.idPermission = idPermission;
	}

	public PermissionType getNamePermission() {
		return namePermission;
	}

	public void setNamePermission(PermissionType namePermission) {
		this.namePermission = namePermission;
	}

	public String getDetailPermission() {
		return detailPermission;
	}

	public void setDetailPermission(String detailPermission) {
		this.detailPermission = detailPermission;
	}

	@Override
	public String toString() {
		return "PermissionName: " + namePermission;
	}

}

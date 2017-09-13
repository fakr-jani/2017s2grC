package ro.msg.edu.business.permission.dto;

import ro.msg.edu.business.common.dto.AbstractDTO;

public class PermissionDTO extends AbstractDTO {

	private Long idPermission;
	private String namePermission;
	private String detailPermission;

	public Long getIdPermission() {
		return idPermission;
	}

	public void setIdPermission(Long idPermission) {
		this.idPermission = idPermission;
	}

	public String getNamePermission() {
		return namePermission;
	}

	public void setNamePermission(String namePermission) {
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
		return "PermissionDTO [idPermission=" + idPermission + ", namePermission=" + namePermission
				+ ", detailPermission=" + detailPermission + "]";
	}

}

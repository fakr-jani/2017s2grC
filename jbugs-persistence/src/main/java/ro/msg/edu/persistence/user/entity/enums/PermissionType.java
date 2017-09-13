package ro.msg.edu.persistence.user.entity.enums;

public enum PermissionType {

	PERMISSION_MANAGEMENT("PERMISSION_MANAGEMENT"), USER_MANAGEMENT("USER_MANAGEMENT"), BUG_MANAGEMENT(
			"BUG_MANAGEMENT"), BUG_CLOSE("BUG_CLOSE"), BUG_EXPORT_PDF("BUG_EXPORT_PDF");

	private final String permission;

	private PermissionType(final String permission) {
		this.permission = permission;
	}
}

package ro.msg.edu.persistence.user.entity.enums;

public enum RoleType {
	ADMINISTRATOR("ADMINISTRATOR"), PROJECT_MANAGER("PROJECT_MANAGER"), TEST_MANAGER("TEST_MANAGER"), DEVELOPER(
			"DEVELOPER"), TESTER("TESTER");

	private final String role;

	private RoleType(String role) {
		this.role = role;
	}
}

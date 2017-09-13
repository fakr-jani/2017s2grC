package ro.msg.edu.persistence.bug.entity.enums;

public enum BugStatusType {
	OPEN("OPEN"), IN_PROGRESS("IN_PROGRESS"), FIXED("FIXED"), INFO_NEEDED("INFO_NEEDED"), REJECTED(
			"INFO_NEEDED"), CLOSED("CLOSED");

	private final String statusType;

	private BugStatusType(String statusType) {
		this.statusType = statusType;
	}

}

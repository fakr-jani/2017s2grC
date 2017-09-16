package ro.msg.edu.persistence.bug.entity.enums;

public enum BugStatusType {
	OPEN("OPEN"), IN_PROGRESS("IN_PROGRESS"), FIXED("FIXED"), INFO_NEEDED("INFO_NEEDED"), REJECTED("REJECTED"), CLOSED(
			"CLOSED");

	private final String statusType;

	public String getStatusType() {
		return statusType;
	}

	private BugStatusType(String statusType) {
		this.statusType = statusType;
	}

}

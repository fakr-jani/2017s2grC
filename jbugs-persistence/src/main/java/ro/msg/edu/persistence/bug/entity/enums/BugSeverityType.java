package ro.msg.edu.persistence.bug.entity.enums;

public enum BugSeverityType {
	CRITICAL("CRITICAL"), HIGH("HIGH"), MEDIUM("MEDIUIM"), LOW("LOW");

	private final String severityType;

	public String getSeverityType() {
		return severityType;
	}

	private BugSeverityType(String severityType) {
		this.severityType = severityType;
	}
}

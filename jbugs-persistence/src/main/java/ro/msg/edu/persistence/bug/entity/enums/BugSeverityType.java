package ro.msg.edu.persistence.bug.entity.enums;

public enum BugSeverityType {
	CRITICAL("CRITICAL"), HIGH("HIGH"), MEDIUM("MEDIUIM"), LOW("LOW");

	private final String severityType;

	private BugSeverityType(String severityType) {
		this.severityType = severityType;
	}
}

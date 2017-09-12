package ro.msg.edu.business.bug.dto;

import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.persistence.bug.entity.Bug.bugStatus;
import ro.msg.edu.persistence.user.entity.User;

/**
 * DTO for {@link Bug} entity.
 * 
 * @author Alex Noja
 *
 */
public class BugDTO extends AbstractDTO {

	private String titleBug;

	private String descriptionBug;

	private String version;

	private String versionFixed;
	
	private String targetDate;

	private Long severity;

	private User createdBy;

	private bugStatus status;

	private User assignedTo;

	public String getTitleBug() {
		return titleBug;
	}

	public void setTitleBug(String titleBug) {
		this.titleBug = titleBug;
	}

	public String getDescriptionBug() {
		return descriptionBug;
	}

	public void setDescriptionBug(String descriptionBug) {
		this.descriptionBug = descriptionBug;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionFixed() {
		return versionFixed;
	}

	public void setVersionFixed(String versionFixed) {
		this.versionFixed = versionFixed;
	}

	public Long getSeverity() {
		return severity;
	}

	public void setSeverity(Long severity) {
		this.severity = severity;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public bugStatus getStatus() {
		return status;
	}

	public void setStatus(bugStatus status) {
		this.status = status;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

}

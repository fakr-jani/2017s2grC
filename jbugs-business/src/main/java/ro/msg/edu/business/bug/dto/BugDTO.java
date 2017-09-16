package ro.msg.edu.business.bug.dto;

import java.sql.Date;
import java.util.List;

import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.persistence.bug.entity.Attachment;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;
import ro.msg.edu.persistence.user.entity.User;

public class BugDTO extends AbstractDTO {

	private String titleBug;

	private String descriptionBug;

	private String version;

	private String versionFixed;

	private Date targetDate;

	private BugSeverityType severity;

	private User createdBy;

	private BugStatusType status;

	private User assignedTo;

	private List<Attachment> attachments;

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

	public BugSeverityType getSeverity() {
		return severity;
	}

	public void setSeverity(BugSeverityType severity) {
		this.severity = severity;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public BugStatusType getStatus() {
		return status;
	}

	public void setStatus(BugStatusType status) {
		this.status = status;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

}

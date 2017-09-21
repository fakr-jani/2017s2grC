package ro.msg.edu.business.bug.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.business.notification.dto.NotificationDTO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;

public class BugDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String titleBug;

	private String descriptionBug;

	private String version;

	private String versionFixed;

	private Date targetDate;

	private BugSeverityType severity;

	private UserDTO createdBy;

	private BugStatusType status;

	private UserDTO assignedTo;

	private List<AttachmentDTO> attachments;

	private List<NotificationDTO> notifications;

	private Map<BugStatusType, List<BugStatusType>> statusGraph;

	public BugDTO() {
		this.fillStatusGraph();
	}

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

	public UserDTO getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDTO createdBy) {
		this.createdBy = createdBy;
	}

	public BugStatusType getStatus() {
		return status;
	}

	public void setStatus(BugStatusType status) {
		this.status = status;
	}

	public UserDTO getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(UserDTO assignedTo) {
		this.assignedTo = assignedTo;
	}

	public List<AttachmentDTO> getAttachments() {
		if (attachments == null) {
			attachments = new ArrayList<AttachmentDTO>();
		}
		return attachments;
	}

	public void setAttachments(List<AttachmentDTO> attachments) {
		this.attachments = attachments;
	}

	public List<NotificationDTO> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationDTO> notifications) {
		this.notifications = notifications;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	private void fillStatusGraph() {
		statusGraph = new HashMap<>();
		statusGraph.put(BugStatusType.OPEN,
				new ArrayList<>(Arrays.asList(BugStatusType.REJECTED, BugStatusType.IN_PROGRESS)));
		statusGraph.put(BugStatusType.IN_PROGRESS,
				new ArrayList<>(Arrays.asList(BugStatusType.REJECTED, BugStatusType.INFO_NEEDED, BugStatusType.FIXED)));
		statusGraph.put(BugStatusType.INFO_NEEDED, new ArrayList<>(Arrays.asList(BugStatusType.IN_PROGRESS)));
		statusGraph.put(BugStatusType.REJECTED, new ArrayList<>(Arrays.asList(BugStatusType.CLOSED)));
		statusGraph.put(BugStatusType.FIXED, new ArrayList<>(Arrays.asList(BugStatusType.OPEN, BugStatusType.CLOSED)));
	}

	public List<BugStatusType> getPossibleTransitionsFromCurrentBugStatus() {
		return statusGraph.get(this.status);
	}

}

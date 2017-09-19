package ro.msg.edu.persistence.bug.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;
import ro.msg.edu.persistence.common.entity.AbstractEntity;
import ro.msg.edu.persistence.notification.entity.Notification;
import ro.msg.edu.persistence.user.entity.User;

@NamedQueries({ @NamedQuery(name = Bug.FIND_BUG_BY_TITLE, query = "SELECT b from Bug b WHERE b.titleBug = :title"),
		@NamedQuery(name = Bug.FIND_ALL_BUGS, query = "SELECT b from Bug b") })
@Entity
public class Bug extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_BUG_BY_TITLE = "Bug.findBugByTitle";

	public static final String FIND_ALL_BUGS = "Bug.findAllBugs";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBug;

	@NotNull
	@Column
	private String titleBug;

	@NotNull
	// @Size(min = 250)
	@Column
	private String descriptionBug;

	@NotNull
	@Column
	private String version;

	@Column
	private String versionFixed;

	@Future
	@Temporal(TemporalType.DATE)
	@Column
	private Date targetDate;

	@Column
	@Enumerated(EnumType.STRING)
	private BugSeverityType severity;

	@ManyToOne
	private User createdBy;

	@Column
	@Enumerated(EnumType.STRING)
	private BugStatusType status;

	@ManyToOne
	private User assignedTo;

	@OneToMany(mappedBy = "bug", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Attachment> attachments;

	@OneToMany(mappedBy = "bug")
	private List<Notification> notifications;

	@OneToMany(mappedBy = "bug")
	private List<History> history;

	@Override
	public Long getId() {
		return idBug;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
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
		return this.status;
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

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "Bug [idBug=" + idBug + ", titleBug=" + titleBug + ", createdBy=" + createdBy + ", status=" + status
				+ ", assignedTo=" + assignedTo + "]";
	}

	public void setIdBug(Long idBug) {
		this.idBug = idBug;
	}

}

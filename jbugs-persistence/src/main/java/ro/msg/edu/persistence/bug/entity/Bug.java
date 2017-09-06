package ro.msg.edu.persistence.bug.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ro.msg.edu.persistence.common.entity.AbstractEntity;
import ro.msg.edu.persistence.user.entity.User;

@Entity
public class Bug extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBug;

	@Column
	private String titleBug;

	@Column
	private String descriptionBug;

	@Column
	private Long version;

	@Column
	private Long versionFixed;

	@Column
	private String targetDate;

	@Column
	private Long severity;

	@ManyToOne
	private User createdBy;

	@Column
	private String status;

	@ManyToOne
	private User assignedTo;

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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersionFixed() {
		return versionFixed;
	}

	public void setVersionFixed(Long versionFixed) {
		this.versionFixed = versionFixed;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	@Override
	public String toString() {
		return "Bug [idBug=" + idBug + ", titleBug=" + titleBug + ", createdBy=" + createdBy + ", status=" + status
				+ ", assignedTo=" + assignedTo + "]";
	}

}

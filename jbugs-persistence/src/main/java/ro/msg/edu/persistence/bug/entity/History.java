package ro.msg.edu.persistence.bug.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ro.msg.edu.persistence.common.entity.AbstractEntity;
import ro.msg.edu.persistence.user.entity.User;

@Entity
public class History extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHistory;

	@Column
	private String attribute;

	@Column
	private String oldValue;

	@Column
	private String newValue;

	@Column
	private Date modifiedDate;

	@ManyToOne
	private Bug bug;

	@ManyToOne
	private User modifiedBy;

	@Override
	public Long getId() {
		return idHistory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Bug getBug() {
		return bug;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	@Override
	public String toString() {
		return "History [idHistoy=" + idHistory + ", oldValue=" + oldValue + ", newValue=" + newValue + "]";
	}

}

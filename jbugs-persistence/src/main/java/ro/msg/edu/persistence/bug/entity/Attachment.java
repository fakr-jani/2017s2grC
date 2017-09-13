package ro.msg.edu.persistence.bug.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import ro.msg.edu.persistence.common.entity.AbstractEntity;

@Entity
public class Attachment extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAttachment;

	@NotNull
	@Column
	private Long fileDescriptor;
	//

	@ManyToOne
	private Bug bug;

	@Override
	public Long getId() {
		return idAttachment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getFileDescriptor() {
		return fileDescriptor;
	}

	public void setFileDescriptor(Long fileDescriptor) {
		this.fileDescriptor = fileDescriptor;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	@Override
	public String toString() {
		return "Attachment [idAttachment=" + idAttachment + ", fileDescriptor=" + fileDescriptor + "]";
	}

}

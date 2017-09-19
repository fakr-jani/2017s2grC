package ro.msg.edu.persistence.bug.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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

	@Column
	private String fileName;

	@Lob
	@Column
	private byte[] fileBytes;

	@ManyToOne
	private Bug bug;

	@Override
	public Long getId() {
		return idAttachment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	@Override
	public String toString() {
		return "Attachment [idAttachment=" + idAttachment + ", fileDescriptor=" + "]";
	}

}

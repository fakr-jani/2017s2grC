package ro.msg.edu.business.bug.dto;

import ro.msg.edu.business.common.dto.AbstractDTO;

public class AttachmentDTO extends AbstractDTO {

	private byte[] fileBytes;

	private BugDTO bug;

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public BugDTO getBug() {
		return bug;
	}

	public void setBug(BugDTO bug) {
		this.bug = bug;
	}

	@Override
	public String toString() {
		return "Attachment" + this.getId();
	}

}

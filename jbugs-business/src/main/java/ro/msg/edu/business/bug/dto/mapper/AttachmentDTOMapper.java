package ro.msg.edu.business.bug.dto.mapper;

import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.AttachmentDTO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.persistence.bug.entity.Attachment;
import ro.msg.edu.persistence.bug.entity.Bug;

@Stateless
public class AttachmentDTOMapper extends AbstractDTOMapper<Attachment, AttachmentDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttachmentDTO getDTOInstance() {
		return new AttachmentDTO();
	}

	@Override
	protected void mapEntityToDTOFields(Attachment attachmentEntity, AttachmentDTO attachmentDTO) {
		attachmentDTO.setFileName(attachmentEntity.getFileName());
		attachmentDTO.setFileBytes(attachmentEntity.getFileBytes());

		Bug bugEntity = attachmentEntity.getBug();
		if (bugEntity != null) {
			BugDTO bugDTO = new BugDTO();
			bugDTO.setId(bugEntity.getId());
			attachmentDTO.setBug(bugDTO);
		}

	}

	@Override
	protected void mapDTOToEntityFields(AttachmentDTO attachmentDTO, Attachment attachmentEntity) {
		attachmentEntity.setFileName(attachmentDTO.getFileName());
		attachmentEntity.setFileBytes(attachmentDTO.getFileBytes());

		BugDTO bugDTO = attachmentDTO.getBug();
		if (bugDTO != null) {
			Bug bugEntity = new Bug();
			bugEntity.setIdBug(bugDTO.getId());
			attachmentEntity.setBug(bugEntity);
		}
	}

}

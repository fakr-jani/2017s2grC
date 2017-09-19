package ro.msg.edu.business.bug.dto.mapper;

import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.AttachmentDTO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.persistence.bug.entity.Attachment;
import ro.msg.edu.persistence.bug.entity.Bug;

@Stateless
public class AttachmentDTOMapper extends AbstractDTOMapper<Attachment, AttachmentDTO> {

	@Override
	public AttachmentDTO getDTOInstance() {
		return new AttachmentDTO();
	}

	@Override
	protected void mapEntityToDTOFields(Attachment entity, AttachmentDTO dto) {
		dto.setFileName(entity.getFileName());
		dto.setFileBytes(entity.getFileBytes());
		BugDTO bugDTO = new BugDTO();
		bugDTO.setId(entity.getBug().getId());
		dto.setBug(bugDTO);
	}

	@Override
	protected void mapDTOToEntityFields(AttachmentDTO dto, Attachment entity) {
		entity.setFileName(dto.getFileName());
		entity.setFileBytes(dto.getFileBytes());
		Bug bugEntity = new Bug();
		bugEntity.setIdBug(dto.getBug().getId());
		entity.setBug(bugEntity);
	}

}

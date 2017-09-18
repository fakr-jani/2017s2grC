package ro.msg.edu.business.bug.dto.mapper;

import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.AttachmentDTO;
import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.persistence.bug.entity.Attachment;

@Stateless
public class AttachmentDTOMapper extends AbstractDTOMapper<Attachment, AttachmentDTO> {

	@Override
	public AttachmentDTO getDTOInstance() {
		return new AttachmentDTO();
	}

	@Override
	protected void mapEntityToDTOFields(Attachment entity, AttachmentDTO dto) {
		dto.setFileBytes(entity.getFileBytes());
	}

	@Override
	protected void mapDTOToEntityFields(AttachmentDTO dto, Attachment entity) {
		entity.setFileBytes(dto.getFileBytes());
	}

}

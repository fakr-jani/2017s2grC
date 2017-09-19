package ro.msg.edu.business.bug.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.AttachmentDTO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
import ro.msg.edu.persistence.bug.entity.Attachment;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.user.entity.User;

/**
 * Mapper for {@link Bug} and {@link BugDTO}.
 * 
 * @author Alex Noja
 *
 */
@Stateless
public class BugDTOMapper extends AbstractDTOMapper<Bug, BugDTO> {

	@EJB
	UserDTOMapper userDTOMapper;

	@EJB
	AttachmentDTOMapper attachmentDTOMapper;

	@Override
	public BugDTO getDTOInstance() {
		return new BugDTO();
	}

	@Override
	protected void mapEntityToDTOFields(Bug entity, BugDTO dto) {
		dto.setTitleBug(entity.getTitleBug());
		dto.setDescriptionBug(entity.getDescriptionBug());
		dto.setVersion(entity.getVersion());
		dto.setVersionFixed(entity.getVersionFixed());
		dto.setTargetDate(entity.getTargetDate());
		dto.setCreatedBy(userDTOMapper.mapToDTO(entity.getCreatedBy()));
		dto.setStatus(entity.getStatus());
		dto.setSeverity(entity.getSeverity());
		dto.setAssignedTo(userDTOMapper.mapToDTO(entity.getAssignedTo()));

		List<Attachment> attachmentEntities = entity.getAttachments();
		if (attachmentEntities != null) {
			List<AttachmentDTO> attachmentDTOs = attachmentEntities.stream().map(attachmentEntity -> {
				AttachmentDTO attachmentDTO = attachmentDTOMapper.mapToDTO(attachmentEntity);
				return attachmentDTO;
			}).collect(Collectors.toList());
			dto.setAttachments(attachmentDTOs);
		}

	}

	@Override
	protected void mapDTOToEntityFields(BugDTO dto, Bug entity) {
		entity.setTitleBug(dto.getTitleBug());
		entity.setDescriptionBug(dto.getDescriptionBug());
		entity.setVersion(dto.getVersion());
		entity.setVersionFixed(dto.getVersionFixed());
		entity.setTargetDate(dto.getTargetDate());

		User createdByEntity = new User();
		userDTOMapper.mapToEntity(dto.getCreatedBy(), createdByEntity);
		entity.setCreatedBy(createdByEntity);

		entity.setStatus(dto.getStatus());
		entity.setSeverity(dto.getSeverity());

		UserDTO assignedToDTO = dto.getAssignedTo();
		if (assignedToDTO != null) {
			User assignedToEntity = new User();
			userDTOMapper.mapToEntity(assignedToDTO, assignedToEntity);
			entity.setAssignedTo(assignedToEntity);
		}

		List<AttachmentDTO> attachmentDTOs = dto.getAttachments();
		if (attachmentDTOs != null) {
			List<Attachment> attachmentEntities = attachmentDTOs.stream().map(attachmentDTO -> {
				Attachment attachmentEntity = new Attachment();
				attachmentDTOMapper.mapToEntity(attachmentDTO, attachmentEntity);
				return attachmentEntity;
			}).collect(Collectors.toList());
			entity.setAttachments(attachmentEntities);
		}
	}

}

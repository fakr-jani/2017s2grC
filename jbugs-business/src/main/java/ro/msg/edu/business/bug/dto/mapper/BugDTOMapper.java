package ro.msg.edu.business.bug.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.AttachmentDTO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.business.user.dto.UserDTO;
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

	private static final long serialVersionUID = 1L;

	@EJB
	AttachmentDTOMapper attachmentDTOMapper;

	@Override
	public BugDTO getDTOInstance() {
		return new BugDTO();
	}

	@Override
	protected void mapEntityToDTOFields(Bug bugEntity, BugDTO bugDTO) {
		bugDTO.setTitleBug(bugEntity.getTitleBug());
		bugDTO.setDescriptionBug(bugEntity.getDescriptionBug());
		bugDTO.setVersion(bugEntity.getVersion());
		bugDTO.setVersionFixed(bugEntity.getVersionFixed());
		bugDTO.setTargetDate(bugEntity.getTargetDate());

		User createdByEntity = bugEntity.getCreatedBy();
		if (createdByEntity != null) {
			UserDTO createdByDTO = new UserDTO();
			createdByDTO.setId(createdByEntity.getId());
			createdByDTO.setUsername(createdByEntity.getUsername());
			bugDTO.setCreatedBy(createdByDTO);
		}

		bugDTO.setStatus(bugEntity.getStatus());
		bugDTO.setSeverity(bugEntity.getSeverity());

		User assignedToEntity = bugEntity.getAssignedTo();
		if (assignedToEntity != null) {
			UserDTO assignedToDTO = new UserDTO();
			assignedToDTO.setId(assignedToEntity.getId());
			assignedToDTO.setUsername(assignedToEntity.getUsername());
			bugDTO.setAssignedTo(assignedToDTO);
		}

		List<Attachment> attachmentEntities = bugEntity.getAttachments();
		if (attachmentEntities != null) {
			List<AttachmentDTO> attachmentDTOs = attachmentEntities.stream().map(attachmentEntity -> {
				return attachmentDTOMapper.mapToDTO(attachmentEntity);
			}).collect(Collectors.toList());
			bugDTO.setAttachments(attachmentDTOs);
		}

	}

	@Override
	protected void mapDTOToEntityFields(BugDTO bugDTO, Bug bugEntity) {
		bugEntity.setTitleBug(bugDTO.getTitleBug());
		bugEntity.setDescriptionBug(bugDTO.getDescriptionBug());
		bugEntity.setVersion(bugDTO.getVersion());
		bugEntity.setVersionFixed(bugDTO.getVersionFixed());
		bugEntity.setTargetDate(bugDTO.getTargetDate());
		bugEntity.setStatus(bugDTO.getStatus());
		bugEntity.setSeverity(bugDTO.getSeverity());

		UserDTO createdByDTO = bugDTO.getCreatedBy();
		if (createdByDTO != null) {
			User createdByEntity = new User();
			createdByEntity.setIdUser(createdByDTO.getId());
			createdByEntity.setUsername(createdByDTO.getUsername());
			bugEntity.setCreatedBy(createdByEntity);
		}

		UserDTO assignedToDTO = bugDTO.getAssignedTo();
		if (assignedToDTO != null) {
			User assignedToEntity = new User();
			assignedToEntity.setIdUser(assignedToDTO.getId());
			assignedToEntity.setUsername(assignedToDTO.getUsername());
			bugEntity.setAssignedTo(assignedToEntity);
		}

		List<AttachmentDTO> attachmentDTOs = bugDTO.getAttachments();
		if (attachmentDTOs != null) {
			List<Attachment> attachmentEntities = attachmentDTOs.stream().map(attachmentDTO -> {
				Attachment attachmentEntity = new Attachment();
				attachmentDTOMapper.mapToEntity(attachmentDTO, attachmentEntity);
				return attachmentEntity;
			}).collect(Collectors.toList());
			bugEntity.setAttachments(attachmentEntities);
		}
	}

}

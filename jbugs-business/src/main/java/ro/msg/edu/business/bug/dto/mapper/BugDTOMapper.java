package ro.msg.edu.business.bug.dto.mapper;

import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.persistence.bug.entity.Bug;

/**
 * Mapper for {@link Bug} and {@link BugDTO}.
 * 
 * @author Alex Noja
 *
 */
@Stateless
public class BugDTOMapper extends AbstractDTOMapper<Bug, BugDTO> {

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
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setStatus(entity.getStatus());
		dto.setAssignedTo(entity.getAssignedTo());

	}

	@Override
	protected void mapDTOToEntityFields(BugDTO dto, Bug entity) {
		entity.setTitleBug(dto.getTitleBug());
		entity.setDescriptionBug(dto.getDescriptionBug());
		entity.setVersion(dto.getVersion());
		entity.setVersionFixed(dto.getVersionFixed());
		entity.setTargetDate(dto.getTargetDate());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setStatus(dto.getStatus());
		entity.setAssignedTo(dto.getAssignedTo());

	}

}

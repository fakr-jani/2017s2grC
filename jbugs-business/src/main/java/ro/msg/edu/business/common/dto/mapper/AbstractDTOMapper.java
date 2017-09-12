package ro.msg.edu.business.common.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.persistence.common.entity.AbstractEntity;

public abstract class AbstractDTOMapper<E extends AbstractEntity, DTO extends AbstractDTO> {

	public abstract DTO getDTOInstance();

	public DTO mapToDTO(E entity) {
		if (entity == null) {
			return null;
		} else {
			DTO dto = getDTOInstance();

			dto.setId(entity.getId());
			dto.setLockVersion(entity.getLockVersion());

			mapEntityToDTOFields(entity, dto);

			return dto;
		}

	}

	public void mapToEntity(DTO dto, E entity) {
		entity.setLockVersion(dto.getLockVersion());

		mapDTOToEntityFields(dto, entity);
	}

	public List<DTO> mapToDTOs(List<E> entities) {
		List<DTO> dtos = new ArrayList<>();
		for (E entity : entities) {
			dtos.add(mapToDTO(entity));
		}

		return dtos;
	}

	// TODO implement mapToEntites()

	protected abstract void mapEntityToDTOFields(E entity, DTO dto);

	protected abstract void mapDTOToEntityFields(DTO dto, E entity);

}

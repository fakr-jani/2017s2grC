package ro.msg.edu.business.common.dto.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.persistence.common.entity.AbstractEntity;

public abstract class AbstractDTOMapper<E extends AbstractEntity, D extends AbstractDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract D getDTOInstance();

	public abstract E getEntityInstance();

	public D mapToDTO(E entity) {
		if (entity == null) {
			return null;
		} else {
			D dto = getDTOInstance();

			dto.setId(entity.getId());
			dto.setLockVersion(entity.getLockVersion());

			mapEntityToDTOFields(entity, dto);

			return dto;
		}

	}

	public void mapToEntity(D dto, E entity) {
		entity.setLockVersion(dto.getLockVersion());

		mapDTOToEntityFields(dto, entity);
	}

	public List<D> mapToDTOs(List<E> entities) {
		if (entities != null) {
			return entities.stream().map(entity -> mapToDTO(entity)).collect(Collectors.toList());
		}
		return new ArrayList<D>();
	}

	public List<E> mapToEntities(List<D> dtos) {
		if (dtos != null) {
			return dtos.stream().map(dto -> {
				E e = getEntityInstance();
				mapToEntity(dto, e);
				return e;
			}).collect(Collectors.toList());
		}
		return new ArrayList<E>();
	}

	protected abstract void mapEntityToDTOFields(E entity, D dto);

	protected abstract void mapDTOToEntityFields(D dto, E entity);

}

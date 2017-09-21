package ro.msg.edu.business.permission.dto.mapper;

import javax.ejb.Stateless;

import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.business.permission.dto.PermissionDTO;
import ro.msg.edu.persistence.user.entity.Permission;

@Stateless
public class PermissionDTOMapper extends AbstractDTOMapper<Permission, PermissionDTO> {

	@Override
	public Permission getEntityInstance() {
		return new Permission();
	}

	@Override
	public PermissionDTO getDTOInstance() {
		return new PermissionDTO();
	}

	@Override
	protected void mapEntityToDTOFields(Permission entity, PermissionDTO dto) {
		dto.setNamePermission(entity.getNamePermission());
		dto.setDetailPermission(entity.getDetailPermission());
	}

	@Override
	protected void mapDTOToEntityFields(PermissionDTO dto, Permission entity) {
		entity.setNamePermission(dto.getNamePermission());
		entity.setDetailPermission(dto.getDetailPermission());
	}

}

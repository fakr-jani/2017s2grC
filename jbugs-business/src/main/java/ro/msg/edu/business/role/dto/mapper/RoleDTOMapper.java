package ro.msg.edu.business.role.dto.mapper;

import javax.ejb.Stateless;

import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.business.role.dto.RoleDTO;
import ro.msg.edu.persistence.user.entity.Role;

@Stateless
public class RoleDTOMapper extends AbstractDTOMapper<Role, RoleDTO> {

	@Override
	public Role getEntityInstance() {
		return new Role();
	}

	@Override
	public RoleDTO getDTOInstance() {
		return new RoleDTO();
	}

	@Override
	protected void mapEntityToDTOFields(Role entity, RoleDTO dto) {
		dto.setRoleName(entity.getRoleName());

	}

	@Override
	protected void mapDTOToEntityFields(RoleDTO dto, Role entity) {
		entity.setLockVersion(dto.getLockVersion());
		entity.setRoleName(dto.getRoleName());
	}

}

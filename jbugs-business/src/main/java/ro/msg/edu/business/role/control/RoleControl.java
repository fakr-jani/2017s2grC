package ro.msg.edu.business.role.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.role.dao.RoleDAO;
import ro.msg.edu.business.role.dto.RoleDTO;
import ro.msg.edu.business.role.dto.mapper.RoleDTOMApper;
import ro.msg.edu.persistence.user.entity.Role;

@Stateless
public class RoleControl {
	@EJB
	private RoleDAO roleDAO;

	@EJB
	private RoleDTOMApper roleDTOMapper;

	public List<RoleDTO> findAllRoles() {
		List<Role> roles = roleDAO.getAllRoles();
		List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
		for (Role r : roles) {
			rolesDTO.add(roleDTOMapper.mapToDTO(r));
		}
		return rolesDTO;
	}
}

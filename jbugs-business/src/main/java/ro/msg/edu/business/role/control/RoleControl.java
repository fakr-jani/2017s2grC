package ro.msg.edu.business.role.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.permission.dao.PermissionDAO;
import ro.msg.edu.business.permission.dto.PermissionDTO;
import ro.msg.edu.business.permission.dto.mapper.PermissionDTOMapper;
import ro.msg.edu.business.role.dao.RoleDAO;
import ro.msg.edu.business.role.dto.RoleDTO;
import ro.msg.edu.business.role.dto.mapper.RoleDTOMApper;
import ro.msg.edu.persistence.user.entity.Permission;
import ro.msg.edu.persistence.user.entity.Role;

@Stateless
public class RoleControl {
	@EJB
	private RoleDAO roleDAO;

	@EJB
	private PermissionDAO permissionDAO;

	@EJB
	private RoleDTOMApper roleDTOMapper;

	@EJB
	private PermissionDTOMapper permissionDTOMapper;

	public List<RoleDTO> findAllRoles() {
		List<Role> roles = roleDAO.getAllRoles();
		List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
		for (Role r : roles) {
			rolesDTO.add(roleDTOMapper.mapToDTO(r));
		}
		return rolesDTO;
	}

	public RoleDTO addPermissions(String selectedRole, String[] selectedPermissions) {
		Role role = roleDAO.findRoleByName(selectedRole);
		List<Permission> permissionList = new ArrayList<>();

		for (String permissionName : selectedPermissions) {
			permissionList.add(permissionDAO.findPermissionByName(permissionName));
		}
		role.setPermissions(permissionList);
		roleDAO.persistEntity(role);
		Role persistedRole = roleDAO.findEntity(role.getId());
		return roleDTOMapper.mapToDTO(persistedRole);
	}

	public List<PermissionDTO> viewPermissions(String roleName) {
		Role role;
		if (roleName == null) {
			role = roleDAO.findRoleByName("ADMINISTRATOR");
		} else {
			role = roleDAO.findRoleByName(roleName);
		}

		List<Permission> permissionList = role.getPermissions();
		List<PermissionDTO> permissionListDTO = new ArrayList<>();
		for (Permission permission : permissionList) {
			permissionListDTO.add(permissionDTOMapper.mapToDTO(permission));
		}
		return permissionListDTO;

	}

}

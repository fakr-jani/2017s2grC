package ro.msg.edu.business.role.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.permission.dao.PermissionDAO;
import ro.msg.edu.business.permission.dto.mapper.PermissionDTOMapper;
import ro.msg.edu.business.role.dao.RoleDAO;
import ro.msg.edu.business.role.dto.RoleDTO;
import ro.msg.edu.business.role.dto.mapper.RoleDTOMApper;
import ro.msg.edu.persistence.user.entity.Permission;
import ro.msg.edu.persistence.user.entity.Role;
import ro.msg.edu.persistence.user.entity.enums.PermissionType;
import ro.msg.edu.persistence.user.entity.enums.RoleType;

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
		Role role = roleDAO.getRoleByName(RoleType.valueOf(selectedRole));
		List<Permission> permissionList = new ArrayList<>();

		for (String permissionName : selectedPermissions) {
			permissionList.add(permissionDAO.findPermissionByName(PermissionType.valueOf(permissionName)));
		}
		role.setPermissions(permissionList);
		roleDAO.persistEntity(role);
		Role persistedRole = roleDAO.findEntity(role.getId());
		return roleDTOMapper.mapToDTO(persistedRole);
	}

	public List<String> viewPermissions(String selectedRole) {
		Role role = roleDAO.getRoleByName(getRoleType(selectedRole));

		List<Permission> permissionList = role.getPermissions();
		List<String> permissionTypeList = new ArrayList<>();
		for (Permission permission : permissionList) {
			permissionTypeList.add(permission.getNamePermission().toString());
		}
		return permissionTypeList;

	}

	private RoleType getRoleType(String selectedRole) {
		if (selectedRole == null) {
			return RoleType.ADMINISTRATOR;
		}
		return RoleType.valueOf(selectedRole);
	}

}

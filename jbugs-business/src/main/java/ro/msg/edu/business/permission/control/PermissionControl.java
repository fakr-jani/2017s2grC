package ro.msg.edu.business.permission.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.permission.dao.PermissionDAO;
import ro.msg.edu.business.permission.dto.PermissionDTO;
import ro.msg.edu.business.permission.dto.mapper.PermissionDTOMapper;
import ro.msg.edu.persistence.user.entity.Permission;

@Stateless
public class PermissionControl implements Serializable{

	@EJB
	private PermissionDAO permissionDAO;

	@EJB
	private PermissionDTOMapper permissionDTOMapper;

	public List<PermissionDTO> findAllPermission() {
		List<Permission> permissions = permissionDAO.getAllPermissions();
		List<PermissionDTO> permissionsDTO = new ArrayList<PermissionDTO>();
		permissions.stream().forEach(e -> permissionsDTO.add(permissionDTOMapper.mapToDTO(e)));
		return permissionsDTO;
	}

}

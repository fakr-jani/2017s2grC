package ro.msg.edu.business.user.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.bug.dto.mapper.BugDTOMapper;
import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.business.role.dto.RoleDTO;
import ro.msg.edu.business.role.dto.mapper.RoleDTOMapper;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.user.entity.Role;
import ro.msg.edu.persistence.user.entity.User;

/**
 * Mapper for {@link User} and {@link UserDTO}.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
@Stateless
public class UserDTOMapper extends AbstractDTOMapper<User, UserDTO> {

	@EJB
	RoleDTOMapper roleDTOMapper;

	@EJB
	BugDTOMapper bugDTOMapper;

	@Override
	public UserDTO getDTOInstance() {
		return new UserDTO();
	}

	@Override
	protected void mapEntityToDTOFields(User entity, UserDTO dto) {
		dto.setEmail(entity.getEmail());
		dto.setFirstname(entity.getFirstname());
		dto.setLastname(entity.getLastname());
		dto.setPassword(entity.getPassword());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setUsername(entity.getUsername());
		dto.setActive(entity.isActive());
		dto.setCounter(entity.getCounter());

		List<Role> roleEntities = entity.getRoles();
		if (roleEntities != null) {
			List<RoleDTO> roleDTOs = roleEntities.stream().map(e -> roleDTOMapper.mapToDTO(e))
					.collect(Collectors.toList());
			dto.setRoles(roleDTOs);
		}

		List<Bug> assignedBugsEntities = entity.getAssignedBugs();
		if (assignedBugsEntities != null) {
			List<BugDTO> bugDTOs = assignedBugsEntities.stream().map(bugEntity -> bugDTOMapper.mapToDTO(bugEntity))
					.collect(Collectors.toList());
			dto.setAssignedBugs(bugDTOs);
		}

		List<Bug> createdBugsEntities = entity.getCreatedBugs();
		if (createdBugsEntities != null) {
			List<BugDTO> bugDTOs = createdBugsEntities.stream().map(bugEntity -> bugDTOMapper.mapToDTO(bugEntity))
					.collect(Collectors.toList());
			dto.setCreatedBugs(bugDTOs);
		}
	}

	@Override
	protected void mapDTOToEntityFields(UserDTO dto, User entity) {
		entity.setEmail(dto.getEmail());
		entity.setFirstname(dto.getFirstname());
		entity.setLastname(dto.getLastname());
		entity.setPassword(dto.getPassword());
		entity.setLockVersion(dto.getLockVersion());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setUsername(dto.getUsername());
		entity.setActive(dto.isActive());
		entity.setCounter(dto.getCounter());

		List<RoleDTO> roleDTOs = dto.getRoles();
		if (roleDTOs != null) {
			List<Role> roleEntities = roleDTOs.stream().map(roleDTO -> {
				Role roleEntity = new Role();
				roleDTOMapper.mapToEntity(roleDTO, roleEntity);
				return roleEntity;
			}).collect(Collectors.toList());
			entity.setRoles(roleEntities);
		}

		List<BugDTO> assignedBugsDTOs = dto.getAssignedBugs();
		if (assignedBugsDTOs != null) {
			List<Bug> bugEntities = assignedBugsDTOs.stream().map(bugDTO -> {
				Bug bugEntity = new Bug();
				bugDTOMapper.mapToEntity(bugDTO, bugEntity);
				return bugEntity;
			}).collect(Collectors.toList());
			entity.setAssignedBugs(bugEntities);
		}

		List<BugDTO> createdBugsDTOs = dto.getCreatedBugs();
		if (createdBugsDTOs != null) {
			List<Bug> bugEntities = createdBugsDTOs.stream().map(bugDTO -> {
				Bug bugEntity = new Bug();
				bugDTOMapper.mapToEntity(bugDTO, bugEntity);
				return bugEntity;
			}).collect(Collectors.toList());
			entity.setCreatedBugs(bugEntities);
		}
	}

}

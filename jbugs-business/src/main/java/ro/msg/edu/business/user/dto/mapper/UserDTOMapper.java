package ro.msg.edu.business.user.dto.mapper;

import java.util.ArrayList;
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
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
@Stateless
public class UserDTOMapper extends AbstractDTOMapper<User, UserDTO> {

	private static final long serialVersionUID = 1L;

	@EJB
	RoleDTOMapper roleDTOMapper;

	@EJB
	BugDTOMapper bugDTOMapper;

	@Override
	public UserDTO getDTOInstance() {
		return new UserDTO();
	}

	@Override
	protected void mapEntityToDTOFields(User userEntity, UserDTO userDTO) {
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setFirstname(userEntity.getFirstname());
		userDTO.setLastname(userEntity.getLastname());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setPhoneNumber(userEntity.getPhoneNumber());
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setActive(userEntity.isActive());
		userDTO.setNumberOfTries(userEntity.getNumberOfTries());

		List<Role> roleEntities = userEntity.getRoles();
		if (roleEntities != null) {
			List<RoleDTO> roleDTOs = roleEntities.stream().map(e -> roleDTOMapper.mapToDTO(e))
					.collect(Collectors.toList());
			if (userDTO.getRoles() == null) {
				userDTO.setRoles(new ArrayList<>());
			}
			userDTO.setRoles(roleDTOs);
		}

		List<Bug> assignedBugsEntities = userEntity.getAssignedBugs();
		if (assignedBugsEntities != null) {
			List<BugDTO> bugDTOs = assignedBugsEntities.stream().map(bugEntity -> bugDTOMapper.mapToDTO(bugEntity))
					.collect(Collectors.toList());
			if (userDTO.getAssignedBugs() == null) {
				userDTO.setAssignedBugs(new ArrayList<>());
			}
			userDTO.getAssignedBugs().addAll(bugDTOs);
		}

		userDTO.setRoles(roleDTOMapper.mapToDTOs(userEntity.getRoles()));
		userDTO.setAssignedBugs(bugDTOMapper.mapToDTOs(userEntity.getAssignedBugs()));
		userDTO.setCreatedBugs(bugDTOMapper.mapToDTOs(userEntity.getCreatedBugs()));

		List<Bug> createdBugsEntities = userEntity.getCreatedBugs();
		if (createdBugsEntities != null) {
			List<BugDTO> bugDTOs = createdBugsEntities.stream().map(bugEntity -> bugDTOMapper.mapToDTO(bugEntity))
					.collect(Collectors.toList());
			if (userDTO.getCreatedBugs() == null) {
				userDTO.setCreatedBugs(new ArrayList<>());
			}
			userDTO.getCreatedBugs().addAll(bugDTOs);
		}

	}

	@Override
	protected void mapDTOToEntityFields(UserDTO userDTO, User userEntity) {
		userEntity.setId(userDTO.getId());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setFirstname(userDTO.getFirstname());
		userEntity.setLastname(userDTO.getLastname());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setLockVersion(userDTO.getLockVersion());
		userEntity.setPhoneNumber(userDTO.getPhoneNumber());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setActive(userDTO.isActive());
		userEntity.setNumberOfTries(userDTO.getNumberOfTries());

		List<RoleDTO> roleDTOs = userDTO.getRoles();
		if (roleDTOs != null) {
			List<Role> roleEntities = roleDTOs.stream().map(roleDTO -> {
				Role roleEntity = new Role();
				roleDTOMapper.mapToEntity(roleDTO, roleEntity);
				return roleEntity;
			}).collect(Collectors.toList());
			if (userEntity.getRoles() == null) {
				userEntity.setRoles(new ArrayList<>());
			}
			userEntity.getRoles().addAll(roleEntities);
		}

		List<BugDTO> assignedBugsDTOs = userDTO.getAssignedBugs();
		if (assignedBugsDTOs != null) {
			List<Bug> bugEntities = assignedBugsDTOs.stream().map(bugDTO -> {
				Bug bugEntity = new Bug();
				bugDTOMapper.mapToEntity(bugDTO, bugEntity);
				return bugEntity;
			}).collect(Collectors.toList());
			if (userEntity.getAssignedBugs() == null) {
				userEntity.setAssignedBugs(new ArrayList<>());
			}
			userEntity.getAssignedBugs().addAll(bugEntities);
		}

		List<BugDTO> createdBugsDTOs = userDTO.getCreatedBugs();
		if (createdBugsDTOs != null) {
			List<Bug> bugEntities = createdBugsDTOs.stream().map(bugDTO -> {
				Bug bugEntity = new Bug();
				bugDTOMapper.mapToEntity(bugDTO, bugEntity);
				return bugEntity;
			}).collect(Collectors.toList());
			if (userEntity.getCreatedBugs() == null) {
				userEntity.setCreatedBugs(new ArrayList<>());
			}
			userEntity.getCreatedBugs().addAll(bugEntities);
		}

	}

}

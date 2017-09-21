package ro.msg.edu.business.user.dto.mapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.mapper.BugDTOMapper;
import ro.msg.edu.business.common.dto.mapper.AbstractDTOMapper;
import ro.msg.edu.business.role.dto.mapper.RoleDTOMapper;
import ro.msg.edu.business.user.dto.UserDTO;
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
	public User getEntityInstance() {
		return new User();
	}

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

		userDTO.setAssignedBugs(bugDTOMapper.mapToDTOs(userEntity.getAssignedBugs()));

		userDTO.setCreatedBugs(bugDTOMapper.mapToDTOs(userEntity.getCreatedBugs()));

	}

	@Override
	protected void mapDTOToEntityFields(UserDTO userDTO, User userEntity) {
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setFirstname(userDTO.getFirstname());
		userEntity.setLastname(userDTO.getLastname());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setLockVersion(userDTO.getLockVersion());
		userEntity.setPhoneNumber(userDTO.getPhoneNumber());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setActive(userDTO.isActive());
		userEntity.setNumberOfTries(userDTO.getNumberOfTries());

		userEntity.setRoles(roleDTOMapper.mapToEntities(userDTO.getRoles()));

		userEntity.setAssignedBugs(bugDTOMapper.mapToEntities(userDTO.getAssignedBugs()));

		userEntity.setCreatedBugs(bugDTOMapper.mapToEntities(userDTO.getCreatedBugs()));

	}

}

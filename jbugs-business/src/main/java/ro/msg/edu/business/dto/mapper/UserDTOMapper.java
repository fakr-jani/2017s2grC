package ro.msg.edu.business.dto.mapper;

import ro.msg.edu.business.dto.UserDTO;
import ro.msg.edu.persistence.user.entity.User;

/**
 * Mapper for {@link UserDTO} and {@link User}
 * 
 * @author maresb
 *
 */
public class UserDTOMapper {
	public UserDTO mapToDTO(User userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setLastname(userEntity.getLastname());
		userDTO.setFirstname(userEntity.getFirstname());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setPhoneNumber(userEntity.getPhoneNumber());
		userDTO.setActive(userEntity.isActive());
		userDTO.setUsername(userEntity.getUsername());
		// userDTO.setVersion(userEntity.getUsername());
		return userDTO;
	}

	public void mapToEntity(UserDTO userDTO, User userEntity) {

		userEntity.setFirstname(userDTO.getFirstname());
		userEntity.setLastname(userDTO.getLastname());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setPhoneNumber(userDTO.getPhoneNumber());
		userEntity.setActive(userDTO.isActive());
		userEntity.setUsername(userDTO.getUsername());
	}

}

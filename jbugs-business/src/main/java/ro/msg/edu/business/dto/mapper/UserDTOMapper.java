package ro.msg.edu.business.dto.mapper;

import ro.msg.edu.business.dto.UserDTO;
import ro.msg.edu.persistence.user.entity.User;

public class UserDTOMapper {

	public UserDTO mapToDTO(User userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setLastname(userEntity.getLastname());
		userDTO.setFirstname(userEntity.getFirstname());
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setPhoneNumber(userEntity.getPhoneNumber());
		userDTO.setActive(userEntity.isActive());

		return userDTO;
	}

	public void mapToEntity(UserDTO userDTO, User userEntity) {
		// TODO maybe
		// if userDTO.id == null, then persist
		// else load entity and update

		userEntity.setId(userDTO.getId());
		userEntity.setFirstname(userDTO.getFirstname());
		userEntity.setLastname(userDTO.getLastname());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPhoneNumber(userDTO.getPhoneNumber());
		userEntity.setActive(userDTO.isActive());
	}

}

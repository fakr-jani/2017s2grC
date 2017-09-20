package ro.msg.edu.business.user.dto;

import java.util.List;

import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.business.role.dto.RoleDTO;
import ro.msg.edu.persistence.user.entity.User;

/**
 * DTO for {@link User} entity.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
public class UserDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String firstname;

	private String lastname;

	private String email;

	private String username;

	private String password;

	private String phoneNumber;

	private boolean active;

	private int numberOfTries;

	private List<RoleDTO> roles;

	private List<BugDTO> createdBugs;

	private List<BugDTO> assignedBugs;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public int getNumberOfTries() {
		return numberOfTries;

	}

	public List<BugDTO> getCreatedBugs() {
		return createdBugs;
	}

	public void setCreatedBugs(List<BugDTO> createdBugs) {
		this.createdBugs = createdBugs;
	}

	public List<BugDTO> getAssignedBugs() {
		return assignedBugs;
	}

	public void setAssignedBugs(List<BugDTO> assignedBugs) {
		this.assignedBugs = assignedBugs;
	}

	public void setNumberOfTries(int numberOfTries) {
		this.numberOfTries = numberOfTries;
	}

	@Override
	public String toString() {
		return getId() + "," + firstname + "," + lastname + "," + email + "," + username + "," + password + ","
				+ phoneNumber + "," + active + "," + numberOfTries;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (active != other.active)
			return false;
		if (numberOfTries != other.numberOfTries)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;

	}

}

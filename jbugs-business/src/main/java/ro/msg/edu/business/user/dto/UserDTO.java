package ro.msg.edu.business.user.dto;

import java.util.List;

import ro.msg.edu.business.common.dto.AbstractDTO;
import ro.msg.edu.persistence.user.entity.Role;
import ro.msg.edu.persistence.user.entity.User;

/**
 * DTO for {@link User} entity.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
public class UserDTO extends AbstractDTO {

	private String firstname;

	private String lastname;

	private String email;

	private String username;

	private String password;

	private String phoneNumber;

	private boolean active;

	private int counter;

	private List<Role> roles;

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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "UserDTO [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", username="
				+ username + ", password=" + password + ", phoneNumber=" + phoneNumber + ", active=" + active
				+ ", counter=" + counter + ", roles=" + roles + "]";
	}

}

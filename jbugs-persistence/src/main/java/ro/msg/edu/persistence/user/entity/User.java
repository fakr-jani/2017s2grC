package ro.msg.edu.persistence.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * 
 * @author maresb
 *
 */
@NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "SELECT u from User u WHERE u.email = :email")
@Entity
public class User extends AbstractEntity {

	public static final String FIND_USER_BY_EMAIL = "User.findUserByEmail";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String firstname;

	@Column
	private String lastname;

	@Column
	private String email;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String phoneNumber;

	@Column
	private boolean active;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}

}
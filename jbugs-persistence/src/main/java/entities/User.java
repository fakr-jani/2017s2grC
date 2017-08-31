package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="User.findAllUsers",query="select u from User u")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	private String firstName;
	private String lastName;

	@Column(length = 6, unique = true)
	private String userName;
	private String password;
	private String email;

	@Column(length = 14)
	private String phoneNumber;
	private Boolean status;
	
	@ManyToMany
	@JoinTable(name="User_Role",
			   joinColumns=@JoinColumn(name="idUser"),
			   inverseJoinColumns=@JoinColumn(name="idRole"))
	private List<Role> roles;

	public User() {
		super();
	}

	public User( String firstName, String lastName, String userName, String password, String email,
			String phoneNumber, Boolean status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int iduser) {
		this.idUser = iduser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", status=" + status + ", roles=" + roles + "]";
	}

}

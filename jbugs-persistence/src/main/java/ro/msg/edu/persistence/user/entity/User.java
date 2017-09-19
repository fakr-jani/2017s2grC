package ro.msg.edu.persistence.user.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.bug.entity.History;
import ro.msg.edu.persistence.common.entity.AbstractEntity;
import ro.msg.edu.persistence.notification.entity.Notification;

/**
 * Entity for the User.
 * 
 * @author Patricia
 *
 */
@NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "SELECT u from User u WHERE u.email = :email")
@Entity
public class User extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_USER_BY_EMAIL = "User.findUserByEmail";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;

	@NotNull
	@Column
	private String firstname;

	@NotNull
	@Column
	private String lastname;

	@Column(unique = true)
	private String email;

	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	@Column
	private String password;

	@Column
	private String phoneNumber;

	@Column
	private int counter;

	@Column
	private boolean active;

	@ManyToMany
	@JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = @JoinColumn(name = "idRole"))
	private List<Role> roles;

	@ManyToMany
	@JoinTable(name = "User_Notification", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = @JoinColumn(name = "idNotification"))
	private List<Notification> notifications;

	@OneToMany(mappedBy = "assignedTo", cascade = CascadeType.PERSIST)
	private List<Bug> assignedBugs;

	@OneToMany(mappedBy = "createdBy")
	private List<Bug> createdBugs;

	@OneToMany(mappedBy = "modifiedBy")
	private List<History> history;

	@Override
	public Long getId() {
		return idUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public List<Bug> getAssignedBugs() {
		return assignedBugs;
	}

	public void setAssignedBugs(List<Bug> assignedBugs) {
		this.assignedBugs = assignedBugs;
	}

	public List<Bug> getCreatedBugs() {
		return createdBugs;
	}

	public void setCreatedBugs(List<Bug> createdBugs) {
		this.createdBugs = createdBugs;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "User [id=" + idUser + ", username=" + username + "]";
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
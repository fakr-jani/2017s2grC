package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idRole;
	String roleName;

	@ManyToMany(mappedBy="roles")
	private List<User> users;
	
	@ManyToMany
	@JoinTable(name="Permission_Role",
			   joinColumns=@JoinColumn(name="idRole"),
			   inverseJoinColumns=@JoinColumn(name="idPermission"))
	private List<Permission> permissions;
	
	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

	public Role() {
		super();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String name) {
		this.roleName = name;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", roleName=" + roleName + ", users=" + users + ", permissions=" + permissions
				+ "]";
	}
}

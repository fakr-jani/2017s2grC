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
	private int idRole;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;
	
	@ManyToMany
	@JoinTable(name = "Role_Permission", joinColumns = @JoinColumn(name = "idRole"), inverseJoinColumns = {
			@JoinColumn(name = "idPermission") })
	private List<Permission> permissions;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", name=" + name + ", users=" + users + ", permissions=" + permissions + "]";
	}
	
	

}

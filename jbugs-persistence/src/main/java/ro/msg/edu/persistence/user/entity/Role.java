package ro.msg.edu.persistence.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import ro.msg.edu.persistence.common.entity.AbstractEntity;
import ro.msg.edu.persistence.user.entity.enums.RoleType;

@Entity
public class Role extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;

	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private RoleType roleName;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	@ManyToMany
	@JoinTable(name = "Role_Permission", joinColumns = @JoinColumn(name = "idRole"), inverseJoinColumns = @JoinColumn(name = "idPermission"))
	private List<Permission> permissions;

	@Override
	public Long getId() {
		return idRole;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public RoleType getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleType roleName) {
		this.roleName = roleName;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", roleName=" + roleName + "]";
	}

}

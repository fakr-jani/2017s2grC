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
public class Permission implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPermission;
	
	private String permissioName;
	private String details;
	
	@ManyToMany(mappedBy="permissions")
	private List<Role> roles;
	
	public int getIdPermission() {
		return idPermission;
	}
	public void setIdPermission(int idPermission) {
		this.idPermission = idPermission;
	}
	public String getPermissioName() {
		return permissioName;
	}
	public void setPermissioName(String permissioName) {
		this.permissioName = permissioName;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String permissionDetails) {
		this.details = permissionDetails;
	}
	
}

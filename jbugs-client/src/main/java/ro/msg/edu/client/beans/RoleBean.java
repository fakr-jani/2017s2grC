package ro.msg.edu.client.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ro.msg.edu.business.role.boundary.RoleFacade;
import ro.msg.edu.business.role.dto.RoleDTO;

@ManagedBean
@SessionScoped
public class RoleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private RoleFacade roleFacade;

	private String selectedRole;

	private String[] selectedPermission;

	private List<String> permissionTypeList;

	public void addPermissions() {
		roleFacade.addPermissions(selectedRole, selectedPermission);
	}

	public String addPermissionPageReturn() {
		addPermissions();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Permission was added!"));
		return "addPermission";
	}

	public String removePermissionPageReturn() {
		addPermissions();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Permission was remove!"));
		return "removePermission";
	}

	/**
	 * permissionTypeList - contains the permissions of the selectedRole
	 * selectedPermission - array we pass the checkbox
	 */
	public void getViewPermissions() {
		int i = 0;
		permissionTypeList = roleFacade.viewPermissions(selectedRole);
		selectedPermission = new String[permissionTypeList.size()];
		for (String type : permissionTypeList) {
			selectedPermission[i] = type;
			i++;
		}
	}

	public List<RoleDTO> getAllRoles() {
		return roleFacade.findAllRoles();
	}

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

	public String[] getSelectedPermission() {
		return selectedPermission;
	}

	public void setSelectedPermission(String[] selectedPermission) {
		this.selectedPermission = selectedPermission;
	}

	public List<String> getPermissionTypeLst() {
		return permissionTypeList;
	}

	public void setPermissionTypeLst(List<String> permissionTypeLst) {
		this.permissionTypeList = permissionTypeLst;
	}

}

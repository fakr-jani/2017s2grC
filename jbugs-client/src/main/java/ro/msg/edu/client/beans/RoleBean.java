package ro.msg.edu.client.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ro.msg.edu.business.permission.dto.PermissionDTO;
import ro.msg.edu.business.role.boundary.RoleFacade;
import ro.msg.edu.business.role.dto.RoleDTO;

@ManagedBean
@ViewScoped
public class RoleBean extends AbstractBean {
	@EJB
	private RoleFacade roleFacade;

	private String selectedRole;

	private String[] selectedPermission;

	public List<RoleDTO> getAllRoles() {
		return roleFacade.findAllRoles();
	}

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

	public String addPermissions() {
		roleFacade.addPermissions(selectedRole, selectedPermission);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Permission was added!"));
		return "addPermission";
	}

	public List<PermissionDTO> getViewPermissions() {

		return roleFacade.viewPermissions(selectedRole);
	}

	public String[] getSelectedPermission() {
		return selectedPermission;
	}

	public void setSelectedPermission(String[] selectedPermission) {
		this.selectedPermission = selectedPermission;
	}

}

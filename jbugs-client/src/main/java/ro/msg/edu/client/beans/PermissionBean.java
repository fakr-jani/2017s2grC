package ro.msg.edu.client.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ro.msg.edu.business.permission.boundary.PermissionFacade;
import ro.msg.edu.business.permission.dto.PermissionDTO;

@ManagedBean
@ViewScoped
public class PermissionBean extends AbstractBean {

	@EJB
	PermissionFacade permissionFacade;

	public List<PermissionDTO> getAllPermissions() {
		return permissionFacade.findAllPermissions();
	}

}

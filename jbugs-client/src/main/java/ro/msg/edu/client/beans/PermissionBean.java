package ro.msg.edu.client.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ro.msg.edu.business.permission.boundary.PermissionFacade;
import ro.msg.edu.business.permission.dto.PermissionDTO;

@ManagedBean
@ViewScoped
public class PermissionBean implements Serializable {

	@EJB
	PermissionFacade permissionFacade;

	public List<PermissionDTO> getAllPermissions() {
		return permissionFacade.findAllPermissions();
	}

}

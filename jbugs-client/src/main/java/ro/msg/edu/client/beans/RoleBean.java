package ro.msg.edu.client.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ro.msg.edu.business.role.boundary.RoleFacade;
import ro.msg.edu.business.role.dto.RoleDTO;

@ManagedBean
@ViewScoped
public class RoleBean implements Serializable {

	@EJB
	private RoleFacade roleFacade;

	public List<RoleDTO> getAllRoles() {
		return roleFacade.findAllRoles();
	}

}

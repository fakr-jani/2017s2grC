
package ro.msg.edu.client.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.user.entity.enums.PermissionType;

@ManagedBean
@SessionScoped
public class UserBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	UserFacade userFacade;

	private UserDTO newUser = new UserDTO();

	private UserDTO selectedUser = new UserDTO();

	private String[] selectedRoles;
	
	private List<String> updateRoles;

	private static final String EDIT_USERS = "editUsers";
	private static final String DELETE_USER = "deleteUser";
	private static final String ADD_USER = "addUser";

	public UserDTO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserDTO selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<UserDTO> getAllUsers() {
		return userFacade.findAllUsers();
	}

	public UserDTO getNewUser() {
		return newUser;
	}

	public void setNewUser(UserDTO newUser) {
		this.newUser = newUser;
	}

	public String createNewUser() {
		try {
			UserDTO userCreated = userFacade.createUser(newUser, selectedRoles);
			addMessage(userCreated.getUsername() + " " + getMessageFromProperty("#{msg['user.added']}"));
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
		}
		return ADD_USER;
	}

	public String deleteUser(UserDTO user) {
		try {
			userFacade.deleteUser(user);
			addMessage(user.getUsername() + " " + getMessageFromProperty("#{msg['user.deleted']}"));
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
		}
		return DELETE_USER;
	}

	public String activateUser(UserDTO user) {
		userFacade.activateUser(user);
		addMessage(user.getUsername() + " " + getMessageFromProperty("#{msg['user.activated']}"));
		return EDIT_USERS ;
	}

	public String enterUpdateMode(UserDTO user) {
		this.selectedUser = user;
		return EDIT_USERS ;
	}

	public String leaveUpdateMode() {

		selectedUser = new UserDTO();
		return EDIT_USERS ;
	}

	public boolean verifyUserRendered(UserDTO user) {
		return userFacade.hasActiveTasks(user);
	}

	public boolean verifyEditRendered(UserDTO user) {
		return (selectedUser != null && user.getId().equals(selectedUser.getId()));
	}

	public String updateUser() {
		try {
			userFacade.updateUser(selectedUser,updateRoles);
			addMessage(selectedUser.getUsername() + " " + getMessageFromProperty("#{msg['user.updated']}"));
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
		}
		return EDIT_USERS;
	}

	public String[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(String[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public String getMessageFromProperty(String messageProperty) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context, messageProperty, String.class);
	}
	
	public List<String> getUpdateRoles() {
		return updateRoles;
	}

	public void setUpdateRoles(List<String> updateRoles) {
		this.updateRoles = updateRoles;
	}

}
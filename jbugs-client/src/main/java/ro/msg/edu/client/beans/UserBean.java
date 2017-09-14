package ro.msg.edu.client.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ro.msg.edu.business.common.exception.JBugsException;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;

@ManagedBean
@SessionScoped
public class UserBean extends AbstractBean {

	@EJB
	UserFacade userFacade;

	private UserDTO newUser = new UserDTO();

	private UserDTO selectedUser = new UserDTO();

	private String[] selectedRoles;

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
			userFacade.createUser(newUser, selectedRoles);
		} catch (JBugsException e) {

			handleExceptioni18n(e);

		}
		return "addUser";
	}

	public String deleteUser(UserDTO user) {
		try {

			userFacade.deleteUser(user);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Userul " + user.getUsername() + " a fost sters"));
		} catch (TechnicalException e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return "deleteUser";
	}

	public String activateUser(UserDTO user) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Userul " + user.getUsername() + " a fost activat"));
		userFacade.activateUser(user);
		return "users";
	}

	public String enterUpdateMode(UserDTO user) {

		this.selectedUser = user;
		return "users";
	}

	public String leaveUpdateMode() {

		selectedUser = new UserDTO();
		return "users";
	}

	public boolean verifyUserRendered(UserDTO user) {
		return (selectedUser != null && user.getId().equals(selectedUser.getId()) || userFacade.hasActiveTasks(user));
	}

	public String editUser() {
		try {

			userFacade.updateUser(selectedUser);
			addMessage(selectedUser.getUsername() + " a fost editat");
		} catch (JBugsException e) {

			handleExceptioni18n(e);

		}

		return "users";
	}

	public String[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(String[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

}

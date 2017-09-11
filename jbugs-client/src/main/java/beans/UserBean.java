package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;

@ManagedBean
@RequestScoped
public class UserBean {
	@EJB
	UserFacade userFacade;

	private UserDTO newUser = new UserDTO();

	/**
	 * @return the user
	 */
	public UserDTO getNewUser() {
		return newUser;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setNewUser(UserDTO user) {
		this.newUser = user;
	}

	public List<UserDTO> getAllUsers() {
		return userFacade.getAllUsers();

	}

	public String createNewUser() throws BusinessException {
		userFacade.createUser(newUser);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Userul " + newUser.getFirstname() + " a fost creat!"));
		newUser = new UserDTO();
		return "users";
	}

	public String deleteUser(UserDTO user) {
		userFacade.deleteUser(user);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Userul " + newUser.getFirstname() + " a fost sters!"));
		return "users";

	}

}

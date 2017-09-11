package edu.msg.ro.user.bean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;

@Named
@RequestScoped
public class LoginBean extends HttpServlet {

	@EJB
	UserFacade userFacade;

	UserDTO u = new UserDTO();

	public UserDTO getUser() {
		return u;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(UserDTO user) {
		this.u = user;
	}

	public void loginActionListener(ActionEvent event) {
		System.err.println("something something event from " + event.getComponent().getClientId());
	}

	public String processLogin() {

		HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(false);

		UserDTO loggedUser;

		loggedUser = userFacade.findUserbyUsername(u.getUsername());
		if (loggedUser != null && u.getPassword().equals(loggedUser.getPassword())) {
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage("loginForm:username",
					new FacesMessage("Password or Username wrong!"));
			return "login";
		}

	}

	@Produces
	@RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public String processLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "login";
	}

}

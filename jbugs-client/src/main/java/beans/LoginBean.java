/**
 * 
 */
package beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;

@ManagedBean
@javax.faces.bean.RequestScoped
public class LoginBean {

	@EJB
	UserFacade userFacade;

	private UserDTO user = new UserDTO();

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String processLogin() {

		if (userFacade.verifyLoggedInUser(user)) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("username", user.getUsername());
			// getFacesContext().addMessage(null, new FacesMessage("Welcome!"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome, " + user.getUsername()));
			return "users";
		} else {
			getFacesContext().addMessage("loginForm:username", new FacesMessage("Password or Username wrong!"));
			return "loginFailed";
		}
	}

	public String processLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "login";
	}

	/**
	 * Sadly FacesContext is not injectable. For Consistency's sake the
	 * recommended way of getting it is with a producer.
	 * 
	 * @return {@link FacesContext}
	 */

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
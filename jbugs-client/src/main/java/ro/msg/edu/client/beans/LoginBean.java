package ro.msg.edu.client.beans;

import java.io.Serializable;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	UserFacade userFacade;

	private UserDTO user = new UserDTO();

	private Locale locale = new Locale("");

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

	public void loginActionListener(ActionEvent event) {
		System.err.println("something something event from " + event.getComponent().getClientId());
	}

	public void setLocaleLanguege(String locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(locale));
	}

	public String processLogin() {

		if (userFacade.verifyLoggedInUser(user)) {

			userFacade.resetStatus(user);
			HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(false);
			session.setAttribute("username", user.getUsername());
			FacesContext context = FacesContext.getCurrentInstance();
			String message = context.getApplication().evaluateExpressionGet(context, "#{msg['login.title']}",
					String.class);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(message + " " + user.getUsername() + "!"));

			return "menu";
		} else

		{

			UserDTO userUpdated = userFacade.setStatus(user);
			if (userUpdated.getCounter() > 4) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User deactivated"));

			}

			FacesContext.getCurrentInstance().addMessage("loginForm:username",
					new FacesMessage("Password or Username wrong!"));
			return "login";
		}

	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public String processLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "login";
	}

}

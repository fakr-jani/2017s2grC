package ro.msg.edu.client.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.notification.boundary.NotificationFacade;
import ro.msg.edu.business.notification.dao.NotificationDAO;
import ro.msg.edu.business.notification.dto.NotificationDTO;
import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;

@ManagedBean
@SessionScoped
public class NotificationBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	@EJB
	private NotificationFacade notificationFacade;

	@EJB
	private UserFacade userFacade;

	public List<NotificationDTO> getNotifications() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String userName = (String) session.getAttribute("username");
		UserDTO logedinUser = userFacade.findUserbyUsername(userName);
		try {
			return notificationFacade.getAllNotifications(logedinUser);
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
			return new ArrayList<NotificationDTO>();
		}

	}

}

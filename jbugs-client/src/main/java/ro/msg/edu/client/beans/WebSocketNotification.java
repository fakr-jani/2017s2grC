package ro.msg.edu.client.beans;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.notification.boundary.NotificationFacade;
import ro.msg.edu.business.notification.dto.NotificationDTO;
import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;

@ServerEndpoint(value = "/notification", configurator = SocketConfigurator.class)
public class WebSocketNotification {

	static ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

	private static Set<Session> allSessions;
	private static String username;
	@EJB
	private NotificationFacade notificationFacade;
	@EJB
	private UserFacade userFacade;

	@OnOpen
	public void showTime(Session session, EndpointConfig config) {
		allSessions = session.getOpenSessions();
		username = (String) config.getUserProperties().get("user");
		if (allSessions.size() >= 1) {
			timer.scheduleAtFixedRate(() -> sendNotifications(session), 0, 1, TimeUnit.SECONDS);
		}

	}

	private void sendNotifications(Session session) {
		allSessions = session.getOpenSessions();
		List<NotificationDTO> notifications;
		for (Session sess : allSessions) {
			UserDTO user = userFacade.findUserbyUsername(username);

			try {
				notifications = notificationFacade.findNotificationsNotReceived(user);
				if (!notifications.isEmpty()) {
					for (NotificationDTO notification : notifications) {
						sess.getBasicRemote().sendText(notification.getMessage());
						notificationFacade.setReceivedTrue(notification);
					}
				}

			} catch (IOException | TechnicalException exception) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Session Expired"));
			}
		}
	}

}
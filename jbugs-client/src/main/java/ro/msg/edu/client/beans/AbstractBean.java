package ro.msg.edu.client.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ro.msg.edu.business.common.exception.JBugsException;

public class AbstractBean {
	public void handleExceptioni18n(JBugsException e) {
		FacesContext context = FacesContext.getCurrentInstance();
		String message = context.getApplication().evaluateExpressionGet(context, "#{msg['" + e.getMessage() + "']}",
				String.class);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}

	public void addMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}

	public void addMessage(String key, String message) {
		FacesContext.getCurrentInstance().addMessage(key, new FacesMessage(message));
	}
}

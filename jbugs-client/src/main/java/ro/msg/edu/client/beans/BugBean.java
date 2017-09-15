/**
 * 
 */
package ro.msg.edu.client.beans;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Handle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import ro.msg.edu.business.bug.boundary.BugFacade;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.JBugsException;
import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;

/**
 * 
 * @author Alex Noja
 * 
 */
@ManagedBean
@SessionScoped
public class BugBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	List<BugSeverityType> severityList = Arrays.asList(BugSeverityType.values());

	
	
	@EJB
	BugFacade bugFacade;

	private Date date2;

	private String selectedSeverity;

	
	private BugDTO newBug = new BugDTO();

	private BugDTO selectedBug = new BugDTO();

	public String createNewBug() {
		try {
			bugFacade.createBug(newBug);
		} catch (JBugsException e) {
			handleExceptioni18n(e);
		}
		return "addBug";
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	public String getSelectedSeverity() {
		return selectedSeverity;
	}

	public void setSelectedSeverity(String selectedSeverity) {
		this.selectedSeverity = selectedSeverity;
	}

	public List<BugSeverityType> getSeverityList() {
		return severityList;
	}

	public void setSeverityList(List<BugSeverityType> severityList) {
		this.severityList = severityList;
	}

	public BugDTO getNewBug() {
		return newBug;
	}

	public void setNewBug(BugDTO newBug) {
		this.newBug = newBug;
	}

	public BugDTO getSelectedBug() {
		return selectedBug;
	}

	public void setSelectedBug(BugDTO selectedBug) {
		this.selectedBug = selectedBug;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	
	Map<String, List<String>> statusGraph;

	@PostConstruct
	public void init() {
		statusGraph = new HashMap<>();
		statusGraph.put("OPEN", new ArrayList<>(Arrays.asList("REJECTED", "IN_PROGRESS")));
		statusGraph.put("IN_PROGRESS", new ArrayList<>(Arrays.asList("REJECTED", "INFO_NEEDED", "FIXED")));
		statusGraph.put("INFO_NEEDED", new ArrayList<>(Arrays.asList("IN_PROGRESS")));
		statusGraph.put("REJECTED", new ArrayList<>(Arrays.asList("CLOSED")));
		statusGraph.put("FIXED", new ArrayList<>(Arrays.asList("OPEN", "CLOSED")));
	}

	public List<String> getPossibleTransitionsFromCurrentBugStatus(String currentBugStatus) {
		return statusGraph.get(currentBugStatus);
	}

	public List<BugDTO> getAllBugs() {
		return bugFacade.findAllBugs();
	}

}

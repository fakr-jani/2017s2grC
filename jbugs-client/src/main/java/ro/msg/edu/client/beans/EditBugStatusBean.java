package ro.msg.edu.client.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import ro.msg.edu.business.bug.boundary.BugFacade;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;

@ManagedBean
@SessionScoped
public class EditBugStatusBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private BugFacade bugFacade;

	private List<BugDTO> allBugs = new ArrayList<>();

	@PostConstruct
	public void init() {
		allBugs = bugFacade.findAllBugs();
	}

	public List<BugDTO> getAllBugs() {
		return allBugs;
	}

	public void setAllBugs(List<BugDTO> allBugs) {
		this.allBugs = allBugs;
	}

	public void onCellEdit(CellEditEvent event) {
		BugDTO updatedBug = getAllBugs().get(event.getRowIndex());
		updatedBug.setStatus((BugStatusType) event.getNewValue());
		try {
			bugFacade.updateBugStatus(updatedBug);
			FacesMessage msg = new FacesMessage("Status Edited for " + updatedBug.getTitleBug());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
		}
	}

}

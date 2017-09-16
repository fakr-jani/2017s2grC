package ro.msg.edu.client.filters;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ro.msg.edu.business.bug.control.BugService;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;

/**
 * Filter class for Bugs.
 * 
 * @author maresb
 *
 */
@ManagedBean(name = "bugFilter")
@ViewScoped
public class BugFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{bugService}")
	private BugService bugService;

	private BugDTO selectedBug;

	private List<BugDTO> bugs;

	private List<BugDTO> filteredBugs;

	@PostConstruct
	public void init() {
		bugs = bugService.getBugs();
	}

	public List<BugDTO> getBugs() {
		return bugs;
	}

	public void setBugs(List<BugDTO> bugs) {
		this.bugs = bugs;
	}

	public List<BugDTO> getFilteredBugs() {
		return filteredBugs;
	}

	public void setFilteredBugs(List<BugDTO> filteredBugs) {
		this.filteredBugs = filteredBugs;
	}

	public BugService getBugService() {
		return bugService;
	}

	public void setBugService(BugService bugService) {
		this.bugService = bugService;
	}

	public List<BugStatusType> getStatus() {
		return bugService.getStatus();
	}

	public List<BugSeverityType> getSeverity() {
		return bugService.getSeverity();
	}

	public BugDTO getSelectedBug() {
		return selectedBug;
	}

	public void setSelectedBug(BugDTO selectedBug) {
		this.selectedBug = selectedBug;
	}

}

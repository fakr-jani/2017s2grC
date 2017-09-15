package ro.msg.edu.client.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ro.msg.edu.business.bug.boundary.BugFacade;
import ro.msg.edu.business.bug.dto.BugDTO;

@ManagedBean
@ViewScoped
public class BugBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private BugFacade bugFacade;

	private BugDTO bugDTO = new BugDTO();

	private BugDTO selectedBug = new BugDTO();

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
